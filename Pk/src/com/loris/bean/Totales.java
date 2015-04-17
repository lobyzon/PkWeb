package com.loris.bean;

import java.math.BigDecimal;

public class Totales {
	private BigDecimal subTotal;
	private BigDecimal descuentoTotal;	
	private BigDecimal subTotalDescontado;
	private BigDecimal iva;
	private BigDecimal total;
	
	public BigDecimal getSubTotal() {
		return subTotal;
	}
	public void setSubTotal(BigDecimal subTotal) {
		this.subTotal = subTotal;
	}
	public BigDecimal getDescuentoTotal() {
		return descuentoTotal;
	}
	public void setDescuentoTotal(BigDecimal descuentoTotal) {
		this.descuentoTotal = descuentoTotal;
	}
	public BigDecimal getSubTotalDescontado() {
		return subTotalDescontado;
	}
	public void setSubTotalDescontado(BigDecimal subTotalDescontado) {
		this.subTotalDescontado = subTotalDescontado;
	}
	public BigDecimal getIva() {
		if(this.iva == null)
			return new BigDecimal(0);
		return iva;
	}
	public void setIva(BigDecimal iva) {
		this.iva = iva;
	}
	public BigDecimal getTotal() {
		return total;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
	}		
}