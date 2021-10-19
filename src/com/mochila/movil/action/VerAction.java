package com.mochila.movil.action;

import java.io.IOException;
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
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Html;
import org.zkoss.zul.Image;
import org.zkoss.zul.Panel;
import org.zkoss.zul.Panelchildren;
import org.zkoss.zul.Separator;
import org.zkoss.zul.Vbox;
import org.zkoss.zul.Window;

import com.mochila.framework.facade.ParametrizacionFac;
import com.mochila.framework.helper.UtilZKProcesosComunesHelper;
import com.mochila.gen.action.EnviaCorreo;
import com.mochila.gen.dto.Interaccion;
import com.mochila.gen.dto.Usuario;
import com.mochila.inv.action.ProductoAction;
import com.mochila.inv.dto.FotoProducto;
import com.mochila.inv.dto.Producto;

public class VerAction extends Borderlayout implements AfterCompose {
	protected static Logger log = Logger.getLogger(VerAction.class);
	UtilZKProcesosComunesHelper util = new UtilZKProcesosComunesHelper();
	Image idWinVerImgGrande;
	Hbox idWinVerHboxGaleria;
	Vbox idWinVerVboxDescripcion;
	
	FotoProducto fotoProducto = new FotoProducto();
	
	@Override
	public void afterCompose() {
		// TODO Auto-generated method stub
		cargar();
		fotoProducto = (FotoProducto) Executions.getCurrent().getSession().getAttribute("FOTO_PRODUCTO_SESSION");
		if (fotoProducto != null) {
		AImage media;
		try {
			if(fotoProducto.getImg()!=null){
			media = new AImage("fp",fotoProducto.getImg());
			idWinVerImgGrande.setContent(media);
			}else{
				idWinVerImgGrande.setSrc("../imagen/imageDefaultGrande.png");
			}
			idWinVerImgGrande.setWidth("256px");
			idWinVerImgGrande.setHeight("256px");
			
			dibujarDetalles();
			dibujarGaleria();
			guardarIteraccion();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.info("error al cargar la imagen :"+e);
		}
		}else{
			//Executions.getCurrent().getDesktop().getFirstPage().getFirstRoot().detach();
			//Executions.createComponents("../index.zul", null, null);
			Executions.sendRedirect("../index.zul");
		}
		
	}
	
	public void cargar(){
		idWinVerImgGrande =(Image)this.getFellow("idWinVerImgGrande");
		idWinVerHboxGaleria=(Hbox)this.getFellow("idWinVerHboxGaleria");
		idWinVerVboxDescripcion=(Vbox)this.getFellow("idWinVerVboxDescripcion");
	}
	
	public void dibujarDetalles() {
		log.info("metodo[dibujarDetalles]");
		idWinVerVboxDescripcion.getChildren().clear();
		Html htmlContenedor=new Html();
		htmlContenedor.setContent("<font color=\"gray\" size=\"2\" face=\"arial\"><div>");
		htmlContenedor.setContent(htmlContenedor.getContent()+"<b>"+fotoProducto.getProducto().getNombre()+"</b></br>");
		htmlContenedor.setContent(htmlContenedor.getContent()+fotoProducto.getProducto().getDescripcion());
		htmlContenedor.setContent(htmlContenedor.getContent()+"</div></font>");
		
		idWinVerVboxDescripcion.appendChild(htmlContenedor);
		
		int precio=(int) Math.round(fotoProducto.getProducto().getPrecioVenta());		
		int precioConDescuento=(int) Math.round(fotoProducto.getProducto().getPrecioVenta()-((fotoProducto.getProducto().getPrecioVenta()*fotoProducto.getProducto().getDescuentoMax())/100));	
		
		Hbox hbox =new Hbox();
		if(fotoProducto.getProducto().getDescuentoMax()!=0){
		hbox.appendChild(util.crearPanelColorTexto("-"+fotoProducto.getProducto().getDescuentoMax()+"%",
				"white","1", "#006EB3", "10px", "25px"));
		
		hbox.appendChild(util.crearPanelColorTexto("$"+precioConDescuento,
				"#ff7700","1", "#E5E5E5", "10px", "35px"));
		
		hbox.appendChild(util.crearPanelColorTexto("<s>$"+precio+"</s>",
				"gray","1", "#E5E5E5", "10px", "35px"));
		
		}else{
			
			hbox.appendChild(util.crearPanelColorTexto("$"+precio,
					"#006EB3","2", "#E5E5E5", "10px", "50px"));	
										
		}		
		
		
		
		
		
		Panel disponibilidad=util.crearPanelColorTexto("<u>Seguir explorando</u>",
				"#94C120","1", "#E5E5E5", "15px", "90px");
		
		disponibilidad.addEventListener(Events.ON_CLICK, new EventListener() {
			public void onEvent(Event event) throws Exception {
				//Executions.getCurrent().getDesktop().getFirstPage().getFirstRoot().detach();
				//Executions.createComponents("/disponibilidad.zul", null, null);				
				//Executions.sendRedirect("disponibilidad.zul");
				Executions.sendRedirect("index.zul");
			}
		});
		
		hbox.appendChild(disponibilidad);
		idWinVerVboxDescripcion.appendChild(hbox);
		
	}
	
	public void dibujarGaleria() {
		log.info("metodo[dibujarGaleria] "+fotoProducto.toString());
		idWinVerHboxGaleria.getChildren().clear();
		
		fotoProducto.setDescripcion("%%");
		try {
			ArrayList<FotoProducto>	listaProducto = (ArrayList<FotoProducto>) ParametrizacionFac.getFacade().obtenerListado("getFotoProducto",fotoProducto);
			log.info("Hay " + listaProducto.size() + " datos encontradas.");
			
		for(final FotoProducto dto :listaProducto){
			Panel panel=new Panel();
			panel.setStyle("margin-top:3px");
			panel.setHeight("48px");
			panel.setWidth("48px");
			panel.setBorder("0");					
			Panelchildren panelChild =new Panelchildren();			
			
			Vbox vbox=new Vbox();
			vbox.setAlign(CENTER);
			vbox.setPack(CENTER);
			vbox.setHeight("90%");
			vbox.setWidth("100%");
		
			Image img=new Image();
			AImage media= new AImage("xxx",dto.getImg());
			img.setContent(media);
			img.setWidth("48px");
			img.setHeight("48px");
			img.setStyle("black;font-size:15px;cursor:pointer");
			img.addEventListener(Events.ON_CLICK, new EventListener() {
				public void onEvent(Event event) throws Exception {
					
					AImage media = new AImage("fp",dto.getImg());
					idWinVerImgGrande.setContent(media);
					idWinVerImgGrande.setWidth("256px");
					idWinVerImgGrande.setHeight("256px");
				}
			});
			vbox.appendChild(img);
			panelChild.appendChild(vbox);
			panel.appendChild(panelChild);
			idWinVerHboxGaleria.appendChild(panel);
		}
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void guardarIteraccion() {
		log.info("metodo[guardarIteraccion]");
		Interaccion interaccion = new Interaccion();
		interaccion.setProducto(fotoProducto.getProducto());
		interaccion.setDescripcion("CVM");
		interaccion.setReferencia("IP:" + Executions.getCurrent().getRemoteAddr());
		interaccion.setFechaCreacion(new Date());
		try {
			ParametrizacionFac.getFacade().insertarRegistro("postInteraccion", interaccion);

			String[] ErrorMessage = new String[20];
			ErrorMessage[0] = "e0";
			ErrorMessage[1] = "e1";
			
			Usuario dto= getUsuario(fotoProducto.getProducto().getUsuarioCreacion());

			int correo = EnviaCorreo.enviar("smtp.mi.com.co", "587", 1, 1, "info@tumochila.com.co", "Chrono99", 0,
					"info@tumochila.com.co",dto.getPersona().getCorreoElectronico(), "", "¡A alguien le ha gustado tu Articulo!",
					"<img src=\"http://tumochila.com.co/imagen/logo.png\" height=\"24px\" width=\"124px\"  alt=\"TuMochila logo\">"
							+ "<font color=\"black\" size=\"3\" face=\"arial\">"
							+ "<hr align=\"left\" width=\"50%\"> </hr>" + "<h2>Hola, "
							+ dto.getPersona().getNombres() + ":</h2>"
							+ "<p>A alguien le ha gustado tu articulo " + fotoProducto.getProducto().getNombre()
							+ ".</p>" + "<p>Gracias,<br></br>" + "El equipo de <b>TuMochila</b></p>"
							+ "<hr align=\"left\" width=\"50%\"></hr>" + "</font>"
							+ "<font color=\"black\" size=\"1\" face=\"arial\">" + "Este mensaje se ha enviado a "
							+ dto.getPersona().getCorreoElectronico()
							+ ". Si no quieres recibir este tipo de correos electrónicos de TuMochila, cancela la suscripción."
							+ "</font>",
					ErrorMessage);

			log.info("se guardo la interaccion" + correo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.info("error: algo salio mal al guardar la interaccion" + e);
		}

	}

	public Usuario getUsuario(Usuario dto) {
		try {
			ArrayList<Usuario> listaUsuario = (ArrayList<Usuario>) ParametrizacionFac.getFacade()
					.obtenerListado("getUsuario", dto);
			if (listaUsuario.get(0) != null)
				return listaUsuario.get(0);
		} catch (Exception e) {
			log.info("error: algo salio mal al obtener el usuario " + e);
		}
		return null;
	}
}
