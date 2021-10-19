package com.mochila.viewModel;



import java.util.ArrayList;
import java.util.Properties;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Captcha;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import com.mochila.framework.facade.ParametrizacionFac;
import com.mochila.framework.helper.UtilZKProcesosComunesHelper;
import com.mochila.gen.dto.Usuario;



public class IniciarSesionViewModel extends ViewModelStandar {

	@Wire
	private Grid gridFormularioIniciarSesion;
	@Wire
	private Captcha idCaptcha;
	@Wire
	private Textbox idTboxCaptcha;
	
	private Usuario usuario;
	UtilZKProcesosComunesHelper util = new UtilZKProcesosComunesHelper();
	ArrayList<Usuario> listaPersona = new ArrayList();
	
	@Init
	public void init() {
		log.info("init");
		usuario = (Usuario)Executions.getCurrent().getSession().getAttribute("USUARIO_SESSION");
		log.debug("Cliente: " + usuario);
		
	
		
		if(usuario == null){
			usuario = new Usuario();	
		}else{
			iniciarSesion();
		}
	}

	/**
	 * Metodo obligatorio para cargar los componentes en la clase
	 * 
	 * @param view
	 */
	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
	}

	@Command
	public void iniciarSesion() {
		log.info("metodo [iniciarSesion]");
		
			
			log.info("parametro: " +usuario.toString());
			String validado="N";
			
			try {
				
				if(!usuario.getNombreUsuario().isEmpty()){
					
					
				listaPersona = (ArrayList<Usuario>) ParametrizacionFac.getFacade()
					      .obtenerListado("validarAutenticacion2",usuario);
				
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
				    	Executions.createComponents("/movil/menu_movil.zul", null, null);	
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

	public boolean validarCaptcha(String captcha, String captchaInput){
		if(captchaInput != null && captcha.equals(captchaInput)){
			return true;
		}else{
			return false;
		}
	}
	
	public void lanzarExcepcion(){
		Messagebox.show(
				"Credenciales de inicio de sesión inválidas",
				"Advertencia", Messagebox.OK,
				Messagebox.EXCLAMATION);
	}
	
	
	

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
