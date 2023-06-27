package br.ufes.informatica.trabpoo.IO;

import br.ufes.informatica.trabpoo.domain.Curso;
import br.ufes.informatica.trabpoo.domain.Disciplina;
import br.ufes.informatica.trabpoo.domain.Avaliacao;
import br.ufes.informatica.trabpoo.domain.Aluno;
import br.ufes.informatica.trabpoo.util.Funcao;
import br.ufes.informatica.trabpoo.domain.Prova;
import br.ufes.informatica.trabpoo.domain.Trabalho;
import br.ufes.informatica.trabpoo.domain.ProvaFinal;
import br.ufes.informatica.trabpoo.domain.AlunoGraduacao;
import br.ufes.informatica.trabpoo.domain.AlunoPos;
import br.ufes.informatica.trabpoo.domain.Nota;

import br.ufes.informatica.trabpoo.IO.Entrada;

import java.util.Map;
import java.util.HashMap;

import java.time.LocalDate;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InputMismatchException;
import java.time.format.DateTimeParseException;

public class Entrada {
    // Mapas estáticos para armazenar os objetos do sistema
    private static Map<String, Curso> mapaDeCursos = new HashMap<>();
    private static Map<String, Disciplina> mapaDeDisciplinas = new HashMap<>();
    private static Map<String, Avaliacao> mapaDeAvaliacoes = new HashMap<>();
    private static Map<String, Aluno> mapaDeAlunos = new HashMap<>();

    // Método para definir o mapa de cursos
    public static void setMapaDeCursos(Map<String, Curso> mapaDeCursos) {
        // Atualiza o mapa de cursos com o mapa fornecido
        Entrada.mapaDeCursos = mapaDeCursos;
    }

    // Método para definir o mapa de disciplinas
    public static void setMapaDeDisciplinas(Map<String, Disciplina> mapaDeDisciplinas) {
        // Atualiza o mapa de disciplinas com o mapa fornecido
        Entrada.mapaDeDisciplinas = mapaDeDisciplinas;
    }

    // Método para definir o mapa de avaliações
    public static void setMapaDeAvaliacoes(Map<String, Avaliacao> mapaDeAvaliacoes) {
        // Atualiza o mapa de avaliações com o mapa fornecido
        Entrada.mapaDeAvaliacoes = mapaDeAvaliacoes;
    }

    // Método para definir o mapa de alunos
    public static void setMapaDeAlunos(Map<String, Aluno> mapaDeAlunos) {
        // Atualiza o mapa de alunos com o mapa fornecido
        Entrada.mapaDeAlunos = mapaDeAlunos;
    }

    // Método para retornar o mapa de cursos
    public static Map<String, Curso> retornaMapaDeCursos() {
        // Retorna o mapa de cursos
        return mapaDeCursos;
    }

    // Método para retornar o mapa de disciplinas
    public static Map<String, Disciplina> retornaMapaDeDisciplinas() {
        // Retorna o mapa de disciplinas
        return mapaDeDisciplinas;
    }

    // Método para retornar o mapa de avaliações
    public static Map<String, Avaliacao> retornaMapaDeAvaliacoes() {
        // Retorna o mapa de avaliações
        return mapaDeAvaliacoes;

    }

    // Método para retornar o mapa de alunos
    public static Map<String, Aluno> retornaMapaDeAlunos() {
        // Retorna o mapa de alunos
        return mapaDeAlunos;

    }

    public static void salvarCursos(String nomeDoArquivo)
            throws FileNotFoundException, IOException, InputMismatchException, Exception {
        // Abre o arquivo de entrada
        FileInputStream fluxoDeEntrada = new FileInputStream(nomeDoArquivo);

        // Cria um leitor para ler o arquivo
        InputStreamReader isr = new InputStreamReader(fluxoDeEntrada);
        BufferedReader conteudoCSV = new BufferedReader(isr);

        String linha = "";
        String csvSeparadorDeCampo = ";";

        // Lê a primeira linha do arquivo (cabeçalho)
        linha = conteudoCSV.readLine();

        // Itera sobre as linhas restantes do arquivo
        while ((linha = conteudoCSV.readLine()) != null) {

            linha = linha.replaceAll(" ;", ";");
            // Divide a linha em campos usando o separador ","
            String[] informacaoDoCurso = linha.split(csvSeparadorDeCampo);

            // try catch para verificar erro de formatação
            try {

                Integer.parseInt(informacaoDoCurso[0]);

            } catch (NumberFormatException e) {

                conteudoCSV.close();

                throw new Exception("Erro de formatação.");

            }

            // Obtém o código do curso e o converte para Integer
            Integer codigoDoCurso = Integer.parseInt(informacaoDoCurso[0]);

            // Obtém o nome do curso
            String nomeDoCurso = informacaoDoCurso[1];

            // Verifica se o código do curso já existe no mapa de cursos
            if (mapaDeCursos.containsKey(Integer.toString(codigoDoCurso))) {
                // Fecha o arquivo
                conteudoCSV.close();

                // Lança uma exceção informando que o código do curso está repetido
                throw new Exception("Código repetido para curso: " + codigoDoCurso + ".");
            } else {
                // Cria um novo objeto Curso com o código e o nome do curso
                Curso curso = new Curso(codigoDoCurso, nomeDoCurso);

                // Adiciona o curso ao mapa de cursos usando o código como chave
                mapaDeCursos.put(Integer.toString(codigoDoCurso), curso);
            }
        }

        // Fecha o arquivo
        conteudoCSV.close();
    }

    public static void salvarDisciplinas(String nomeDoArquivo)
            throws FileNotFoundException, IOException, InputMismatchException, Exception {
        // Abre o arquivo de entrada
        FileInputStream fluxoDeEntrada = new FileInputStream(nomeDoArquivo);

        // Cria um leitor para ler o arquivo
        InputStreamReader isr = new InputStreamReader(fluxoDeEntrada);
        BufferedReader conteudoCSV = new BufferedReader(isr);

        String linha = "";
        String csvSeparadorDeCampo = ";";

        // Lê a primeira linha do arquivo (cabeçalho)
        linha = conteudoCSV.readLine();

        // Itera sobre as linhas restantes do arquivo
        while ((linha = conteudoCSV.readLine()) != null) {

            linha = linha.replaceAll(" ;", ";");

            // Divide a linha em campos usando o separador ","
            String[] informacaoDaDisciplina = linha.split(csvSeparadorDeCampo);

            // Obtém o código da disciplina
            String codigoDaDisciplina = informacaoDaDisciplina[0];

            // Obtém o nome da disciplina
            String nomeDaDisciplina = informacaoDaDisciplina[1];

            // Verifica se o código da disciplina já existe no mapa de disciplinas
            if (mapaDeDisciplinas.containsKey(codigoDaDisciplina)) {
                // Fecha o arquivo
                conteudoCSV.close();

                // Lança uma exceção informando que o código da disciplina está repetido
                throw new Exception("Codigo repetido para disciplina: " + codigoDaDisciplina + ".");
            } else {
                // Cria um novo objeto Disciplina com o código e o nome da disciplina
                Disciplina disciplina = new Disciplina(codigoDaDisciplina, nomeDaDisciplina);

                // Adiciona a disciplina ao mapa de disciplinas usando o código como chave
                mapaDeDisciplinas.put(codigoDaDisciplina, disciplina);
            }
        }

        // Fecha o arquivo
        conteudoCSV.close();
    }

    public static void salvarAvaliacoes(String nomeDoArquivo)
            throws FileNotFoundException, IOException, InputMismatchException, Exception, DateTimeParseException {
        // Abre o arquivo de entrada
        FileInputStream fluxoDeEntrada = new FileInputStream(nomeDoArquivo);

        // Cria um leitor para ler o arquivo
        InputStreamReader isr = new InputStreamReader(fluxoDeEntrada);
        BufferedReader conteudoCSV = new BufferedReader(isr);

        String linha = "";
        String csvSeparadorDeCampo = ";";

        // Lê a primeira linha do arquivo (cabeçalho)
        linha = conteudoCSV.readLine();

        // Itera sobre as linhas restantes do arquivo
        while ((linha = conteudoCSV.readLine()) != null) {

            linha = linha.replaceAll(" ;", ";");

            // Divide a linha em campos usando o separador ";"
            String[] informacaoDaAvaliacao = linha.split(csvSeparadorDeCampo);

            // Obtém as informações da avaliação
            String codigoDaDisciplina = informacaoDaAvaliacao[0];
            String codigoDaAvaliacao = informacaoDaAvaliacao[1];
            String nomeDaAvaliacao = informacaoDaAvaliacao[2];

            try {

                Double.parseDouble(informacaoDaAvaliacao[3]);

            } catch (NumberFormatException e) {

                conteudoCSV.close();

                throw new Exception("Erro de formatação.");

            }

            Double pesoDaAvaliacao = Double.parseDouble(informacaoDaAvaliacao[3]);
            int pesoDaAvaliacaoINT = Integer.parseInt(informacaoDaAvaliacao[3]);

            // Verifica se o peso da avaliação é válido
            if (pesoDaAvaliacao <= 0) {
                conteudoCSV.close();

                throw new Exception(
                        "Peso de avaliação inválido para " + codigoDaAvaliacao + ": " + pesoDaAvaliacaoINT + ".");
            } else {
                String tipoDaAvaliacao = informacaoDaAvaliacao[4];
                tipoDaAvaliacao = tipoDaAvaliacao.replaceAll(" ", "");
                LocalDate dataDaAvaliacao = Funcao.converteParaData(informacaoDaAvaliacao[5]);
                Integer tamanhoDoGrupoDaAvaliacao = 1;

                if (tipoDaAvaliacao.equalsIgnoreCase("T")) {
                    tamanhoDoGrupoDaAvaliacao = Integer.parseInt(informacaoDaAvaliacao[6]);
                }

                Integer valorDeData = Funcao.geraValorDeData(informacaoDaAvaliacao[5]);

                // Verifica se o código da avaliação já existe no mapa de avaliações
                if (mapaDeAvaliacoes.containsKey(codigoDaAvaliacao)) {
                    conteudoCSV.close();
                    throw new Exception("Codigo repetido para avaliacao: " + codigoDaAvaliacao + ".");
                } else {
                    // Verifica o tipo da avaliação
                    if (tipoDaAvaliacao.equalsIgnoreCase("P")) {
                        // Verifica se o código da disciplina existe no mapa de disciplinas
                        if (mapaDeDisciplinas.containsKey(codigoDaDisciplina)) {
                            // Cria um objeto Prova
                            Prova prova = new Prova(mapaDeDisciplinas.get(codigoDaDisciplina), codigoDaAvaliacao,
                                    nomeDaAvaliacao, pesoDaAvaliacao, dataDaAvaliacao, valorDeData);
                            // Adiciona a prova ao mapa de avaliações
                            mapaDeAvaliacoes.put(codigoDaAvaliacao, prova);
                            // Adiciona a prova à disciplina correspondente
                            mapaDeDisciplinas.get(codigoDaDisciplina).adicionaAvaliacao(codigoDaAvaliacao, prova);
                        } else {
                            conteudoCSV.close();
                            throw new Exception("Código de disciplina não definido usado na avaliação "
                                    + codigoDaAvaliacao + ": " + codigoDaDisciplina + ".");
                        }

                        // Verifica se há um tamanho máximo de grupo especificado (não deveria haver)
                        if (informacaoDaAvaliacao.length == 7) {
                            conteudoCSV.close();
                            tamanhoDoGrupoDaAvaliacao = Integer.parseInt(informacaoDaAvaliacao[6]);
                            throw new Exception("Tamanho máximo de grupo especificado para a prova " + codigoDaAvaliacao
                                    + ": " + tamanhoDoGrupoDaAvaliacao + ".");
                        }
                    } else if (tipoDaAvaliacao.equalsIgnoreCase("T")) {
                        // Verifica se o código da disciplina existe no mapa de disciplinas
                        if (mapaDeDisciplinas.containsKey(codigoDaDisciplina)) {
                            // Verifica se o tamanho do grupo da avaliação é válido
                            if (tamanhoDoGrupoDaAvaliacao <= 0) {
                                conteudoCSV.close();
                                throw new Exception("Tamanho máximo de grupo inválido para trabalho "
                                        + codigoDaAvaliacao + ": " + tamanhoDoGrupoDaAvaliacao + ".");
                            } else {
                                // Cria um objeto Trabalho
                                Trabalho trabalho = new Trabalho(mapaDeDisciplinas.get(codigoDaDisciplina),
                                        codigoDaAvaliacao, nomeDaAvaliacao, pesoDaAvaliacao, dataDaAvaliacao,
                                        valorDeData, tamanhoDoGrupoDaAvaliacao);
                                // Adiciona o trabalho ao mapa de avaliações
                                mapaDeAvaliacoes.put(codigoDaAvaliacao, trabalho);
                                // Adiciona o trabalho à disciplina correspondente
                                mapaDeDisciplinas.get(codigoDaDisciplina).adicionaAvaliacao(codigoDaAvaliacao,
                                        trabalho);
                            }
                        } else {
                            conteudoCSV.close();
                            throw new Exception("Código de disciplina não definido usado na prova " + codigoDaAvaliacao
                                    + ": " + codigoDaDisciplina + ".");
                        }
                    } else if (tipoDaAvaliacao.equalsIgnoreCase("F")) {
                        // Verifica se o código da disciplina existe no mapa de disciplinas
                        if (mapaDeDisciplinas.containsKey(codigoDaDisciplina)) {
                            // Cria um objeto ProvaFinal
                            ProvaFinal provaFinal = new ProvaFinal(mapaDeDisciplinas.get(codigoDaDisciplina),
                                    codigoDaAvaliacao, nomeDaAvaliacao, pesoDaAvaliacao, dataDaAvaliacao, valorDeData,
                                    tipoDaAvaliacao);
                            // Adiciona a prova final ao mapa de avaliações
                            mapaDeAvaliacoes.put(codigoDaAvaliacao, provaFinal);
                            // Adiciona a prova final à disciplina correspondente
                            mapaDeDisciplinas.get(codigoDaDisciplina).adicionaAvaliacao(codigoDaAvaliacao, provaFinal);
                        } else {
                            conteudoCSV.close();
                            throw new Exception("Código de disciplina não definido usado na prova " + codigoDaAvaliacao
                                    + ": " + codigoDaDisciplina + ".");
                        }

                        // Verifica se há um tamanho máximo de grupo especificado (não deveria haver)
                        if (informacaoDaAvaliacao.length == 7) {
                            conteudoCSV.close();
                            tamanhoDoGrupoDaAvaliacao = Integer.parseInt(informacaoDaAvaliacao[6]);
                            throw new Exception("Tamanho máximo de grupo especificado para a prova " + codigoDaAvaliacao
                                    + ": " + tamanhoDoGrupoDaAvaliacao + ".");
                        }
                    } else {
                        conteudoCSV.close();
                        throw new Exception("Tipo de avaliação desconhecido para " + codigoDaAvaliacao + ": "
                                + tipoDaAvaliacao + ".");
                    }
                }
            }
        }

        // Fecha o arquivo de entrada
        conteudoCSV.close();
    }

    public static void salvarAlunos(String nomeDoArquivo)
            throws FileNotFoundException, IOException, InputMismatchException, Exception {

        // Abre o arquivo para leitura
        FileInputStream fluxoDeEntrada = new FileInputStream(nomeDoArquivo);

        // Cria um leitor de caracteres para o arquivo
        InputStreamReader isr = new InputStreamReader(fluxoDeEntrada);

        // Cria um buffer para a leitura do arquivo
        BufferedReader conteudoCSV = new BufferedReader(isr);

        String linha = "";

        String csvSeparadorDeCampo = ";"; // Separador de campos no arquivo CSV

        String DisciplinasSeparadorDeCampo = ","; // Separador de disciplinas no campo

        // Lê a primeira linha (cabeçalho) do arquivo
        linha = conteudoCSV.readLine();

        // Loop para ler cada linha do arquivo
        while ((linha = conteudoCSV.readLine()) != null) {

            // Divide a linha em campos usando o separador definido
            String[] informacaoDoAluno = linha.split(csvSeparadorDeCampo);

            try {

                Integer.parseInt(informacaoDoAluno[0]);

            } catch (NumberFormatException e) {

                conteudoCSV.close();

                throw new Exception("Erro de formatação.");

            }

            // Extrai as informações do aluno de cada campo
            Integer matriculaDoAluno = Integer.parseInt(informacaoDoAluno[0]);
            String nomeDoAluno = informacaoDoAluno[1];
            String disciplinas = informacaoDoAluno[2];
            String tipoDeAluno = informacaoDoAluno[3];
            tipoDeAluno = tipoDeAluno.replaceAll(" ", "");
            String codigoDoCurso = informacaoDoAluno[4];
            codigoDoCurso = codigoDoCurso.replaceAll(" ", "");

            // Verifica se a matrícula do aluno já existe no mapa de alunos
            if (mapaDeAlunos.containsKey(matriculaDoAluno.toString())) {

                conteudoCSV.close();

                // Lança uma exceção indicando matrícula repetida
                throw new Exception("Matrícula repetida para aluno: " + matriculaDoAluno + ".");

            } else {

                // Verifica o tipo de aluno
                if (tipoDeAluno.equalsIgnoreCase("G")) {

                    // Verifica se o código do curso existe no mapa de cursos
                    if (mapaDeCursos.containsKey(codigoDoCurso)) {

                        // Cria um objeto AlunoGraduacao com as informações do aluno
                        AlunoGraduacao alunoGraduacao = new AlunoGraduacao(matriculaDoAluno, nomeDoAluno,
                                mapaDeCursos.get(codigoDoCurso));

                        // Adiciona o aluno ao mapa de alunos
                        mapaDeAlunos.put(matriculaDoAluno.toString(), alunoGraduacao);

                        // Adiciona o aluno ao mapa de alunos do curso correspondente
                        mapaDeCursos.get(codigoDoCurso).adicionaAluno(matriculaDoAluno, alunoGraduacao);

                    } else {

                        conteudoCSV.close();

                        // Lança uma exceção indicando código de curso indefinido
                        throw new Exception("Código de curso não definido usado no aluno " + matriculaDoAluno + ": "
                                + codigoDoCurso + ".");
                    }

                } else if (tipoDeAluno.equalsIgnoreCase("P")) {

                    // Verifica o código do curso para alunos de pós-graduação
                    if (codigoDoCurso.equalsIgnoreCase("M")) {

                        // Cria um objeto AlunoPos para mestrado com as informações do aluno
                        AlunoPos alunoPos = new AlunoPos(matriculaDoAluno, nomeDoAluno, "M");

                        // Adiciona o aluno ao mapa de alunos
                        mapaDeAlunos.put(matriculaDoAluno.toString(), alunoPos);

                        // Verifica se o curso de mestrado já existe no mapa de cursos
                        if (mapaDeCursos.get(codigoDoCurso) == null) {

                            // Cria um objeto Curso para mestrado
                            Curso cursoPos = new Curso("Mestrado");

                            // Adiciona o curso ao mapa de cursos
                            mapaDeCursos.put(codigoDoCurso, cursoPos);

                        }

                        // Adiciona o aluno ao mapa de alunos do curso correspondente
                        mapaDeCursos.get(codigoDoCurso).adicionaAluno(matriculaDoAluno, alunoPos);

                    } else if (codigoDoCurso.equalsIgnoreCase("D")) {

                        // Cria um objeto AlunoPos para doutorado com as informações do aluno
                        AlunoPos alunoPos = new AlunoPos(matriculaDoAluno, nomeDoAluno, "D");

                        // Adiciona o aluno ao mapa de alunos
                        mapaDeAlunos.put(matriculaDoAluno.toString(), alunoPos);

                        // Verifica se o curso de doutorado já existe no mapa de cursos
                        if (mapaDeCursos.get(codigoDoCurso) == null) {

                            // Cria um objeto Curso para doutorado
                            Curso cursoPos = new Curso("Doutorado");

                            // Adiciona o curso ao mapa de cursos
                            mapaDeCursos.put(codigoDoCurso, cursoPos);

                        }

                        // Adiciona o aluno ao mapa de alunos do curso correspondente
                        mapaDeCursos.get(codigoDoCurso).adicionaAluno(matriculaDoAluno, alunoPos);

                    } else {

                        conteudoCSV.close();

                        // Lança uma exceção indicando tipo de aluno de pós-graduação desconhecido
                        throw new Exception("Tipo de aluno de pós-graduação desconhecido para " + matriculaDoAluno
                                + ": " + codigoDoCurso + ".");

                    }

                } else {

                    conteudoCSV.close();

                    // Lança uma exceção indicando tipo de aluno desconhecido
                    throw new Exception(
                            "Tipo de aluno desconhecido para " + matriculaDoAluno + ": " + tipoDeAluno + ".");

                }

                // Remove os espaços em branco das disciplinas
                disciplinas = disciplinas.replace(" ", "");

                // Divide as disciplinas em um array usando o separador definido
                String[] codigosDasDisciplinas = disciplinas.split(DisciplinasSeparadorDeCampo);

                // Loop para percorrer todas as disciplinas do aluno
                for (int i = 0; i < codigosDasDisciplinas.length; i++) {

                    // Verifica se o código da disciplina existe no mapa de disciplinas
                    if (mapaDeDisciplinas.containsKey(codigosDasDisciplinas[i])) {

                        // Adiciona o aluno à disciplina correspondente no mapa de disciplinas
                        mapaDeDisciplinas.get(codigosDasDisciplinas[i]).adicionaAluno(matriculaDoAluno.toString(),
                                mapaDeAlunos.get(matriculaDoAluno.toString()));

                        // Verifica se o curso da disciplina já existe no mapa de cursos da disciplina
                        if (mapaDeDisciplinas.get(codigosDasDisciplinas[i]).retornaMapaDeCursos()
                                .get(codigoDoCurso) == null) {

                            // Adiciona o curso ao mapa de cursos da disciplina
                            mapaDeDisciplinas.get(codigosDasDisciplinas[i]).retornaMapaDeCursos().put(codigoDoCurso,
                                    mapaDeCursos.get(codigoDoCurso));

                        }

                        // Verifica se a disciplina já existe no mapa de disciplinas do curso
                        if (mapaDeCursos.get(codigoDoCurso).retornaMapaDeDisciplinas()
                                .get(codigosDasDisciplinas[i]) == null) {

                            // Adiciona a disciplina ao mapa de disciplinas do curso
                            mapaDeCursos.get(codigoDoCurso).retornaMapaDeDisciplinas().put(codigosDasDisciplinas[i],
                                    mapaDeDisciplinas.get(codigosDasDisciplinas[i]));

                        }

                    } else {

                        conteudoCSV.close();

                        // Lança uma exceção indicando código de disciplina não definido
                        throw new Exception(
                                "Código de disciplina não definido usado " + codigosDasDisciplinas[i] + ".");

                    }

                }

            }

        }

        // Fecha o arquivo
        conteudoCSV.close();

    }

    public static void salvarNotas(String nomeDoArquivo)
            throws FileNotFoundException, IOException, InputMismatchException, Exception {

        // Abre o arquivo para leitura
        FileInputStream fluxoDeEntrada = new FileInputStream(nomeDoArquivo);

        // Cria um InputStreamReader para ler os caracteres do fluxo de entrada
        InputStreamReader isr = new InputStreamReader(fluxoDeEntrada);

        // Cria um BufferedReader para ler as linhas do arquivo
        BufferedReader conteudoCSV = new BufferedReader(isr);

        String linha = "";

        // Define o separador de campo do arquivo CSV
        String csvSeparadorDeCampo = ";";

        // Define o separador de matrículas no arquivo CSV
        String MatriculasSeparadorDeCampo = ",";

        // Lê a primeira linha do arquivo (cabeçalho) e descarta
        linha = conteudoCSV.readLine();

        // Lê as linhas restantes do arquivo
        while ((linha = conteudoCSV.readLine()) != null) {

            linha = linha.replaceAll(" ;", ";");

            // Divide a linha em campos separados pelo separador de campo definido
            String[] informacaoDaNota = linha.split(csvSeparadorDeCampo);

            // Obtém o código da avaliação
            String codigoDaAvaliacao = informacaoDaNota[0];

            // Obtém as matrículas dos alunos
            String matriculas = informacaoDaNota[1];

            informacaoDaNota[2] = informacaoDaNota[2].replaceAll(",", ".");

            // Verifica se o código da avaliação está presente no mapa de avaliações
            if (mapaDeAvaliacoes.containsKey(codigoDaAvaliacao)) {

                try {

                    Double.parseDouble(informacaoDaNota[2]);

                } catch (NumberFormatException e) {

                    conteudoCSV.close();

                    throw new Exception("Nota inválida para avaliação " + codigoDaAvaliacao + " do(s) aluno(s) "
                            + matriculas + ": " + informacaoDaNota[2] + ".");

                }

                Double notaDoAluno = Double.parseDouble(informacaoDaNota[2]);

                // Verifica se a nota do aluno é inválida (fora do intervalo de 0 a 10)
                if (notaDoAluno > 10 || notaDoAluno < 0) {

                    conteudoCSV.close();
                    String notaDoAlunoFormatada = String.format("%.1f", notaDoAluno);
                    notaDoAlunoFormatada = notaDoAlunoFormatada.replace(".", ",");

                    // Lança uma exceção informando a nota inválida
                    throw new Exception("Nota inválida para avaliação " + codigoDaAvaliacao + " do(s) aluno(s) "
                            + matriculas + ": " + notaDoAlunoFormatada + ".");

                } else {

                    // Cria um objeto Nota com a nota do aluno
                    Nota nota = new Nota(notaDoAluno);

                    // Remove espaços em branco das matrículas
                    matriculas = matriculas.replaceAll(" ", "");

                    // Divide as matrículas dos alunos separadas pelo separador de matrículas
                    String[] matriculaDosAlunos = matriculas.split(MatriculasSeparadorDeCampo);

                    // Itera sobre as matrículas dos alunos
                    for (int i = 0; i < matriculaDosAlunos.length; i++) {

                        // Obtém a matrícula do aluno
                        String matriculaDoAluno = matriculaDosAlunos[i];

                        try {

                            Long.parseLong(matriculaDoAluno);

                        } catch (NumberFormatException e) {

                            conteudoCSV.close();

                            throw new Exception("Erro de formatação.");

                        }

                        // Verifica se a matrícula do aluno está presente no mapa de alunos
                        if (mapaDeAlunos.containsKey(matriculaDoAluno)) {

                            // Verifica se o aluno está matriculado na disciplina da avaliação
                            if (mapaDeAvaliacoes.get(codigoDaAvaliacao).getDisciplinaDaAvaliacao().retornaMapaDeAlunos()
                                    .get(matriculaDoAluno) == null) {

                                conteudoCSV.close();

                                // Lança uma exceção informando que o aluno não está matriculado na disciplina
                                throw new Exception("O aluno " + matriculaDoAluno + " possui nota na avaliação "
                                        + codigoDaAvaliacao + " da disciplina "
                                        + mapaDeAvaliacoes.get(codigoDaAvaliacao).getDisciplinaDaAvaliacao()
                                                .getCodigoDaDisciplina()
                                        + ", porém não encontra-se matriculado nesta disciplina.");

                            }

                            // Verifica se o aluno já possui nota registrada para a avaliação
                            if (mapaDeAvaliacoes.get(codigoDaAvaliacao).retornaMapaDeNotas()
                                    .get(matriculaDoAluno) != null) {

                                conteudoCSV.close();

                                // Lança uma exceção informando que o aluno foi registrado em mais de um grupo
                                // para a avaliação
                                throw new Exception("O aluno " + matriculaDoAluno
                                        + " foi registrado em mais de um grupo para a avaliação " + codigoDaAvaliacao
                                        + ".");

                            }

                            // Verifica o tipo da avaliação
                            if (mapaDeAvaliacoes.get(codigoDaAvaliacao).getTipoDaAvaliacao().equals("T")) {

                                // Adiciona a nota do aluno para a avaliação
                                mapaDeAvaliacoes.get(codigoDaAvaliacao).adicionaNota(matriculaDoAluno, nota);

                                // Calcula o peso do trabalho
                                double pesoTrabalho = (mapaDeAvaliacoes.get(codigoDaAvaliacao).getPesoDaAvaliacao())
                                        / (matriculaDosAlunos.length);

                                // Adiciona a nota do aluno à média da avaliação com o peso do trabalho
                                mapaDeAvaliacoes.get(codigoDaAvaliacao).adicionaNaMedia(notaDoAluno, pesoTrabalho);

                                // Adiciona a avaliação ao aluno
                                mapaDeAlunos.get(matriculaDoAluno).adicionaAvaliacao(codigoDaAvaliacao,
                                        mapaDeAvaliacoes.get(codigoDaAvaliacao));

                            } else {
                                // Adiciona a nota do aluno para a avaliação
                                mapaDeAvaliacoes.get(codigoDaAvaliacao).adicionaNota(matriculaDoAluno, nota);

                                // Adiciona a nota do aluno à média da avaliação com o peso da avaliação
                                mapaDeAvaliacoes.get(codigoDaAvaliacao).adicionaNaMedia(notaDoAluno,
                                        mapaDeAvaliacoes.get(codigoDaAvaliacao).getPesoDaAvaliacao());

                                // Adiciona a avaliação ao aluno
                                mapaDeAlunos.get(matriculaDoAluno).adicionaAvaliacao(codigoDaAvaliacao,
                                        mapaDeAvaliacoes.get(codigoDaAvaliacao));
                            }

                        } else {

                            conteudoCSV.close();

                            // Lança uma exceção informando que a matrícula do aluno não está definida
                            throw new Exception(
                                    "Matrícula de aluno não definida usada na planilha de notas, associada à avaliação "
                                            + codigoDaAvaliacao + ": " + matriculaDoAluno + ".");

                        }

                    }

                    // Obtém a quantidade máxima de alunos para a avaliação
                    Integer quantidadeDeAlunosMaxima = mapaDeAvaliacoes.get(codigoDaAvaliacao)
                            .retornaQuantidadeDeAlunos();

                    // Verifica se a quantidade de alunos na planilha é maior do que a quantidade
                    // máxima permitida
                    if (matriculaDosAlunos.length > quantidadeDeAlunosMaxima) {

                        conteudoCSV.close();

                        // Lança uma exceção informando que a quantidade de alunos é inválida
                        throw new Exception("Quantidade de alunos inválida para " + codigoDaAvaliacao
                                + ", quantidade máxima: " + quantidadeDeAlunosMaxima + ".");

                    }

                }

            } else {

                conteudoCSV.close();

                // Lança uma exceção informando que o código da avaliação não está definido
                throw new Exception(
                        "Código de avaliação não definido usado na planilha de notas, associado ao(s) aluno(s) "
                                + matriculas + ": " + codigoDaAvaliacao + ".");

            }

        }

        // Fecha o BufferedReader
        conteudoCSV.close();

    }
}
