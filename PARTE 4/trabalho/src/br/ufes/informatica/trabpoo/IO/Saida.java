package br.ufes.informatica.trabpoo.IO;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.LinkedList;
import java.util.Collections;

import br.ufes.informatica.trabpoo.domain.Curso;
import br.ufes.informatica.trabpoo.domain.Disciplina;
import br.ufes.informatica.trabpoo.domain.Avaliacao;
import br.ufes.informatica.trabpoo.domain.Aluno;
import br.ufes.informatica.trabpoo.domain.Nota;
import br.ufes.informatica.trabpoo.domain.CursoComMedia;

import br.ufes.informatica.trabpoo.util.Funcao;

public class Saida {

    public static void gerarRelatorioPautaFinal() throws FileNotFoundException, IOException, Exception {

        // Loop para cada disciplina no mapa de disciplinas retornado pelo método Entrada.retornaMapaDeDisciplinas()
        for (Disciplina disciplina : Entrada.retornaMapaDeDisciplinas().values()) {

            if (disciplina.retornaMapaDeAvaliacoes().isEmpty()) {
                throw new Exception("A disciplina " + disciplina.getCodigoDaDisciplina() + " não possui nenhuma avaliação cadastrada.");
            }

            // Nome do arquivo
            String nomeArquivo = "1-pauta-" + disciplina.getCodigoDaDisciplina() + ".csv";

            // Criando arquivo para escrita
            BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo));

            // Escrevendo cabeçalho no arquivo
            writer.write("Matrícula;Aluno;");

            LinkedList<Avaliacao> listaDeAvaliacoes = new LinkedList<>();

            for (Avaliacao avaliacao : disciplina.retornaMapaDeAvaliacoes().values()) {
                Avaliacao a = avaliacao;
                listaDeAvaliacoes.add(a);
            }

            // Ordenando o mapa de avaliações pela data
            Collections.sort(listaDeAvaliacoes);

            // Loop para cada avaliação na disciplina
            for (Avaliacao avaliacao : listaDeAvaliacoes) {
                // Verifica se o tipo da avaliação é "N" (nota) ou "T" (trabalho)
                if (avaliacao.getTipoDaAvaliacao().equals("N") || avaliacao.getTipoDaAvaliacao().equals("T")) {
                    // Escreve o código da avaliação no arquivo
                    writer.write(avaliacao.getCodigoDaAvaliacao() + ";");
                }
            }

            // Escreve os cabeçalhos para Média Parcial, Prova Final e Média Final
            writer.write("Média Parcial;Prova Final;Média Final");

            // Quebra de linha após o cabeçalho
            writer.write("\n");

            LinkedList<Aluno> listaDeAlunos = new LinkedList<>();

            for (Aluno aluno : disciplina.retornaMapaDeAlunos().values()) {
                Aluno a = aluno;
                listaDeAlunos.add(a);
            }

            // Ordenando o mapa de alunos pelo nome
            Collections.sort(listaDeAlunos);

            // Loop para cada aluno na disciplina
            for (Aluno aluno : listaDeAlunos) {
                // Escreve a matrícula e o nome do aluno no arquivo
                writer.write(aluno.getMatricula() + ";" + aluno.getNomeDoAluno() + ";");

                double mediaParcial = Funcao.calculaMediaParcial(disciplina, aluno);
                double notaDaProvaFinal = 0.0;

                // Loop para cada avaliação na disciplina
                for (Avaliacao avaliacao : listaDeAvaliacoes) {
                    // Loop para cada entrada no mapa de notas da avaliação
                    for (Map.Entry<String, Nota> entry : avaliacao.retornaMapaDeNotas().entrySet()) {
                        // Verifica se a matrícula do aluno corresponde à entrada no mapa de notas
                        if (aluno.getMatricula().toString().equals(entry.getKey())) {
                            // Verifica se o tipo da avaliação é "N" (nota) ou "T" (trabalho)
                            if (avaliacao.getTipoDaAvaliacao().equals("N") || avaliacao.getTipoDaAvaliacao().equals("T")) {
                                // Escreve o valor da nota no arquivo
                                String valorFormatado = String.format("%.2f", entry.getValue().getValor());
                                valorFormatado = valorFormatado.replace(".", ",");
                                writer.write(valorFormatado + ";");
                            } else if (avaliacao.getTipoDaAvaliacao().equals("F")) {
                                // Verifica se a média parcial é menor que 7.00
                                if (mediaParcial < 7.00) {
                                    // Obtém o valor da nota da prova final
                                    notaDaProvaFinal = entry.getValue().getValor();
                                }
                            }
                        }
                    }
                }

                // Calcula a média final
                double mediaFinal = (mediaParcial + notaDaProvaFinal) / 2;

                // Verifica se a média parcial é maior que 7.00
                if (mediaParcial >= 7.00) {
                    // Escreve a média parcial e os campos de prova final e média final como "-"
                    String mediaFormatada = String.format("%.2f", mediaParcial);
                    mediaFormatada = mediaFormatada.replace(".", ",");
                    writer.write(mediaFormatada + ";-;" + mediaFormatada);
                    writer.write("\n");
                } else {
                    // Escreve a média parcial, média final e a média parcial novamente
                    String mediaParcialFormatada = String.format("%.2f", mediaParcial);
                    String mediaFinalFormatada = String.format("%.2f", mediaFinal);
                    String notaDaProvaFinalFormatada = String.format("%.2f", notaDaProvaFinal);
                    mediaParcialFormatada = mediaParcialFormatada.replace(".", ",");
                    mediaFinalFormatada = mediaFinalFormatada.replace(".", ",");
                    notaDaProvaFinalFormatada = notaDaProvaFinalFormatada.replace(".", ",");
                    writer.write(mediaParcialFormatada + ";" + notaDaProvaFinalFormatada + ";" + mediaFinalFormatada);
                    writer.write("\n");
                }
            }

            // Fecha o arquivo
            writer.close();
        }
    }

    public static void gerarRelatorioEstatisticasDisciplina() throws FileNotFoundException, IOException, Exception {

        LinkedList<Disciplina> listaDeDisciplinas = new LinkedList<>();

        for (Disciplina disciplina : Entrada.retornaMapaDeDisciplinas().values()) {
            Disciplina d = disciplina;
            listaDeDisciplinas.add(d);
        }

        // Ordenando o mapa de disciplinas pelo codigo
        Collections.sort(listaDeDisciplinas);

        // Nome do arquivo
        String nomeArquivo = "2-disciplinas.csv";

        // Criando arquivo
        BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo));

        // Escrevendo cabeçalho no arquivo
        writer.write("Código;Disciplina;Curso;Média;% Aprovados");
        writer.write("\n");

        for (Disciplina disciplina : listaDeDisciplinas) {

            LinkedList<CursoComMedia> listaDeCursoComMedia = new LinkedList<>();

            // Iterar sobre cada curso envolvido na disciplina
            for (Curso curso : disciplina.retornaMapaDeCursos().values()) {
                double mediaDosAlunosDoCurso = Funcao.retornaMediaDosAlunosDoCursoNaDisciplina(curso, disciplina);
                String nomeDoCurso = curso.getNomeDoCurso();
                CursoComMedia cursoComMedia = new CursoComMedia(nomeDoCurso, mediaDosAlunosDoCurso);
                listaDeCursoComMedia.add(cursoComMedia);
            }

            // Ordenando o mapa de curso pelo nome do curso e pela média
            Collections.sort(listaDeCursoComMedia);
            Collections.sort(listaDeCursoComMedia, new CursoComMedia.ComparadorMedia());

            for (CursoComMedia curso : listaDeCursoComMedia) {
                double percentualDeAprovados = Funcao.retornaPercentualDeAprovados(curso, disciplina);
                String mediaDoCursoFormatada = String.format("%.2f", curso.getMedia());
                mediaDoCursoFormatada = mediaDoCursoFormatada.replace(".", ",");
                String percentualDeAprovadosFormatado = String.format("%.1f", percentualDeAprovados);
                percentualDeAprovadosFormatado = percentualDeAprovadosFormatado.replace(".", ",");
                writer.write(disciplina.getCodigoDaDisciplina() + ";" + disciplina.getNomeDaDisciplina() + ";" + curso.getNomeDoCurso() + ";" + mediaDoCursoFormatada + ";" + percentualDeAprovadosFormatado + "%");
                writer.write("\n");
            }
        }

        writer.close();
    }

    public static void gerarRelatorioEstatisticasAvaliacao() throws FileNotFoundException, IOException, Exception {

        LinkedList<Disciplina> listaDeDisciplinas = new LinkedList<>();

        for (Disciplina disciplina : Entrada.retornaMapaDeDisciplinas().values()) {
            Disciplina d = disciplina;
            listaDeDisciplinas.add(d);
        }

        // Ordenando o mapa de disciplinas pelo codigo
        Collections.sort(listaDeDisciplinas);

        // Nome do arquivo
        String nomeArquivo = "3-avaliacoes.csv";

        // Criando arquivo
        BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo));

        // Escrevendo cabeçalho no arquivo
        writer.write("Disciplina;Código;Avaliação;Data;Média");
        writer.write("\n");

        for (Disciplina disciplina : listaDeDisciplinas) {

            LinkedList<Avaliacao> listaDeAvaliacoes = new LinkedList<>();

            for (Avaliacao avaliacao : disciplina.retornaMapaDeAvaliacoes().values()) {
                Avaliacao a = avaliacao;
                listaDeAvaliacoes.add(a);
            }

            Collections.sort(listaDeAvaliacoes);

            for (Avaliacao avaliacao : listaDeAvaliacoes) {
                if (avaliacao.getTipoDaAvaliacao().equals("N") || avaliacao.getTipoDaAvaliacao().equals("T")) {
                    if (avaliacao.retornaMapaDeNotas() != null) {
                        avaliacao.calculaMedia();
                    } else {
                        writer.close();
                        throw new Exception("Não foram registradas notas para essa avaliação.");
                    }

                    String mediaDasNotasFormatada = String.format("%.2f", avaliacao.getMediaDasNotas());
                    mediaDasNotasFormatada = mediaDasNotasFormatada.replace(".", ",");
                    String dataFormatada = Funcao.formataData(avaliacao.getDataDaAvaliacao());

                    writer.write(disciplina.getCodigoDaDisciplina() + ";" + avaliacao.getCodigoDaAvaliacao() + ";" + avaliacao.getNomeDaAvaliacao() + ";" + dataFormatada + ";" + mediaDasNotasFormatada);
                    writer.write("\n");
                }
            }
        }

        writer.close();
    }

      public static void imprimeRelatorios() throws FileNotFoundException, IOException, Exception {
          
          gerarRelatorioPautaFinal();
          gerarRelatorioEstatisticasDisciplina();
          gerarRelatorioEstatisticasAvaliacao();
  
    }
  
}

