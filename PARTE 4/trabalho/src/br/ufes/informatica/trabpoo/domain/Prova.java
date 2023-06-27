package br.ufes.informatica.trabpoo.domain;

import java.time.LocalDate;

public class Prova extends Avaliacao {

  public Prova(Disciplina disciplinaDaAvaliacao, String codigoDaAvaliacao, String nomeDaAvaliacao,
      Double pesoDaAvaliacao, LocalDate dataDaAvaliacao, Integer valorDeData) {
    // Construtor da classe Prova que recebe os parâmetros necessários para criar uma avaliação (disciplina, código, nome, peso e data)
    super(disciplinaDaAvaliacao, codigoDaAvaliacao, nomeDaAvaliacao, pesoDaAvaliacao, dataDaAvaliacao, valorDeData);
    // Chama o construtor da superclasse Avaliacao passando os parâmetros recebidos
  }

}
