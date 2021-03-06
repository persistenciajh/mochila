package com.mochila.framework.facade;

/**
 * ReportesFacade.java
 */

import java.sql.Connection;

import com.mochila.framework.manager.ConexionReporteMgr;

/**
 * @author Marlene Rojas
 * @date 22/01/2009
 * @name ReportesFacade.java
 * @desc --
 */
public class ReportesFacade {

  /** desc: Esta clase es singlenton */
  private static final ReportesFacade pReportesFacade = new ReportesFacade();

  /**
   * @type Constructor de la clase ReportesFacade
   * @name LogginFacade
   * @return void
   * @desc Crea una instancia de ReportesFacade
   */
  private ReportesFacade() {
    super();
  }

  /**
   * @type Método de la clase ReportesFacade
   * @name getFacade
   * @return LogginFacade
   * @desc permite obtener la instancia del objeto ReportesFacade (singlenton)
   */
  public static ReportesFacade getFacade() {
    return pReportesFacade;
  }

  /****************************************************************************************/
  /** METODOS DE LA FACHADA */
  /****************************************************************************************/

  /**
   * @type Método de la clase ReportesFacade
   * @name obtenerConexionReporte
   * @return Connection
   * @throws Exception
   * @desc Conexi�n necesaria para realizar al ejecuci�n de los reportes.
   */
  public Connection obtenerConexionReporte() throws Exception {
    ConexionReporteMgr mConexion = new ConexionReporteMgr();
    return mConexion.obtenerConexionReporte();
  }
}
