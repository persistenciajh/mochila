package com.mochila.gen.action;

import java.util.ArrayList;
import java.util.Date;
import org.apache.log4j.Logger;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.ext.AfterCompose;
import org.zkoss.zul.Borderlayout;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Radiogroup;
import org.zkoss.zul.Rows;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Toolbarbutton;
import com.mochila.framework.facade.ParametrizacionFac;
import com.mochila.framework.helper.UtilZKProcesosComunesHelper;
import com.mochila.gen.dto.Interaccion;
import com.mochila.gen.dto.Usuario;

public class InteraccionAction  {
	protected static Logger log = Logger.getLogger(InteraccionAction.class);
/*
	Textbox idGenInteraccionTbxBusqueda;
	Listbox idGenInteraccionLbxInteraccion;
	Toolbarbutton idGenInteraccionBtnNuevo;
	Toolbarbutton idGenInteraccionBtnEditar;
	Toolbarbutton idGenInteraccionBtnGuardar;
	Toolbarbutton idGenInteraccionBtnBorrar;
	Toolbarbutton idGenInteraccionBtnCancelar;
	Rows idGenInteraccionFormulario;
	Textbox idGenInteraccionTbxOperacion;
Textbox idGenInteraccionTbxSecuencia;
Textbox idGenInteraccionTbxProducto;
Textbox idGenInteraccionTbxDescripcion;
Textbox idGenInteraccionTbxReferencia;

	UtilZKProcesosComunesHelper util = new UtilZKProcesosComunesHelper();
	Interaccion dtoSeleccionado = new Interaccion();
	Usuario usuario = new Usuario();
	ArrayList<Interaccion> listaInteraccion = new ArrayList<Interaccion>();
/*
@Override
	public void afterCompose() {
		// TODO Auto-generated method stub
		cargar();
		usuario = (Usuario) Executions.getCurrent().getSession().getAttribute("USUARIO_SESSION");
		log.info("Usuario: " + usuario.getNombreUsuario());
	}

	public void cargar() {
		idGenInteraccionTbxBusqueda = (Textbox) this.getFellow("idGenInteraccionTbxBusqueda");
		idGenInteraccionLbxInteraccion = (Listbox) this.getFellow("idGenInteraccionLbxPersonas");
		idGenInteraccionBtnNuevo = (Toolbarbutton) this.getFellow("idGenInteraccionBtnNuevo");
		idGenInteraccionBtnEditar = (Toolbarbutton) this.getFellow("idGenInteraccionBtnEditar");
		idGenInteraccionBtnGuardar = (Toolbarbutton) this.getFellow("idGenInteraccionBtnGuardar");
		idGenInteraccionBtnBorrar = (Toolbarbutton) this.getFellow("idGenInteraccionBtnBorrar");
		idGenInteraccionBtnCancelar = (Toolbarbutton) this.getFellow("idGenInteraccionBtnCancelar");
		idGenInteraccionFormulario = (Rows) this.getFellow("idGenInteraccionFormulario");
		idGenInteraccionTbxOperacion = (Textbox) this.getFellow("idGenInteraccionTbxOperacion");
idGenInteraccionTbxSecuencia = (Textbox ) this.getFellow("idGenInteraccionTbxSecuencia");
idGenInteraccionTbxProducto = (Textbox ) this.getFellow("idGenInteraccionTbxProducto");
idGenInteraccionTbxDescripcion = (Textbox ) this.getFellow("idGenInteraccionTbxDescripcion");
idGenInteraccionTbxReferencia = (Textbox ) this.getFellow("idGenInteraccionTbxReferencia");
}

 	public void onConsultar(String criterio) {
		log.info("Ejecutando el metodo [ onConsultar ]... ");
		idGenInteraccionLbxInteraccion.getItems().clear();
		dtoSeleccionado = null;

		idGenInteraccionFormulario.invalidate();
		idGenInteraccionBtnCancelar.setDisabled(true);
		idGenInteraccionBtnGuardar.setDisabled(true);
		idGenInteraccionBtnEditar.setDisabled(true);
		idGenInteraccionBtnBorrar.setDisabled(true);

		util.limpiarCampos(idGenInteraccionFormulario);
		util.deshabilitarCampos(idGenInteraccionFormulario);

		log.info("parmatro:" + criterio);

		Interaccion interaccion = new Interaccion();
		interaccion.setNombre("%" + (criterio.equals(" ") ? "" : criterio) + "%");
		try {
			listaInteraccion = (ArrayList<Interaccion>) ParametrizacionFac.getFacade().obtenerListado("getInteraccion", interaccion);

			log.info("Hay " + listaInteraccion.size() + " datos encontrados.");

			for (final Interaccion dto : listaInteraccion) {
				Listitem listItem = util.crearListitem(dto, dto.getEstado(), dto.getNombre());
				listItem.addEventListener(Events.ON_CLICK, new EventListener() {
					public void onEvent(Event event) throws Exception {

						onSeleccionar(dto);
						// listItemSeleccionado=listItem;
						// listItem.setSelected(true);

					}
				});
				idGenInteraccionLbxInteraccion.appendChild(listItem);

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

public void onGuardar() {
		log.info("Ejecutando el metodo [onGuardar ]... ");
		String op = idGenInteraccionTbxOperacion.getValue();
		ArrayList<String> arrayRequired = new ArrayList<String>();

		if (arrayRequired.size() > 0) {
			log.info("Faltan campos obligatorios por llenar");
			util.lanzarMensajeCamposFatantes("faltan campos obligatorios por llenar", arrayRequired);

		} else {
			Interaccion interaccion = new Interaccion();
			interaccion.setProducto(idGenInteraccionTbxProducto.getValue());

			interaccion.setDescripcion(idGenInteraccionTbxDescripcion.getValue());

			interaccion.setReferencia(idGenInteraccionTbxReferencia.getValue());

			log.info("Parametro: " + interaccion.toString());
			if (idGenInteraccionTbxOperacion.getValue().equals("U")) {
				log.info(" metodo [actualizar]");
				interaccion.setSecuencia(new Long(idGenInteraccionTbxSecuencia.getValue()));
				interaccion.setFechaActualizacion(new Date());
				interaccion.setSecUsuarioActualizacion(usuario.getSecuencia());
				interaccion.setFechaCreacion(dtoSeleccionado.getFechaCreacion());
				interaccion.setSecUsuarioCreacion(dtoSeleccionado.getSecUsuarioCreacion());

				try {
					ParametrizacionFac.getFacade().insertarRegistro("putInteraccion", interaccion);
					util.deshabilitarCampos(idGenInteraccionFormulario);
					onConsultar("");
					this.onSeleccionar(interaccion);

					log.info("Actualizado");
					util.lanzarMensaje("Actualizado");
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (op.equals("N")) {
				log.info("metodo [Guardar]");
				try {
					interaccion.setFechaCreacion(new Date());
					interaccion.setSecUsuarioCreacion(usuario.getSecuencia());
					ParametrizacionFac.getFacade().insertarRegistro("postInteraccion", interaccion);
					util.deshabilitarCampos(idGenInteraccionFormulario);
					onConsultar("");
					this.onSeleccionar(interaccion);
					log.info("Guardado");
					util.lanzarMensaje("Guardado");
				} catch (Exception e) {
					util.lanzarMensaje("Error:::> " + e);
					log.info("Error:::> " + e);
					e.printStackTrace();
				}
				idGenInteraccionBtnGuardar.setDisabled(true);
				idGenInteraccionBtnCancelar.setDisabled(true);
				idGenInteraccionBtnNuevo.setDisabled(false);
			}
		}
	}

	public void onSeleccionar(Persona dto) {
		log.info(" Ejecutando el metodo [ onSeleccionar ]... ");

		dtoSeleccionado = dto;

		util.deshabilitarCampos(idGenInteraccionFormulario);
		util.limpiarCampos(idGenInteraccionFormulario);

		idGenInteraccionBtnNuevo.setDisabled(false);
		idGenInteraccionBtnGuardar.setDisabled(true);
		idGenInteraccionBtnCancelar.setDisabled(true);
		idGenInteraccionBtnEditar.setDisabled(false);
		idGenInteraccionBtnBorrar.setDisabled(false);
		// logica
		idGenInteraccionTbxSecuencia.setValue(dto.getSecuencia() + "");
		idGenInteraccionTbxProducto.setValue(dto.getProducto() + "");
		idGenInteraccionTbxDescripcion.setValue(dto.getDescripcion() + "");
		idGenInteraccionTbxReferencia.setValue(dto.getReferencia() + "");
	}

public void onNuevo() {
		log.info("Ejecutando el metodo [ onNuevo]... ");

		dtoSeleccionado = null;

		idGenInteraccionBtnNuevo.setDisabled(true);
		util.habilitarCampos(idGenInteraccionFormulario);
		util.limpiarCampos(idGenInteraccionFormulario);
		util.seleccionarRadio(idGenInteraccionRgbEstado, "A");

		idGenInteraccionTbxOperacion.setValue("N");
		idGenInteraccionRgbEstado.getParent().setVisible(false);
		idGenInteraccionFormulario.invalidate();

		idGenInteraccionBtnCancelar.setDisabled(false);
		idGenInteraccionBtnEditar.setDisabled(true);
		idGenInteraccionBtnBorrar.setDisabled(true);
		idGenInteraccionBtnGuardar.setDisabled(false);

	}

	public void onEditar() {
		log.info("Ejecutando el metodo [ onEditar ]...");
		util.habilitarCampos(idGenInteraccionFormulario);

		/* default en modo edicion 

		idGenInteraccionBtnBorrar.setDisabled(true);
		idGenInteraccionBtnEditar.setDisabled(true);
		idGenInteraccionBtnGuardar.setDisabled(false);
		idGenInteraccionBtnCancelar.setDisabled(false);
		idGenInteraccionTbxOperacion.setValue("U");
		idGenInteraccionRgbEstado.getParent().setVisible(true);
	}

	public void onBorrar() {
		log.info("metodo [onBorrar]");

		try {

			ParametrizacionFac.getFacade().borrarRegistro("deletePersona", dtoSeleccionado);
			util.lanzarMensaje("Se elimino ");
			log.info(" Se elimino ");

			util.limpiarCampos(idGenInteraccionFormulario);
			onConsultar("");

		}
catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void onCancelar() {
		log.info("Ejecutando el metodo [ onCancelar ]...");

		util.limpiarCampos(idGenInteraccionFormulario);
		util.deshabilitarCampos(idGenInteraccionFormulario);

		idGenInteraccionBtnGuardar.setDisabled(true);
		idGenInteraccionBtnCancelar.setDisabled(true);
		idGenInteraccionBtnEditar.setDisabled(true);
		idGenInteraccionBtnBorrar.setDisabled(true);
		idGenInteraccionBtnNuevo.setDisabled(false);

	}
	
	*/
}
 