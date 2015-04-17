/*
 * Creado el 02/08/2010
 *
 */
package com.loris.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * @author Sergio
 *
 */
@Entity
@Table(name="Pk.Familia")
public class Familia{		
	public static final String FAMILIA_ACTIVE = "S";
	public static final String FAMILIA_NON_ACTIVE = "N";
	
	private Integer codigo;
	private String descripcion;
	private String activo;
	
	public Familia(){}
	
	public Familia(Integer familiaId){
		this.codigo = familiaId;
	}
	
	/**
	 * @return Devuelve codigo.
	 */
	@Id
	@Column(name="codigo_familia")
	public Integer getCodigo() {
		return codigo;
	}
	
	/**
	 * @param codigo El codigo a establecer.
	 */
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	
	/**
	 * @return Devuelve descripcion.
	 */
	@Column(name="descripcion")
	public String getDescripcion() {
		return descripcion;
	}
	
	/**
	 * @param descripcion El descripcion a establecer.
	 */
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
	public String getFamiliaComboDesc(){
		return (codigo + " - " + descripcion).toUpperCase();
	}		
}