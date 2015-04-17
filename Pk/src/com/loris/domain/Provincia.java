/*
 * Creado el 01/08/2010
 *
 */
package com.loris.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Sergio
 *
 */
@Entity
@Table(name="Pk.Provincia")
public class Provincia {
	private Integer id;
	private String descripcion;
	
	/**
	 * @return Devuelve descripcion.
	 */
	@Column(name="descripcion", length = 64)
	public String getDescripcion() {
		return descripcion;
	}
	/**
	 * @param descripcion El descripcion a establecer.
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	/**
	 * @return Devuelve id.
	 */
	@Id
	@Column(name="id")
	public Integer getId() {
		return id;
	}
	/**
	 * @param id El id a establecer.
	 */
	public void setId(Integer id) {
		this.id = id;
	}
}