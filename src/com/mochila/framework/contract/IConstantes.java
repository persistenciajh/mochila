package com.mochila.framework.contract;

public interface IConstantes {

	 public static final int ESTADO_INSERCION_OK = 1;
	  public static final int ESTADO_INSERCION_ERROR = 2;

	  public static final int ESTADO_EDICION_OK = 3;
	  public static final int ESTADO_EDICION_ERROR = 4;

	  public static final int ESTADO_BORRAR_OK = 5;
	  public static final int ESTADO_BORRAR_ERROR = 6;

	  public static final int ESTADO_DEFAULT_OK = 7;
	  public static final int ESTADO_DEFAULT_ERROR = 8;
	  public static final int ESTADO_ADVERTENCIA = 9;
	  
	  public static final String ESTADO_IMAGEN_OK = "/imagenes/estado_ok.gif"; // TODO cambiar iconos
	  public static final String ESTADO_IMAGEN_ERROR = "/imagenes/estado_error.gif";
	  public static final String IMAGEN_INFORMATION = "/imagenes/excepcionalert.gif";
	  public static final String ESTADO_IMAGEN_ADVERT = "/imagenes/adverten_important.png";
	  
	  // - se definen los tipos de formatos de exportación de los reportes
	  public static final String REP_FORMATO_SALIDA_RTF = "rtf";
	  public static final String REP_FORMATO_SALIDA_PDF = "pdf";
	  public static final String REP_FORMATO_SALIDA_XLS = "xls";
	  public static final String REP_FORMATO_SALIDA_HTML = "html";
	  public static final String REP_FORMATO_SALIDA_IMAGEN = "png";
	  public static final String REP_FORMATO_SALIDA_INVALIDO = "XXX";

	  // - se definen las extensiones de los archivos generados
	  public static final String REP_EXTENSION_ARCHIVO_RTF = "rtf";
	  public static final String REP_EXTENSION_ARCHIVO_PDF = "pdf";
	  public static final String REP_EXTENSION_ARCHIVO_XLS = "xls";
	  public static final String REP_EXTENSION_ARCHIVO_HTML = "html";
	  public static final String REP_EXTENSION_ARCHIVO_IMAGEN = "png";
	  
	  /* constantes para definir la ruta de la ventana donde se previsualiz el correo */
	  public static final String PANTALLA_REPORTES_NUEVO = "/WEB-INF/components/previsualizadorReportes.zul";
	
	final Boolean CONSTRUIR_OPCIONES_MENU = false ;
	public static final String ID_MENU = "ID_MENU_";
	
}
