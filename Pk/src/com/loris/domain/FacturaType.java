package com.loris.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="PK.Factura_Type")
@Entity
public class FacturaType {
	public final static Integer FACTURA_TYPE 			= new Integer(1);
	public final static Integer FACTURA_N_TYPE 			= new Integer(2);	
	public final static Integer FACTURA_NC_TYPE 		= new Integer(3);
	public final static Integer FACTURA_ANULADA_TYPE 	= new Integer(4);
	
	public FacturaType(){}
	
	public FacturaType(Integer facturaType){
		this.facturaTypeId = facturaType;
	}
	
	@Id
	@Column(name="FACTURA_TYPE_ID")
	private Integer facturaTypeId;
	
	@Column(name="DESCRIPTION", nullable=false, length=16)
	private String description;

	
	public Integer getFacturaTypeId() {
		return facturaTypeId;
	}

	public void setFacturaTypeId(Integer facturaTypeId) {
		this.facturaTypeId = facturaTypeId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}	
}