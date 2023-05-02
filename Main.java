import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Main {

    public static LocalDate converteParaData(String data) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    return LocalDate.parse(data, formatter);
  }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
      
        boolean continuar = true;

      HashMap<Integer,Curso> mapaDeCursos = new HashMap<Integer,Curso>();
      HashMap<String,Disciplina> mapaDeDisciplinas = new HashMap<String,Disciplina>();
      HashMap<Integer,Aluno> mapaDeAlunos = new HashMap<Integer,Aluno>();
      HashMap<String,Prova> mapaDeProvas = new HashMap <String,Prova>();
        
        while (continuar) {
            System.out.println("=== MENU ===");
            System.out.println("1 - Cadastrar curso");
            System.out.println("2 - Cadastrar disciplina");
            System.out.println("3 - Cadastrar prova");
            System.out.println("4 - Cadastrar aluno");
            System.out.println("5 - Matricular aluno em disciplina");
            System.out.println("6 - Registrar nota de aluno em prova");
            System.out.println("7 - Imprimir dados");
            System.out.println("8 - Sair do programa");
            System.out.print("Digite uma opção: ");
            int opcao = sc.nextInt();
            
            switch (opcao) {
            
                case 1: //OK so esta analisando o codigo do curso nome nao

                System.out.println("Escreva o codigo do curso:");

                Integer codigo = sc.nextInt();
                sc.nextLine();

                System.out.println(codigo);
                
                System.out.println("Escreva o nome do curso:");

                String nome = sc.nextLine();

                System.out.println(nome);

                if (mapaDeCursos.containsKey(codigo)){
                   System.out.println("Já existe uma disciplina cadastrada com esse código.");
                }
                else{

                Curso c = new Curso();

                c.setCodigoDoCurso(codigo);
                c.setNomeDoCurso(nome);

                mapaDeCursos.put(codigo, c);
                }
                    break;
                    
                case 2: //OK

                System.out.println("Escreva o codigo da disciplina:");

                sc.nextLine();
                String codigo2 = sc.nextLine();

                System.out.println(codigo2);

                System.out.println("Escreva o nome da disciplina:");

                String nome2 = sc.nextLine();

                System.out.println(nome2);

                Disciplina d  = new Disciplina();

                d.setCodigoDaDisciplina(codigo2);
                d.setNomeDaDisciplina(nome2);

                mapaDeDisciplinas.put(codigo2, d);
                
                    break;
                    
                case 3: //OK

                System.out.println("Escreva o codigo da disciplina que dá a prova:");

                sc.nextLine();
                String codigo3 = sc.nextLine();
               
                System.out.println(codigo3);

                System.out.println("Escreva o codigo da prova:");

                String codigo4 = sc.nextLine();

                System.out.println(codigo4);

                System.out.println("Escreva o nome da prova:");

                String nome3 = sc.nextLine();

                System.out.println(nome3);

                System.out.println("Escreva o peso da prova:");

                Double peso = sc.nextDouble();

                System.out.println(peso);

                System.out.println("Escreva a data da prova:");

                sc.nextLine();
                String data = sc.nextLine();

                System.out.println(data);

                Prova p  = new Prova();

                p.setCodigoDaProva(codigo4);
                p.setNomeDaProva(nome3);
                p.setPesoDaProva(peso);
                p.setDataDaProva(converteParaData(data));
                p.setDisciplina(mapaDeDisciplinas.get(codigo3));

                mapaDeProvas.put(codigo4,p);

                if(mapaDeDisciplinas.containsKey(codigo3)){
                  mapaDeDisciplinas.get(codigo3).adicionaProva(codigo4,p);

                  break;
               
                }else{
                  System.out.println("Codigo da Disciplina invalido, tente novamente:");
                
                  break;
                }
                 
                case 4: //OK

                System.out.println("Escreva a matricula do aluno:");

                Integer codigo5 = sc.nextInt();

                System.out.println(codigo5);

                System.out.println("Escreva o nome do aluno:");

                sc.nextLine();
                String nome4 = sc.nextLine();

                System.out.println(nome4);

                System.out.println("Escreva o codigo do curso que o aluno está:");

                Integer codigo6 = sc.nextInt();

                System.out.println(codigo6);

                Aluno a  = new Aluno();

                a.setMatricula(codigo5);
                a.setNomeDoAluno(nome4);
                a.setCurso(mapaDeCursos.get(codigo6));

                mapaDeAlunos.put(codigo5, a);

                if(mapaDeCursos.containsKey(codigo6)){
                    mapaDeCursos.get(codigo6).adicionaAluno(codigo5,a);

                  break;
               
                }else{
                  System.out.println("Codigo de curso invalido, tente novamente:");
                
                  break;

                }
                   
                case 5: //OK

                System.out.println("Escreva a matricula do aluno:");

                Integer codigo7 = sc.nextInt();

                System.out.println(codigo7);

                System.out.println("Escreva o codigo da disciplina:");

                sc.nextLine();
                String codigo8 = sc.nextLine();

                System.out.println(codigo8);

                if(mapaDeAlunos.containsKey(codigo7)){
                    mapaDeDisciplinas.get(codigo8).adicionaAluno(codigo7,mapaDeAlunos.get(codigo7));

                    break;
               
                }else{
                  System.out.println("Matricula do aluno invalida, tente novamente:");
                
                  break;

                }

                case 6: //OK

                System.out.println("Escreva a matricula do aluno:");

                Integer codigo9 = sc.nextInt();

                System.out.println(codigo9);

                System.out.println("Escreva o codigo da prova:");

                sc.nextLine();
                String codigo10 = sc.nextLine();
                
                 System.out.println(codigo10);

                 System.out.println("Escreva a nota que o aluno tirou na prova:");

                Double nota = sc.nextDouble();

                System.out.println(nota);

                Nota n = new Nota();

                n.setValor(nota);

                if(mapaDeProvas.containsKey(codigo10)){
                  mapaDeProvas.get(codigo10).adicionaNota(codigo9,n);

                    break;
               
                }else{
                  System.out.println("Codigo da prova invalido, tente novamente:");
                
                  break;

                }
                    
                case 7:
                

                System.out.println("Disciplinas e alunos matriculados:"); 
                for(Disciplina dd : mapaDeDisciplinas.values()){
                  System.out.println("- "+dd.getNomeDaDisciplina()+" ("+dd.getCodigoDaDisciplina()+")");
                
                  for(Aluno aa : dd.retornaMapaDeAlunos().values()){
                    System.out.println("\t- "+aa.getNomeDoAluno()+" ("+aa.getCurso().getNomeDoCurso()+")");
                    
                  }
                  
                }
                System.out.println("Provas e notas recebidas:");
                for(Prova pp : mapaDeProvas.values()){
                  System.out.println("- "+pp.getDisciplina().getNomeDaDisciplina()+" - "+pp.getNomeDaProva()+" ("+pp.getDataDaProva()+")");
                  
                  for(Map.Entry<Integer,Nota> entry : pp.retornaMapaDeNotas().entrySet()){
                    System.out.println("\t - "+mapaDeAlunos.get(entry.getKey()).getNomeDoAluno()+": "+entry.getValue().getValor());
                    
                  }
                  
                }
                    break;
                    
                case 8:
                    System.out.println("Encerrando o programa...");
                    continuar = false;
                    break;
                    
                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        }
        
        sc.close();

    }

}
