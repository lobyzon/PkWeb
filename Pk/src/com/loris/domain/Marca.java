package com.loris.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="Pk.Marca")
public class Marca {
	public static final String MARCA_ACTIVA 	= "S";
	public static final String MARCA_NO_ACTIVA = "N";
	
	private Integer id;
	private String descripcion;
	private String activo;
	
	public Marca(){	}
	
	public Marca(Integer id){
		this.id = id;
	}
	
	@Id
	@Column(name="id_marca")
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}	
	
	@Column(name="descripcion")
	public String getDescripcion() {
		return descripcion;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
		
	@Column(name="ACTIVO", nullable=false, length=1)
	public String getActivo() {
		return activo;
	}

	public void setActivo(String activo) {
		this.activo = activo;
	}

	@Transient
	public String getMarcaComboDesc(){
		return (id + " - " + descripcion).toUpperCase();
	}	
}