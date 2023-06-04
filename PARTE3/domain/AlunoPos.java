package domain;

class AlunoPos extends Aluno {

  private String nivelDePosDoAluno;

  public AlunoPos(Integer matriculaDoAluno, String nomeDoAluno, String nivelDePosDoAluno) {
    // Construtor da classe AlunoPos que recebe a matrícula, o nome e a pós-graduação do aluno
    super(matriculaDoAluno, nomeDoAluno);
    // Chama o construtor da classe pai (Aluno) para inicializar a matrícula e o nome do aluno
    this.nivelDePosDoAluno = nivelDePosDoAluno;
    // Atribui a pós-graduação do aluno à variável posGraduacaoDoAluno
  }

  public String getNivelDePosDoAluno() {
    // Método para obter a pós-graduação do aluno
    return nivelDePosDoAluno;
  }
}
