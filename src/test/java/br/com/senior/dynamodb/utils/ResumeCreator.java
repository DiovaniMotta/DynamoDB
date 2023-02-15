package br.com.senior.dynamodb.utils;

import static br.com.senior.dynamodb.builder.ArrayBuilder.oneArray;
import static br.com.senior.dynamodb.builder.JSONObjectBuilder.oneJSONObject;
import static br.com.senior.dynamodb.builder.ResumeBuilder.oneResume;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import org.springframework.stereotype.Service;

import br.com.senior.dynamodb.entity.Resume;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Service
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ResumeCreator {

    List<String> cpfs = new ArrayList<>();
    List<UUID> identifiers = new ArrayList<>();
    List<String> genericValues = new ArrayList<>();

    private String generateGenericValues() {
        var genericValue = RandomizeValues.getGenericValue(10);
        if (genericValues.contains(genericValue)) {
            genericValue = generateGenericValues();
        }
        genericValues.add(genericValue);
        return genericValue;
    }

    private String generateCpf() {
        var cpf = RandomizeValues.cpf();
        if (cpfs.contains(cpf)) {
            cpf = generateCpf();
        }
        cpfs.add(cpf);
        return cpf;
    }

    public Resume getResume() {
        var cpf = generateCpf();
        var name = RandomizeValues.getName();
        var email = RandomizeValues.getEmail(name);
        var phoneContact = RandomizeValues.getPhoneContact();
        var genericValue = generateGenericValues();
        return oneResume() //
                .createKey() //
                .document(cpf) //
                .typeDocument("CPF") //
                .locale(new Locale("pt", "BR")).addKey() //
                .genericValue(genericValue) //
                .resume("name", name)//
                .resume("email", email)//
                .resume("phoneContact", oneArray() //
                        .put(phoneContact) //
                        .toArray()) //
                .resume("profiles", oneArray() //
                        .put(oneJSONObject() //
                                     .put("name", "Tecnologico")//
                                     .put("goals", oneArray() //
                                             .put("DHO – DESENVOLVIMENTO HUMANO E ORGANIZACIONAL") //
                                             .put("R&S – RECRUTAMENTO E SELEÇÃO") //
                                             .put("RH – ESTRATÉGICO") //
                                             .put("PO/PM – DESENVOLVIMENTO DE PRODUTO") //
                                             .put("DESENVOLVIMENTO DE NEGÓCIOS") //
                                             .put("NEGOCIAÇÕES ESTRATÉGICAS") //
                                             .toArray()) //
                                     .put("qualification", "Profissional com carreira generalista, " //
                                             + "atuando em indústrias e prestadores de serviços, " //
                                             + "com amplo conhecimento e desenvolvimento de gestão de processos, " //
                                             + "elaboração e disseminação de procedimentos, " //
                                             + "elaboração e desenvolvimento de pesquisas, " //
                                             + "gestão e desenvolvimento de projetos de estruturação e reestruturação organizacional, " //
                                             + "produtos e negócios, " //
                                             + "vivência em negociação estratégica e atendimento, " //
                                             + "gestão estratégica de pessoas, desenvolvimento humano e organizacional (DHO); " //
                                             + "vivência e desenvolvimento de projetos a partir das metodologias ágeis e frameworks de gestão")//
                                     .put("about_me", "As dinâmicas do relacionamento humano são a força motriz da minha vida. " //
                                             + "Sou cientista por natureza, apaixonada por transformação humana, sou líder por espírito. " //
                                             + "O olhar direcionado às pessoas, à visão sistêmica e holística, bem como, para a liderança transformacional, " //
                                             + "são o foco para o desenvolvimento das minhas atividades diárias. " //
                                             + "Acredito que as mudanças marcantes ocorridas no mundo são motivadas por pessoas e, diariamente, " //
                                             + "eu sou a pessoa que incentiva e apoia outras pessoas a promoverem mudanças conscientes, " //
                                             + "visando o lucro sustentável no longo prazo. Prezo pela cooperação entre as equipes valorizando a diversidade. " //
                                             + "Sou motivada por ajudar a transformação para melhor de: pessoas e organizações Valorizo o comprometimento, " //
                                             + "a transparência, a excelência, o reconhecimento, os relacionamentos genuínos, a ética e o sucesso.") //
                                     .put("habilities", oneArray() //
                                             .put(oneJSONObject()  //
                                                          .put("description", "Resiliência: Habilitade de formação e transformação, construção e descontrução.") //
                                                          .toJSON())//
                                             .put(oneJSONObject()  //
                                                          .put("description", "Resoluta: Habilidade para lidar com problemas, " //
                                                                  + "descobrir o que está \"errado\" e corrigi-los Atividade intelectual intensa: " //
                                                                  + "uma idéia para cada momento, introspectiva com apreciação de discussões intelectuais.") //
                                                          .toJSON()) //
                                             .put(oneJSONObject()  //
                                                          .put("description", "Hands-on: capacidade de colocar em prática idéia e projetos, além de, influenciar pessoas para construir os obejtivos.") //
                                                          .toJSON())//
                                             .put(oneJSONObject()  //
                                                          .put("description", "Estudiosa: O quê? Como? Quando? Onde? Quem? Por quê? " //
                                                                  + "São sempre questionamentos que estão comigo para serem resolvidos, assim, busco conhecimento para solucioná-los, o tempo todo.") //
                                                          .toJSON())//
                                             .put(oneJSONObject()  //
                                                          .put("description", "Estudiosa: O quê? Como? Quando? Onde? Quem? Por quê? " //
                                                                  + "Comunicação: Gosto de contribuir acerca das discussões, se tenho espaço, " //
                                                                  + "gosto de participar dos diálogos. Gosto de fazer apresentações para o grande público. Conversas francas, concisas e diretas aquecem meu coração.") //
                                                          .toJSON())//
                                             .toArray()) //
                                     .put("education", oneArray() //
                                             .put(oneJSONObject().put("name", "MBA Comportamento organizacional, gestão estratégica e desenvolvimento de pessoas") //
                                                          .put("institution_name", "Faculdade NovoOeste") //
                                                          .put("startDate", LocalDate.of(2021, 01, 01)) //
                                                          .put("endDate", LocalDate.of(2021, 12, 31)) //
                                                          .put("conclusion_year", 2021) //
                                                          .put("workload", 400) //
                                                          .toJSON()) //
                                             .put(oneJSONObject().put("name", "Especialista em Fenomenologia Sistêmica com Ênfase em Constelações Familiares e Organizacionais") //
                                                          .put("institution_name", "Faculdade Integrada Espírita") //
                                                          .put("startDate", LocalDate.of(2018, 01, 01)) //
                                                          .put("endDate", LocalDate.of(2019, 12, 31)) //
                                                          .put("conclusion_year", 2019) //
                                                          .put("workload", 400) //
                                                          .toJSON()) //
                                             .put(oneJSONObject().put("name", "Formação em Constelação Familiar Sistêmica e Organizacional") //
                                                          .put("institution_name", "Faculdade Facon – Conchas") //
                                                          .put("startDate", LocalDate.of(2017, 01, 01)) //
                                                          .put("endDate", LocalDate.of(2017, 12, 31)) //
                                                          .put("conclusion_year", 2017) //
                                                          .put("workload", 400) //
                                                          .toJSON()) //
                                             .put(oneJSONObject().put("name", "Bacharela em Administração com habilitação em Marketing e Propagand") //
                                                          .put("institution_name", "Faculdade Internacional de Curitiba - Curitiba") //
                                                          .put("startDate", LocalDate.of(2009, 01, 01)) //
                                                          .put("endDate", LocalDate.of(2014, 12, 31)) //
                                                          .put("conclusion_year", 2014) //
                                                          .put("workload", 3312) //
                                                          .toJSON()) //
                                             .toArray()) //
                                     .put("courses", oneArray() //
                                             .put(oneJSONObject().put("name", "SCRUM Product Owner") //
                                                          .put("institution_name", "Trainning Education") //
                                                          .put("workload", 16) //
                                                          .toJSON())//
                                             .put(oneJSONObject().put("name", "NeuroGestão: Desenvolvendo o potencial humano com a neurociência") //
                                                          .put("institution_name", "Instituto Brasileiro de Neuromarketing") //
                                                          .put("workload", 20) //
                                                          .toJSON())//
                                             .put(oneJSONObject().put("name", "Gestão Estratégica de Pessoas e Planos de Carreira") //
                                                          .put("institution_name", "Escola Nacional de Administração Pública, ENAP") //
                                                          .put("workload", 20) //
                                                          .toJSON())//
                                             .put(oneJSONObject().put("name", "Introdução à Gestão de Recursos Humanos") //
                                                          .put("institution_name", "Fundação Getúlio Vargas, FGV") //
                                                          .put("workload", 5) //
                                                          .toJSON())//
                                             .put(oneJSONObject().put("name", "Gestão Pessoal - Base da Liderança.") //
                                                          .put("institution_name", "Escola Nacional de Administração Pública, ENAP") //
                                                          .put("workload", 50) //
                                                          .toJSON())//
                                             .put(oneJSONObject().put("name", "Introdução à Hipnose") //
                                                          .put("institution_name", "Sociedade Brasileira de Psicanálise Integrativa, SBPI") //
                                                          .put("workload", 36) //
                                                          .toJSON())//
                                             .put(oneJSONObject().put("name", "Gestão de processos") //
                                                          .put("institution_name", "Faculdade Internacional de Curitiba") //
                                                          .put("workload", 40) //
                                                          .toJSON())//
                                             .put(oneJSONObject().put("name", "Analista comportament") //
                                                          .put("institution_name", "Metodologia DISC") //
                                                          .put("workload", 20) //
                                                          .toJSON())//
                                             .toArray())//
                                     .put("professional_experiences", oneArray() //
                                             .put(oneJSONObject() //
                                                          .put("company", "Senior Sistemas") //
                                                          .put("jobposition", "Product Owner") //
                                                          .put("startDate", LocalDate.of(2021, 12, 06)) //
                                                          .put("description", "Product owner responsável pelo módulo de gestão de recrutamento e seleção da linha de HCM da Senior, sendo as principais responsabilidades e atributos:"//
                                                                  + "• Elaborar pesquisas para desenvolvimento do produto;"//
                                                                  + "• Elaborar o Discovery; "//
                                                                  + "• Acompanhar o Delivery; "//
                                                                  + "• Gestão do product discovery; "//
                                                                  + "• Elaborar e priorizar o refinamento do backlog do produto;"//
                                                                  + "• Planejar releases;"//
                                                                  + "• Gerenciar a interface entre as diversas áreas da organização (tecnologia/desenvolvimento, ux, atendimento, comercial, marketing); "//
                                                                  + "• Acompanhar o mercado e a concorrência, para melhoria do produto, bem como, dos lançamentos de novas features e ofertas, realizando a execução do go-to-market "//
                                                                  + "• Conduzir a comunicação e os testes com os clientes pilotos. "//
                                                                  + "• Comunicar o progresso, metas (metodologia OKR) e desafios aos stakeholders internos e externos") //
                                                          .toJSON()) //
                                             .put(oneJSONObject() //
                                                          .put("company", "Comercial São Francisco") //
                                                          .put("jobposition", "Coordenadora de Desenvolvimento Humano e Organizacional") //
                                                          .put("startDate", LocalDate.of(2021, 07, 05)) //
                                                          .put("endDate", LocalDate.of(2021, 10, 01)) //
                                                          .put("description", "Aqui, eu fui contratada para desenvolver um projeto de implementação sobre Recursos Humanos Estratégicos para duas empresas: " //
                                                                  + "Leme Laboratório e Ceopar – Centro Odontológico do Paraná, com foco em" + "desenvolvimento humano e organizacional (DHO), " //
                                                                  + "atendendo às necessidades da empresa, bem como, conduzir todo o trabalho de implementação desse projeto, abordando principalmente os tópicos:"//
                                                                  + "• Reestruturação da equipe de trabalho das áreas administrativas e produtiva"//
                                                                  + "• Estruturação do processo de Recrutamento e Seleção (R&S) "//
                                                                  + "• Onbording das novas contratações "//
                                                                  + "• Treinamento e Desenvolvimento (T&D)"//
                                                                  + "• Avaliação de Desempenho – feedback de avaliação "//
                                                                  + "• Elaboração da Política de Recursos Humanos de acordo com as diretrizes da Diretoria "//
                                                                  + "• Plano de Desenvolvimento Individual (PDI) • Mapeamento comportamental – Individual e das equipes de trabalho com feedback de desenvolvimento individual e em equipe "//
                                                                  + "• Implementação do Programa de Desenvolvimento da liderança "//
                                                                  + "• Métodos e meios de Comunicação organizacional " //
                                                                  + "• Estruturação de cargos e salários – jornada do colaborador"//
                                                                  + "• Estruturação da Política de benefícios • Acompanhamento das demissões – entrevista de desligamento "//
                                                                  + "• Employee Experience – Acompanhamento de jornada do colaborador da contratação ao desligamento"//
                                                                  + "• Employer Branding – Desenvolvimento das ações direcionadas à divulgação da marca empregadora"//
                                                                  + "• Analista Comportamental (metodologia disc - Sólides)"//
                                                                  + "• Elaborar, apresentar e propor tratativas para a Pesquisa de Clima Organizacional "//
                                                                  + "• Elaborar e implementar estratégias de tratativa de cultura e clima organizacional") //
                                                          .toJSON()) //
                                             .put(oneJSONObject() //
                                                          .put("company", "Desenvolvimento humano e organizacional (DHO") //
                                                          .put("jobposition", "Administradora, professora, terapeuta, facilitadora de processo de desenvolvimento humano e organizacional (DHO)") //
                                                          .put("startDate", LocalDate.of(2017, 11, 27)) //
                                                          .put("endDate", LocalDate.of(2021, 07, 07)) //
                                                          .put("description", "Consultora / Facilitadora (ORGANIZAÇÕES E LIDERANÇAS): " //
                                                                  + "Elaborar, planejar e ministrar treinamentos com o objetivo de auxiliar " //
                                                                  + "e promover o desenvolvimento organizacional em caráter sistêmico e " //
                                                                  + "holístico, trabalho voltado para a micro, pequena e média empresa," //
                                                                  + "desenvolvendo uma visão integral sobre a forma de gestão empresarial," //
                                                                  + "ampliando a percepção e a consciência do empreendedor, visando " //
                                                                  + "construir negócios mais conscientes e saudáveis, promovendo e disseminando a sustentabilidade econômica, " //
                                                                  + "ambiental e social. Esse trabalho não é um fim, mas um meio, que proporciona uma gestão mais " //
                                                                  + "humana, saudável e mais sustentável para o negócio e toda a cadeia de envolvidos, ao longo do tempo." //
                                                                  + "Professora independente (PESSOAS): Ministrar workshops sobre temas relacionados à Constelação Sistêmica; " //
                                                                  + "Elaborar, planejar e ministrar treinamentos vivenciais em Constelação Familiar e Organizacional" //
                                                                  + "voltado ao desenvolvimento humano e organizacional; Promover e disseminar a visão sistêmica e holística " //
                                                                  + "conectando a espiritualidade na prática diária") //
                                                          .toJSON()) //
                                             .put(oneJSONObject() //
                                                          .put("company", "JASMINE ALIMENTOS") //
                                                          .put("jobposition", "Analista de Processos Logísticos") //
                                                          .put("startDate", LocalDate.of(2014, 04, 01)) //
                                                          .put("endDate", LocalDate.of(2021, 11, 27)) //
                                                          .put("description", "Mapear processos, desenhar fluxos de atividades, analisar e definir métodos de trabalho, " //
                                                                  + "definir estratégias para resolução de problemas, estruturar documentos e procedimentos, " //
                                                                  + "adequar normas de qualidade, elaborar processos e fluxogramas, padronizar processos de áreas "//
                                                                  + "ligadas ao departamento de logística, conduzir reuniões com equipe de trabalho, apresentar resultados.") //
                                                          .toJSON()) //
                                             .toArray()) //
                                     .toJSON()) //
                        .toArray()) //
                .toResume();
    }
}
