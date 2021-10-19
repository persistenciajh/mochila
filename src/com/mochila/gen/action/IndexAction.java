package com.mochila.gen.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.zkoss.image.AImage;
import org.zkoss.util.media.AMedia;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.ext.AfterCompose;
import org.zkoss.zul.Borderlayout;
import org.zkoss.zul.Button;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Html;
import org.zkoss.zul.Image;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Panel;
import org.zkoss.zul.Panelchildren;
import org.zkoss.zul.Separator;
import org.zkoss.zul.Slider;
import org.zkoss.zul.Space;
import org.zkoss.zul.Vbox;
import org.zkoss.zul.West;
import org.zkoss.zul.Window;

import com.mochila.framework.util.Handlers;
import com.mochila.framework.util.MyBatisUtil;
import com.mochila.framework.helper.AppContext;
import com.mochila.framework.helper.UtilZKProcesosComunesHelper;
import com.mochila.framework.facade.ParametrizacionFac;
import com.mochila.gen.dto.Interaccion;
import com.mochila.gen.dto.Persona;
import com.mochila.gen.dto.Usuario;
import com.mochila.inv.dto.FotoProducto;
import com.mochila.inv.dto.Producto;
import com.mochila.inv.dto.TipoProducto;

public class IndexAction extends Borderlayout implements AfterCompose {
	public static Logger log = Logger.getLogger(IndexAction.class);
	private List<Persona> detalleColumnaList;
	
	ArrayList<TipoProducto> listaTipoProducto = new ArrayList<TipoProducto>();
	Usuario usuario = new Usuario();
	Producto producto;

	double precioMin = 0;
	double precioMax = 0;
	int imagenPorFila = 4;

	Intbox idWinSlider;
	West idWinWestFiltroNorte;
	West idWinWestFiltroSur;
	Listbox idWinlistboxBandboxProducto;

	UtilZKProcesosComunesHelper util = new UtilZKProcesosComunesHelper();

	@Override
	public void afterCompose() {
		log.info("metodo[afterCompose]");
		// TODO Auto-generated method stub
		cargar();
		Properties appProperties = AppContext.getInstance().getAppProperties();
		String mostrarFiltoPrincipal = appProperties.getProperty("ap.mostrarFiltoPrincipal");

		if (mostrarFiltoPrincipal.equals("N")) {
			idWinWestFiltroSur.setVisible(false);
			idWinWestFiltroNorte.setVisible(false);
			imagenPorFila = 5;
		}

		usuario = (Usuario) Executions.getCurrent().getSession().getAttribute("USUARIO_SESSION");

		producto = (Producto) Executions.getCurrent().getSession().getAttribute("PRODUCTO_SESSION");

		if (producto == null) {
			log.info("xxxxxxx");// solo entra una vez aqui y es cuando inicia la
								// pag y muestra todos los productos
			producto = new Producto();

		}

		if (usuario != null) {
			log.info("Usuario: " + usuario.getNombreUsuario());

			iniciarSesion();

		} else {

			cargarCategorias();
			cargarProducto();
			cargarPreciosTopes();
			guardarIteraccion();
		}
	}

	public void cargar() {
		idWinSlider = (Intbox) this.getFellow("idWinSlider");
		idWinWestFiltroNorte = (West) this.getFellow("idWinWestFiltroNorte");
		idWinWestFiltroSur = (West) this.getFellow("idWinWestFiltroSur");

		idWinlistboxBandboxProducto = (Listbox) this.getFellow("idWinlistboxBandboxProducto");
	}

	public void iniciarSesion() {
		log.info("metodo[iniciarSesion]");
		if (usuario.getNombreUsuario() != null) {
			Executions.getCurrent().getDesktop().getFirstPage().getFirstRoot().detach();
			if (usuario.getGrupo() != 0) {
				Executions.createComponents("/accessControl/merchan.zul", null, null);
			} else {
				Executions.createComponents("/accessControl/menu.zul", null, null);
			}
		}
	}

	public void winUsuario() {
		log.info("metodo[winUsuario]");
		Window window = (Window) Executions.createComponents("/login.zul", null, null);
		window.setMaximizable(true);
		window.doModal();

	}

	public void cargarCategorias() {
		log.info("metodo[cargarCategorias]");
		Hbox idWinHboxCategorias = (Hbox) this.getFellow("idWinHboxCategorias");
		listaTipoProducto.clear();
		TipoProducto tp = new TipoProducto();
		tp.setNombre("%%");
		try {
			listaTipoProducto = (ArrayList<TipoProducto>) ParametrizacionFac.getFacade()
					.obtenerListado("getTipoProducto", tp);

			log.info("Hay " + listaTipoProducto.size() + " datos encontradas.");

			for (final TipoProducto dto : listaTipoProducto) {
				Panel panel = new Panel();
				panel.setBorder("0");
				panel.setHeight("30px");
				panel.setWidth(
						(dto.getNombre().length() < 10 ? "60px" : dto.getNombre().length() < 15 ? "100px" : "140px"));
				panel.setBorder("0");

				Panelchildren panelChild = new Panelchildren();
				panelChild.setId("idCategoria_" + dto.getSecuencia());
				panelChild.setStyle("color:black;font-size:15px;cursor:pointer");
				panelChild.addEventListener(Events.ON_CLICK, new EventListener() {

					public void onEvent(Event event) throws Exception {

						try {
							producto.setTipoProducto(dto);
							producto.setUsuarioCreacion(null);
							dibujarCategorias(dto.getSecuencia());
							cargarProducto();
						} catch (Exception e) {
							// TODO: handle exception
						}

					}
				});

				Vbox vbox = new Vbox();
				vbox.setAlign(CENTER);
				vbox.setPack(CENTER);
				vbox.setHeight("90%");
				vbox.setWidth("100%");

				Html h = new Html();
				String contenido = (dto.getNombre().length() < 20 ? dto.getNombre()
						: (dto.getNombre().substring(0, 16) + "..."));
				contenido = (contenido.charAt(0) + "").toUpperCase() + contenido.substring(1, contenido.length());

				h.setContent("<font  size=\"2\" face=\"arial\"><div>");
				h.setContent(h.getContent() + "<b>" + contenido + "</b>");
				h.setContent(h.getContent() + "</div></font>");

				vbox.appendChild(h);
				panelChild.appendChild(vbox);
				panel.appendChild(panelChild);
				idWinHboxCategorias.appendChild(panel);

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.info("Error: " + e);
			e.printStackTrace();
		}
	}

	public void dibujarCategorias(Long secuencia) {
		log.info("metodo[dibujarCategorias]");
		((Panelchildren) this.getFellow("idCategoriaNovedad")).setStyle("color:black;font-size:15px;cursor:pointer");

		for (TipoProducto dto : this.listaTipoProducto) {
			((Panelchildren) this.getFellow("idCategoria_" + dto.getSecuencia()))
					.setStyle("color:black;font-size:15px;cursor:pointer");

			if (dto.getSecuencia() == secuencia) {

				Panelchildren pc = (Panelchildren) this.getFellow("idCategoria_" + dto.getSecuencia());
				pc.setStyle("background:#E5E5E5;color:white;font-size:15px;cursor:pointer");

			}
		}
	}

	public void cargarNovedades() {
		producto.setTipoProducto(null);
		producto.setUsuarioCreacion(null);
		dibujarCategorias(new Long(999));
		((Panelchildren) this.getFellow("idCategoriaNovedad"))
				.setStyle("background:#E5E5E5;color:white;font-size:15px;cursor:pointer");
		cargarProducto();
	}
	
	public void cargarDesdeCategoria() {
		//producto.setTipoProducto(null);
		producto.setUsuarioCreacion(null);
//		dibujarCategorias(new Long(999));
//		((Panelchildren) this.getFellow("idCategoriaNovedad"))
//				.setStyle("background:#E5E5E5;color:white;font-size:15px;cursor:pointer");
		cargarProducto();
	}

	public void cargarProducto() {
		log.info("metodo[cargarProducto]");
		Vbox idWinVboxProductos = (Vbox) this.getFellow("idWinVboxProductos");
		idWinVboxProductos.getChildren().clear();

		producto.setNombre("%%");
		

		try {
			ArrayList<Producto> listaProducto = (ArrayList<Producto>) ParametrizacionFac.getFacade()
					.obtenerListado("getProducto", producto);

			log.info("Hay " + listaProducto.size() + " datos encontradas.");

			for (int i = 0; i < listaProducto.size();) {
				// Producto dto=listaProducto.get(i);
				Hbox fila = new Hbox();
				for (int j = 1; j <= imagenPorFila; j++) {
					// log.info("i= "+i+" j= "+j);
					if (i < listaProducto.size()) {
						final Producto dto = listaProducto.get(i);

						Panel panel = new Panel();
						panel.setStyle("margin-top:3px");
						panel.setHeight("356px");
						panel.setWidth("256px");
						panel.setBorder("1");
						Panelchildren panelChild = new Panelchildren();

						Vbox vbox = new Vbox();
						vbox.setAlign(CENTER);
						vbox.setPack(CENTER);
						vbox.setHeight("90%");
						vbox.setWidth("100%");

						Image img = new Image();

						final FotoProducto fp = getFotoProductoPrincipal(dto);

						if (fp.getImg() != null) {

							AImage media = new AImage("xxx", fp.getImg());
							img.setContent(media);
							fp.setProducto(dto);
						} else {
							img.setSrc("imagen/imageDefault.png");
						}
						img.setWidth("256px");
						img.setHeight("256px");
						img.setStyle("black;font-size:15px;cursor:pointer");

						panelChild.addEventListener(Events.ON_CLICK, new EventListener() {
							public void onEvent(Event event) throws Exception {
								cargarProductoVer(fp);
							}
						});

						vbox.appendChild(img);

						Html htmlContenedor = new Html();
						htmlContenedor.setContent("<font color=\"gray\" size=\"3\" face=\"arial\"><div>");
						htmlContenedor.setContent(htmlContenedor.getContent() + "<b>" + dto.getNombre() + "</b>");
						htmlContenedor.setContent(htmlContenedor.getContent() + "</div></font>");
						vbox.appendChild(htmlContenedor);

						int precio = (int) Math.round(dto.getPrecioVenta());

						int precioConDescuento = (int) Math
								.round(dto.getPrecioVenta() - ((dto.getPrecioVenta() * dto.getDescuentoMax()) / 100));

						Hbox hbox = new Hbox();
						if (dto.getDescuentoMax() != 0) {
							hbox.appendChild(util.crearPanelColorTexto("-" + dto.getDescuentoMax() + "%", "white", "3",
									"#006EB3", "20px", "40px"));

							hbox.appendChild(util.crearPanelColorTexto("$" + precioConDescuento, "#ff7700", "3",
									"white", "20px", "55px"));

							hbox.appendChild(util.crearPanelColorTexto("<s>$" + precio + "</s>", "gray", "3", "white",
									"20px", "55px"));
						} else {

							hbox.appendChild(
									util.crearPanelColorTexto("$" + precio, "#006EB3", "4", "white", "20px", "70px"));

							// hbox.appendChild(util.crearPanelColorTexto("<u>Ver
							// más</u>",
							// "#ff7700","3", "white", "20px", "80px"));

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

		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.info("Error: " + e);
			e.printStackTrace();
		}
	}

	public FotoProducto getFotoProductoPrincipal(Producto p) {
		log.info("metodo[getFotoProductoPrincipal]");
		FotoProducto fp = new FotoProducto();

		fp.setProducto(p);

		try {
			ArrayList<FotoProducto> listaProducto = (ArrayList<FotoProducto>) ParametrizacionFac.getFacade()
					.obtenerListado("getFotoProductoPrincipal", fp);
			log.info("Hay " + listaProducto.size() + " datos encontradas.");

			if (listaProducto.size() > 0) {
				fp = listaProducto.get(0);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return fp;
	}

	private void cargarProductoVer(FotoProducto fp) {
		log.info("metodo[cargarProductoVer]");
		// TODO Auto-generated method stub PRODUCTO_SESSION
		Executions.getCurrent().getSession().setAttribute("PRODUCTO_SESSION", producto);
		Executions.getCurrent().getSession().setAttribute("FOTO_PRODUCTO_SESSION", fp);

		Executions.sendRedirect("ver.zul");
	}

	public void cargarProductoporPrecioTopes(int a) {

		log.info("metodo[cargarProductoporPrecioTopes]" + a);
		if (idWinSlider.getValue() == null) {
			producto.setPrecioVenta(this.precioMax);
			producto.setUltPrecioCompra(precioMin);
		} else {
			producto.setPrecioVenta(idWinSlider.getValue() * 1.0);
			producto.setUltPrecioCompra(precioMin);

		}
		log.info(producto.getUltPrecioCompra() + "-" + producto.getPrecioVenta());
		cargarProducto();
	}

	private void cargarPreciosTopes() {
		Producto p = new Producto();

		Html idWinHtmlRangoPrecio = (Html) this.getFellow("idWinHtmlRangoPrecio");

		try {
			ArrayList<Producto> listaProducto = (ArrayList<Producto>) ParametrizacionFac.getFacade()
					.obtenerListado("getPreciosTopes");
			log.info("Hay " + listaProducto.size() + " datos encontradas.");
			if (listaProducto.size() > 0) {
				p = listaProducto.get(0);
				precioMax = listaProducto.get(0).getPrecioVenta().intValue();
				precioMin = listaProducto.get(0).getUltPrecioCompra().intValue();
				idWinHtmlRangoPrecio.setContent("$" + listaProducto.get(0).getUltPrecioCompra().intValue() + " - $"
						+ listaProducto.get(0).getPrecioVenta().intValue());

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
		interaccion.setDescripcion("VRP");
		interaccion.setReferencia("IP:" + Executions.getCurrent().getRemoteAddr());
		interaccion.setFechaCreacion(new Date());
		try {
			ParametrizacionFac.getFacade().insertarRegistro("postInteraccion", interaccion);
			log.info("se guardo la interaccion");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.info("error: algo salio mal al guardar la interaccion" + e);
		}

	}

}
