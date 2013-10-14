package com.gaboverriding.iaopm.mb;

import static javax.faces.context.FacesContext.getCurrentInstance;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.Set;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import org.apache.log4j.Logger;

import com.gaboverriding.iaopm.dao.ProyectoDAO;
import com.gaboverriding.iaopm.dao.ProyectoDAOObjectify;
import com.gaboverriding.iaopm.model.Pais;
import com.gaboverriding.iaopm.model.Proyecto;
import com.gaboverriding.iaopm.model.ProyectoEstado;

@ManagedBean
@SessionScoped
public class ProyectoMB implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static Logger log = Logger.getLogger(ProyectoMB.class);

	private ProyectoDAO dao;

	private Proyecto proyecto;

	private String idSelecionado;
	
	private String varPruebas;

	public String getVarPruebas() {
		log.debug("Getting varPruebas:" + this.varPruebas + ":");
		return varPruebas;
	}


	public void setVarPruebas(String varPruebas) {
		log.debug("Setting varPruebas:" + varPruebas + ":");		
		this.varPruebas = varPruebas;
	}


	private Map<String, Proyecto> proyectos;

	//public void obtenNombre
	

	
	public ProyectoMB() {
		dao = new ProyectoDAOObjectify();
		this.proyecto = new Proyecto();
		this.idSelecionado = "1234567890";
		fillProyectos();
		log.debug("Constructor de ProyectoMB - this.proyecto.nombre:" + this.proyecto.getNombre());
		log.debug("Constructor de ProyectoMB - this.getIdSelecionado():" + this.getIdSelecionado());

	}

	
	public void actualizar() {
		fillProyectos();
	}	

	private void fillProyectos() {
		try {
			List<Proyecto> qryProyectos = new ArrayList<Proyecto>(dao.getAll());		
			proyectos = new HashMap<String, Proyecto>();		
			for (Proyecto a: qryProyectos) {
				proyectos.put(a.getId(), a);
			}

			log.debug("Tamaño de la Lista de Proyectos:"+proyectos.size()+":");
		} catch(Exception ex) {
			log.error("Error al cargar la lista de proyectos.", ex);
			addMessage(getMessageFromI18N("msg.erro.listar.proyecto"), ex.getMessage());
		}
	}

	public static Logger getLog() {
		return log;
	}

	public static void setLog(Logger log) {
		ProyectoMB.log = log;
	}

	public ProyectoDAO getDao() {
		return dao;
	}

	public void setDao(ProyectoDAO dao) {
		this.dao = dao;
	}

	public Proyecto getProyecto() {
		log.debug("Obteniendo referencia al objeto proyecto:" + this.proyecto + ":");
		return this.proyecto;
	}

	public void setProyecto(Proyecto proyecto) {
		this.proyecto = proyecto;
	}

	public String getIdSelecionado() {
		log.debug("El id seleccionado es:" + idSelecionado + ":");		
		return idSelecionado;
	}

	public void setIdSelecionado(String idSelecionado) {
		log.debug("Setting idSeleccionado ... :" + this.idSelecionado + ":");
		this.idSelecionado = idSelecionado;
	}

	public Map<String, Proyecto> getProyectos() {
		return proyectos;
	}

	public void setProyectos(Map<String, Proyecto> proyectos) {
		this.proyectos = proyectos;
	}

	public DataModel<Proyecto> getDataProyectos() {
		return new ListDataModel<Proyecto>(new ArrayList<Proyecto>(proyectos.values()));
	
	}
	
	public DataModel<Proyecto> getDataPruebas(){
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			/*
		log.debug("printing ... debug ...");	
		log.info("printing ... info ...");
		log.warn("printing ... warn ...");
		log.error("printing ... error ...");
		log.fatal("printing ... fatal ...");
		*/
		Date d1 = sdf.parse("21/12/2012");
		Date d2 = sdf.parse("21/12/2012");
		Date d3 = sdf.parse("29/04/1975");
		
		Proyecto dataProyectos[] = new	Proyecto[] {
				new Proyecto("PROYCO0319101611","PISCO", "PISCO Co", new Pais("Colombia"),(short)57,2,ProyectoEstado.AMARILLO, d1, d2, d3),
				new Proyecto("PROYMX1030160310","PISCO", "PISCO Mx", new Pais("México"),(short)57,0,ProyectoEstado.ROJO, d1, d2, d3)
			};
		log.debug("El numero de proyectos de prueba son:" + dataProyectos.length + ":");
		
		this.proyectos.put(dataProyectos[0].getId(), dataProyectos[0]);
		this.proyectos.put(dataProyectos[1].getId(), dataProyectos[1]);
		
		Set<Proyecto> setProyectos = new HashSet<Proyecto>();
		
		setProyectos.add(dataProyectos[0]);
		setProyectos.add(dataProyectos[1]);
		log.debug("El numero de proyectos en el Set son:" + setProyectos.size() + ":");
		
		DataModel<Proyecto> listaDataModel = new ListDataModel<Proyecto>(new ArrayList<Proyecto>(setProyectos));
				
		return listaDataModel;
		
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public String getMetodo1() {
		System.out.println("finalizando metodo 1...");
		return "algo";
	}
	

	public void reset() {
		//proyecto = null;
		//idSelecionado = null;
		log.debug("RESETEANDO LOS VALORES DE proyecto y idSeleccionado");
	}

	public void agregar(){
		proyecto = new Proyecto();
		log.debug("Proyecto a incluir");
	}

	public void editar() {
		if (idSelecionado == null) {
			log.debug("el id seleccionado resulto ser NULL");
			return;
		}
		proyecto = proyectos.get(idSelecionado);
		log.debug("Vamos a editar proyecto cuyo id es:" + idSelecionado + ":");
	}	


	public String guardar() {
		try {
			dao.save(proyecto);
			proyectos.put(proyecto.getId(), proyecto);
		} catch(Exception ex) {
			log.error("Error al guardar proyecto.", ex);
			addMessage(getMessageFromI18N("msg.error.guardar.proyecto"), ex.getMessage());
			return "";
		}
		log.debug("Proyecto guardado con id  "+proyecto.getId());
		return "listaProyectos";
	}

	public String guardarPruebasLocales() {
		try {
			//dao.save(proyecto); // SE COMENTA PORQUE NO SE ESTA TRABAJANDO CON LA BD DE GAE
			log.debug("El Proyecto que se pretende guardar es:" + proyecto.getId() + ":" + proyecto.getNombre() + ":");
			proyectos.put(proyecto.getId(), proyecto);
			log.debug("Ahora el contenido de la hashmap es:" + proyectos + ":");
		} catch(Exception ex) {
			log.error("Error al guardar proyecto.", ex);
			addMessage(getMessageFromI18N("msg.error.guardar.proyecto"), ex.getMessage());
			return "";
		}
		log.debug("Proyecto guardado con id  "+proyecto.getId());
		return "listaProyectos";
	}

	
	
	
	public String eliminar() {
		try {
			dao.remove(proyecto);
			proyectos.remove(proyecto.getId());
		} catch(Exception ex) {
			log.error("Error al eliminar proyecto.", ex);
			addMessage(getMessageFromI18N("msg.error.eliminar.proyecto"), ex.getMessage());
			return "";
		}
		log.debug("Eliminar proyecto "+proyecto.getId());
		return "listaProyectos";
	}


	private String getMessageFromI18N(String key) {
		ResourceBundle bundle = ResourceBundle.getBundle("messages_labels", getCurrentInstance().getViewRoot().getLocale());
		return bundle.getString(key);
	}


	private void addMessage(String summary, String detail) {
		getCurrentInstance().addMessage(null, new FacesMessage(summary, summary.concat("<br/>").concat(detail)));
	}

}
