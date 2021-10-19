package com.mochila.gen.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
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

public class UsuarioAction extends Borderlayout implements AfterCompose {
	protected static Logger log = Logger.getLogger(UsuarioAction.class);

	Textbox idGenUsuarioTbxBusqueda;
	Listbox idGenUsuarioLbxUsuario;
	Toolbarbutton idGenUsuarioBtnNuevo;
	Toolbarbutton idGenUsuarioBtnEditar;
	Toolbarbutton idGenUsuarioBtnGuardar;
	Toolbarbutton idGenUsuarioBtnBorrar;
	Toolbarbutton idGenUsuarioBtnCancelar;
	Rows idGenUsuarioFormulario;
	Textbox idGenUsuarioTbxOperacion;
Textbox idGenUsuarioTbxSecuencia;
Textbox idGenUsuarioTbxPersona;
Textbox idGenUsuarioTbxGrupo;
Textbox idGenUsuarioTbxNombreUsuario;
Textbox idGenUsuarioTbxCorreoElectronico;
Textbox idGenUsuarioTbxClave;
Radiogroup idGenUsuarioRgbEstado;
Textbox idGenUsuarioTbxDescripcion;

Bandbox idGenUsuarioBandboxPersona;
Listbox idGenUsuariolistboxBandboxPersona;

	UtilZKProcesosComunesHelper util = new UtilZKProcesosComunesHelper();
	Usuario dtoSeleccionado = new Usuario();
	Usuario usuario = new Usuario();
	ArrayList<Usuario> listaUsuario = new ArrayList<Usuario>();

@Override
	public void afterCompose() {
		// TODO Auto-generated method stub
		cargar();
		usuario = (Usuario) Executions.getCurrent().getSession().getAttribute("USUARIO_SESSION");
		log.info("Usuario: " + usuario.getNombreUsuario());
	}

	public void cargar() {
		idGenUsuarioTbxBusqueda = (Textbox) this.getFellow("idGenUsuarioTbxBusqueda");
		idGenUsuarioLbxUsuario = (Listbox) this.getFellow("idGenUsuarioLbxUsuario");
		idGenUsuarioBtnNuevo = (Toolbarbutton) this.getFellow("idGenUsuarioBtnNuevo");
		idGenUsuarioBtnEditar = (Toolbarbutton) this.getFellow("idGenUsuarioBtnEditar");
		idGenUsuarioBtnGuardar = (Toolbarbutton) this.getFellow("idGenUsuarioBtnGuardar");
		idGenUsuarioBtnBorrar = (Toolbarbutton) this.getFellow("idGenUsuarioBtnBorrar");
		idGenUsuarioBtnCancelar = (Toolbarbutton) this.getFellow("idGenUsuarioBtnCancelar");
		idGenUsuarioFormulario = (Rows) this.getFellow("idGenUsuarioFormulario");
		idGenUsuarioTbxOperacion = (Textbox) this.getFellow("idGenUsuarioTbxOperacion");
idGenUsuarioTbxSecuencia = (Textbox ) this.getFellow("idGenUsuarioTbxSecuencia");
idGenUsuarioTbxPersona = (Textbox ) this.getFellow("idGenUsuarioTbxPersona");
idGenUsuarioTbxGrupo = (Textbox ) this.getFellow("idGenUsuarioTbxGrupo");
idGenUsuarioTbxNombreUsuario = (Textbox ) this.getFellow("idGenUsuarioTbxNombreUsuario");
idGenUsuarioTbxCorreoElectronico = (Textbox ) this.getFellow("idGenUsuarioTbxCorreoElectronico");
idGenUsuarioTbxClave = (Textbox ) this.getFellow("idGenUsuarioTbxClave");
idGenUsuarioRgbEstado = (Radiogroup ) this.getFellow("idGenUsuarioRgbEstado");
idGenUsuarioTbxDescripcion = (Textbox ) this.getFellow("idGenUsuarioTbxDescripcion");

//BandboxPersona
idGenUsuarioBandboxPersona = (Bandbox ) this.getFellow("idGenUsuarioBandboxPersona");
idGenUsuariolistboxBandboxPersona = (Listbox ) this.getFellow("idGenUsuariolistboxBandboxPersona");
}

 	public void onConsultar(String criterio) {
		log.info("Ejecutando el metodo [ onConsultar ]... ");
		idGenUsuarioLbxUsuario.getItems().clear();
		dtoSeleccionado = null;

		idGenUsuarioFormulario.invalidate();
		idGenUsuarioBtnCancelar.setDisabled(true);
		idGenUsuarioBtnGuardar.setDisabled(true);
		idGenUsuarioBtnEditar.setDisabled(true);
		idGenUsuarioBtnBorrar.setDisabled(true);

		util.limpiarCampos(idGenUsuarioFormulario);
		util.deshabilitarCampos(idGenUsuarioFormulario);

		log.info("parmatro:" + criterio);

		Usuario usuariox = new Usuario();
		usuariox.setNombreUsuario("%" + (criterio.equals(" ") ? "" : criterio) + "%");
		try {
			listaUsuario = (ArrayList<Usuario>) ParametrizacionFac.getFacade().obtenerListado("getUsuario", usuariox);

			log.info("Hay " + listaUsuario.size() + " datos encontrados.");

			for (final Usuario dto : listaUsuario) {
				Listitem listItem = util.crearListitem(dto, dto.getEstado(), dto.getNombreUsuario());
				listItem.addEventListener(Events.ON_CLICK, new EventListener() {
					public void onEvent(Event event) throws Exception {

						onSeleccionar(dto);
						// listItemSeleccionado=listItem;
						// listItem.setSelected(true);

					}
				});
				idGenUsuarioLbxUsuario.appendChild(listItem);

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

public void onGuardar() {
		log.info("Ejecutando el metodo [onGuardar ]... ");
		String op = idGenUsuarioTbxOperacion.getValue();
		ArrayList<String> arrayRequired = new ArrayList<String>();

		if (idGenUsuarioTbxPersona.getValue().isEmpty()) {arrayRequired.add("PERSONA");}

		if (idGenUsuarioTbxGrupo.getValue().isEmpty()) {arrayRequired.add("GRUPO");}

		if (idGenUsuarioTbxNombreUsuario.getValue().isEmpty()) {arrayRequired.add("NOMBREUSUARIO");}

		if (idGenUsuarioTbxCorreoElectronico.getValue().isEmpty()) {arrayRequired.add("CORREOELECTRONICO");}

		if (idGenUsuarioTbxClave.getValue().isEmpty()) {arrayRequired.add("CLAVE");}

		if (arrayRequired.size() > 0) {
			log.info("Faltan campos obligatorios por llenar");
			util.lanzarMensajeCamposFatantes("faltan campos obligatorios por llenar", arrayRequired);

		} else {
			Usuario usuario = new Usuario();
			Persona persona= new Persona();
			persona.setSecuencia(new Long(idGenUsuarioTbxPersona.getValue()));
			persona.setNombres(idGenUsuarioBandboxPersona.getValue());
			usuario.setPersona(persona);

			usuario.setGrupo(new Long(idGenUsuarioTbxGrupo.getValue()));

			usuario.setNombreUsuario(idGenUsuarioTbxNombreUsuario.getValue());

			usuario.setCorreoElectronico(idGenUsuarioTbxCorreoElectronico.getValue());

			usuario.setClave(idGenUsuarioTbxClave.getValue());

			usuario.setEstado(idGenUsuarioRgbEstado.getSelectedIndex() == 0 ? "A" : "I");

			usuario.setDescripcion(idGenUsuarioTbxDescripcion.getValue());

			log.info("Parametro: " + usuario.toString());
			if (idGenUsuarioTbxOperacion.getValue().equals("U")) {
				log.info(" metodo [actualizar]");
				usuario.setSecuencia(new Long(idGenUsuarioTbxSecuencia.getValue()));
				usuario.setFechaActualizacion(new Date());
				usuario.setUsuarioActualizacion(this.usuario);
				usuario.setFechaCreacion(dtoSeleccionado.getFechaCreacion());
				usuario.setUsuarioCreacion(dtoSeleccionado);

				try {
					ParametrizacionFac.getFacade().insertarRegistro("putUsuario", usuario);
					util.deshabilitarCampos(idGenUsuarioFormulario);
					onConsultar("");
					this.onSeleccionar(usuario);

					log.info("Actualizado");
					util.lanzarMensaje("Actualizado");
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (op.equals("N")) {
				log.info("metodo [Guardar]");
				try {
					usuario.setFechaCreacion(new Date());
					usuario.setUsuarioCreacion(this.usuario);
					ParametrizacionFac.getFacade().insertarRegistro("postUsuario", usuario);
					util.deshabilitarCampos(idGenUsuarioFormulario);
					onConsultar("");
					this.onSeleccionar(usuario);
					log.info("Guardado");
					util.lanzarMensaje("Guardado");
				} catch (Exception e) {
					util.lanzarMensaje("Error:::> " + e);
					log.info("Error:::> " + e);
					e.printStackTrace();
				}
				idGenUsuarioBtnGuardar.setDisabled(true);
				idGenUsuarioBtnCancelar.setDisabled(true);
				idGenUsuarioBtnNuevo.setDisabled(false);
			}
		}
	}

	public void onSeleccionar(Usuario dto) {
		log.info(" Ejecutando el metodo [ onSeleccionar ]... ");

		dtoSeleccionado = dto;

		util.deshabilitarCampos(idGenUsuarioFormulario);
		util.limpiarCampos(idGenUsuarioFormulario);

		idGenUsuarioBtnNuevo.setDisabled(false);
		idGenUsuarioBtnGuardar.setDisabled(true);
		idGenUsuarioBtnCancelar.setDisabled(true);
		idGenUsuarioBtnEditar.setDisabled(false);
		idGenUsuarioBtnBorrar.setDisabled(false);
		// logica
		idGenUsuarioTbxSecuencia.setValue(dto.getSecuencia() + "");		
		idGenUsuarioTbxPersona.setValue(dto.getPersona().getSecuencia() + "");
		idGenUsuarioBandboxPersona.setValue(dto.getPersona().getNombres() + "");
		idGenUsuarioTbxGrupo.setValue(dto.getGrupo() + "");
		idGenUsuarioTbxNombreUsuario.setValue(dto.getNombreUsuario() + "");
		idGenUsuarioTbxCorreoElectronico.setValue(dto.getCorreoElectronico() + "");
		idGenUsuarioTbxClave.setValue(dto.getClave() + "");
		idGenUsuarioRgbEstado.setSelectedIndex(dto.getEstado().equals("A") ? 0 : 1);
		idGenUsuarioTbxDescripcion.setValue(dto.getDescripcion() + "");
		
		
	}

public void onNuevo() {
		log.info("Ejecutando el metodo [ onNuevo]... ");

		dtoSeleccionado = null;

		idGenUsuarioBtnNuevo.setDisabled(true);
		util.habilitarCampos(idGenUsuarioFormulario);
		util.limpiarCampos(idGenUsuarioFormulario);
		util.seleccionarRadio(idGenUsuarioRgbEstado, "A");

		idGenUsuarioTbxOperacion.setValue("N");
		idGenUsuarioRgbEstado.getParent().setVisible(false);
		idGenUsuarioFormulario.invalidate();

		idGenUsuarioBtnCancelar.setDisabled(false);
		idGenUsuarioBtnEditar.setDisabled(true);
		idGenUsuarioBtnBorrar.setDisabled(true);
		idGenUsuarioBtnGuardar.setDisabled(false);

	}

	public void onEditar() {
		log.info("Ejecutando el metodo [ onEditar ]...");
		util.habilitarCampos(idGenUsuarioFormulario);

		/* default en modo edicion */

		idGenUsuarioBtnBorrar.setDisabled(true);
		idGenUsuarioBtnEditar.setDisabled(true);
		idGenUsuarioBtnGuardar.setDisabled(false);
		idGenUsuarioBtnCancelar.setDisabled(false);
		idGenUsuarioTbxOperacion.setValue("U");
		idGenUsuarioRgbEstado.getParent().setVisible(true);
	}

	public void onBorrar() {
		log.info("metodo [onBorrar]");

		try {

			ParametrizacionFac.getFacade().borrarRegistro("deleteUsuario", dtoSeleccionado);
			util.lanzarMensaje("Se elimino ");
			log.info(" Se elimino ");

			util.limpiarCampos(idGenUsuarioFormulario);
			onConsultar("");

		}
catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void onCancelar() {
		log.info("Ejecutando el metodo [ onCancelar ]...");

		util.limpiarCampos(idGenUsuarioFormulario);
		util.deshabilitarCampos(idGenUsuarioFormulario);

		idGenUsuarioBtnGuardar.setDisabled(true);
		idGenUsuarioBtnCancelar.setDisabled(true);
		idGenUsuarioBtnEditar.setDisabled(true);
		idGenUsuarioBtnBorrar.setDisabled(true);
		idGenUsuarioBtnNuevo.setDisabled(false);

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
				idGenUsuariolistboxBandboxPersona.getItems().clear();
				for (final Persona dto : listaPersona) {
					log.info("persona: " + dto.getNombres());
					Listitem listItem = new Listitem();
					listItem.setValue(dto.getSecuencia() + "");
					listItem.setLabel(dto.getNombres());
					listItem.setAttribute("persona", dto);
					listItem.getAttribute("persona");
					idGenUsuariolistboxBandboxPersona.appendChild(listItem);
				}
			
		} catch (Exception e) {
			// TODO: handle exception
			log.info("");
		}
	}
}
 