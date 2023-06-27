package br.ufes.informatica.trabpoo.ui;

import br.ufes.informatica.trabpoo.IO.Entrada;
import br.ufes.informatica.trabpoo.IO.Saida;
import br.ufes.informatica.trabpoo.util.Funcao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InputMismatchException;
import java.time.format.DateTimeParseException;

public class Main {

    public static void main(String[] args) {
        try {
            
            boolean readOnly = false;
            boolean writeOnly = false;

            // Verifica os argumentos passados para o programa
            for (int i = 0; i < args.length; i++) {
                if (args[i].equals("-c")) {
                    // Salva os cursos a partir do arquivo especificado
                    Entrada.salvarCursos(args[i + 1]);

                } else if (args[i].equals("-d")) {
                    // Salva as disciplinas a partir do arquivo especificado
                    Entrada.salvarDisciplinas(args[i + 1]);

                } else if (args[i].equals("-p")) {
                    // Salva as avaliações a partir do arquivo especificado
                    Entrada.salvarAvaliacoes(args[i + 1]);

                } else if (args[i].equals("-a")) {
                    // Salva os alunos a partir do arquivo especificado
                    Entrada.salvarAlunos(args[i + 1]);

                } else if (args[i].equals("-n")) {
                    // Salva as notas a partir do arquivo especificado
                    Entrada.salvarNotas(args[i + 1]);
                    
                } else if (args[i].equals("--read-only")) {
                    // Define o modo somente leitura
                    readOnly = true;

                } else if (args[i].equals("--write-only")) {
                    // Define o modo somente escrita
                    writeOnly = true;
                    
                }
            }

            if (readOnly) {
                // Modo somente leitura
                Funcao.salvar();
                //Funcao.carregar();

            } else if (writeOnly) {
                // Modo somente escrita
                Funcao.carregar();
                //Funcao.salvar();
                Saida.imprimeRelatorios();

            } else {
                // Modo padrão
                Saida.imprimeRelatorios();
                
            }

        } catch (FileNotFoundException e) {
            // Trata exceção de arquivo não encontrado
            System.out.println("Erro de I/O.");
    
        } catch (IOException e) {
            // Trata exceção de I/O
            System.out.println("Erro de I/O.");
    
        } catch (InputMismatchException e) {
            // Trata exceção de entrada inválida
            System.out.println("Erro de formatação.");
    
        } catch (DateTimeParseException e) {
            // Trata exceção de formatação de data inválida
            System.out.println("Erro de formatação.");
    
        } catch (Exception e) {
            // Trata exceção genérica
            System.out.println(e.getMessage());
    
        } 
      
    }
}

