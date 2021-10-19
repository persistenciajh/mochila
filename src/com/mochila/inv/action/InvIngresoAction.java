package com.mochila.inv.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.zkoss.image.AImage;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.ext.AfterCompose;
import org.zkoss.zul.Bandbox;
import org.zkoss.zul.Borderlayout;
import org.zkoss.zul.Button;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Image;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Radiogroup;
import org.zkoss.zul.Rows;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Toolbarbutton;
import com.mochila.framework.facade.ParametrizacionFac;
import com.mochila.framework.helper.UtilZKProcesosComunesHelper;
import com.mochila.gen.dto.Persona;
import com.mochila.gen.dto.Usuario;
import com.mochila.inv.dto.InvDetalleIngreso;
import com.mochila.inv.dto.InvIngreso;
import com.mochila.inv.dto.Producto;

public class InvIngresoAction extends Borderlayout implements AfterCompose {
	
	private static final long serialVersionUID = 1L;

	protected static Logger log = Logger.getLogger(InvIngresoAction.class);

	Textbox idInvIngresoTbxBusqueda;
	Listbox idInvIngresoLbxInvIngreso;
	Toolbarbutton idInvIngresoBtnNuevo;
	Toolbarbutton idInvIngresoBtnEditar;
	Toolbarbutton idInvIngresoBtnGuardar;
	Toolbarbutton idInvIngresoBtnBorrar;
	Toolbarbutton idInvIngresoBtnCancelar;
	Rows idInvIngresoFormulario;
	Rows idInvIngresoFormulario2;
	Rows idInvIngresoFormulario3;
	Textbox idInvIngresoTbxOperacion;
	Textbox idInvIngresoTbxSecuencia;
	Textbox idInvIngresoTbxNombre;
	Textbox idInvIngresoTbxNombrePersona;
	Textbox idInvIngresoTbxCelPersona;
	
	Textbox idInvIngresoTbxCodigo;
	Textbox idInvIngresoTbxNumero;
	Listbox idInvIngresoLbxTipo;
	Datebox idInvIngresoDbxFecha;
	
	
	
	Textbox idInvIngresoTbxPersona;
	Bandbox idInvIngresoBandboxPersona;
	Listbox idInvIngresolistboxBandboxPersona;
	
	//Detalle
	Textbox idInvIngresoTbxCodigoProd;
	Intbox idInvIngresoTbxCantidadProd;
	Textbox idInvIngresoTbxTipoProd;
	Textbox idInvIngresoTbxPrecioProd;
	Textbox idInvIngresoTbxDescripcion;
	Button idInvIngresoBtnAgregarItem;
	Listbox idInvIngresoLbxDetalle;
	

	UtilZKProcesosComunesHelper util = new UtilZKProcesosComunesHelper();
	InvIngreso dtoSeleccionado = new InvIngreso();
	Usuario usuario = new Usuario();
	ArrayList<InvIngreso> listaInvIngreso = new ArrayList<InvIngreso>();
	ArrayList<InvDetalleIngreso> listaInvDetalleIngreso = new ArrayList<InvDetalleIngreso>();
	
	Long pk;
	
@Override
	public void afterCompose() {
		// TODO Auto-generated method stub
		cargar();
		
		try {
			Map<String, Object> parametro = new HashMap<String, Object>();			
			parametro.put("pk", null);
			
			 pk= (Long) ParametrizacionFac.getFacade().obtenerRegistro("invIngresoSiguientePK");
			
			log.info("Siguiente PK =>>>>> " + pk);
			
		
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			//e1.printStackTrace();
			log.info("error al obtener la llave pk");
		}
		
		usuario = (Usuario) Executions.getCurrent().getSession().getAttribute("USUARIO_SESSION");
		
		log.info("Usuario: " + usuario.getNombreUsuario());
	}

	public void cargar() {
		idInvIngresoTbxBusqueda = (Textbox) this.getFellow("idInvIngresoTbxBusqueda");
														
		idInvIngresoLbxInvIngreso = (Listbox) this.getFellow("idInvIngresoLbxInvIngreso");
		idInvIngresoBtnNuevo = (Toolbarbutton) this.getFellow("idInvIngresoBtnNuevo");
		idInvIngresoBtnEditar = (Toolbarbutton) this.getFellow("idInvIngresoBtnEditar");
		idInvIngresoBtnGuardar = (Toolbarbutton) this.getFellow("idInvIngresoBtnGuardar");
		idInvIngresoBtnBorrar = (Toolbarbutton) this.getFellow("idInvIngresoBtnBorrar");
		idInvIngresoBtnCancelar = (Toolbarbutton) this.getFellow("idInvIngresoBtnCancelar");
		idInvIngresoFormulario = (Rows) this.getFellow("idInvIngresoFormulario");
		idInvIngresoFormulario2 = (Rows) this.getFellow("idInvIngresoFormulario2");
		idInvIngresoFormulario3 = (Rows) this.getFellow("idInvIngresoFormulario3");
		idInvIngresoTbxOperacion = (Textbox) this.getFellow("idInvIngresoTbxOperacion");
		idInvIngresoTbxSecuencia = (Textbox ) this.getFellow("idInvIngresoTbxSecuencia");
		
		
		idInvIngresoTbxDescripcion = (Textbox ) this.getFellow("idInvIngresoTbxDescripcion");
		
		//BandboxPersona
		idInvIngresoTbxPersona  = (Textbox ) this.getFellow("idInvIngresoTbxPersona");
		idInvIngresoBandboxPersona = (Bandbox ) this.getFellow("idInvIngresoBandboxPersona");
		idInvIngresolistboxBandboxPersona = (Listbox ) this.getFellow("idInvIngresolistboxBandboxPersona");		
		idInvIngresoTbxNombrePersona = (Textbox ) this.getFellow("idInvIngresoTbxNombrePersona");
		idInvIngresoTbxCelPersona = (Textbox ) this.getFellow("idInvIngresoTbxCelPersona");
		
		 idInvIngresoTbxCodigo  = (Textbox ) this.getFellow("idInvIngresoTbxCodigo");
		 idInvIngresoTbxNumero  = (Textbox ) this.getFellow("idInvIngresoTbxNumero");
		 idInvIngresoLbxTipo  = (Listbox ) this.getFellow("idInvIngresoLbxTipo");
		 idInvIngresoDbxFecha  = (Datebox ) this.getFellow("idInvIngresoDbxFecha");
		 
		 //Detalle
		     idInvIngresoTbxCodigoProd = (Textbox ) this.getFellow("idInvIngresoTbxCodigoProd");
			 idInvIngresoTbxCantidadProd = (Intbox ) this.getFellow("idInvIngresoTbxCantidadProd");
			 idInvIngresoTbxTipoProd = (Textbox ) this.getFellow("idInvIngresoTbxTipoProd");
			 idInvIngresoTbxPrecioProd = (Textbox ) this.getFellow("idInvIngresoTbxPrecioProd");
			 idInvIngresoTbxDescripcion = (Textbox ) this.getFellow("idInvIngresoTbxDescripcion");
			 idInvIngresoBtnAgregarItem = (Button ) this.getFellow("idInvIngresoBtnAgregarItem");
			 idInvIngresoLbxDetalle = (Listbox ) this.getFellow("idInvIngresoLbxDetalle");
		

}

 	public void onConsultar(String criterio) {
		log.info("Ejecutando el metodo [ onConsultar ]... ");
		idInvIngresoLbxInvIngreso.getItems().clear();
		dtoSeleccionado = null;

		idInvIngresoFormulario.invalidate();
		idInvIngresoFormulario2.invalidate();
		idInvIngresoFormulario3.invalidate();
		idInvIngresoBtnCancelar.setDisabled(true);
		idInvIngresoBtnGuardar.setDisabled(true);
		idInvIngresoBtnEditar.setDisabled(true);
		idInvIngresoBtnBorrar.setDisabled(true);

		util.limpiarCampos(idInvIngresoFormulario);
		util.deshabilitarCampos(idInvIngresoFormulario);
		util.limpiarCampos(idInvIngresoFormulario2);
		util.deshabilitarCampos(idInvIngresoFormulario2);
		//util.limpiarCampos(idInvIngresoFormulario3);
		//util.deshabilitarCampos(idInvIngresoFormulario3);
		//util.deshabilitarCampos(idInvIngresoFormulario3);		
				idInvIngresoTbxCodigoProd.setDisabled(true);
				idInvIngresoTbxCantidadProd.setDisabled(true);
				util.limpiarCampos(idInvIngresoFormulario3);
				listaInvDetalleIngreso.clear();
				idInvIngresoLbxDetalle.getItems().clear();

		log.info("parmatro:" + criterio);

		InvIngreso dtoParmatro = new InvIngreso();
		dtoParmatro.setCodigo("%" + (criterio.equals(" ") ? "" : criterio) + "%");
		
		try {
			listaInvIngreso = (ArrayList<InvIngreso>) ParametrizacionFac.getFacade().obtenerListado("getInvIngreso", dtoParmatro);

			log.info("Hay " + listaInvIngreso.size() + " datos encontrados.");

			for (final InvIngreso dto : listaInvIngreso) {
				Listitem listItem = util.crearListitem(dto, dto.getEstado(), dto.getCodigo());
				listItem.addEventListener(Events.ON_CLICK, new EventListener() {
					public void onEvent(Event event) throws Exception {

						onSeleccionar(dto);
						// listItemSeleccionado=listItem;
						// listItem.setSelected(true);

					}
				});
				idInvIngresoLbxInvIngreso.appendChild(listItem);

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
 	
 	
 	
 	public void onConsultarProducto(String criterio) {
		log.info("Ejecutando el metodo [ onConsultarProducto ]... ");
		
		
		Producto producto = new Producto();
		producto.setCodigo(criterio);
		
		try {
		
			
			List <Producto> listaProducto = (ArrayList<Producto>) ParametrizacionFac.getFacade().obtenerListado("getProducto", producto);

			
			
			if(listaProducto.size()>0) {
				 //idInvIngresoTbxCodigoProd.set;
				 //idInvIngresoTbxCantidadProd;
				 idInvIngresoTbxTipoProd.setValue(listaProducto.get(0).getTipoProducto().getNombre());
				 idInvIngresoTbxTipoProd.setAttribute("getproducto", listaProducto.get(0));
				 idInvIngresoTbxPrecioProd.setValue(listaProducto.get(0).getPrecioVenta()+"");
				 idInvIngresoTbxDescripcion.setValue(listaProducto.get(0).getNombre());
				 idInvIngresoBtnAgregarItem.setDisabled(false);
			}else {
				log.info(" 0 datos encontrados.");
				
				 idInvIngresoTbxTipoProd.setValue("");
				 idInvIngresoTbxTipoProd.setAttribute("getproducto", null);
				 idInvIngresoTbxPrecioProd.setValue("");
				 idInvIngresoTbxDescripcion.setValue("");
				 idInvIngresoBtnAgregarItem.setDisabled(true);
			}

			

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
 	
 	public void	onAgregarProducto(){
 		
 		Producto dto=(Producto) idInvIngresoTbxTipoProd.getAttribute("getproducto");
 		ArrayList<String> arrayRequired = new ArrayList<String>();

		if (idInvIngresoTbxCantidadProd.getValue()==null) {arrayRequired.add("CAMPO CANTIDAD ES OBLIGATORIO");}
		
 		for(int i=0;  i < listaInvDetalleIngreso.size(); i++) {
 			
 			if(listaInvDetalleIngreso.get(i).getSecuencia_producto().getSecuencia().equals(dto.getSecuencia())) 
 			{
 				arrayRequired.add("PRODUCTO YA UNCLUIDO ");
 				i= listaInvDetalleIngreso.size()+1;
 			}
 			
 		}
 		
 		
 		
 		if (arrayRequired.size() > 0) {
			log.info("Excepciones por corregir");
			util.lanzarMensajeCamposFatantes("Excepciones por corregir", arrayRequired);

		} else {
 			Listitem listItem = util.crearListitemDetalle(dto, dto.getCodigo(), dto.getNombre(),idInvIngresoTbxCantidadProd.getValue().toString(),dto.getUltPrecioCompra().toString());
 			
 			Listcell cel=new Listcell();
 			Image img= new Image();
 			img.setSrc("imagen/close.png");
 			img.setWidth("16px");
 			img.setHeight("16px");
 			img.addEventListener(Events.ON_CLICK, new EventListener() {
				public void onEvent(Event event) throws Exception {

					idInvIngresoLbxDetalle.getChildren().remove(listItem);
					
					for(int i=0;  i < listaInvDetalleIngreso.size(); i++) {
			 			
			 			if(listaInvDetalleIngreso.get(i).getSecuencia_producto().getSecuencia().equals(dto.getSecuencia())) 
			 			{
			 				listaInvDetalleIngreso.remove(i);
			 				
			 				i= listaInvDetalleIngreso.size()+1;
			 			}
			 			
			 		}
					
					

				}
			});
 			
 			cel.appendChild(img);
 			listItem.appendChild(cel);
 			
 			
 			idInvIngresoLbxDetalle.appendChild(listItem);
 	 		
 	 		
 	 		
 	 		InvDetalleIngreso dtoDetalle = new InvDetalleIngreso();
 	 		dtoDetalle.setSecuencia_producto(dto);
 	 		InvIngreso dtoIngreso=new InvIngreso();
 	 		dtoIngreso.setSecuencia(pk);
 	 		dtoDetalle.setSecuencia_ingreso(dtoIngreso);
 	 		dtoDetalle.setCantidad(idInvIngresoTbxCantidadProd.getValue());
 	 		dtoDetalle.setPrecio(dto.getUltPrecioCompra());
 	 		listaInvDetalleIngreso.add(dtoDetalle);
 	 		
 	 		idInvIngresoTbxCantidadProd.setValue(null);
 	 	
 	 	} 
 		
 	}

public void onGuardar() {
		log.info("Ejecutando el metodo [onGuardar ]... ");
		String op = idInvIngresoTbxOperacion.getValue();
		ArrayList<String> arrayRequired = new ArrayList<String>();

		if (idInvIngresoTbxCodigo.getValue().isEmpty()) {arrayRequired.add("CODIGO");}
//		if (idInvIngresoTbxPersona.getValue().isEmpty()) {arrayRequired.add("PROVEEDOR");}
		if (listaInvDetalleIngreso.size()<1) {arrayRequired.add("DEBE INGRESAR MINIMO UN PRODUCTO");}

		
		
		
		if (arrayRequired.size() > 0) {
			log.info("Faltan campos obligatorios por llenar");
			util.lanzarMensajeCamposFatantes("faltan campos obligatorios por llenar", arrayRequired);

		} else {
			InvIngreso dto = new InvIngreso();
			dto.setSecuencia(pk);
			
			//se guarda la persona del bambox
			if(!idInvIngresoTbxPersona.getValue().isEmpty()  & !idInvIngresoBandboxPersona.getValue().isEmpty() ) {
			Persona dtoPersona =new Persona();
			dtoPersona.setSecuencia(new Long(idInvIngresoTbxPersona.getValue()));
			//dto.setSecuenciaPersona(dtoPersona);
			dto.setSecuenciaPersona((Persona) idInvIngresolistboxBandboxPersona.getSelectedItem().getAttribute("persona"));
			}
			
			if(idInvIngresoTbxOperacion.getValue().equals("U") & idInvIngresoBandboxPersona.getValue().isEmpty()) {
				dto.setSecuenciaPersona(null);
				
			}
			

			

			
			
			
			
			dto.setCodigo(idInvIngresoTbxCodigo.getValue());
			dto.setNumero(idInvIngresoTbxNumero.getValue());
			dto.setFecha(idInvIngresoDbxFecha.getValue());
			dto.setEstado("A");
			dto.setTipo(idInvIngresoLbxTipo.getSelectedItem().getValue());
			dto.setDescripcion(idInvIngresoTbxDescripcion.getValue());
			dto.setFechaCreacion(idInvIngresoDbxFecha.getValue());
			
			
			log.info("Parametro: " + dto.toString());
			
			
			
			
			
			if (idInvIngresoTbxOperacion.getValue().equals("U")) {
				log.info(" metodo [actualizar]");
				dto.setSecuencia(new Long(idInvIngresoTbxSecuencia.getValue()));
				

				try {
					ParametrizacionFac.getFacade().insertarRegistro("putInvIngreso", dto);
					util.deshabilitarCampos(idInvIngresoFormulario);
					util.deshabilitarCampos(idInvIngresoFormulario2);
					//util.habilitarCampos(idInvIngresoFormulario3);
					idInvIngresoTbxCodigoProd.setDisabled(false);
					idInvIngresoTbxCantidadProd.setDisabled(false);
					util.limpiarCampos(idInvIngresoFormulario3);
					onConsultar("");
					
					this.onSeleccionar(dto);

					log.info("Actualizado");
					util.lanzarMensaje("Actualizado");
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (op.equals("N")) {
				log.info("metodo [Guardar]");
				try {
					dto.setFechaCreacion(new Date());
					dto.setSecuenciaUsuario(usuario.getSecuencia());
					dto.setFechaCreacion(new Date());
					ParametrizacionFac.getFacade().insertarRegistro("postInvIngreso", dto);
					
					/*
					 * gestionar las listas de detalle
					 */
					
					if(listaInvDetalleIngreso.size()>0) {
						
					for(int i=0;  i < listaInvDetalleIngreso.size(); i++) {					
					ParametrizacionFac.getFacade().insertarRegistro("postInvDetalleIngreso", listaInvDetalleIngreso.get(i));
					
					}
					
					}else {
						log.info("0 detalles");
					}
					
					util.deshabilitarCampos(idInvIngresoFormulario);
					util.deshabilitarCampos(idInvIngresoFormulario2);
					util.deshabilitarCampos(idInvIngresoFormulario3);
					onConsultar("");
					this.onSeleccionar(dto);
					log.info("Guardado");
					util.lanzarMensaje("Guardado");
				} catch (Exception e) {
					util.lanzarMensaje("Error:::> " + e);
					log.info("Error:::> " + e);
					e.printStackTrace();
				}
				idInvIngresoBtnGuardar.setDisabled(true);
				idInvIngresoBtnCancelar.setDisabled(true);
				idInvIngresoBtnNuevo.setDisabled(false);
			}
		}
	}

	public void onSeleccionar(InvIngreso dto) {
		log.info(" Ejecutando el metodo [ onSeleccionar ]... ");

		dtoSeleccionado = dto;

		util.deshabilitarCampos(idInvIngresoFormulario);
		util.limpiarCampos(idInvIngresoFormulario);
		util.deshabilitarCampos(idInvIngresoFormulario2);
		util.limpiarCampos(idInvIngresoFormulario2);
		//util.deshabilitarCampos(idInvIngresoFormulario3);
		//util.deshabilitarCampos(idInvIngresoFormulario3);		
				idInvIngresoTbxCodigoProd.setDisabled(true);
				idInvIngresoTbxCantidadProd.setDisabled(true);
				util.limpiarCampos(idInvIngresoFormulario3);
				listaInvDetalleIngreso.clear();
				idInvIngresoLbxDetalle.getItems().clear();
		//util.limpiarCampos(idInvIngresoFormulario3);

		idInvIngresoBtnNuevo.setDisabled(false);
		idInvIngresoBtnGuardar.setDisabled(true);
		idInvIngresoBtnCancelar.setDisabled(true);
		idInvIngresoBtnEditar.setDisabled(false);
		idInvIngresoBtnBorrar.setDisabled(false);
		// logica
		idInvIngresoTbxSecuencia.setValue(dto.getSecuencia() + "");
		 idInvIngresoTbxCodigo.setValue(dto.getCodigo());
		 idInvIngresoTbxNumero.setValue(dto.getNumero());
		 
		idInvIngresoDbxFecha.setValue(dto.getFecha());
		idInvIngresoLbxTipo.setSelectedIndex(dto.getTipo().equals("CP")?0:dto.getTipo().equals("OT")?1:2);
		idInvIngresoTbxDescripcion.setValue(dto.getDescripcion() + "");
		
		//BandboxPersona
		if(dto.getSecuenciaPersona()!=null) {
				idInvIngresoTbxPersona.setValue(dto.getSecuenciaPersona().getSecuencia()+"");  
				idInvIngresoBandboxPersona.setValue(dto.getSecuenciaPersona().getNombres()+""); 
				idInvIngresoTbxNombrePersona.setValue(dto.getSecuenciaPersona().getNombres()+"");   
				idInvIngresoTbxCelPersona.setValue(dto.getSecuenciaPersona().getTelefono()+"");   
				
				Listitem listItemPersona = new Listitem();
				listItemPersona.setValue(dto.getSecuenciaPersona().getSecuencia() + "");
				listItemPersona.setLabel(dto.getSecuenciaPersona().getNombres()+" "+dto.getSecuenciaPersona().getApellidos());
				listItemPersona.setAttribute("persona", dto.getSecuenciaPersona());
				listItemPersona.setSelected(true);
				
				
				//listItem.getAttribute("persona");
				idInvIngresolistboxBandboxPersona.appendChild(listItemPersona);
				
		}
				/*
				 * se obtiene los detalles si existen
				 */
	
	InvDetalleIngreso dtoInvDetalleIngreso =new InvDetalleIngreso();
	dtoInvDetalleIngreso.setSecuencia_ingreso(dto);
	
	try {
		 listaInvDetalleIngreso = (ArrayList<InvDetalleIngreso>) ParametrizacionFac.getFacade().obtenerListado("getDetalleInvIngreso", dtoInvDetalleIngreso);
	
		log.info("detalles encontrados "+listaInvDetalleIngreso.size());
		idInvIngresoLbxDetalle.getItems().clear();
		
		if(listaInvDetalleIngreso.size()>0) {
			
			for(InvDetalleIngreso detalle:listaInvDetalleIngreso)
			{
				
			Listitem listItem = util.crearListitemDetalle(detalle, detalle.getSecuencia_producto().getCodigo(), detalle.getSecuencia_producto().getNombre(),detalle.getCantidad()+"",detalle.getPrecio().toString());
			Listcell cel=new Listcell();
 			Image img= new Image();
 			img.setSrc("imagen/close.png");
 			img.setWidth("16px");
 			img.setHeight("16px");
 			img.addEventListener(Events.ON_CLICK, new EventListener() {
				public void onEvent(Event event) throws Exception {
					util.lanzarMensaje("NO SE PUEDE EDITAR EL DETALLE");
//					idInvIngresoLbxDetalle.getChildren().remove(listItem);
//					listaInvDetalleIngreso.remove(dto);

				}
			});
 			cel.appendChild(img);
 			listItem.appendChild(cel);
 			
			idInvIngresoLbxDetalle.appendChild(listItem);
			
			}
		}
	
	} catch (Exception e) {
		// TODO Auto-generated catch block
		//e.printStackTrace();
		log.info("error al consultar el detalle");
	}

		
		
	}

public void onNuevo() {
		log.info("Ejecutando el metodo [ onNuevo]... ");

		dtoSeleccionado = null;

		idInvIngresoBtnNuevo.setDisabled(true);
		util.habilitarCampos(idInvIngresoFormulario);
		util.limpiarCampos(idInvIngresoFormulario);
		
		//util.habilitarCampos(idInvIngresoFormulario2);
		idInvIngresoBandboxPersona.setDisabled(false);
		util.limpiarCampos(idInvIngresoFormulario2);
		
		//util.habilitarCampos(idInvIngresoFormulario3);
		idInvIngresoTbxCodigoProd.setDisabled(false);
		idInvIngresoTbxCantidadProd.setDisabled(false);
		util.limpiarCampos(idInvIngresoFormulario3);
		listaInvDetalleIngreso.clear();
		idInvIngresoLbxDetalle.getItems().clear();

		idInvIngresoTbxOperacion.setValue("N");
		
		idInvIngresoFormulario.invalidate();
		idInvIngresoFormulario2.invalidate();
		idInvIngresoFormulario3.invalidate();

		idInvIngresoBtnCancelar.setDisabled(false);
		idInvIngresoBtnEditar.setDisabled(true);
		idInvIngresoBtnBorrar.setDisabled(true);
		idInvIngresoBtnGuardar.setDisabled(false);
		
		
		idInvIngresoTbxCodigo.setValue(util.getCodigoCompuesto());
		

	}

	public void onEditar() {
		log.info("Ejecutando el metodo [ onEditar ]...");
		util.habilitarCampos(idInvIngresoFormulario);
		util.habilitarCampos(idInvIngresoFormulario2);
		//util.habilitarCampos(idInvIngresoFormulario3);
				idInvIngresoTbxCodigoProd.setDisabled(false);
				idInvIngresoTbxCantidadProd.setDisabled(false);
				//util.limpiarCampos(idInvIngresoFormulario3);
				//listaInvDetalleIngreso.clear();
				//idInvIngresoLbxDetalle.getItems().clear();
		
		/* default en modo edicion */

		idInvIngresoBtnBorrar.setDisabled(true);
		idInvIngresoBtnEditar.setDisabled(true);
		idInvIngresoBtnGuardar.setDisabled(false);
		idInvIngresoBtnCancelar.setDisabled(false);
		idInvIngresoTbxOperacion.setValue("U");
		
	}

	public void onBorrar() {
		log.info("metodo [onBorrar]");

		try {
			InvDetalleIngreso dtoDetalle = new InvDetalleIngreso();
			dtoDetalle.setSecuencia_ingreso(dtoSeleccionado);
			
			ParametrizacionFac.getFacade().borrarRegistro("deleteDetalleInvIngreso", dtoDetalle);

			ParametrizacionFac.getFacade().borrarRegistro("deleteInvIngreso", dtoSeleccionado);
			util.lanzarMensaje("Se elimino ");
			log.info(" Se elimino ");

			util.limpiarCampos(idInvIngresoFormulario);
			util.limpiarCampos(idInvIngresoFormulario2);
			util.limpiarCampos(idInvIngresoFormulario3);
			
			onConsultar("");

		}
catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void onCancelar() {
		log.info("Ejecutando el metodo [ onCancelar ]...");

		util.limpiarCampos(idInvIngresoFormulario);
		util.deshabilitarCampos(idInvIngresoFormulario);
		util.deshabilitarCampos(idInvIngresoFormulario2);
		util.limpiarCampos(idInvIngresoFormulario2);
		
		//util.deshabilitarCampos(idInvIngresoFormulario3);		
		idInvIngresoTbxCodigoProd.setDisabled(true);
		idInvIngresoTbxCantidadProd.setDisabled(true);
		util.limpiarCampos(idInvIngresoFormulario3);
		listaInvDetalleIngreso.clear();
		idInvIngresoLbxDetalle.getItems().clear();
		

		idInvIngresoBtnGuardar.setDisabled(true);
		idInvIngresoBtnCancelar.setDisabled(true);
		idInvIngresoBtnEditar.setDisabled(true);
		idInvIngresoBtnBorrar.setDisabled(true);
		idInvIngresoBtnNuevo.setDisabled(false);

	}
	
	public void llenarBandboxPersona(String criterio) {
		List<Persona> listaPersona;
		Persona persona = new Persona();
		persona.setNombres("%" + (criterio.equals(" ") ? "" : criterio) + "%");		
		persona.setEtiquetaProveedor("S");
		log.info("parmatro: " + persona.getNombres());
		
		try {			
			listaPersona = (ArrayList<Persona>) ParametrizacionFac.getFacade().obtenerListado("getPersona", persona);
			log.info("Hay " + listaPersona.size() + " datos encontradas.");						
			idInvIngresolistboxBandboxPersona.getItems().clear();
				for (final Persona dto : listaPersona) {
					log.info("persona: " + dto.getNombres());
					Listitem listItem = new Listitem();
					listItem.setValue(dto.getSecuencia() + "");
					listItem.setLabel(dto.getNombres()+" "+dto.getApellidos());
					listItem.setAttribute("persona", dto);
					listItem.addEventListener(Events.ON_CLICK, new EventListener() {
						public void onEvent(Event event) throws Exception {

							idInvIngresoTbxNombrePersona.setValue(dto.getNombres());
							idInvIngresoTbxCelPersona.setValue(dto.getTelefono());
							

						}
					});
					
					
					
					
					
					idInvIngresolistboxBandboxPersona.appendChild(listItem);
				}
			
		} catch (Exception e) {
			// TODO: handle exception
			log.info("");
		}
	}
	
	
}
 