package com.mochila.inv.action;

import java.io.IOException;
import java.text.SimpleDateFormat;
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
import org.zkoss.zul.Doublebox;
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
import com.mochila.inv.dto.InvDetalleSalida;
import com.mochila.inv.dto.InvSalida;
import com.mochila.inv.dto.Producto;

public class InvSalidaAction extends Borderlayout implements AfterCompose {
	
	private static final long serialVersionUID = 1L;

	protected static Logger log = Logger.getLogger(InvSalidaAction.class);

	Textbox idInvSalidaTbxBusqueda;
	Listbox idInvSalidaLbxInvSalida;
	Toolbarbutton idInvSalidaBtnNuevo;
	Toolbarbutton idInvSalidaBtnEditar;
	Toolbarbutton idInvSalidaBtnGuardar;
	Toolbarbutton idInvSalidaBtnBorrar;
	Toolbarbutton idInvSalidaBtnCancelar;
	Rows idInvSalidaFormulario;
	Rows idInvSalidaFormulario2;
	Rows idInvSalidaFormulario3;
	Textbox idInvSalidaTbxOperacion;
	Textbox idInvSalidaTbxSecuencia;
	Textbox idInvSalidaTbxNombre;
	Textbox idInvSalidaTbxNombrePersona;
	Textbox idInvSalidaTbxCelPersona;
	
	Textbox idInvSalidaTbxCodigo;
	Intbox idInvSalidaTbxDescuento;
	Intbox idInvSalidaTbxImpuesto;
	Listbox idInvSalidaLbxTipo;
	Listbox idInvSalidaLbxEstado;
	Datebox idInvSalidaDbxFecha;
	Doublebox idInvSalidaTbxTotal;
	
	
	
	Textbox idInvSalidaTbxPersona;
	Bandbox idInvSalidaBandboxPersona;
	Listbox idInvSalidalistboxBandboxPersona;
	
	
	//Detalle
	Textbox idInvSalidaTbxCodigoProd;
	Intbox idInvSalidaTbxCantidadProd;
	Textbox idInvSalidaTbxTipoProd;
	Textbox idInvSalidaTbxPrecioProd;
	Textbox idInvSalidaTbxDescripcion;
	Button idInvSalidaBtnAgregarItem;
	Button idInvSalidaBtnBuscarItem;
	Listbox idInvSalidaLbxDetalle;
	

	UtilZKProcesosComunesHelper util = new UtilZKProcesosComunesHelper();
	InvSalida dtoSeleccionado = new InvSalida();
	Usuario usuario = new Usuario();
	ArrayList<InvSalida> listaInvSalida = new ArrayList<InvSalida>();
	ArrayList<InvDetalleSalida> listaInvDetalleSalida = new ArrayList<InvDetalleSalida>();
	
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
		idInvSalidaTbxBusqueda = (Textbox) this.getFellow("idInvSalidaTbxBusqueda");
														
		idInvSalidaLbxInvSalida = (Listbox) this.getFellow("idInvSalidaLbxInvSalida");
		idInvSalidaBtnNuevo = (Toolbarbutton) this.getFellow("idInvSalidaBtnNuevo");
		idInvSalidaBtnEditar = (Toolbarbutton) this.getFellow("idInvSalidaBtnEditar");
		idInvSalidaBtnGuardar = (Toolbarbutton) this.getFellow("idInvSalidaBtnGuardar");
		idInvSalidaBtnBorrar = (Toolbarbutton) this.getFellow("idInvSalidaBtnBorrar");
		idInvSalidaBtnCancelar = (Toolbarbutton) this.getFellow("idInvSalidaBtnCancelar");
		idInvSalidaFormulario = (Rows) this.getFellow("idInvSalidaFormulario");
		idInvSalidaFormulario2 = (Rows) this.getFellow("idInvSalidaFormulario2");
		idInvSalidaFormulario3 = (Rows) this.getFellow("idInvSalidaFormulario3");
		idInvSalidaTbxOperacion = (Textbox) this.getFellow("idInvSalidaTbxOperacion");
		idInvSalidaTbxSecuencia = (Textbox ) this.getFellow("idInvSalidaTbxSecuencia");
		
		
		idInvSalidaTbxDescripcion = (Textbox ) this.getFellow("idInvSalidaTbxDescripcion");
		
		//BandboxPersona
		idInvSalidaTbxPersona  = (Textbox ) this.getFellow("idInvSalidaTbxPersona");
		idInvSalidaBandboxPersona = (Bandbox ) this.getFellow("idInvSalidaBandboxPersona");
		idInvSalidalistboxBandboxPersona = (Listbox ) this.getFellow("idInvSalidalistboxBandboxPersona");		
		idInvSalidaTbxNombrePersona = (Textbox ) this.getFellow("idInvSalidaTbxNombrePersona");
		idInvSalidaTbxCelPersona = (Textbox ) this.getFellow("idInvSalidaTbxCelPersona");
		
		 idInvSalidaTbxCodigo  = (Textbox ) this.getFellow("idInvSalidaTbxCodigo");
		 idInvSalidaTbxDescuento = (Intbox ) this.getFellow("idInvSalidaTbxDescuento");
		 idInvSalidaTbxImpuesto = (Intbox ) this.getFellow("idInvSalidaTbxImpuesto");
		 idInvSalidaLbxTipo  = (Listbox ) this.getFellow("idInvSalidaLbxTipo");
		 idInvSalidaLbxEstado  = (Listbox ) this.getFellow("idInvSalidaLbxEstado");
		 idInvSalidaDbxFecha  = (Datebox ) this.getFellow("idInvSalidaDbxFecha");
		 idInvSalidaTbxTotal = (Doublebox )  this.getFellow("idInvSalidaTbxTotal");
		 
		 //Detalle
		     idInvSalidaTbxCodigoProd = (Textbox ) this.getFellow("idInvSalidaTbxCodigoProd");
			 idInvSalidaTbxCantidadProd = (Intbox ) this.getFellow("idInvSalidaTbxCantidadProd");
			 idInvSalidaTbxTipoProd = (Textbox ) this.getFellow("idInvSalidaTbxTipoProd");
			 idInvSalidaTbxPrecioProd = (Textbox ) this.getFellow("idInvSalidaTbxPrecioProd");
			 idInvSalidaTbxDescripcion = (Textbox ) this.getFellow("idInvSalidaTbxDescripcion");
			 idInvSalidaBtnAgregarItem = (Button ) this.getFellow("idInvSalidaBtnAgregarItem");
			 idInvSalidaLbxDetalle = (Listbox ) this.getFellow("idInvSalidaLbxDetalle");
			 idInvSalidaBtnBuscarItem= (Button) this.getFellow("idInvSalidaBtnBuscarItem");
			

}

 	public void onConsultar(String criterio) {
		log.info("Ejecutando el metodo [ onConsultar ]... ");
		idInvSalidaLbxInvSalida.getItems().clear();
		dtoSeleccionado = null;

		idInvSalidaFormulario.invalidate();
		idInvSalidaFormulario2.invalidate();
		idInvSalidaFormulario3.invalidate();
		idInvSalidaBtnCancelar.setDisabled(true);
		idInvSalidaBtnGuardar.setDisabled(true);
		idInvSalidaBtnEditar.setDisabled(true);
		idInvSalidaBtnBorrar.setDisabled(true);

		util.limpiarCampos(idInvSalidaFormulario);
		util.deshabilitarCampos(idInvSalidaFormulario);
		util.limpiarCampos(idInvSalidaFormulario2);
		util.deshabilitarCampos(idInvSalidaFormulario2);
		//util.limpiarCampos(idInvSalidaFormulario3);
		//util.deshabilitarCampos(idInvSalidaFormulario3);
		//util.deshabilitarCampos(idInvSalidaFormulario3);		
				idInvSalidaTbxCodigoProd.setDisabled(true);
				idInvSalidaBtnBuscarItem.setDisabled(true);
				idInvSalidaTbxCantidadProd.setDisabled(true);
				util.limpiarCampos(idInvSalidaFormulario3);
				listaInvDetalleSalida.clear();
				idInvSalidaLbxDetalle.getItems().clear();

		log.info("parmatro:" + criterio);

		InvSalida dtoParmatro = new InvSalida();
		dtoParmatro.setCodigo("%" + (criterio.equals(" ") ? "" : criterio) + "%");
		
		try {
			listaInvSalida = (ArrayList<InvSalida>) ParametrizacionFac.getFacade().obtenerListado("getInvSalida", dtoParmatro);

			log.info("Hay " + listaInvSalida.size() + " datos encontrados.");

			for (final InvSalida dto : listaInvSalida) {
				Listitem listItem = util.crearListitem(dto, dto.getEstado(), dto.getCodigo());
				listItem.addEventListener(Events.ON_CLICK, new EventListener() {
					public void onEvent(Event event) throws Exception {

						onSeleccionar(dto);
						// listItemSeleccionado=listItem;
						// listItem.setSelected(true);

					}
				});
				idInvSalidaLbxInvSalida.appendChild(listItem);

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
				 //idInvSalidaTbxCodigoProd.set;
				 //idInvSalidaTbxCantidadProd;
				 idInvSalidaTbxTipoProd.setValue(listaProducto.get(0).getTipoProducto().getNombre());
				 idInvSalidaTbxTipoProd.setAttribute("getproducto", listaProducto.get(0));
				 idInvSalidaTbxPrecioProd.setValue(listaProducto.get(0).getPrecioVenta()+"");
				 idInvSalidaTbxDescripcion.setValue(listaProducto.get(0).getNombre());
				 idInvSalidaBtnAgregarItem.setDisabled(false);
			}else {
				log.info(" 0 datos encontrados.");
				
				 idInvSalidaTbxTipoProd.setValue("");
				 idInvSalidaTbxTipoProd.setAttribute("getproducto", null);
				 idInvSalidaTbxPrecioProd.setValue("");
				 idInvSalidaTbxDescripcion.setValue("");
				 idInvSalidaBtnAgregarItem.setDisabled(true);
			}

			

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
 	
 	public void calcularTotal() {
 		log.info("metodo [calcularTotal()]");
 	Double total=0.0;	
 	Double totalDescuento=0.0;
 	Double totalImpuestoo=0.0;
 		
for(int i=0;  i < listaInvDetalleSalida.size(); i++) {
 			
 			total= total+listaInvDetalleSalida.get(i).getPrecio();
 			
 		}

		if(idInvSalidaTbxDescuento.getValue()!=null) 
		{
			totalDescuento=(((total*idInvSalidaTbxDescuento.getValue())/100));
		}
		
		if(idInvSalidaTbxImpuesto.getValue()!=null) 
		{
			totalImpuestoo=(((total*idInvSalidaTbxImpuesto.getValue())/100));
		}
		
		idInvSalidaTbxTotal.setValue((total-totalDescuento)+totalImpuestoo);


 	}
 	

 	
 	public void	onAgregarProducto() throws Exception{
 		
 		Producto dto=(Producto) idInvSalidaTbxTipoProd.getAttribute("getproducto");
 		ArrayList<String> arrayRequired = new ArrayList<String>();

		if (idInvSalidaTbxCantidadProd.getValue()==null) {arrayRequired.add("CAMPO CANTIDAD ES OBLIGATORIO");}
		
 		for(int i=0;  i < listaInvDetalleSalida.size(); i++) {
 			
 			if(listaInvDetalleSalida.get(i).getSecuencia_producto().getSecuencia().equals(dto.getSecuencia())) 
 			{
 				arrayRequired.add("PRODUCTO YA UNCLUIDO ");
 				i= listaInvDetalleSalida.size()+1;
 			}
 			
 		}
 		
 		
 		/*
 		 * se  valida la existencia del producto que se intenta agregar al detalle
 		 */
 		
 		Long existencia= (Long) ParametrizacionFac.getFacade().obtenerRegistro("invExistenciaProducto",dto);

 		 if(existencia !=null ) {
 			 
 			 if(existencia<idInvSalidaTbxCantidadProd.getValue()) {
 				 
 				arrayRequired.add(" PRODUCTOS INSUFICIENTES");
 			 }
 		 }
 		
 		
 		
 		if (arrayRequired.size() > 0) {
			log.info("Excepciones por corregir");
			util.lanzarMensajeCamposFatantes("Excepciones por corregir", arrayRequired);

		} else {
 			Listitem listItem = util.crearListitemDetalle(dto, dto.getCodigo(), dto.getNombre(),idInvSalidaTbxCantidadProd.getValue().toString(),dto.getPrecioVenta().toString());
 			
 			Listcell cel=new Listcell();
 			Image img= new Image();
 			img.setSrc("imagen/close.png");
 			img.setWidth("16px");
 			img.setHeight("16px");
 			cel.appendChild(img);
 			img.addEventListener(Events.ON_CLICK, new EventListener() {
				public void onEvent(Event event) throws Exception {

					idInvSalidaLbxDetalle.getChildren().remove(listItem);
					
					for(int i=0;  i < listaInvDetalleSalida.size(); i++) {
			 			
			 			if(listaInvDetalleSalida.get(i).getSecuencia_producto().getSecuencia().equals(dto.getSecuencia())) 
			 			{
			 				listaInvDetalleSalida.remove(i);
			 				
			 				i= listaInvDetalleSalida.size()+1;
			 			}
			 			
			 		}
					
					calcularTotal();

				}
			});
 			listItem.appendChild(cel);
 			
 			
 			idInvSalidaLbxDetalle.appendChild(listItem);
 			
 	 		
 	 		
 	 		InvDetalleSalida dtoDetalle = new InvDetalleSalida();
 	 		dtoDetalle.setSecuencia_producto(dto);
 	 		InvSalida dtoIngreso=new InvSalida();
 	 		dtoIngreso.setSecuencia(pk);
 	 		dtoDetalle.setSecuencia_ingreso(dtoIngreso);
 	 		dtoDetalle.setCantidad(idInvSalidaTbxCantidadProd.getValue());
 	 		dtoDetalle.setPrecio(dto.getUltPrecioCompra());
 	 		listaInvDetalleSalida.add(dtoDetalle);
 	 		
 	 		idInvSalidaTbxCantidadProd.setValue(null);
 	 	
 	 	} 
 		calcularTotal();
 	}

public void onGuardar() {
		log.info("Ejecutando el metodo [onGuardar ]... ");
		String op = idInvSalidaTbxOperacion.getValue();
		ArrayList<String> arrayRequired = new ArrayList<String>();

		if (idInvSalidaTbxCodigo.getValue().isEmpty()) {arrayRequired.add("CODIGO");}
//		if (idInvSalidaTbxPersona.getValue().isEmpty()) {arrayRequired.add("PROVEEDOR");}
		if (listaInvDetalleSalida.size()<1) {arrayRequired.add("DEBE INGRESAR MINIMO UN PRODUCTO");}
		if(idInvSalidaLbxTipo.getSelectedItem().getValue().equals("SD") && idInvSalidaTbxPersona.getValue().isEmpty()) {arrayRequired.add("DEBE INGRESAR UN DISTRIBUIDOR / CLIENTE PARA ESTE TIPO DE ORDEN");}

		
		
		
		if (arrayRequired.size() > 0) {
			log.info("Faltan campos obligatorios por llenar");
			util.lanzarMensajeCamposFatantes("faltan campos obligatorios por llenar", arrayRequired);

		} else {
			InvSalida dto = new InvSalida();
			dto.setSecuencia(pk);
			
			//se guarda la persona del bambox
			if(!idInvSalidaTbxPersona.getValue().isEmpty()  & !idInvSalidaBandboxPersona.getValue().isEmpty() ) {
			Persona dtoPersona =new Persona();
			dtoPersona.setSecuencia(new Long(idInvSalidaTbxPersona.getValue()));
			//dto.setSecuenciaPersona(dtoPersona);
			dto.setSecuenciaPersona((Persona) idInvSalidalistboxBandboxPersona.getSelectedItem().getAttribute("persona"));
			}
			
			if(idInvSalidaTbxOperacion.getValue().equals("U") & idInvSalidaBandboxPersona.getValue().isEmpty()) {
				dto.setSecuenciaPersona(null);
				
			}
			

			

			
			
			
			
			dto.setCodigo(idInvSalidaTbxCodigo.getValue());
			dto.setDescuento(idInvSalidaTbxDescuento.getValue()!=null?idInvSalidaTbxDescuento.getValue():0);
			dto.setImpuesto(idInvSalidaTbxImpuesto.getValue()!=null?idInvSalidaTbxImpuesto.getValue():0);
			dto.setFecha(idInvSalidaDbxFecha.getValue());
			dto.setEstado("A");
			dto.setTipo(idInvSalidaLbxTipo.getSelectedItem().getValue());
			dto.setEstado(idInvSalidaLbxEstado.getSelectedItem().getValue());
			dto.setDescripcion( idInvSalidaTbxDescripcion.getValue());
			dto.setFecha(idInvSalidaDbxFecha.getValue());
			calcularTotal();
			dto.setTotal(idInvSalidaTbxTotal.getValue());
			
			
			log.info("Parametro: " + dto.toString());
			
			
			
			
			
			if (idInvSalidaTbxOperacion.getValue().equals("U")) {
				log.info(" metodo [actualizar]");
				dto.setSecuencia(new Long(idInvSalidaTbxSecuencia.getValue()));
				
				

				try {
					ParametrizacionFac.getFacade().insertarRegistro("putInvSalida", dto);
					util.deshabilitarCampos(idInvSalidaFormulario);
					util.deshabilitarCampos(idInvSalidaFormulario2);
					//util.habilitarCampos(idInvSalidaFormulario3);
					idInvSalidaTbxCodigoProd.setDisabled(false);
					idInvSalidaBtnBuscarItem.setDisabled(false);
					idInvSalidaTbxCantidadProd.setDisabled(false);
					util.limpiarCampos(idInvSalidaFormulario3);
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
					
					dto.setSecuenciaUsuario(usuario.getSecuencia());
					dto.setFechaCreacion(new Date());
					ParametrizacionFac.getFacade().insertarRegistro("postInvSalida", dto);
					
					/*
					 * gestionar las listas de detalle
					 */
					
					if(listaInvDetalleSalida.size()>0) {
						
					for(int i=0;  i < listaInvDetalleSalida.size(); i++) {					
					ParametrizacionFac.getFacade().insertarRegistro("postInvDetalleSalida", listaInvDetalleSalida.get(i));
					
					}
					
					}else {
						log.info("0 detalles");
					}
					
					util.deshabilitarCampos(idInvSalidaFormulario);
					util.deshabilitarCampos(idInvSalidaFormulario2);
					util.deshabilitarCampos(idInvSalidaFormulario3);
					onConsultar("");
					this.onSeleccionar(dto);
					log.info("Guardado");
					util.lanzarMensaje("Guardado");
				} catch (Exception e) {
					util.lanzarMensaje("Error:::> " + e);
					log.info("Error:::> " + e);
					e.printStackTrace();
				}
				idInvSalidaBtnGuardar.setDisabled(true);
				idInvSalidaBtnCancelar.setDisabled(true);
				idInvSalidaBtnNuevo.setDisabled(false);
			}
		}
	}

	public void onSeleccionar(InvSalida dto) {
		log.info(" Ejecutando el metodo [ onSeleccionar ]... ");

		dtoSeleccionado = dto;

		util.deshabilitarCampos(idInvSalidaFormulario);
		util.limpiarCampos(idInvSalidaFormulario);
		util.deshabilitarCampos(idInvSalidaFormulario2);
		util.limpiarCampos(idInvSalidaFormulario2);
		//util.deshabilitarCampos(idInvSalidaFormulario3);
		//util.deshabilitarCampos(idInvSalidaFormulario3);		
				idInvSalidaTbxCodigoProd.setDisabled(true);
				idInvSalidaBtnBuscarItem.setDisabled(true);
				idInvSalidaTbxCantidadProd.setDisabled(true);
				util.limpiarCampos(idInvSalidaFormulario3);
				listaInvDetalleSalida.clear();
				idInvSalidaLbxDetalle.getItems().clear();
		//util.limpiarCampos(idInvSalidaFormulario3);

		idInvSalidaBtnNuevo.setDisabled(false);
		idInvSalidaBtnGuardar.setDisabled(true);
		idInvSalidaBtnCancelar.setDisabled(true);
		idInvSalidaBtnEditar.setDisabled(false);
		idInvSalidaBtnBorrar.setDisabled(false);
		// logica
		idInvSalidaTbxSecuencia.setValue(dto.getSecuencia() + "");
		 idInvSalidaTbxCodigo.setValue(dto.getCodigo());
		 idInvSalidaTbxDescuento.setValue(dto.getDescuento());
		 idInvSalidaTbxImpuesto.setValue(dto.getImpuesto());
		 
		idInvSalidaDbxFecha.setValue(dto.getFecha());
		idInvSalidaLbxTipo.setSelectedIndex(
		dto.getTipo().equals("SD")?0:
			dto.getTipo().equals("SE")?1:null);
		
		idInvSalidaLbxEstado.setSelectedIndex(
				dto.getEstado().equals("CR")?0:
					dto.getEstado().equals("AC")?1:
						dto.getEstado().equals("VA")?2:
							dto.getEstado().equals("AT")?3:
								dto.getEstado().equals("DE")?4:
									dto.getEstado().equals("EN")?5:
										dto.getEstado().equals("ET")?6:null);
		
		
		idInvSalidaTbxDescripcion.setValue(dto.getDescripcion() + "");
		
		//BandboxPersona
		if(dto.getSecuenciaPersona()!=null) {
				idInvSalidaTbxPersona.setValue(dto.getSecuenciaPersona().getSecuencia()+"");  
				idInvSalidaBandboxPersona.setValue(dto.getSecuenciaPersona().getNombres()+""); 
				idInvSalidaTbxNombrePersona.setValue(dto.getSecuenciaPersona().getNombres()+"");   
				idInvSalidaTbxCelPersona.setValue(dto.getSecuenciaPersona().getTelefono()+"");   
				
				Listitem listItemPersona = new Listitem();
				listItemPersona.setValue(dto.getSecuenciaPersona().getSecuencia() + "");
				listItemPersona.setLabel(dto.getSecuenciaPersona().getNombres()+" "+dto.getSecuenciaPersona().getApellidos());
				listItemPersona.setAttribute("persona", dto.getSecuenciaPersona());
				listItemPersona.setSelected(true);
				
				
				//listItem.getAttribute("persona");
				idInvSalidalistboxBandboxPersona.appendChild(listItemPersona);
				
		}
				/*
				 * se obtiene los detalles si existen
				 */
	
	InvDetalleSalida dtoInvDetalleSalida =new InvDetalleSalida();
	dtoInvDetalleSalida.setSecuencia_ingreso(dto);
	
	try {
		 listaInvDetalleSalida = (ArrayList<InvDetalleSalida>) ParametrizacionFac.getFacade().obtenerListado("getDetalleInvSalida", dtoInvDetalleSalida);
	
		log.info("detalles encontrados "+listaInvDetalleSalida.size());
		idInvSalidaLbxDetalle.getItems().clear();
		
		if(listaInvDetalleSalida.size()>0) {
			
			for(InvDetalleSalida detalle:listaInvDetalleSalida)
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
//					idInvSalidaLbxDetalle.getChildren().remove(listItem);
//					listaInvDetalleSalida.remove(dto);

				}
			});
 			
 			cel.appendChild(img);
 			
 			listItem.appendChild(cel);
			idInvSalidaLbxDetalle.appendChild(listItem);
			calcularTotal();
			
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

		idInvSalidaBtnNuevo.setDisabled(true);
		util.habilitarCampos(idInvSalidaFormulario);
		util.limpiarCampos(idInvSalidaFormulario);
		
		//util.habilitarCampos(idInvSalidaFormulario2);
		idInvSalidaBandboxPersona.setDisabled(false);
		util.limpiarCampos(idInvSalidaFormulario2);
		
		//util.habilitarCampos(idInvSalidaFormulario3);
		idInvSalidaTbxCodigoProd.setDisabled(false);
		idInvSalidaBtnBuscarItem.setDisabled(false);
		idInvSalidaTbxCantidadProd.setDisabled(false);
		util.limpiarCampos(idInvSalidaFormulario3);
		listaInvDetalleSalida.clear();
		idInvSalidaLbxDetalle.getItems().clear();

		idInvSalidaTbxOperacion.setValue("N");
		
		idInvSalidaFormulario.invalidate();
		idInvSalidaFormulario2.invalidate();
		idInvSalidaFormulario3.invalidate();

		idInvSalidaBtnCancelar.setDisabled(false);
		idInvSalidaBtnEditar.setDisabled(true);
		idInvSalidaBtnBorrar.setDisabled(true);
		idInvSalidaBtnGuardar.setDisabled(false);
		
		
		idInvSalidaTbxCodigo.setValue(util.getCodigoCompuesto());
		

	}

	public void onEditar() {
		log.info("Ejecutando el metodo [ onEditar ]...");
		util.habilitarCampos(idInvSalidaFormulario);
		util.habilitarCampos(idInvSalidaFormulario2);
		//util.habilitarCampos(idInvSalidaFormulario3);
				idInvSalidaTbxCodigoProd.setDisabled(false);
				idInvSalidaBtnBuscarItem.setDisabled(false);
				idInvSalidaTbxCantidadProd.setDisabled(false);
				//util.limpiarCampos(idInvSalidaFormulario3);
				//listaInvDetalleSalida.clear();
				//idInvSalidaLbxDetalle.getItems().clear();
		
		/* default en modo edicion */

		idInvSalidaBtnBorrar.setDisabled(true);
		idInvSalidaBtnEditar.setDisabled(true);
		idInvSalidaBtnGuardar.setDisabled(false);
		idInvSalidaBtnCancelar.setDisabled(false);
		idInvSalidaTbxOperacion.setValue("U");
		
	}
	
	public void onCrearFactura() {
		log.info("Ejecutando el metodo [ onCrearFactura ]...");
		
		
	}

	public void onBorrar() {
		log.info("metodo [onBorrar]");

		try {
			InvDetalleSalida dtoDetalle = new InvDetalleSalida();
			dtoDetalle.setSecuencia_ingreso(dtoSeleccionado);
			
			ParametrizacionFac.getFacade().borrarRegistro("deleteDetalleInvSalida", dtoDetalle);

			ParametrizacionFac.getFacade().borrarRegistro("deleteInvSalida", dtoSeleccionado);
			util.lanzarMensaje("Se elimino ");
			log.info(" Se elimino ");

			util.limpiarCampos(idInvSalidaFormulario);
			util.limpiarCampos(idInvSalidaFormulario2);
			util.limpiarCampos(idInvSalidaFormulario3);
			
			onConsultar("");

		}
catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void onCancelar() {
		log.info("Ejecutando el metodo [ onCancelar ]...");

		util.limpiarCampos(idInvSalidaFormulario);
		util.deshabilitarCampos(idInvSalidaFormulario);
		util.deshabilitarCampos(idInvSalidaFormulario2);
		util.limpiarCampos(idInvSalidaFormulario2);
		
		//util.deshabilitarCampos(idInvSalidaFormulario3);		
		idInvSalidaTbxCodigoProd.setDisabled(true);
		idInvSalidaBtnBuscarItem.setDisabled(true);
		idInvSalidaTbxCantidadProd.setDisabled(true);
		util.limpiarCampos(idInvSalidaFormulario3);
		listaInvDetalleSalida.clear();
		idInvSalidaLbxDetalle.getItems().clear();
		

		idInvSalidaBtnGuardar.setDisabled(true);
		idInvSalidaBtnCancelar.setDisabled(true);
		idInvSalidaBtnEditar.setDisabled(true);
		idInvSalidaBtnBorrar.setDisabled(true);
		idInvSalidaBtnNuevo.setDisabled(false);

	}
	

	
	public void llenarBandboxPersona(String criterio) {
		List<Persona> listaPersona;
		Persona persona = new Persona();
		persona.setNombres("%" + (criterio.equals(" ") ? "" : criterio) + "%");		
		persona.setEtiquetaDistribuidor("S");
		log.info("parmatro: " + persona.getNombres());
		
		try {			
			listaPersona = (ArrayList<Persona>) ParametrizacionFac.getFacade().obtenerListado("getPersona", persona);
			log.info("Hay " + listaPersona.size() + " datos encontradas.");						
			idInvSalidalistboxBandboxPersona.getItems().clear();
				for (final Persona dto : listaPersona) {
					log.info("persona: " + dto.getNombres());
					Listitem listItem = new Listitem();
					listItem.setValue(dto.getSecuencia() + "");
					listItem.setLabel(dto.getNombres()+" "+dto.getApellidos());
					listItem.setAttribute("persona", dto);
					listItem.addEventListener(Events.ON_CLICK, new EventListener() {
						public void onEvent(Event event) throws Exception {

							idInvSalidaTbxNombrePersona.setValue(dto.getNombres());
							idInvSalidaTbxCelPersona.setValue(dto.getTelefono());
							

						}
					});
					
					
					
					
					
					idInvSalidalistboxBandboxPersona.appendChild(listItem);
				}
			
		} catch (Exception e) {
			// TODO: handle exception
			log.info("error:"+e);
		}
	}
	
	
	
}
 