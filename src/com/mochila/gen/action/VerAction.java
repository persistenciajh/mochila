package com.mochila.gen.action;

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
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Panel;
import org.zkoss.zul.Panelchildren;
import org.zkoss.zul.Separator;
import org.zkoss.zul.Vbox;
import org.zkoss.zul.Window;

import com.mochila.framework.facade.ParametrizacionFac;
import com.mochila.framework.helper.UtilZKProcesosComunesHelper;
import com.mochila.gen.dto.Interaccion;
import com.mochila.gen.dto.Usuario;
import com.mochila.inv.action.ProductoAction;
import com.mochila.inv.dto.FotoProducto;
import com.mochila.inv.dto.Producto;

public class VerAction extends Borderlayout implements AfterCompose {
	protected static Logger log = Logger.getLogger(VerAction.class);
	UtilZKProcesosComunesHelper util = new UtilZKProcesosComunesHelper();
	Image idWinVerImgGrande;
	Vbox idWinVerVboxDescripcion, idWinVerVboxGaleria;
	Listbox idWinlistboxBandboxProducto;
	FotoProducto fotoProducto = new FotoProducto();
	EnviaCorreo maili = new EnviaCorreo();

	@Override
	public void afterCompose() {
		// TODO Auto-generated method stub
		cargar();
		fotoProducto = (FotoProducto) Executions.getCurrent().getSession().getAttribute("FOTO_PRODUCTO_SESSION");
		if (fotoProducto != null) {
			AImage media;
			try {
				if (fotoProducto.getImg() != null) {
					media = new AImage("fp", fotoProducto.getImg());
					idWinVerImgGrande.setContent(media);
				} else {
					idWinVerImgGrande.setSrc("imagen/imageDefaultGrande.png");
				}
				idWinVerImgGrande.setWidth("512px");
				idWinVerImgGrande.setHeight("525px");

				dibujarDetalles();
				dibujarGaleria();
				guardarIteraccion();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				log.info("error al cargar la imagen :" + e);
			}
		} else {
			Executions.createComponents("/index.zul", null, null);
		}
	}

	public void cargar() {
		idWinVerImgGrande = (Image) this.getFellow("idWinVerImgGrande");

		idWinVerVboxDescripcion = (Vbox) this.getFellow("idWinVerVboxDescripcion");

		idWinVerVboxGaleria = (Vbox) this.getFellow("idWinVerVboxGaleria");
		idWinlistboxBandboxProducto = (Listbox) this.getFellow("idWinlistboxBandboxProducto");
	}

	public void dibujarDetalles() {
		log.info("metodo[dibujarDetalles]");

		Html hDescripcion = new Html();
		hDescripcion.setContent("<p>" + fotoProducto.getProducto().getDescripcion() + "</p>");

		hDescripcion.setContent("<div>");
		hDescripcion
				.setContent(hDescripcion.getContent() + "</br><font color=\"gray\" size=\"5\" face=\"arial\"><b><big>"
						+ fotoProducto.getProducto().getNombre() + "</big></b></font></br></br>");
		hDescripcion.setContent(
				hDescripcion.getContent() + "<font color=\"gray\" size=\"3\" face=\"arial\"><p align=\"justify\">"
						+ fotoProducto.getProducto().getDescripcion() + "</p></font></br></br>");

		hDescripcion.setContent(hDescripcion.getContent() + "</div>");
		idWinVerVboxDescripcion.appendChild(hDescripcion);

		int precio = (int) Math.round(fotoProducto.getProducto().getPrecioVenta());

		int precioConDescuento = (int) Math.round(fotoProducto.getProducto().getPrecioVenta()
				- ((fotoProducto.getProducto().getPrecioVenta() * fotoProducto.getProducto().getDescuentoMax()) / 100));

		Hbox hbox = new Hbox();
		if (fotoProducto.getProducto().getDescuentoMax() != 0) {
			hbox.appendChild(util.crearPanelColorTexto("-" + fotoProducto.getProducto().getDescuentoMax() + "%",
					"white", "3", "#006EB3", "20px", "40px"));

			hbox.appendChild(
					util.crearPanelColorTexto("$" + precioConDescuento, "#ff7700", "3", "#E5E5E5", "20px", "55px"));

			hbox.appendChild(
					util.crearPanelColorTexto("<s>$" + precio + "</s>", "gray", "3", "#E5E5E5", "20px", "55px"));
			idWinVerVboxDescripcion.appendChild(hbox);

		} else {

			Html hPrecio = new Html();
			hPrecio.setContent(
					"<font color=\"gray\" size=\"6\" face=\"arial\"><b><big>$" + precio + "</big></b></font></br>");
			idWinVerVboxDescripcion.appendChild(hPrecio);

		}

		Panel disponibilidad = util.crearPanelColorTexto("<b><u>Seguir explorando</u></b>", "#94C120", "3",
				"#E5E5E5", "40px", "155px");

		disponibilidad.addEventListener(Events.ON_CLICK, new EventListener() {
			public void onEvent(Event event) throws Exception {

				Executions.getCurrent().getDesktop().getFirstPage().getFirstRoot().detach();
				Executions.createComponents("/inicio.zul", null, null);
			}
		});

		idWinVerVboxDescripcion.appendChild(disponibilidad);

		Panel volver = (Panel) util.crearPanelColorTexto("Ver disponibilidad en tienda?", "white", "5", "#E40152", "50px", "370px");
		volver.addEventListener(Events.ON_CLICK, new EventListener() {
			public void onEvent(Event event) throws Exception {

				
				Window window = (Window) Executions.createComponents("/disponibilidad.zul", null, null);
				window.setMaximizable(true);
				window.doModal();
			}
		});
		idWinVerVboxDescripcion.appendChild(volver);

	}

	public void dibujarGaleria() {
		log.info("metodo[dibujarGaleria] " + fotoProducto.toString());
		idWinVerVboxGaleria.getChildren().clear();

		fotoProducto.setDescripcion("%%");
		try {
			ArrayList<FotoProducto> listaProducto = (ArrayList<FotoProducto>) ParametrizacionFac.getFacade()
					.obtenerListado("getFotoProducto", fotoProducto);
			log.info("Hay " + listaProducto.size() + " datos encontradas.");

			for (final FotoProducto dto : listaProducto) {
				Panel panel = new Panel();
				panel.setStyle("margin-top:3px");
				panel.setHeight("64px");
				panel.setWidth("64px");
				panel.setBorder("0");
				Panelchildren panelChild = new Panelchildren();

				Vbox vbox = new Vbox();
				vbox.setAlign(CENTER);
				vbox.setPack(CENTER);
				vbox.setHeight("90%");
				vbox.setWidth("100%");

				Image img = new Image();
				AImage media = new AImage("xxx", dto.getImg());
				img.setContent(media);
				img.setWidth("64px");
				img.setHeight("64px");
				img.setStyle("black;font-size:15px;cursor:pointer");
				img.addEventListener(Events.ON_CLICK, new EventListener() {
					public void onEvent(Event event) throws Exception {

						AImage media = new AImage("fp", dto.getImg());
						idWinVerImgGrande.setContent(media);
						idWinVerImgGrande.setWidth("512px");
						idWinVerImgGrande.setHeight("525px");
					}
				});
				vbox.appendChild(img);
				panelChild.appendChild(vbox);
				panel.appendChild(panelChild);
				idWinVerVboxGaleria.appendChild(panel);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void llenarBandboxBusqueda(String criterio) {
		// idWinlistboxBandboxProducto.getChildren().clear();

		log.info("parmatro: " + criterio);

		Listitem listItem = new Listitem();
		listItem.setValue("");
		listItem.setLabel("No se han encontrado resultados para tu búsqueda");

		idWinlistboxBandboxProducto.appendChild(listItem);

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
