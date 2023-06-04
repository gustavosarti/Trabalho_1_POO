package domain;

class AlunoGraduacao extends Aluno {

  private Curso cursoDoAluno;

  public AlunoGraduacao(Integer matriculaDoAluno, String nomeDoAluno, Curso cursoDoAluno) {
    // Construtor da classe AlunoGraduacao que recebe a matrícula, o nome e o curso do aluno
    super(matriculaDoAluno, nomeDoAluno);
    // Chama o construtor da classe pai (Aluno) para inicializar a matrícula e o nome do aluno
    this.cursoDoAluno = cursoDoAluno;
    // Atribui o curso do aluno à variável cursoDoAluno
  }

  public Curso getCurso() {
    // Método para obter o curso do aluno
    return cursoDoAluno;
  }
}
