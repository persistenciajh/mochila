package com.mochila.gen.action;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import org.zkoss.zk.ui.ext.AfterCompose;
import org.zkoss.zul.Cell;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Window;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;

import com.mochila.framework.facade.ParametrizacionFac;
import com.mochila.inv.dto.Producto;
import com.mochila.movil.action.IndexAction;

public class PaginAction extends Window implements AfterCompose{
	public static Logger log = Logger.getLogger(PaginAction.class);
	
	Listbox idLbxLista;
	int registrosTotales=0;
	int registroPorPag=4;
	int punteroIni=0;
	int punteroFin=0;
	int aux1=0;
	int aux2=0;
	int numPagWeb=0;
	
	
	
	@Override
	public void afterCompose() {
		log.info("metodo [afterCompose]");
		
		try {
			ArrayList<Producto> listaProducto = (ArrayList<Producto>) ParametrizacionFac.getFacade().obtenerListado("getNumeroRegistros");
			
			registrosTotales= (int) listaProducto.get(0).getDescuentoMax().shortValue();

		} catch (Exception e) {
			// TODO: handle exception
			log.info("error:"+e);
		}
		
		
		aux1=(int) (registrosTotales/registroPorPag);
		
		aux2 =aux1*registroPorPag;
		
		if(aux2!=registrosTotales){
			aux1++;			
		}
		numPagWeb=aux1;
		
		
		cargar();
		paginar();
		onSeleccionar("1");
		idLbxLista.setSelectedIndex(0);
		
	}
	
	public void cargar(){
		
		idLbxLista=(Listbox)this.getFellow("idLbxLista");		
	}
	
	public void paginar(){
		log.info("metodo [paginar]");
		idLbxLista.getChildren().clear();
		
		
		
		for(int i=1;i<=numPagWeb;i++){
			
			Listitem listItem =new Listitem();
			
			listItem.setLabel(i+"");
			listItem.setValue(i);
						
			idLbxLista.appendChild(listItem);
			
		}
		
	}
	
	public void onSeleccionar(String item){
		log.info("metodo [onSeleccionar]"+item);
		
		
		int paginaActual=Integer.parseInt(item.trim());
//		log.info("1 paginaActual "+paginaActual);
		punteroFin=paginaActual*registroPorPag;
//		log.info("2 punteroFin "+punteroFin);
		punteroIni=punteroFin-registroPorPag+1;
//		log.info("3 punteroIni "+punteroIni);
		
		log.info("pag Actual: "+paginaActual+" punteroIni:"+punteroIni+" punteroFin: "+punteroFin);
		
		Producto producto=new Producto();
		producto.setNombre("%%");
		producto.setCantidadMin(new Long(punteroIni));
		producto.setDescuentoMax(new Long(punteroFin));
		try {
			ArrayList<Producto> listaProducto = (ArrayList<Producto>) ParametrizacionFac.getFacade().obtenerListado("getProductoMovil",producto);

		log.info("Hay " + listaProducto.size() + " datos encontradas.");
		
		
		for(Producto dto:listaProducto){
			
			log.info(dto.getSecuencia()+") producto--->"+dto.getNombre());
		}
		
		
		}catch (Exception e) {
			// TODO: handle exception
			log.info("error:"+e);
		}
	}
	
	

}
