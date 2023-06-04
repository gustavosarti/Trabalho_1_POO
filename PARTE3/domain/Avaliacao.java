package domain;

import java.time.LocalDate;
import java.util.Map;
import java.util.HashMap;
import java.io.Serializable;

public class Avaliacao implements Serializable {
  
  private Disciplina disciplinaDaAvaliacao;
  private String codigoDaAvaliacao;
  private String nomeDaAvaliacao;
  private double pesoDaAvaliacao;
  private LocalDate dataDaAvaliacao;
  private double somaDasNotas;
  private double somaDosPesos;
  private double mediaDasNotas;

  public Avaliacao(Disciplina disciplinaDaAvaliacao, String codigoDaAvaliacao, String nomeDaAvaliacao, Double pesoDaAvaliacao, LocalDate dataDaAvaliacao) {
    // Construtor da classe Avaliacao que recebe a disciplina, o código, o nome, o peso e a data da avaliação
    this.disciplinaDaAvaliacao = disciplinaDaAvaliacao;
    this.codigoDaAvaliacao = codigoDaAvaliacao;
    this.nomeDaAvaliacao = nomeDaAvaliacao;
    this.pesoDaAvaliacao = pesoDaAvaliacao;
    this.dataDaAvaliacao = dataDaAvaliacao;
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

  private Map<Integer, Nota> mapaDeNotas = new HashMap<>();

  public void adicionaNota(Integer codigoDaAvaliacao, Nota nota) {
    // Método para adicionar uma nota ao mapa de notas da avaliação
    mapaDeNotas.put(codigoDaAvaliacao, nota);
  }

  public Map<Integer, Nota> retornaMapaDeNotas() {
    // Método para retornar o mapa de notas da avaliação
    return mapaDeNotas;
  }

  public Map<Integer, Curso> getDisciplinas() {
    // Método para obter as disciplinas (retorna null, pois parece estar inacabado ou não utilizado)
    return null;
  }

}
