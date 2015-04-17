/*
 * Creado el 01/08/2010
 *
 */
package com.loris.domain;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;

import com.loris.dao.IVADAO;

/**
 * @author Sergio
 *
 */
@Entity
@Table(name="Pk.Cliente")
public class Cliente {
	@Id
	@Column(name="id")
	private Integer id;
	
	@Column(name="razon_social", length=64, nullable=false)
	private String razonSocial;
	
	@Column(name="email", length=64)
	private String email;
	
	@Column(name="email_2", length=64)
	private String email2;
	
	@Column(name="TELEFONO", length=15, nullable=false)
	private String telefono;
	
	@Column(name="TELEFONO_2", length=15, nullable=true)
	private String telefono2;
	
	@Column(name="TELEFONO_3", length=15, nullable=true)
	private String telefono3;
	
	@Column(name="TELEFONO_4", length=15, nullable=true)
	private String telefono4;
	
	@Column(name="CUIT", length=11)
	private String cuit;
	
	@Column(name="RESPONSABLE_INSC", length=1, nullable=false)
	private String responsableInsc;
	
	@Column(name="DESCUENTO", nullable=false)
	private BigDecimal descuento;
	
	@OneToOne(cascade={CascadeType.ALL})
	@Cascade(value={org.hibernate.annotations.CascadeType.ALL})
	@JoinColumn(name="direccion_id_fk", referencedColumnName="id")
	private Direccion direccion;
	
	/**
	 * @return Devuelve email.
	 */	
	public String getEmail() {
		return email;
	}
	/**
	 * @param email El email a establecer.
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return Devuelve razonSocial.
	 */	
	public String getRazonSocial() {
		return razonSocial;
	}
	/**
	 * @param razonSocial El razonSocial a establecer.
	 */
	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}
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
	 * @return Devuelve direccion.
	 */	
	public Direccion getDireccion() {
		return direccion;
	}
	/**
	 * @param direccion El direccion a establecer.
	 */
	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}
	
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	@Transient
	public String getClienteComboDescription(){
		return id + " - " + razonSocial;
	}
	
	public String getCuit() {
		return cuit;
	}
	public void setCuit(String cuit) {
		this.cuit = cuit;
	}
	
	public String getResponsableInsc() {
		return responsableInsc;
	}
	public void setResponsableInsc(String responsableInsc) {
		this.responsableInsc = responsableInsc;
	}
	
	public BigDecimal getDescuento() {
		return descuento;
	}
	public void setDescuento(BigDecimal descuento) {
		this.descuento = descuento;
	}
	@Transient
	public String getRegimenForPrint(){
		if(this.responsableInsc != null && responsableInsc.equals("S"))
			return "Inscripto";
		if(this.responsableInsc != null && responsableInsc.equals("N"))
			return "No Inscripto";
		
		return "";
	}
	public String getTelefono2() {
		return telefono2;
	}
	public void setTelefono2(String telefono2) {
		this.telefono2 = telefono2;
	}
	public String getTelefono3() {
		return telefono3;
	}
	public void setTelefono3(String telefono3) {
		this.telefono3 = telefono3;
	}
	public String getTelefono4() {
		return telefono4;
	}
	public void setTelefono4(String telefono4) {
		this.telefono4 = telefono4;
	}
	public String getEmail2() {
		return email2;
	}
	public void setEmail2(String email2) {
		this.email2 = email2;
	}
	
	public IVA getIva(IVADAO ivaDAO){
		IVA currentIVA = null;
		if("S".equals(this.responsableInsc)){
			currentIVA = ivaDAO.getIVAInscripto();
		}
		if("N".equals(this.responsableInsc)){
			currentIVA = ivaDAO.getIVANoInscripto();
		}
		
		return currentIVA;
	}
}