package com.loris.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.web.servlet.ModelAndView;

import com.loris.bean.Totales;
import com.loris.domain.FacturaType;
import com.loris.domain.IVA;
import com.loris.domain.ItemFactura;

public class FacturaUtils {	
	
	private static final BigDecimal PORCENTUAL = new BigDecimal(100);
	
	public static Totales makeTotales(HashMap<String, ItemFactura> itemsFactura,
			BigDecimal subTotalResult, BigDecimal ivaResult,
			BigDecimal descuentoTotalResult, IVA currentIVA, Integer facturaType){
		
		Totales totales = new Totales();
		for (Iterator iterator = itemsFactura.keySet().iterator(); iterator.hasNext();) {
			String itemKey = (String) iterator.next();
			ItemFactura itemFactura = itemsFactura.get(itemKey);
			BigDecimal cantidadB;
			if(itemFactura.getCantidad() == null)
				cantidadB = new BigDecimal(0);
			else
				cantidadB = itemFactura.getCantidad();
				
			subTotalResult = subTotalResult.add(itemFactura.getPrecio().multiply(cantidadB));
			descuentoTotalResult = descuentoTotalResult.add(itemFactura.getDescuento().divide(PORCENTUAL).multiply(itemFactura.getPrecio().multiply(cantidadB)));			
		}
		
		totales.setSubTotal(subTotalResult);
		totales.setDescuentoTotal(descuentoTotalResult);		
		totales.setSubTotalDescontado(subTotalResult.subtract(descuentoTotalResult));		
		
		if(!FacturaType.FACTURA_N_TYPE.equals(facturaType)){
			ivaResult = subTotalResult.subtract(descuentoTotalResult).multiply(currentIVA.getIVA().divide(PORCENTUAL));
			totales.setIva(ivaResult);
			totales.setTotal((subTotalResult.add(ivaResult)).subtract(descuentoTotalResult));
		}else
			totales.setTotal(subTotalResult.subtract(descuentoTotalResult));
		
		return totales;
	}
	
	public static void makeTotales(List<ItemFactura> items, IVA currentIVA, Integer facturaType, ModelAndView modelAndView){
		
		Totales totales = new Totales();
		BigDecimal subTotalResult = new BigDecimal(0);
		BigDecimal ivaResult = new BigDecimal(0);
		BigDecimal descuentoTotalResult = new BigDecimal(0);
		
		final NumberFormat decimalFormat = DecimalFormat.getInstance();
		decimalFormat.setGroupingUsed(false);
		decimalFormat.setMaximumFractionDigits(2);
		decimalFormat.setMinimumFractionDigits(2);
		
		for(ItemFactura item: items){
			BigDecimal cantidadB;
			if(item.getCantidad() == null)
				cantidadB = new BigDecimal(0);
			else
				cantidadB = item.getCantidad();
				
			subTotalResult = subTotalResult.add(item.getPrecio().multiply(cantidadB));
			descuentoTotalResult = descuentoTotalResult.add(item.getDescuento().divide(PORCENTUAL).multiply(item.getPrecio().multiply(cantidadB)));
		}		
		
		totales.setSubTotal(subTotalResult);
		totales.setDescuentoTotal(descuentoTotalResult);		
		totales.setSubTotalDescontado(subTotalResult.subtract(descuentoTotalResult));		
		
		if(!FacturaType.FACTURA_N_TYPE.equals(facturaType)){
			ivaResult = subTotalResult.subtract(descuentoTotalResult).multiply(currentIVA.getIVA().divide(PORCENTUAL));
			totales.setIva(ivaResult);
			totales.setTotal((subTotalResult.add(ivaResult)).subtract(descuentoTotalResult));
		}else
			totales.setTotal(subTotalResult.subtract(descuentoTotalResult));
		
		modelAndView.addObject("subTotal", totales.getSubTotal());
		modelAndView.addObject("descuentoTotal", totales.getDescuentoTotal());		
		modelAndView.addObject("iva", totales.getIva());
		modelAndView.addObject("total", decimalFormat.format(totales.getTotal()));
	}
}
