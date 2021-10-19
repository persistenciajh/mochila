package com.mochila.viewModel;



import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import net.sf.jasperreports.engine.JRException;

import org.apache.log4j.Logger;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zul.Label;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Borderlayout;
import org.zkoss.zul.Cell;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Row;
import org.zkoss.zul.Rows;
import org.zkoss.zul.Window;
import org.zkoss.zk.ui.ext.AfterCompose;

import com.mochila.framework.facade.ParametrizacionFac;
import com.mochila.gen.action.LoginAction;
import com.mochila.gen.dto.Usuario;
import com.mochila.inv.dto.InvSalida;


@SuppressWarnings("unchecked")
public class VMOtrosPagos extends Window implements AfterCompose {

	public static Logger log = Logger.getLogger(VMOtrosPagos.class);
	SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
	ArrayList<InvSalida> listaInvSalida = new ArrayList<InvSalida>();
	Listbox OPLbxRecibos;
	Rows idMibodegaMovilRws;
	
	Usuario usuario;
	@Override
	public void afterCompose() {
		// TODO Auto-generated method stub
		OPLbxRecibos =(Listbox) this.getFellow("OPLbxRecibos");
		idMibodegaMovilRws =(Rows) this.getFellow("idMibodegaMovilRws");
		
		usuario = (Usuario)Executions.getCurrent().getSession().getAttribute("USUARIO_SESSION");
		onconsultar();
	}

	public void onconsultar() {
		OPLbxRecibos.getChildren().clear();

		InvSalida dtoParmatro = new InvSalida();
		dtoParmatro.setSecuencia(usuario.getPersona().getSecuencia());
		
		try {
			listaInvSalida = (ArrayList<InvSalida>) ParametrizacionFac.getFacade().obtenerListado("getInvSalidasDeVendedores", dtoParmatro);

			log.info("Hay " + listaInvSalida.size() + " datos encontrados.");

			for (final InvSalida dto : listaInvSalida) {
				Listitem listItem = new Listitem();
				
				listItem.appendChild(new Listcell(format.format(dto.getFechaCreacion())));
				listItem.appendChild(new Listcell(dto.getCodigo()));
				listItem.appendChild(new Listcell(dto.getTotal().toString()));
				
				listItem.addEventListener(Events.ON_CLICK, new EventListener() {
					public void onEvent(Event event) throws Exception {

						onSeleccionar(dto);
						

					}
				});
				OPLbxRecibos.appendChild(listItem);

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void onSeleccionar(InvSalida dtoParmatro) {
		log.info("onSeleccionar");
		idMibodegaMovilRws.getChildren().clear();
		ArrayList<InvSalida> listaDetalle = new ArrayList<InvSalida>();
		
		try {
			
			listaDetalle = (ArrayList<InvSalida>) ParametrizacionFac.getFacade().obtenerListado("getInvSalidasDeVendedoresDetalle", dtoParmatro);

			log.info("Hay " + listaDetalle.size() + " datos encontrados.");

			for (final InvSalida dto : listaDetalle) {
				
				Row row = new Row();
				
				row.appendChild(new Label(dto.getCodigo()));
				row.appendChild(new Label(dto.getDescripcion()));
				row.appendChild(new Label(dto.getTipo()));
				row.appendChild(new Label(dto.getNumero()));


				idMibodegaMovilRws.appendChild(row);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		
		
		
	}
	


}

