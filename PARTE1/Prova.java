import java.time.LocalDate;
import java.util.HashMap;

public class Prova {

  private Disciplina disciplina;

  public Disciplina getDisciplina() {
    return disciplina;
  }

  public void setDisciplina(Disciplina disciplina) {
    this.disciplina = disciplina;
  }

  private String codigoDaProva;

  public String getCodigoDaProva() {
    return codigoDaProva;
  }

  public void setCodigoDaProva(String codigoDaProva) {
    this.codigoDaProva = codigoDaProva;
  }

  private String nomeDaProva;

  public String getNomeDaProva() {
    return nomeDaProva;
  }

  public void setNomeDaProva(String nomeDaProva) {
    this.nomeDaProva = nomeDaProva;
  }

  private double pesoDaProva;

  public double getPesoDaProva() {
    return pesoDaProva;
  }

  public void setPesoDaProva(double pesoDaProva) {
    this.pesoDaProva = pesoDaProva;
  }

  private LocalDate dataDaProva;

  public LocalDate getDataDaProva() {
    return dataDaProva;
  }

  public void setDataDaProva(LocalDate dataDaProva) {
    this.dataDaProva = dataDaProva;
  }

   private HashMap<Integer,Nota> mapaDeNotas = new HashMap<Integer,Nota>();

  public void adicionaNota(Integer codigo,Nota n){
    mapaDeNotas.put(codigo,n);
  }

  public HashMap<Integer,Nota> retornaMapaDeNotas(){
    return mapaDeNotas;
  }

public HashMap<Integer, Curso> getDisciplinas() {
    return null;
}
  

}