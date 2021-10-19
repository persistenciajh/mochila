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
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Radiogroup;
import org.zkoss.zul.Rows;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Toolbarbutton;
import com.mochila.framework.facade.ParametrizacionFac;
import com.mochila.framework.helper.UtilZKProcesosComunesHelper;
import com.mochila.gen.dto.Persona;
import com.mochila.gen.dto.Usuario;
import com.mochila.inv.dto.Bodega;

public class BodegaAction extends Borderlayout implements AfterCompose {
	protected static Logger log = Logger.getLogger(BodegaAction.class);

	Textbox idInvBodegaTbxBusqueda;
	Listbox idInvBodegaLbxBodega;
	Toolbarbutton idInvBodegaBtnNuevo;
	Toolbarbutton idInvBodegaBtnEditar;
	Toolbarbutton idInvBodegaBtnGuardar;
	Toolbarbutton idInvBodegaBtnBorrar;
	Toolbarbutton idInvBodegaBtnCancelar;
	Rows idInvBodegaFormulario;
	Textbox idInvBodegaTbxOperacion;
Textbox idInvBodegaTbxSecuencia;
Textbox idInvBodegaTbxPersona;
Textbox idInvBodegaTbxCodigo;
Textbox idInvBodegaTbxNombre;
Textbox idInvBodegaTbxDireccion;
Textbox idInvBodegaTbxTelefono;
Radiogroup idInvBodegaRgbEstado;
Textbox idInvBodegaTbxDescripcion;

Bandbox idInvBodegaBandboxPersona;
Listbox idInvBodegalistboxBandboxPersona;

	UtilZKProcesosComunesHelper util = new UtilZKProcesosComunesHelper();
	Bodega dtoSeleccionado = new Bodega();
	Usuario usuario = new Usuario();
	ArrayList<Bodega> listaBodega = new ArrayList<Bodega>();

@Override
	public void afterCompose() {
		// TODO Auto-generated method stub
		cargar();
		usuario = (Usuario) Executions.getCurrent().getSession().getAttribute("USUARIO_SESSION");
		log.info("Usuario: " + usuario.getNombreUsuario());
	}

	public void cargar() {
		idInvBodegaTbxBusqueda = (Textbox) this.getFellow("idInvBodegaTbxBusqueda");
		idInvBodegaLbxBodega = (Listbox) this.getFellow("idInvBodegaLbxBodega");
		idInvBodegaBtnNuevo = (Toolbarbutton) this.getFellow("idInvBodegaBtnNuevo");
		idInvBodegaBtnEditar = (Toolbarbutton) this.getFellow("idInvBodegaBtnEditar");
		idInvBodegaBtnGuardar = (Toolbarbutton) this.getFellow("idInvBodegaBtnGuardar");
		idInvBodegaBtnBorrar = (Toolbarbutton) this.getFellow("idInvBodegaBtnBorrar");
		idInvBodegaBtnCancelar = (Toolbarbutton) this.getFellow("idInvBodegaBtnCancelar");
		idInvBodegaFormulario = (Rows) this.getFellow("idInvBodegaFormulario");
		idInvBodegaTbxOperacion = (Textbox) this.getFellow("idInvBodegaTbxOperacion");
idInvBodegaTbxSecuencia = (Textbox ) this.getFellow("idInvBodegaTbxSecuencia");
idInvBodegaTbxPersona = (Textbox ) this.getFellow("idInvBodegaTbxPersona");
idInvBodegaTbxCodigo = (Textbox ) this.getFellow("idInvBodegaTbxCodigo");
idInvBodegaTbxNombre = (Textbox ) this.getFellow("idInvBodegaTbxNombre");
idInvBodegaTbxDireccion = (Textbox ) this.getFellow("idInvBodegaTbxDireccion");
idInvBodegaTbxTelefono = (Textbox ) this.getFellow("idInvBodegaTbxTelefono");
idInvBodegaRgbEstado = (Radiogroup ) this.getFellow("idInvBodegaRgbEstado");
idInvBodegaTbxDescripcion = (Textbox ) this.getFellow("idInvBodegaTbxDescripcion");

//BandboxPersona
idInvBodegaBandboxPersona = (Bandbox ) this.getFellow("idInvBodegaBandboxPersona");
idInvBodegalistboxBandboxPersona = (Listbox ) this.getFellow("idInvBodegalistboxBandboxPersona");

}

 	public void onConsultar(String criterio) {
		log.info("Ejecutando el metodo [ onConsultar ]... ");
		idInvBodegaLbxBodega.getItems().clear();
		dtoSeleccionado = null;

		idInvBodegaFormulario.invalidate();
		idInvBodegaBtnCancelar.setDisabled(true);
		idInvBodegaBtnGuardar.setDisabled(true);
		idInvBodegaBtnEditar.setDisabled(true);
		idInvBodegaBtnBorrar.setDisabled(true);

		util.limpiarCampos(idInvBodegaFormulario);
		util.deshabilitarCampos(idInvBodegaFormulario);

		log.info("parmatro:" + criterio);

		Bodega bodega = new Bodega();
		bodega.setNombre("%" + (criterio.equals(" ") ? "" : criterio) + "%");
		try {
			listaBodega = (ArrayList<Bodega>) ParametrizacionFac.getFacade().obtenerListado("getBodega", bodega);

			log.info("Hay " + listaBodega.size() + " datos encontrados.");

			for (final Bodega dto : listaBodega) {
				Listitem listItem = util.crearListitem(dto, dto.getEstado(), dto.getNombre());
				listItem.addEventListener(Events.ON_CLICK, new EventListener() {
					public void onEvent(Event event) throws Exception {

						onSeleccionar(dto);
						// listItemSeleccionado=listItem;
						// listItem.setSelected(true);

					}
				});
				idInvBodegaLbxBodega.appendChild(listItem);

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

public void onGuardar() {
		log.info("Ejecutando el metodo [onGuardar ]... ");
		String op = idInvBodegaTbxOperacion.getValue();
		ArrayList<String> arrayRequired = new ArrayList<String>();
		
		if (idInvBodegaTbxPersona.getValue().isEmpty()) {arrayRequired.add("PERSONA");}

		if (idInvBodegaTbxCodigo.getValue().isEmpty()) {arrayRequired.add("CODIGO");}

		if (idInvBodegaTbxNombre.getValue().isEmpty()) {arrayRequired.add("NOMBRE");}

		if (arrayRequired.size() > 0) {
			log.info("Faltan campos obligatorios por llenar");
			util.lanzarMensajeCamposFatantes("faltan campos obligatorios por llenar", arrayRequired);

		} else {
			Bodega bodega = new Bodega();
			Persona persona=new Persona();
			persona.setSecuencia(new Long(idInvBodegaTbxPersona.getValue()));
			persona.setNombres(idInvBodegaBandboxPersona.getValue());
			bodega.setPersona(persona);

			bodega.setCodigo(idInvBodegaTbxCodigo.getValue());

			bodega.setNombre(idInvBodegaTbxNombre.getValue());

			bodega.setDireccion(idInvBodegaTbxDireccion.getValue());

			bodega.setTelefono(idInvBodegaTbxTelefono.getValue());

			bodega.setEstado(idInvBodegaRgbEstado.getSelectedIndex() == 0 ? "A" : "I");

			bodega.setDescripcion(idInvBodegaTbxDescripcion.getValue());

			log.info("Parametro: " + bodega.toString());
			if (idInvBodegaTbxOperacion.getValue().equals("U")) {
				log.info(" metodo [actualizar]");
				bodega.setSecuencia(new Long(idInvBodegaTbxSecuencia.getValue()));
				bodega.setFechaActualizacion(new Date());
				bodega.setUsuarioActualizacion(this.usuario);
				bodega.setFechaCreacion(dtoSeleccionado.getFechaCreacion());
				bodega.setUsuarioCreacion(dtoSeleccionado.getUsuarioCreacion());

				try {
					ParametrizacionFac.getFacade().insertarRegistro("putBodega", bodega);
					util.deshabilitarCampos(idInvBodegaFormulario);
					onConsultar("");
					this.onSeleccionar(bodega);

					log.info("Actualizado");
					util.lanzarMensaje("Actualizado");
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (op.equals("N")) {
				log.info("metodo [Guardar]");
				try {
					bodega.setFechaCreacion(new Date());
					bodega.setUsuarioCreacion(this.usuario);
					ParametrizacionFac.getFacade().insertarRegistro("postBodega", bodega);
					util.deshabilitarCampos(idInvBodegaFormulario);
					onConsultar("");
					this.onSeleccionar(bodega);
					log.info("Guardado");
					util.lanzarMensaje("Guardado");
				} catch (Exception e) {
					util.lanzarMensaje("Error:::> " + e);
					log.info("Error:::> " + e);
					e.printStackTrace();
				}
				idInvBodegaBtnGuardar.setDisabled(true);
				idInvBodegaBtnCancelar.setDisabled(true);
				idInvBodegaBtnNuevo.setDisabled(false);
			}
		}
	}

	public void onSeleccionar(Bodega dto) {
		log.info(" Ejecutando el metodo [ onSeleccionar ]... ");

		dtoSeleccionado = dto;

		util.deshabilitarCampos(idInvBodegaFormulario);
		util.limpiarCampos(idInvBodegaFormulario);

		idInvBodegaBtnNuevo.setDisabled(false);
		idInvBodegaBtnGuardar.setDisabled(true);
		idInvBodegaBtnCancelar.setDisabled(true);
		idInvBodegaBtnEditar.setDisabled(false);
		idInvBodegaBtnBorrar.setDisabled(false);
		// logica
		idInvBodegaTbxSecuencia.setValue(dto.getSecuencia() + "");
		idInvBodegaTbxPersona.setValue(dto.getPersona().getSecuencia() + "");
		idInvBodegaBandboxPersona.setValue(dto.getPersona().getNombres() + "");
		idInvBodegaTbxCodigo.setValue(dto.getCodigo() + "");
		idInvBodegaTbxNombre.setValue(dto.getNombre() + "");
		idInvBodegaTbxDireccion.setValue(dto.getDireccion() + "");
		idInvBodegaTbxTelefono.setValue(dto.getTelefono() + "");
		idInvBodegaRgbEstado.setSelectedIndex(dto.getEstado().equals("A") ? 0 : 1);
		idInvBodegaTbxDescripcion.setValue(dto.getDescripcion() + "");
	}

public void onNuevo() {
		log.info("Ejecutando el metodo [ onNuevo]... ");

		dtoSeleccionado = null;

		idInvBodegaBtnNuevo.setDisabled(true);
		util.habilitarCampos(idInvBodegaFormulario);
		util.limpiarCampos(idInvBodegaFormulario);
		util.seleccionarRadio(idInvBodegaRgbEstado, "A");

		idInvBodegaTbxOperacion.setValue("N");
		idInvBodegaRgbEstado.getParent().setVisible(false);
		idInvBodegaFormulario.invalidate();

		idInvBodegaBtnCancelar.setDisabled(false);
		idInvBodegaBtnEditar.setDisabled(true);
		idInvBodegaBtnBorrar.setDisabled(true);
		idInvBodegaBtnGuardar.setDisabled(false);

	}

	public void onEditar() {
		log.info("Ejecutando el metodo [ onEditar ]...");
		util.habilitarCampos(idInvBodegaFormulario);

		/* default en modo edicion */

		idInvBodegaBtnBorrar.setDisabled(true);
		idInvBodegaBtnEditar.setDisabled(true);
		idInvBodegaBtnGuardar.setDisabled(false);
		idInvBodegaBtnCancelar.setDisabled(false);
		idInvBodegaTbxOperacion.setValue("U");
		idInvBodegaRgbEstado.getParent().setVisible(true);
	}

	public void onBorrar() {
		log.info("metodo [onBorrar]");

		try {

			ParametrizacionFac.getFacade().borrarRegistro("deleteBodega", dtoSeleccionado);
			util.lanzarMensaje("Se elimino ");
			log.info(" Se elimino ");

			util.limpiarCampos(idInvBodegaFormulario);
			onConsultar("");

		}
catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void onCancelar() {
		log.info("Ejecutando el metodo [ onCancelar ]...");

		util.limpiarCampos(idInvBodegaFormulario);
		util.deshabilitarCampos(idInvBodegaFormulario);

		idInvBodegaBtnGuardar.setDisabled(true);
		idInvBodegaBtnCancelar.setDisabled(true);
		idInvBodegaBtnEditar.setDisabled(true);
		idInvBodegaBtnBorrar.setDisabled(true);
		idInvBodegaBtnNuevo.setDisabled(false);

	}
	
	public void llenarBandboxPersona(String criterio) {
		List<Persona> listaPersona;
		Persona persona = new Persona();
		persona.setNombres("%" + (criterio.equals(" ") ? "" : criterio) + "%");
		persona.setIdentificacion("%" + criterio + "%");
		log.info("parmatro: " + persona.getNombres());
		
		try {			
			listaPersona = (ArrayList<Persona>) ParametrizacionFac.getFacade().obtenerListado("getPersona", persona);
			log.info("Hay " + listaPersona.size() + " datos encontradas.");						
				idInvBodegalistboxBandboxPersona.getItems().clear();
				for (final Persona dto : listaPersona) {
					log.info("persona: " + dto.getNombres());
					Listitem listItem = new Listitem();
					listItem.setValue(dto.getSecuencia() + "");
					listItem.setLabel(dto.getNombres());
					listItem.setAttribute("persona", dto);
					listItem.getAttribute("persona");
					idInvBodegalistboxBandboxPersona.appendChild(listItem);
				}
			
		} catch (Exception e) {
			// TODO: handle exception
			log.info("");
		}
	}
}
 