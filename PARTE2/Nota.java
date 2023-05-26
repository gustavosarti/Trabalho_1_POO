import java.io.Serializable;

public class Nota implements Serializable {
  
  private Double valor;

  public Nota (Double a){

  valor = a;
    
  }
  
  public Double getValor() {
    return valor;
  }

}
