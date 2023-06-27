package br.ufes.informatica.trabpoo.domain;

import java.util.Map;
import java.util.HashMap;
import java.io.Serializable;

public abstract class Aluno implements Serializable, Comparable<Aluno> {

  private Integer matriculaDoAluno;  // Armazena a matrícula do aluno
  private String nomeDoAluno;  // Armazena o nome do aluno
  
  public Aluno(Integer matriculaDoAluno, String nomeDoAluno) {
    // Construtor da classe Aluno que recebe a matrícula e o nome do aluno
    this.matriculaDoAluno = matriculaDoAluno;
    this.nomeDoAluno = nomeDoAluno;
  }

  public Integer getMatricula() {
    // Método para obter a matrícula do aluno
    return matriculaDoAluno;
  }

  public String getNomeDoAluno() {
    // Método para obter o nome do aluno
    return nomeDoAluno;
  }

  private Map<String, Avaliacao> mapaDeAvaliacoes = new HashMap<>();
  // Mapa para armazenar as avaliações do aluno, usando o código da avaliação como chave

  public void adicionaAvaliacao(String codigoDaAvaliacao, Avaliacao avaliacao) {
    // Método para adicionar uma avaliação ao mapa de avaliações do aluno
    mapaDeAvaliacoes.put(codigoDaAvaliacao, avaliacao);
  }

  public Map<String, Avaliacao> retornaMapaDeAvaliacoes() {
    // Método para retornar o mapa de avaliações do aluno
    return mapaDeAvaliacoes;
  }

  public String retornaNomeDoCursoOuPos() {
    // Método para retornar o nome do curso ou pós do aluno
    return "0";
  }

  @Override
  public int compareTo(Aluno aluno){

    return this.getNomeDoAluno().compareTo(aluno.getNomeDoAluno());

  }

}
