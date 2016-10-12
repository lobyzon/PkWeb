package com.loris.domain;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;

@Entity
@Table(name="Pk.Params")
public class Params {
	@Id
	@Column(name="PARAMS_ID")
	private Integer id;
	
	@Column(name="COTIZACION_DOLAR", nullable=false)
	private BigDecimal cotizacionDolar;
	
	@Column(name="PROX_NUM_FACTURA", nullable=false)
	private Integer proxNumFactura;
	
	@Column(name="CANT_COPIAS_FACTURA", nullable=false)
	private Integer cantCopiasFactura;
	
	@Column(name="PROX_NUM_REMITO", nullable=false)
	private Integer proxNumRemito;
	
	@Column(name="CANT_COPIAS_REMITO", nullable=false)
	private Integer cantCopiasRemito;
	
	/**
	 * @param Integer Próximo Número de Nota de Crédito
	 */
	@Column(name="PROX_NUM_NC", nullable=false)
	private Integer proxNumNC;
	
	/**
	 * @param Integer Cantidad de copias Nota de Crédito
	 */
	@Column(name="CANT_COPIAS_NC", nullable=false)
	private Integer cantCopiasNC;
	
	@OneToOne(cascade={CascadeType.ALL})
	@Cascade(value={org.hibernate.annotations.CascadeType.ALL})
	@JoinColumn(name="IVA_INSCRIPTO_FK", referencedColumnName="ID")
	private IVA ivaInscripto;
	
	@OneToOne(cascade={CascadeType.ALL})
	@Cascade(value={org.hibernate.annotations.CascadeType.ALL})
	@JoinColumn(name="IVA_NO_INSCRIPTO_FK", referencedColumnName="ID")
	private IVA ivaNoInscripto;
	
	@Column(name="PROX_NUM_FACTURA_N", nullable=false)
	private Integer proxNumFacturaN;
	
	@Column(name="CANT_COPIAS_FACTURA_N", nullable=false)
	private Integer cantCopiasFacturaN;
	
	@Column(name="PROX_NUM_FACTURA_D", nullable=false)
	private Integer proxNumFacturaD;
	
	@Column(name="CANT_COPIAS_FACTURA_D", nullable=false)
	private Integer cantCopiasFacturaD;
	
	@Column(name="PROX_NUM_REMITO_D", nullable=false)
	private Integer proxNumRemitoD;
	
	@Column(name="CANT_COPIAS_REMITO_D", nullable=false)
	private Integer cantCopiasRemitoD;
	
	@Column(name="PROX_NUM_FACTURA_ELECTRONICA", nullable=false)
	private Integer proxNumFacturaElectronica;
	
	@Column(name="PROX_NUM_NC_D_ELECTRONICA", nullable=false)
	private Integer proxNumNCDElectronica;
	
	/**
	 * @param Integer Próximo Número de Nota de Crédito
	 */
	@Column(name="PROX_NUM_NC_D", nullable=false)
	private Integer proxNumNCD;
	
	/**
	 * @param Integer Cantidad de copias Nota de Crédito
	 */
	@Column(name="CANT_COPIAS_NC_D", nullable=false)
	private Integer cantCopiasNCD;
	
	@Column(name="MATRIX_PRINTER_NAME", nullable=false, length=30)
	private String matrixPrinterName;
	
	@Column(name="LASER_PRINTER_NAME", nullable=false, length=30)
	private String laserPrinterName;
	
	@Column(name="SIGN")
	private String sign;
	
	@Column(name="TOKEN")
	private String token;
	
	@Column(name="GENERATION_TIME_TA")
	private String generationTimeTA;
	
	@Column(name="EXPIRATION_TIME_TA")
	private String expirationTimeTA;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BigDecimal getCotizacionDolar() {
		return cotizacionDolar;
	}

	public void setCotizacionDolar(BigDecimal cotizacionDolar) {
		this.cotizacionDolar = cotizacionDolar;
	}

	public Integer getProxNumFactura() {		
		return proxNumFactura;
	}

	public void setProxNumFactura(Integer proxNumFactura) {
		this.proxNumFactura = proxNumFactura;
	}

	public Integer getCantCopiasFactura() {
		return cantCopiasFactura;
	}

	public void setCantCopiasFactura(Integer cantCopiasFactura) {
		this.cantCopiasFactura = cantCopiasFactura;
	}

	public Integer getProxNumRemito() {		
		return proxNumRemito;
	}

	public void setProxNumRemito(Integer proxNumRemito) {
		this.proxNumRemito = proxNumRemito;
	}

	public Integer getCantCopiasRemito() {
		return cantCopiasRemito;
	}

	public void setCantCopiasRemito(Integer cantCopiasRemito) {
		this.cantCopiasRemito = cantCopiasRemito;
	}

	public Integer getProxNumNC() {		
		return proxNumNC;
	}

	public void setProxNumNC(Integer proxNumNC) {
		this.proxNumNC = proxNumNC;
	}

	public Integer getCantCopiasNC() {
		return cantCopiasNC;
	}

	public void setCantCopiasNC(Integer cantCopiasNC) {
		this.cantCopiasNC = cantCopiasNC;
	}

	public IVA getIvaInscripto() {
		return ivaInscripto;
	}

	public void setIvaInscripto(IVA ivaInscripto) {
		this.ivaInscripto = ivaInscripto;
	}

	public IVA getIvaNoInscripto() {
		return ivaNoInscripto;
	}

	public void setIvaNoInscripto(IVA ivaNoInscripto) {
		this.ivaNoInscripto = ivaNoInscripto;
	}

	public Integer getProxNumFacturaN() {		
		return proxNumFacturaN;
	}

	public void setProxNumFacturaN(Integer proxNumFacturaN) {
		this.proxNumFacturaN = proxNumFacturaN;
	}

	public Integer getCantCopiasFacturaN() {
		return cantCopiasFacturaN;
	}

	public void setCantCopiasFacturaN(Integer cantCopiasFacturaN) {
		this.cantCopiasFacturaN = cantCopiasFacturaN;
	}

	public Integer getProxNumFacturaD() {		
		return proxNumFacturaD;
	}

	public void setProxNumFacturaD(Integer proxNumFacturaD) {
		this.proxNumFacturaD = proxNumFacturaD;
	}

	public Integer getCantCopiasFacturaD() {
		return cantCopiasFacturaD;
	}

	public void setCantCopiasFacturaD(Integer cantCopiasFacturaD) {
		this.cantCopiasFacturaD = cantCopiasFacturaD;
	}

	public Integer getProxNumRemitoD() {		
		return proxNumRemitoD;
	}

	public void setProxNumRemitoD(Integer proxNumRemitoD) {
		this.proxNumRemitoD = proxNumRemitoD;
	}

	public Integer getCantCopiasRemitoD() {
		return cantCopiasRemitoD;
	}

	public void setCantCopiasRemitoD(Integer cantCopiasRemitoD) {
		this.cantCopiasRemitoD = cantCopiasRemitoD;
	}

	public Integer getProxNumNCD() {		
		return proxNumNCD;
	}

	public void setProxNumNCD(Integer proxNumNCD) {
		this.proxNumNCD = proxNumNCD;
	}

	public Integer getCantCopiasNCD() {
		return cantCopiasNCD;
	}

	public void setCantCopiasNCD(Integer cantCopiasNCD) {
		this.cantCopiasNCD = cantCopiasNCD;
	}

	public String getMatrixPrinterName() {
		return matrixPrinterName;
	}

	public void setMatrixPrinterName(String matrixPrinterName) {
		this.matrixPrinterName = matrixPrinterName;
	}

	public String getLaserPrinterName() {
		return laserPrinterName;
	}

	public void setLaserPrinterName(String laserPrinterName) {
		this.laserPrinterName = laserPrinterName;
	}

	public Integer getProxNumFacturaElectronica() {
		return proxNumFacturaElectronica;
	}

	public void setProxNumFacturaElectronica(Integer proxNumFacturaElectronica) {
		this.proxNumFacturaElectronica = proxNumFacturaElectronica;
	}

	public Integer getProxNumNCDElectronica() {
		return proxNumNCDElectronica;
	}

	public void setProxNumNCDElectronica(Integer proxNumNCDElectronica) {
		this.proxNumNCDElectronica = proxNumNCDElectronica;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getGenerationTimeTA() {
		return generationTimeTA;
	}

	public void setGenerationTimeTA(String generationTimeTA) {
		this.generationTimeTA = generationTimeTA;
	}

	public String getExpirationTimeTA() {
		return expirationTimeTA;
	}

	public void setExpirationTimeTA(String expirationTimeTA) {
		this.expirationTimeTA = expirationTimeTA;
	}	
}