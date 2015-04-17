/*
 * Creado el 02/08/2010
 *
 */
package com.loris.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * @author Sergio
 *
 */
@Entity
@Table(name="Pk.Articulo")
public class Articulo {
	public static final String ACTIVE_MARK_YES 	= "S";
	public static final String ACTIVE_MARK_NO 	= "N";
	
	private Integer id;
	private String codigo;
	private String descripcion;	
	private Familia familia;
	private Marca marca;
	private Integer stockMinimo;
	private BigDecimal stock;
	private BigDecimal precioCosto;
	private BigDecimal precioVenta;
	private Date fechaModificacion;
	private Date fechaModifVenta;
	private String activo;
	
	//Transients
	private Integer familiaId;
	private Integer familiaIdSearch;
	private Integer marcaId;	
	private BigDecimal porcentajeModif;	
	
		
	@Column(name="fecha_modif_venta")
	public Date getFechaModifVenta() {
		return fechaModifVenta;
	}

	public void setFechaModifVenta(Date fechaModifVenta) {
		this.fechaModifVenta = fechaModifVenta;
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
	
	/**
	 * @return Devuelve familia.
	 */	
	@OneToOne
	@JoinColumn(name="familia_id_fk", referencedColumnName="codigo_familia")
	public Familia getFamilia() {
		return familia;
	}
	
	/**
	 * @param familia El familia a establecer.
	 */
	public void setFamilia(Familia familia) {
		this.familia = familia;
	}	
	
	@Column(name="codigo")
	public String getCodigo() {
		return codigo;
	}
	
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	@OneToOne	
	@JoinColumn(name="marca_id_fk", referencedColumnName="id_marca")
	public Marca getMarca() {
		return marca;
	}
	
	public void setMarca(Marca marca) {
		this.marca = marca;
	}
	
	@Id
	@GeneratedValue
	@Column(name="id")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name="stock_min")
	public Integer getStockMinimo() {
		return stockMinimo;
	}
	
	public void setStockMinimo(Integer stockMinimo) {
		this.stockMinimo = stockMinimo;
	}
	
	@Column(name="stock")
	public BigDecimal getStock() {
		return stock;
	}
	
	public void setStock(BigDecimal stock) {
		this.stock = stock;
	}
	
	@Column(name="precio_costo")
	public BigDecimal getPrecioCosto() {
		return precioCosto;
	}
	
	public void setPrecioCosto(BigDecimal precioCosto) {
		this.precioCosto = precioCosto;
	}
	
	@Column(name="precio_venta")
	public BigDecimal getPrecioVenta() {
		return precioVenta;
	}
	
	public void setPrecioVenta(BigDecimal precioVenta) {
		this.precioVenta = precioVenta;
	}
	
	@Column(name="fecha_modif")
	public Date getFechaModificacion() {
		return fechaModificacion;
	}
	
	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}
	
	@Column(name="activo", nullable=false, length=1)
	public String getActivo() {
		return activo;
	}

	public void setActivo(String activo) {
		this.activo = activo;
	}
	
	@Transient
	public Integer getFamiliaId() {
		return familiaId;
	}
	
	public void setFamiliaId(Integer familiaId) {
		this.familiaId = familiaId;
	}
	
	@Transient
	public Integer getMarcaId() {
		return marcaId;
	}
	
	public void setMarcaId(Integer marcaId) {
		this.marcaId = marcaId;
	}
	
	@Transient
	public Integer getFamiliaIdSearch() {
		return familiaIdSearch;
	}
	
	public void setFamiliaIdSearch(Integer familiaIdSearch) {
		this.familiaIdSearch = familiaIdSearch;
	}
	
	@Transient
	public Integer getFamiliaIdForSearch(){
		if(this.familiaId != null)
			return this.familiaId;
		if(this.familiaIdSearch != null)
			return this.familiaIdSearch;
		
		return null;
	}
	
	@Transient
	public String getArticuloComboDesc(){
		return (this.codigo + " - " + this.descripcion).toUpperCase();
	}
	
	@Transient
	public BigDecimal getPorcentajeModif() {
		return porcentajeModif;
	}
	
	public void setPorcentajeModif(BigDecimal porcentajeModif) {
		this.porcentajeModif = porcentajeModif;
	}			
}