package com.gaboverriding.iaopm.mb;

import static javax.faces.context.FacesContext.getCurrentInstance;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import org.apache.log4j.Logger;

import com.gaboverriding.iaopm.dao.ArticuloDAO;
import com.gaboverriding.iaopm.dao.ArticuloDAOObjectify;
import com.gaboverriding.iaopm.model.Articulo;

@ManagedBean
@SessionScoped
public class ArticuloMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8149277260790273226L;


	private static Logger log = Logger.getLogger(ArticuloMB.class);

	private ArticuloDAO dao;

	private Articulo articulo;

	private Long idSelecionado;


	private Map<Long, Articulo> articulos;

	public ArticuloMB() {
		dao = new ArticuloDAOObjectify();
		fillArticulos();
	}



	public void actualizar() {
		fillArticulos();
	}	

	private void fillArticulos() {
		try {
			List<Articulo> qryArticulos = new ArrayList<Articulo>(dao.getAll());
			articulos = new HashMap<Long, Articulo>();
			for (Articulo a: qryArticulos) {
				articulos.put(a.getId(), a);
			}

			log.debug("Tamaño de la Lista de Articulos ("+articulos.size()+")");
		} catch(Exception ex) {
			log.error("Error al cargar la lista de articulos.", ex);
			addMessage(getMessageFromI18N("msg.erro.listar.articulo"), ex.getMessage());
		}
	}

	public static Logger getLog() {
		return log;
	}

	public static void setLog(Logger log) {
		ArticuloMB.log = log;
	}

	public ArticuloDAO getDao() {
		return dao;
	}

	public void setDao(ArticuloDAO dao) {
		this.dao = dao;
	}

	public Articulo getArticulo() {
		return articulo;
	}

	public void setArticulo(Articulo articulo) {
		this.articulo = articulo;
	}

	public Long getIdSelecionado() {
		return idSelecionado;
	}

	public void setIdSelecionado(Long idSelecionado) {
		this.idSelecionado = idSelecionado;
	}

	public Map<Long, Articulo> getArticulos() {
		return articulos;
	}

	public void setArticulos(Map<Long, Articulo> articulos) {
		this.articulos = articulos;
	}


	public DataModel<Articulo> getDataArticulos() {
		return new ListDataModel<Articulo>(new ArrayList<Articulo>(articulos.values()));
	}

	public void reset() {
		articulo = null;
		idSelecionado = null;
	}

	public void agregar(){
		articulo = new Articulo();
		log.debug("Articulo a incluir");
	}

	public void editar() {
		if (idSelecionado == null) {
			return;
		}
		articulo = articulos.get(idSelecionado);
		log.debug("Vamos a editar articulo");
	}	


	public String guardar() {
		try {
			dao.save(articulo);
			articulos.put(articulo.getId(), articulo);
		} catch(Exception ex) {
			log.error("Error al guardar articulo.", ex);
			addMessage(getMessageFromI18N("msg.error.guardar.articulo"), ex.getMessage());
			return "";
		}
		log.debug("Articulo guardado con id  "+articulo.getId());
		return "listaArticulos";
	}


	public String eliminar() {
		try {
			dao.remove(articulo);
			articulos.remove(articulo.getId());
		} catch(Exception ex) {
			log.error("Error al eliminar articulo.", ex);
			addMessage(getMessageFromI18N("msg.error.eliminar.articulo"), ex.getMessage());
			return "";
		}
		log.debug("Eliminar articulo "+articulo.getId());
		return "listaArticulos";
	}


	private String getMessageFromI18N(String key) {
		ResourceBundle bundle = ResourceBundle.getBundle("messages_labels", getCurrentInstance().getViewRoot().getLocale());
		return bundle.getString(key);
	}


	private void addMessage(String summary, String detail) {
		getCurrentInstance().addMessage(null, new FacesMessage(summary, summary.concat("<br/>").concat(detail)));
	}

}