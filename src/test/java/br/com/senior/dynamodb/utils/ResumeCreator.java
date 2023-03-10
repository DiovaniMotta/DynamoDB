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
                                             .put("DHO ??? DESENVOLVIMENTO HUMANO E ORGANIZACIONAL") //
                                             .put("R&S ??? RECRUTAMENTO E SELE????O") //
                                             .put("RH ??? ESTRAT??GICO") //
                                             .put("PO/PM ??? DESENVOLVIMENTO DE PRODUTO") //
                                             .put("DESENVOLVIMENTO DE NEG??CIOS") //
                                             .put("NEGOCIA????ES ESTRAT??GICAS") //
                                             .toArray()) //
                                     .put("qualification", "Profissional com carreira generalista, " //
                                             + "atuando em ind??strias e prestadores de servi??os, " //
                                             + "com amplo conhecimento e desenvolvimento de gest??o de processos, " //
                                             + "elabora????o e dissemina????o de procedimentos, " //
                                             + "elabora????o e desenvolvimento de pesquisas, " //
                                             + "gest??o e desenvolvimento de projetos de estrutura????o e reestrutura????o organizacional, " //
                                             + "produtos e neg??cios, " //
                                             + "viv??ncia em negocia????o estrat??gica e atendimento, " //
                                             + "gest??o estrat??gica de pessoas, desenvolvimento humano e organizacional (DHO); " //
                                             + "viv??ncia e desenvolvimento de projetos a partir das metodologias ??geis e frameworks de gest??o")//
                                     .put("about_me", "As din??micas do relacionamento humano s??o a for??a motriz da minha vida. " //
                                             + "Sou cientista por natureza, apaixonada por transforma????o humana, sou l??der por esp??rito. " //
                                             + "O olhar direcionado ??s pessoas, ?? vis??o sist??mica e hol??stica, bem como, para a lideran??a transformacional, " //
                                             + "s??o o foco para o desenvolvimento das minhas atividades di??rias. " //
                                             + "Acredito que as mudan??as marcantes ocorridas no mundo s??o motivadas por pessoas e, diariamente, " //
                                             + "eu sou a pessoa que incentiva e apoia outras pessoas a promoverem mudan??as conscientes, " //
                                             + "visando o lucro sustent??vel no longo prazo. Prezo pela coopera????o entre as equipes valorizando a diversidade. " //
                                             + "Sou motivada por ajudar a transforma????o para melhor de: pessoas e organiza????es Valorizo o comprometimento, " //
                                             + "a transpar??ncia, a excel??ncia, o reconhecimento, os relacionamentos genu??nos, a ??tica e o sucesso.") //
                                     .put("habilities", oneArray() //
                                             .put(oneJSONObject()  //
                                                          .put("description", "Resili??ncia: Habilitade de forma????o e transforma????o, constru????o e descontru????o.") //
                                                          .toJSON())//
                                             .put(oneJSONObject()  //
                                                          .put("description", "Resoluta: Habilidade para lidar com problemas, " //
                                                                  + "descobrir o que est?? \"errado\" e corrigi-los Atividade intelectual intensa: " //
                                                                  + "uma id??ia para cada momento, introspectiva com aprecia????o de discuss??es intelectuais.") //
                                                          .toJSON()) //
                                             .put(oneJSONObject()  //
                                                          .put("description", "Hands-on: capacidade de colocar em pr??tica id??ia e projetos, al??m de, influenciar pessoas para construir os obejtivos.") //
                                                          .toJSON())//
                                             .put(oneJSONObject()  //
                                                          .put("description", "Estudiosa: O qu??? Como? Quando? Onde? Quem? Por qu??? " //
                                                                  + "S??o sempre questionamentos que est??o comigo para serem resolvidos, assim, busco conhecimento para solucion??-los, o tempo todo.") //
                                                          .toJSON())//
                                             .put(oneJSONObject()  //
                                                          .put("description", "Estudiosa: O qu??? Como? Quando? Onde? Quem? Por qu??? " //
                                                                  + "Comunica????o: Gosto de contribuir acerca das discuss??es, se tenho espa??o, " //
                                                                  + "gosto de participar dos di??logos. Gosto de fazer apresenta????es para o grande p??blico. Conversas francas, concisas e diretas aquecem meu cora????o.") //
                                                          .toJSON())//
                                             .toArray()) //
                                     .put("education", oneArray() //
                                             .put(oneJSONObject().put("name", "MBA Comportamento organizacional, gest??o estrat??gica e desenvolvimento de pessoas") //
                                                          .put("institution_name", "Faculdade NovoOeste") //
                                                          .put("startDate", LocalDate.of(2021, 01, 01)) //
                                                          .put("endDate", LocalDate.of(2021, 12, 31)) //
                                                          .put("conclusion_year", 2021) //
                                                          .put("workload", 400) //
                                                          .toJSON()) //
                                             .put(oneJSONObject().put("name", "Especialista em Fenomenologia Sist??mica com ??nfase em Constela????es Familiares e Organizacionais") //
                                                          .put("institution_name", "Faculdade Integrada Esp??rita") //
                                                          .put("startDate", LocalDate.of(2018, 01, 01)) //
                                                          .put("endDate", LocalDate.of(2019, 12, 31)) //
                                                          .put("conclusion_year", 2019) //
                                                          .put("workload", 400) //
                                                          .toJSON()) //
                                             .put(oneJSONObject().put("name", "Forma????o em Constela????o Familiar Sist??mica e Organizacional") //
                                                          .put("institution_name", "Faculdade Facon ??? Conchas") //
                                                          .put("startDate", LocalDate.of(2017, 01, 01)) //
                                                          .put("endDate", LocalDate.of(2017, 12, 31)) //
                                                          .put("conclusion_year", 2017) //
                                                          .put("workload", 400) //
                                                          .toJSON()) //
                                             .put(oneJSONObject().put("name", "Bacharela em Administra????o com habilita????o em Marketing e Propagand") //
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
                                             .put(oneJSONObject().put("name", "NeuroGest??o: Desenvolvendo o potencial humano com a neuroci??ncia") //
                                                          .put("institution_name", "Instituto Brasileiro de Neuromarketing") //
                                                          .put("workload", 20) //
                                                          .toJSON())//
                                             .put(oneJSONObject().put("name", "Gest??o Estrat??gica de Pessoas e Planos de Carreira") //
                                                          .put("institution_name", "Escola Nacional de Administra????o P??blica, ENAP") //
                                                          .put("workload", 20) //
                                                          .toJSON())//
                                             .put(oneJSONObject().put("name", "Introdu????o ?? Gest??o de Recursos Humanos") //
                                                          .put("institution_name", "Funda????o Get??lio Vargas, FGV") //
                                                          .put("workload", 5) //
                                                          .toJSON())//
                                             .put(oneJSONObject().put("name", "Gest??o Pessoal - Base da Lideran??a.") //
                                                          .put("institution_name", "Escola Nacional de Administra????o P??blica, ENAP") //
                                                          .put("workload", 50) //
                                                          .toJSON())//
                                             .put(oneJSONObject().put("name", "Introdu????o ?? Hipnose") //
                                                          .put("institution_name", "Sociedade Brasileira de Psican??lise Integrativa, SBPI") //
                                                          .put("workload", 36) //
                                                          .toJSON())//
                                             .put(oneJSONObject().put("name", "Gest??o de processos") //
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
                                                          .put("description", "Product owner respons??vel pelo m??dulo de gest??o de recrutamento e sele????o da linha de HCM da Senior, sendo as principais responsabilidades e atributos:"//
                                                                  + "??? Elaborar pesquisas para desenvolvimento do produto;"//
                                                                  + "??? Elaborar o Discovery; "//
                                                                  + "??? Acompanhar o Delivery; "//
                                                                  + "??? Gest??o do product discovery; "//
                                                                  + "??? Elaborar e priorizar o refinamento do backlog do produto;"//
                                                                  + "??? Planejar releases;"//
                                                                  + "??? Gerenciar a interface entre as diversas ??reas da organiza????o (tecnologia/desenvolvimento, ux, atendimento, comercial, marketing); "//
                                                                  + "??? Acompanhar o mercado e a concorr??ncia, para melhoria do produto, bem como, dos lan??amentos de novas features e ofertas, realizando a execu????o do go-to-market "//
                                                                  + "??? Conduzir a comunica????o e os testes com os clientes pilotos. "//
                                                                  + "??? Comunicar o progresso, metas (metodologia OKR) e desafios aos stakeholders internos e externos") //
                                                          .toJSON()) //
                                             .put(oneJSONObject() //
                                                          .put("company", "Comercial S??o Francisco") //
                                                          .put("jobposition", "Coordenadora de Desenvolvimento Humano e Organizacional") //
                                                          .put("startDate", LocalDate.of(2021, 07, 05)) //
                                                          .put("endDate", LocalDate.of(2021, 10, 01)) //
                                                          .put("description", "Aqui, eu fui contratada para desenvolver um projeto de implementa????o sobre Recursos Humanos Estrat??gicos para duas empresas: " //
                                                                  + "Leme Laborat??rio e Ceopar ??? Centro Odontol??gico do Paran??, com foco em" + "desenvolvimento humano e organizacional (DHO), " //
                                                                  + "atendendo ??s necessidades da empresa, bem como, conduzir todo o trabalho de implementa????o desse projeto, abordando principalmente os t??picos:"//
                                                                  + "??? Reestrutura????o da equipe de trabalho das ??reas administrativas e produtiva"//
                                                                  + "??? Estrutura????o do processo de Recrutamento e Sele????o (R&S) "//
                                                                  + "??? Onbording das novas contrata????es "//
                                                                  + "??? Treinamento e Desenvolvimento (T&D)"//
                                                                  + "??? Avalia????o de Desempenho ??? feedback de avalia????o "//
                                                                  + "??? Elabora????o da Pol??tica de Recursos Humanos de acordo com as diretrizes da Diretoria "//
                                                                  + "??? Plano de Desenvolvimento Individual (PDI) ??? Mapeamento comportamental ??? Individual e das equipes de trabalho com feedback de desenvolvimento individual e em equipe "//
                                                                  + "??? Implementa????o do Programa de Desenvolvimento da lideran??a "//
                                                                  + "??? M??todos e meios de Comunica????o organizacional " //
                                                                  + "??? Estrutura????o de cargos e sal??rios ??? jornada do colaborador"//
                                                                  + "??? Estrutura????o da Pol??tica de benef??cios ??? Acompanhamento das demiss??es ??? entrevista de desligamento "//
                                                                  + "??? Employee Experience ??? Acompanhamento de jornada do colaborador da contrata????o ao desligamento"//
                                                                  + "??? Employer Branding ??? Desenvolvimento das a????es direcionadas ?? divulga????o da marca empregadora"//
                                                                  + "??? Analista Comportamental (metodologia disc - S??lides)"//
                                                                  + "??? Elaborar, apresentar e propor tratativas para a Pesquisa de Clima Organizacional "//
                                                                  + "??? Elaborar e implementar estrat??gias de tratativa de cultura e clima organizacional") //
                                                          .toJSON()) //
                                             .put(oneJSONObject() //
                                                          .put("company", "Desenvolvimento humano e organizacional (DHO") //
                                                          .put("jobposition", "Administradora, professora, terapeuta, facilitadora de processo de desenvolvimento humano e organizacional (DHO)") //
                                                          .put("startDate", LocalDate.of(2017, 11, 27)) //
                                                          .put("endDate", LocalDate.of(2021, 07, 07)) //
                                                          .put("description", "Consultora / Facilitadora (ORGANIZA????ES E LIDERAN??AS): " //
                                                                  + "Elaborar, planejar e ministrar treinamentos com o objetivo de auxiliar " //
                                                                  + "e promover o desenvolvimento organizacional em car??ter sist??mico e " //
                                                                  + "hol??stico, trabalho voltado para a micro, pequena e m??dia empresa," //
                                                                  + "desenvolvendo uma vis??o integral sobre a forma de gest??o empresarial," //
                                                                  + "ampliando a percep????o e a consci??ncia do empreendedor, visando " //
                                                                  + "construir neg??cios mais conscientes e saud??veis, promovendo e disseminando a sustentabilidade econ??mica, " //
                                                                  + "ambiental e social. Esse trabalho n??o ?? um fim, mas um meio, que proporciona uma gest??o mais " //
                                                                  + "humana, saud??vel e mais sustent??vel para o neg??cio e toda a cadeia de envolvidos, ao longo do tempo." //
                                                                  + "Professora independente (PESSOAS): Ministrar workshops sobre temas relacionados ?? Constela????o Sist??mica; " //
                                                                  + "Elaborar, planejar e ministrar treinamentos vivenciais em Constela????o Familiar e Organizacional" //
                                                                  + "voltado ao desenvolvimento humano e organizacional; Promover e disseminar a vis??o sist??mica e hol??stica " //
                                                                  + "conectando a espiritualidade na pr??tica di??ria") //
                                                          .toJSON()) //
                                             .put(oneJSONObject() //
                                                          .put("company", "JASMINE ALIMENTOS") //
                                                          .put("jobposition", "Analista de Processos Log??sticos") //
                                                          .put("startDate", LocalDate.of(2014, 04, 01)) //
                                                          .put("endDate", LocalDate.of(2021, 11, 27)) //
                                                          .put("description", "Mapear processos, desenhar fluxos de atividades, analisar e definir m??todos de trabalho, " //
                                                                  + "definir estrat??gias para resolu????o de problemas, estruturar documentos e procedimentos, " //
                                                                  + "adequar normas de qualidade, elaborar processos e fluxogramas, padronizar processos de ??reas "//
                                                                  + "ligadas ao departamento de log??stica, conduzir reuni??es com equipe de trabalho, apresentar resultados.") //
                                                          .toJSON()) //
                                             .toArray()) //
                                     .toJSON()) //
                        .toArray()) //
                .toResume();
    }
}
