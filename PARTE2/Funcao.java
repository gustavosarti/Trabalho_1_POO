import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Funcao {

  // Scanner para leitura de entrada do usuário
    private static Scanner sc = new Scanner(System.in);

  // HashMaps para armazenar os dados do programa
    private static Map<String, Curso> mapaDeCursos = new HashMap<String, Curso>();
    private static Map<String, Disciplina> mapaDeDisciplinas = new HashMap<String, Disciplina>();
    private static Map<Integer, Aluno> mapaDeAlunos = new HashMap<Integer, Aluno>();
    private static Map<String, Prova> mapaDeProvas = new HashMap<String, Prova>();
    private static Map<String, Trabalho> mapaDeTrabalhos = new HashMap<String, Trabalho>();

    // Método para converter uma String em LocalDate
    public static LocalDate converteParaData(String data) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(data, formatter);
    }
  
// CASE 1 =======================================================================
  
// Método para cadastrar um curso ou pos_graduacao
    public static void cadastrarCurso() {
        System.out.println("Escreva o codigo do curso ou o tipo de pos_graduacao(Mestrado ou Doutorado):");
        String codigo = sc.nextLine();
      if((codigo.equals("Mestrado"))||(codigo.equals("Doutorado"))){

        if (mapaDeCursos.containsKey(codigo)) {
            System.out.println("Já existe um curso cadastrado com esse código.");
        } else {
            Curso c = new Curso(codigo);
            mapaDeCursos.put(codigo, c);
        }

      }else{
        System.out.println("Escreva o nome do curso:");
        String nome = sc.nextLine();

        if (mapaDeCursos.containsKey(codigo)) {
            System.out.println("Já existe um curso cadastrado com esse código.");
        } else {
            Curso c = new Curso(codigo,nome);
            mapaDeCursos.put(codigo, c);
        }
      }
    }
  
// CASE 2 =======================================================================
  
    // Método para cadastrar uma disciplina
    public static void cadastrarDisciplina() {
        System.out.println("Escreva o codigo da disciplina:");
        String codigo = sc.nextLine();
        System.out.println("Escreva o nome da disciplina:");
        String nome = sc.nextLine();

        Disciplina d = new Disciplina(codigo,nome);
        mapaDeDisciplinas.put(codigo, d);
    }

// CASE 3 =======================================================================

// Método para cadastrar uma prova ou trabalho
    public static void cadastrarProvaouTrabalho() {
    System.out.println("Escolha o tipo de atividade a cadastrar:");
    System.out.println("1. Prova");
    System.out.println("2. Trabalho");
    System.out.println("3. Prova Final");
    int escolha = sc.nextInt();
    sc.nextLine(); // Limpar o buffer

    if (escolha == 1) {
        cadastrarProva();
    } else if (escolha == 2) {
        cadastrarTrabalho();
    } else if (escolha == 3) {
        cadastrarProvaFinal();
    } else {
        System.out.println("Escolha inválida.");
    }
}

// Método para cadastrar uma prova
private static void cadastrarProva() {
    System.out.println("Escreva o código da disciplina que dá a prova:");
    String codigoDaDisciplina = sc.nextLine();
    System.out.println("Escreva o código da prova:");
    String codigoDaProva = sc.nextLine();
    System.out.println("Escreva o nome da prova:");
    String nomeDaProva = sc.nextLine();
    System.out.println("Escreva o peso da prova:");
    Double pesoDaProva = sc.nextDouble();
    System.out.println("Escreva a data da prova (dd/MM/yyyy):");
    sc.nextLine();
    String dataDaProva = sc.nextLine();

    if (mapaDeDisciplinas.containsKey(codigoDaDisciplina)) {
        Prova p = new Prova(mapaDeDisciplinas.get(codigoDaDisciplina), codigoDaProva, nomeDaProva, pesoDaProva, converteParaData(dataDaProva), "prova");

        mapaDeProvas.put(codigoDaProva, p);
        mapaDeDisciplinas.get(codigoDaDisciplina).adicionaProva(codigoDaProva, p);
    } else {
        System.out.println("Código da disciplina inválido.");
    }
}

// Método para cadastrar um trabalho
private static void cadastrarTrabalho() {
    System.out.println("Escreva o código da disciplina que dá o trabalho:");
    String codigoDaDisciplina = sc.nextLine();
    System.out.println("Escreva o código do trabalho:");
    String codigoDoTrabalho = sc.nextLine();
    System.out.println("Escreva o nome do trabalho:");
    String nomeDoTrabalho = sc.nextLine();
    System.out.println("Escreva o peso do trabalho:");
    Double pesoDoTrabalho = sc.nextDouble();
    System.out.println("Escreva a data de entrega do trabalho (dd/MM/yyyy):");
    sc.nextLine();
    String dataDeEntrega = sc.nextLine();
    System.out.println("Escreva a quantidade de alunos do trabalho:");
    Integer quantidadeDeAlunos = sc.nextInt();

    if (mapaDeDisciplinas.containsKey(codigoDaDisciplina)) {
        Trabalho t = new Trabalho(mapaDeDisciplinas.get(codigoDaDisciplina), codigoDoTrabalho, nomeDoTrabalho, pesoDoTrabalho, converteParaData(dataDeEntrega), quantidadeDeAlunos,"trabalho");
        
        mapaDeTrabalhos.put(codigoDoTrabalho, t);
        mapaDeDisciplinas.get(codigoDaDisciplina).adicionaTrabalho(codigoDoTrabalho, t);
        mapaDeProvas.put(codigoDoTrabalho, t);
        mapaDeDisciplinas.get(codigoDaDisciplina).adicionaProva(codigoDoTrabalho, t);
      
    } else {
        System.out.println("Código da disciplina inválido.");
    }
}
  
// Método para cadastrar uma prova final
  private static void cadastrarProvaFinal() {
    System.out.println("Escreva o código da disciplina para a prova final:");
    String codigoDaDisciplina = sc.nextLine();
    System.out.println("Escreva o código da prova final:");
    String codigoDaProvaFinal = sc.nextLine();
    System.out.println("Escreva o nome da prova final:");
    String nomeDaProvaFinal = sc.nextLine();
    System.out.println("Escreva a data da prova final (dd/MM/yyyy):");
    String dataDaProvaFinal = sc.nextLine();

    if (mapaDeDisciplinas.containsKey(codigoDaDisciplina)) {
        Prova pf = new Prova(mapaDeDisciplinas.get(codigoDaDisciplina), codigoDaProvaFinal, nomeDaProvaFinal,5.00, converteParaData(dataDaProvaFinal), "provaFinal");

        mapaDeProvas.put(codigoDaProvaFinal, pf);
        mapaDeDisciplinas.get(codigoDaDisciplina).adicionaProva(codigoDaProvaFinal, pf);
    } else {
        System.out.println("Código da disciplina inválido.");
    }
}

  // CASE 4 =======================================================================
  
    // Método para cadastrar um aluno
    public static void cadastrarAluno() {
        System.out.println("Escreva a matricula do aluno:");
        Integer matriculaDoAluno = sc.nextInt();
        sc.nextLine();
        System.out.println("Escreva o nome do aluno:");
        String nomeDoAluno = sc.nextLine();
        System.out.println("Escreva o codigo do curso que o aluno está ou o tipo de pos graduacao (Mestrado ou Doutorado):");
        String codigoDoCurso = sc.nextLine();

        Aluno a = new Aluno(matriculaDoAluno,nomeDoAluno,mapaDeCursos.get(codigoDoCurso));

        mapaDeAlunos.put(matriculaDoAluno, a);

        if (mapaDeCursos.containsKey(codigoDoCurso)) {
            mapaDeCursos.get(codigoDoCurso).adicionaAluno(matriculaDoAluno, a);
        } else {
            System.out.println("Código do curso inválido.");
        }
    }
  
// CASE 5 =======================================================================
  
    // Método para matricular um aluno em uma disciplina
    public static void matricularAlunoDisciplina() {
        System.out.println("Escreva a matricula do aluno:");
        Integer matriculaAluno = sc.nextInt();
        sc.nextLine();
        System.out.println("Escreva o codigo da disciplina:");
        String codigoDisciplina = sc.nextLine();

        if (mapaDeAlunos.containsKey(matriculaAluno)) {
            mapaDeDisciplinas.get(codigoDisciplina).adicionaAluno(matriculaAluno, mapaDeAlunos.get(matriculaAluno));
        } else {
            System.out.println("Matricula do aluno inválida.");
        }
    }
  
// CASE 6 =======================================================================

    public static void registrarNota() {
    System.out.println("Escolha o tipo de avaliação:");
    System.out.println("1. Prova");
    System.out.println("2. Trabalho");
    int escolha = sc.nextInt();
    sc.nextLine(); // Limpar o buffer

    if (escolha == 1) {
        registrarNotaAlunoProva();
    } else if (escolha == 2) {
        registrarNotaAlunoTrabalho();
    } else {
        System.out.println("Escolha inválida.");
    }
}

  
    // Método para registrar a nota de um aluno em uma prova
    public static void registrarNotaAlunoProva() {
        System.out.println("Escreva a matricula do aluno:");
        Integer matriculaAluno = sc.nextInt();
        sc.nextLine();
        System.out.println("Escreva o codigo da prova:");
        String codigoProva = sc.nextLine();
        System.out.println("Escreva a nota que o aluno tirou na prova:");
        Double nota = sc.nextDouble();

        Nota n = new Nota(nota);
      
        if (mapaDeProvas.containsKey(codigoProva)) {
            mapaDeProvas.get(codigoProva).adicionaNota(matriculaAluno, n);
        } else {
            System.out.println("Código da prova inválido.");
        }
        if(mapaDeAlunos.containsKey(matriculaAluno)){
          mapaDeAlunos.get(matriculaAluno).adicionaProva(codigoProva,mapaDeProvas.get(codigoProva));
        }else {
            System.out.println("Código do aluno inválido.");
        }
     }  
      // Método para registrar a nota de um aluno em um trabalho
      public static void registrarNotaAlunoTrabalho() {
        System.out.println("Escreva o codigo do trabalho:");
        String codigoDoTrabalho = sc.nextLine();

        Integer x = mapaDeTrabalhos.get(codigoDoTrabalho).getQuantidadeDeAlunos();

        System.out.println("Escreva a nota que os alunos tiraram no trabalho:");
        Double nota = sc.nextDouble();

        Nota n = new Nota(nota);

        System.out.println("Escreva a matricula dos alunos:");
        while(x>0){
          Integer matriculaAluno = sc.nextInt();
          sc.nextLine();
       
          if (mapaDeTrabalhos.containsKey(codigoDoTrabalho)) {
              mapaDeTrabalhos.get(codigoDoTrabalho).adicionaNota(matriculaAluno, n);
          } else {
              System.out.println("Código do trabalho inválido.");
          }if(mapaDeAlunos.containsKey(matriculaAluno)){
          mapaDeAlunos.get(matriculaAluno).adicionaTrabalho(codigoDoTrabalho,mapaDeTrabalhos.get(codigoDoTrabalho));
          }else {
            System.out.println("Código do aluno inválido.");
          }
        x--;
      }
    }  

// CASE 7 =======================================================================

      // Método para registrar curso em uma disciplina
      public static void registrarCursoemDisciplina() {
        System.out.println("Escreva o codigo do curso:");
        Integer codigoDoCurso = sc.nextInt();
        sc.nextLine();
        System.out.println("Escreva o codigo da disciplina:");
        String codigoDaDisciplina = sc.nextLine();
      
        if (mapaDeDisciplinas.containsKey(codigoDaDisciplina)) {
            mapaDeDisciplinas.get(codigoDaDisciplina).adicionaCurso(codigoDoCurso,mapaDeCursos.get(codigoDoCurso));
        } else {
            System.out.println("Código da disciplina inválido.");
        }
      }
   
// CASE 8 =======================================================================
      
    // Método para imprimir os dados
    public static void imprimirDados() {
        System.out.println("Disciplinas e alunos matriculados:");
        for (Disciplina dd : mapaDeDisciplinas.values()) {
            System.out.println("- " + dd.getNomeDaDisciplina() + " (" + dd.getCodigoDaDisciplina() + ")");

            for (Aluno aa : dd.retornaMapaDeAlunos().values()) {
                System.out.println("\t- " + aa.getNomeDoAluno() + " (" + aa.getCurso().getNomeDoCurso() + ")");

            }

        }
        System.out.println("Provas e notas recebidas:");
        for (Prova pp : mapaDeProvas.values()) {
            System.out.println("- " + pp.getDisciplinaDaProva().getNomeDaDisciplina() + " - " + pp.getNomeDaProva() + " ("
                    + pp.getDataDaProva() + ")");

            for (Map.Entry<Integer, Nota> entry : pp.retornaMapaDeNotas().entrySet()) {
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
            //exibirEstatisticasPorDisciplina();
            break;
        case 3:
            //exibirEstatisticasPorAvaliacao();
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
    String codigoDisciplina = sc.nextLine();
    Double somaDePeso = 0.0;
    Double notaTotal = 0.0;
    Double notaProvaFinal = 0.0;

    if (mapaDeDisciplinas.containsKey(codigoDisciplina)) {
        Disciplina disciplina = mapaDeDisciplinas.get(codigoDisciplina);

        System.out.println("=== Pauta Final de Disciplina ===");
        System.out.println("Disciplina: " + disciplina.getNomeDaDisciplina() + " (" + disciplina.getCodigoDaDisciplina() + ")");
        System.out.println("Alunos matriculados:");

        for (Aluno aluno : disciplina.retornaMapaDeAlunos().values()) {
            System.out.println("Matrícula: " + aluno.getMatricula());
            System.out.println("Nome: " + aluno.getNomeDoAluno());
            somaDePeso = 0.0;
            notaTotal = 0.0;

            for (Prova prova : aluno.retornaMapaDeProvas().values()) {
                if (prova.getDisciplinaDaProva().getCodigoDaDisciplina().equals(disciplina.getCodigoDaDisciplina())) {
                    System.out.println("Código da prova: " + prova.getCodigoDaProva());
                    for (Map.Entry<Integer, Nota> entry : prova.retornaMapaDeNotas().entrySet()) {
                        if (aluno.getMatricula().equals(entry.getKey())) {
                            if (prova.getTipoDeProva().equals("prova")) {
                                System.out.println("\t - " + mapaDeAlunos.get(entry.getKey()).getNomeDoAluno() + ": "
                                        + entry.getValue().getValor());
                                somaDePeso += prova.getPesoDaProva();
                                notaTotal += entry.getValue().getValor() * prova.getPesoDaProva();
                            } else if (prova.getTipoDeProva().equals("provaFinal")) {
                                notaProvaFinal = entry.getValue().getValor();
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
            for (Aluno aluno : disciplina.retornaMapaDeAlunos().values()) {
                if (aluno.getCurso().equals(curso)) {
                    qtdAlunos++;
                    somaDePeso = 0.0;
                    notaTotal = 0.0;

                    for (Prova prova : aluno.retornaMapaDeProvas().values()) {
                        if (prova.getDisciplinaDaProva().getCodigoDaDisciplina().equals(disciplina.getCodigoDaDisciplina())) {
                            System.out.println("Código da prova: " + prova.getCodigoDaProva());
                            for (Map.Entry<Integer, Nota> entry : prova.retornaMapaDeNotas().entrySet()) {
                                if (aluno.getMatricula().equals(entry.getKey())) {
                                    if (prova.getTipoDeProva().equals("prova")) {
                                        System.out.println("\t - " + mapaDeAlunos.get(entry.getKey()).getNomeDoAluno() + ": "
                                                + entry.getValue().getValor());
                                        somaDePeso += prova.getPesoDaProva();
                                        notaTotal += entry.getValue().getValor() * prova.getPesoDaProva();
                                    } else if (prova.getTipoDeProva().equals("provaFinal")) {
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

    for(Prova prova : mapaDeProvas.values()){

     System.out.println("=== Estatísticas por Avaliação ===");
        System.out.println("Disciplina: " + prova.getDisciplinaDaProva().getNomeDaDisciplina() + " (" + prova.getDisciplinaDaProva().getCodigoDaDisciplina() + ")");

    System.out.println("Código da avaliação: " + prova.getCodigoDaProva());
            System.out.println("Nome: " + prova.getNomeDaProva());
            System.out.println("Data: " + prova.getDataDaProva());

    double somaNotas = 0.0;
    int quantidadeNotas = 0;
      
      for(Nota nota : prova.retornaMapaDeNotas().values()){

        somaNotas = somaNotas + nota.getValor();
        quantidadeNotas ++;

      }

      if (quantidadeNotas > 0) {
                double mediaNotas = somaNotas / quantidadeNotas;
                System.out.println("Média das notas: " + mediaNotas);
            } else {
                System.out.println("Não foram registradas notas para essa avaliação.");
            }
      }
        
        
        
    
    }
}