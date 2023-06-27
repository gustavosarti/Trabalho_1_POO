package br.ufes.informatica.trabpoo.domain;

import java.util.Map;
import java.util.HashMap;
import java.io.Serializable;

public class Disciplina implements Serializable, Comparable<Disciplina> {

  private String codigoDaDisciplina; //Armazena o código da disciplina
  private String nomeDaDisciplina; //Armazena o nome da disciplina

  public Disciplina(String codigoDaDisciplina, String nomeDaDisciplina) {
    // Construtor da classe Disciplina que recebe o código e o nome da disciplina
    this.codigoDaDisciplina = codigoDaDisciplina;
    this.nomeDaDisciplina = nomeDaDisciplina;
  }

  public String getCodigoDaDisciplina() {
    // Método para obter o código da disciplina
    return codigoDaDisciplina;
  }

  public String getNomeDaDisciplina() {
    // Método para obter o nome da disciplina
    return nomeDaDisciplina;
  }

  private Map<String, Avaliacao> mapaDeAvaliacoes = new HashMap<>();

  public void adicionaAvaliacao(String codigoDaAvaliacao, Avaliacao avaliacao) {
    // Método para adicionar uma avaliação ao mapa de avaliações da disciplina
    mapaDeAvaliacoes.put(codigoDaAvaliacao, avaliacao);
  }

  public Map<String, Avaliacao> retornaMapaDeAvaliacoes() {
    // Método para retornar o mapa de avaliações da disciplina
    return mapaDeAvaliacoes;
  }

  private Map<String, Aluno> mapaDeAlunos = new HashMap<>();

  public void adicionaAluno(String codigoDoAluno, Aluno aluno) {
    // Método para adicionar um aluno ao mapa de alunos da disciplina
    mapaDeAlunos.put(codigoDoAluno, aluno);
  }

  public Map<String, Aluno> retornaMapaDeAlunos() {
    // Método para retornar o mapa de alunos da disciplina
    return mapaDeAlunos;
  }

  private Map<String, Curso> mapaDeCursos = new HashMap<>();

  public void adicionaCurso(String codigoDoCurso, Curso curso) {
    // Método para adicionar um curso ao mapa de cursos da disciplina
    mapaDeCursos.put(codigoDoCurso, curso);
  }

  public Map<String, Curso> retornaMapaDeCursos() {
    // Método para retornar o mapa de cursos da disciplina
    return mapaDeCursos;
  }

  @Override
  public int compareTo(Disciplina disciplina) {
    // Método para comparar dois objetos da classe CursoComMedia com base no código da disciplina
      
    return this.getCodigoDaDisciplina().compareTo(disciplina.getCodigoDaDisciplina());
    // Compara o código da disciplina do objeto atual com o código da disciplina do objeto passado como parâmetro
    // Retorna um valor negativo se o código do objeto atual for menor, um valor positivo se for maior ou zero se forem iguais
  }

}
