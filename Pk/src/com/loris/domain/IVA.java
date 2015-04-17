package com.loris.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Pk.IVA")
public class IVA {
	@Id
	@Column(name="ID")
	private Integer id;
	
	@Column(name="IVA", nullable=false)
	private BigDecimal IVA;
	
	@Column(name="INSCRIPTO", nullable=false)
	private Boolean inscripto;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public BigDecimal getIVA() {
		return IVA;
	}
	public void setIVA(BigDecimal iVA) {
		IVA = iVA;
	}
	public Boolean getInscripto() {
		return inscripto;
	}
	public void setInscripto(Boolean inscripto) {
		this.inscripto = inscripto;
	}	
}