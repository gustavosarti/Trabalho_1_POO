package br.ufes.informatica.trabpoo.domain;

import java.time.LocalDate;
import java.util.Map;
import java.util.HashMap;
import java.io.Serializable;

public abstract class Avaliacao implements Serializable, Comparable<Avaliacao> {
  
  private Disciplina disciplinaDaAvaliacao;  // Armazena a disciplina da avaliação
  private String codigoDaAvaliacao;  // Armazena o código da avaliação
  private String nomeDaAvaliacao;  // Armazena o nome da avaliação
  private double pesoDaAvaliacao;  // Armazena o peso da avaliação
  private LocalDate dataDaAvaliacao;  // Armazena a data da avaliação
  private double somaDasNotas;  // Armazena a soma das notas dos alunos
  private double somaDosPesos;  // Armazena a soma dos pesos das avaliações
  private double mediaDasNotas;  // Armazena a média das notas das avaliações
  private Integer valorDeData;  //Armazena o valor de data da avaliação

  public Avaliacao(Disciplina disciplinaDaAvaliacao, String codigoDaAvaliacao, String nomeDaAvaliacao, Double pesoDaAvaliacao, LocalDate dataDaAvaliacao, Integer valorDeData) {
    // Construtor da classe Avaliacao que recebe a disciplina, o código, o nome, o peso , a data da avaliação e o valor de data da avaliação
    this.disciplinaDaAvaliacao = disciplinaDaAvaliacao;
    this.codigoDaAvaliacao = codigoDaAvaliacao;
    this.nomeDaAvaliacao = nomeDaAvaliacao;
    this.pesoDaAvaliacao = pesoDaAvaliacao;
    this.dataDaAvaliacao = dataDaAvaliacao;
    this.valorDeData = valorDeData;
  }

  public Disciplina getDisciplinaDaAvaliacao() {
    // Método para obter a disciplina da avaliação
    return disciplinaDaAvaliacao;
  }

  public String getCodigoDaAvaliacao() {
    // Método para obter o código da avaliação
    return codigoDaAvaliacao;
  }

  public String getNomeDaAvaliacao() {
    // Método para obter o nome da avaliação
    return nomeDaAvaliacao;
  }

  public double getPesoDaAvaliacao() {
    // Método para obter o peso da avaliação
    return pesoDaAvaliacao;
  }

  public LocalDate getDataDaAvaliacao() {
    // Método para obter a data da avaliação
    return dataDaAvaliacao;
  }

  public Integer getValorDeData() {
    // Método para obter o valor de data da avaliação
    return valorDeData;
  }

  public double getMediaDasNotas() {
    // Método para obter a média das notas
    return mediaDasNotas;
  }

  public void adicionaNaMedia(double notaDoAluno, double pesoDaAvaliacao){
    // Método para adicionar uma nota à soma das notas e atualizar a soma dos pesos
    somaDasNotas = somaDasNotas + (notaDoAluno*pesoDaAvaliacao);
    somaDosPesos = somaDosPesos + pesoDaAvaliacao;
  }

  public void calculaMedia(){
    // Método para calcular a média das notas
    mediaDasNotas = somaDasNotas/somaDosPesos;
  }

  private Map<String, Nota> mapaDeNotas = new HashMap<>();

  public void adicionaNota(String matriculaDoAluno, Nota nota) {
    // Método para adicionar uma nota ao mapa de notas da avaliação
    mapaDeNotas.put(matriculaDoAluno, nota);
  }

  public Map<String, Nota> retornaMapaDeNotas() {
    // Método para retornar o mapa de notas da avaliação
    return mapaDeNotas;
  }

  public Integer retornaQuantidadeDeAlunos(){
    // Método para retornar a quantidade de alunos
    return 1;
  }

  public String getTipoDaAvaliacao() {
    // Método para obter o tipo da avaliação
    return "N";
  }

@Override
public int compareTo(Avaliacao avaliacao) {
    // Comparação com base no valor de data da avaliação
    // Se o valor de data desta avaliação for maior que o valor de data da outra avaliação,
    // retorna 1 indicando que esta avaliação é maior
    if (this.getValorDeData() > avaliacao.getValorDeData()) {
        return 1;
    }
    // Se o valor de data desta avaliação for menor que o valor de data da outra avaliação,
    // retorna -1 indicando que esta avaliação é menor
    if (this.getValorDeData() < avaliacao.getValorDeData()) {
        return -1;
    }
    // Se os valores de data forem iguais, retorna 0 indicando que as avaliações são iguais
    return 0;
}


}

