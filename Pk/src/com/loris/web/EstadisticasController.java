package com.loris.web;

import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.loris.bean.DateFilter;
import com.loris.dao.ClienteDAO;
import com.loris.dao.EstadisticasDAO;
import com.loris.dao.FacturaDAO;
import com.loris.dao.IVADAO;
import com.loris.domain.Articulo;
import com.loris.domain.Factura;
import com.loris.domain.ItemFactura;
import com.loris.utils.FacturaUtils;

@Controller
public class EstadisticasController {
	@Autowired
	EstadisticasDAO estadisticasDAO;
	@Autowired
	FacturaDAO facturaDAO;
	@Autowired
	IVADAO ivaDAO;
	@Autowired
	ClienteDAO clienteDAO;
	
	
	@RequestMapping(value="/estadisticas.htm")
	public String showEstadisticasLinks(){
		return "/estadisticas/estadisticasLinks";
	}
	
	@RequestMapping(value="/estadisticas/articulosMasVendidos.htm", method = RequestMethod.GET)
	public String showArticulosMasVendidosForm(ModelMap map){
		map.addAttribute("filtros", new DateFilter());		
		
		return "/estadisticas/articulosMasVendidosForm";
	}
	
	@RequestMapping(value="/estadisticas/articulosMasVendidos.htm", method = RequestMethod.POST)
	public ModelAndView getArticulosMasVendidos(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("filtros")DateFilter filter, BindingResult result){
		
		if(result.hasErrors()){
			return new ModelAndView("/estadisticas/articulosMasVendidosForm", "filtros", filter);
		}
		
		List<ItemFactura> items = estadisticasDAO.getArticulosMasVendidos(filter);		
		// create a dataset...
		DefaultPieDataset data = new DefaultPieDataset();
		
		for(ItemFactura item : items){
			Articulo articulo = item.getArticulo();
			data.setValue(articulo.getFamilia().getDescripcion() + " - " + articulo.getDescripcion() + " - " 
				+ articulo.getMarca().getDescripcion(), item.getCantidad());
		}		
		
		// create a chart
		JFreeChart chart = ChartFactory.createPieChart3D("Artículos Más Vendidos",data, true, true, false);
        
		response.setContentType("image/jpeg");
        
        OutputStream salida;
		try {
			salida = response.getOutputStream();
			ChartUtilities.writeChartAsJPEG(salida,chart,900,700);

	        salida.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value="/estadisticas/facturadoAUnClientePorFecha.htm", method = RequestMethod.GET)
	public ModelAndView showFacturadoAUnClientePorFechaForm(){
		ModelAndView map = new ModelAndView();
		map.addObject("filtros", new DateFilter());
		map.addObject("clientes", clienteDAO.getClientes());
		map.setViewName("/estadisticas/facturadoAUnClientePorFechaForm");
		
		return map;
	}
	
	@RequestMapping(value="/estadisticas/facturadoAUnClientePorFecha.htm", method = RequestMethod.POST)
	public ModelAndView showFacturacion(@ModelAttribute(value="filtros")DateFilter filtros,BindingResult result, 
			@RequestParam(value="clienteCode")Integer clienteCode, @RequestParam(value="typeN")Integer typeN ){
		
		ModelAndView model = new ModelAndView();
		model.addObject("filtros", filtros);
		model.addObject("clientes", clienteDAO.getClientes());
		model.addObject("clienteCodeValue", clienteCode);
		model.addObject("typeN", typeN);
		model.setViewName("/estadisticas/facturadoAUnClientePorFechaForm");
		
		if(result.hasErrors()){
			return model;
		}
		
		List<Factura> facturas = facturaDAO.getFacturasClienteByDateAndType(clienteCode, filtros, typeN);
		
		if(facturas == null || facturas.isEmpty()){
			model.addObject("errorMessage", "No existen facturas para el cliente: " + clienteCode + " en el periodo indicado");
		}
		model.addObject("facturas", facturas);
		model.addObject("totalCliente", facturaDAO.getTotalSubtotalesClienteByDateAndType(clienteCode, filtros, typeN));
		
		return model;
	}
	
	@RequestMapping(value="/estadisticas/consultaFactura.htm")
	public ModelAndView showFacturaDetail(@RequestParam(value="id")Integer facturaId,
			@RequestParam(value="clienteCode")Integer clienteCode){
		Factura factura = facturaDAO.getFactura(facturaId);
		
		ModelAndView model = new ModelAndView();
		model.addObject("factura", factura);
		model.addObject("filtros", new DateFilter());
		model.addObject("clientes", clienteDAO.getClientes());
		model.addObject("clienteCodeValue", clienteCode);
		FacturaUtils.makeTotales(factura.getItems(), factura.getCliente().getIva(ivaDAO), factura.getFacturaType().getFacturaTypeId(), model);
		model.setViewName("/estadisticas/facturadoAUnClientePorFechaForm");
		
		return model;
	}
	
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }
}
