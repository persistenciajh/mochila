/**
 * 
 */
package com.mochila.framework.dao;

import java.util.Collection;

@SuppressWarnings("unchecked")
public interface IDaoRepo {

  /**
   * @type Método de la clase IDao
   * @name obtenerListado
   * @return Collection
   * @param objeto
   * @desc Este método es la plantilla para traer un listado de objetos producto de una consulta
   */
  abstract Collection obtenerListado(String sqlName) throws Exception;

  abstract Collection obtenerListado(String sqlName, Object object) throws Exception;

  /**
   * @type Método de la clase IDao
   * @name obtenerRegistro
   * @return Object
   * @param objeto
   * @desc
   */
  abstract Object obtenerRegistro(String sqlName) throws Exception;

  /**
   * @type Método de la clase IDao
   * @name obtenerRegistro
   * @return Object
   * @param objeto
   * @desc
   */
  abstract Object obtenerRegistro(String sqlName, Object objeto) throws Exception;

  /**
   * @type Método de la clase IDao
   * @name insertarRegistro
   * @return Object
   * @param objeto
   * @desc Este método es la plantilla para insertar registros a la base de datos
   */
  abstract Object insertarRegistro(String sqlName, Object objeto) throws Exception;

  /**
   * @type Método de la clase IDao
   * @name actualizarRegistro
   * @return Object
   * @param objeto
   * @throws Exception
   * @desc Este método es la plantilla para actualizar registros a la base de datos
   */
  abstract Object actualizarRegistro(String sqlName, Object objeto) throws Exception;

  /**
   * @type Método de la clase IDao
   * @name borrarRegistro
   * @return Object
   * @param objeto
   * @throws Exception
   * @desc Este método es la plantilla para borrar registros a la base de datos
   */
  abstract Object borrarRegistro(String sqlName, Object objeto) throws Exception;

  abstract Object validarSQL(String sql) throws Exception;

}
