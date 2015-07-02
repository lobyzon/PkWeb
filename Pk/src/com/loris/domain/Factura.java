/*
 * Creado el 01/08/2010
 *
 */
package com.loris.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;

/**
 * @author Sergio
 *
 */
@Entity
@Table(name="Pk.Factura")
public class Factura {	
	public static final String TIPO_FACTURA_MANUAL = "fm";
	public static final String TIPO_FACTURA_ELECTRONICA = "fe";
	
	@Id
	@Column(name="ID")
	@GeneratedValue
	private Integer id;
	
	@Column(name="NRO_FACTURA", nullable=false)
	private Integer nroFactura;
		 
	@OneToMany(cascade={CascadeType.ALL}, fetch=FetchType.EAGER)
	@Cascade(value={org.hibernate.annotations.CascadeType.ALL})
	@JoinColumn(name="FACTURA_ID_FK")
	private List<ItemFactura> items;
	
	@Column(name="SUB_TOTAL", nullable=false)
	private BigDecimal subTotal;
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="CLIENTE_ID_FK", referencedColumnName="ID")
	private Cliente cliente;
	
	@Column(name="FECHA", nullable=false)
	private Date fecha;
	
	@ManyToOne(fetch=FetchType.EAGER)	
	@JoinColumn(name="FACTURA_TYPE_FK", referencedColumnName="FACTURA_TYPE_ID")
	private FacturaType facturaType;
	
	@Column(name="COMENTARIOS", length=372)
	private String comentarios;
	
	@Column(name="CAE", nullable=true)
	private String CAE;
	
	@Column(name="FECHA_VTO_CAE", nullable=true)
	private Date fechaVtoCAE;
		
	
	/**
	 * @return Devuelve id.
	 */
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
	 * @return Devuelve cliente.
	 */
	public Cliente getCliente() {
		return cliente;
	}
	
	/**
	 * @param cliente El cliente a establecer.
	 */
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	/**
	 * @return Devuelve fecha.
	 */
	public Date getFecha() {
		return fecha;
	}
	
	/**
	 * @param fecha El fecha a establecer.
	 */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	/**
	 * @return Devuelve nroFactura.
	 */
	public Integer getNroFactura() {
		return nroFactura;
	}
	
	/**
	 * @param nroFactura El nroFactura a establecer.
	 */
	public void setNroFactura(Integer nroFactura) {
		this.nroFactura = nroFactura;
	}
	
	/**
	 * @return Devuelve subTotal.
	 */
	public BigDecimal getSubTotal() {
		return subTotal;
	}
	
	/**
	 * @param subTotal El subTotal a establecer.
	 */	
	public void setSubTotal(BigDecimal subTotal) {
		this.subTotal = subTotal;
	}
	
	
	public List<ItemFactura> getItems() {
		return items;
	}

	public void setItems(List<ItemFactura> items) {
		this.items = items;
	}

	@Transient public BigDecimal getSubTotalDescontado(BigDecimal subTotal, BigDecimal totalDescuento){
		if(subTotal != null && totalDescuento != null)
			return subTotal.subtract(totalDescuento);
		return null;
	}
	
	public String getComentarios() {
		return comentarios;
	}
	
	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}		
	
	@Transient
	public void updateStock(Integer facturaType){
		for (ItemFactura item : this.items) {
			if(FacturaType.FACTURA_NC_TYPE.equals(facturaType) || FacturaType.FACTURA_ANULADA_TYPE.equals(facturaType)
					|| FacturaType.FACTURA_NC_TYPE_ELECTRONIC.equals(facturaType)){
				item.getArticulo().setStock(item.getCantidad().add(item.getArticulo().getStock()));
			}else{
				if(item.getCantidad().compareTo(item.getArticulo().getStock()) == 0 ||
						item.getCantidad().compareTo(item.getArticulo().getStock()) == 1){
					item.getArticulo().setStock(new BigDecimal(0));
				}else{
					item.getArticulo().setStock(item.getArticulo().getStock().subtract(item.getCantidad()));
				}
			}
				
		}
	}	
	
	public FacturaType getFacturaType() {
		return facturaType;
	}
	
	public void setFacturaType(FacturaType facturaType) {
		this.facturaType = facturaType;
	}
	
	public String getCAE() {
		return CAE;
	}

	public void setCAE(String cAE) {
		CAE = cAE;
	}

	public Date getFechaVtoCAE() {
		return fechaVtoCAE;
	}

	public void setFechaVtoCAE(Date fechaVtoCAE) {
		this.fechaVtoCAE = fechaVtoCAE;
	}

	@Transient
	public static void updateFacturaType(Factura factura){
		if(factura.getFacturaType() == null || factura.getFacturaType().getFacturaTypeId() == null)
			factura.setFacturaType(new FacturaType(FacturaType.FACTURA_TYPE));
	}
	
	@Transient
	public Boolean getIsFacturaN(){
		return FacturaType.FACTURA_N_TYPE.equals(this.facturaType.getFacturaTypeId());
	}
	
	@Transient
	public Boolean getIsFacturaElectronica(){
		return FacturaType.FACTURA_TYPE_ELECTRONIC.equals(this.facturaType.getFacturaTypeId());
	}
	
	@Transient
	public Boolean getIsNotaDeCreditoElectronica(){
		return FacturaType.FACTURA_NC_TYPE_ELECTRONIC.equals(this.facturaType.getFacturaTypeId());
	}
}