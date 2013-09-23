package com.gaboverriding.iaopm.model;

import java.util.Date;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

import java.io.Serializable;

@Entity
public class Proyecto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4957310538924882064L;
	@Id
	private Long id;
	private String nombre;
	private String descripcion;
	private Pais pais;
	private short avance;
	private int replanificaciones;
	private ProyectoEstado estado;
	private Date dia_inicio;
	private Date dia_fin;
	private Date dia_d;
	/*SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	Date d = sdf.parse("21/12/2012");*/
	
	public Proyecto (long id, String nombre, String descripcion, Pais pais, short avance, int replanificaciones, ProyectoEstado estado, Date d_ini, Date d_fin, Date d_d) {
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.pais = pais;
		this.avance = avance;
		this.replanificaciones = replanificaciones;
		this.estado = estado;
		this.dia_inicio = d_ini;
		this.dia_fin = d_fin;
		this.dia_d = d_d;
	}	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}

	public short getAvance() {
		return avance;
	}

	public void setAvance(short avance) {
		this.avance = avance;
	}

	public int getReplanificaciones() {
		return replanificaciones;
	}

	public void setReplanificaciones(int replanificaciones) {
		this.replanificaciones = replanificaciones;
	}

	public ProyectoEstado getEstado() {
		return estado;
	}

	public void setEstado(ProyectoEstado estado) {
		this.estado = estado;
	}

	public Date getDia_inicio() {
		return dia_inicio;
	}

	public void setDia_inicio(Date dia_inicio) {
		this.dia_inicio = dia_inicio;
	}

	public Date getDia_fin() {
		return dia_fin;
	}

	public void setDia_fin(Date dia_fin) {
		this.dia_fin = dia_fin;
	}

	public Date getDia_d() {
		return dia_d;
	}

	public void setDia_d(Date dia_d) {
		this.dia_d = dia_d;
	}
		
}
