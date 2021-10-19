package com.mochila;

import org.apache.log4j.Logger;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.ext.AfterCompose;
import org.zkoss.zul.Borderlayout;
import org.zkoss.zul.Include;
import org.zkoss.zul.Label;

import com.mochila.gen.action.UsuarioAction;
import com.mochila.gen.dto.Usuario;

public class MenuAction extends Borderlayout implements AfterCompose {
	protected static Logger log = Logger.getLogger(MenuAction.class);
	Include pagina;
	Usuario usuario =new Usuario();
	Label idMenuLbxNombreUsuario;
	@Override
	public void afterCompose() {
		// TODO Auto-generated method stub
		pagina = (Include) this.getFellow("include");
		idMenuLbxNombreUsuario=(Label)this.getFellow("idMenuLbxNombreUsuario");
//		
		usuario = (Usuario) Executions.getCurrent().getSession()
				.getAttribute("USUARIO_SESSION");
	   
		
		if(usuario != null){
			log.info("Usuario: " + usuario.getNombreUsuario());
			idMenuLbxNombreUsuario.setValue(usuario.getNombreUsuario());			
		}
	}
	
	public void abrirPagina(String pagina){
		this.pagina.setSrc(pagina);
	}

	public void cerrarSesion() {
		log.info("cerrarSesion");
		try {
			Executions.getCurrent().getSession().invalidate();
			Executions.getCurrent().sendRedirect("/");
		} catch (Exception e) {
			log.info("error: al cerrar sesion");
		}
	}
	
}
