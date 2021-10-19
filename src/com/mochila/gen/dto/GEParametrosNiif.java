package com.mochila.gen.dto;

public class GEParametrosNiif {
  private String NameParametro;
  private String Descripcion;
  private String Requerido;

  /**
   * @return the descripcion
   */
  public String getDescripcion() {
    return Descripcion;
  }

  /**
   * @param descripcion the descripcion to set
   */
  public void setDescripcion(String descripcion) {
    Descripcion = descripcion;
  }

  /**
   * @return the nameParametro
   */
  public String getNameParametro() {
    return NameParametro;
  }

  /**
   * @param nameParametro the nameParametro to set
   */
  public void setNameParametro(String nameParametro) {
    NameParametro = nameParametro;
  }

  /**
   * @return the requerido
   */
  public String getRequerido() {
    return Requerido;
  }

  /**
   * @param requerido the requerido to set
   */
  public void setRequerido(String requerido) {
    Requerido = requerido;
  }

}
