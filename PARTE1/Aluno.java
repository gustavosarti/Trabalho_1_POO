import java.util.HashMap;

public class Aluno{

  private Integer matricula;

  public Integer getMatricula() {
    return matricula;
  }

  public void setMatricula(Integer matricula) {
    this.matricula = matricula;
  }

  private String nomeDoAluno;

  public String getNomeDoAluno() {
    return nomeDoAluno;
  }

  public void setNomeDoAluno(String nomeDoAluno) {
    this.nomeDoAluno = nomeDoAluno;
  }

  private Curso curso;

  public Curso getCurso() {
    return curso;
  }

  public void setCurso(Curso curso) {
    this.curso = curso;
    }

}