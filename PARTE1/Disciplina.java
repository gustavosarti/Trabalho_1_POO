import java.util.HashMap;

public class Disciplina {

  private String codigoDaDisciplina;

  public String getCodigoDaDisciplina() {
    return codigoDaDisciplina;
  }

  public void setCodigoDaDisciplina(String codigoDaDisciplina) {
    this.codigoDaDisciplina = codigoDaDisciplina;
  }

  private String nomeDaDisciplina;

  public String getNomeDaDisciplina() {
    return nomeDaDisciplina;
  }

  public void setNomeDaDisciplina(String nomeDaDisciplina) {
    this.nomeDaDisciplina = nomeDaDisciplina;
  }

  private HashMap<String,Prova> mapaDeProvas = new HashMap<String,Prova>();

  public void adicionaProva(String codigo,Prova p){
    mapaDeProvas.put(codigo,p);
  }

  public HashMap<String,Prova> retornaMapaDeProvas(){
    return mapaDeProvas;
  }

  private HashMap<Integer,Aluno> mapaDeAlunos = new HashMap<Integer,Aluno>();

  public void adicionaAluno(Integer codigo,Aluno a){
    mapaDeAlunos.put(codigo,a);
  }

  public HashMap<Integer,Aluno> retornaMapaDeAlunos(){
    return mapaDeAlunos;
  }
/*
public HashMap<Integer, Curso> getProvas() {
    return null;
}

public HashMap<Integer, Curso> getAlunos() {
    return null;
}
*/
}