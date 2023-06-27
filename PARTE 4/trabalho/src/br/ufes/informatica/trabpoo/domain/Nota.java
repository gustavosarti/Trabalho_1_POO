package br.ufes.informatica.trabpoo.domain;

import java.io.Serializable;

public class Nota implements Serializable {
  
  private Double valor; // Armazena o valor o da nota

  public Nota(Double valor) {
    // Construtor da classe Nota que recebe o valor da nota
    this.valor = valor;
  }
  
  public Double getValor() {
    // Método para obter o valor da nota
    return valor;
  }
}
