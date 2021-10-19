package com.mochila.gen.action;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.ext.AfterCompose;
import org.zkoss.zul.Borderlayout;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Image;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Row;
import org.zkoss.zul.Rows;
import org.zkoss.zul.Textbox;

import com.mochila.framework.facade.ParametrizacionFac;
import com.mochila.gen.dto.Persona;
import com.mochila.gen.dto.Usuario;
import com.mochila.inv.action.ProductoAction;
import com.mochila.inv.dto.FotoProducto;
import com.mochila.inv.dto.Producto;

public class DisponibilidadAction extends Borderlayout implements AfterCompose {
	protected static Logger log = Logger.getLogger(DisponibilidadAction.class);
	FotoProducto fotoProducto;
	Rows idWindowDiponibilidadRowResultado;
	Listbox idWindowDiponibilidadBusqueda;
	Textbox idWindowDiponibilidadTxtBusqueda;

	@Override
	public void afterCompose() {
		cargar();
		fotoProducto = (FotoProducto) Executions.getCurrent().getSession().getAttribute("FOTO_PRODUCTO_SESSION");
		if (fotoProducto != null) {
			mostrarInfoDisponibilidad();
		} else {
			//Executions.createComponents("/index.zul", null, null);
			Executions.sendRedirect("index.zul");
		}
	}

	public void cargar() {
		idWindowDiponibilidadRowResultado = (Rows) this.getFellow("idWindowDiponibilidadRowResultado");
		 idWindowDiponibilidadBusqueda = (Listbox) this.getFellow("idWindowDiponibilidadBusqueda");
		 idWindowDiponibilidadTxtBusqueda = (Textbox) this.getFellow("idWindowDiponibilidadTxtBusqueda");
	}
	
	public void mostrarInfoDisponibilidad(){
		log.info("metodo[mostrarInfoDisponibilidad]");
		idWindowDiponibilidadRowResultado.getChildren().clear();
		Usuario dto= new Usuario();
		log.info("metodo[mostrarInfoDisponibilidad]");
		dto.setSecuencia(fotoProducto.getProducto().getUsuarioCreacion().getSecuencia());
		log.info("==>"+dto.toString());
		try {
			ArrayList<Usuario> listaUsuario = (ArrayList<Usuario>) ParametrizacionFac.getFacade().obtenerListado("getUsuario", dto);
			
			for(Usuario dtoUsuario: listaUsuario){
			Row row =new Row();		
			
			row.appendChild(new Label(dtoUsuario.getPersona().getNombres()));
			row.appendChild(new Label(dtoUsuario.getPersona().getDireccion()));
			Image imgCell =new Image();
			imgCell.setWidth("20px");
			imgCell.setHeight("20px");
			imgCell.setSrc("imagen/ws.png");
			Hbox hbox=new Hbox();
			hbox.appendChild(imgCell);
			hbox.appendChild(new Label(dtoUsuario.getPersona().getTelefono()));
			row.appendChild(hbox);
			idWindowDiponibilidadRowResultado.appendChild(row);
			}
		
		} catch (Exception e) {	// TODO Auto-generated catch block
			log.info("error al listar"+e);
			e.printStackTrace();
		}
	}
	
	public void mostrarInfoPersona(){
		log.info("metodo:[mostrarInfoPersona]");
		idWindowDiponibilidadRowResultado.getChildren().clear();
		Persona per= new Persona();
		if(idWindowDiponibilidadBusqueda.getSelectedIndex()==0){per.setTelefono("%"+idWindowDiponibilidadTxtBusqueda.getValue()+"%");}
		if(idWindowDiponibilidadBusqueda.getSelectedIndex()==1){per.setDireccion("%"+idWindowDiponibilidadTxtBusqueda.getValue()+"%");}
		if(idWindowDiponibilidadBusqueda.getSelectedIndex()==2){per.setNombres("%"+idWindowDiponibilidadTxtBusqueda.getValue()+"%");}
		log.info("==>"+per.toString());	
		
		
		try {
			ArrayList<Persona> listaPersona = (ArrayList<Persona>) ParametrizacionFac.getFacade().obtenerListado("getPersona", per);
			
			log.info("personas encontradas:"+listaPersona.size());
			
			for(Persona dtoPersona: listaPersona){
			Row row =new Row();		
			
			row.appendChild(new Label(dtoPersona.getNombres()));
			row.appendChild(new Label(dtoPersona.getDireccion()));
			Image imgCell =new Image();
			imgCell.setWidth("20px");
			imgCell.setHeight("20px");
			imgCell.setSrc("imagen/ws.png");
			Hbox hbox=new Hbox();
			hbox.appendChild(imgCell);
			hbox.appendChild(new Label(dtoPersona.getTelefono()));
			row.appendChild(hbox);
			idWindowDiponibilidadRowResultado.appendChild(row);
			}
		
		} catch (Exception e) {	// TODO Auto-generated catch block
			log.info("error al listar"+e);
			e.printStackTrace();
		}
	}
	

}
