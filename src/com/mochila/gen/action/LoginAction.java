package com.mochila.gen.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


import org.apache.log4j.Logger;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.ext.AfterCompose;
import org.zkoss.zul.Borderlayout;
import org.zkoss.zul.Button;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;


import com.mochila.framework.facade.ParametrizacionFac;
import com.mochila.framework.helper.UtilZKProcesosComunesHelper;
import com.mochila.gen.dto.Usuario;

public class LoginAction extends Borderlayout implements AfterCompose {
	public static Logger log = Logger.getLogger(LoginAction.class);
	ArrayList<Usuario> listaPersona = new ArrayList();
	Button idbuton;
	Textbox idUsuario;
	Textbox idClave;
	
	Usuario usuario;
	UtilZKProcesosComunesHelper util = new UtilZKProcesosComunesHelper();

	@Override
	public void afterCompose() {
		// TODO Auto-generated method stub
		
		
		usuario = (Usuario)Executions.getCurrent().getSession().getAttribute("USUARIO_SESSION");
		
		
		cargar();
		
		if(usuario == null){
			usuario = new Usuario();	
		}else{
			log.debug("Cliente: " + usuario.getCorreoElectronico());
			iniciarSesion();
		}

	}
	
	public void cargar(){
		idUsuario =(Textbox)this.getFellow("idUsuario");
		idClave=(Textbox)this.getFellow("idClave");
	}

public void iniciarSesion(){
		
		if(usuario.getPersona()==null) {
		usuario.setCorreoElectronico(idUsuario.getValue());
		usuario.setClave(idClave.getValue());
		}else {
			idUsuario.setValue(usuario.getCorreoElectronico());
			
		}
		
		log.info("parametro: " +usuario.toString());
		String validado="N";
		
		try {
			
			if(!idUsuario.getValue().isEmpty()){
				
				
			listaPersona = (ArrayList<Usuario>) ParametrizacionFac.getFacade()
				      .obtenerListado("validarAutenticacion",usuario);
			
			log.info("Hay " + listaPersona.size()
			+ " datos encontradas.");
			
			
			
			if(listaPersona.get(0).getNombreUsuario() != null){
				validado ="S";
				}	
			
			
		    	
			    if(validado.equals("S")){
			    	
			    	 log.info("Validado: " + validado+" "+usuario.getSecuencia());
			    	 
			    	usuario=listaPersona.get(0); 
			    	Executions.getCurrent().getSession().setAttribute("USUARIO_SESSION", usuario);
			    	Executions.getCurrent().getDesktop().getFirstPage().getFirstRoot().detach();
			    	
			    	if(usuario.getGrupo()!=0){
			    	Executions.createComponents("/accessControl/menu_movil.zul", null, null);	
			    	}else{
					Executions.createComponents("/accessControl/menu.zul", null, null);
			    	}
					log.info("y se marcho!!!");
			    	
			    }else{
			    	
			    	log.info("Pailas no pasa...");
			    	lanzarExcepcion();
			    }
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			util.lanzarMensaje("Usuario no encontrado");
		}
		
}

public void lanzarExcepcion(){
	Messagebox.show(
			"Credenciales de inicio de sesi�n invalidas",
			"Advertencia", Messagebox.OK,
			Messagebox.EXCLAMATION);
}
	

}
