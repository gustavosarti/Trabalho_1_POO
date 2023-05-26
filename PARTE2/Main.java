import java.util.Scanner;

public class Main {

    // Scanner para leitura de entrada do usuário
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        boolean continuar = true;

        while (continuar) {
            System.out.println("=== MENU ===");
            System.out.println("1 - Cadastrar curso ou tipo de pós-graduação");
            System.out.println("2 - Cadastrar disciplina");
            System.out.println("3 - Cadastrar prova, trabalho ou prova final");
            System.out.println("4 - Cadastrar aluno");
            System.out.println("5 - Matricular aluno em disciplina");
            System.out.println("6 - Registrar nota de aluno em prova");
            System.out.println("7 - Registrar curso em disciplina");
            System.out.println("8 - Imprimir dados");
            System.out.println("9 - Relatórios");
            System.out.println("10 - Carregar");
            System.out.println("11 - Salvar");
            System.out.println("12 - Sair do programa");
            System.out.print("Digite uma opção: ");
            int opcao = sc.nextInt();

            switch (opcao) {
                case 1:
                    Funcao.cadastrarCurso();
                    break;
                case 2:
                    Funcao.cadastrarDisciplina();
                    break;
                case 3:
                    Funcao.cadastrarProvaouTrabalho();
                    break;
                case 4:
                    Funcao.cadastrarAluno();
                    break;
                case 5:
                    Funcao.matricularAlunoDisciplina();
                    break;
                case 6:
                    Funcao.registrarNota();
                    break;
                case 7:
                    Funcao.registrarCursoemDisciplina();
                    break;
                case 8:
                    Funcao.imprimirDados();
                    break;
                case 9:
                    Funcao.exibirRelatorios();
                    break;
                case 10:
                    Funcao.carregar();
                    break;
                case 11:
                    Funcao.salvar();
                    break;
                case 12:
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
