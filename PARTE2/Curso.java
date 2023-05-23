import java.util.Map;
import java.util.HashMap;

public class Curso{

  private String codigoDoCurso;
  private String nomeDoCurso;

   public Curso (String z){
    codigoDoCurso = z;
  
  }
  public Curso (String x,String y){
    codigoDoCurso = x;
    nomeDoCurso = y;
  
  }
  
  public String getCodigoDoCurso() {
    return codigoDoCurso;
  }

  public String getNomeDoCurso() {
    return nomeDoCurso;
  }

  private Map<Integer,Aluno> mapaDeAlunos = new HashMap<Integer,Aluno>();

  public void adicionaAluno(Integer codigo,Aluno a){
    mapaDeAlunos.put(codigo,a);
  }

  public Map<Integer,Aluno> retornaMapaDeAlunos(){
    return mapaDeAlunos;
  }

public Map<Integer, Curso> getAlunos() {
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
