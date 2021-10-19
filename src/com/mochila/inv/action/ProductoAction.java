package com.mochila.inv.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.ext.AfterCompose;
import org.zkoss.zul.Bandbox;
import org.zkoss.zul.Borderlayout;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Doublebox;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Radiogroup;
import org.zkoss.zul.Rows;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.Window;

import com.mochila.framework.facade.ParametrizacionFac;
import com.mochila.framework.helper.UtilZKProcesosComunesHelper;
import com.mochila.gen.dto.Usuario;
import com.mochila.inv.dto.Producto;
import com.mochila.inv.dto.TipoProducto;

public class ProductoAction extends Borderlayout implements AfterCompose {
	protected static Logger log = Logger.getLogger(ProductoAction.class);

	Textbox idInvProductoTbxBusqueda;
	Listbox idInvProductoLbxProducto;
	Toolbarbutton idInvProductoBtnNuevo;
	Toolbarbutton idInvProductoBtnEditar;
	Toolbarbutton idInvProductoBtnGuardar;
	Toolbarbutton idInvProductoBtnBorrar;
	Toolbarbutton idInvProductoBtnCancelar;
	Toolbarbutton idInvProductoBtnImagen;
	Rows idInvProductoFormulario;
	Textbox idInvProductoTbxOperacion;
Textbox idInvProductoTbxSecuencia;
Textbox idInvProductoTbxTipoProducto;
Textbox idInvProductoTbxCodigo;
Textbox idInvProductoTbxNombre;
Doublebox idInvProductoDbxPrecioVenta;
Doublebox idInvProductoDbxUltPrecioCompra;
Textbox idInvProductoTbxCantidadMin;
Textbox idInvProductoTbxDescuentoMax;
Radiogroup idInvProductoRgbEstado;
Textbox idInvProductoTbxDescripcion;

Bandbox idInvProductoBandboxTipoProducto;
Listbox idInvProductolistboxBandboxTipoProducto;

	UtilZKProcesosComunesHelper util = new UtilZKProcesosComunesHelper();
	Producto dtoSeleccionado = new Producto();
	Usuario usuario = new Usuario();
	ArrayList<Producto> listaProducto = new ArrayList<Producto>();

@Override
	public void afterCompose() {
		// TODO Auto-generated method stub
		cargar();
		usuario = (Usuario) Executions.getCurrent().getSession().getAttribute("USUARIO_SESSION");
		log.info("Usuario: " + usuario.getNombreUsuario());
		onConsultar("");
	}

	public void cargar() {
		idInvProductoTbxBusqueda = (Textbox) this.getFellow("idInvProductoTbxBusqueda");
		idInvProductoLbxProducto = (Listbox) this.getFellow("idInvProductoLbxProducto");
		idInvProductoBtnNuevo = (Toolbarbutton) this.getFellow("idInvProductoBtnNuevo");
		idInvProductoBtnEditar = (Toolbarbutton) this.getFellow("idInvProductoBtnEditar");
		idInvProductoBtnGuardar = (Toolbarbutton) this.getFellow("idInvProductoBtnGuardar");
		idInvProductoBtnBorrar = (Toolbarbutton) this.getFellow("idInvProductoBtnBorrar");
		idInvProductoBtnCancelar = (Toolbarbutton) this.getFellow("idInvProductoBtnCancelar");
		idInvProductoBtnImagen = (Toolbarbutton) this.getFellow("idInvProductoBtnImagen");
		idInvProductoFormulario = (Rows) this.getFellow("idInvProductoFormulario");
		idInvProductoTbxOperacion = (Textbox) this.getFellow("idInvProductoTbxOperacion");
idInvProductoTbxSecuencia = (Textbox ) this.getFellow("idInvProductoTbxSecuencia");
idInvProductoTbxTipoProducto = (Textbox ) this.getFellow("idInvProductoTbxTipoProducto");
idInvProductoTbxCodigo = (Textbox ) this.getFellow("idInvProductoTbxCodigo");
idInvProductoTbxNombre = (Textbox ) this.getFellow("idInvProductoTbxNombre");
idInvProductoDbxPrecioVenta = (Doublebox) this.getFellow("idInvProductoDbxPrecioVenta");
idInvProductoDbxUltPrecioCompra = (Doublebox) this.getFellow("idInvProductoDbxUltPrecioCompra");
idInvProductoTbxCantidadMin = (Textbox ) this.getFellow("idInvProductoTbxCantidadMin");
idInvProductoTbxDescuentoMax = (Textbox ) this.getFellow("idInvProductoTbxDescuentoMax");
idInvProductoRgbEstado = (Radiogroup ) this.getFellow("idInvProductoRgbEstado");
idInvProductoTbxDescripcion = (Textbox ) this.getFellow("idInvProductoTbxDescripcion");

//BandboxTipoProducto
idInvProductoBandboxTipoProducto = (Bandbox ) this.getFellow("idInvProductoBandboxTipoProducto");
idInvProductolistboxBandboxTipoProducto = (Listbox ) this.getFellow("idInvProductolistboxBandboxTipoProducto");
}

 	public void onConsultar(String criterio) {
		log.info("Ejecutando el metodo [ onConsultar ]... ");
		idInvProductoLbxProducto.getItems().clear();
		dtoSeleccionado = null;

		idInvProductoFormulario.invalidate();
		idInvProductoBtnCancelar.setDisabled(true);
		idInvProductoBtnGuardar.setDisabled(true);
		idInvProductoBtnEditar.setDisabled(true);
		idInvProductoBtnImagen.setDisabled(true);
		idInvProductoBtnBorrar.setDisabled(true);

		util.limpiarCampos(idInvProductoFormulario);
		util.deshabilitarCampos(idInvProductoFormulario);

		log.info("parmatro:" + criterio);

		Producto producto = new Producto();
		producto.setNombre("%" + (criterio.equals(" ") ? "" : criterio) + "%");
		try {
			if(usuario.getGrupo()!=0){producto.setUsuarioCreacion(usuario);}
			
			listaProducto = (ArrayList<Producto>) ParametrizacionFac.getFacade().obtenerListado("getProducto", producto);

			log.info("Hay " + listaProducto.size() + " datos encontrados.");

			for (final Producto dto : listaProducto) {
				Listitem listItem = util.crearListitem(dto, dto.getEstado(),"["+dto.getCodigo()+"] "+dto.getNombre());
				listItem.addEventListener(Events.ON_CLICK, new EventListener() {
					public void onEvent(Event event) throws Exception {

						onSeleccionar(dto);
						// listItemSeleccionado=listItem;
						// listItem.setSelected(true);

					}
				});
				idInvProductoLbxProducto.appendChild(listItem);

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

public void onGuardar() {
		log.info("Ejecutando el metodo [onGuardar ]... ");
		String op = idInvProductoTbxOperacion.getValue();
		ArrayList<String> arrayRequired = new ArrayList<String>();

		if (idInvProductoTbxTipoProducto.getValue().isEmpty()) {arrayRequired.add("TIPOPRODUCTO");}

		if (idInvProductoTbxCodigo.getValue().isEmpty()) {arrayRequired.add("CODIGO");}

		if (idInvProductoTbxNombre.getValue().isEmpty()) {arrayRequired.add("NOMBRE");}
				
		if (idInvProductoTbxCantidadMin.getValue().isEmpty()) {arrayRequired.add("CANTIDAD MINIMA");}
		
		if (idInvProductoTbxDescuentoMax.getValue().isEmpty()) {arrayRequired.add("% DESCUENTO");}

		if (arrayRequired.size() > 0) {
			log.info("Faltan campos obligatorios por llenar");
			util.lanzarMensajeCamposFatantes("faltan campos obligatorios por llenar", arrayRequired);

		} else {
			Producto producto = new Producto();
			TipoProducto tipoProducto =new TipoProducto();
			tipoProducto.setSecuencia(new Long(idInvProductoTbxTipoProducto.getValue()));
			tipoProducto.setNombre(idInvProductoBandboxTipoProducto.getValue());
			
			producto.setTipoProducto(tipoProducto);

			producto.setCodigo(idInvProductoTbxCodigo.getValue());

			producto.setNombre(idInvProductoTbxNombre.getValue());

			producto.setPrecioVenta(idInvProductoDbxPrecioVenta.getValue());

			producto.setUltPrecioCompra(idInvProductoDbxUltPrecioCompra.getValue());

			producto.setCantidadMin(new Long(idInvProductoTbxCantidadMin.getValue()));

			producto.setDescuentoMax(new Long(idInvProductoTbxDescuentoMax.getValue()));

			producto.setEstado(idInvProductoRgbEstado.getSelectedIndex() == 0 ? "A" : "I");

			producto.setDescripcion(idInvProductoTbxDescripcion.getValue());

			log.info("Parametro: " + producto.toString());
			if (idInvProductoTbxOperacion.getValue().equals("U")) {
				log.info(" metodo [actualizar]");
				producto.setSecuencia(new Long(idInvProductoTbxSecuencia.getValue()));
				producto.setFechaActualizacion(new Date());
				producto.setUsuarioActualizacion(this.usuario);
				producto.setFechaCreacion(dtoSeleccionado.getFechaCreacion());
				producto.setUsuarioCreacion(dtoSeleccionado.getUsuarioCreacion());

				try {
					ParametrizacionFac.getFacade().insertarRegistro("putProducto", producto);
					util.deshabilitarCampos(idInvProductoFormulario);
					onConsultar("");
					this.onSeleccionar(producto);

					log.info("Actualizado");
					util.lanzarMensaje("Actualizado");
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (op.equals("N")) {
				log.info("metodo [Guardar]");
				try {
					producto.setFechaCreacion(new Date());
					producto.setUsuarioCreacion(this.usuario);
					ParametrizacionFac.getFacade().insertarRegistro("postProducto", producto);
					util.deshabilitarCampos(idInvProductoFormulario);
					onConsultar("");
					this.onSeleccionar(producto);
					log.info("Guardado");
					util.lanzarMensaje("Guardado");
				} catch (Exception e) {
					util.lanzarMensaje("Error:::> " + e);
					log.info("Error:::> " + e);
					e.printStackTrace();
				}
				idInvProductoBtnGuardar.setDisabled(true);
				idInvProductoBtnCancelar.setDisabled(true);
				idInvProductoBtnNuevo.setDisabled(false);
			}
		}
	}

	public void onSeleccionar(Producto dto) {
		log.info(" Ejecutando el metodo [ onSeleccionar ]... ");

		dtoSeleccionado = dto;

		util.deshabilitarCampos(idInvProductoFormulario);
		util.limpiarCampos(idInvProductoFormulario);

		idInvProductoBtnNuevo.setDisabled(false);
		idInvProductoBtnGuardar.setDisabled(true);
		idInvProductoBtnCancelar.setDisabled(true);
		idInvProductoBtnEditar.setDisabled(false);
		idInvProductoBtnImagen.setDisabled(false);
		idInvProductoBtnBorrar.setDisabled(false);
		// logica
		idInvProductoTbxSecuencia.setValue(dto.getSecuencia() + "");
		idInvProductoTbxTipoProducto.setValue(dto.getTipoProducto().getSecuencia()+ "");
		idInvProductoBandboxTipoProducto.setValue(dto.getTipoProducto().getNombre());
		idInvProductoTbxCodigo.setValue(dto.getCodigo() + "");
		idInvProductoTbxNombre.setValue(dto.getNombre() + "");
		idInvProductoDbxPrecioVenta.setValue(dto.getPrecioVenta());
		idInvProductoDbxUltPrecioCompra.setValue(dto.getUltPrecioCompra());
		idInvProductoTbxCantidadMin.setValue(dto.getCantidadMin() + "");
		idInvProductoTbxDescuentoMax.setValue(dto.getDescuentoMax() + "");
		idInvProductoRgbEstado.setSelectedIndex(dto.getEstado().equals("A") ? 0 : 1);
		idInvProductoTbxDescripcion.setValue(dto.getDescripcion()+ "");
	}

public void onNuevo() {
		log.info("Ejecutando el metodo [ onNuevo]... ");

		dtoSeleccionado = null;

		idInvProductoBtnNuevo.setDisabled(true);
		util.habilitarCampos(idInvProductoFormulario);
		util.limpiarCampos(idInvProductoFormulario);
		util.seleccionarRadio(idInvProductoRgbEstado, "A");

		idInvProductoTbxOperacion.setValue("N");
		idInvProductoRgbEstado.getParent().setVisible(false);
		idInvProductoFormulario.invalidate();

		idInvProductoBtnCancelar.setDisabled(false);
		idInvProductoBtnEditar.setDisabled(true);
		idInvProductoBtnImagen.setDisabled(true);
		idInvProductoBtnBorrar.setDisabled(true);
		idInvProductoBtnGuardar.setDisabled(false);

	}

	public void onEditar() {
		log.info("Ejecutando el metodo [ onEditar ]...");
		util.habilitarCampos(idInvProductoFormulario);

		/* default en modo edicion */

		idInvProductoBtnBorrar.setDisabled(true);
		idInvProductoBtnEditar.setDisabled(true);
		idInvProductoBtnImagen.setDisabled(true);
		idInvProductoBtnGuardar.setDisabled(false);
		idInvProductoBtnCancelar.setDisabled(false);
		idInvProductoTbxOperacion.setValue("U");
		idInvProductoRgbEstado.getParent().setVisible(true);
	}

	public void onBorrar() {
		log.info("metodo [onBorrar]"+dtoSeleccionado.getSecuencia());

		try {

			ParametrizacionFac.getFacade().borrarRegistro("deleteProducto", dtoSeleccionado);
			util.lanzarMensaje("Se elimino ");
			log.info(" Se elimino ");

			util.limpiarCampos(idInvProductoFormulario);
			onConsultar("");

		}
catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void onCancelar() {
		log.info("Ejecutando el metodo [ onCancelar ]...");

		util.limpiarCampos(idInvProductoFormulario);
		util.deshabilitarCampos(idInvProductoFormulario);

		idInvProductoBtnGuardar.setDisabled(true);
		idInvProductoBtnCancelar.setDisabled(true);
		idInvProductoBtnEditar.setDisabled(true);
		idInvProductoBtnImagen.setDisabled(true);
		idInvProductoBtnBorrar.setDisabled(true);
		idInvProductoBtnNuevo.setDisabled(false);

	}
	
	
	public void onImagen() {
		log.info("Ejecutando el metodo [ onImagen ]...");
		

		/* default en modo edicion */
		Executions.getCurrent().getSession().setAttribute("PRODUCTO_SESSION", dtoSeleccionado);
		
		Window window = (Window)Executions.createComponents(
	            "mochila/inv/contenedoFotoProducto.zul", null, null);
		window.setMaximizable(true);
	    window.doModal();
	    
			}
	
	
	
	public void llenarBandboxTipoProducto(String criterio) {
		List<TipoProducto> listaTipoProducto;
		TipoProducto TipoProducto = new TipoProducto();
		TipoProducto.setNombre("%" + (criterio.equals(" ") ? "" : criterio) + "%");
		
		log.info("parmatro: " + TipoProducto.getNombre());
		
		try {			
			listaTipoProducto = (ArrayList<TipoProducto>) ParametrizacionFac.getFacade().obtenerListado("getTipoProducto", TipoProducto);
			log.info("Hay " + listaTipoProducto.size() + " datos encontradas.");						
				idInvProductolistboxBandboxTipoProducto.getItems().clear();
				for (final TipoProducto dto : listaTipoProducto) {
					log.info("TipoProducto: " + dto.getNombre());
					Listitem listItem = new Listitem();
					listItem.setValue(dto.getSecuencia() + "");
					listItem.setLabel(dto.getNombre());
					listItem.setAttribute("TipoProducto", dto);
					listItem.getAttribute("TipoProducto");
					idInvProductolistboxBandboxTipoProducto.appendChild(listItem);
				}
			
		} catch (Exception e) {
			// TODO: handle exception
			log.info("");
		}
	}
	
	
}
 