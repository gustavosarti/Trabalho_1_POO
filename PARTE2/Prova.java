import java.time.LocalDate;
import java.util.Map;
import java.util.HashMap;
import java.io.Serializable;

public class Prova implements Serializable {
  
  private Disciplina disciplinaDaProva;
  private String codigoDaProva;
  private String nomeDaProva;
  private double pesoDaProva;
  private LocalDate dataDaProva;
  private String tipoDeProva;

  public Prova (Disciplina x,String y,String z,Double a,LocalDate b,String c){

    disciplinaDaProva = x;
    codigoDaProva = y;
    nomeDaProva = z;
    pesoDaProva = a;
    dataDaProva = b;
    tipoDeProva = c;

  }

  public Disciplina getDisciplinaDaProva() {
    return disciplinaDaProva;
  }

  public String getCodigoDaProva() {
    return codigoDaProva;
  }

  public String getNomeDaProva() {
    return nomeDaProva;
  }

  public double getPesoDaProva() {
    return pesoDaProva;
  }

  public LocalDate getDataDaProva() {
    return dataDaProva;
  }

  public String getTipoDeProva() {
    return tipoDeProva;
  }

  private Map<Integer,Nota> mapaDeNotas = new HashMap<Integer,Nota>();

  public void adicionaNota(Integer codigo,Nota n){
    mapaDeNotas.put(codigo,n);
  }

  public Map<Integer,Nota> retornaMapaDeNotas(){
    return mapaDeNotas;
  }

  public Map<Integer, Curso> getDisciplinas() {
    return null;
  }

}
