import java.util.Map;
import java.util.HashMap;

public class Disciplina {

  private String codigoDaDisciplina;
  private String nomeDaDisciplina;

  public Disciplina (String x,String y){
    
    codigoDaDisciplina = x;
    nomeDaDisciplina = y;
  
  }

  public String getCodigoDaDisciplina() {
    return codigoDaDisciplina;
  }

  public String getNomeDaDisciplina() {
    return nomeDaDisciplina;
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

  public Map<String,Trabalho> retornaMapaDeTrabalhos(){
    return mapaDeTrabalhos;
  }

  private Map<Integer,Aluno> mapaDeAlunos = new HashMap<Integer,Aluno>();

  public void adicionaAluno(Integer codigo,Aluno a){
    mapaDeAlunos.put(codigo,a);
  }

  public Map<Integer,Aluno> retornaMapaDeAlunos(){
    return mapaDeAlunos;
  }

  private Map<Integer,Curso> mapaDeCursos = new HashMap<Integer,Curso>();

  public void adicionaCurso(Integer codigo,Curso c){
    mapaDeCursos.put(codigo,c);
  }

  public Map<Integer,Curso> retornaMapaDeCursos(){
    return mapaDeCursos;
  }

}