/*
 * Creado el 02/08/2010
 *
 */
package com.loris.domain;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.Cascade;

/**
 * @author Sergio
 * 
 */
@Entity
@Table(name = "PK.ITEM_FACTURA")
public class ItemFactura implements Comparable<ItemFactura> {
	@Id
	@Column(name = "ID")
	@GeneratedValue
	private Integer id;

	@OneToOne(cascade = { CascadeType.ALL })
	@Cascade(value = { org.hibernate.annotations.CascadeType.ALL })
	@JoinColumn(name = "ARTICULO_ID_FK", referencedColumnName = "ID")
	private Articulo articulo;

	@Column(name = "CANTIDAD", nullable = false)
	private BigDecimal cantidad;

	@Column(name = "DESCUENTO", nullable = false)
	private BigDecimal descuento;

	@Column(name = "PRECIO", nullable = false)
	private BigDecimal precio;	

	/**
	 * @return Devuelve articulo.
	 */
	public Articulo getArticulo() {
		return articulo;
	}

	/**
	 * @param articulo
	 *            El articulo a establecer.
	 */
	public void setArticulo(Articulo articulo) {
		this.articulo = articulo;
	}

	/**
	 * @return Devuelve cantidad.
	 */
	public BigDecimal getCantidad() {
		return cantidad;
	}

	/**
	 * @param cantidad
	 *            El cantidad a establecer.
	 */
	public void setCantidad(BigDecimal cantidad) {
		this.cantidad = cantidad;
	}

	/**
	 * @return Devuelve id.
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 *            El id a establecer.
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	public BigDecimal getDescuento() {
		return descuento;
	}

	public void setDescuento(BigDecimal descuento) {
		this.descuento = descuento;
	}

	public BigDecimal getPrecio() {
		return precio;
	}

	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}
	
	@Transient
	public BigDecimal getTotalItem() {
		if (this.precio != null && this.cantidad != null)
			return this.precio.multiply(this.cantidad);

		return null;
	}

	public int compareTo(ItemFactura item) {
		int familiaCompare = this.articulo.getFamilia().getCodigo().compareTo(item.getArticulo().getFamilia().getCodigo()); 
		
		if(familiaCompare == 0){
			int marcaCompare = this.articulo.getMarca().getId().compareTo(item.getArticulo().getMarca().getId()); 
			if(marcaCompare == 0){
				return this.articulo.getCodigo().compareTo(item.getArticulo().getCodigo());
			}else {
				return marcaCompare;
			}
		}else {
			return familiaCompare;
		} 
	}
	
	@Transient
	public String getArticuloConsulta(){
		if(this.articulo != null && this.articulo.getDescripcion() != null)
			return 	this.articulo.getDescripcion().substring(0,1) 
				+ this.articulo.getDescripcion().substring(1).toLowerCase();
		return "";
	}
	
	@Transient
	public String getMarcaConsulta(){
		if(this.articulo != null && this.articulo.getMarca() != null && this.articulo.getMarca().getDescripcion() != null){
			String firstLetterMarca = this.articulo.getMarca().getDescripcion().substring(0,1);
			String descripcionMarca = this.articulo.getMarca().getDescripcion().substring(1).toLowerCase();
			
			if("\"".equals(firstLetterMarca)){
				firstLetterMarca = this.articulo.getMarca().getDescripcion().substring(0,2);
				descripcionMarca = this.articulo.getMarca().getDescripcion().substring(2).toLowerCase();
			}
			return	firstLetterMarca + descripcionMarca;
		}
		return "";
	}
	
	@Transient
	public String getFamiliaConsulta(){
		if(this.articulo != null && this.articulo.getFamilia() != null && this.articulo.getFamilia().getDescripcion() != null)
			return  this.articulo.getFamilia().getDescripcion().substring(0,1)
				+ this.articulo.getFamilia().getDescripcion().substring(1).toLowerCase();
		return "";
	}
}