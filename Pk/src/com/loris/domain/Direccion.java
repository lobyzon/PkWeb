/*
 * Creado el 01/08/2010
 *
 */
package com.loris.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author Sergio
 *
 */
@Entity
@Table(name="Pk.Direccion")
public class Direccion {
	private Integer id;
	private String calle;
	private String numero;
	private String piso;
	private String depto;
	private String codPostal;
	private String localidad;	
	private Provincia provincia;
	
	
	/**
	 * @return Devuelve calle.
	 */
	@Column(name="calle", length = 64)
	public String getCalle() {
		return calle;
	}
	/**
	 * @param calle El calle a establecer.
	 */
	public void setCalle(String calle) {
		this.calle = calle;
	}
	/**
	 * @return Devuelve codPostal.
	 */
	@Column(name="codigo_postal", length = 15)
	public String getCodPostal() {
		return codPostal;
	}
	/**
	 * @param codPostal El codPostal a establecer.
	 */
	public void setCodPostal(String codPostal) {
		this.codPostal = codPostal;
	}
	/**
	 * @return Devuelve depto.
	 */
	@Column(name="depto", length = 3)
	public String getDepto() {
		return depto;
	}
	/**
	 * @param depto El depto a establecer.
	 */
	public void setDepto(String depto) {
		this.depto = depto;
	}
	/**
	 * @return Devuelve id.
	 */
	@Id
	@GeneratedValue
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
	/**
	 * @return Devuelve localidad.
	 */
	@Column(name="localidad", length = 64)
	public String getLocalidad() {
		return localidad;
	}
	/**
	 * @param localidad El localidad a establecer.
	 */
	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}
	/**
	 * @return Devuelve numero.
	 */
	@Column(name="numero", length = 5)
	public String getNumero() {
		return numero;
	}
	/**
	 * @param numero El numero a establecer.
	 */
	public void setNumero(String numero) {
		this.numero = numero;
	}
	/**
	 * @return Devuelve piso.
	 */
	@Column(name="piso", length = 10)
	public String getPiso() {
		return piso;
	}
	/**
	 * @param piso El piso a establecer.
	 */
	public void setPiso(String piso) {
		this.piso = piso;
	}
	/**
	 * @return Devuelve provincia.
	 */
	@OneToOne	
	@JoinColumn(name="provincia_id_fk", referencedColumnName="id")	
	public Provincia getProvincia() {
		return provincia;
	}
	/**
	 * @param provincia El provincia a establecer.
	 */
	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}
}