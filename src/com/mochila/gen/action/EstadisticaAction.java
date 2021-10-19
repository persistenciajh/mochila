package com.mochila.gen.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.zkoss.image.AImage;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.ext.AfterCompose;
import org.zkoss.zul.Borderlayout;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Html;
import org.zkoss.zul.Image;
import org.zkoss.zul.Panel;
import org.zkoss.zul.Panelchildren;
import org.zkoss.zul.Separator;
import org.zkoss.zul.Space;
import org.zkoss.zul.Vbox;

import com.mochila.framework.facade.ParametrizacionFac;
import com.mochila.framework.helper.UtilZKProcesosComunesHelper;
import com.mochila.gen.dto.Interaccion;
import com.mochila.gen.dto.Usuario;
import com.mochila.inv.action.ProductoAction;
import com.mochila.inv.dto.FotoProducto;
import com.mochila.inv.dto.Producto;

public class EstadisticaAction extends Borderlayout implements AfterCompose {
	protected static Logger log = Logger.getLogger(EstadisticaAction.class);

	Vbox idEstadisticasVbox;
	Html idEstadisticaCounInteraccionProductoUsuarioHtml;
	Html idEstadisticaRangoFechasCounHtml;
	Html idEstadisticaCounVRPHtml;
	Usuario usuario = new Usuario();
	UtilZKProcesosComunesHelper util = new UtilZKProcesosComunesHelper();
	int imagenPorFila = 5;
	SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

	@Override
	public void afterCompose() {
		// TODO Auto-generated method stub
		usuario = (Usuario) Executions.getCurrent().getSession().getAttribute("USUARIO_SESSION");
		log.info("Usuario: " + usuario.getNombreUsuario());
		cargar();
		listar();
		contarInterraccionPublicacion();
		contarInterraccionVisitasRegistradasPagina();
	}

	public void cargar() {
		idEstadisticasVbox = (Vbox) this.getFellow("idEstadisticasVbox");
		idEstadisticaCounInteraccionProductoUsuarioHtml= (Html) this.getFellow("idEstadisticaCounInteraccionProductoUsuarioHtml");
		idEstadisticaRangoFechasCounHtml= (Html) this.getFellow("idEstadisticaRangoFechasCounHtml");
		idEstadisticaCounVRPHtml= (Html) this.getFellow("idEstadisticaCounVRPHtml");
	} 

	public void listar() {
		log.info("metodo[listar()]");
		idEstadisticasVbox.getChildren().clear();
		
		Producto producto = new Producto();
		producto.setNombre("%" + "" + "%");
		try {
			if (usuario.getGrupo() != 0) {
				producto.setUsuarioCreacion(usuario);
			}
			ArrayList<Producto> listaProducto = (ArrayList<Producto>) ParametrizacionFac.getFacade()
					.obtenerListado("getProducto", producto);
			log.info("Hay " + listaProducto.size() + " datos encontradas.");
			for (int i = 0; i < listaProducto.size();) {
				Hbox fila = new Hbox();
				for (int j = 1; j <= imagenPorFila; j++) {
					if (i < listaProducto.size()) {
						final Producto dto = listaProducto.get(i);
						Panel panel = new Panel();
						panel.setStyle("margin-top:3px");
						panel.setHeight("90px");
						panel.setWidth("148px");
						panel.setBorder("normal");
						Panelchildren panelChild = new Panelchildren();
						Vbox vbox = new Vbox();
						vbox.setAlign("start");
						vbox.setPack("start");
						vbox.setHeight("90%");
						vbox.setWidth("100%");
						
						Hbox hbox = new Hbox();
						hbox.setAlign(CENTER);
						hbox.setPack("start");
						hbox.setHeight("90%");
						hbox.setWidth("100%");

						Image img = new Image();

						final FotoProducto fp =getFotoProductoPrincipal(dto);

						if (fp.getImg() != null) {

							AImage media = new AImage("www.tumochila.com.co"+fp.getProducto().getNombre(), fp.getImg());
							img.setContent(media);
							fp.setProducto(dto);
						} else {
							img.setSrc("imagen/imageDefault.png");
						}
						img.setWidth("64px");
						img.setHeight("64px");
						img.setStyle("black;font-size:15px;cursor:pointer");

						hbox.appendChild(img);

						Html htmlContenedor = new Html();
						htmlContenedor.setContent("<font color=\"black\" size=\"1\" face=\"arial\">");
						htmlContenedor.setContent(htmlContenedor.getContent() + dto.getNombre());
						htmlContenedor.setContent(htmlContenedor.getContent() + "</font>");
						hbox.appendChild(htmlContenedor);
						
						vbox.appendChild(hbox);
						
						Hbox piePanel=new Hbox();
						piePanel.setAlign(CENTER);
						piePanel.setPack("CENTER");
						
						Html htmlContenedorCountInteraccion = new Html();						
						htmlContenedorCountInteraccion.setContent("</font>"+"<font color=\"gray\" size=\"1\" face=\"arial\">&nbsp;"+format.format(dto.getFechaCreacion())+"</font>");
						piePanel.appendChild(htmlContenedorCountInteraccion);
						Space sp=new Space();
						sp.setWidth("10px");
						piePanel.appendChild(sp);
						Html htmlContenedorFechaCreacion = new Html();						
						htmlContenedorFechaCreacion.setContent("</font>"+"<font color=\"gray\" size=\"1\" face=\"arial\">"+"Interacción <b>"+contarInterraccionProducto(dto)+ "</b></font>");
						piePanel.appendChild(htmlContenedorFechaCreacion);
						
						vbox.appendChild(piePanel);
						panelChild.appendChild(vbox);
						panel.appendChild(panelChild);

						fila.appendChild(panel);

						i++;
					}
				}

				idEstadisticasVbox.appendChild(fila);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.info("error:algo salio mal al consultar los productos");

		}

	}
	
	public FotoProducto  getFotoProductoPrincipal( Producto p){
		//log.info("metodo[getFotoProductoPrincipal]");
		FotoProducto fp=new FotoProducto();
		
		fp.setProducto(p);
		 
		try {
			ArrayList<FotoProducto>	listaProducto = (ArrayList<FotoProducto>) ParametrizacionFac.getFacade().obtenerListado("getFotoProductoPrincipal",fp);
			//log.info("Hay " + listaProducto.size() + " datos encontradas.");
			
		if (listaProducto.size()>0){
			 fp = listaProducto.get(0);
		}
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return fp;	
	}
	
	public void contarInterraccionPublicacion(){
		log.info("metodo[contarInterraccionPublicacion]");
		Interaccion dto=new Interaccion();
		
		if(usuario.getGrupo()!=0){dto.setUsuarioCreacion(usuario);}
		
		ArrayList<Interaccion> listaInteraccion;
		try {
			listaInteraccion = (ArrayList<Interaccion>) ParametrizacionFac.getFacade().obtenerListado("getCounInteraccionProductoUsuario",dto);
			log.info("Hay " + listaInteraccion.get(0).getDescripcion() + " datos encontradas.");
			idEstadisticaCounInteraccionProductoUsuarioHtml.setContent("<font color=\"white\" size=\"3\" face=\"arial\"><div><b>Interacción con las publicaciones </br></br>"+listaInteraccion.get(0).getDescripcion()+ "</b></div></font>");
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.info("algo salio mal al obtener las interacciones");
		}
		
		
	}
	
	public void contarInterraccionVisitasRegistradasPagina(){
		log.info("metodo[contarInterraccionVisitasRegistradasPagina]");
		
		
		
		ArrayList<Interaccion> listaInteraccion;
		try {
			listaInteraccion = (ArrayList<Interaccion>) ParametrizacionFac.getFacade().obtenerListado("getCounInteraccionVRP");
			log.info("Hay " + listaInteraccion.get(0).getDescripcion() + " datos encontradas.");
			idEstadisticaCounVRPHtml.setContent("<font color=\"white\" size=\"3\" face=\"arial\"><div><b>Visitas a la página </br></br>"+listaInteraccion.get(0).getDescripcion()+ "</b></div></font>");
			idEstadisticaCounVRPHtml.invalidate();
			idEstadisticaRangoFechasCounHtml.setContent("<font color=\"gray\" size=\"2\" face=\"arial\"><div>"+listaInteraccion.get(0).getReferencia()+ "</div></font>");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.info("algo salio mal al obtener las interacciones");
		}
		
		
	}
	
	public String contarInterraccionProducto(Producto dto){
		log.info("metodo[contarInterraccionProducto]");		
		ArrayList<Interaccion> listaInteraccion = null;
		Interaccion interaccion=new Interaccion();
		interaccion.setProducto(dto);
		
		try {
			listaInteraccion = (ArrayList<Interaccion>) ParametrizacionFac.getFacade().obtenerListado("getCountInteraccionProducto",interaccion);
						
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.info("algo salio mal al obtener las interacciones");
		}
		return listaInteraccion.get(0).getDescripcion();
		
		
	}
}
