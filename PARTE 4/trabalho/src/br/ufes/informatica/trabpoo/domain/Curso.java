package br.ufes.informatica.trabpoo.domain;

import java.util.Map;
import java.util.HashMap;
import java.io.Serializable;

public class Curso implements Serializable {

  private Integer codigoDoCurso;  // Armazena o código do curso
  private String nomeDoCurso;  // Armazena o nome do curso

  public Curso(Integer codigoDoCurso, String nomeDoCurso) {
    // Construtor da classe Curso que recebe o código e o nome do curso
    this.codigoDoCurso = codigoDoCurso;
    this.nomeDoCurso = nomeDoCurso;
  }

  public Curso(String nomeDoCurso) {
    // Construtor da classe Curso que recebe o nome do curso
    this.nomeDoCurso = nomeDoCurso;
  }

  public Integer getCodigoDoCurso() {
    // Método para obter o código do curso
    return codigoDoCurso;
  }

  public String getNomeDoCurso() {
    // Método para obter o nome do curso
    return nomeDoCurso;
  }

  private Map<Integer, Aluno> mapaDeAlunos = new HashMap<>();  // Mapa para armazenar os alunos do curso, usando o código do aluno como chave

  public void adicionaAluno(Integer codigoDoAluno, Aluno aluno) {
    // Método para adicionar um aluno ao mapa de alunos do curso
    mapaDeAlunos.put(codigoDoAluno, aluno);
  }

  public Map<Integer, Aluno> retornaMapaDeAlunos() {
    // Método para retornar o mapa de alunos do curso
    return mapaDeAlunos;
  }

  private Map<String, Disciplina> mapaDeDisciplinas = new HashMap<>();

  public void adicionaDisciplina(String codigoDaDisciplina, Disciplina disciplina) {
    // Método para adicionar uma disciplina ao mapa de disciplinas do curso
    mapaDeDisciplinas.put(codigoDaDisciplina, disciplina);
  }

  public Map<String, Disciplina> retornaMapaDeDisciplinas() {
    // Método para retornar o mapa de disciplinas do curso
    return mapaDeDisciplinas;
  }

}

