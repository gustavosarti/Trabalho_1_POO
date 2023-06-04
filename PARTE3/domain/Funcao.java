package domain;
import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

//Serialização
//============================================
import java.io.FileInputStream;
import java.io.ObjectInputStream;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
//============================================

public class Funcao {

    // Scanner para leitura de entrada do usuário
    private static Scanner sc = new Scanner(System.in);

    // HashMaps para armazenar os dados do programa
    private static Map<String, Curso> mapaDeCursos = new HashMap<>();
    private static Map<String, Disciplina> mapaDeDisciplinas = new HashMap<>();
    private static Map<Integer, Aluno> mapaDeAlunos = new HashMap<>();
    private static Map<Integer, AlunoGraduacao> mapaDeAlunosGraduacao = new HashMap<>();
    private static Map<Integer, AlunoPos> mapaDeAlunosPos = new HashMap<>();
    private static Map<String, Avaliacao> mapaDeAvaliacoes = new HashMap<>();
    private static Map<String, Avaliacao> mapaDeProvas = new HashMap<>();
    private static Map<String, Trabalho> mapaDeTrabalhos = new HashMap<>();


    // Método para converter uma String em LocalDate
    public static LocalDate converteParaData(String data) {

        try{

            // Define o formato da data como "dd/MM/yyyy"
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            // Converte a String em LocalDate usando o formato definido
            return LocalDate.parse(data, formatter);

        }catch (DateTimeParseException e) {
        
            System.out.println("Erro de formatação.");
            return null;

        }
  
    }

    // CASE 1 =======================================================================
  
    // Método para cadastrar um curso
    public static void cadastrarCurso() {

        try{
    
            // Solicita o código do curso
            System.out.println("Escreva o codigo do curso:");
            String codigoDoCurso = sc.nextLine();
    
            // Solicita o nome do curso
            System.out.println("Escreva o nome do curso:");
            String nomeDoCurso = sc.nextLine();
    
            // Verifica se o código do curso já existe no mapa de cursos
            if(mapaDeCursos.containsKey(codigoDoCurso)){
    
                System.out.println("Codigo repetido para curso: <" + codigoDoCurso + ">");
    
            } else{
    
                // Cria um objeto Curso com os dados informados
                Curso curso = new Curso(codigoDoCurso, nomeDoCurso);
    
                // Adiciona o curso no mapa de cursos
                mapaDeCursos.put(codigoDoCurso, curso);
    
            }
    
        }catch (InputMismatchException e) {
            
            System.out.println("Erro de formatação.");
    
        }
    }
    
  
    // CASE 2 =======================================================================
  
    // Método para cadastrar uma disciplina
    public static void cadastrarDisciplina() {

        try{

            // Solicita o código da disciplina
            System.out.println("Escreva o codigo da disciplina:");
            String codigoDaDisciplina = sc.nextLine();

            // Solicita o nome da disciplina
            System.out.println("Escreva o nome da disciplina:");
            String nomeDaDisciplina = sc.nextLine();

            // Verifica se o código da disciplina já existe no mapa de disciplinas
            if(mapaDeDisciplinas.containsKey(codigoDaDisciplina)){

                System.out.println("Codigo repetido para disciplina: <" + codigoDaDisciplina + ">");

            } else{

                // Cria um objeto Disciplina com os dados informados
                Disciplina disciplina = new Disciplina(codigoDaDisciplina, nomeDaDisciplina);

                // Adiciona a disciplina no mapa de disciplinas
                mapaDeDisciplinas.put(codigoDaDisciplina, disciplina);

            }

        }catch (InputMismatchException e) {
            
            System.out.println("Erro de formatação.");

        }
    }


// CASE 3 =======================================================================

public static void cadastrarAvaliacao() {
    try {
        // Solicita o código da disciplina que dará a avaliação
        System.out.println("Escreva o codigo da disciplina que da a avaliacao:");
        String codigoDaDisciplina = sc.nextLine();

        // Solicita o código da avaliação
        System.out.println("Escreva o codigo da avaliacao:");
        String codigoDaAvaliacao = sc.nextLine();

        // Solicita o nome da avaliação
        System.out.println("Escreva o nome da avaliacao:");
        String nomeDaAvaliacao = sc.nextLine();

        // Solicita o peso da avaliação
        System.out.println("Escreva o peso da avaliacao:");
        Double pesoDaAvaliacao = sc.nextDouble();
        sc.nextLine();

        if (pesoDaAvaliacao <= 0) {
            
            // Exibe uma mensagem de erro se o peso da avaliação for inválido
            System.out.println("Peso de avaliação inválido para " + codigoDaAvaliacao + ": " + pesoDaAvaliacao);
            
        } else {
            // Solicita a data da avaliação
            System.out.println("Escreva a data da avaliacao (dd/MM/yyyy):");
            String dataDaAvaliacao = sc.nextLine();
            LocalDate dataDaAvaliacaoFormatada = converteParaData(dataDaAvaliacao);

            // Solicita o tipo de avaliação
            System.out.println("Escreva o tipo de avaliacao (P para prova, T para trabalho, F para prova final):");
            String tipoDeAvaliacao = sc.nextLine();

            if (mapaDeAvaliacoes.containsKey(codigoDaAvaliacao)) {
                // Exibe uma mensagem de erro se o código da avaliação já existe
                System.out.println("Codigo repetido para avaliacao: <" + codigoDaAvaliacao + ">");
            } else {
                if (tipoDeAvaliacao.equalsIgnoreCase("P")) {
                    if (mapaDeDisciplinas.containsKey(codigoDaDisciplina)) {
                        // Cria uma nova prova e a adiciona no mapa de avaliações e na disciplina correspondente
                        Prova prova = new Prova(mapaDeDisciplinas.get(codigoDaDisciplina), codigoDaAvaliacao, nomeDaAvaliacao, pesoDaAvaliacao, dataDaAvaliacaoFormatada);
                        mapaDeAvaliacoes.put(codigoDaAvaliacao, prova);
                        mapaDeProvas.put(codigoDaAvaliacao, prova);
                        mapaDeDisciplinas.get(codigoDaDisciplina).adicionaAvaliacao(codigoDaAvaliacao, prova);
                    } else {
                        // Exibe uma mensagem de erro se o código da disciplina não está definido
                        System.out.println("Código de disciplina não definido usado na prova <" + codigoDaAvaliacao + ">:<" + codigoDaDisciplina + ">)");
                    }
                } else if (tipoDeAvaliacao.equalsIgnoreCase("T")) {
                    if (mapaDeDisciplinas.containsKey(codigoDaDisciplina)) {
                        // Solicita a quantidade máxima de alunos do trabalho
                        System.out.println("Escreva a quantidade maxima de alunos do trabalho:");
                        Integer quantidadeDeAlunosDoTrabalho = sc.nextInt();

                        if (quantidadeDeAlunosDoTrabalho <= 0) {
                            // Exibe uma mensagem de erro se a quantidade de alunos do trabalho for inválida
                            System.out.println("Peso de avaliação inválido para " + codigoDaAvaliacao + ": " + pesoDaAvaliacao);
                        } else {
                            // Cria um novo trabalho e o adiciona no mapa de avaliações, de trabalhos e na disciplina correspondente
                            Trabalho trabalho = new Trabalho(mapaDeDisciplinas.get(codigoDaDisciplina), codigoDaAvaliacao, nomeDaAvaliacao, pesoDaAvaliacao, dataDaAvaliacaoFormatada, quantidadeDeAlunosDoTrabalho);
                            mapaDeTrabalhos.put(codigoDaAvaliacao, trabalho);
                            mapaDeAvaliacoes.put(codigoDaAvaliacao, trabalho);
                            mapaDeDisciplinas.get(codigoDaDisciplina).adicionaAvaliacao(codigoDaAvaliacao, trabalho);
                        }
                    } else {
                        // Exibe uma mensagem de erro se o código da disciplina não está definido
                        System.out.println("Código de disciplina não definido usado no trabalho <" + codigoDaAvaliacao + ">:<" + codigoDaDisciplina + ">)");
                    }
                } else if (tipoDeAvaliacao.equalsIgnoreCase("F")) {
                    if (mapaDeDisciplinas.containsKey(codigoDaDisciplina)) {
                        // Cria uma nova prova final e a adiciona no mapa de avaliações e na disciplina correspondente
                        ProvaFinal provaFinal = new ProvaFinal(mapaDeDisciplinas.get(codigoDaDisciplina), codigoDaAvaliacao, nomeDaAvaliacao, pesoDaAvaliacao, dataDaAvaliacaoFormatada);
                        mapaDeAvaliacoes.put(codigoDaAvaliacao, provaFinal);
                        mapaDeProvas.put(codigoDaAvaliacao, provaFinal);
                        mapaDeDisciplinas.get(codigoDaDisciplina).adicionaAvaliacao(codigoDaAvaliacao, provaFinal);
                    } else {
                        // Exibe uma mensagem de erro se o código da disciplina não está definido
                        System.out.println("Código de disciplina não definido usado na prova <" + codigoDaAvaliacao + ">:<" + codigoDaDisciplina + ">)");
                    }
                } else {
                    // Exibe uma mensagem de erro se o tipo de avaliação é desconhecido
                    System.out.println("Tipo de avaliação desconhecido para <" + codigoDaAvaliacao + ">:<" + tipoDeAvaliacao + ">");
                }
            }
        }
    } catch (InputMismatchException e) {
        // Exibe uma mensagem de erro em caso de formatação incorreta de entrada
        System.out.println("Erro de formatação.");
    }
}


    // CASE 4 =======================================================================

    public static void cadastrarAluno() {

        try {

            // Solicita a matrícula do aluno
            System.out.println("Escreva a matricula do aluno:");
            Integer matriculaDoAluno = sc.nextInt();
            sc.nextLine(); // Limpa o buffer do scanner
            
            // Solicita o nome do aluno
            System.out.println("Escreva o nome do aluno:");
            String nomeDoAluno = sc.nextLine();
            
            // Solicita o código do curso
            System.out.println("Escreva o codigo do curso:");
            String codigoDoCurso = sc.nextLine();
            
            // Solicita o tipo do aluno (graduação ou pós-graduação)
            System.out.println("Escreva o tipo do aluno (G para graduação, P para pós-graduação):");
            String tipoDoAluno = sc.nextLine();

            // Verifica se a matrícula já existe no mapa de alunos
            if(mapaDeAlunos.containsKey(matriculaDoAluno)){

                System.out.println("Matricula repetida para aluno: <" + matriculaDoAluno + ">");

            } else{

                if (tipoDoAluno.equalsIgnoreCase("G")) {
                    // Aluno de graduação
                    
                    if (mapaDeCursos.containsKey(codigoDoCurso)) {
                        // Verifica se o código do curso existe no mapa de cursos

                        // Cria um objeto AlunoGraduacao com os dados informados
                        AlunoGraduacao alunoGraduacao = new AlunoGraduacao(matriculaDoAluno, nomeDoAluno,mapaDeCursos.get(codigoDoCurso));

                        // Adiciona o aluno no mapa de alunos e no mapa de cursos
                        mapaDeAlunos.put(matriculaDoAluno, alunoGraduacao);
                        mapaDeCursos.get(codigoDoCurso).adicionaAluno(matriculaDoAluno, alunoGraduacao);
                        mapaDeAlunosGraduacao.put(matriculaDoAluno, alunoGraduacao);

                    } else {

                        System.out.println("Código de curso não definido usado no aluno <" + matriculaDoAluno + ">:<" + codigoDoCurso + ">)");

                    }
            
                } else if (tipoDoAluno.equalsIgnoreCase("P")) {
                    // Aluno de pós-graduação

                    // Solicita o nível de pós-graduação (mestrado ou doutorado)
                    System.out.println("Escreva o nível de pós graduação do aluno (M para mestrado, D para doutorado):");
                    String tipoDePos = sc.nextLine();

                    if (tipoDePos.equalsIgnoreCase("M")){
                        // Aluno de mestrado

                        // Cria um objeto AlunoPos (pós-graduação) com os dados informados
                        AlunoPos alunoPos = new AlunoPos(matriculaDoAluno, nomeDoAluno, "M");

                        // Adiciona o aluno no mapa de alunos
                        mapaDeAlunos.put(matriculaDoAluno, alunoPos);
                        mapaDeAlunosPos.put(matriculaDoAluno, alunoPos);

                    }else if (tipoDePos.equalsIgnoreCase("D")) {
                        // Aluno de doutorado

                        // Cria um objeto AlunoPos (pós-graduação) com os dados informados
                        AlunoPos alunoPos = new AlunoPos(matriculaDoAluno, nomeDoAluno, "D");

                        // Adiciona o aluno no mapa de alunos
                        mapaDeAlunos.put(matriculaDoAluno, alunoPos);
                        mapaDeAlunosPos.put(matriculaDoAluno, alunoPos);

                    } else {

                        System.out.println("Tipo de aluno de pós graduação desconhecido para <" + matriculaDoAluno + ">:<" + tipoDePos + ">)");

                    }

                } else {

                    System.out.println("Tipo de aluno desconhecido para <" + matriculaDoAluno + ">:<" + tipoDoAluno + ">");

                }

            }

        } catch (InputMismatchException e) {
            
            System.out.println("Erro de formatação.");

        }

    }

    // CASE 5 =======================================================================
  
    // Método para matricular um aluno em uma disciplina
    public static void matricularAlunoDisciplina() {
        // Solicita a matrícula do aluno ao usuário
        System.out.println("Escreva a matricula do aluno:");
        Integer matriculaDoAluno = sc.nextInt();
        sc.nextLine();
        
        // Solicita o código da disciplina ao usuário
        System.out.println("Escreva o codigo da disciplina:");
        String codigoDaDisciplina = sc.nextLine();

        if(mapaDeDisciplinas.containsKey(codigoDaDisciplina)){

            if (mapaDeAlunos.containsKey(matriculaDoAluno)) {
                // Adiciona o aluno à disciplina no mapa de disciplinas
                mapaDeDisciplinas.get(codigoDaDisciplina).adicionaAluno(matriculaDoAluno, mapaDeAlunos.get(matriculaDoAluno));
            } else {
                // Exibe uma mensagem de erro se a matrícula do aluno não estiver definida
                System.out.println("Matricula de aluno não definida usada <" + matriculaDoAluno + ">)");
            }

        } else {
            // Exibe uma mensagem de erro se o código da disciplina não estiver definido
            System.out.println("Código de disciplina não definido usado <" + codigoDaDisciplina + ">)");
        }
    }

  
    // CASE 6 =======================================================================

    public static void registrarNota() {
 
        try {
    
            // Solicita o código da avaliação
            System.out.println("Escreva o codigo da avaliacao:");
            String codigoDaAvaliacao = sc.nextLine();
    
            // Solicita o tipo de avaliação (P para prova, T para trabalho, F para prova final)
            System.out.println("Escreva o tipo de avaliacao (P para prova, T para trabalho, F para prova final):");
            String tipoDeAvaliacao = sc.nextLine();
    
            // Verifica se o código da avaliação já existe no mapa de avaliações
            if(mapaDeAvaliacoes.containsKey(codigoDaAvaliacao)){
    
                if (tipoDeAvaliacao.equalsIgnoreCase("P")) {
                    // Avaliação do tipo prova
    
                    // Solicita a matrícula do aluno
                    System.out.println("Escreva a matricula do aluno:");
                    Integer matriculaDoAluno = sc.nextInt();
                    sc.nextLine();
    
                    // Solicita a nota que o aluno tirou na avaliação
                    System.out.println("Escreva a nota que o aluno tirou na avaliacao:");
                    Double notaDoAluno = sc.nextDouble();
                    sc.nextLine();

                    if(notaDoAluno > 10 || notaDoAluno < 0){

                        System.out.println("Nota inválida para avaliação <" + codigoDaAvaliacao + "> <" + matriculaDoAluno + ">:<" + notaDoAluno + ">");

                    }else{

                        if(mapaDeAlunos.containsKey(matriculaDoAluno)){
                            // Verifica se a matrícula do aluno está cadastrada
        
                            // Cria um objeto Nota com a nota do aluno
                            Nota nota = new Nota(notaDoAluno);
        
                            // Adiciona a nota no objeto Avaliacao correspondente ao código informado
                            mapaDeAvaliacoes.get(codigoDaAvaliacao).adicionaNota(matriculaDoAluno, nota);
        
                            // Atualiza a média da avaliação
                            mapaDeAvaliacoes.get(codigoDaAvaliacao).adicionaNaMedia(notaDoAluno, mapaDeAvaliacoes.get(codigoDaAvaliacao).getPesoDaAvaliacao());
        
                            // Adiciona a avaliação ao aluno
                            mapaDeAlunos.get(matriculaDoAluno).adicionaAvaliacao(codigoDaAvaliacao, mapaDeAvaliacoes.get(codigoDaAvaliacao));
        
                        }else {
        
                            System.out.println("Codigo de avaliação não definida usada <" + matriculaDoAluno + ">)");
        
                        }
                    }
        
                } else if (tipoDeAvaliacao.equalsIgnoreCase("T")) {
                    // Avaliação do tipo trabalho
    
                    // Solicita a nota que os alunos tiraram no trabalho
                    System.out.println("Escreva a nota que os alunos tiraram no trabalho:");
                    Double notaDosAlunos = sc.nextDouble();
                    sc.nextLine();

                    if(notaDosAlunos > 10 || notaDosAlunos < 0){

                        System.out.println("Nota inválida para avaliação <" + codigoDaAvaliacao + ">");

                    }else{
    
                        // Solicita a quantidade de alunos no grupo do trabalho
                        System.out.println("Escreva a quantidade de alunos no grupo do trabalho:");
                        Integer quantidadeDeAlunos = sc.nextInt();
                        sc.nextLine();
        
                        Integer quantidadeDeAlunosMaxima = mapaDeTrabalhos.get(codigoDaAvaliacao).getQuantidadeDeAlunos();
        
                        if(quantidadeDeAlunos > quantidadeDeAlunosMaxima){
                            // Verifica se a quantidade de alunos informada é válida
        
                            System.out.println("Quantidade de alunos invalida, quantidade maxima: <" + quantidadeDeAlunosMaxima + ">)");
                                
                        } else {
        
                            System.out.println("Escreva a matricula dos alunos:");
                                
                            while(quantidadeDeAlunos>0){
                                // Solicita a matrícula dos alunos e adiciona as notas
        
                                Integer matriculaDoAlunot = sc.nextInt();
                                sc.nextLine();
        
                                if(mapaDeAlunos.containsKey(matriculaDoAlunot)){
                                    // Verifica se a matrícula do aluno está cadastrada
        
                                    // Cria um objeto Nota com a nota dos alunos no trabalho
                                    Nota nota = new Nota(notaDosAlunos);
                                    
                                    // Adiciona a nota no objeto Avaliacao correspondente ao código informado
                                    mapaDeAvaliacoes.get(codigoDaAvaliacao).adicionaNota(matriculaDoAlunot, nota);
                                    
                                    // Adiciona a avaliação ao aluno
                                    mapaDeAlunos.get(matriculaDoAlunot).adicionaAvaliacao(codigoDaAvaliacao, mapaDeAvaliacoes.get(codigoDaAvaliacao));
                                    
                                    quantidadeDeAlunos--;
        
                                } else {
        
                                    System.out.println("Codigo de avaliação não definida usada <" + matriculaDoAlunot + ">)");
        
                                }
        
                            }  
        
                        }
                    }
    
                }else if(tipoDeAvaliacao.equalsIgnoreCase("F")) {
                    // Avaliação do tipo prova final
    
                    // Solicita a matrícula do aluno
                    System.out.println("Escreva a matricula do aluno:");
                    Integer matriculaDoAlunof = sc.nextInt();
                    sc.nextLine();
    
                    if(mapaDeAlunos.containsKey(matriculaDoAlunof)){
                        // Verifica se a matrícula do aluno está cadastrada
    
                        // Solicita a nota que o aluno tirou na avaliação
                        System.out.println("Escreva a nota que o aluno tirou na avaliacao:");
                        Double notaDoAluno = sc.nextDouble();
                        sc.nextLine();

                        if(notaDoAluno > 10 || notaDoAluno < 0){

                            System.out.println("Nota inválida para avaliação <" + codigoDaAvaliacao + "> <" + matriculaDoAlunof + ">:<" + notaDoAluno + ">");

                        }else{
    
                            // Cria um objeto Nota com a nota do aluno
                            Nota nota = new Nota(notaDoAluno);
        
                            // Adiciona a nota no objeto Avaliacao correspondente ao código informado
                            mapaDeAvaliacoes.get(codigoDaAvaliacao).adicionaNota(matriculaDoAlunof, nota);
        
                            // Adiciona a avaliação ao aluno
                            mapaDeAlunos.get(matriculaDoAlunof).adicionaAvaliacao(codigoDaAvaliacao, mapaDeAvaliacoes.get(codigoDaAvaliacao));

                        }
        
                    } else {
    
                        System.out.println("Codigo de avaliação não definida usada <" + matriculaDoAlunof + ">)");
    
                    }
    
                } else {
                    
                    System.out.println("Tipo de avaliação desconhecido para <" + codigoDaAvaliacao + ">:<" + tipoDeAvaliacao + ">");
                }
    
            } else {
    
                System.out.println("Codigo de avaliação não definida usada <" + codigoDaAvaliacao + ">)");
    
            }
            
        } catch (InputMismatchException e) {
                
            System.out.println("Erro de formatação.");
    
        }
            
    }
    

    // CASE 7 =======================================================================

// Método para registrar um curso em uma disciplina
public static void registrarCursoemDisciplina() {
    System.out.println("Escreva o código do curso:");
    String codigoDoCurso = sc.nextLine();

    System.out.println("Escreva o código da disciplina:");
    String codigoDaDisciplina = sc.nextLine();

    if (mapaDeDisciplinas.containsKey(codigoDaDisciplina)) {
        // Verifica se o código da disciplina existe no mapa de disciplinas

        if (mapaDeCursos.containsKey(codigoDoCurso)) {
            // Verifica se o código do curso existe no mapa de cursos

            mapaDeDisciplinas.get(codigoDaDisciplina).adicionaCurso(codigoDoCurso, mapaDeCursos.get(codigoDoCurso));
            // Adiciona o curso à disciplina no mapa de disciplinas

        } else {
            System.out.println("Código de curso não definido usado <" + codigoDoCurso + ">");
            // Exibe uma mensagem de erro caso o código do curso não exista no mapa de cursos
        }

    } else {
        System.out.println("Código de disciplina não definido usado <" + codigoDaDisciplina + ">");
        // Exibe uma mensagem de erro caso o código da disciplina não exista no mapa de disciplinas
    }
}

   
// CASE 8 =======================================================================
      
    // Método para imprimir os dados
    public static void imprimirDados() {
        
        System.out.println("Disciplinas e alunos matriculados:");
        for (Disciplina disciplina : mapaDeDisciplinas.values()) {
            System.out.println("- " + disciplina.getNomeDaDisciplina() + " (" + disciplina.getCodigoDaDisciplina() + ")");

            for (AlunoGraduacao alunoGraduacao : disciplina.retornaMapaDeAlunosGraduacao().values()) {
                System.out.println("\t- " + alunoGraduacao.getNomeDoAluno() + " (" + alunoGraduacao.getCurso().getNomeDoCurso() + ")");

            }

        }
        System.out.println("Provas e notas recebidas:");
        for (Avaliacao prova : mapaDeProvas.values()) {
            System.out.println("- " + prova.getDisciplinaDaAvaliacao().getNomeDaDisciplina() + " - " + prova.getNomeDaAvaliacao() + " ("
                    + prova.getDataDaAvaliacao() + ")");

            for (Map.Entry<Integer, Nota> entry : prova.retornaMapaDeNotas().entrySet()) {
                System.out.println("\t - " + mapaDeAlunos.get(entry.getKey()).getNomeDoAluno() + ": "
                        + entry.getValue().getValor());

            }

        }
    }
      
// CASE 9 =======================================================================
//Método para relatório:
// Switch case para opcoes de relatorio
      
  public static void exibirRelatorios() {
    System.out.println("=== RELATÓRIOS ===");
    System.out.println("1 - Pauta final de disciplina");
    System.out.println("2 - Estatísticas por disciplina");
    System.out.println("3 - Estatísticas por avaliação");
    System.out.print("Escolha um relatório para exibir: ");
    int opcao = sc.nextInt();
    
    switch (opcao) {
        case 1:
            exibirPautaFinal();
            break;
        case 2:
            exibirEstatisticasPorDisciplina();
            break;
        case 3:
            exibirEstatisticasPorAvaliacao();
            break;
        default:
            System.out.println("Opção inválida!");
            break;
    }
}
// continuação do relatório:
// parte de exibir Pautas final

  public static void exibirPautaFinal() {
    System.out.print("Digite o código da disciplina: ");
    sc.nextLine();
    String codigoDaDisciplina = sc.nextLine();
    Double somaDePeso = 0.0;
    Double notaTotal = 0.0;
    Double notaProvaFinal = 0.0;

    if (mapaDeDisciplinas.containsKey(codigoDaDisciplina)) {
        Disciplina disciplina = mapaDeDisciplinas.get(codigoDaDisciplina);
    
        System.out.println("=== Pauta Final de Disciplina ===");
        System.out.println("Disciplina: " + disciplina.getNomeDaDisciplina() + " (" + disciplina.getCodigoDaDisciplina() + ")");
        System.out.println("Alunos matriculados:");

        for (Aluno aluno : disciplina.retornaMapaDeAlunos().values()) {
            System.out.println("Matrícula: " + aluno.getMatricula());
            System.out.println("Nome: " + aluno.getNomeDoAluno());

            somaDePeso = 0.0;
            notaTotal = 0.0;

            if(aluno.retornaMapaDeAvaliacoes() == null){

               System.out.println("A disciplina <" + disciplina.getCodigoDaDisciplina() + ">)" + "não possui nenhuma avaliação cadastrada");

            }else {
                
                for (Avaliacao avaliacao : aluno.retornaMapaDeAvaliacoes().values()) {

                    if (avaliacao.getDisciplinaDaAvaliacao().getCodigoDaDisciplina().equals(disciplina.getCodigoDaDisciplina())) {
                        System.out.println("Código da avaliação: " + avaliacao.getCodigoDaAvaliacao());
                        for (Map.Entry<Integer, Nota> entry : avaliacao.retornaMapaDeNotas().entrySet()) {
                            if (aluno.getMatricula().equals(entry.getKey())) {
                                if (avaliacao instanceof Prova || avaliacao instanceof Trabalho) {
                                    System.out.println("\t - " + mapaDeAlunos.get(entry.getKey()).getNomeDoAluno() + ": "
                                            + entry.getValue().getValor());
                                    somaDePeso += avaliacao.getPesoDaAvaliacao();
                                    notaTotal += entry.getValue().getValor() * avaliacao.getPesoDaAvaliacao();
                                } else if (avaliacao instanceof ProvaFinal) {
                                    notaProvaFinal = entry.getValue().getValor();
                                }
                            }
                        }
                    }
                }
            }

            Double mediaParcial = notaTotal / somaDePeso;
            System.out.println("Média parcial: " + mediaParcial);

            if (mediaParcial < 7.0) {
                System.out.println("Nota na prova final: " + notaProvaFinal);
                Double mediaFinal = (notaProvaFinal + mediaParcial) / 2;
                System.out.println("Média final: " + mediaFinal);
            } else if (mediaParcial >= 7.0) {
                System.out.println("Média final: " + mediaParcial);
            }

            System.out.println("------------------------");
        }

    } else {
        System.out.println("Código da disciplina inválido.");
    }
}


// Parte exibir estatisticas por disciplina
  
public static void exibirEstatisticasPorDisciplina() {
    System.out.print("Digite o código da disciplina: ");
    sc.nextLine();
    String codigoDisciplina = sc.nextLine();
    Double somaDePeso = 0.0;
    Double notaTotal = 0.0;
    Double notaProvaFinal = 0.0;

    if (mapaDeDisciplinas.containsKey(codigoDisciplina)) {
        Disciplina disciplina = mapaDeDisciplinas.get(codigoDisciplina);
        System.out.println("=== Estatísticas por Disciplina ===");
        System.out.println("Disciplina: " + disciplina.getNomeDaDisciplina() + " (" + disciplina.getCodigoDaDisciplina() + ")");

        // Iterar sobre cada curso envolvido na disciplina
        for (Curso curso : disciplina.retornaMapaDeCursos().values()) {
            System.out.println("Curso: " + curso.getNomeDoCurso());

            Integer qtdAlunos = 0;
            Double mediaTotal = 0.0;
            Integer aprovados = 0;

            // Filtrar os alunos da disciplina pelo curso
            for (AlunoGraduacao alunoGraduacao : disciplina.retornaMapaDeAlunosGraduacao().values()) {
                if (alunoGraduacao.getCurso().equals(curso)) {
                    qtdAlunos++;
                    somaDePeso = 0.0;
                    notaTotal = 0.0;

                    for (Avaliacao avaliacao : alunoGraduacao.retornaMapaDeAvaliacoes().values()) {
                        if (avaliacao.getDisciplinaDaAvaliacao().getCodigoDaDisciplina().equals(disciplina.getCodigoDaDisciplina())) {
                            System.out.println("Código da prova: " + avaliacao.getCodigoDaAvaliacao());
                            for (Map.Entry<Integer, Nota> entry : avaliacao.retornaMapaDeNotas().entrySet()) {
                                if (alunoGraduacao.getMatricula().equals(entry.getKey())) {
                                    if (avaliacao instanceof Prova) {
                                        System.out.println("\t - " + mapaDeAlunos.get(entry.getKey()).getNomeDoAluno() + ": "
                                                + entry.getValue().getValor());
                                        somaDePeso += avaliacao.getPesoDaAvaliacao();
                                        notaTotal += entry.getValue().getValor() * avaliacao.getPesoDaAvaliacao();
                                    } else if (avaliacao instanceof ProvaFinal) {
                                        notaProvaFinal = entry.getValue().getValor();
                                    }
                                }
                            }
                        }
                    }

                    Double mediaParcial = notaTotal / somaDePeso;

                    if (mediaParcial < 7.0) {
                        Double mediaFinal = (notaProvaFinal + mediaParcial) / 2;
                        mediaTotal = mediaTotal + mediaFinal;
                        if (mediaFinal >= 5) {
                            aprovados++;
                        }
                    } else if (mediaParcial >= 7.0) {
                        mediaTotal = mediaTotal + mediaParcial;
                        aprovados++;
                    }

                    System.out.println("------------------------");
                }
            }

            // Exibir as estatísticas do curso
            System.out.println("Média das notas finais: " + mediaTotal / qtdAlunos);
            System.out.println("Percentual de alunos aprovados: " + (aprovados * 100.0 / qtdAlunos) + "%");
            System.out.println("------------------------");
        }
    } else {
        System.out.println("Código da disciplina inválido.");
    }
}



// parte de exibir estatisticas por avaliação
public static void exibirEstatisticasPorAvaliacao() {


    for(Avaliacao avaliacao : mapaDeAvaliacoes.values()){

     System.out.println("=== Estatísticas por Avaliação ===");
        System.out.println("Disciplina: " + avaliacao.getDisciplinaDaAvaliacao().getNomeDaDisciplina() + " (" + avaliacao.getDisciplinaDaAvaliacao().getCodigoDaDisciplina() + ")");

    System.out.println("Código da avaliação: " + avaliacao.getCodigoDaAvaliacao());
            System.out.println("Nome: " + avaliacao.getNomeDaAvaliacao());
            System.out.println("Data: " + avaliacao.getDataDaAvaliacao());

      if (avaliacao.retornaMapaDeNotas() != null) {

                avaliacao.calculaMedia();
                double mediaNotas = avaliacao.getMediaDasNotas();
                System.out.println("Média das notas: " + mediaNotas);

            } else {
                System.out.println("Não foram registradas notas para essa avaliação.");
            }
      }
        System.out.println("1.3");
    }


// CASE 10 ==============================================================================
// SERIALIZAÇÃO "carregar"

@SuppressWarnings("unchecked")
public static void carregar() {
    try {
        // Cria um FileInputStream para ler o arquivo "dados.dat"
        FileInputStream fileIn = new FileInputStream("dados.dat");
        // Cria um ObjectInputStream para ler os objetos do arquivo
        ObjectInputStream objectIn = new ObjectInputStream(fileIn);

        // Carrega os HashMaps dos dados do arquivo
        mapaDeCursos = (Map<String, Curso>) objectIn.readObject();
        mapaDeDisciplinas = (Map<String, Disciplina>) objectIn.readObject();
        mapaDeAlunos = (Map<Integer, Aluno>) objectIn.readObject();
        mapaDeAvaliacoes = (Map<String, Avaliacao>) objectIn.readObject();
        mapaDeTrabalhos = (Map<String, Trabalho>) objectIn.readObject();

        // Fecha os fluxos de leitura
        objectIn.close();
        fileIn.close();

        System.out.println("Dados carregados com sucesso.");
    } catch (Exception e) {
        System.out.println("Erro ao carregar os dados: " + e.getMessage());
    }
}




// CASE 11 ==============================================================================
// SERIALIZAÇÃO "salvar"
  
@SuppressWarnings("unchecked")
public static void salvar() {
    try {
        // Cria um FileOutputStream para escrever no arquivo "dados.dat"
        FileOutputStream fileOut = new FileOutputStream("dados.dat");
        // Cria um ObjectOutputStream para escrever os objetos no arquivo
        ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
    
        // Salva os HashMaps dos dados no arquivo
        objectOut.writeObject(mapaDeCursos);
        objectOut.writeObject(mapaDeDisciplinas);
        objectOut.writeObject(mapaDeAlunos);
        objectOut.writeObject(mapaDeAvaliacoes);
        objectOut.writeObject(mapaDeTrabalhos);
    
        // Fecha os fluxos de escrita
        objectOut.close();
        fileOut.close();
    
        System.out.println("Dados salvos com sucesso.");
    } catch (Exception e) {
        System.out.println("Erro ao salvar os dados: " + e.getMessage());
    }
    
}
}// fim do codigo