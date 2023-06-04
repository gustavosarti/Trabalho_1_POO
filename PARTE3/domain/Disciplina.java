package domain;

import java.util.Map;
import java.util.HashMap;
import java.io.Serializable;

public class Disciplina implements Serializable {

  private String codigoDaDisciplina;
  private String nomeDaDisciplina;

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

  private Map<Integer, Aluno> mapaDeAlunos = new HashMap<>();

  public void adicionaAluno(Integer codigoDoAluno, Aluno aluno) {
    // Método para adicionar um aluno ao mapa de alunos da disciplina
    mapaDeAlunos.put(codigoDoAluno, aluno);
  }

  public Map<Integer, Aluno> retornaMapaDeAlunos() {
    // Método para retornar o mapa de alunos da disciplina
    return mapaDeAlunos;
  }

  private Map<Integer, AlunoGraduacao> mapaDeAlunosGraduacao = new HashMap<>();

  public void adicionaAluno(Integer codigoDoAluno, AlunoGraduacao aluno) {
    // Método para adicionar um aluno ao mapa de alunos da disciplina
    mapaDeAlunos.put(codigoDoAluno, aluno);
  }

  public Map<Integer, AlunoGraduacao> retornaMapaDeAlunosGraduacao() {
    // Método para retornar o mapa de alunos da disciplina
    return mapaDeAlunosGraduacao;
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
}
