package com.mochila.inv.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import org.apache.log4j.Logger;
import org.zkoss.image.AImage;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.ext.AfterCompose;
import org.zkoss.zul.Borderlayout;
import org.zkoss.zul.Button;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Image;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Radiogroup;
import org.zkoss.zul.Rows;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Toolbarbutton;
import com.mochila.framework.facade.ParametrizacionFac;
import com.mochila.framework.helper.UtilZKProcesosComunesHelper;
import com.mochila.gen.dto.Usuario;
import com.mochila.inv.dto.TipoProducto;

public class TipoProductoAction extends Borderlayout implements AfterCompose {
	protected static Logger log = Logger.getLogger(TipoProductoAction.class);

	Textbox idInvTipoProductoTbxBusqueda;
	Listbox idInvTipoProductoLbxTipoProducto;
	Toolbarbutton idInvTipoProductoBtnNuevo;
	Toolbarbutton idInvTipoProductoBtnEditar;
	Toolbarbutton idInvTipoProductoBtnGuardar;
	Toolbarbutton idInvTipoProductoBtnBorrar;
	Toolbarbutton idInvTipoProductoBtnCancelar;
	Rows idInvTipoProductoFormulario;
	Textbox idInvTipoProductoTbxOperacion;
Textbox idInvTipoProductoTbxSecuencia;
Textbox idInvTipoProductoTbxNombre;
Radiogroup idInvTipoProductoRgbEstado;
Textbox idInvTipoProductoTbxDescripcion;
Image idInvTipoProductoImageImg;
Button idInvTipoProductoBtnImagen; 

	UtilZKProcesosComunesHelper util = new UtilZKProcesosComunesHelper();
	TipoProducto dtoSeleccionado = new TipoProducto();
	Usuario usuario = new Usuario();
	ArrayList<TipoProducto> listaTipoProducto = new ArrayList<TipoProducto>();

@Override
	public void afterCompose() {
		// TODO Auto-generated method stub
		cargar();
		usuario = (Usuario) Executions.getCurrent().getSession().getAttribute("USUARIO_SESSION");
		log.info("Usuario: " + usuario.getNombreUsuario());
	}

	public void cargar() {
		idInvTipoProductoTbxBusqueda = (Textbox) this.getFellow("idInvTipoProductoTbxBusqueda");
		idInvTipoProductoLbxTipoProducto = (Listbox) this.getFellow("idInvTipoProductoLbxTipoProducto");
		idInvTipoProductoBtnNuevo = (Toolbarbutton) this.getFellow("idInvTipoProductoBtnNuevo");
		idInvTipoProductoBtnEditar = (Toolbarbutton) this.getFellow("idInvTipoProductoBtnEditar");
		idInvTipoProductoBtnGuardar = (Toolbarbutton) this.getFellow("idInvTipoProductoBtnGuardar");
		idInvTipoProductoBtnBorrar = (Toolbarbutton) this.getFellow("idInvTipoProductoBtnBorrar");
		idInvTipoProductoBtnCancelar = (Toolbarbutton) this.getFellow("idInvTipoProductoBtnCancelar");
		idInvTipoProductoFormulario = (Rows) this.getFellow("idInvTipoProductoFormulario");
		idInvTipoProductoTbxOperacion = (Textbox) this.getFellow("idInvTipoProductoTbxOperacion");
idInvTipoProductoTbxSecuencia = (Textbox ) this.getFellow("idInvTipoProductoTbxSecuencia");
idInvTipoProductoTbxNombre = (Textbox ) this.getFellow("idInvTipoProductoTbxNombre");
idInvTipoProductoRgbEstado = (Radiogroup ) this.getFellow("idInvTipoProductoRgbEstado");
idInvTipoProductoTbxDescripcion = (Textbox ) this.getFellow("idInvTipoProductoTbxDescripcion");
idInvTipoProductoImageImg=(Image) this.getFellow("idInvTipoProductoImageImg");
 idInvTipoProductoBtnImagen=(Button) this.getFellow("idInvTipoProductoBtnImagen");

}

 	public void onConsultar(String criterio) {
		log.info("Ejecutando el metodo [ onConsultar ]... ");
		idInvTipoProductoLbxTipoProducto.getItems().clear();
		dtoSeleccionado = null;

		idInvTipoProductoFormulario.invalidate();
		idInvTipoProductoBtnCancelar.setDisabled(true);
		idInvTipoProductoBtnGuardar.setDisabled(true);
		idInvTipoProductoBtnEditar.setDisabled(true);
		idInvTipoProductoBtnBorrar.setDisabled(true);

		util.limpiarCampos(idInvTipoProductoFormulario);
		util.deshabilitarCampos(idInvTipoProductoFormulario);
		idInvTipoProductoImageImg.setSrc("imagen/imageDefault.png");

		log.info("parmatro:" + criterio);

		TipoProducto tipoProducto = new TipoProducto();
		tipoProducto.setNombre("%" + (criterio.equals(" ") ? "" : criterio) + "%");
		try {
			listaTipoProducto = (ArrayList<TipoProducto>) ParametrizacionFac.getFacade().obtenerListado("getTipoProducto", tipoProducto);

			log.info("Hay " + listaTipoProducto.size() + " datos encontrados.");

			for (final TipoProducto dto : listaTipoProducto) {
				Listitem listItem = util.crearListitem(dto, dto.getEstado(), dto.getNombre());
				listItem.addEventListener(Events.ON_CLICK, new EventListener() {
					public void onEvent(Event event) throws Exception {

						onSeleccionar(dto);
						// listItemSeleccionado=listItem;
						// listItem.setSelected(true);

					}
				});
				idInvTipoProductoLbxTipoProducto.appendChild(listItem);

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

public void onGuardar() {
		log.info("Ejecutando el metodo [onGuardar ]... ");
		String op = idInvTipoProductoTbxOperacion.getValue();
		ArrayList<String> arrayRequired = new ArrayList<String>();

		if (idInvTipoProductoTbxNombre.getValue().isEmpty()) {arrayRequired.add("NOMBRE");}

		if (arrayRequired.size() > 0) {
			log.info("Faltan campos obligatorios por llenar");
			util.lanzarMensajeCamposFatantes("faltan campos obligatorios por llenar", arrayRequired);

		} else {
			TipoProducto tipoProducto = new TipoProducto();
			tipoProducto.setNombre(idInvTipoProductoTbxNombre.getValue());

			tipoProducto.setEstado(idInvTipoProductoRgbEstado.getSelectedIndex() == 0 ? "A" : "I");

			tipoProducto.setDescripcion(idInvTipoProductoTbxDescripcion.getValue());

			log.info("Parametro: " + tipoProducto.toString());
			if (idInvTipoProductoTbxOperacion.getValue().equals("U")) {
				log.info(" metodo [actualizar]");
				tipoProducto.setSecuencia(new Long(idInvTipoProductoTbxSecuencia.getValue()));
				tipoProducto.setFechaActualizacion(new Date());
				tipoProducto.setUsuarioActualizacion(this.usuario);
				tipoProducto.setFechaCreacion(dtoSeleccionado.getFechaCreacion());
				tipoProducto.setUsuarioCreacion(this.usuario);
				if(idInvTipoProductoImageImg.getSrc()==null ) {
					
				tipoProducto.setImg(idInvTipoProductoImageImg.getContent().getByteData());
				
				}

				try {
					ParametrizacionFac.getFacade().insertarRegistro("putTipoProducto", tipoProducto);
					util.deshabilitarCampos(idInvTipoProductoFormulario);
					onConsultar("");
					this.onSeleccionar(tipoProducto);

					log.info("Actualizado");
					util.lanzarMensaje("Actualizado");
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (op.equals("N")) {
				log.info("metodo [Guardar]");
				try {
					tipoProducto.setFechaCreacion(new Date());
					tipoProducto.setUsuarioCreacion(usuario);
					ParametrizacionFac.getFacade().insertarRegistro("postTipoProducto", tipoProducto);
					util.deshabilitarCampos(idInvTipoProductoFormulario);
					onConsultar("");
					this.onSeleccionar(tipoProducto);
					log.info("Guardado");
					util.lanzarMensaje("Guardado");
				} catch (Exception e) {
					util.lanzarMensaje("Error:::> " + e);
					log.info("Error:::> " + e);
					e.printStackTrace();
				}
				idInvTipoProductoBtnGuardar.setDisabled(true);
				idInvTipoProductoBtnCancelar.setDisabled(true);
				idInvTipoProductoBtnNuevo.setDisabled(false);
			}
		}
	}

	public void onSeleccionar(TipoProducto dto) {
		log.info(" Ejecutando el metodo [ onSeleccionar ]... ");

		dtoSeleccionado = dto;

		util.deshabilitarCampos(idInvTipoProductoFormulario);
		util.limpiarCampos(idInvTipoProductoFormulario);

		idInvTipoProductoBtnNuevo.setDisabled(false);
		idInvTipoProductoBtnGuardar.setDisabled(true);
		idInvTipoProductoBtnCancelar.setDisabled(true);
		idInvTipoProductoBtnEditar.setDisabled(false);
		idInvTipoProductoBtnBorrar.setDisabled(false);
		// logica
		idInvTipoProductoTbxSecuencia.setValue(dto.getSecuencia() + "");
		idInvTipoProductoTbxNombre.setValue(dto.getNombre() + "");
		idInvTipoProductoRgbEstado.setSelectedIndex(dto.getEstado().equals("A") ? 0 : 1);
		idInvTipoProductoTbxDescripcion.setValue(dto.getDescripcion() + "");
		if(dto.getImg()!=null){
			try {
				AImage media = new AImage("fgf",dto.getImg());
				idInvTipoProductoImageImg.setContent(media);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				log.info("error al cargar la imagen");
			}	
			}else{
				idInvTipoProductoImageImg.setSrc("imagen/imageDefault.png");
			}
	}

public void onNuevo() {
		log.info("Ejecutando el metodo [ onNuevo]... ");

		dtoSeleccionado = null;

		idInvTipoProductoBtnNuevo.setDisabled(true);
		util.habilitarCampos(idInvTipoProductoFormulario);
		util.limpiarCampos(idInvTipoProductoFormulario);
		util.seleccionarRadio(idInvTipoProductoRgbEstado, "A");

		idInvTipoProductoTbxOperacion.setValue("N");
		idInvTipoProductoRgbEstado.getParent().setVisible(false);
		idInvTipoProductoFormulario.invalidate();

		idInvTipoProductoBtnCancelar.setDisabled(false);
		idInvTipoProductoBtnEditar.setDisabled(true);
		idInvTipoProductoBtnBorrar.setDisabled(true);
		idInvTipoProductoBtnGuardar.setDisabled(false);
		idInvTipoProductoImageImg.setSrc("imagen/imageDefault.png");

	}

	public void onEditar() {
		log.info("Ejecutando el metodo [ onEditar ]...");
		util.habilitarCampos(idInvTipoProductoFormulario);
		idInvTipoProductoBtnImagen.invalidate();
		/* default en modo edicion */

		idInvTipoProductoBtnBorrar.setDisabled(true);
		idInvTipoProductoBtnEditar.setDisabled(true);
		idInvTipoProductoBtnGuardar.setDisabled(false);
		idInvTipoProductoBtnCancelar.setDisabled(false);
		idInvTipoProductoTbxOperacion.setValue("U");
		idInvTipoProductoRgbEstado.getParent().setVisible(true);
	}

	public void onBorrar() {
		log.info("metodo [onBorrar]");

		try {

			ParametrizacionFac.getFacade().borrarRegistro("deleteTipoProducto", dtoSeleccionado);
			util.lanzarMensaje("Se elimino ");
			log.info(" Se elimino ");

			util.limpiarCampos(idInvTipoProductoFormulario);
			idInvTipoProductoImageImg.setSrc("imagen/imageDefault.png");
			onConsultar("");

		}
catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void onCancelar() {
		log.info("Ejecutando el metodo [ onCancelar ]...");

		util.limpiarCampos(idInvTipoProductoFormulario);
		util.deshabilitarCampos(idInvTipoProductoFormulario);
		idInvTipoProductoImageImg.setSrc("imagen/imageDefault.png");

		idInvTipoProductoBtnGuardar.setDisabled(true);
		idInvTipoProductoBtnCancelar.setDisabled(true);
		idInvTipoProductoBtnEditar.setDisabled(true);
		idInvTipoProductoBtnBorrar.setDisabled(true);
		idInvTipoProductoBtnNuevo.setDisabled(false);

	}
}
 