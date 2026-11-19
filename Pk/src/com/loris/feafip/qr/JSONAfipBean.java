package com.loris.feafip.qr;

import java.math.BigDecimal;
import java.math.BigInteger;

public class JSONAfipBean {
	private int ver;
	private String fecha;
	private BigInteger cuit;
	private int ptoVta;
	private int tipoCmp;
	private int nroCmp;
	private BigDecimal importe;
	private String moneda;
	private BigDecimal ctz;
	private int tipoDocRec;
	private BigInteger nroDocRec;
	private String tipoCodAut;
	private BigInteger codAut;
	
	public int getVer() {
		return ver;
	}
	public void setVer(int ver) {
		this.ver = ver;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public BigInteger getCuit() {
		return cuit;
	}
	public void setCuit(BigInteger cuit) {
		this.cuit = cuit;
	}
	public int getPtoVta() {
		return ptoVta;
	}
	public void setPtoVta(int ptoVta) {
		this.ptoVta = ptoVta;
	}
	public int getTipoCmp() {
		return tipoCmp;
	}
	public void setTipoCmp(int tipoCmp) {
		this.tipoCmp = tipoCmp;
	}
	public int getNroCmp() {
		return nroCmp;
	}
	public void setNroCmp(int nroCmp) {
		this.nroCmp = nroCmp;
	}
	public BigDecimal getImporte() {
		return importe;
	}
	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}
	public String getMoneda() {
		return moneda;
	}
	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}
	public BigDecimal getCtz() {
		return ctz;
	}
	public void setCtz(BigDecimal ctz) {
		this.ctz = ctz;
	}
	public int getTipoDocRec() {
		return tipoDocRec;
	}
	public void setTipoDocRec(int tipoDocRec) {
		this.tipoDocRec = tipoDocRec;
	}
	public BigInteger getNroDocRec() {
		return nroDocRec;
	}
	public void setNroDocRec(BigInteger nroDocRec) {
		this.nroDocRec = nroDocRec;
	}
	public String getTipoCodAut() {
		return tipoCodAut;
	}
	public void setTipoCodAut(String tipoCodAut) {
		this.tipoCodAut = tipoCodAut;
	}
	public BigInteger getCodAut() {
		return codAut;
	}
	public void setCodAut(BigInteger codAut) {
		this.codAut = codAut;
	}	
}