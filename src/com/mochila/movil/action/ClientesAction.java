package com.mochila.movil.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.ext.AfterCompose;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Row;
import org.zkoss.zul.Rows;
import org.zkoss.zul.Window;

import com.mochila.framework.facade.ParametrizacionFac;
import com.mochila.gen.dto.Persona;
import com.mochila.gen.dto.Usuario;
import com.mochila.inv.dto.InvSalida;
import com.mochila.viewModel.VMOtrosPagos;

public class ClientesAction extends Window  implements AfterCompose{

	public static Logger log = Logger.getLogger(VMOtrosPagos.class);
	SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
	ArrayList<Persona> listaPersona = new ArrayList<Persona>();
	Rows idMisClientesMovilRws;
	Usuario usuario;
	
	@Override
	public void afterCompose() {
		// TODO Auto-generated method stub
		idMisClientesMovilRws = (Rows) this.getFellow("idMisClientesMovilRws");
		usuario = (Usuario)Executions.getCurrent().getSession().getAttribute("USUARIO_SESSION");
	}
	
	public void listar() {
		idMisClientesMovilRws.getChildren().clear();
		Persona dtoParmatro = new Persona();
		dtoParmatro.setSecUsuarioCreacion(usuario.getSecuencia());
		
try {
			
			listaPersona = (ArrayList<Persona>) ParametrizacionFac.getFacade().obtenerListado("getPersona", dtoParmatro);

			log.info("Hay " + listaPersona.size() + " datos encontrados.");

			for (final Persona dto : listaPersona) {
				
				Row row = new Row();
				
				row.appendChild(new Label(dto.getIdentificacion()));
				


				idMisClientesMovilRws.appendChild(row);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

}
