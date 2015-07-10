package com.loris.web;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.print.PrinterJob;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.loris.bean.Items;
import com.loris.bean.Totales;
import com.loris.dao.ArticuloDAO;
import com.loris.dao.ClienteDAO;
import com.loris.dao.FacturaDAO;
import com.loris.dao.IVADAO;
import com.loris.dao.MarcaDAO;
import com.loris.dao.ParamsDAO;
import com.loris.domain.Articulo;
import com.loris.domain.Factura;
import com.loris.domain.FacturaType;
import com.loris.domain.IVA;
import com.loris.domain.ItemFactura;
import com.loris.domain.Params;
import com.loris.feafip.utils.WsaaService;
import com.loris.feafip.utils.WsaaToken;
import com.loris.print.AbstractPrint;
import com.loris.print.PrintFactura;
import com.loris.print.PrintFacturaA4;
import com.loris.print.PrintFacturaN;
import com.loris.utils.DateUtils;
import com.loris.utils.FacturaUtils;
import com.loris.utils.SuccessUtils;
import com.loris.validator.ConsultaFacturaValidator;
import com.lowagie.text.pdf.BarcodeInter25;

import fev1.dif.afip.gov.ar.AlicIva;
import fev1.dif.afip.gov.ar.ArrayOfAlicIva;
import fev1.dif.afip.gov.ar.ArrayOfErr;
import fev1.dif.afip.gov.ar.ArrayOfFECAEDetRequest;
import fev1.dif.afip.gov.ar.ArrayOfFECAEDetResponse;
import fev1.dif.afip.gov.ar.ArrayOfObs;
import fev1.dif.afip.gov.ar.Err;
import fev1.dif.afip.gov.ar.FEAuthRequest;
import fev1.dif.afip.gov.ar.FECAECabRequest;
import fev1.dif.afip.gov.ar.FECAEDetRequest;
import fev1.dif.afip.gov.ar.FECAEDetResponse;
import fev1.dif.afip.gov.ar.FECAERequest;
import fev1.dif.afip.gov.ar.FECAEResponse;
import fev1.dif.afip.gov.ar.FERecuperaLastCbteResponse;
import fev1.dif.afip.gov.ar.Obs;
import fev1.dif.afip.gov.ar.Service;
import fev1.dif.afip.gov.ar.ServiceSoap;

@Controller
@SessionAttributes(value = { "factura"/* , "printers" */})
public class FacturaController extends AbstractPrint {
	private final String FACTURA_TITLE = "Facturación";
	private final String FACTURA_EMISION = "Factura emitida exitosamente";
	@Autowired
	private MarcaDAO marcaDAO;
	@Autowired
	private ClienteDAO clienteDAO;
	@Autowired
	private FacturaDAO facturaDAO;
	@Autowired
	private ArticuloDAO articuloDAO;
	@Autowired
	private ParamsDAO paramsDAO;
	@Autowired
	private IVADAO ivaDAO;
	@Autowired
	private Items items;
	@Autowired
	private ConsultaFacturaValidator consultaFacturaValidator;

	@RequestMapping(value = "/factura/facturacion.htm")
	public String showFacturaLinks() {
		return "/factura/facturaLinks";
	}

	@RequestMapping(value = "/factura/emisionA4.htm", method = RequestMethod.GET)
	public String showFacturacionA4Page(ModelMap modelMap) {
		modelMap.addAttribute("marcas", marcaDAO.getMarcas());
		modelMap.addAttribute("clientes", clienteDAO.getClientes());
		modelMap.addAttribute("factura", new Factura());
		modelMap.addAttribute("printers", PrinterJob.lookupPrintServices());

		items = new Items();

		return "/factura/facturacionPageA4";
	}

	@RequestMapping(value = "/factura/emisionA4.htm", method = RequestMethod.POST)
	public ModelAndView facturarA4(@ModelAttribute("factura") Factura factura,BindingResult result,
									@RequestParam(value = "printerServiceIndex") int printerServiceIndex,
									@RequestParam(value = "tipoFactura") String tipoFactura) {
		
		Params params = paramsDAO.getParams();
		factura.setItems(getArticulos(items.getItemsFactura()));
		factura.setFecha(new Date());
		Factura.updateFacturaType(factura);		
		factura.setCliente(clienteDAO.getCliente(factura.getCliente().getId()));
		factura.updateStock(factura.getFacturaType().getFacturaTypeId());

		BigDecimal subTotalResult = new BigDecimal(0);
		BigDecimal ivaResult = new BigDecimal(0);
		BigDecimal descuentoTotalResult = new BigDecimal(0);

		Totales totales = FacturaUtils.makeTotales(items.getItemsFactura(),	subTotalResult, ivaResult, descuentoTotalResult, 
							items.getCurrentIVA(factura.getCliente().getId(), clienteDAO, ivaDAO), factura.getFacturaType().getFacturaTypeId());
		factura.setSubTotal(totales.getSubTotal());

		Collections.sort(factura.getItems());

		// Obtener parámetro TipoFactura: fm (Manual) o fe (Factura electrónica)
		if (Factura.TIPO_FACTURA_ELECTRONICA.equals(tipoFactura)) {
			String erroresYObservaciones = getCAEFacturaElectronica(factura, totales, FacturaType.FACTURA_TYPE_ELECTRONIC, FacturaType.TIPO_COMPROBANTE_FACTURA_A_AFIP);
			if(StringUtils.isNotBlank(erroresYObservaciones)){
				return new ModelAndView("successPage", "success", SuccessUtils.setSuccessBean(FACTURA_TITLE, erroresYObservaciones));
			}		
			
			try {
				generateXMLInputFEPDF(factura, totales, params.getIvaInscripto().getIVA());
			} catch (IOException e) {
				return new ModelAndView("successPage", "success", SuccessUtils.setSuccessBean(FACTURA_TITLE, "Error generando XML de entrada para FE en PDF mediante BirtViewer"));
			}
			
			return new ModelAndView("/factura/feComprobantes");
		} else {
			// Tipo Factura fm
			factura.setNroFactura(getNroFactura(params, factura.getFacturaType().getFacturaTypeId()));
			if (FacturaType.FACTURA_TYPE.equals(factura.getFacturaType().getFacturaTypeId())) {
				new PrintFacturaA4(factura, totales, params, printerServiceIndex);
			} else {
				new PrintFacturaN(factura, totales, params, printerServiceIndex);
			}
			
			
			facturaDAO.saveUpdateFactura(factura);
			
			return new ModelAndView("successPage", "success", SuccessUtils.setSuccessBean(FACTURA_TITLE, FACTURA_EMISION));
		}		
	}

	private String getCAEFacturaElectronica(Factura factura, Totales totales, Integer facturaType, int tipoComprobanteAFIP){
		// Tipo Factura fe
		WsaaService wsaaService = new WsaaService();
		WsaaToken wsaaToken = wsaaService.getWsaaToken();
		Service serviceWsfe = new Service();
		ServiceSoap service = serviceWsfe.getPort(ServiceSoap.class);
		
		FEAuthRequest auth = new FEAuthRequest();
		try{
			auth.setSign(wsaaToken.getSign());
			auth.setToken(wsaaToken.getToken());
			auth.setCuit(new Long("23045244059"));
		} catch(NullPointerException ex){
			return "Error obteniendo Token y Sign de AFIP, intente nuevamente";
		}		

		// Obtener ultimo comprobante autorizado
		FERecuperaLastCbteResponse cbteResponse = service.feCompUltimoAutorizado(auth, 2, tipoComprobanteAFIP);
		System.out.println("Nro Comprobante: " + cbteResponse.getCbteNro()	+ " Tipo Cbte: " + cbteResponse.getCbteTipo() + " Pto Venta: " + cbteResponse.getPtoVta());
		
		//Guardo el nro de comprobante
		factura.setNroFactura(cbteResponse.getCbteNro() + 1);
		
		// Obtener CAE
		FECAERequest feCAEReq = new FECAERequest();
		FECAECabRequest feCAECabRequest = new FECAECabRequest();
		feCAECabRequest.setCbteTipo(tipoComprobanteAFIP);
		//TODO Revisar
		feCAECabRequest.setPtoVta(2);
		feCAECabRequest.setCantReg(1);

		FECAEDetRequest fecaeDetRequest = new FECAEDetRequest();
		// Concepto: 1 Productos 2 Servicios 3 Productos y Servicios
		fecaeDetRequest.setConcepto(1);
		// DocTipo: tipo de documento
		fecaeDetRequest.setDocTipo(80);
		// DocNro
		fecaeDetRequest.setDocNro(new Long(factura.getCliente().getCuit()));
		fecaeDetRequest.setCbteDesde(factura.getNroFactura());
		fecaeDetRequest.setCbteHasta(factura.getNroFactura());
		// CbtedFch: fecha del comprobante, formato yyyymmdd
		fecaeDetRequest.setCbteFch(new SimpleDateFormat("yyyyMMdd").format(new Date()));
		fecaeDetRequest.setImpTotal(getAmount(totales.getSubTotalDescontado().add(totales.getIva())));
		// ImpTotConc: importe neto no gravado
		fecaeDetRequest.setImpTotConc(new Double("0"));
		fecaeDetRequest.setImpNeto(getAmount(totales.getSubTotalDescontado()));
		fecaeDetRequest.setImpIVA(getAmount(totales.getIva()));
		// MonId: código de moneda del comprobante
		fecaeDetRequest.setMonId("PES");
		// MonCotiz: cotización de la moneda, para pesos argentinos debe ser 1
		fecaeDetRequest.setMonCotiz(new Double("1.000"));

		ArrayOfAlicIva ivaImpuesto = new ArrayOfAlicIva();
		List<AlicIva> ivas = ivaImpuesto.getAlicIva();
		AlicIva iva = new AlicIva();
		// ID 5 = 21%
		iva.setId(5);
		iva.setBaseImp(getAmount(totales.getSubTotalDescontado()));
		iva.setImporte(getAmount(totales.getIva()));

		ivas.add(iva);
		fecaeDetRequest.setIva(ivaImpuesto);

		ArrayOfFECAEDetRequest arrayOfFECAEDetRequest = new ArrayOfFECAEDetRequest();
		arrayOfFECAEDetRequest.getFECAEDetRequest().add(fecaeDetRequest);
		
		feCAEReq.setFeCabReq(feCAECabRequest);
		feCAEReq.setFeDetReq(arrayOfFECAEDetRequest);
		FECAEResponse feCAEResponse = service.fecaeSolicitar(auth, feCAEReq);
		
		ArrayOfFECAEDetResponse feDetResponse = feCAEResponse.getFeDetResp();
		List<FECAEDetResponse> feCAEDetResponses = feDetResponse.getFECAEDetResponse();
		
		if(!"A".equalsIgnoreCase(feCAEResponse.getFeCabResp().getResultado())){
			String erroresYObservaciones = getFECAESolicitudErrorsAndObservations(feCAEResponse);
			return erroresYObservaciones;
		}			
		
		System.out.println("CAE: " + feCAEDetResponses.get(0).getCAE()	+ " CAE Vto: " + feCAEDetResponses.get(0).getCAEFchVto());
		
		factura.setCAE(feCAEDetResponses.get(0).getCAE());
		
		try {
			factura.setFechaVtoCAE(new SimpleDateFormat("yyyyMMdd").parse(feCAEDetResponses.get(0).getCAEFchVto()));
		} catch (ParseException e) {
			return "Error de formato de la fecha de vencimiento del CAE";
		}
		
		factura.setFacturaType(new FacturaType(facturaType));
		
		Params params = paramsDAO.getParams();
		if(FacturaType.FACTURA_TYPE_ELECTRONIC.equals(facturaType))
			params.setProxNumFacturaElectronica(factura.getNroFactura() + 1);
		else
			params.setProxNumNCDElectronica(factura.getNroFactura() + 1);
		
		paramsDAO.saveOrUpdateParams(params);
		
		facturaDAO.saveUpdateFactura(factura);
		
		return "";
	}
	
	private String getFECAESolicitudErrorsAndObservations(FECAEResponse feCAEResponse) {
		String errors = "Solicitud de CAE Rechazada<br>";
		ArrayOfErr arrayOfErrors = feCAEResponse.getErrors();
		if(arrayOfErrors != null){
			for (Err error : arrayOfErrors.getErr()) {
				errors += "Código de error: " + error.getCode() + " Descripción: " + error.getMsg() + "<br>";
			}
		}
		FECAEDetResponse fecaeDetResponse = feCAEResponse.getFeDetResp().getFECAEDetResponse().get(0);
		ArrayOfObs arrayOfObs = fecaeDetResponse.getObservaciones();
		if(arrayOfObs != null){
			for(Obs observation: arrayOfObs.getObs()){
				errors += "Código de Observación: " + observation.getCode() + " Descripción: " + observation.getMsg() + "<br>";
			}
		}
		
		return errors;
	}

	private void generateXMLInputFEPDF(Factura factura, Totales totales, BigDecimal iva) throws IOException {		
		FileWriter xmlInputFEPDF = new FileWriter(new File(FacturaUtils.getProperty(FacturaUtils.PATH_INPUT_PDF)));
		BufferedWriter bufferedWriter = new BufferedWriter(xmlInputFEPDF);

		bufferedWriter.append("<?xml version=" + "\"" + "1.0" + "\"" + " encoding=" + "\"" + "ISO-8859-1" + "\"" + "?>");
		bufferedWriter.newLine();
		bufferedWriter.append("<InputData>");
		bufferedWriter.newLine();
		bufferedWriter.append("<feDATA>");
		bufferedWriter.newLine();
		bufferedWriter.append("<feTipoFactura>" + getTipoFactura(factura) + "</feTipoFactura>");
		bufferedWriter.newLine();
		bufferedWriter.append("<feCodigoTipoFactura>" + getTipoComprobante(factura) + "</feCodigoTipoFactura>");
		bufferedWriter.newLine();
		bufferedWriter.append("<feNro>" + "0002 - " + getNroFactura(factura.getNroFactura()) + "</feNro>");
		bufferedWriter.newLine();
		bufferedWriter.append("<feFecha>" + DateUtils.convertDateToString(factura.getFecha()) + "</feFecha>");
		bufferedWriter.newLine();
		bufferedWriter.append("<feComprador>" + factura.getCliente().getId() + " "
								+ factura.getCliente().getRazonSocial() + "</feComprador>");
		bufferedWriter.newLine();
		bufferedWriter.append("<feCompradorCalle>"	+ factura.getCliente().getDireccion().getCalle() + " "
								+ factura.getCliente().getDireccion().getNumero() + "</feCompradorCalle>");
		bufferedWriter.newLine();
		bufferedWriter.append("<feCompradorLocalidad> CP: "
								+ factura.getCliente().getDireccion().getCodPostal()
								+ " - "
								+ factura.getCliente().getDireccion().getLocalidad()
								+ "</feCompradorLocalidad>");
		bufferedWriter.newLine();
		bufferedWriter.append("<feCUIT>" + getCuit(factura.getCliente().getCuit()) + "</feCUIT>");
		bufferedWriter.newLine();
		bufferedWriter.append("<feCondicionesVenta>Contado</feCondicionesVenta>");
		bufferedWriter.newLine();
		bufferedWriter.append("<feRemitoNro></feRemitoNro>");
		bufferedWriter.newLine();
		bufferedWriter.append("<fePorcentajeIVA>"	+ getAmount(iva, 4) + "%</fePorcentajeIVA>");
		bufferedWriter.newLine();
		bufferedWriter.append("<feIVA>" + getAmount(totales.getIva(), 8) + "</feIVA>");
		bufferedWriter.newLine();
		bufferedWriter.append("<feSubTotalUno>" + getAmount(totales.getSubTotal(), 8) + "</feSubTotalUno>");
		bufferedWriter.newLine();
		bufferedWriter.append("<feDescuentoPorcentaje></feDescuentoPorcentaje>");
		bufferedWriter.newLine();
		bufferedWriter.append("<feDescuento>" + getAmount(totales.getDescuentoTotal(), 8)	+ "</feDescuento>");
		bufferedWriter.newLine();
		bufferedWriter.append("<feSubTotalDos>"	+ getAmount(totales.getSubTotalDescontado(), 8) + "</feSubTotalDos>");
		bufferedWriter.newLine();
		bufferedWriter.append("<feTotal>" + getAmount(totales.getTotal(), 8) + "</feTotal>");
		bufferedWriter.newLine();
		bufferedWriter.append("<feCAE>" + factura.getCAE() + "</feCAE>");
		bufferedWriter.newLine();
		bufferedWriter.append("<feCAEVto>" + DateUtils.convertDateToString(factura.getFechaVtoCAE()) + "</feCAEVto>");
		bufferedWriter.newLine();
		// ITEMS
		int i = 1;
		for (ItemFactura item : factura.getItems()) {
			bufferedWriter.append(	"<feItem"	+ i + "Codigo>" + getNormalizedWidthCode(item.getArticulo().getMarca().getId().toString(), 3) 
									+ getNormalizedWidthCode(item.getArticulo().getFamilia().getCodigo().toString(), 4)
									+ getNormalizedWidthCode(item.getArticulo().getCodigo().toString(), 8)
									+ "</feItem"+ i + "Codigo>");
			bufferedWriter.newLine();
			bufferedWriter.append("<feItem"
									+ i	+ "Descripcion>" + item.getArticulo().getFamilia().getDescripcion().toUpperCase()
									+ " " + item.getArticulo().getDescripcion()	+ " "
									+ item.getArticulo().getMarca().getDescripcion().toUpperCase() + "</feItem" + i
									+ "Descripcion>");
			bufferedWriter.newLine();
			bufferedWriter.append("<feItem"
									+ i	+ "Cant>" + getNormalizedWidthCode(item.getCantidad().toString(),	6) 
									+ "</feItem" + i + "Cant>");
			bufferedWriter.newLine();
			bufferedWriter.append("<feItem" + i + "Descuento>"
									+ getAmount(item.getDescuento(), 4) + "</feItem" + i + "Descuento>");
			bufferedWriter.newLine();
			bufferedWriter.append("<feItem" + i + "Precio>"	+ getAmount(item.getArticulo().getPrecioVenta(), 8)
									+ "</feItem" + i + "Precio>");
			bufferedWriter.newLine();
			bufferedWriter.append("<feItem" + i + "Importe>" + getAmount(item.getTotalItem(), 8) + "</feItem" + i + "Importe>");
			bufferedWriter.newLine();
			++i;
		}

		for (; i <= 30; i++) {
			bufferedWriter.append("<feItem"	+ i + "Codigo>" + " " + "</feItem"+ i + "Codigo>");
			bufferedWriter.newLine();
			bufferedWriter.append("<feItem" + i + "Descripcion>" + "</feItem" + i + "Descripcion>");
			bufferedWriter.newLine();
			bufferedWriter.append("<feItem" + i + "Cant>" + "</feItem" + i + "Cant>");
			bufferedWriter.newLine();
			bufferedWriter.append("<feItem" + i + "Descuento>" + "</feItem"	+ i + "Descuento>");
			bufferedWriter.newLine();
			bufferedWriter.append("<feItem" + i + "Precio>" + "</feItem" + i + "Precio>");
			bufferedWriter.newLine();
			bufferedWriter.append("<feItem" + i + "Importe>" + "</feItem"
					+ i + "Importe>");
			bufferedWriter.newLine();
		}
		
		//Codigo de Barras
		String codigoBarras = getCodigoBarrasList(factura);		
		bufferedWriter.append("<feCodigoBarras>" + codigoBarras + generateCodigoBarrasImage(codigoBarras) + "</feCodigoBarras>");
		bufferedWriter.newLine();
		bufferedWriter.append("<feComentarios1>" + getLineaComentarios(1, factura.getComentarios()) + "</feComentarios1>");
		bufferedWriter.newLine();
		bufferedWriter.append("<feComentarios2>" + getLineaComentarios(2, factura.getComentarios()) + "</feComentarios2>");
		bufferedWriter.newLine();
		bufferedWriter.append("<feComentarios3>" + getLineaComentarios(3, factura.getComentarios()) + "</feComentarios3>");
		bufferedWriter.newLine();
		bufferedWriter.append("<feComentarios4>" + getLineaComentarios(4, factura.getComentarios()) + "</feComentarios4>");
		bufferedWriter.newLine();
		
		bufferedWriter.append("</feDATA>");
		bufferedWriter.newLine();
		bufferedWriter.append("</InputData>");

		bufferedWriter.close();
	}
	
	private String getNroFactura(Integer nroFactura) {
		String nroFacturaString = nroFactura.toString();
		if(nroFacturaString.length() < 8){
			return StringUtils.leftPad(nroFacturaString, 8, "0");
		}
		return null;
	}

	private String getLineaComentarios(int i, String comentarios) {
		if(StringUtils.isNotBlank(comentarios)){
			String[] comentariosVector = StringUtils.split(comentarios, "\r\n");
			if(i <= comentariosVector.length)
				return comentariosVector[i - 1];
		}
		return "";
	}

	private char generateCodigoBarrasImage(String codigoBarras) throws IOException {		
		BarcodeInter25 barcodeInter25 = new BarcodeInter25();
		barcodeInter25.setGenerateChecksum(true);
		barcodeInter25.setCode(codigoBarras);
		barcodeInter25.setBarHeight(53);
		Image barCode = barcodeInter25.createAwtImage(Color.black, Color.white);
		
		BufferedImage bi = new BufferedImage(barCode.getWidth(null), barCode.getHeight(null), BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = bi.createGraphics();
		g2d.drawImage(barCode, 0, 0, null);
		//TODO Change
		ImageIO.write(bi, "gif", new File(FacturaUtils.getProperty(FacturaUtils.FILE_PATH_CODIGO_BARRAS)));
		
		return barcodeInter25.getChecksum(codigoBarras);
	}

	private String getCodigoBarrasList(Factura factura) {
		String codigoBarrasString = "";
		//CUIT 23045244059
		codigoBarrasString += "23045244059";
		//Codigo Comprobante
		if(factura.getIsFacturaElectronica()){
			codigoBarrasString += "01";
		}else{
			codigoBarrasString += "03";
		}
		//Punto de Venta
		codigoBarrasString += "0002"; 
		//CAE
		codigoBarrasString += factura.getCAE();
		//Fecha de Vto
		codigoBarrasString += DateUtils.convertDateToStringCodigoBarras(factura.getFechaVtoCAE());
		
		return codigoBarrasString;
	}

	private int getDigitoVerificador(char[] codigoBarrasInput){
		 /*- C.U.I.T. (Clave Unica de Identificación Tributaria) del emisor (11 caracteres).
		 - Código de tipo de comprobante (2 caracteres).
		 - Punto de venta (4 caracteres).
		 - Código de Autorización de Impresión (14 caracteres).
		 - Fecha de vencimiento (8 caracteres).
		 - Dígito verificador (1 carácter)*/
		
		int digitoVerificador = 0;
		
		int etapa1 = 0;
		for (int i = 0; i < codigoBarrasInput.length; i += 2) {
			etapa1 += new Integer(codigoBarrasInput[i]);
		}
		
		int etapa2 = etapa1 * 3;
		int etapa3 = 0;
		for (int i = 1; i < codigoBarrasInput.length; i += 2) {
			etapa3 += new Integer(codigoBarrasInput[i]);
		}
		int etapa4 = etapa2 + etapa3;
		
		List<Integer> listaOrdenada = new ArrayList<Integer>();
		for (int j = 0; j < codigoBarrasInput.length; j++) {
			listaOrdenada.add(new Integer(codigoBarrasInput[j]));
		}
		Collections.sort(listaOrdenada);
		
		for (Integer num : listaOrdenada) {
			int temp = etapa4 + num;
			if(temp % 10 == 0)
				return num;
		}
		
		return digitoVerificador;
	}

	private String getTipoComprobante(Factura factura) {
		if(FacturaType.FACTURA_TYPE_ELECTRONIC.equals(factura.getFacturaType().getFacturaTypeId()))
			return "01";
		return "03";
	}

	private String getTipoFactura(Factura factura) {
		if(FacturaType.FACTURA_TYPE_ELECTRONIC.equals(factura.getFacturaType().getFacturaTypeId()))
			return "FACTURA";
		return "NOTA DE CREDITO";
	}

	@RequestMapping(value = "/factura/emision.htm", method = RequestMethod.GET)
	public String showFacturacionPage(ModelMap modelMap) {
		modelMap.addAttribute("marcas", marcaDAO.getMarcas());
		modelMap.addAttribute("clientes", clienteDAO.getClientes());
		modelMap.addAttribute("factura", new Factura());
		modelMap.addAttribute("printers", PrinterJob.lookupPrintServices());

		items = new Items();

		return "/factura/facturacionPage";
	}

	@RequestMapping(value = "/factura/emision.htm", method = RequestMethod.POST)
	public ModelAndView facturar(@ModelAttribute("factura") Factura factura, BindingResult result,
			@RequestParam(value = "printerServiceIndex") int printerServiceIndex) {
		Params params = paramsDAO.getParams();
		factura.setItems(getArticulos(items.getItemsFactura()));
		factura.setFecha(new Date());
		Factura.updateFacturaType(factura);
		factura.setNroFactura(getNroFactura(params, factura.getFacturaType().getFacturaTypeId()));
		factura.setCliente(clienteDAO.getCliente(factura.getCliente().getId()));
		factura.updateStock(factura.getFacturaType().getFacturaTypeId());

		BigDecimal subTotalResult = new BigDecimal(0);
		BigDecimal ivaResult = new BigDecimal(0);
		BigDecimal descuentoTotalResult = new BigDecimal(0);

		Totales totales = FacturaUtils.makeTotales(items.getItemsFactura(),
				subTotalResult, ivaResult, descuentoTotalResult, items.getCurrentIVA(factura.getCliente().getId(),
				clienteDAO, ivaDAO), factura.getFacturaType().getFacturaTypeId());
		factura.setSubTotal(totales.getSubTotal());

		Collections.sort(factura.getItems());

		facturaDAO.saveUpdateFactura(factura);

		if (FacturaType.FACTURA_TYPE.equals(factura.getFacturaType()
				.getFacturaTypeId())) {
			new PrintFactura(factura, totales, params);
		} else {
			new PrintFacturaN(factura, totales, params, printerServiceIndex);
		}

		return new ModelAndView("successPage", "success", SuccessUtils.setSuccessBean(FACTURA_TITLE, FACTURA_EMISION));
	}

	@RequestMapping(value = "/factura/consulta.htm", method = RequestMethod.GET)
	public String showConsultaFactura(ModelMap modelMap) {
		modelMap.addAttribute("factura", new Factura());

		return "/factura/consultaFacturaForm";
	}

	@RequestMapping(value = "/factura/consulta.htm", method = RequestMethod.POST)
	public ModelAndView findFactura(@ModelAttribute("factura") Factura factura,	BindingResult result, @RequestParam(value="typeF") String typeF) {
		if(StringUtils.isNotBlank(typeF)){
			factura.getFacturaType().setFacturaTypeId(new Integer(typeF));
		}
		Factura facturaDB = facturaDAO.getFacturaByNumberAndType(factura.getNroFactura(), getFacturaType(factura.getFacturaType().getFacturaTypeId()));
		consultaFacturaValidator.validate(facturaDB, result);

		if (result.hasErrors()) {
			return new ModelAndView("/factura/consultaFacturaForm", "factura", factura);
		}

		ModelAndView modelAndView = new ModelAndView();

		FacturaUtils.makeTotales(facturaDB.getItems(), items.getCurrentIVA(facturaDB.getCliente().getId(), clienteDAO, ivaDAO), 
				facturaDB.getFacturaType().getFacturaTypeId(), modelAndView);
		modelAndView.addObject("factura", facturaDB);
		modelAndView.setViewName("/factura/consultaFacturaForm");

		return modelAndView;
	}
	
	@RequestMapping(value = "/factura/reImpresionFacturaElectronica.htm", method = RequestMethod.GET)
	public String showReImpresionFacturaElectronica(ModelMap modelMap) {
		modelMap.addAttribute("factura", new Factura());

		return "/factura/reImpresionFacturaElectronicaForm";
	}
	
	@RequestMapping(value = "/factura/reImpresionFacturaElectronica.htm", method = RequestMethod.POST)
	public ModelAndView findFacturaElectronica(@ModelAttribute("factura") Factura factura, BindingResult result) {
		Factura facturaDB = facturaDAO.getFacturaByNumberAndType(factura.getNroFactura(), FacturaType.FACTURA_TYPE_ELECTRONIC);
		consultaFacturaValidator.validate(facturaDB, result);

		if (result.hasErrors()) {
			return new ModelAndView("/factura/reImpresionFacturaElectronicaForm", "factura", factura);
		}		

		ModelAndView modelAndView = new ModelAndView();
		this.items = new Items();
		
		FacturaUtils.makeTotales(facturaDB.getItems(), items.getCurrentIVA(facturaDB.getCliente().getId(), clienteDAO, ivaDAO), 
				facturaDB.getFacturaType().getFacturaTypeId(), modelAndView);		

		try {
			generateXMLInputFEPDF(facturaDB, (Totales)modelAndView.getModel().get("totales"), paramsDAO.getParams().getIvaInscripto().getIVA());
		} catch (IOException e) {
			return new ModelAndView("successPage", "success", SuccessUtils.setSuccessBean(FACTURA_TITLE, "Error generando XML de entrada para FE en PDF mediante BirtViewer"));
		}
		
		return new ModelAndView("/factura/feComprobantes");
	}
	
	@RequestMapping(value = "/factura/birtViewer.htm", method = RequestMethod.GET)
	public ModelAndView redirectBirtViewer(@RequestParam(value="originalDuplicado") String originalDuplicado) {

		return new ModelAndView("/factura/feComprobante" + originalDuplicado);
	}
	
	@RequestMapping(value = "/factura/notaCredito.htm", method = RequestMethod.GET)
	public String showNotaCredito(ModelMap modelMap) {
		modelMap.addAttribute("factura", new Factura());

		return "/factura/notaCreditoForm";
	}

	@RequestMapping(value = "/factura/notaCredito.htm", method = RequestMethod.POST)
	public ModelAndView findFacturaNC(@ModelAttribute("factura") Factura factura, BindingResult result) {
		Factura facturaDB = facturaDAO.getFacturaByNumberAndType(factura.getNroFactura(), getFacturaType(factura.getFacturaType().getFacturaTypeId()));
		consultaFacturaValidator.validate(facturaDB, result);

		if (result.hasErrors()) {
			return new ModelAndView("/factura/notaCreditoForm", "factura", factura);
		}

		this.items = new Items();
		ModelAndView modelAndView = new ModelAndView();
		
		FacturaUtils.makeTotales(facturaDB.getItems(), items.getCurrentIVA(facturaDB.getCliente().getId(), clienteDAO, ivaDAO), 
				facturaDB.getFacturaType().getFacturaTypeId(), modelAndView);
		modelAndView.addObject("factura", facturaDB);
		modelAndView.addObject("printers", PrinterJob.lookupPrintServices());
		modelAndView.setViewName("/factura/notaCreditoFormB");

		return modelAndView;
	}

	@RequestMapping(value = "/factura/notaCreditoSave.htm")
	public ModelAndView saveNotaCredito(HttpSession session, HttpServletRequest request,
			@RequestParam(value = "printerServiceIndex") int printerServiceIndex) {
		Factura factura = (Factura) session.getAttribute("factura");
		factura.setFacturaType(getFacturaTypeForNC(factura));

		factura.setItems(getArticulos(this.items.getItemsFactura()));
		factura.setId(null);		
		factura.setComentarios(request.getParameter("comentarios"));
		factura.updateStock(factura.getFacturaType().getFacturaTypeId());

		BigDecimal subTotalResult = new BigDecimal(0);
		BigDecimal ivaResult = new BigDecimal(0);
		BigDecimal descuentoTotalResult = new BigDecimal(0);

		Collections.sort(factura.getItems());

		Totales totales = FacturaUtils.makeTotales(items.getItemsFactura(),	subTotalResult, ivaResult, descuentoTotalResult, 
				items.getCurrentIVA(factura.getCliente().getId(), clienteDAO, ivaDAO), factura.getFacturaType().getFacturaTypeId());
		factura.setSubTotal(totales.getSubTotal());
		factura.setFecha(new Date());

		facturaDAO.saveUpdateFactura(factura);
		// PRINT NC.
		if (FacturaType.FACTURA_TYPE_ELECTRONIC.equals(factura.getFacturaType().getFacturaTypeId())) {
			String erroresYObservaciones = getCAEFacturaElectronica(factura, totales, FacturaType.FACTURA_NC_TYPE_ELECTRONIC, FacturaType.TIPO_COMPROBANTE_NOTA_CREDITO_A_AFIP);
			if(StringUtils.isNotBlank(erroresYObservaciones)){
				return new ModelAndView("successPage", "success", SuccessUtils.setSuccessBean(FACTURA_TITLE, erroresYObservaciones));
			}		
			
			try {
				generateXMLInputFEPDF(factura, totales, paramsDAO.getParams().getIvaInscripto().getIVA());
			} catch (IOException e) {
				return new ModelAndView("successPage", "success", SuccessUtils.setSuccessBean(FACTURA_TITLE, "Error generando XML de entrada para FE en PDF mediante BirtViewer"));
			}
			
			return new ModelAndView("/factura/feComprobantes");
		}else{
			factura.setNroFactura(getNroFactura(paramsDAO.getParams(), factura.getFacturaType().getFacturaTypeId()));
			new PrintFacturaA4(factura, totales, paramsDAO.getParams(),	printerServiceIndex);	
		}

		return new ModelAndView("successPage", "success", SuccessUtils.setSuccessBean("Notas de Credito", "Nota de Crédito emitida satisfactoriamente"));
	}

	private FacturaType getFacturaTypeForNC(Factura factura) {
		if(FacturaType.FACTURA_TYPE.equals(factura.getFacturaType().getFacturaTypeId()))
			return new FacturaType(FacturaType.FACTURA_NC_TYPE);
		if(FacturaType.FACTURA_TYPE_ELECTRONIC.equals(factura.getFacturaType().getFacturaTypeId()))
			return new FacturaType(FacturaType.FACTURA_NC_TYPE_ELECTRONIC);
		return null;
	}

	@RequestMapping(value = "/factura/anularFactura.htm", method = RequestMethod.GET)
	public String anularFactura(ModelMap modelMap) {
		modelMap.addAttribute("factura", new Factura());

		return "/factura/anularFacturaForm";
	}

	@RequestMapping(value = "/factura/anularFactura.htm", method = RequestMethod.POST)
	public ModelAndView findFacturaAnulacion(
			@ModelAttribute("factura") Factura factura, BindingResult result) {
		Factura facturaDB = facturaDAO.getFacturaByNumberAndType(factura.getNroFactura(), getFacturaType(factura.getFacturaType().getFacturaTypeId()));
		consultaFacturaValidator.validate(facturaDB, result);

		if (result.hasErrors()) {
			return new ModelAndView("/factura/anularFacturaForm", "factura", factura);
		}

		this.items = new Items();
		ModelAndView modelAndView = new ModelAndView();

		FacturaUtils.makeTotales(facturaDB.getItems(), items.getCurrentIVA(facturaDB.getCliente().getId(), clienteDAO, ivaDAO), 
				facturaDB.getFacturaType().getFacturaTypeId(), modelAndView);
		modelAndView.addObject("factura", facturaDB);
		modelAndView.setViewName("/factura/anularFacturaFormB");

		return modelAndView;
	}

	@RequestMapping(value = "/factura/anularFacturaSave.htm")
	public ModelAndView anularFacturaSave(HttpSession session,
			HttpServletRequest request) {
		Factura factura = (Factura) session.getAttribute("factura");

		factura.getFacturaType().setFacturaTypeId(FacturaType.FACTURA_ANULADA_TYPE);
		factura.updateStock(factura.getFacturaType().getFacturaTypeId());

		facturaDAO.saveUpdateFactura(factura);

		return new ModelAndView("successPage", "success", SuccessUtils.setSuccessBean("Anulación Factura", "Factura: "
						+ factura.getNroFactura() + " anulada satisfactoriamente"));
	}

	@RequestMapping(value = "/factura/DoCalculos.json", method = RequestMethod.GET)
	public ModelAndView doCalculos(@RequestParam("itemId") String itemId, @RequestParam("cantidad") BigDecimal cantidad,
			@RequestParam("descuento") BigDecimal descuento, @RequestParam("marcaId") Integer marcaId,
			@RequestParam("familiaId") Integer familiaId, @RequestParam("codigo") String codigo,
			@RequestParam("precio") BigDecimal precio,	@RequestParam("idCliente") Integer idCliente,
			@RequestParam("n") Integer typeN) {

		ModelAndView modelAndView = new ModelAndView();

		if (codigo != null && marcaId != null && familiaId != null) {
			Articulo articulo = new Articulo();
			articulo.setCodigo(codigo);
			articulo.setFamiliaId(familiaId);
			articulo.setMarcaId(marcaId);

			articulo = articuloDAO.getArticulo(articulo);
			ItemFactura itemFactura = new ItemFactura();
			itemFactura.setArticulo(articulo);
			itemFactura.setCantidad(cantidad);
			itemFactura.setPrecio(precio);

			if (descuento == null)
				itemFactura.setDescuento(new BigDecimal(0));
			else
				itemFactura.setDescuento(descuento);

			this.items.getItemsFactura().put(itemId, itemFactura);

			BigDecimal subTotalResult = new BigDecimal(0);
			BigDecimal ivaResult = new BigDecimal(0);
			BigDecimal descuentoTotalResult = new BigDecimal(0);

			makeCalculos(items.getItemsFactura(), subTotalResult, ivaResult, descuentoTotalResult, modelAndView,
					items.getCurrentIVA(idCliente, clienteDAO, ivaDAO), typeN);
		} else
			modelAndView.addObject("success", Boolean.FALSE);

		return modelAndView;
	}

	@RequestMapping(value = "/factura/DeleteItem.json")
	public ModelAndView deleteItem(@RequestParam("itemId") String itemId,
			@RequestParam("idCliente") Integer idCliente, @RequestParam("n") Integer typeN) {

		final ModelAndView modelAndView = new ModelAndView();

		items.getItemsFactura().remove(itemId);

		BigDecimal subTotalResult = new BigDecimal(0);
		BigDecimal ivaResult = new BigDecimal(0);
		BigDecimal descuentoTotalResult = new BigDecimal(0);

		return makeCalculos(items.getItemsFactura(), subTotalResult, ivaResult,	descuentoTotalResult, 
				modelAndView,	this.items.getCurrentIVA(idCliente, clienteDAO, ivaDAO), typeN);
	}

	@RequestMapping(value = "/factura/ValidateItem.json")
	public ModelAndView validateItem(@RequestParam("marca") Integer marcaId,
			@RequestParam("familia") Integer familiaId,	@RequestParam("codigo") String codigo) {

		final ModelAndView modelAndView = new ModelAndView();

		for (Iterator iterator = items.getItemsFactura().keySet().iterator(); iterator.hasNext();) {
			String itemKey = (String) iterator.next();
			ItemFactura itemFactura = items.getItemsFactura().get(itemKey);

			if (itemFactura.getArticulo().getFamilia().getCodigo().equals(familiaId)
					&& itemFactura.getArticulo().getCodigo().equals(codigo)
					&& itemFactura.getArticulo().getMarca().getId().equals(marcaId)) {
				return modelAndView.addObject("existing", Boolean.TRUE);
			}
		}

		return modelAndView.addObject("existing", Boolean.FALSE);
	}

	// TODO
	@RequestMapping(value = "/factura/remitos.htm", method = RequestMethod.GET)
	public String showRemitoForm(ModelMap modelMap) {
		return "construction";
	}

	// TODO
	@RequestMapping(value = "/factura/emisionFromRemito.htm", method = RequestMethod.GET)
	public String showFacturaFromRemito(ModelMap modelMap) {
		return "construction";
	}

	private Integer getFacturaType(Integer facturaType) {
		if (facturaType == null)
			return FacturaType.FACTURA_TYPE;
		return facturaType;
	}

	private Integer getNroFactura(Params params, Integer facturaType) {
		Integer proxNumFactura = null;
		if (facturaType != null) {
			switch (facturaType) {
			case 1:/* FACTURA TYPE */
				proxNumFactura = params.getProxNumFactura();
				params.setProxNumFactura(proxNumFactura + 1);
				params.setProxNumNC(params.getProxNumNC() + 1);
				break;
			case 2:/* FACTURA TYPE N */
				proxNumFactura = params.getProxNumFacturaN();
				params.setProxNumFacturaN(params.getProxNumFacturaN() + 1);
				break;
			case 3:/* FACTURA TYPE NC */
				proxNumFactura = params.getProxNumNC();
				params.setProxNumNC(proxNumFactura + 1);
				params.setProxNumFactura(proxNumFactura + 1);
				break;
			default:
				break;
			}
		}

		paramsDAO.saveOrUpdateParams(params);

		return proxNumFactura;
	}

	private List<ItemFactura> getArticulos(Map<String, ItemFactura> itemsFactura) {
		List<ItemFactura> itemsResult = new ArrayList<ItemFactura>();
		for (Iterator iterator = itemsFactura.keySet().iterator(); iterator.hasNext();) {
			String itemKey = (String) iterator.next();
			ItemFactura itemFactura = itemsFactura.get(itemKey);
			if (StringUtils.isNotBlank(itemFactura.getArticulo().getCodigo())) {
				itemFactura.setArticulo(articuloDAO.getArticulo(itemFactura.getArticulo()));
				itemsResult.add(itemFactura);
			}
		}
		return itemsResult;
	}

	private ModelAndView makeCalculos(
			HashMap<String, ItemFactura> itemsFactura, BigDecimal subTotalResult, BigDecimal ivaResult,
			BigDecimal descuentoTotalResult, ModelAndView modelAndView,	IVA currentIVA, Integer facturaType) {

		final NumberFormat decimalFormat = DecimalFormat.getInstance();
		decimalFormat.setGroupingUsed(false);
		decimalFormat.setMaximumFractionDigits(2);
		decimalFormat.setMinimumFractionDigits(2);

		Totales totales = FacturaUtils.makeTotales(itemsFactura, subTotalResult, ivaResult, descuentoTotalResult, currentIVA,
				facturaType);

		modelAndView.addObject("subTotal", totales.getSubTotal());
		modelAndView.addObject("descuentoTotal", totales.getDescuentoTotal());
		modelAndView.addObject("iva", totales.getIva());
		modelAndView.addObject("total",	decimalFormat.format(totales.getTotal()));
		modelAndView.addObject("success", Boolean.TRUE);

		return modelAndView;
	}
}
