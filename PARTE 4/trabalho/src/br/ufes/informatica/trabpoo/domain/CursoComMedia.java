package br.ufes.informatica.trabpoo.domain;

import java.util.Comparator;

public class CursoComMedia implements Comparable<CursoComMedia> {

  private String nomeDoCurso;
  private Double media;  

  public CursoComMedia(String nomeDoCurso, Double media) {
    // Construtor da classe CursoComMedia que recebe o nome do curso e a média como parâmetros
    this.nomeDoCurso = nomeDoCurso;
    this.media = media;
  }

  public String getNomeDoCurso() {
    // Método para obter o nome do curso
    return nomeDoCurso;
  }

  public Double getMedia() {
    // Método para obter a média
    return media;
  }

  @Override
  public int compareTo(CursoComMedia cursoComMedia){
    // Método para comparar dois objetos da classe CursoComMedia com base no nome do curso
    return this.getNomeDoCurso().compareTo(cursoComMedia.getNomeDoCurso());
  }

  public static class ComparadorMedia implements Comparator<CursoComMedia> {
    // Classe interna estática que implementa a interface Comparator para comparar dois objetos da classe CursoComMedia com base na média

    public int compare(CursoComMedia c1, CursoComMedia c2){
      // Método para comparar dois objetos da classe CursoComMedia com base na média

      if(c1.getMedia() < c2.getMedia()){
        // Se a média do primeiro objeto for menor que a média do segundo objeto, retorna 1
        return 1;
      }
      if(c1.getMedia() > c2.getMedia()){
        // Se a média do primeiro objeto for maior que a média do segundo objeto, retorna -1
        return -1;
      }

      // Se as médias forem iguais, retorna 0
      return 0;  
    }    
  }
}