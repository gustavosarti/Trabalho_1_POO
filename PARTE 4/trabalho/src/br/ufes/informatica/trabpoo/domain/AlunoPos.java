package br.ufes.informatica.trabpoo.domain;

public class AlunoPos extends Aluno{

  private String nivelDePosDoAluno;  // Armazena o nível de pós-graduação do aluno

  public AlunoPos(Integer matriculaDoAluno, String nomeDoAluno, String nivelDePosDoAluno) {
    // Construtor da classe AlunoPos que recebe a matrícula, o nome e a pós-graduação do aluno
    super(matriculaDoAluno, nomeDoAluno);
    // Chama o construtor da classe pai (Aluno) para inicializar a matrícula e o nome do aluno
    this.nivelDePosDoAluno = nivelDePosDoAluno;
    // Atribui a pós-graduação do aluno à variável nivelDePosDoAluno
  }

  public String getNivelDePosDoAluno() {
    // Método para obter a pós-graduação do aluno
    return nivelDePosDoAluno;
  }

  @Override
  public String retornaNomeDoCursoOuPos() {
    // Método sobrescrito para retornar o nome da pós-graduação do aluno
    if (nivelDePosDoAluno.equals("M")) {
      return "Mestrado";
    } else if (nivelDePosDoAluno.equals("D")) {
      return "Doutorado";
    } else {
      return null;
    }
  }
}

