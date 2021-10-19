package com.mochila.gen.action;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.jar.JarException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Font;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.ext.AfterCompose;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Filedownload;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Row;
import org.zkoss.zul.Rows;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;



import com.mochila.gen.assembler.GEReportesNiifAssembler;
import com.mochila.gen.dto.Cargue;
import com.mochila.framework.util.LegacyJasperInputStream;

import com.mochila.framework.contract.IConstantes;

import com.mochila.framework.facade.ReportesFacade;

import com.mochila.framework.facade.ParametrizacionFac;
import com.mochila.framework.helper.UtilZKProcesosComunesHelper;
import com.mochila.gen.dto.GEParametrosNiif;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;





public class GEZReportesNiif  extends Window implements AfterCompose {
	protected static Logger log = Logger.getLogger(GEZReportesNiif.class);
	UtilZKProcesosComunesHelper util = new UtilZKProcesosComunesHelper();

//	  private JasperDesign plantilla = null;
//	  private EFTReportePlantilla reportePlantilla = null;

	  private Combobox idCboxPlantillas;
	  private Rows idRwsParaReportes;
	  private List listaDto;
	  private List<GEParametrosNiif> ParametrosPlano;
	  private List<GEParametrosNiif> ParametrosCbox;
	  private List<GEParametrosNiif> ColumnasCbox;
	  private List datos_tabla;
	  private Row idRowCamposRP;
	  private Row idRowParametrosRP;
	  private Listbox idLstBColumnas;
	  private Combobox idCboxParametros;
	  private Grid idGridParametros;
	  private final String separator = System.getProperty("file.separator");
	  private int columcount;
	  private HashMap<String, Object> propiedades;
//	  private GENAutenticacion       usuario;

	@Override
	public void afterCompose() {
		// TODO Auto-generated method stub
		 idCboxPlantillas = (Combobox) this.getFellow("idCboxPlantillas");
		    idLstBColumnas = (Listbox) this.getFellow("idLstBColumnas");
		    idCboxParametros = (Combobox) this.getFellow("idCboxParametros");
		    idRwsParaReportes = (Rows) this.getFellow("idRwsParaReportes");
		    idRowCamposRP = (Row) this.getFellow("idRowCamposRP");
		    idRowParametrosRP = (Row) this.getFellow("idRowParametrosRP");
		    idGridParametros = (Grid) this.getFellow("idGridParametros");
		
	}
	
	  public void onSelectReporte() {
		    log.info("Ejecutando Método [onSelectReporte]");
		    columcount = 0;
		    String nombreReporte = idCboxPlantillas.getSelectedItem().getValue();
		    /*
		     * Se limpia el listado de la sección Parametros Reporte por si el nuevo Item seleccionado no es
		     * un jrxml se puedan cargar solamente el listado de que viene desde Base de Datos
		     */
		    idRwsParaReportes.getChildren().clear();

		    if (nombreReporte != null) {
		      if (nombreReporte.endsWith(".jrxml")) {
		        idRowCamposRP.setVisible(false);
		        idRowParametrosRP.setVisible(false);
//		        ContextoAplicacion pContextoAplicacion = ContextoAplicacion.getInstance();
//		        String reportPath = pContextoAplicacion.getRutaContexto() + "/repositorio/fuentes/"
//		                            + nombreReporte;
		        String reportPath = "C://compilados_jasper/repositorio/fuentes/"
                        + nombreReporte;
		        
		        try {
		          listaDto = GetParametros(reportPath);

		          /*
		           * Se llena el listado de la sección: Parametros Reporte
		           */
		          if (listaDto != null)
		            GEReportesNiifAssembler.crearRowDesdeDTO(listaDto, idRwsParaReportes, false);
		        } catch (Exception e) {
		          log.error(e.getMessage(), e.fillInStackTrace());
//		          super.lanzarExcepcion(new Excepcion(IExcepcion.EXCEPTION_GLOBAL, e));
		        }
		      } else if (nombreReporte.equalsIgnoreCase("S")) {
		        idRowCamposRP.setVisible(false);
		        idRowParametrosRP.setVisible(false);
		      } else {

		        /*
		         * Se selecciona un Item cuyo final no es una extension .jrxml
		         */

		        idRowCamposRP.setVisible(true);
		        idRowParametrosRP.setVisible(true);
		        // En caso de Ser report Query para ser exportado a Excel

		        try {
		          ParametrosPlano = new ArrayList<GEParametrosNiif>();
		          ColumnasCbox = new ArrayList<GEParametrosNiif>();
		          
		          datos_tabla = (List) ParametrizacionFac.getFacade().obtenerListado("getListDatosTabla",
		                                                                             nombreReporte);
		          
		          
		          log.info("tamali lista: " + datos_tabla.size());
		          ParametrosCbox = new ArrayList<GEParametrosNiif>();
		          for (Object dto : datos_tabla) {
		            Cargue cargue = (Cargue) dto;
		            GEParametrosNiif ParametrosNiif = new GEParametrosNiif();
		            ParametrosNiif.setNameParametro(cargue.getCOLUMNA());
		            ParametrosNiif.setDescripcion(cargue.getCOLUMNA());
		            ParametrosNiif.setRequerido("S");
		            ParametrosCbox.add(ParametrosNiif);
		            log.info("loop cargue parametros: " + cargue.getCOLUMNA());
		          }
		          if (ParametrosCbox != null) {
		            /*
		             * Generando Lista de Items desde la base de datos
		             */
		            GEReportesNiifAssembler.crearListItemsColumnasDesdeDto(ParametrosCbox, idLstBColumnas);
		            idLstBColumnas.applyProperties();
		            /*
		             * Generando el Listado Parametros Reporte Plano
		             */
		            GEReportesNiifAssembler.crearComboItemDesdeDTO(ParametrosCbox, idCboxParametros);
		          }

		          idCboxParametros.applyProperties();
		          idCboxParametros.setSelectedIndex(0);

		        } catch (Exception e) {
//		          super.lanzarExcepcion(new Excepcion(IExcepcion.EXCEPCION_GENERAL, e));
		          log.info("Error al ejecutar la accion");
		        }

		      }
		    }

		  }
	  
	  
	  public List GetParametros(String reportPath) throws JarException {
		    log.info("Ejecutando Método [GetParametros]");
		    List<GEParametrosNiif> Parametros = new ArrayList<GEParametrosNiif>();
		    JRParameter[] parameters = null;

		    try {
		      String str = FileUtils.readFileToString(new File(reportPath));
		      JasperDesign jasperDesign = null;

		      // verifica que el reporte tenga la etiqueta bucket y que tenga el atributo class ya que este
		      // debe tener un tratamiento especial para poder realizar la compiacion
		      if (str.contains("bucket class=\"java.lang.String\"")) {
		        // se crea una expresion regular para eliminar el atributo class del bucket el cual causa
		        // error en la compilacion del reporte
		        Pattern patron =
		                       Pattern.compile("^(<bucket)[\\s](class=\"java.lang.String\">)|(<bucket)[\\s](class=\"java.lang.String\">)");
		        Matcher encaja = patron.matcher(str);
		        String resultado = encaja.replaceAll("<bucket>");

		        // se crea una expresion regular para agreagr el atributo class a la etiqueta
		        // bucketExpression el cual es nesesaria para compilar el reporte sin errores

		        Pattern patron2 = Pattern.compile("(<bucketExpression>)");
		        Matcher encaja2 = patron2.matcher(resultado);
		        String resultado2 = encaja2.replaceAll("<bucketExpression class=\"java.lang.String\">");

		        log.info(resultado2);

		        jasperDesign = cargaFuenteReporte(resultado2);

		      } else {
		        // reporte sin atributo bucket
		        log.info(str);
		        jasperDesign = cargaFuenteReporte(str);

		      }

		      log.info("cargaFuenteReporte----->");
		      JasperReport report = JasperCompileManager.compileReport(jasperDesign);

		      // JasperReport report = JasperCompileManager.compileReport(reportPath);

		      parameters = report.getParameters();
		      JRParameter param = null;

		      // I skip the 16 built-in parameters
		      if (parameters.length > 19) {
		        for (int i = 19; i < parameters.length; i++) {
		          param = parameters[i];
		          if (param.isForPrompting()) {
		            GEParametrosNiif ParametrosNiif = new GEParametrosNiif();
		            String propertier = param.getPropertiesMap().getProperty("Requerido");
		            ParametrosNiif.setNameParametro(param.getName());
		            log.info("Nombre  " + param.getName());
		            ParametrosNiif.setDescripcion(param.getDescription());
		            ParametrosNiif.setRequerido(propertier == null || propertier.equals("N")
		                                                                                     ? "N"
		                                                                                     : propertier);
		            if (!ParametrosNiif.getNameParametro().equals("IS_IGNORE_PAGINATION")) {
		              Parametros.add(ParametrosNiif);
		            }
		          }
		        }
		      }
		    } catch (Exception ex) {
		      ex.printStackTrace();
		    }
		    return Parametros;
		  }
	  
	  public void onGenerarReporte(String fuenteReporte) {
		    log.info("Ejecutando Método [onGenerarReporte]");
		    if (fuenteReporte.equalsIgnoreCase("S")) {
		      util.setMensajeHistorico(IConstantes.ESTADO_ADVERTENCIA,
		                          "Debe seleccionar un reporte",
		                          null,
		                          null,
		                          "Reportes Niif");
		      return;
		    }
		    HashMap<String, Object> parametrosReporte = null;
		    if (idLstBColumnas.getSelectedCount() <= 0 && !fuenteReporte.endsWith(".jrxml")) {
		    	util.setMensajeHistorico(IConstantes.ESTADO_ADVERTENCIA,
		                          "Debe seleccionar al menos una columna del reporte",
		                          null,
		                          null,
		                          "Reportes Niif");
		    }
		    // validacion que escoja al menos una columna del reporte
		    else {
		      for (int i = 0; i < idRwsParaReportes.getChildren().size(); i++) {
		        Textbox txtBoxParam =
		                            (Textbox) (idRwsParaReportes.getChildren().get(i)).getChildren().get(2);

		        if (!fuenteReporte.endsWith(".jrxml"))
		          txtBoxParam.getConstraint().validate(txtBoxParam, txtBoxParam.getValue());
		      }

		      /*
		       * Fuente no es NULL y termina en .jrxml
		       */
		      if (fuenteReporte != null && fuenteReporte.endsWith(".jrxml")) {

		        try {
		          String formato = IConstantes.REP_FORMATO_SALIDA_PDF;

		          String rutaReporte = "";
		          Connection conexion = null;

		         // ContextoAplicacion pContextoAplicacion = ContextoAplicacion.getInstance();

		          /*
		           * calculamos la ruta física de los reportes
		           */
		          rutaReporte = "C://compilados_jasper/repositorio/fuentes"
		                        + fuenteReporte;// ruta
		                                        // fisica
		                                        // del
		                                        // reporte

		          /*
		           * obtenemos una conexion a la base de datos
		           */
		          conexion = ReportesFacade.getFacade().obtenerConexionReporte();

		          /*
		           * Tomamos los valores de los parámetros
		           */
		          parametrosReporte =
		                            listaDto != null
		                                             ? GEReportesNiifAssembler.crearMapDesdeDTO(listaDto,
		                                                                                        idRwsParaReportes)
		                                             : null;

//		          previsualizadorReportesAction win =
//		                                            (previsualizadorReportesAction) getEmergente(IConstantes.PANTALLA_REPORTES_NUEVO,
//		                                                                                         parametrosReporte);

		          /*
		           * enviamos la información necesaria...
		           */

//		          win.inicializarReporte(rutaReporte, fuenteReporte, conexion, parametrosReporte, formato);
		          /*
		           * mostramos el reporte en una ventana nueva
		           */
//		          win.ejecutarReporte();

		        } catch (Exception e) {
		          log.error("Error: "+e.getMessage(), e.fillInStackTrace());
//		          super.lanzarExcepcion(new Excepcion(IExcepcion.EXCEPTION_GLOBAL, e));
		          
		        }

		      } // Fuente no es NULL y termina en .jrxml

		      else if (fuenteReporte != null) {
		        // Fuente no es NULL Y ES NOMBRE_TABLA
		        String columnas = null;
		        String filtros = "";
		        String tipo_dato = null;
		        char comilla = '"';
		        char coma = ',';
		        String alias = " as columna";
		        String Inicorteche = "#{";
		        String Fincorteche = "}";
		        String Like = "like";
		        String porciento = "%";
		        String and = "AND";
		        String or = "OR";
		        String espacio = " ";
		        String concatenar = "||";
		        String fecha = "TRUNC(";
		        String finParentisis = ")";
		        String validaDate;
		        String validaDateColum;

		        int j = 0;

		        Set<Listitem> listitems = idLstBColumnas.getSelectedItems();

		        List<Listitem> listaDetCompro = new ArrayList<Listitem>(listitems);
		        for (Iterator iterator = listaDetCompro.iterator(); iterator.hasNext();) {
		          Listitem listitem = (Listitem) iterator.next();
		          List<Component> datos = listitem.getChildren();
		          for (int i = 0; i < datos.size(); i++) {
		            GEParametrosNiif ParametrosNiif = new GEParametrosNiif();
		            ParametrosNiif.setDescripcion(((Listcell) datos.get(i)).getLabel());
		            ParametrosNiif.setNameParametro(((Listcell) datos.get(i)).getLabel());
		            ParametrosNiif.setRequerido("N");
		            ColumnasCbox.add(ParametrosNiif);
		          }
		        }

		        try {

		            for (Object dto : ColumnasCbox) {
		                j++;
		                GEParametrosNiif para = (GEParametrosNiif) dto;

		                propiedades = new HashMap<String, Object>();
		                propiedades.put("EFP_FUENTE_REPORTE", fuenteReporte);
		                propiedades.put("EFP_COLUMNA", para.getDescripcion());

		                tipo_dato = (String) ParametrizacionFac.getFacade()
		                                                       .obtenerRegistro("obtenerTipoDatoColumna",
		                                                                        propiedades);

		                if (tipo_dato.equals("DATE")) {
		                  validaDateColum = fecha + para.getDescripcion() + finParentisis;
		                } else {
		                  validaDateColum = para.getDescripcion();
		                }

		                if (columnas == null) {

		                  columnas = validaDateColum + alias + j + coma;
		                } else if (ColumnasCbox.size() == j) {
		                  columnas = columnas + validaDateColum + alias + j;
		                } else {
		                  columnas = columnas + validaDateColum + alias + j + coma;
		                }
		              }
		            } catch (Exception e) {
		             // super.lanzarExcepcion(new Excepcion(IExcepcion.EXCEPCION_GENERAL, e));
		            	log.info("error al armar las columnas");
		            }    
		        
		        for (Object dto : ParametrosPlano) {
		          j++;
		          GEParametrosNiif para = (GEParametrosNiif) dto;
		          Combobox combo =
		                         (Combobox) idGridParametros.getFellow("idCbox" + para.getNameParametro());
		          String Igual = combo.getSelectedItem().getValue();
		          log.info("para.getDescripcion: " + para.getDescripcion());

		          try {

		            propiedades = new HashMap<String, Object>();
		            propiedades.put("EFP_FUENTE_REPORTE", fuenteReporte);
		            propiedades.put("EFP_COLUMNA", para.getDescripcion());

		            tipo_dato = (String) ParametrizacionFac.getFacade()
		                                                   .obtenerRegistro("obtenerTipoDatoColumna",
		                                                                    propiedades);
		            											 
		            

		            if (tipo_dato.equals("DATE")) {
		              validaDate = fecha + para.getDescripcion() + finParentisis;
		            } else {
		              validaDate = para.getDescripcion();
		            }

		            log.info(": " + para.getDescripcion());
		            log.info("Igual: " + Igual);
		            log.info("tipo_dato: " + tipo_dato);
		            log.info("fuenteReporte: " + fuenteReporte);
		            log.info("validaDate: " + validaDate);

		            if (filtros == null || filtros.isEmpty()) {

		              if (Igual.equals(">=") || Igual.equals("<=")) {

		                filtros = and + espacio + (esNumerico(tipo_dato)?validaDate+"||''":validaDate ) + espacio + Igual + espacio + Inicorteche
		                          + para.getDescripcion().replaceAll(" ", "") + Fincorteche;
		                log.info("filtros vacio: " + filtros);
		              } else {

		                if (Igual.equals("=")) {

		                  filtros = and + espacio + (esNumerico(tipo_dato)?validaDate+"||''":validaDate ) + espacio + Igual + espacio + Inicorteche
		                            + para.getDescripcion().replaceAll(" ", "") + Fincorteche;

		                } else {

		                  filtros = and + espacio + (esNumerico(tipo_dato)?validaDate+"||''":validaDate ) + espacio + Igual + espacio + "'" + porciento
		                            + "'" + concatenar + Inicorteche
		                            + para.getDescripcion().replaceAll(" ", "") + Fincorteche;
		                }

		              }
		            } else {
		              if (Igual.equals(">=") || Igual.equals("<=")) {
		                filtros = filtros + espacio + and + espacio + (esNumerico(tipo_dato)?validaDate+"||''":validaDate ) + espacio + Igual + espacio
		                          + Inicorteche + para.getDescripcion().replaceAll(" ", "") + Fincorteche;
		                log.info("filtros else: " + filtros);
		              } else {

		                if (Igual.equals("=")) {
		                	

		                  filtros = and + espacio + (esNumerico(tipo_dato)?validaDate+"||''":validaDate )+ espacio + Igual + espacio + Inicorteche
		                            + para.getDescripcion().replaceAll(" ", "") + Fincorteche;

		                } else {

		                  filtros = and + espacio + (esNumerico(tipo_dato)?validaDate+"||''":validaDate ) + espacio + Igual + espacio + "'" + porciento
		                            + "'" + concatenar + Inicorteche
		                            + para.getDescripcion().replaceAll(" ", "") + Fincorteche;
		                }
		              }
		            }

		          } catch (Exception e) {
//		            super.lanzarExcepcion(new Excepcion(IExcepcion.EXCEPCION_GENERAL, e));
		        	  log.info("error: "+e);
		          }

		        }
		        
		        

		        log.info("*******columnas: " + columnas);
		        log.info("*******filtros: " + filtros);
		        
		      
		       
		        
		        
		        if (columnas.endsWith(",")) {
		          columnas = columnas.substring(0, columnas.length() - 1);
		        }

		        try {
		          if (ParametrosPlano != null && ParametrosPlano.size() > 0) {
		            parametrosReporte = GEReportesNiifAssembler.crearMapDesdeDTO(ParametrosPlano,
		                                                                         idRwsParaReportes);
		            parametrosReporte.put("FILTROS", filtros);
		          } else {
		            parametrosReporte = new HashMap();
		            parametrosReporte.put("FILTROS", filtros);
		          }

		          parametrosReporte.put("CONSULTA", columnas);
		          parametrosReporte.put("TABLA_NAME", idCboxPlantillas.getSelectedItem().getValue());

		          log.info("CONSULTA----->" + columnas);
		          log.info("idCboxPlantillas.getSelectedItem().getValue()----->"
		                   + idCboxPlantillas.getSelectedItem().getValue());
		         
		          List result = (List) ParametrizacionFac.getFacade().obtenerListado("getReportePlano",
		                                                                             parametrosReporte);

		          log.info("result----->" + result);
		          writeXlFile(result);

		        } catch (Exception e) {
//		          log.error(e.getMessage(), e.fillInStackTrace());
//		          super.lanzarExcepcion(new Excepcion(IExcepcion.EXCEPTION_GLOBAL, e));
		          log.info("error: "+e);
		        }

		      } // Fuente no es NULL Y ES NOMBRE_TABLA

		    } // validacion que escoja al menos una columna del reporte
		  }
	  
	  
	  public static JasperDesign cargaFuenteReporte(String xml) throws JRException {

		    log.info("cargaFuenteReporte JasperDesign ----->");
		    JasperDesign design = null;
		    /*
		     * Atributos que ponen problema en las versiones superiores del JRXM 4.5.X
		     */
		    design =
		           JRXmlLoader.load(new LegacyJasperInputStream(new ByteArrayInputStream(xml.replaceAll("uuid=\"[^\"]*\"",
		                                                                                                "")
		                                                                                    .replaceAll("splitType=\"[^\"]*\"",
		                                                                                                "")
		                                                                                    .getBytes())));

		    return design;
		  }

	  
	  public void writeXlFile(List ListDTO) {
		    // List headers_name=null;

		    ArrayList<ArrayList<String>> data = new ArrayList<ArrayList<String>>();
		    ArrayList<String> headers = new ArrayList<String>();
		    String Patch = Executions.getCurrent().getDesktop().getWebApp().getRealPath(separator)
		                   + separator;

		    File file123 = new File(Patch + idCboxPlantillas.getSelectedItem().getLabel());
		    String sheetName = idCboxPlantillas.getSelectedItem().getValue();
		    File outputFile = file123;

		    log.info("idCboxPlantillas.getSelectedItem().getValue()----->"
		             + idCboxPlantillas.getSelectedItem().getValue());

		    for (Object dto : ColumnasCbox) {
		      GEParametrosNiif para = (GEParametrosNiif) dto;
		      headers.add(para.getDescripcion() != null ? para.getDescripcion() : "");
		    }

		    // Lista De Items

		    GEReportesNiifAssembler.crearCellDesdeDTO(ListDTO, data);

		    HSSFWorkbook wb = new HSSFWorkbook();
		    HSSFSheet sheet = wb.createSheet(sheetName);

		    int rowIdx = 0;
		    int cellIdx = 0;
		    // Header
		    HSSFRow hssfHeader = sheet.createRow(rowIdx);
		    HSSFCellStyle cellStyle = wb.createCellStyle();
		    cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		    Font font = wb.createFont();
		    font.setFontName("Courier New");
		    font.setBoldweight(Font.BOLDWEIGHT_BOLD);
		    cellStyle.setFont(font);
		    for (Iterator<String> cells = headers.iterator(); cells.hasNext();) {
		      HSSFCell hssfCell = hssfHeader.createCell(cellIdx++);
		      hssfCell.setCellStyle(cellStyle);
		      hssfCell.setCellValue((String) cells.next());
		    }

		    // Datos
		    rowIdx = 1;
		    for (Iterator<ArrayList<String>> rows = data.iterator(); rows.hasNext();) {
		      ArrayList<?> row = (ArrayList<?>) rows.next();

		      HSSFRow hssfRow = sheet.createRow(rowIdx++);
		      cellIdx = 0;
		      for (Iterator<?> cells = row.iterator(); cells.hasNext();) {
		        String tmp = (String) cells.next();
		        HSSFCell hssfCell = null;
		        try {
		          Long cell = Long.parseLong(tmp);
		          hssfCell = hssfRow.createCell(cellIdx++, 0);// Numeric
		          hssfCell.setCellValue(cell);
		        } catch (Exception e) {
		          String cell = tmp;
		          if (cell == null || cell.equals("null")) {
		            cell = "";
		          }
		          hssfCell = hssfRow.createCell(cellIdx++);// String
		          hssfCell.setCellValue(cell != null ? cell : "");
		        }
		      }
		    }

		    for (int i = 0; i < headers.size(); i++) {
		      sheet.autoSizeColumn((short) i);// AutoTama?o Segun Contenido
		    }
		    wb.setSheetName(0, sheetName);
		    try {
		      FileOutputStream outs = new FileOutputStream(outputFile);
		      wb.write(outs);
		      outs.close();
		      Filedownload.save(outputFile.getAbsoluteFile(), "xls");
		    } catch (IOException e) {
		     // super.lanzarExcepcion(new Excepcion(IExcepcion.EXCEPTION_GLOBAL, e));
		    	log.info("Error: en metodo writeXlFile");
		    }

		  }
	  
	  
	  public void onAddParametro() {
		    log.info("Ejecutando metodo [onAddParametro]");

		    try {

		      if (idCboxParametros.getSelectedItem().getValue() != null) {
		        GEParametrosNiif ParametrosNiif = new GEParametrosNiif();
		        ParametrosNiif.setDescripcion(idCboxParametros.getSelectedItem().getLabel());
		        ParametrosNiif.setNameParametro(idCboxParametros.getSelectedItem().getValue().toString());
		        ParametrosNiif.setRequerido("S");
		        ParametrosPlano.add(ParametrosNiif);

		        /*
		         * Si se escoje el Item, se elimina del Listado y se vuelve a seleccionar el primero
		         */
		        idCboxParametros.removeChild(idCboxParametros.getSelectedItem());
		        idCboxParametros.setSelectedIndex(0);
		        GEReportesNiifAssembler.crearRowDesdeDTO(ParametrosPlano, idRwsParaReportes, true);

		      } else {

		        /*
		         * Se escogio la Opción: Agregar Todos
		         */

		        if (ParametrosCbox != null) {
		          GEReportesNiifAssembler.crearRowDesdeDTO(ParametrosCbox, idRwsParaReportes, true);
		          idCboxParametros.getChildren().clear();
		        }
		      }
		    } catch (Exception e) {

		      e.printStackTrace();
		    }

		  }


	  public Component getEmergente(String nombre, Map<String, Object> param) throws Exception {
		    log.info("Ejecutando el metodo [getEmergente]");
		    return Executions.createComponents(nombre, null, param);

		  }


	  public boolean esNumerico(String valor) { 
		  
		  if(valor.equals("bigint") || valor.equals("integer")) {
			  return true;
		  }
		  
		  return false;
		  
	  }
}
