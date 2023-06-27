package br.ufes.informatica.trabpoo.util;

import br.ufes.informatica.trabpoo.domain.Curso;
import br.ufes.informatica.trabpoo.domain.Disciplina;
import br.ufes.informatica.trabpoo.domain.Nota;
import br.ufes.informatica.trabpoo.domain.Avaliacao;
import br.ufes.informatica.trabpoo.IO.Entrada;
import br.ufes.informatica.trabpoo.domain.Aluno;
import br.ufes.informatica.trabpoo.domain.CursoComMedia;

import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.Map;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

//Serialização
//============================================
import java.io.FileInputStream;
import java.io.ObjectInputStream;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
//============================================

public class Funcao {

  public static LocalDate converteParaData(String data) throws DateTimeParseException {
      // Converte a string de data para o formato LocalDate
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
      return LocalDate.parse(data, formatter);
  }
  
  public static String formataData(LocalDate data){
      // Converte o LocalDate para uma string no formato desejado
      String dataString = data.toString();
    
      // Divide a string em partes separadas pelo caractere "-"
      String[] valores = dataString.split("-");
  
      // Formata a string de data no formato desejado (dd/MM/yyyy)
      String dataFormatada = (valores[2] +"/"+valores[1]+ "/"+ valores[0]);
  
      return dataFormatada;
  }
  
  public static Integer geraValorDeData(String data){
      // Divide a string de data em partes separadas pelo caractere "/"
      String[] valores = data.split("/");
  
      // Converte as partes da data em valores inteiros e realiza os cálculos para gerar um valor numérico único
      Integer valor = Integer.parseInt(valores[0]) + (Integer.parseInt(valores[1])*100) + (Integer.parseInt(valores[2])*10000);
  
      return valor;
  }


    // SERIALIZAÇÃO "carregar"
    @SuppressWarnings("unchecked")
    public static void carregar() throws FileNotFoundException, IOException, Exception {
        // Cria um FileInputStream para ler o arquivo "dados.dat"
        FileInputStream fileIn = new FileInputStream("dados.dat");
        // Cria um ObjectInputStream para ler os objetos do arquivo
        ObjectInputStream objectIn = new ObjectInputStream(fileIn);

        // Carrega os HashMaps dos dados do arquivo
        Map<String, Curso> mapaDeCursos = (Map<String, Curso>) objectIn.readObject();
        Map<String, Disciplina> mapaDeDisciplinas = (Map<String, Disciplina>) objectIn.readObject();
        Map<String, Avaliacao> mapaDeAvaliacoes = (Map<String, Avaliacao>) objectIn.readObject();
        Map<String, Aluno> mapaDeAlunos = (Map<String, Aluno>) objectIn.readObject();

        Entrada.setMapaDeCursos(mapaDeCursos);
        Entrada.setMapaDeDisciplinas(mapaDeDisciplinas);
        Entrada.setMapaDeAvaliacoes(mapaDeAvaliacoes);
        Entrada.setMapaDeAlunos(mapaDeAlunos);

        // Fecha os fluxos de leitura
        objectIn.close();
        fileIn.close();
    }

    // SERIALIZAÇÃO "salvar"
    public static void salvar() throws FileNotFoundException, IOException, Exception {
        // Cria um FileOutputStream para escrever no arquivo "dados.dat"
        FileOutputStream fileOut = new FileOutputStream("dados.dat");
        // Cria um ObjectOutputStream para escrever os objetos no arquivo
        ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);

        //ObjectOutputStream objectOut = new ObjectOutputStream(new FileOutputStream("dados.dat"));

        // Salva os HashMaps dos dados no arquivo
        objectOut.writeObject(Entrada.retornaMapaDeCursos());
        objectOut.writeObject(Entrada.retornaMapaDeDisciplinas());
        objectOut.writeObject(Entrada.retornaMapaDeAvaliacoes());
        objectOut.writeObject(Entrada.retornaMapaDeAlunos());

        // Fecha os fluxos de escrita
        objectOut.close();
        fileOut.close();
    }

    public static double calculaMediaParcial(Disciplina disciplina, Aluno aluno) {
        // Variáveis para cálculo da média parcial
        double somaDePeso = 0.0;
        double notaTotal = 0.0;

        // Percorre as avaliações da disciplina
        for (Avaliacao avaliacao : disciplina.retornaMapaDeAvaliacoes().values()) {
            // Percorre as notas do aluno na avaliação
            for (Map.Entry<String, Nota> entry : avaliacao.retornaMapaDeNotas().entrySet()) {
                // Verifica se a nota pertence ao aluno
                if (aluno.getMatricula().toString().equals(entry.getKey())) {
                    // Verifica se a avaliação é do tipo "N" ou "T"
                    if (avaliacao.getTipoDaAvaliacao().equals("N") || avaliacao.getTipoDaAvaliacao().equals("T")) {
                        somaDePeso += avaliacao.getPesoDaAvaliacao();
                        notaTotal += entry.getValue().getValor() * avaliacao.getPesoDaAvaliacao();
                    }
                }
            }
        }

        Double mediaParcial = notaTotal / somaDePeso;

        return mediaParcial;
    }

    public static double retornaPercentualDeAprovados(CursoComMedia curso, Disciplina disciplina) {
        // Variáveis para cálculo do percentual de aprovados
        Double somaDePeso = 0.0;
        Double notaTotal = 0.0;
        Double notaProvaFinal = 0.0;
        Integer qtdAlunos = 0;
        Double aprovados = 0.0;

        // Percorre os alunos da disciplina
        for (Aluno aluno : disciplina.retornaMapaDeAlunos().values()) {

          if (aluno.retornaNomeDoCursoOuPos().equals(curso.getNomeDoCurso())) {
            
                qtdAlunos++;
                somaDePeso = 0.0;
                notaTotal = 0.0;
    
                // Percorre as avaliações do aluno
                for (Avaliacao avaliacao : aluno.retornaMapaDeAvaliacoes().values()) {
                    // Verifica se a avaliação é da disciplina atual
                    if (avaliacao.getDisciplinaDaAvaliacao().getCodigoDaDisciplina().equals(disciplina.getCodigoDaDisciplina())) {
                        // Percorre as notas do aluno na avaliação
                        for (Map.Entry<String, Nota> entry : avaliacao.retornaMapaDeNotas().entrySet()) {
                            // Verifica se a nota pertence ao aluno
                            if (aluno.getMatricula().toString().equals(entry.getKey())) {
                                // Verifica se a avaliação é do tipo "N"
                                if (avaliacao.getTipoDaAvaliacao().equals("N") || avaliacao.getTipoDaAvaliacao().equals("T")) {
                                    somaDePeso += avaliacao.getPesoDaAvaliacao();
                                    notaTotal += entry.getValue().getValor() * avaliacao.getPesoDaAvaliacao();
                                }
                                // Verifica se a avaliação é do tipo "F"
                                else if (avaliacao.getTipoDaAvaliacao().equals("F")) {
                                    notaProvaFinal = entry.getValue().getValor();
                                }
                            }
                        }
                    }
                }
    
            // Calcula a média parcial do aluno na disciplina
            Double mediaParcial = notaTotal / somaDePeso;

            // Verifica se o aluno está aprovado
            if (mediaParcial < 7.0) {
              
                Double mediaFinal = (notaProvaFinal + mediaParcial) / 2; // Calcula a média final
    
                // Verifica se o aluno atingiu a nota mínima para aprovação na média final
                if (mediaFinal >= 5) {
                    aprovados++; // Incrementa o número de alunos aprovados
                }
            } else if (mediaParcial >= 7.0) {
                aprovados++; // Incrementa o número de alunos aprovados
            }
        }
    }

    // Calcula o percentual de alunos aprovados, dividindo o número de aprovados pelo total de alunos na disciplina
    double percentualDeAprovados = (aprovados*100) / (qtdAlunos);

    // Retorna o percentual de alunos aprovados
    return percentualDeAprovados;
  }

    public static double retornaMediaDosAlunosDoCursoNaDisciplina(Curso curso, Disciplina disciplina) {
        // Variáveis para cálculo da média da disciplina
        Double mediaDoCursoNaDisciplina = 0.0;
        Double somaDasMediasDosAlunos = 0.0;
        Integer qtdAlunosDisciplinaCurso = 0;

        // Percorre os alunos da disciplina
        for (Aluno aluno : disciplina.retornaMapaDeAlunos().values()) {
            // Verifica se o aluno pertence ao curso
            if (aluno.retornaNomeDoCursoOuPos().equals(curso.getNomeDoCurso())) {
                Double somaDePeso = 0.0;
                Double notaTotal = 0.0;
                Double notaProvaFinal = 0.0;
                Double mediaDoAluno = 0.0;
                qtdAlunosDisciplinaCurso++;

                // Percorre as avaliações do aluno
                for (Avaliacao avaliacao : aluno.retornaMapaDeAvaliacoes().values()) {
                    // Verifica se a avaliação é da disciplina atual
                    if (avaliacao.getDisciplinaDaAvaliacao().getCodigoDaDisciplina().equals(disciplina.getCodigoDaDisciplina())) {
                        // Percorre as notas do aluno na avaliação
                        for (Map.Entry<String, Nota> entry : avaliacao.retornaMapaDeNotas().entrySet()) {
                            // Verifica se a nota pertence ao aluno
                            if (aluno.getMatricula().toString().equals(entry.getKey())) {
                                // Verifica se a avaliação é do tipo "N"
                                if (avaliacao.getTipoDaAvaliacao().equals("N") || avaliacao.getTipoDaAvaliacao().equals("T")) {
                                    somaDePeso += avaliacao.getPesoDaAvaliacao();
                                    notaTotal += entry.getValue().getValor() * avaliacao.getPesoDaAvaliacao();
                                }
                                // Verifica se a avaliação é do tipo "F"
                                else if (avaliacao.getTipoDaAvaliacao().equals("F")) {
                                    notaProvaFinal = entry.getValue().getValor();
                                }
                            }
                        }
                    }
                }

                // Calcula a média parcial do aluno na disciplina
                Double mediaParcial = notaTotal / somaDePeso;
    
                // Verifica se o aluno precisa fazer prova final ou não
                if (mediaParcial < 7.0) {
                    mediaDoAluno = (notaProvaFinal + mediaParcial) / 2; // Calcula a média final
                } else if (mediaParcial >= 7.0) {
                    mediaDoAluno = mediaParcial; // A média final é igual à média parcial
                }
    
                // Acumula a média do aluno à soma das médias de todos os alunos da disciplina
                somaDasMediasDosAlunos = somaDasMediasDosAlunos + mediaDoAluno;
            }
        }

      // Calcula a média da disciplina, dividindo a soma das médias dos alunos pelo total de alunos na disciplina
      mediaDoCursoNaDisciplina = somaDasMediasDosAlunos / qtdAlunosDisciplinaCurso;
  
      // Retorna a média da disciplina
      return mediaDoCursoNaDisciplina;
    }
  
}