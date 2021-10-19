package com.mochila.movil.action;

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
import org.zkoss.zul.Div;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Html;
import org.zkoss.zul.Image;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Panel;
import org.zkoss.zul.Panelchildren;
import org.zkoss.zul.Separator;
import org.zkoss.zul.Vbox;

import com.mochila.framework.facade.ParametrizacionFac;
import com.mochila.framework.helper.UtilZKProcesosComunesHelper;
import com.mochila.gen.dto.Interaccion;
import com.mochila.inv.dto.FotoProducto;
import com.mochila.inv.dto.Producto;



public class IndexAction extends Borderlayout implements AfterCompose {
	public static Logger log = Logger.getLogger(IndexAction.class);
	UtilZKProcesosComunesHelper util = new UtilZKProcesosComunesHelper();
	ArrayList<Producto>	listaProducto = new ArrayList<Producto>();
	Producto producto;
	int imagenPorFila=2;
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
		// TODO Auto-generated method stub
		log.info("metodo [afterCompose]");
		
producto = (Producto) Executions.getCurrent().getSession().getAttribute("PRODUCTO_SESSION");
		
if (producto == null) {
	log.info("xxxxxxx");// solo entra una vez aqui y es cuando inicia la
						// pag y muestra todos los productos
	producto = new Producto();

}
		
try {
	ArrayList<Producto> listaProducto = (ArrayList<Producto>) ParametrizacionFac.getFacade().obtenerListado("getNumeroRegistros",producto);
	
	registrosTotales= (int) listaProducto.get(0).getDescuentoMax().shortValue();
	log.info("registrosTotales:"+registrosTotales);
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
log.info(":::::::::::::::"+numPagWeb);
numPagWeb= registrosTotales/registroPorPag +(registrosTotales%registroPorPag==0?0:1);
log.info(":::::::::::::::"+numPagWeb);
cargar();
paginar();
onSeleccionar("1");
idLbxLista.setSelectedIndex(0);
guardarIteraccion();
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
	Vbox idWinVboxProductos=(Vbox)this.getFellow("idWinVboxProductos");
	idWinVboxProductos.getChildren().clear();
	
	int paginaActual=Integer.parseInt(item);
//	log.info("1 paginaActual "+paginaActual);
	punteroFin=paginaActual*registroPorPag;
//	log.info("2 punteroFin "+punteroFin);
//	punteroIni=punteroFin-registroPorPag+1;
	punteroIni=punteroFin-registroPorPag+1;
//	log.info("3 punteroIni "+punteroIni);
	
	punteroIni=punteroIni-1;
	
	log.info("pag Actual: "+paginaActual+" punteroIni:"+punteroIni+" limite: "+registroPorPag);
	
	//Producto producto=new Producto();
	producto.setNombre("%%");
	producto.setCantidadMin(new Long (punteroIni));

	producto.setDescuentoMax(new Long (registroPorPag));
	try {
		ArrayList<Producto> listaProducto = (ArrayList<Producto>) ParametrizacionFac.getFacade().obtenerListado("getProductoMovil",producto);

	log.info("Hay " + listaProducto.size() + " datos encontradas.");
	
	
	for (int i=0;i<listaProducto.size();) {
//		Producto dto=listaProducto.get(i);
			Hbox fila =new Hbox();
		    for(int j=1;j<=imagenPorFila;j++){
//		    log.info("i= "+i+" j= "+j);	
		    if(i<listaProducto.size()){
			final Producto dto=listaProducto.get(i);
			
			Panel panel=new Panel();
//			panel.setStyle("margin-top:6px");
			panel.setHeight("200px");
			panel.setWidth("150px");
			panel.setBorder("0");		
			panel.setBorder("normal");
			Panelchildren panelChild =new Panelchildren();	
			
			
			
			Vbox vbox=new Vbox();
			vbox.setAlign(CENTER);
			vbox.setPack(CENTER);
			vbox.setHeight("90%");
			vbox.setWidth("100%");
		
			Image img=new Image();
			
			final FotoProducto fp=getFotoProductoPrincipal(dto);
			
			if(fp.getImg()!=null){
				
				AImage media= new AImage("xxx",fp.getImg());
				img.setContent(media);						
				fp.setProducto(dto);
			}else{
				img.setSrc("../imagen/imageDefault.png");	
			}
			img.setWidth("128px");
			img.setHeight("128px");
			img.setStyle("black;font-size:15px;cursor:pointer");
			
			
			panelChild.addEventListener(Events.ON_CLICK, new EventListener() {
				public void onEvent(Event event) throws Exception {
					cargarProductoVer(fp);
				}
			});			
			
			vbox.appendChild(img);
			
			Html htmlContenedor=new Html();
			htmlContenedor.setContent("<font color=\"gray\" size=\"1\" face=\"arial\"><div>");
			htmlContenedor.setContent(htmlContenedor.getContent()+"<b>"+dto.getNombre()+"</b>");			
			htmlContenedor.setContent(htmlContenedor.getContent()+"</div></font>");
			vbox.appendChild(htmlContenedor);
			
			int precio=(int) Math.round(dto.getPrecioVenta());
			
			
			int precioConDescuento=(int) Math.round(dto.getPrecioVenta()-((dto.getPrecioVenta()*dto.getDescuentoMax())/100));	
			
			Hbox hbox =new Hbox();
			if(dto.getDescuentoMax()!=0){
			hbox.appendChild(util.crearPanelColorTexto("-"+dto.getDescuentoMax()+"%",
					"white","1", "#006EB3", "20px", "25px"));
			
			hbox.appendChild(util.crearPanelColorTexto("$"+precioConDescuento,
					"#ff7700","1", "white", "20px", "35px"));
			
			hbox.appendChild(util.crearPanelColorTexto("<s>$"+precio+"</s>",
					"gray","1", "white", "20px", "35px"));
			}
			else{
				
				hbox.appendChild(util.crearPanelColorTexto("$"+precio,
						"#006EB3","2", "white", "20px", "45px"));	
				
//				hbox.appendChild(util.crearPanelColorTexto("<u>Ver más</u>",
//						"#ff7700","1", "white", "20px", "45px"));
								
			}			
			
			vbox.appendChild(hbox);
			panelChild.appendChild(vbox);
			panel.appendChild(panelChild);
			
			fila.appendChild(panel);					
			
			i++;
		    }					    
		    }
		    
			idWinVboxProductos.appendChild(fila);
	}
	
	
	}catch (Exception e) {
		// TODO: handle exception
		log.info("error:"+e);
	}
}

	
	public FotoProducto  getFotoProductoPrincipal( Producto p){
		log.info("metodo[getFotoProductoPrincipal]");
		FotoProducto fp=new FotoProducto();
		
		fp.setProducto(p);
		 
		try {
			ArrayList<FotoProducto>	listaProducto = (ArrayList<FotoProducto>) ParametrizacionFac.getFacade().obtenerListado("getFotoProductoPrincipal",fp);
			log.info("Hay " + listaProducto.size() + " datos encontradas.");
			
		if (listaProducto.size()>0){
			 fp = listaProducto.get(0);
		}
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return fp;	
	}
	
	private void cargarProductoVer( FotoProducto fp) {
		log.info("metodo[cargarProductoVer]");
		// TODO Auto-generated method stub				   PRODUCTO_SESSION
		Executions.getCurrent().getSession().setAttribute("PRODUCTO_SESSION", producto);
		Executions.getCurrent().getSession().setAttribute("FOTO_PRODUCTO_SESSION", fp);
		
		Executions.sendRedirect("ver.zul");
	}
	
	public void guardarIteraccion(){
		log.info("metodo[guardarIteraccion]");
		Interaccion interaccion = new Interaccion();	
		interaccion.setDescripcion("VRP");
		interaccion.setReferencia("IP:"+Executions.getCurrent().getRemoteAddr());
		interaccion.setFechaCreacion(new Date());
		try {
			ParametrizacionFac.getFacade().insertarRegistro("postInteraccion", interaccion);
			log.info("se guardo la interaccion");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.info("error: algo salio mal al guardar la interaccion"+e);
		}
		
	}

}
