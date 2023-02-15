package br.com.senior.dynamodb.service;

import static br.com.senior.dynamodb.builder.ResumeBuilder.ResumeKeyBuilder.oneResumeKey;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.senior.dynamodb.entity.Resume;
import br.com.senior.dynamodb.entity.ResumeKey;
import br.com.senior.dynamodb.repository.ResumeRepository;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ResumeService {

    @Autowired
    ResumeRepository repository;
    List<String> documents;
    AtomicLong erros = new AtomicLong(0L);
    ConcurrentLinkedQueue<String> listaErros = new ConcurrentLinkedQueue<>();

    ConcurrentLinkedQueue<Long> timesByFind = new ConcurrentLinkedQueue<>();

    public void addTime(long start, long end) {
        timesByFind.add(end - start);
    }

    private long average(ConcurrentLinkedQueue<Long> liczby) {
        return liczby.stream().reduce(0L, Long::sum) / liczby.size();
    }

    private long median(ConcurrentLinkedQueue<Long> liczby) {
        List<Long> valores = liczby.stream().collect(Collectors.toList());
        Collections.sort(valores);
        int n = valores.size() / 2;
        long m;
        if (liczby.size() % 2 == 0) m = (valores.get(n) + valores.get(n - 1)) / 2;
        else m = valores.get(n);
        return m;
    }

    private List<String> getRandomList(int requisicao) {
        int min = 0;
        int max = documents.size() - requisicao;
        int valor = (int) ((Math.random() * (max - min)) + min);

        return documents.subList(valor, valor + requisicao);
    }

    public void loadAllByDocument() {
        System.out.println("Iniciando busca de IDs");
        Long start = System.currentTimeMillis();
        documents = StreamSupport //
                .stream(repository.findAll(PageRequest.of(1, 5)).spliterator(), false) //
                .map(resume -> resume.getKey().getDocument()) //
                .collect(Collectors.toList());
        long elapsed = System.currentTimeMillis() - start;
        System.out.println(String.format("Tempo total de buscas por IDs: %d (ms)\n", elapsed));
    }

    public void prepareExecution(int amountRequest) {
        System.out.println(String.format("Iniciando com %d requisição", amountRequest));
        searchById(amountRequest);
        System.out.println(String.format("Finalizado com %d requisição", amountRequest));
        printReport(amountRequest);
    }

    private void searchById(int amountRequest) {
        List<String> selectDocuments = getRandomList(amountRequest);
        ExecutorService exec = Executors.newCachedThreadPool();
        List<Callable<Resume>> tasks = new ArrayList<Callable<Resume>>();
        selectDocuments.forEach(document -> {
            Callable<Resume> callable = new Callable<Resume>() {

                @Override
                public Resume call() throws Exception {
                    try {
                        Long start = System.currentTimeMillis();
                        ResumeKey key = oneResumeKey().document(document) //
                                .typeDocument("CPF") //
                                .locale(new Locale("pt", "BR")).toKey();
                        var resume = repository.findById(key);
                        Long end = System.currentTimeMillis();
                        addTime(start, end);
                        return resume.orElseThrow(() -> {
                            throw new IllegalArgumentException("Registro não encontrado!");
                        });
                    } catch (Exception e) {
                        erros.incrementAndGet();
                        listaErros.add(e.getMessage());
                        return null;
                    }
                }
            };
        });
    }

    private void printReport(int amountRequest){
        System.out.println("Tempos de resposta no DynamoDB");
        System.out.println(String.format("Mínimo: %d (ms)", Collections.min(timesByFind)));
        System.out.println(String.format("Máximo: %d (ms)", Collections.max(timesByFind)));
        System.out.println(String.format("Mediana: %d (ms)", median(timesByFind)));
        System.out.println(String.format("Media: %d (ms)", average(timesByFind)));
        System.out.println(String.format("Total de erros: %d", erros.get()));
        System.out.println(String.format("Total de requisições: %d %n", amountRequest));
        erros.set(0L);
        timesByFind.clear();
    }
}
