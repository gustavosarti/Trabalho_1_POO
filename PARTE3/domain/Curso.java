package domain;

import java.util.Map;
import java.util.HashMap;
import java.io.Serializable;

public class Curso implements Serializable {

  private String codigoDoCurso;
  private String nomeDoCurso;

  public Curso(String codigoDoCurso, String nomeDoCurso) {
    // Construtor da classe Curso que recebe o código e o nome do curso
    this.codigoDoCurso = codigoDoCurso;
    this.nomeDoCurso = nomeDoCurso;
  }

  public String getCodigoDoCurso() {
    // Método para obter o código do curso
    return codigoDoCurso;
  }

  public String getNomeDoCurso() {
    // Método para obter o nome do curso
    return nomeDoCurso;
  }

  private Map<Integer, Aluno> mapaDeAlunos = new HashMap<>();

  public void adicionaAluno(Integer codigoDoAluno, Aluno aluno) {
    // Método para adicionar um aluno ao mapa de alunos do curso
    mapaDeAlunos.put(codigoDoAluno, aluno);
  }

  public Map<Integer, Aluno> retornaMapaDeAlunos() {
    // Método para retornar o mapa de alunos do curso
    return mapaDeAlunos;
  }

}
