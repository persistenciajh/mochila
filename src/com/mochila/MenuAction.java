package com.mochila;

import java.util.List;

import org.apache.log4j.Logger;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.ext.AfterCompose;
import org.zkoss.zul.Borderlayout;
import org.zkoss.zul.DefaultTreeNode;
import org.zkoss.zul.Include;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Tabbox;
import org.zkoss.zul.Tabpanel;
import org.zkoss.zul.Tabpanels;
import org.zkoss.zul.Tabs;
import org.zkoss.zul.Tree;
import org.zkoss.zul.Treecell;


import com.mochila.framework.contract.IConstantes;
import com.mochila.framework.util.MyItemTree;

public class MenuAction extends Borderlayout implements AfterCompose {
	protected static Logger log = Logger.getLogger(MenuAction.class);
//	Include pagina;
//	Usuario usuario =new Usuario();
//	Label idMenuLbxNombreUsuario;
//	
	
	private Tree menuOpciones;
	private Listbox idMNZLbxUnidadesSession;
	private Tabbox idMNZTbxVentanas;
	private int selectedBefore;
	
	
	@Override
	public void afterCompose() {
		log.info("Ejecutando el método [ afterCompose ]...");
		
		// TODO Auto-generated method stub
//		pagina = (Include) this.getFellow("include");
//		idMenuLbxNombreUsuario=(Label)this.getFellow("idMenuLbxNombreUsuario");
////		
//		usuario = (Usuario) Executions.getCurrent().getSession()
//				.getAttribute("USUARIO_SESSION");
//	   
//		
//		if(usuario != null){
//			log.info("Usuario: " + usuario.getNombreUsuario());
//			idMenuLbxNombreUsuario.setValue(usuario.getNombreUsuario());			
//		}
		
		menuOpciones = (Tree) this.getFellow("idMNZTreeMenuOpciones");
		idMNZTbxVentanas = (Tabbox) this.getFellow("idMNZTbxVentanas");
		//idMNZLbxUnidadesSession = (Listbox) this.getFellow("idMNZLbxUnidadesSession");
		//cargarUnidadesSession();
		Execution exec = Executions.getCurrent();
		String tipoMenu = (String) exec.getArg().get("tipoMenu");

	}
	
	
	

	@SuppressWarnings("rawtypes")
	public void cargarVentana() {
		log.info("Ejecutando el mEtodo [ cargarVentana ]... ");

		try {
			Tree menuOpciones = (Tree) this.getFellow("idMNZTreeMenuOpciones");
			Tabbox tabboxVentanas = (Tabbox) this.getFellow("idMNZTbxVentanas");
			String mZul = null;
			String mTitulo = null;
			String mID = null;

			// 1. Obtener la informacion suficiente para cargar la ventana
			if (IConstantes.CONSTRUIR_OPCIONES_MENU) {
				DefaultTreeNode nodo = (DefaultTreeNode) menuOpciones.getSelectedItem().getValue();
				MyItemTree item = (MyItemTree) nodo.getData();

				mZul = item.getValor();
				mTitulo = item.getEtiqueta();
				mID = item.getId();
			} else {
				mZul = menuOpciones.getSelectedItem().getValue().toString();
				mTitulo = ((Treecell) menuOpciones.getSelectedItem().getTreerow().getFirstChild()).getLabel()
						.toString();
				mID = menuOpciones.getSelectedItem().getId();
			}

			// si es null el value de item seleccionado indica que no tiene un
			// zul que ejecutar
			if (mZul != null) {
				log.info("Opcion de menu: " + mZul);
				log.info("Id item selected: " + mID);
				log.info("Titulo: " + mTitulo);

				if (!mID.equals("idMNZTitemSalir") && !mID.equals("idMNZTitemCerrarTodos")) {
					// 2. configuramos el componente <tabbox> si es
					// necesario
					if (tabboxVentanas.getTabs() == null) {
						inicializarTabbox();
					}
					// 3. Revisar que la ventana ya exista en el
					// listado de
					// tabsmessage
					Integer indice = this.getTab(mID);
					if (indice >= 0) {
						// -- seleccionamos el tab correspwondiente
						// y
						// salimos del metodo
						tabboxVentanas.setSelectedIndex(indice);
						menuOpciones.clearSelection();
						return;
					}
					if (mID.equals("idMNZTitemParametro") && tabboxVentanas.hasFellow("idPRZWinParametro")) {

						indice = this.getTab("idMNZTitemGEParametro");
						if (indice >= 0) {
							// -- seleccionamos el tab
							// correspwondiente y
							// salimos del metodo
							tabboxVentanas.setSelectedIndex(indice);
							menuOpciones.clearSelection();
							return;
						}
					} else if (mID.equals("idMNZTitemGEParametro") && tabboxVentanas.hasFellow("idPRZWinParametro")) {

						indice = this.getTab("idMNZTitemParametro");
						if (indice >= 0) {
							// -- seleccionamos el tab
							// correspwondiente y
							// salimos del metodo
							tabboxVentanas.setSelectedIndex(indice);
							menuOpciones.clearSelection();
							return;
						}
					}

					// 4. Prepara un nuevo Tab que contenga la
					// ventana a
					// cargar
					this.inicializarTab(mTitulo, mID);

					// 5. cargar el contenido.
					Tabpanel areaTrabajo = (Tabpanel) this.getFellow(mID +"cuerpo");
					
					log.info("jh---->  idZul "+mZul+" id area "+areaTrabajo.getId());
					Executions.createComponents(mZul, areaTrabajo, null);

					// 6. ejecutamos cualquier logica adicinal antes
					// de
					// terminar este metodo
					menuOpciones.clearSelection();

					// se selecciona el tab que se acaba de crear
					indice = this.getTab(mID);
					tabboxVentanas.setSelectedIndex(indice);
				} else {
					if (mID.equals("idMNZTitemCerrarTodos")) {
						tabboxVentanas.getChildren().clear();
					} else {
						this.onSalir();
					}
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * @type Metodo de la clase IPZMenuAction
	 * @name getTab
	 * @return Integer
	 * @param pID
	 * @desc Este metodo se encarga de obtener un tab
	 */
	public Integer getTab(String pID) {
		Tabs grupoCabecerasTabs = (Tabs) this.getFellow("idMNZTabsHeaderVentanas");
		Integer i = 0; // -mantenemos un indice del tab, los tab existentes son
						// indexados desde el 0 (cero)

		if (grupoCabecerasTabs.getChildren().size() <= 0) {
			// -- simplemente no hay que evaluar
			return -2;
		} else {

			List<Tab> listadoTabsVentanas = grupoCabecerasTabs.getChildren();
			for (Tab tab : listadoTabsVentanas) {
				if (tab.getId().equals(pID + "cabeza")) {
					// -- el tab ya existe!!!!
					return i;
				}
				i++;
			}
			// --el tab no fue encontrado
			return -1;
		}
	}
	
	
	
	/**
	 * @type Metodo de la clase IPZMenuAction
	 * @name inicializarTab
	 * @return void
	 * @desc Este metodo se encarga de preparar un panel vacï¿œo para su uso.
	 */
	public void inicializarTab(String pTabTitulo, String pID) {

		// ---creamos un elemento <tab>
		Tabs grupoCabecerasTabs = (Tabs) this.getFellow("idMNZTabsHeaderVentanas");
		Tab tab = new Tab();
		tab.setLabel(pTabTitulo);
		tab.setId(pID + "cabeza");
		tab.setClosable(true);
		grupoCabecerasTabs.appendChild(tab);
		grupoCabecerasTabs.setContext("idMNZPUPCerrarFormas");

		// -- creamos un elemento <tabpanel>
		Tabpanels grupoTabPanels = (Tabpanels) this.getFellow("idMNZTabpanelsBodyVentanas");
		Tabpanel tabpanel = new Tabpanel();
		tabpanel.setId(pID + "cuerpo");
		grupoTabPanels.appendChild(tabpanel);
	}
	
	
	/**
	 * @type Metodo de la clase IPZMenuAction
	 * @name inicializarTabbox
	 * @return void
	 * @desc Este metodo se encarga de inicializar el tabbox de las ventanas
	 */
	public void inicializarTabbox() {
		Tabbox tabboxVentanas = (Tabbox) this.getFellow("idMNZTbxVentanas");

		// --- creamos su componente <tabs>
		Tabs grupoCabecerasTabs = new Tabs();
		grupoCabecerasTabs.setId("idMNZTabsHeaderVentanas");
		tabboxVentanas.appendChild(grupoCabecerasTabs);

		// --- creamos su componente <tabs>
		Tabpanels grupoTabPanels = new Tabpanels();
		grupoTabPanels.setId("idMNZTabpanelsBodyVentanas");
		tabboxVentanas.appendChild(grupoTabPanels);
	}
	
	
	public void onCerrarAllFormas() {
		log.info("[ onCerrarAllFormas ]");
		try {
			idMNZTbxVentanas.getChildren().clear();

		} catch (Exception e) {
			log.info("error: algo salio mal al cerrar las formas"+e);
		}

	}

	
//	public void abrirPagina(String pagina){
//		this.pagina.setSrc(pagina);
//	}
//
	public void onSalir() {
		log.info("cerrarSesion");
		try {
			Executions.getCurrent().getSession().invalidate();
			Executions.getCurrent().sendRedirect("/");
		} catch (Exception e) {
			log.info("error: al cerrar sesion");
		}
	}
	
	public void cerrarSesion() {
		log.info("cerrarSesion");
		try {
			Executions.getCurrent().getSession().invalidate();
			Executions.getCurrent().sendRedirect("/");
		} catch (Exception e) {
			log.info("error: al cerrar sesion");
		}
	}
	
}
