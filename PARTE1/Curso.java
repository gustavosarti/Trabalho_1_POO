import java.util.HashMap;

public class Curso{

  private Integer codigoDoCurso;

  public Integer getCodigoDoCurso() {
    return codigoDoCurso;
  }

  public void setCodigoDoCurso(Integer codigoDoCurso) {
    this.codigoDoCurso = codigoDoCurso;
  }

  private String nomeDoCurso;

  public String getNomeDoCurso() {
    return nomeDoCurso;
  }

  public void setNomeDoCurso(String nomeDoCurso) {
    this.nomeDoCurso = nomeDoCurso;
  }

  private HashMap<Integer,Aluno> mapaDeAlunos = new HashMap<Integer,Aluno>();

  public void adicionaAluno(Integer codigo,Aluno a){
    mapaDeAlunos.put(codigo,a);
  }

  public HashMap<Integer,Aluno> retornaMapaDeAlunos(){
    return mapaDeAlunos;
  }

public HashMap<Integer, Curso> getAlunos() {
    return null;
}

public String getMatricula() {
    return null;
}

public String getNomeDaProva() {
    return null;
}

public String getCodigoDaDisciplina() {
    return null;
}

}
