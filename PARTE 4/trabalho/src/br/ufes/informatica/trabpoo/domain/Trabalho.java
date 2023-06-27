package br.ufes.informatica.trabpoo.domain;

import java.time.LocalDate;

public class Trabalho extends Avaliacao {

  private Integer quantidadeDeAlunos;  // Armazena a quantidade de alunos do trabalho

  public Trabalho(Disciplina disciplinaDaAvaliacao, String codigoDaAvaliacao, String nomeDaAvaliacao, Double pesoDaAvaliacao, LocalDate dataDaAvaliacao, Integer valorDeData, Integer quantidadeDeAlunos) {
    // Construtor da classe Trabalho que recebe os parâmetros necessários para criar uma avaliação de trabalho (disciplina, código, nome, peso, data e quantidade de alunos)
    super(disciplinaDaAvaliacao, codigoDaAvaliacao, nomeDaAvaliacao, pesoDaAvaliacao, dataDaAvaliacao, valorDeData);
    // Chama o construtor da superclasse Avaliacao passando os parâmetros recebidos
    this.quantidadeDeAlunos = quantidadeDeAlunos;
    // Atribui a quantidade de alunos à variável quantidadeDeAlunos da classe Trabalho
  }

  public Integer getQuantidadeDeAlunos() {
    // Método para obter a quantidade de alunos do trabalho
    return quantidadeDeAlunos;
  }

  @Override
  public Integer retornaQuantidadeDeAlunos(){
    // Sobrescreve o método retornaQuantidadeDeAlunos da superclasse Avaliacao
    return quantidadeDeAlunos;
  }

  @Override
  public String getTipoDaAvaliacao() {
    // Sobrescreve o método getTipoDaAvaliacao da superclasse Avaliacao
    return "T";
  }

}

