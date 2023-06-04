package domain;
import java.time.LocalDate;

class ProvaFinal extends Avaliacao {

  public ProvaFinal(Disciplina disciplinaDaAvaliacao, String codigoDaAvaliacao, String nomeDaAvaliacao,
            Double pesoDaAvaliacao, LocalDate dataDaAvaliacao) {
        // Construtor da classe ProvaFinal que recebe os parâmetros necessários para criar uma avaliação (disciplina, código, nome, peso e data)
        super(disciplinaDaAvaliacao, codigoDaAvaliacao, nomeDaAvaliacao, pesoDaAvaliacao, dataDaAvaliacao);
        // Chama o construtor da superclasse Avaliacao passando os parâmetros recebidos
    }

}
