package com.mochila.gen.action;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.ext.AfterCompose;
import org.zkoss.zul.Borderlayout;
import org.zkoss.zul.Image;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Radio;
import org.zkoss.zul.Radiogroup;
import org.zkoss.zul.Rows;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Toolbarbutton;

import com.mochila.framework.facade.ParametrizacionFac;
import com.mochila.framework.helper.UtilZKProcesosComunesHelper;
import com.mochila.gen.dto.TipoDistribuidor;

public class TipoDistribuidorAction extends Borderlayout implements AfterCompose {

	private static final long serialVersionUID = 1L;

	protected static Logger log = Logger.getLogger(PersonaAction.class);

	Image idInvDistribuidorBtnBuscar;

	Rows idInvDistribuidorFormulario;

	Textbox idInvDistribuidorTbxOperacion;
	Textbox idInvDistribuidorTbxSecuencia;
	Textbox idInvDistribuidorTbxBusqueda;
	Textbox idInvDistribuidorTbxNombre;
	Textbox idInvDistribuidorTbxDescripcion;

	Listbox idInvDistribuidorLbxBusquedaDist;

	Radiogroup idInvDistribuidorRgbEstado;
	Radio idInvDistribuidorRbtnActivo;
	Radio idInvDistribuidorRbtnInactivo;

	Toolbarbutton idInvDistribuidorBtnNuevo;
	Toolbarbutton idInvDistribuidorBtnEditar;
	Toolbarbutton idInvDistribuidorBtnGuardar;
	Toolbarbutton idInvDistribuidorBtnBorrar;
	Toolbarbutton idInvDistribuidorBtnCancelar;

	UtilZKProcesosComunesHelper util = new UtilZKProcesosComunesHelper();

	TipoDistribuidor dtoSeleccionado = new TipoDistribuidor();

	private ArrayList<TipoDistribuidor> distribuidores = new ArrayList<TipoDistribuidor>();

	@Override
	public void afterCompose() {
		// TODO Auto-generated method stub
		cargar();
	}

	public void cargar() {

		idInvDistribuidorBtnBuscar = (Image) this.getFellow("idInvDistribuidorBtnBuscar");

		idInvDistribuidorFormulario = (Rows) this.getFellow("idInvDistribuidorFormulario");

		idInvDistribuidorTbxOperacion = (Textbox) this.getFellow("idInvDistribuidorTbxOperacion");
		idInvDistribuidorTbxSecuencia = (Textbox) this.getFellow("idInvDistribuidorTbxSecuencia");
		idInvDistribuidorTbxBusqueda = (Textbox) this.getFellow("idInvDistribuidorTbxBusqueda");
		idInvDistribuidorTbxNombre = (Textbox) this.getFellow("idInvDistribuidorTbxNombre");
		idInvDistribuidorTbxDescripcion = (Textbox) this.getFellow("idInvDistribuidorTbxDescripcion");

		idInvDistribuidorLbxBusquedaDist = (Listbox) this.getFellow("idInvDistribuidorLbxBusquedaDist");

		idInvDistribuidorRgbEstado = (Radiogroup) this.getFellow("idInvDistribuidorRgbEstado");
		idInvDistribuidorRbtnActivo = (Radio) this.getFellow("idInvDistribuidorRbtnActivo");
		idInvDistribuidorRbtnInactivo = (Radio) this.getFellow("idInvDistribuidorRbtnInactivo");

		idInvDistribuidorBtnNuevo = (Toolbarbutton) this.getFellow("idInvDistribuidorBtnNuevo");
		idInvDistribuidorBtnEditar = (Toolbarbutton) this.getFellow("idInvDistribuidorBtnEditar");
		idInvDistribuidorBtnGuardar = (Toolbarbutton) this.getFellow("idInvDistribuidorBtnGuardar");
		idInvDistribuidorBtnBorrar = (Toolbarbutton) this.getFellow("idInvDistribuidorBtnBorrar");
		idInvDistribuidorBtnCancelar = (Toolbarbutton) this.getFellow("idInvDistribuidorBtnCancelar");

	}

	public boolean existePersona(String nombre) {
		for (TipoDistribuidor distribuidor : distribuidores) {
			if (distribuidor.getNombre().equals(nombre)) {
				return true;
			}
		}
		return false;
	}

	public void onConsultar(String criterio) {

		log.info("Ejecutando el metodo [ onConsultarDistribuidor ]... ");

		idInvDistribuidorLbxBusquedaDist.getItems().clear(); // limpiar la lista para
		dtoSeleccionado = null;
		// agregar nuevos registros

		// Instrucciones para invalidar los campos
		idInvDistribuidorFormulario.invalidate();
		idInvDistribuidorTbxNombre.setDisabled(true);
		idInvDistribuidorTbxDescripcion.setDisabled(true);

		util.limpiarCampos(idInvDistribuidorFormulario);
		util.deshabilitarCampos(idInvDistribuidorFormulario);

		TipoDistribuidor distribuidor = new TipoDistribuidor();
		distribuidor.setNombre("%" + (criterio.equals(" ") ? "" : criterio) + "%"); // Poner el nombre para
																					// buscar en la
		// bd
//		tipoDistribuidor.setIdentificacion("%" + criterio + "%"); // usando LIKE("%cadena%");

		try {
			// Obtener el listado de las personas
			distribuidores = (ArrayList<TipoDistribuidor>) ParametrizacionFac.getFacade()
					.obtenerListado("getTipoDistribuidor", distribuidor);

			log.info("Hay " + distribuidores.size() + " datos encontradas." + distribuidores.get(0).getSecuencia());
			log.info("NOMBRE: " + distribuidores.get(0).getNombre());

			for (final TipoDistribuidor dto : distribuidores) {

				// Por cada persona encontrada, crear un hijo de la ListBox
				Listitem listItem = util.crearListitem(dto, dto.getEstado(), dto.getNombre());
//				listItem.setValue(dto.getSecuencia());

				// Añadir el evento ONCLICK a cada hijo de la listBox(ListItem)
				listItem.addEventListener(Events.ON_CLICK, new EventListener() {
					@Override
					public void onEvent(Event event) throws Exception {
						log.info("Evento ONCLICK ingresado");
						onSeleccionar(dto);

					}
				});
				idInvDistribuidorLbxBusquedaDist.appendChild(listItem);

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.info("Error: " + e);
			e.printStackTrace();
		}

	}

	public void onSeleccionar(TipoDistribuidor dto) {
		log.info(" Ejecutando el metodo [ onSeleccionarDistribuidor ]... ");

		dtoSeleccionado = dto;

		util.deshabilitarCampos(idInvDistribuidorFormulario);
		util.limpiarCampos(idInvDistribuidorFormulario);

		// deshabilitar el form
		idInvDistribuidorBtnNuevo.setDisabled(false);
		idInvDistribuidorBtnGuardar.setDisabled(true);
		idInvDistribuidorBtnCancelar.setDisabled(true);
		idInvDistribuidorBtnEditar.setDisabled(false);
		idInvDistribuidorBtnBorrar.setDisabled(false);

		// logica
		// Llenar los campos
		idInvDistribuidorTbxSecuencia.setValue("" + dto.getSecuencia());
		idInvDistribuidorTbxNombre.setValue(dto.getNombre());
		idInvDistribuidorTbxDescripcion.setValue(dto.getDescripcion());

		idInvDistribuidorRgbEstado.setSelectedIndex(dto.getEstado().equals("A") ? 0 : 1);

	}

	public void onGuardar() {
		log.info("Ejecutando el metodo [ onGuardar2 ]... ");
		String op = idInvDistribuidorTbxOperacion.getValue();
		ArrayList<String> arrayRequired = new ArrayList<String>();

		if (idInvDistribuidorTbxNombre.getValue().isEmpty()) {
			arrayRequired.add("NOMBRE");
		}
		if (idInvDistribuidorTbxDescripcion.getValue().isEmpty()) {
			arrayRequired.add("APELLIDOS");
		}

		if (arrayRequired.size() > 0) {
			log.info("Faltan campos obligatorios por llenar");
			util.lanzarMensajeCamposFatantes("Faltan campos obligatorios por llenar", arrayRequired);

		} else {

			TipoDistribuidor tipoDistribuidor = new TipoDistribuidor();

			if (!idInvDistribuidorTbxSecuencia.getValue().isEmpty()
					&& (idInvDistribuidorTbxSecuencia.getValue() != null)) {
				tipoDistribuidor.setSecuencia(Long.parseLong(idInvDistribuidorTbxSecuencia.getValue()));
			}

			tipoDistribuidor.setNombre(idInvDistribuidorTbxNombre.getValue().toUpperCase());
			tipoDistribuidor.setDescripcion(idInvDistribuidorTbxDescripcion.getValue().toUpperCase());
			tipoDistribuidor.setEstado(idInvDistribuidorRgbEstado.getSelectedIndex() == 0 ? "A" : "I");

			log.info("Parametro: " + tipoDistribuidor.toString());

			if (idInvDistribuidorTbxOperacion.getValue().equals("U")) {
				log.info(" metodo [actualizar]");
//				tipoDistribuidor.setSecuencia(new Long(idInvDistribuidorTbxSecuencia.getValue()));
//				tipoDistribuidor.setFechaActualizacion(new Date());
//				tipoDistribuidor.setSecUsuarioActualizacion(usuario.getSecuencia());
//				tipoDistribuidor.setFechaCreacion(dtoSeleccionado.getFechaCreacion());
//				tipoDistribuidor.setSecUsuarioCreacion(dtoSeleccionado.getSecUsuarioCreacion());

				try {
					ParametrizacionFac.getFacade().insertarRegistro("putTipoDistribuidor", tipoDistribuidor);

					util.deshabilitarCampos(idInvDistribuidorFormulario);
					onConsultar("");
					this.onSeleccionar(tipoDistribuidor);

					log.info("Actualizado");
					util.lanzarMensaje("Actualizado");

				} catch (Exception e) {
					e.printStackTrace();
				}

			} else if (op.equals("N")) {

				log.info("metodo [Guardar]");
				try {

					ParametrizacionFac.getFacade().insertarRegistro("postTipoDistribuidor", tipoDistribuidor);
					util.deshabilitarCampos(idInvDistribuidorFormulario);
					onConsultar("");
					this.onSeleccionar(tipoDistribuidor);

					log.info("Guardado");
					util.lanzarMensaje("Guardado");

				} catch (Exception e) {
					util.lanzarMensaje("Error:::> " + e);
					log.info("Error:::> " + e);
					e.printStackTrace();
				}

				idInvDistribuidorBtnGuardar.setDisabled(true);
				idInvDistribuidorBtnCancelar.setDisabled(true);
				idInvDistribuidorBtnNuevo.setDisabled(false);

			}
		}
	}

	public void onNuevo() {
		log.info("Ejecutando el metodo [ onNuevo - TipoDistribuidor ]... ");

		dtoSeleccionado = null;

		idInvDistribuidorBtnNuevo.setDisabled(true);
		util.habilitarCampos(idInvDistribuidorFormulario);
		util.limpiarCampos(idInvDistribuidorFormulario);
		util.seleccionarRadio(idInvDistribuidorRgbEstado, "Activo");

		idInvDistribuidorTbxOperacion.setValue("N");
		// idInvDistribuidorRgbEstado.getParent().setVisible(false);
		idInvDistribuidorFormulario.invalidate();

		idInvDistribuidorBtnCancelar.setDisabled(false);
		idInvDistribuidorBtnEditar.setDisabled(true);
		idInvDistribuidorBtnBorrar.setDisabled(true);
		idInvDistribuidorBtnGuardar.setDisabled(false);
	}

	public void onEditar() {
		log.info("Ejecutando el metodo [ onEditar - TipoDistribuidor ]...");
		util.habilitarCampos(idInvDistribuidorFormulario);

		// default en modo edicion

		idInvDistribuidorBtnBorrar.setDisabled(true);
		idInvDistribuidorBtnEditar.setDisabled(true);
		idInvDistribuidorBtnGuardar.setDisabled(false);
		idInvDistribuidorBtnCancelar.setDisabled(false);
		idInvDistribuidorTbxOperacion.setValue("U");
		idInvDistribuidorRgbEstado.getParent().setVisible(true);
	}

	public void onBorrar() {
		log.info("metodo [ onBorrar - TipoDistribuidor ]");

		try {

			ParametrizacionFac.getFacade().borrarRegistro("deleteTipoDistribuidor", dtoSeleccionado);
			util.lanzarMensaje("Se elimino ");
			log.info(" Se elimino ");

			util.limpiarCampos(idInvDistribuidorFormulario);
			onConsultar("");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void onCancelar() {
		log.info("Ejecutando el metodo [ onCancelar - TipoDistribuidor ]...");

		util.limpiarCampos(idInvDistribuidorFormulario);
		util.deshabilitarCampos(idInvDistribuidorFormulario);

		idInvDistribuidorBtnGuardar.setDisabled(true);
		idInvDistribuidorBtnCancelar.setDisabled(true);
		idInvDistribuidorBtnEditar.setDisabled(true);
		idInvDistribuidorBtnBorrar.setDisabled(true);
		idInvDistribuidorBtnNuevo.setDisabled(false);

	}

}
