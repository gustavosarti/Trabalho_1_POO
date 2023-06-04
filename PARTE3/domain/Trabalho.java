package domain;
import java.time.LocalDate;

class Trabalho extends Avaliacao {

  private Integer quantidadeDeAlunos;

  public Trabalho(Disciplina disciplinaDaAvaliacao, String codigoDaAvaliacao, String nomeDaAvaliacao,Double pesoDaAvaliacao, LocalDate dataDaAvaliacao, Integer quantidadeDeAlunos) {
    // Construtor da classe Trabalho que recebe os parâmetros necessários para criar uma avaliação de trabalho (disciplina, código, nome, peso, data e quantidade de alunos)
    super(disciplinaDaAvaliacao, codigoDaAvaliacao, nomeDaAvaliacao, pesoDaAvaliacao, dataDaAvaliacao);
    // Chama o construtor da superclasse Avaliacao passando os parâmetros recebidos
    this.quantidadeDeAlunos = quantidadeDeAlunos;
    // Atribui a quantidade de alunos à variável quantidadeDeAlunos da classe Trabalho
  }

  public Integer getQuantidadeDeAlunos() {
    return quantidadeDeAlunos;
    // Retorna a quantidade de alunos do trabalho
  }

}
