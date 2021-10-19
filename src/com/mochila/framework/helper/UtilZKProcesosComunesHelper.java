package com.mochila.framework.helper;
 
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.zkoss.zul.Label;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.zkoss.image.AImage;
import org.zkoss.zk.ui.AbstractComponent;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.A;
import org.zkoss.zul.Bandbox;
import org.zkoss.zul.Button;
import org.zkoss.zul.Cell;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Groupbox;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Html;
import org.zkoss.zul.Image;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Panel;
import org.zkoss.zul.Panelchildren;
import org.zkoss.zul.Radio;
import org.zkoss.zul.Radiogroup;
import org.zkoss.zul.Row;
import org.zkoss.zul.Separator;
import org.zkoss.zul.Spinner;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Timebox;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.Vbox;
import org.zkoss.zul.impl.InputElement;

import com.mochila.framework.contract.IConstantes;

import com.mochila.framework.facade.ParametrizacionFac;
import com.mochila.framework.util.Bean;
import com.mochila.framework.util.MyBatisUtil;
import com.mochila.framework.zk.notificaciones.Notificaciones;
import com.mochila.inv.dto.FotoProducto;
import com.mochila.inv.dto.Producto;




public class UtilZKProcesosComunesHelper {
	protected static Logger log = Logger.getLogger(UtilZKProcesosComunesHelper.class);
	
	public void lanzarMensajeCamposFatantes(String msn, ArrayList<String> values) {
		String valores="Validar:";
		for (int i = 0; i < values.size(); i++) { 
			
			valores=valores+","+values.get(i);
		}
		
		Messagebox.show(
				msn+": "+valores,
				"Advertencia", Messagebox.OK,
				Messagebox.EXCLAMATION);
	}
	
	
	public static Listitem crearListitemDetalle(Object obj, String... values) {
		Listitem item = new Listitem();
		Label label=new Label();
		Listcell celda = new Listcell();
		
				
		
		label.setValue(values[0]);
		label.setStyle("color:white;font-size:18px;cursor:pointer");
		label.setTooltiptext(values[0].equals("A")?"Activo":values[0].equals("I")?"Inactivo":"Desconocido!");
		
		celda.appendChild(label);
		celda.setStyle("background:#179FF3");
		
		item.appendChild(celda);
		
		// agregando los valores dinamicos		
		
		for (int i = 1; i < values.length; i++) { 
			
			celda = new Listcell(values[i]);
			celda.setValue(values[i]);
			item.appendChild(celda);
		}
		
		item.setValue(obj);

		return item;
	}
	
	public static Listitem crearListitem(Object obj, String... values) {
		Listitem item = new Listitem();
		Label label=new Label();
		Listcell celda = new Listcell();
		
				
		
		label.setValue(values[0]);
		label.setStyle("color:white;font-size:18px;cursor:pointer");
		label.setTooltiptext(values[0].equals("A")?"Activo":values[0].equals("I")?"Inactivo":"Desconocido!");
		
		celda.appendChild(label);
		celda.setStyle("background:#179FF3");
		
		item.appendChild(celda);
		
		// agregando los valores dinamicos		
		
		for (int i = 1; i < values.length; i++) { 
			
			celda = new Listcell(values[i]);
			celda.setValue(values[i]);
			item.appendChild(celda);
		}
		
		if (obj instanceof Producto ){
			Producto dto=(Producto)obj;
			final FotoProducto fp = getFotoProductoPrincipal(dto);
			Image img=new Image();
			img.setWidth("45px");
			img.setHeight("45px");
			Listcell celdaImg = new Listcell();
			if(fp.getImg()!=null){
				try {
					AImage media = new AImage(dto.getDescripcion(),fp.getImg());
					img.setContent(media);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					log.info("error al cargar la imagen");
				}	
				}else{
					img.setSrc("imagen/imageDefault.png");
				}
			celdaImg.appendChild(img);
			item.appendChild(celdaImg);
			
		}
		
		if (obj instanceof FotoProducto ){
			FotoProducto dto= (FotoProducto) obj;
			Image img=new Image();
			img.setWidth("45px");
			img.setHeight("45px");
			Listcell celdaImg = new Listcell();
			if(dto.getImg()!=null){
				try {
					AImage media = new AImage(dto.getDescripcion(),dto.getImg());
					img.setContent(media);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					log.info("error al cargar la imagen");
				}	
				}else{
					img.setSrc("imagen/imageDefault.png");
				}
			celdaImg.appendChild(img);
			item.appendChild(celdaImg);
		}

		item.setValue(obj);

		return item;
	}
	
	public static Panel crearPanelColorTexto(String textoHtml,String colorTextoHtml,String textoTamano,String background,String altoPanelPx,String anchoPanelPx) {
		log.info("metodo[crearPanelColorTexto]");
		
		Panel panelDescuento=new Panel();
//		panelDescuento.setStyle("margin-top:6px");
		panelDescuento.setHeight(altoPanelPx);
		panelDescuento.setWidth(anchoPanelPx);
		panelDescuento.setBorder("0");					
		Panelchildren panelChildDescuento =new Panelchildren();		
		panelChildDescuento.setStyle("background:"+background+";color:"+colorTextoHtml+";font-size:15px;");
		
		Vbox vboxDescuento=new Vbox();
		vboxDescuento.setAlign("CENTER");
		vboxDescuento.setPack("CENTER");
		vboxDescuento.setHeight("90%");
		vboxDescuento.setWidth("100%");
		
		Html htmlContenedorDescuento=new Html();
		htmlContenedorDescuento.setContent("<font color=\""+colorTextoHtml+"\" size=\""+textoTamano+"\" face=\"arial\"><div>");
		htmlContenedorDescuento.setContent(htmlContenedorDescuento.getContent()+"<b>"+textoHtml+"</b>");
		htmlContenedorDescuento.setContent(htmlContenedorDescuento.getContent()+"</div></font>");
		
		vboxDescuento.appendChild(htmlContenedorDescuento);
		panelChildDescuento.appendChild(vboxDescuento);
		panelDescuento.appendChild(panelChildDescuento);
		
		
	return panelDescuento;
	}
	
	public static Panel crearPanelTexto(String texto,String whidth) {
		log.info("metodo[crearPanelTexto]");
		
		Panel panel=new Panel();
		panel.setStyle("margin-top:3px");
		panel.setHeight("30px");
		panel.setWidth("140px");
		panel.setBorder("normal");
		
		
		
		Panelchildren panelChild =new Panelchildren();
		panelChild.setStyle("color:white;font-size:15px;cursor:pointer;background:#ff7700");
		
		Vbox vbox=new Vbox();
		vbox.setAlign("center");
		vbox.setPack("center");
		vbox.setHeight("90%");
		vbox.setWidth("100%");
	
		Html h=new Html();
		h.setContent(texto);
		
		
		vbox.appendChild(h);
		panelChild.appendChild(vbox);
		panel.appendChild(panelChild);

		return panel;
	}
	
	  public static void seleccionarRadio(Radiogroup radiogroup, String value) {
			List<Radio> radios = radiogroup.getItems();
			for (Radio radio : radios) {
			    if (radio.getValue().equals(value)) {
				radiogroup.setSelectedItem(radio);
				break;
			    }
			}
		    }

	public static void mostrarNotificacion(String titulo, String msg,
			String tipoNotificacion, Groupbox idCLIENTEZGbxMensajes,
			Grid idCLIENTEZGridMensajes) {
		Row fila = new Row();
		Label label = new Label(msg);
		Cell cellIcono = new Cell();
		Cell cellMensaje = new Cell();
		cellMensaje.appendChild(label);
		fila.appendChild(cellIcono);
		fila.appendChild(cellMensaje);
		idCLIENTEZGridMensajes.getRows().appendChild(fila);
		idCLIENTEZGbxMensajes.setOpen(true);
		

	}

	public static void limpiarCampos(Component componentePadre) {

		if (componentePadre instanceof Bandbox) {
			Bandbox componenteOriginal = (Bandbox) componentePadre;
			componenteOriginal.setRawValue("");
		} else {
			List<Component> listaComponentes = componentePadre.getChildren();

			for (Component componente : listaComponentes) {
				if (componente instanceof Listbox) {

					Listbox componenteOriginal = (Listbox) componente;
					componenteOriginal.setSelectedIndex(0);
				} else if (componente instanceof Radiogroup) {

					Radiogroup componenteOriginal = (Radiogroup) componente;
					componenteOriginal.setSelectedIndex(0);

				} else if (componente instanceof Datebox) {

					Datebox componenteOriginal = (Datebox) componente;
					componenteOriginal.setValue(new Date());

				} else if (componente.getChildren().size() > 0) {

					limpiarCampos(componente);
				} else {
					if (componente instanceof InputElement) {
						InputElement componenteOriginal = (InputElement) componente;

						if (componenteOriginal.getAttribute("valorAsumido") == null) {
							componenteOriginal.setRawValue(null);
						} else {
							componenteOriginal.setRawValue(componenteOriginal
									.getAttribute("valorAsumido"));
						}
					}

				}
			}
		}
	}

	public static void deshabilitarCampos(Component componentePadre) {
		if (componentePadre instanceof Groupbox) {
			componentePadre.invalidate();
		}
		if (componentePadre instanceof Bandbox) {
			Bandbox componenteOriginal = (Bandbox) componentePadre;
			componenteOriginal.setDisabled(true);
		} else {
			List<Component> listaComponentes = componentePadre.getChildren();

			for (Component componente : listaComponentes) {
				if (componente instanceof Listbox) {
					Listbox componenteOriginal = (Listbox) componente;
					componenteOriginal.setDisabled(true);
					// componenteOriginal.setSelectedIndex(0);
					if (componenteOriginal.isCheckmark())
						deshabilitarCampos(componente);
				} else if (componente instanceof Listitem) {
					Listitem componenteOriginal = (Listitem) componente;
					componenteOriginal.setDisabled(true);
				} else if (componente.getChildren().size() > 0) {
					deshabilitarCampos(componente);
				} else {
					componente.setAttribute("habilitado", "N");
					if (componente instanceof InputElement) {
						InputElement componenteOriginal = (InputElement) componente;
						componenteOriginal.setDisabled(true);
						if (componente instanceof Datebox) {
							((Datebox) componente).setDisabled(true);
						}
						if (componente instanceof Timebox) {
							((Timebox) componente).setDisabled(true);
						} else if (componente instanceof Spinner) {
							((Spinner) componente).setDisabled(true);
						}
					} else if (componente instanceof A) {
						A componenteOriginal = (A) componente;
						componenteOriginal.setDisabled(true);
					} else if (componente instanceof Checkbox) {
						Checkbox componenteOriginal = (Checkbox) componente;
						componenteOriginal.setDisabled(true);
					} else if (componente instanceof Button) {
						Button componenteOriginal = (Button) componente;
						componenteOriginal.setDisabled(true);

					}
				}
			}
		}
	}

	public static void habilitarCampos(Component componentePadre) {
		if (componentePadre instanceof Groupbox) {
			componentePadre.invalidate();
		}
		if (componentePadre instanceof Bandbox) {
			Bandbox componenteOriginal = (Bandbox) componentePadre;
			componenteOriginal.setDisabled(false);
		} else {
			List<Component> listaComponentes = componentePadre.getChildren();

			for (Component componente : listaComponentes) {
				componente.setAttribute("habilitado", "S");
				if (!(componente instanceof Toolbarbutton)) {

					if (componente instanceof Listbox) {
						Listbox componenteOriginal = (Listbox) componente;
						componenteOriginal.setDisabled(false);
						if (componenteOriginal.isCheckmark())
							habilitarCampos(componente);
					} else if (componente instanceof Listitem) {
						Listitem componenteOriginal = (Listitem) componente;
						componenteOriginal.setDisabled(false);
					} else if (componente.getChildren().size() > 0) {
						habilitarCampos(componente);
					} else {
						if (componente instanceof InputElement) {
							InputElement componenteOriginal = (InputElement) componente;
							componenteOriginal.setDisabled(false);
							if (componente instanceof Datebox) {
								((Datebox) componente).setDisabled(false);
							}
							if (componente instanceof Timebox) {
								((Timebox) componente).setDisabled(false);
							} else if (componente instanceof Spinner) {
								((Spinner) componente).setDisabled(false);
							}
						} else if (componente instanceof A) {
							A componenteOriginal = (A) componente;
							componenteOriginal.setDisabled(false);
						} else if (componente instanceof Checkbox) {
							Checkbox componenteOriginal = (Checkbox) componente;
							componenteOriginal.setDisabled(false);
						} else if (componente instanceof Button) {
							Button componenteOriginal = (Button) componente;
							componenteOriginal.setDisabled(false);

						}
					}
				}

			}
		}
	}
	
	public int getIndexList( Listbox list, String valor){
		//log.info("metodo [getIndexList]");
		
		int index=0;
		
		for (int i=0; i< list.getItems().size();i++){
			
			log.info(i+" "+list.getItems().get(i).getValue()+" <---> "+valor);
			
			if(list.getItems().get(i).getValue().equals(valor)){
			log.info(i+" "+list.getItems().get(i).getValue()+" = "+valor);
				index= i;
				
				i=list.getItems().size()+1;
				
			}
			
		}
		
		return index;
	}
	
	
	
//	public long siguientePk(String tabla){
//		//log.info("metodo [siguientePk]");
//		
//		Long secuencia = null;
//		List<Personas> listaPersona = new ArrayList();
//		
//		try {
//			SqlSession session = new MyBatisUtil().getSession();
//			if (session != null) {
//				try {
//					listaPersona  =  session.selectList(
//							"siguientePk"+tabla);
//
//					secuencia=listaPersona.get(0).getSecuencia();
//				} finally {
//					session.close();
//					
//				}
//			} else {
//				//log.info("Session NULL...");
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}	
//		return secuencia;
//	}
//	
//	public long siguientePk(String tabla){
//		//log.info("metodo [siguientePk]");
//		
//		Long secuencia = new Long(1000);
//		List listaResultado = new ArrayList();
//		
//		try {
//			SqlSession session = new MyBatisUtil().getSession();
//			if (session != null) {
//				try {
//					listaResultado  =  session.selectList(
//							"siguientePk"+tabla);
//					if(listaResultado.get(0)!=null){
//					secuencia= (Long) listaResultado.get(0);
//					}
//				} finally {
//					session.close();
//					
//				}
//			} else {
//				//log.info("Session NULL...");
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}	
//		return secuencia;
//	}
//	
	
//	public  PerfilPersonas getPerfil(String tipo,Long secuencia){
//		log.info("metodo [getPerfil]");
//		
//		PerfilPersonas perfil=new  PerfilPersonas();
//		perfil.setSec_persona(secuencia);
//		perfil.setTipoPersona(tipo);
//		
//		List<PerfilPersonas> listaDto = new ArrayList();
//		
//		try {
//			SqlSession session = new MyBatisUtil().getSession();
//			if (session != null) {
//				try {
//					listaDto = session.selectList("getPerfilPersonas", perfil);
//
//					log.info("Hay " + listaDto.size() + " Datos encontrados.");
////					perfil =(PerfilPersonas) listaDto.get(0);
//					
//				} finally {
//					session.close();
//					
//				}
//			} else {
//				//log.info("Session NULL...");
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}	
//		 if(listaDto.size()>0){return listaDto.get(0);}else{return null;}
//	}
	
	
	public void lanzarMensaje(String msn){
		Messagebox.show(
				msn,
				"Advertencia", Messagebox.OK,
				Messagebox.EXCLAMATION);
	}
	
	
	public static FotoProducto getFotoProductoPrincipal(Producto p) {
		log.info("metodo[getFotoProductoPrincipal]");
		FotoProducto fp = new FotoProducto();

		fp.setProducto(p);

		try {
			ArrayList<FotoProducto> listaProducto = (ArrayList<FotoProducto>) ParametrizacionFac.getFacade()
					.obtenerListado("getFotoProductoPrincipal", fp);
			log.info("Hay " + listaProducto.size() + " datos encontradas.");

			if (listaProducto.size() > 0) {
				fp = listaProducto.get(0);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return fp;
	}
	
	public String getCodigoCompuesto() {
		
		// Fecha actual desglosada:
		Calendar fecha = Calendar.getInstance();
		int anio = fecha.get(Calendar.YEAR);
		int mes = fecha.get(Calendar.MONTH) + 1;
		int dia = fecha.get(Calendar.DAY_OF_MONTH);
		int hora = fecha.get(Calendar.HOUR_OF_DAY);
		int minuto = fecha.get(Calendar.MINUTE);
		int segundo = fecha.get(Calendar.SECOND);

		java.util.Formatter fmtMes = new java.util.Formatter();
		java.util.Formatter fmtDia = new java.util.Formatter();
		java.util.Formatter fmtHora = new java.util.Formatter();
		java.util.Formatter fmtMinuto = new java.util.Formatter();
		java.util.Formatter fmtSegundo = new java.util.Formatter();

//		System.out.println("El año es: " + anio);
//		System.out.println("El mes es: " + mes);
//		System.out.println("El día es: " + dia);
//		System.out.printf("La hora es: %02d %n", hora);
//		System.out.printf("El minuto es: %02d %n", minuto);
//		System.out.printf("El segundo es: %02d %n", segundo);
		
		
		String llaveFecha = fmtDia.format("%02d", dia)+""+fmtMes.format("%02d", mes)+""+(anio+"").substring(2) ;
		String llaveHora = fmtHora.format("%02d", hora) + "" + fmtMinuto.format("%02d", minuto) + ""
				+ fmtSegundo.format("%02d", segundo);
		
		return codigoMarcelino(llaveFecha)+llaveHora;
		
	}
	
	public String codigoMarcelino(String parametro) {
		String resultado="";
		for(int i=0;  i < parametro.length(); i++) {
			
		
			
			 switch (parametro.charAt(i)+"") 
		        {
		            case "0":  resultado = resultado+"M";
		                     break;
		            case "1":  resultado = resultado+"A";
		                     break;
		            case "2":  resultado = resultado+"R";
		                     break;
		            case "3":  resultado = resultado+"C";
		                     break;
		            case "4":  resultado = resultado+"E";
		                     break;
		            case "5":  resultado = resultado+"L";
		                     break;
		            case "6":  resultado = resultado+"I";
		                     break;
		            case "7":  resultado = resultado+"N";
		                     break;
		            case "8":  resultado = resultado+"O";
		            		 break;
		            case "9":  resultado = resultado+"S";
                    		 break;
		        }
		}
		
	return resultado.isEmpty()?null:resultado;
	}
	
	/**
	   * Permite obtener la ruta en la que se deben buscar los subreportes
	   *
	   * @return la ruta de los subreportes dentro del contexto de la aplicacion
	   */
	  public static String obtenerDirectorioSubreportes() {
	    String subreportDir = "C:\\compilados_jasper\repositorio\fuentes";
	    return subreportDir;
	  }
	  
	  
	  
	  public void setMensajeHistorico(int estado, String label, String llaveRegistro,
		      String mensajeExt, String titulo) {
		    String imagen = null;
		    String llave;

		    if (llaveRegistro == null || llaveRegistro.isEmpty()) {
		      llave = "";
		    } else {
		      llave = " <b>[ " + llaveRegistro + " ]</b>";
		    }

		    if (mensajeExt == null) {
		      mensajeExt = "";
		    }

		    try {
		      switch (estado) {
		        case IConstantes.ESTADO_INSERCION_OK: {
		          imagen = IConstantes.ESTADO_IMAGEN_OK;
		          break;
		        }
		        case IConstantes.ESTADO_INSERCION_ERROR: {
		          imagen = IConstantes.ESTADO_IMAGEN_ERROR;
		          llave = "";
		          break;
		        }
		        case IConstantes.ESTADO_EDICION_OK: {
		          imagen = IConstantes.ESTADO_IMAGEN_OK;
		          break;
		        }
		        case IConstantes.ESTADO_EDICION_ERROR: {
		          imagen = IConstantes.ESTADO_IMAGEN_ERROR;
		          break;
		        }
		        case IConstantes.ESTADO_BORRAR_OK: {
		          imagen = IConstantes.ESTADO_IMAGEN_OK;
		          break;
		        }
		        case IConstantes.ESTADO_BORRAR_ERROR: {
		          imagen = IConstantes.ESTADO_IMAGEN_ERROR;
		          break;
		        }
		        case IConstantes.ESTADO_DEFAULT_OK: {
		          imagen = IConstantes.ESTADO_IMAGEN_OK;
		          llave = "";
		          break;
		        }
		        case IConstantes.ESTADO_DEFAULT_ERROR: {
		          imagen = IConstantes.ESTADO_IMAGEN_ERROR;
		          llave = "";
		          break;
		        }

		        case IConstantes.ESTADO_ADVERTENCIA: {
		          imagen = IConstantes.ESTADO_IMAGEN_ADVERT;
		          llave = "";
		          break;
		        }
		        default: {
		          imagen = IConstantes.ESTADO_IMAGEN_ERROR;
		        }
		      }// fin switch

		    } catch (Exception e) {
		      // --error del negocio...
//		      this.lanzarExcepcion(new Excepcion(IExcepcion.EXCEPCION_GENERAL, e));
		      log.error("Error -->>"+e.getMessage(), e);
		    }

		    Html lbl = new Html(label + llave + " " + mensajeExt);
		    if (titulo == null)
		      titulo = " ";
		    // Se obtiene el titulo de la forma para las notificaciones
		    // Validamos que la que notificacion debemos mostrar segun el estado de
		    // la imagen
		    if (imagen.equals(IConstantes.ESTADO_IMAGEN_OK)) {
		      Notificaciones.mostrarNotificacionInformacion(titulo, lbl.getContent(), 7000);
		    } else if (imagen.equals(IConstantes.ESTADO_IMAGEN_ERROR)) {
		      Notificaciones.mostrarNotificacionError(titulo, lbl.getContent(), 7000);
		    } else if (imagen.equals(IConstantes.ESTADO_IMAGEN_ADVERT)) {
		      Notificaciones.mostrarNotificacionAlerta(titulo, lbl.getContent(), 7000);
		    } else {
		      Notificaciones.mostrarNotificacion(titulo, lbl.getContent(), 7000);
		    }
		  }
	
}
