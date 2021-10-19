package com.mochila.viewModel;


import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Borderlayout;
import org.zkoss.zul.Div;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Tabbox;
import org.zkoss.zul.Tabpanel;
import org.zkoss.zul.Tabpanels;
import org.zkoss.zul.Tabs;
import org.zkoss.zul.Tree;
import org.zkoss.zul.Treeitem;

import com.mochila.gen.dto.Usuario;



public class MenuViewModel extends ViewModelStandar {
	

	@Wire
	private Tabbox idMenTbxContenido;

	@Wire
	private Tree idMenTreeMenu;

	private Tabs tabs;

	private Tabpanels tabPanels;

	private List<String> listaTabs;
	private Usuario clienteSesion;
	private String valorRutaSeleccionada;
//	private final boolean implementaCreditos = ((String) Executions.getCurrent().getSession()
//			.getAttribute("implementarCreditos")).equals("S");

	private Tabpanels contenedor;

	private String labelTab;

	private Tabs contenedorTabs;

	@Init
	public void init() {
		log.info("init");

		try {

			log.info(Executions.getCurrent().getBrowser("mobile"));
			log.info(Executions.getCurrent().getBrowser());								 
			clienteSesion = (Usuario) Executions.getCurrent().getSession().getAttribute("USUARIO_SESSION");
			log.info("clienteSesion: " + clienteSesion);
			listaTabs = new ArrayList<String>();

			if (tabs != null) {
				tabs = new Tabs();

			}
			if (tabPanels != null) {

				tabPanels = new Tabpanels();
			}
			valorRutaSeleccionada = "";
		} catch (Exception e) {
			e.printStackTrace();
			log.info("error en el init");
		}

	}

	/**
	 * Metodo obligatorio para cargar los componentes en la clase
	 * 
	 * @param view
	 */
	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
		log.info("init");
		Selectors.wireComponents(view, this, false);
		if (tabs != null) {

			idMenTbxContenido.appendChild(tabs);
		}
		if (tabPanels != null) {

			idMenTbxContenido.appendChild(tabPanels);
		}
	}

	@Command
	@NotifyChange("*")
	public void cerrarSesion() {
		log.info("cerrarSesion");
		try {
			Executions.getCurrent().getSession().invalidate();
			Executions.getCurrent().sendRedirect("/");
		} catch (Exception e) {
			log.info("error al cerrar sesion");
		}
	}

	/* Get y Set */

	public String getValorRutaSeleccionada() {
		return valorRutaSeleccionada;
	}

	public void setValorRutaSeleccionada(String valorRutaSeleccionada) {
		this.valorRutaSeleccionada = valorRutaSeleccionada;
	}

	public Usuario getClienteSesion() {
		return clienteSesion;
	}

	public void setClienteSesion(Usuario clienteSesion) {
		this.clienteSesion = clienteSesion;
	}

	public List<String> getListaTabs() {
		return listaTabs;
	}

	public void setListaTabs(List<String> listaTabs) {
		this.listaTabs = listaTabs;
	}

	@Command
	public void crearTab(String nombre, String ruta) {
		
		log.info("crearTab");

		final String label = nombre;
		
		if (!label.equals("Salir")) {
			if (!listaTabs.contains(label)) {
				
				Div div = new Div();
				div.setSclass("container-fluid py-2");
				div.setStyle("padding-right: 1px !important;padding-left: 1px; !important");
				div.appendChild(Executions.createComponents(ruta, null, null));

				final Tabpanel tabpanel = new Tabpanel();
				tabpanel.setId(crearIdPanel(label));
				tabpanel.setStyle("overflow:auto");

				final Tab tab = new Tab();
				tab.setId(crearIdTab(label));
				tab.setLabel(label);
				tab.setClosable(true);

				
				
				tab.addEventListener(Events.ON_CLOSE, new EventListener<Event>() {

					@Override
					public void onEvent(Event event) throws Exception {
						// TODO Auto-generated method stub
						
						onCloseTab(tab.getLabel());
						tab.detach();
						tabpanel.detach();
						
					}
				
				
				});


				
				
				contenedorTabs.appendChild(tab);
				
			
				contenedor.appendChild(tabpanel);
				
				
				tabpanel.setStyle("color:#336699;padding:0;");
				tabpanel.appendChild(div);

				listaTabs.add(label);
				
			}
			
			contenedorTabs.getTabbox().setSelectedIndex(listaTabs.indexOf(label));
		}

	}

	public String crearIdTab(String label) {
		log.info("crearIdPanel "+"idMenTab".concat(label));

		return "idMenTab".concat(label);
	}

	public String crearIdPanel(String label) {
		log.info("crearIdPanel "+"idMenTabPanel".concat(label));
		return "idMenTabPanel".concat(label);
	}

	public void seleccionarTab(Treeitem item) {
		String label = item.getTreerow().getLabel();
		((Tab) tabs.getFellow(crearIdTab(label))).setSelected(true);
	}



	public EventListener<Event> crearListenerSeleccion(final Treeitem item) {
		return new EventListener<Event>() {
			@Override
			public void onEvent(Event arg0) throws Exception {
				idMenTreeMenu.selectItem(item);
			}
		};
	}

	public void onCloseTab(String page) {
		
		log.info("Ejecutando el metodo [onCloseTab]");
	
	
		log.info(listaTabs.indexOf(page));
		
		
		if(listaTabs.size()>0) {
		
			if(listaTabs.indexOf(page) > 0 && listaTabs.get(listaTabs.indexOf(page)-1) != null) {
				contenedorTabs.getTabbox().setSelectedIndex(listaTabs.indexOf(page)-1);
				
				log.info(listaTabs.indexOf(page)-1);
			}else if(listaTabs.indexOf(page) < listaTabs.size()-1  && listaTabs.get(listaTabs.indexOf(page)+1) != null) {
				contenedorTabs.getTabbox().setSelectedIndex(listaTabs.indexOf(page)+1);
				log.info(listaTabs.indexOf(page)+1);
			}
		}
		
		listaTabs.remove(page);
	}

//	public boolean isImplementaCreditos() {
//		return implementaCreditos;
//	}

	public String getLabelTab() {
		return labelTab;
	}

	public void setLabelTab(String labelTab) {
		this.labelTab = labelTab;
	}

	@NotifyChange("*")
	@Command
	public void onCargarFormulario(@BindingParam("ruta") String ruta, @BindingParam("nombre") String nombreTab) {
		log.info("onCargarFormulario");
		this.labelTab = nombreTab;
		crearTab(labelTab, ruta);

	}

	@Command
	@NotifyChange("*")
	public void cargarContenedor(@BindingParam("componente") Tabpanels contenedor) {
		log.info("cargarContenedor");
		this.contenedor = contenedor;
	}

	@Command
	@NotifyChange("*")
	public void cargarContenedorTabs(@BindingParam("componente") Tabs contenedorTabs) {
		log.info("cargarContenedor");
		this.contenedorTabs = contenedorTabs;
	}

}
