package com.mochila.viewModel;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zul.Window;



public class ViewModelStandar {

	public static Logger log = Logger.getLogger(ViewModelStandar.class);
	private Date fechaActualBD;

	public ViewModelStandar() {

		try {
			log.info("ViewModelStandar");
		} catch (Exception e) {
			e.printStackTrace();
			lanzarExcepcion(e);
		}
	}

	

	

	public void lanzarExcepcion(Exception exception) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("EXCEPCION", exception);
		Window window = (Window) Executions.createComponents(
				"/WEB-INF/componentes/ventana_excepcion.zul", null, map);
		window.doModal();
	}

	public Date getFechaActualBD() {
		return fechaActualBD;
	}

	public void setFechaActualBD(Date fechaActualBD) {
		this.fechaActualBD = fechaActualBD;
	}

}