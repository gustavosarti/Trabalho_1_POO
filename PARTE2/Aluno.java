import java.util.Map;
import java.util.HashMap;
import java.io.Serializable;

public class Aluno implements Serializable {

  private Integer matriculaDoAluno;
  private String nomeDoAluno;
  private Curso cursoDoAluno;

  public Aluno (Integer x,String y,Curso z){

    matriculaDoAluno = x;
    nomeDoAluno = y;
    cursoDoAluno = z;
    
  }

  public Integer getMatricula() {
    return matriculaDoAluno;
  }

  public String getNomeDoAluno() {
    return nomeDoAluno;
  }

  public Curso getCurso() {
    return cursoDoAluno;
  }

  private Map<String,Prova> mapaDeProvas = new HashMap<String,Prova>();

  public void adicionaProva(String codigo,Prova p){
    mapaDeProvas.put(codigo,p);
  }

  public Map<String,Prova> retornaMapaDeProvas(){
    return mapaDeProvas;
  }

  private Map<String,Trabalho> mapaDeTrabalhos = new HashMap<String,Trabalho>();

  public void adicionaTrabalho(String codigo,Trabalho t){
    mapaDeTrabalhos.put(codigo,t);
  }
}
