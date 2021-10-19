package com.mochila.gen.assembler;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.zkoss.zk.ui.AbstractComponent;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Row;
import org.zkoss.zul.Rows;
import org.zkoss.zul.Textbox;



import com.mochila.framework.helper.UtilZKProcesosComunesHelper;
import com.mochila.gen.dto.GEParametrosNiif;
import com.mochila.gen.dto.TablaDinamica;

public class GEReportesNiifAssembler   {
  protected static Logger log = Logger.getLogger(GEReportesNiifAssembler.class);

  public static void crearRowDesdeDTO(List listaDTOs, Rows rows, boolean plano) throws Exception {
    log.info("Ejecutando metodo [crearRowDesdeDTO]");
    log.info("tamaño lista parametros: "+listaDTOs.size());
    rows.getChildren().clear();
    for (Object dto : listaDTOs) {
      GEParametrosNiif ParametrosNiif = (GEParametrosNiif) dto;
      Row row = new Row();
      String Requerido = ParametrosNiif.getRequerido().equals("S") ? " * " : "    ";
      Label label = new Label(Requerido);
      label.setStyle("color: red");
      row.appendChild(label);
      log.info(ParametrosNiif.getDescripcion());
      row.appendChild(new Label(ParametrosNiif.getDescripcion() == null
          || ParametrosNiif.getDescripcion().isEmpty() ? ParametrosNiif.getNameParametro()
          : ParametrosNiif.getDescripcion()));
      Textbox textbox = new Textbox();
      textbox.setId("idtbox" + ParametrosNiif.getNameParametro());
      if (Requerido.equals(" * ")) {
        textbox.setConstraint("no empty");
      }
      row.appendChild(textbox);
      if (plano) {
        Combobox combo = new Combobox();
        combo.setId("idCbox" + ParametrosNiif.getNameParametro());
        
        Comboitem comboitem = new Comboitem("Igual");
        
        comboitem.setValue("=");
        combo.appendChild(comboitem);

        comboitem = new Comboitem("Like");
        comboitem.setValue("like");
        combo.appendChild(comboitem);
        
        
        comboitem = new Comboitem("Mayor o igual");
        comboitem.setValue(">=");
        combo.appendChild(comboitem);
        
        
        comboitem = new Comboitem("Menor o igual");
        comboitem.setValue("<=");
        combo.appendChild(comboitem);
        
        

        combo.appendChild(comboitem);
        combo.setReadonly(true);
        
        if (ParametrosNiif.getNameParametro().endsWith("_INICIAL")) combo.setSelectedIndex(2);
        else if (ParametrosNiif.getNameParametro().endsWith("_FINAL")) combo.setSelectedIndex(3);
             else combo.setSelectedIndex(1);
        
        row.appendChild(combo);
      }
      rows.appendChild(row);
    }

  }

  public void crearListItemsDesdeDto(List listaDTOs, Listbox Lista) {
    Lista.getItems().clear();
    Lista.applyProperties();
    if (listaDTOs != null) {
      if (listaDTOs.size() != 0) {
        for (Object dto : listaDTOs) {

          Listitem ListItem = new Listitem();
          TablaDinamica datos = (TablaDinamica) dto;

          if (datos.getColumna1() != null)
            ListItem.appendChild(new Listcell(datos.getColumna1()));

          if (datos.getColumna2() != null)
            ListItem.appendChild(new Listcell(datos.getColumna2()));

          if (datos.getColumna3() != null)
            ListItem.appendChild(new Listcell(datos.getColumna3()));

          if (datos.getColumna4() != null)
            ListItem.appendChild(new Listcell(datos.getColumna4()));

          if (datos.getColumna5() != null)
            ListItem.appendChild(new Listcell(datos.getColumna5()));

          if (datos.getColumna6() != null)
            ListItem.appendChild(new Listcell(datos.getColumna6()));

          if (datos.getColumna7() != null)
            ListItem.appendChild(new Listcell(datos.getColumna7()));

          if (datos.getColumna8() != null)
            ListItem.appendChild(new Listcell(datos.getColumna8()));

          if (datos.getColumna9() != null)
            ListItem.appendChild(new Listcell(datos.getColumna9()));

          if (datos.getColumna10() != null)
            ListItem.appendChild(new Listcell(datos.getColumna10()));

          if (datos.getColumna11() != null)
            ListItem.appendChild(new Listcell(datos.getColumna11()));

          if (datos.getColumna12() != null)
            ListItem.appendChild(new Listcell(datos.getColumna12()));

          if (datos.getColumna13() != null)
            ListItem.appendChild(new Listcell(datos.getColumna13()));

          if (datos.getColumna14() != null)
            ListItem.appendChild(new Listcell(datos.getColumna14()));

          if (datos.getColumna15() != null)
            ListItem.appendChild(new Listcell(datos.getColumna15()));

          if (datos.getColumna16() != null)
            ListItem.appendChild(new Listcell(datos.getColumna16()));

          if (datos.getColumna17() != null)
            ListItem.appendChild(new Listcell(datos.getColumna17()));

          if (datos.getColumna18() != null)
            ListItem.appendChild(new Listcell(datos.getColumna18()));

          if (datos.getColumna19() != null)
            ListItem.appendChild(new Listcell(datos.getColumna19()));

          if (datos.getColumna20() != null)
            ListItem.appendChild(new Listcell(datos.getColumna20()));

          if (datos.getColumna21() != null)
            ListItem.appendChild(new Listcell(datos.getColumna21()));

          if (datos.getColumna22() != null)
            ListItem.appendChild(new Listcell(datos.getColumna22()));

          if (datos.getColumna23() != null)
            ListItem.appendChild(new Listcell(datos.getColumna23()));

          if (datos.getColumna24() != null)
            ListItem.appendChild(new Listcell(datos.getColumna24()));

          if (datos.getColumna25() != null)
            ListItem.appendChild(new Listcell(datos.getColumna25()));

          if (datos.getColumna26() != null)
            ListItem.appendChild(new Listcell(datos.getColumna26()));

          if (datos.getColumna27() != null)
            ListItem.appendChild(new Listcell(datos.getColumna27()));

          if (datos.getColumna28() != null)
            ListItem.appendChild(new Listcell(datos.getColumna28()));

          if (datos.getColumna29() != null)
            ListItem.appendChild(new Listcell(datos.getColumna29()));

          if (datos.getColumna30() != null)
            ListItem.appendChild(new Listcell(datos.getColumna30()));

          if (datos.getColumna31() != null)
            ListItem.appendChild(new Listcell(datos.getColumna31()));

          if (datos.getColumna32() != null)
            ListItem.appendChild(new Listcell(datos.getColumna32()));

          ListItem.appendChild(new Listcell("A"));
          Lista.appendChild(ListItem);
          Lista.setMold("paging");
        }
      }

    }

  }

  public static void crearListItemsColumnasDesdeDto(List listaDTOs, Listbox Lista) {
    log.info("Ejecutando metodo [crearListItemsColumnasDesdeDto]");
    Lista.getItems().clear();
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    if (listaDTOs != null) {
      if (listaDTOs.size() != 0) {
        for (Object dto : listaDTOs) {
          log.info("for cargue");
          
          
          
          
          Listitem ListItem = new Listitem();
          GEParametrosNiif ParametrosNiif = (GEParametrosNiif) dto;
          
          if (ParametrosNiif.getNameParametro().endsWith("_INICIAL") || ParametrosNiif.getNameParametro().endsWith("_FINAL")){
            
          }else{
            ListItem.appendChild(new Listcell(ParametrosNiif.getNameParametro() + ""));
            Lista.getItems().add(ListItem);
          }
          
          
          /*
          if (ParametrosNiif.getNameParametro().endsWith("_INICIAL") || ParametrosNiif.getNameParametro().endsWith("_FINAL")){
              log.info("if no seleleccionar parametro: "+Lista.getSelectedIndex());
              ListItem.setCheckable(false);
          }else{
            log.info("else seleleccionar parametro index: "+Lista.getSelectedIndex());
            ListItem.setCheckable(true);

          }
          */
              
        }       
        
        Lista.selectAll();
        Lista.setMold("paging");
        Lista.applyProperties();
        Lista.invalidate();
        
      }

    }
  }

  public static void crearCellDesdeDTO(List ListDTO, ArrayList<ArrayList<String>> data) {
    for (Object dto : ListDTO) {
      TablaDinamica datos = (TablaDinamica) dto;
      ArrayList<String> cells = new ArrayList<String>();
      SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

      cells.add((datos.getColumna1()));
      cells.add((datos.getColumna2()));
      cells.add((datos.getColumna3()));
      cells.add((datos.getColumna4()));
      cells.add((datos.getColumna5()));
      cells.add((datos.getColumna6()));
      cells.add((datos.getColumna7()));
      cells.add((datos.getColumna8()));
      cells.add((datos.getColumna9()));
      cells.add((datos.getColumna10()));
      cells.add((datos.getColumna11()));
      cells.add((datos.getColumna12()));
      cells.add((datos.getColumna13()));
      cells.add((datos.getColumna14()));
      cells.add((datos.getColumna15()));
      cells.add((datos.getColumna16()));
      cells.add((datos.getColumna17()));
      cells.add((datos.getColumna18()));
      cells.add((datos.getColumna19()));
      cells.add((datos.getColumna20()));
      cells.add((datos.getColumna21()));
      cells.add((datos.getColumna22()));
      cells.add((datos.getColumna23()));
      cells.add((datos.getColumna24()));
      cells.add((datos.getColumna25()));
      cells.add((datos.getColumna26()));
      cells.add((datos.getColumna27()));
      cells.add((datos.getColumna28()));
      cells.add((datos.getColumna29()));
      cells.add((datos.getColumna30()));
      cells.add((datos.getColumna31()));
      cells.add((datos.getColumna32()));

      data.add(cells);
    }

  }

  public static void crearComboItemDesdeDTO(List listaDTOs, Combobox combo) {
    combo.getChildren().clear();
    Comboitem Comboitem = new Comboitem("Agregar Todos");
    combo.appendChild(Comboitem);
    for (Object dto : listaDTOs) {
      GEParametrosNiif ParametrosNiif = (GEParametrosNiif) dto;
      Comboitem = new Comboitem(ParametrosNiif.getDescripcion());
      Comboitem.setValue(ParametrosNiif.getNameParametro());
      combo.appendChild(Comboitem);
    }

  }

  public static HashMap crearMapDesdeDTO(List listaDTOs, Rows rows) throws Exception {
    log.info("Ejecutando metodo [crearMapDesdeDTO]");
    HashMap propiedades = new HashMap();
    for (Object dto : listaDTOs) {
      GEParametrosNiif ParametrosNiif = (GEParametrosNiif) dto;
      propiedades.put(ParametrosNiif.getNameParametro().replaceAll(" ", ""),
          ((Textbox) rows.getFellow("idtbox" + ParametrosNiif.getNameParametro())).getValue());
    }
    String subreportDir = UtilZKProcesosComunesHelper.obtenerDirectorioSubreportes();
    propiedades.put("SUBREPORT_DIR", subreportDir);
    //jsoler para los reportes de niif no estaba tomando el logo  se mapea la ruta
//    propiedades.put("ICEBERGRS_IMAGE_DIR",  new File(ContextoAplicacion.getInstance().getRutaContexto()+"/imagenes"));
    propiedades.put("ICEBERGRS_IMAGE_DIR",  new File("C://compilados_jasper/imagenes"));
    return propiedades;
  }

  public static void crearListdesdeDTO(List result, Listbox listbox) {

    for (Object dto : result) {
      TablaDinamica datos = (TablaDinamica) dto;

      System.out.println(datos.getColumna1());
      System.out.println(datos.getColumna2());
      System.out.println(datos.getColumna3());
      System.out.println(datos.getColumna4());

    }
  }

  public Object crearDtoDesdeRow(AbstractComponent componente) {
    // TODO Auto-generated method stub
    return null;
  }

  public Row crearRowDesdeDto(Object objetoFuente) {
    // TODO Auto-generated method stub
    return null;
  }


}
