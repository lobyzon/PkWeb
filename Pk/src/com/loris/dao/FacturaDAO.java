package com.loris.dao;

import java.math.BigDecimal;
import java.util.List;

import com.loris.bean.DateFilter;
import com.loris.domain.Factura;

public interface FacturaDAO {
	public void saveUpdateFactura(Factura factura);
	public void deleteFactura(Factura factura);
	public Factura getFactura(Integer idFactura);
	public Factura getFacturaByNumberAndType(Integer nroFactura, Integer factuaType);
	public List<Factura> getFacturasClienteByDateAndType(Integer clientCode, DateFilter dateFilter, Integer facturaType);
	public BigDecimal getTotalSubtotalesClienteByDateAndType(Integer clientCode, DateFilter dateFilter, Integer facturaType);
}
