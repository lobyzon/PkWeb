package com.loris.feafip.test;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.loris.bean.Totales;
import com.loris.feafip.utils.WsaaService;
import com.loris.feafip.utils.WsaaToken;

import fev1.dif.afip.gov.ar.AlicIva;
import fev1.dif.afip.gov.ar.ArrayOfAlicIva;
import fev1.dif.afip.gov.ar.ArrayOfFECAEDetRequest;
import fev1.dif.afip.gov.ar.ArrayOfFECAEDetResponse;
import fev1.dif.afip.gov.ar.DummyResponse;
import fev1.dif.afip.gov.ar.FEAuthRequest;
import fev1.dif.afip.gov.ar.FECAECabRequest;
import fev1.dif.afip.gov.ar.FECAEDetRequest;
import fev1.dif.afip.gov.ar.FECAEDetResponse;
import fev1.dif.afip.gov.ar.FECAERequest;
import fev1.dif.afip.gov.ar.FECAEResponse;
import fev1.dif.afip.gov.ar.FERecuperaLastCbteResponse;
import fev1.dif.afip.gov.ar.Service;
import fev1.dif.afip.gov.ar.ServiceSoap;

public class WsfeTest {
	public static void main(String[] args) {
		Service serviceWsfe = new Service();
		ServiceSoap service = serviceWsfe.getPort(ServiceSoap.class);
		
		DummyResponse dummyResponse = service.feDummy();
		System.out.println("AppServer: " + dummyResponse.getAppServer() + " AuthServer: " + dummyResponse.getAuthServer()
				+ " DBServer: " + dummyResponse.getDbServer());
		
		WsaaService wsaaService = new WsaaService();
		WsaaToken wsaaToken = wsaaService.getWsaaToken();
		System.out.println("Token: " + wsaaToken.getToken() + " Sign: " + wsaaToken.getSign());
		
		Totales totales = getTotales();		
		
		FEAuthRequest auth = new FEAuthRequest();
		auth.setSign(wsaaToken.getSign());
		auth.setToken(wsaaToken.getToken());
		auth.setCuit(new Long("23045244059"));

		// Obtener ultimo comprobante autorizado
		FERecuperaLastCbteResponse cbteResponse = service.feCompUltimoAutorizado(auth, 1, 1);
		System.out.println("Nro Comprobante: " + cbteResponse.getCbteNro()	+ " Tipo Cbte: " + cbteResponse.getCbteTipo() + "" + cbteResponse.getPtoVta());
		// Obtener CAE
		FECAERequest feCAEReq = new FECAERequest();
		FECAECabRequest feCAECabRequest = new FECAECabRequest();
		feCAECabRequest.setCbteTipo(1);
		feCAECabRequest.setPtoVta(1);
		feCAECabRequest.setCantReg(1);

		FECAEDetRequest fecaeDetRequest = new FECAEDetRequest();
		// Concepto: 1 Productos 2 Servicios 3 Productos y Servicios
		fecaeDetRequest.setConcepto(1);
		// DocTipo: tipo de documento
		fecaeDetRequest.setDocTipo(80);
		// DocNro
		fecaeDetRequest.setDocNro(new Long("33693450239"));
		fecaeDetRequest.setCbteDesde(cbteResponse.getCbteNro() + 1);
		fecaeDetRequest.setCbteHasta(cbteResponse.getCbteNro() + 1);
		// CbtedFch: fecha del comprobante, formato yyyymmdd
		fecaeDetRequest.setCbteFch(new SimpleDateFormat("yyyyMMdd").format(new Date()));
		fecaeDetRequest.setImpTotal(totales.getSubTotalDescontado().add(totales.getIva()).doubleValue());
		// ImpTotConc: importe neto no gravado
		fecaeDetRequest.setImpTotConc(new Double("0"));
		fecaeDetRequest.setImpNeto(totales.getSubTotalDescontado().doubleValue());
		fecaeDetRequest.setImpIVA(totales.getIva().doubleValue());
		// MonId: código de moneda del comprobante
		fecaeDetRequest.setMonId("PES");
		// MonCotiz: cotización de la moneda, para pesos argentinos debe ser 1
		fecaeDetRequest.setMonCotiz(new Double("1.000"));

		ArrayOfAlicIva ivaImpuesto = new ArrayOfAlicIva();
		List<AlicIva> ivas = ivaImpuesto.getAlicIva();
		AlicIva iva = new AlicIva();
		// ID 5 = 21%
		iva.setId(5);
		iva.setBaseImp(totales.getSubTotalDescontado().doubleValue());
		iva.setImporte(totales.getIva().doubleValue());

		ivas.add(iva);
		fecaeDetRequest.setIva(ivaImpuesto);

		ArrayOfFECAEDetRequest arrayOfFECAEDetRequest = new ArrayOfFECAEDetRequest();
		arrayOfFECAEDetRequest.getFECAEDetRequest().add(fecaeDetRequest);
		
		feCAEReq.setFeCabReq(feCAECabRequest);
		feCAEReq.setFeDetReq(arrayOfFECAEDetRequest);
		FECAEResponse feCAEResponse = service.fecaeSolicitar(auth, feCAEReq);
		ArrayOfFECAEDetResponse feDetResponse = feCAEResponse.getFeDetResp();
		List<FECAEDetResponse> feCAEDetResponses = feDetResponse.getFECAEDetResponse();
		System.out.println("CAE: " + feCAEDetResponses.get(0).getCAE()	+ " CAE Vto: " + feCAEDetResponses.get(0).getCAEFchVto());
	}

	private static Totales getTotales() {
		final NumberFormat decimalFormat = DecimalFormat.getInstance();
		decimalFormat.setGroupingUsed(false);
		decimalFormat.setMaximumFractionDigits(2);
		decimalFormat.setMinimumFractionDigits(2);
		
		Totales totales = new Totales();
		totales.setSubTotal(new BigDecimal("100"));
		totales.setDescuentoTotal(new BigDecimal("35"));
		totales.setSubTotalDescontado(totales.getSubTotal().subtract(totales.getDescuentoTotal()));
		totales.setIva(new BigDecimal("13.65"));
		totales.setTotal(new BigDecimal("78.65"));
		
		return totales;
	}
}