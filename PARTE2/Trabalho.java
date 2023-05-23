import java.time.LocalDate;

class Trabalho extends Prova {

  private Integer quantidadeDeAlunos;

  public Trabalho(Disciplina x, String y, String z, Double a, LocalDate b, Integer c, String d) {
    super(x, y, z, a, b, d);
    quantidadeDeAlunos = c;
  }

  public Integer getQuantidadeDeAlunos() {
    return quantidadeDeAlunos;
  }

}
