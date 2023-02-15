package br.com.senior.dynamodb.utils;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.util.StringUtils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RandomizeValues {

    private static final Random RANDOM = new Random();

    private static final List<String> NAMES = List.of("Adriana", "Ana", "Maria", "Sandra", "Juliana", "Helena", "Madalena", "Antônio", "Carlos", "Francisco", "João", //
                                                      "José", "Afonso", "Lourenço", "Bruna", "Camila", "Jéssica", "Letícia", "Amanda", "Betina", "Lucas", //
                                                      "Luís", "Luiz", "Mateus", "Guilherme", "Pedro", "Helena", "Heloísa", "Maria Clara", "Maria Cecília", "Maria Júlia", //
                                                      "Maitê", "Maria Eduarda", "Elisa", "Lorena", "Maria Luíza", "Alice", "Isabella", "Júlia", "Sophia", "Laura", //
                                                      "Valentina", "Olívia", "Cecília", "Beatriz", "Manuela", "Luiza", "Greta", "Heitor", "Samuel", "João Miguel", //
                                                      "Enzo Gabriel", "Noah", "Pedro", "Ravi", "Lorenzo", "Benício", "Isaac", "Arthur", "Miguel", "Davi", "Gabriel", //
                                                      "Bernardo", "Nicolas", "Gael", "Valentim", "Benjamim", "Santiago", "Enzo", "Téo", "Ben");

    private static final List<String> LAST_NAMES = List.of("Andrade", "Barbosa", "Barros", "Batista", "Borges", "Campos", "Cardoso", "Carvalho", "Castro", "Costa", //
                                                         "Dias", "Duarte", "Freitas", "Fernandes", "Ferreira", "Garcia", "Gomes", "Gonçalves", "Lima", "Lopes", //
                                                         "Machado", "Marques", "Martins", "Medeiros", "Melo", "Mendes", "Miranda", "Monteiro", "Moraes", "Moreira", //
                                                         "Moura", "Nascimento", "Nunes", "Oliveira", "Pereira", "Ramos", "Reis", "Ribeiro", "Rocha", "Santana", //
                                                         "Santos", "Silva", "Soares", "Souza", "Teixeira", "Vieira");

    private static final List<String> EMAIL_PROVIDERS = List.of("@gmail.com", "@outlook.com", "@yahoo.com.br", "@terra.com.br", "@oi.com.br", "@icloud.com", "@hotmail.com", "@govbr.com.br", "@globo.com", "@bol.com.br");

    private static int randomiza(int n) {
        int ranNum = (int) (Math.random() * n);
        return ranNum;
    }

    private static int mod(int dividendo, int divisor) {
        return (int) Math.round(dividendo - (Math.floor(dividendo / divisor) * divisor));
    }

    public static String cpf() {
        int n = 9;
        int numberOne = randomiza(n);
        int numberTwo = randomiza(n);
        int numberThree = randomiza(n);
        int numberFour = randomiza(n);
        int numberFive = randomiza(n);
        int numberSix = randomiza(n);
        int numberSeven = randomiza(n);
        int numberEight = randomiza(n);
        int numberNine = randomiza(n);
        int digitOne = numberNine * 2 + numberEight * 3 + numberSeven * 4 + numberSix * 5 + numberFive * 6 + numberFour * 7 + numberThree * 8 + numberTwo * 9 + numberOne * 10;
        digitOne = 11 - (mod(digitOne, 11));
        if (digitOne >= 10) digitOne = 0;
        int digitTwo = digitOne * 2 + numberNine * 3 + numberEight * 4 + numberSeven * 5 + numberSix * 6 + numberFive * 7 + numberFour * 8 + numberThree * 9 + numberTwo * 10 + numberOne * 11;
        digitTwo = 11 - (mod(digitTwo, 11));
        if (digitTwo >= 10) digitTwo = 0;
        return String.format("%d%d%d.%d%d%d.%d%d%d-%d%d", numberOne, numberTwo, numberThree, numberFour, numberFive, numberSix, numberSeven, numberEight, numberNine, digitOne, digitTwo);
    }

    public static int getLong(int maxValue) {
        return RANDOM.nextInt(maxValue);
    }

    public static String getName(){
        var nameIndex = RANDOM.nextInt(NAMES.size());
        var name = NAMES.get(nameIndex);
        var lastNameIndex = RANDOM.nextInt(LAST_NAMES.size());
        var lastName = LAST_NAMES.get(lastNameIndex);
        return String.format("%s %s", name, lastName);
    }

    public static  String getEmail(String name){
        var emailIndex = RANDOM.nextInt(EMAIL_PROVIDERS.size());
        var emailProvider = EMAIL_PROVIDERS.get(emailIndex);
        return String.format("%s%s", name.toLowerCase().replace(" ", ""), emailProvider);
    }

    public static String getPhoneContact(){
        var ddd1 = ThreadLocalRandom.current().nextInt(1, 9);
        var ddd2 = RANDOM.nextInt(9);
        var number1 = RANDOM.nextInt(9);
        var number2 = RANDOM.nextInt(9);
        var number3 = RANDOM.nextInt(9);
        var number4 = RANDOM.nextInt(9);
        var number5 = RANDOM.nextInt(9);
        var number6 = RANDOM.nextInt(9);
        var number7 = RANDOM.nextInt(9);
        var number8 = RANDOM.nextInt(9);
        return String.format("(%d%d) 9%d%d%d%d-%d%d%d%d", ddd1, ddd2, number1, number2, number3,number4, number5, number6, number7, number8);
    }

    public static String getGenericValue(int length) {
        return RandomStringUtils.randomAlphabetic(length);
    }
}
