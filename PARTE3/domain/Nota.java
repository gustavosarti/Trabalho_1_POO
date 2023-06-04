package domain;
import java.io.Serializable;

public class Nota implements Serializable {
  
  private Double valor;

  public Nota(Double valor) {
    // Construtor da classe Nota que recebe o valor da nota
    this.valor = valor;
  }
  
  public Double getValor() {
    // Método para obter o valor da nota
    return valor;
  }
}
