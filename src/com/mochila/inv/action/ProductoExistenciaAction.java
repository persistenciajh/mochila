package com.mochila.inv.action;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.ext.AfterCompose;
import org.zkoss.zul.Borderlayout;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Textbox;

import com.mochila.framework.facade.ParametrizacionFac;
import com.mochila.framework.helper.UtilZKProcesosComunesHelper;
import com.mochila.inv.dto.InvSalida;
import com.mochila.inv.dto.VInvProductoExistencia;

public class ProductoExistenciaAction extends Borderlayout implements AfterCompose {
	private static final long serialVersionUID = 1L;

	protected static Logger log = Logger.getLogger(ProductoExistenciaAction.class);
	 Listbox idProductoExistenciaLbxInvSalida;
	 ArrayList<VInvProductoExistencia> listaInvSalida = new ArrayList<VInvProductoExistencia>();
	 UtilZKProcesosComunesHelper util = new UtilZKProcesosComunesHelper();
	 
	@Override
	public void afterCompose() {
		// TODO Auto-generated method stub
		cargar();
		onConsultar();
	}
	
	public void cargar() {
		
		idProductoExistenciaLbxInvSalida = (Listbox) this.getFellow("idProductoExistenciaLbxInvSalida");
		
	}
	
	public void onConsultar() {
		log.info("Ejecutando el metodo [ onConsultar ]... ");
		idProductoExistenciaLbxInvSalida.getItems().clear();
		
		try {
			listaInvSalida = (ArrayList<VInvProductoExistencia>) ParametrizacionFac.getFacade().obtenerListado("getVInvProductoExistencia");

			log.info("Hay " + listaInvSalida.size() + " datos encontrados.");

			for (final VInvProductoExistencia dto : listaInvSalida) {
				Listitem listItem = util.crearListitem(
						dto, 
						dto.getProducto().getCodigo(),
						dto.getProducto().getNombre(),
						dto.getProducto().getTipoProducto().getNombre(),
						dto.getEntradas()-dto.getSalidas()+"",
						dto.getProducto().getUltPrecioCompra().toString(),
						dto.getProducto().getPrecioVenta().toString(),
						(dto.getProducto().getPrecioVenta()-dto.getProducto().getUltPrecioCompra())+""
						);
				listItem.addEventListener(Events.ON_CLICK, new EventListener() {
					public void onEvent(Event event) throws Exception {

						//onSeleccionar(dto);
						

					}
				});
				idProductoExistenciaLbxInvSalida.appendChild(listItem);

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		}

}
