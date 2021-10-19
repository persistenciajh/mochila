package com.mochila.inv.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Radiogroup;
import org.zkoss.zul.Rows;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Toolbarbutton;
import com.mochila.framework.facade.ParametrizacionFac;
import com.mochila.framework.helper.UtilZKProcesosComunesHelper;
import com.mochila.gen.dto.Usuario;
import com.mochila.inv.dto.FotoProducto;
import com.mochila.inv.dto.Producto;
import com.mochila.inv.dto.Producto;

public class FotoProductoAction extends Borderlayout implements AfterCompose {
	protected static Logger log = Logger.getLogger(FotoProductoAction.class);

	Textbox idInvFotoProductoTbxBusqueda;
	Listbox idInvFotoProductoLbxFotoProducto;
	Toolbarbutton idInvFotoProductoBtnNuevo;
	Toolbarbutton idInvFotoProductoBtnEditar;
	Toolbarbutton idInvFotoProductoBtnGuardar;
	Toolbarbutton idInvFotoProductoBtnBorrar;
	Toolbarbutton idInvFotoProductoBtnCancelar;
	Rows idInvFotoProductoFormulario;
	Textbox idInvFotoProductoTbxOperacion;
Textbox idInvFotoProductoTbxSecuencia;
Textbox idInvFotoProductoTbxProducto;
Image idInvFotoProductoImageImg;
Radiogroup idInvFotoProductoRgbEstado;
Textbox idInvFotoProductoTbxDescripcion;
Button idInvFotoProductoBtnImagen;

	UtilZKProcesosComunesHelper util = new UtilZKProcesosComunesHelper();
	FotoProducto dtoSeleccionado = new FotoProducto();
	Usuario usuario = new Usuario();
	Producto producto = new Producto();
	ArrayList<FotoProducto> listaFotoProducto = new ArrayList<FotoProducto>();
	
	Bandbox idInvFotoProductoBandboxProducto;
	Listbox idInvFotoProductolistboxBandboxProducto;

@Override
	public void afterCompose() {
		// TODO Auto-generated method stub
		cargar();
		usuario = (Usuario) Executions.getCurrent().getSession().getAttribute("USUARIO_SESSION");
		log.info("Usuario: " + usuario.getNombreUsuario());
		
		
		producto = (Producto) Executions.getCurrent().getSession().getAttribute("PRODUCTO_SESSION");
		
		if(this.producto!=null){
			idInvFotoProductoTbxProducto.setValue(producto.getSecuencia()+"");
			idInvFotoProductoBandboxProducto.setValue(producto.getNombre());
			
	}
		onConsultar("");
	}

	public void cargar() {
		idInvFotoProductoTbxBusqueda = (Textbox) this.getFellow("idInvFotoProductoTbxBusqueda");
		idInvFotoProductoLbxFotoProducto = (Listbox) this.getFellow("idInvFotoProductoLbxFotoProducto");
		idInvFotoProductoBtnNuevo = (Toolbarbutton) this.getFellow("idInvFotoProductoBtnNuevo");
		idInvFotoProductoBtnEditar = (Toolbarbutton) this.getFellow("idInvFotoProductoBtnEditar");
		idInvFotoProductoBtnGuardar = (Toolbarbutton) this.getFellow("idInvFotoProductoBtnGuardar");
		idInvFotoProductoBtnBorrar = (Toolbarbutton) this.getFellow("idInvFotoProductoBtnBorrar");
		idInvFotoProductoBtnCancelar = (Toolbarbutton) this.getFellow("idInvFotoProductoBtnCancelar");
		idInvFotoProductoFormulario = (Rows) this.getFellow("idInvFotoProductoFormulario");
		idInvFotoProductoTbxOperacion = (Textbox) this.getFellow("idInvFotoProductoTbxOperacion");
idInvFotoProductoTbxSecuencia = (Textbox ) this.getFellow("idInvFotoProductoTbxSecuencia");
idInvFotoProductoTbxProducto = (Textbox ) this.getFellow("idInvFotoProductoTbxProducto");
idInvFotoProductoImageImg = (Image) this.getFellow("idInvFotoProductoImageImg");
idInvFotoProductoRgbEstado = (Radiogroup ) this.getFellow("idInvFotoProductoRgbEstado");
idInvFotoProductoTbxDescripcion = (Textbox ) this.getFellow("idInvFotoProductoTbxDescripcion");
idInvFotoProductoBtnImagen=(Button)this.getFellow("idInvFotoProductoBtnImagen");
//BandboxProducto
idInvFotoProductoBandboxProducto = (Bandbox ) this.getFellow("idInvFotoProductoBandboxProducto");
idInvFotoProductolistboxBandboxProducto = (Listbox ) this.getFellow("idInvFotoProductolistboxBandboxProducto");

}

 	public void onConsultar(String criterio) {
		log.info("Ejecutando el metodo [ onConsultar ]... ");
		idInvFotoProductoLbxFotoProducto.getItems().clear();
		dtoSeleccionado = null;

		idInvFotoProductoFormulario.invalidate();
		idInvFotoProductoBtnCancelar.setDisabled(true);
		idInvFotoProductoBtnGuardar.setDisabled(true);
		idInvFotoProductoBtnEditar.setDisabled(true);
		idInvFotoProductoBtnBorrar.setDisabled(true);

		util.limpiarCampos(idInvFotoProductoFormulario);
		util.deshabilitarCampos(idInvFotoProductoFormulario);
		idInvFotoProductoImageImg.setSrc("imagen/imageDefault.png");
		if(this.producto!=null){
			idInvFotoProductoTbxProducto.setValue(producto.getSecuencia()+"");
			idInvFotoProductoBandboxProducto.setValue(producto.getNombre());
			
	}
		log.info("parmatro:" + criterio);

		FotoProducto fotoProducto = new FotoProducto();
		fotoProducto.setDescripcion("%" + (criterio.equals(" ") ? "" : criterio) + "%");
		
		if(this.producto!=null){fotoProducto.setProducto(producto);}
		
		try {
			listaFotoProducto = (ArrayList<FotoProducto>) ParametrizacionFac.getFacade().obtenerListado("getFotoProducto", fotoProducto);

			log.info("Hay " + listaFotoProducto.size() + " datos encontrados.");

			for (final FotoProducto dto : listaFotoProducto) {
				Listitem listItem = util.crearListitem(dto, dto.getEstado(), ("["+dto.getSecuencia()+"] "+dto.getProducto().getNombre()));
				
				listItem.addEventListener(Events.ON_CLICK, new EventListener() {
					public void onEvent(Event event) throws Exception {

						onSeleccionar(dto);
						// listItemSeleccionado=listItem;
						// listItem.setSelected(true);

					}
				});
				idInvFotoProductoLbxFotoProducto.appendChild(listItem);

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.info("error algo salio mal al listar "+e);
		}
	}

public void onGuardar() {
		log.info("Ejecutando el metodo [onGuardar ]... ");
		String op = idInvFotoProductoTbxOperacion.getValue();
		ArrayList<String> arrayRequired = new ArrayList<String>();

		if (idInvFotoProductoTbxProducto.getValue().isEmpty()) {arrayRequired.add("PRODUCTO");}

		

		if (arrayRequired.size() > 0) {
			log.info("Faltan campos obligatorios por llenar");
			util.lanzarMensajeCamposFatantes("faltan campos obligatorios por llenar", arrayRequired);

		} else {
			FotoProducto fotoProducto = new FotoProducto();
			
			Producto producto = new Producto();
			producto.setSecuencia(new Long(idInvFotoProductoTbxProducto.getValue()));
			producto.setNombre(idInvFotoProductoBandboxProducto.getValue());
			fotoProducto.setProducto(producto);
			
			fotoProducto.setImg(idInvFotoProductoImageImg.getContent().getByteData());

			fotoProducto.setEstado(idInvFotoProductoRgbEstado.getSelectedIndex() == 0 ? "A" : "I");

			fotoProducto.setDescripcion(idInvFotoProductoTbxDescripcion.getValue());

			log.info("Parametro: " + fotoProducto.toString());
			if (idInvFotoProductoTbxOperacion.getValue().equals("U")) {
				log.info(" metodo [actualizar]");
				fotoProducto.setSecuencia(new Long(idInvFotoProductoTbxSecuencia.getValue()));
				fotoProducto.setFechaActualizacion(new Date());
				fotoProducto.setUsuarioActualizacion(usuario);
				fotoProducto.setFechaCreacion(dtoSeleccionado.getFechaCreacion());
				fotoProducto.setUsuarioCreacion(dtoSeleccionado.getUsuarioCreacion());

				try {
					ParametrizacionFac.getFacade().insertarRegistro("putFotoProducto", fotoProducto);
					util.deshabilitarCampos(idInvFotoProductoFormulario);
					onConsultar("");
					this.onSeleccionar(fotoProducto);

					log.info("Actualizado");
					util.lanzarMensaje("Actualizado");
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (op.equals("N")) {
				log.info("metodo [Guardar]");
				try {
					fotoProducto.setFechaCreacion(new Date());
					fotoProducto.setUsuarioCreacion(usuario);
					ParametrizacionFac.getFacade().insertarRegistro("postFotoProducto", fotoProducto);
					util.deshabilitarCampos(idInvFotoProductoFormulario);
					onConsultar("");
					this.onSeleccionar(fotoProducto);
					log.info("Guardado");
					util.lanzarMensaje("Guardado");
				} catch (Exception e) {
					util.lanzarMensaje("Error:::> " + e);
					log.info("Error:::> " + e);
					e.printStackTrace();
				}
				idInvFotoProductoBtnGuardar.setDisabled(true);
				idInvFotoProductoBtnCancelar.setDisabled(true);
				idInvFotoProductoBtnNuevo.setDisabled(false);
			}
		}
	}

	public void onSeleccionar(FotoProducto dto) {
		log.info(" Ejecutando el metodo [ onSeleccionar ]... ");

		dtoSeleccionado = dto;

		util.deshabilitarCampos(idInvFotoProductoFormulario);
		util.limpiarCampos(idInvFotoProductoFormulario);

		idInvFotoProductoBtnNuevo.setDisabled(false);
		idInvFotoProductoBtnGuardar.setDisabled(true);
		idInvFotoProductoBtnCancelar.setDisabled(true);
		idInvFotoProductoBtnEditar.setDisabled(false);
		idInvFotoProductoBtnBorrar.setDisabled(false);
		// logica
		idInvFotoProductoTbxSecuencia.setValue(dto.getSecuencia()+"");
		idInvFotoProductoTbxProducto.setValue(dto.getProducto().getSecuencia()+"");
		idInvFotoProductoBandboxProducto.setValue(dto.getProducto().getNombre());
		
		if(dto.getImg()!=null){
		try {
			AImage media = new AImage("fgf",dto.getImg());
			idInvFotoProductoImageImg.setContent(media);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.info("error al cargar la imagen");
		}	
		}else{
			idInvFotoProductoImageImg.setSrc("imagen/imageDefault.png");
		}
		
		idInvFotoProductoRgbEstado.setSelectedIndex(dto.getEstado().equals("A") ? 0 : 1);
		idInvFotoProductoTbxDescripcion.setValue(dto.getDescripcion() + "");
	}

public void onNuevo() {
		log.info("Ejecutando el metodo [ onNuevo]... ");

		dtoSeleccionado = null;

		idInvFotoProductoBtnNuevo.setDisabled(true);
		util.habilitarCampos(idInvFotoProductoFormulario);
		util.limpiarCampos(idInvFotoProductoFormulario);
		util.seleccionarRadio(idInvFotoProductoRgbEstado, "A");
		idInvFotoProductoImageImg.setSrc("imagen/imageDefault.png");
		
		if(this.producto!=null){
			idInvFotoProductoTbxProducto.setValue(producto.getSecuencia()+"");
			idInvFotoProductoBandboxProducto.setValue(producto.getNombre());
			
	}
		idInvFotoProductoTbxOperacion.setValue("N");
		idInvFotoProductoRgbEstado.getParent().setVisible(false);
		idInvFotoProductoFormulario.invalidate();

		idInvFotoProductoBtnCancelar.setDisabled(false);
		idInvFotoProductoBtnEditar.setDisabled(true);
		idInvFotoProductoBtnBorrar.setDisabled(true);
		idInvFotoProductoBtnGuardar.setDisabled(false);

	}

	public void onEditar() {
		log.info("Ejecutando el metodo [ onEditar ]...");
		util.habilitarCampos(idInvFotoProductoFormulario);
		idInvFotoProductoBtnImagen.invalidate();

		/* default en modo edicion */

		idInvFotoProductoBtnBorrar.setDisabled(true);
		idInvFotoProductoBtnEditar.setDisabled(true);
		idInvFotoProductoBtnGuardar.setDisabled(false);
		idInvFotoProductoBtnCancelar.setDisabled(false);
		idInvFotoProductoTbxOperacion.setValue("U");
		idInvFotoProductoRgbEstado.getParent().setVisible(true);
	}

	public void onBorrar() {
		log.info("metodo [onBorrar]");

		try {

			ParametrizacionFac.getFacade().borrarRegistro("deleteFotoProducto", dtoSeleccionado);
			util.lanzarMensaje("Se elimino ");
			log.info(" Se elimino ");

			util.limpiarCampos(idInvFotoProductoFormulario);
			idInvFotoProductoImageImg.setSrc("imagen/imageDefault.png");
			if(this.producto!=null){
				idInvFotoProductoTbxProducto.setValue(producto.getSecuencia()+"");
				idInvFotoProductoBandboxProducto.setValue(producto.getNombre());
				
		}
			onConsultar("");

		}
catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void onCancelar() {
		log.info("Ejecutando el metodo [ onCancelar ]...");

		util.limpiarCampos(idInvFotoProductoFormulario);
		idInvFotoProductoImageImg.setSrc("imagen/imageDefault.png");
		util.deshabilitarCampos(idInvFotoProductoFormulario);
		if(this.producto!=null){
			idInvFotoProductoTbxProducto.setValue(producto.getSecuencia()+"");
			
	}
		idInvFotoProductoBtnGuardar.setDisabled(true);
		idInvFotoProductoBtnCancelar.setDisabled(true);
		idInvFotoProductoBtnEditar.setDisabled(true);
		idInvFotoProductoBtnBorrar.setDisabled(true);
		idInvFotoProductoBtnNuevo.setDisabled(false);

	}
	
	public void llenarBandboxProducto(String criterio) {
		List<Producto> listaProducto;
		Producto producto = new Producto();
		if(usuario.getGrupo()==0){producto.setUsuarioCreacion(usuario);}
		producto.setNombre("%" + (criterio.equals(" ") ? "" : criterio) + "%");
		
		log.info("parmatro: " + producto.getNombre());
		
		try {			
			listaProducto = (ArrayList<Producto>) ParametrizacionFac.getFacade().obtenerListado("getProducto", producto);
			log.info("Hay " + listaProducto.size() + " datos encontradas.");						
				idInvFotoProductolistboxBandboxProducto.getItems().clear();
				for (final Producto dto : listaProducto) {
					log.info("Producto: " + dto.getNombre());
					Listitem listItem = new Listitem();
					listItem.setValue(dto.getSecuencia() + "");
					listItem.setLabel(dto.getNombre());
					listItem.setAttribute("Producto", dto);
					listItem.getAttribute("Producto");
					idInvFotoProductolistboxBandboxProducto.appendChild(listItem);
				}
			
		} catch (Exception e) {
			// TODO: handle exception
			log.info("");
		}
	}
}
 