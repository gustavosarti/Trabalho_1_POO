package br.ufes.informatica.trabpoo.domain;
import java.time.LocalDate;

public class ProvaFinal extends Avaliacao {

    private String tipoDaAvaliacao;  // Armazena o tipo da avaliação

    public ProvaFinal(Disciplina disciplinaDaAvaliacao, String codigoDaAvaliacao, String nomeDaAvaliacao,
            Double pesoDaAvaliacao, LocalDate dataDaAvaliacao, Integer valorDeData , String tipoDaAvaliacao) {
        // Construtor da classe ProvaFinal que recebe os parâmetros necessários para criar uma avaliação (disciplina, código, nome, peso e data)
        super(disciplinaDaAvaliacao, codigoDaAvaliacao, nomeDaAvaliacao, pesoDaAvaliacao, dataDaAvaliacao, valorDeData);
        // Chama o construtor da superclasse Avaliacao passando os parâmetros recebidos

        this.tipoDaAvaliacao = tipoDaAvaliacao;
        // Atribui o tipo da avaliação à variável tipoDaAvaliacao
    }

    @Override
    public String getTipoDaAvaliacao() {
        // Método para obter o tipo da avaliação
        return tipoDaAvaliacao;
    }

}

