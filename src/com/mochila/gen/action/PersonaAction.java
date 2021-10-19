package com.mochila.gen.action;

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
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Radiogroup;
import org.zkoss.zul.Row;
import org.zkoss.zul.Rows;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Toolbarbutton;

import com.mochila.framework.facade.ParametrizacionFac;
import com.mochila.framework.helper.UtilZKProcesosComunesHelper;
import com.mochila.gen.dto.Persona;
import com.mochila.gen.dto.TipoDistribuidor;
import com.mochila.gen.dto.Usuario;

public class PersonaAction extends Borderlayout implements AfterCompose {
	protected static Logger log = Logger.getLogger(PersonaAction.class);

	Textbox idGenPersonaTbxBusqueda;
	Listbox idGenPersonaLbxPersonas;
	Toolbarbutton idGenPersonaBtnNuevo;
	Toolbarbutton idGenPersonaBtnEditar;
	Toolbarbutton idGenPersonaBtnGuardar;
	Toolbarbutton idGenPersonaBtnBorrar;
	Toolbarbutton idGenPersonaBtnCancelar;

	Rows idGenPersonaFormulario;

	Row idGenPersonaRowBandBoxTipoDistribuidor;

	Textbox idGenPersonaTbxOperacion;
	Textbox idGenPersonaTbxSecuencia;
	Textbox idGenPersonaTbxNombre;
	Textbox idGenPersonaTbxApellido;
	Textbox idGenPersonaTbxIdentificacion;
	Textbox idGenPersonaTbxDireccion;
	Textbox idGenPersonaTbxTelefono;
	Textbox idGenPersonaTbxWspTelefono;
	Textbox idGenPersonaTbxCorreo;
	Textbox idGenPersonaTbxTipoDistribuidor; /// IJI

	Datebox idGenPersonaDtbFechaNacimiento;
	Radiogroup idGenPersonaRgbEstado;
	Radiogroup idGenPersonaRgbGenero;

	Listbox idGenPersonaListboxBandboxTipoDistribuidor; /// OJO
	Listbox idGenPersonaLbxTipoDocumento;
	Checkbox idGenPersonaCbxCliente;  
	Checkbox idGenPersonaCbxProveedor;
	Checkbox idGenPersonaCbxDistribuidor;

	Bandbox idGenPersonaBandboxTipoDistribuidor; /// OOJ

	UtilZKProcesosComunesHelper util = new UtilZKProcesosComunesHelper();
	Persona dtoSeleccionado = new Persona();
	Usuario usuario = new Usuario();
	ArrayList<Persona> personas = new ArrayList<Persona>();

	@Override
	public void afterCompose() {
		// TODO Auto-generated method stub
		cargar();		
		usuario = (Usuario) Executions.getCurrent().getSession().getAttribute("USUARIO_SESSION");
		log.info("Usuario: " + usuario.getNombreUsuario());
	}

	public void cargar() {
		idGenPersonaTbxBusqueda = (Textbox) this.getFellow("idGenPersonaTbxBusqueda");
		idGenPersonaLbxPersonas = (Listbox) this.getFellow("idGenPersonaLbxPersonas");
		idGenPersonaBtnNuevo = (Toolbarbutton) this.getFellow("idGenPersonaBtnNuevo");
		idGenPersonaBtnEditar = (Toolbarbutton) this.getFellow("idGenPersonaBtnEditar");
		idGenPersonaBtnGuardar = (Toolbarbutton) this.getFellow("idGenPersonaBtnGuardar");
		idGenPersonaBtnBorrar = (Toolbarbutton) this.getFellow("idGenPersonaBtnBorrar");
		idGenPersonaBtnCancelar = (Toolbarbutton) this.getFellow("idGenPersonaBtnCancelar");

		idGenPersonaFormulario = (Rows) this.getFellow("idGenPersonaFormulario");
		idGenPersonaRowBandBoxTipoDistribuidor = (Row) this.getFellow("idGenPersonaRowBandBoxTipoDistribuidor");

		idGenPersonaTbxOperacion = (Textbox) this.getFellow("idGenPersonaTbxOperacion");
		idGenPersonaTbxSecuencia = (Textbox) this.getFellow("idGenPersonaTbxSecuencia");
		idGenPersonaTbxNombre = (Textbox) this.getFellow("idGenPersonaTbxNombre");
		idGenPersonaTbxApellido = (Textbox) this.getFellow("idGenPersonaTbxApellido");
		idGenPersonaTbxIdentificacion = (Textbox) this.getFellow("idGenPersonaTbxIdentificacion");
		idGenPersonaTbxDireccion = (Textbox) this.getFellow("idGenPersonaTbxDireccion");
		idGenPersonaTbxTelefono = (Textbox) this.getFellow("idGenPersonaTbxTelefono");
		idGenPersonaTbxWspTelefono = (Textbox) this.getFellow("idGenPersonaTbxWspTelefono");
		idGenPersonaTbxCorreo = (Textbox) this.getFellow("idGenPersonaTbxCorreo");
		idGenPersonaTbxTipoDistribuidor = (Textbox) this.getFellow("idGenPersonaTbxTipoDistribuidor");

		idGenPersonaDtbFechaNacimiento = (Datebox) this.getFellow("idGenPersonaDtbFechaNacimiento");
		idGenPersonaRgbEstado = (Radiogroup) this.getFellow("idGenPersonaRgbEstado");
		idGenPersonaRgbGenero = (Radiogroup) this.getFellow("idGenPersonaRgbGenero");
		
		
		idGenPersonaCbxCliente = (Checkbox) this.getFellow("idGenPersonaCbxCliente");
		idGenPersonaCbxProveedor = (Checkbox) this.getFellow("idGenPersonaCbxProveedor");
		idGenPersonaCbxDistribuidor = (Checkbox) this.getFellow("idGenPersonaCbxDistribuidor");

		idGenPersonaListboxBandboxTipoDistribuidor = (Listbox) this
				.getFellow("idGenPersonaListboxBandboxTipoDistribuidor");
		idGenPersonaLbxTipoDocumento = (Listbox) this.getFellow("idGenPersonaLbxTipoDocumento");

		idGenPersonaBandboxTipoDistribuidor = (Bandbox) this.getFellow("idGenPersonaBandboxTipoDistribuidor");
		/*
		 * CUANDO SE PIERDE EL FOCO DEL CAMPO DE CEDULA VALIDA SI EXISTE
		 * idGenPersonaTbxIdentificacion.addEventListener(Events.ON_BLUR, new
		 * EventListener() {
		 * 
		 * @Override public void onEvent(Event event) throws Exception {
		 * 
		 * Persona persona = new Persona(); persona.setNombres(null);
		 * persona.setIdentificacion(idGenPersonaTbxIdentificacion.getText());
		 * 
		 * personas.clear(); personas = (ArrayList<Persona>)
		 * ParametrizacionFac.getFacade().obtenerListado("getPersona", persona); boolean
		 * existe = false;
		 * 
		 * for (Persona dto : personas) { if
		 * (dto.getIdentificacion().equals((idGenPersonaTbxIdentificacion).getText())) {
		 * existe = true; } }
		 * 
		 * if (existe) { idGenPersonaTbxIdentificacion.setText("");
		 * Messagebox.show("La cedula ya existe!", "Advertencia", Messagebox.OK,
		 * Messagebox.EXCLAMATION);
		 * 
		 * } } });
		 */
	}

	public void onConsultar(String criterio) {
		log.info("Ejecutando el metodo [ onConsultar ]... ");
		idGenPersonaLbxPersonas.getItems().clear();
		dtoSeleccionado = null;

		idGenPersonaFormulario.invalidate();
		idGenPersonaBtnCancelar.setDisabled(true);
		idGenPersonaBtnGuardar.setDisabled(true);
		idGenPersonaBtnEditar.setDisabled(true);
		idGenPersonaBtnBorrar.setDisabled(true);

		util.limpiarCampos(idGenPersonaFormulario);
		util.deshabilitarCampos(idGenPersonaFormulario);

		
		log.info("parmatro:" + criterio);

		Persona persona = new Persona();
		persona.setNombres("%" + (criterio.equals(" ") ? "" : criterio) + "%");
		persona.setIdentificacion("%" + criterio + "%");
		try {
			personas = (ArrayList<Persona>) ParametrizacionFac.getFacade().obtenerListado("getPersona", persona);

			log.info("Hay " + personas.size() + " datos encontradas.");

			for (final Persona dto : personas) {
				Listitem listItem = util.crearListitem(dto, dto.getEstado(), dto.getNombres());
				log.info("distr traido:" + dto.getSecuenciaTipoDistribuidor());
				listItem.addEventListener(Events.ON_CLICK, new EventListener() {
					@Override
					public void onEvent(Event event) throws Exception {

						onSeleccionar(dto);
						// listItemSeleccionado=listItem;
						// listItem.setSelected(true);

					}
				});
				idGenPersonaLbxPersonas.appendChild(listItem);

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.info("Error: "+e);
			e.printStackTrace();
		}

	}

	public void onGuardar() {
		log.info("Ejecutando el metodo [onGuardar ]... ");
		String op = idGenPersonaTbxOperacion.getValue();
		ArrayList<String> arrayRequired = new ArrayList<String>();

		Persona persona = new Persona();
		persona.setIdentificacion(idGenPersonaTbxIdentificacion.getText());

		// BUSCAR LA CEDULA EN LA BD
		try {
			personas = (ArrayList<Persona>) ParametrizacionFac.getFacade().obtenerListado("getPersona", persona);
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		boolean existe = false;
		if (personas.size() > 0) {
			for (Persona dto : personas) {
				if (dto.getIdentificacion().equals((idGenPersonaTbxIdentificacion).getText())) {
					existe = true;
				}
			}
		}

		if (idGenPersonaTbxNombre.getValue().isEmpty()) {
			arrayRequired.add("Nombre");
		}
		if (idGenPersonaTbxApellido.getValue().isEmpty()) {
			arrayRequired.add("APERLLIDOS");
		}
		if (idGenPersonaTbxCorreo.getValue().isEmpty()) {
			arrayRequired.add("EMAIL");
		}
		if (idGenPersonaTbxIdentificacion.getValue().isEmpty()) {
			arrayRequired.add("IDENTIFICACION");
		}

		if (arrayRequired.size() > 0) {
			log.info("Faltan campos obligatorios por llenar");
			util.lanzarMensajeCamposFatantes("Faltan campos obligatorios por llenar", arrayRequired);

		} else if (existe && op.equals("N")) {
			idGenPersonaTbxIdentificacion.setText("");
			Messagebox.show("La cedula ya existe!", "Advertencia", Messagebox.OK, Messagebox.EXCLAMATION);
		} else {

			persona = new Persona();

			persona.setNombres(idGenPersonaTbxNombre.getValue().toUpperCase());
			persona.setApellidos(idGenPersonaTbxApellido.getValue().toUpperCase());
			persona.setIdentificacion(idGenPersonaTbxIdentificacion.getValue());
			persona.setDireccion(idGenPersonaTbxDireccion.getValue().toUpperCase());
			persona.setCorreoElectronico(idGenPersonaTbxCorreo.getValue().toUpperCase());
			persona.setTelefono(idGenPersonaTbxTelefono.getValue());
			persona.setWspTelefono(idGenPersonaTbxWspTelefono.getValue());
			persona.setGenero(idGenPersonaRgbGenero.getSelectedIndex() == 0 ? "M" : "F");
			persona.setFechaNacimiento(idGenPersonaDtbFechaNacimiento.getValue());
			
			persona.setTipoIdentificacion(idGenPersonaLbxTipoDocumento.getSelectedItem().getValue());
			

			persona.setEstado(idGenPersonaRgbEstado.getSelectedIndex() == 0 ? "A" : "I");
			persona.setDescripcion("");

			persona.setEtiquetaCliente(idGenPersonaCbxCliente.isChecked() ? "S" : "N");
			persona.setEtiquetaProveedor(idGenPersonaCbxProveedor.isChecked() ? "S" : "N");
			persona.setEtiquetaDistribuidor(idGenPersonaCbxDistribuidor.isChecked() ? "S" : "N");

			persona.setSecUsuarioCreacion(usuario.getSecuencia());

			if (idGenPersonaCbxDistribuidor.isChecked()) {
				// Obtener segun el distribuidor seleccionado su ID
				TipoDistribuidor distr = new TipoDistribuidor();
				distr.setNombre(idGenPersonaBandboxTipoDistribuidor.getValue()); // Asignar un valor al nombre del dist
																					// para
																					// realizar la busqueda
				List<TipoDistribuidor> distribuidores = new ArrayList<TipoDistribuidor>();
				try {
					distribuidores = (ArrayList<TipoDistribuidor>) ParametrizacionFac.getFacade() // Realizar la
																									// busqueda
																									// por el nombre
							.obtenerListado("getTipoDistribuidor", distr);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				persona.setSecuenciaTipoDistribuidor(distribuidores.get(0));
			}
			log.info("Parametro: " + persona.toString());

			if (idGenPersonaTbxOperacion.getValue().equals("U")) {
				log.info(" metodo [actualizar]");
				if (idGenPersonaTbxSecuencia.getValue() != null) {
					persona.setSecuencia(new Long(idGenPersonaTbxSecuencia.getValue()));
				}
				persona.setFechaActualizacion(new Date());
				persona.setSecUsuarioActualizacion(usuario.getSecuencia());
				persona.setFechaCreacion(dtoSeleccionado.getFechaCreacion());
				persona.setSecUsuarioCreacion(dtoSeleccionado.getSecUsuarioCreacion());

				try {
					ParametrizacionFac.getFacade().insertarRegistro("putPersona", persona);

					util.deshabilitarCampos(idGenPersonaFormulario);
					onConsultar("");
					this.onSeleccionar(persona);

					log.info("Actualizado");
					util.lanzarMensaje("Actualizado");

				} catch (Exception e) {
					e.printStackTrace();
				}

			} else if (op.equals("N")) {
				log.info("metodo [Guardar]");
				try {

					persona.setFechaCreacion(new Date());
					persona.setSecuenciaUsuarioCreacion(usuario.getSecuencia());
					

					ParametrizacionFac.getFacade().insertarRegistro("postPersona", persona);
					util.deshabilitarCampos(idGenPersonaFormulario);
					onConsultar("");
					this.onSeleccionar(persona);

					log.info("Guardado");
					util.lanzarMensaje("Guardado");

				} catch (Exception e) {
					util.lanzarMensaje("Error:::> " + e);
					log.info("Error:::> " + e);
					e.printStackTrace();
				}

				idGenPersonaBtnGuardar.setDisabled(true);
				idGenPersonaBtnCancelar.setDisabled(true);
				idGenPersonaBtnNuevo.setDisabled(false);

			}

		}
	}

	public void onSeleccionar(Persona dto) {
		log.info(" Ejecutando el metodo [ onSeleccionar ]... ");

		dtoSeleccionado = dto;

		util.deshabilitarCampos(idGenPersonaFormulario);
		util.limpiarCampos(idGenPersonaFormulario);

		idGenPersonaBtnNuevo.setDisabled(false);
		idGenPersonaBtnGuardar.setDisabled(true);
		idGenPersonaBtnCancelar.setDisabled(true);
		idGenPersonaBtnEditar.setDisabled(false);
		idGenPersonaBtnBorrar.setDisabled(false);

		// logica
		idGenPersonaTbxSecuencia.setValue(dto.getSecuencia() + "");
		idGenPersonaTbxNombre.setValue(dto.getNombres());
		idGenPersonaTbxApellido.setValue(dto.getApellidos());
		idGenPersonaTbxIdentificacion.setValue(dto.getIdentificacion());
		idGenPersonaTbxDireccion.setValue(dto.getDireccion());

		idGenPersonaLbxTipoDocumento.setSelectedIndex(dto.getTipoIdentificacion().equals("CC") ? 0
				: dto.getTipoIdentificacion().equals("CE") ? 1
						: dto.getTipoIdentificacion().equals("PA") ? 2
								: dto.getTipoIdentificacion().equals("NI") ? 3 : null);

		idGenPersonaTbxCorreo.setValue(dto.getCorreoElectronico());
		idGenPersonaTbxTelefono.setValue(dto.getTelefono());
		idGenPersonaTbxWspTelefono.setValue(dto.getWspTelefono());
		idGenPersonaDtbFechaNacimiento.setValue(dto.getFechaNacimiento());

		if (dto.getSecuenciaTipoDistribuidor() != null) {
			idGenPersonaBandboxTipoDistribuidor.setValue(dto.getSecuenciaTipoDistribuidor().getNombre() + "");
		}

		idGenPersonaRgbGenero.setSelectedIndex(dto.getGenero().equals("M") ? 0 : 1);
		idGenPersonaRgbEstado.setSelectedIndex(dto.getEstado().equals("A") ? 0 : 1);

		idGenPersonaCbxCliente.setChecked(dto.getEtiquetaCliente().equals("N") ? false : true);
		idGenPersonaCbxProveedor.setChecked(dto.getEtiquetaProveedor().equals("N") ? false : true);
		idGenPersonaCbxDistribuidor.setChecked(dto.getEtiquetaDistribuidor().equals("N") ? false : true);
		idGenPersonaRowBandBoxTipoDistribuidor.setVisible(idGenPersonaCbxDistribuidor.isChecked());

	}

	public void onNuevo() {
		log.info("Ejecutando el metodo [ onNuevo]... ");

		dtoSeleccionado = null;

		idGenPersonaBtnNuevo.setDisabled(true);
		util.habilitarCampos(idGenPersonaFormulario);
		util.limpiarCampos(idGenPersonaFormulario);
		util.seleccionarRadio(idGenPersonaRgbEstado, "A");

		idGenPersonaTbxOperacion.setValue("N");
		idGenPersonaRgbEstado.getParent().setVisible(false);
		idGenPersonaFormulario.invalidate();

		idGenPersonaBtnCancelar.setDisabled(false);
		idGenPersonaBtnEditar.setDisabled(true);
		idGenPersonaBtnBorrar.setDisabled(true);
		idGenPersonaBtnGuardar.setDisabled(false);
	}

	public void onEditar() {
		log.info("Ejecutando el metodo [ onEditar ]...");
		util.habilitarCampos(idGenPersonaFormulario);

		/* default en modo edicion */

		idGenPersonaBtnBorrar.setDisabled(true);
		idGenPersonaBtnEditar.setDisabled(true);
		idGenPersonaBtnGuardar.setDisabled(false);
		idGenPersonaBtnCancelar.setDisabled(false);
		idGenPersonaTbxOperacion.setValue("U");
		idGenPersonaRgbEstado.getParent().setVisible(true);
		idGenPersonaTbxIdentificacion.setDisabled(true); // La cedula no se puede editar
	}

	public void onBorrar() {
		log.info("metodo [onBorrar]");

		try {

			ParametrizacionFac.getFacade().borrarRegistro("deletePersona", dtoSeleccionado);
			util.lanzarMensaje("Se elimino ");
			log.info(" Se elimino ");

			util.limpiarCampos(idGenPersonaFormulario);
			onConsultar("");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void onCancelar() {
		log.info("Ejecutando el metodo [ onCancelar ]...");

		util.limpiarCampos(idGenPersonaFormulario);
		util.deshabilitarCampos(idGenPersonaFormulario);

		idGenPersonaBtnGuardar.setDisabled(true);
		idGenPersonaBtnCancelar.setDisabled(true);
		idGenPersonaBtnEditar.setDisabled(true);
		idGenPersonaBtnBorrar.setDisabled(true);
		idGenPersonaBtnNuevo.setDisabled(false);

	}

	public void llenarBandboxTipoDistribuidor(String criterio) {
		List<TipoDistribuidor> distribuidores;
		TipoDistribuidor tipo = new TipoDistribuidor();
		tipo.setNombre("%" + (criterio.equals(" ") ? "" : criterio) + "%");
//		tipo.setSecuencia(Integer.parseInt("%" + criterio + "%"));
		log.info("parmatro: " + tipo.getNombre());

		try {
			distribuidores = (ArrayList<TipoDistribuidor>) ParametrizacionFac.getFacade()
					.obtenerListado("getTipoDistribuidor", tipo);
			log.info("Hay " + distribuidores.size() + " datos encontradas.");
			idGenPersonaListboxBandboxTipoDistribuidor.getItems().clear();
			for (final TipoDistribuidor dto : distribuidores) {
				log.info("Distribuidor: " + dto.getNombre());
				Listitem listItem = new Listitem();
				listItem.setValue(dto.getSecuencia() + "");
				listItem.setLabel(dto.getNombre());
				listItem.setAttribute("distribuidor", dto);
				listItem.getAttribute("distribuidor");
				idGenPersonaListboxBandboxTipoDistribuidor.appendChild(listItem);
			}
		} catch (Exception e) {
			// TODO: handle exception
			log.info("");
		}
	}

}
