package com.loris.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.loris.bean.DateFilter;
import com.loris.domain.Factura;
import com.loris.domain.FacturaType;

public class FacturaDAOImpl extends HibernateDaoSupport implements FacturaDAO{

	public void saveUpdateFactura(Factura factura) {
		getHibernateTemplate().saveOrUpdate(factura);
	}

	public void deleteFactura(Factura factura) {
		getHibernateTemplate().delete(factura);
	}

	public Factura getFactura(Integer idFactura) {
		return getHibernateTemplate().get(Factura.class, idFactura);
	}
	
	@SuppressWarnings(value="unchecked")
	public Factura getFacturaByNumberAndType(Integer nroFactura, Integer facturaType){
		String queryS = "FROM Factura WHERE nroFactura = ? AND facturaType.facturaTypeId <> " + FacturaType.FACTURA_ANULADA_TYPE;
		List<Factura> facturas = new ArrayList<Factura>();							
		
		if(facturaType != null && facturaType > 0){
			queryS += " AND facturaType.facturaTypeId = ? ";			
			
			facturas = getHibernateTemplate().find(queryS, new Object[] {nroFactura, facturaType});
		}else{												
			facturas = getHibernateTemplate().find(queryS, new Object[] {nroFactura});
		}			
		
		if(!facturas.isEmpty())
			return facturas.get(0);
		
		return null;
	}
	
	@SuppressWarnings(value="unchecked")
	public List<Factura> getFacturasClienteByDateAndType(Integer clientCode, DateFilter dateFilter, Integer facturaType){
		Object params [] = new Object[]{clientCode, facturaType, dateFilter.getFilterFrom(), dateFilter.getFilterTo()};
		String queryS = "FROM Factura f WHERE cliente.id = ? AND (facturaType.facturaTypeId = ? ";
		if(!FacturaType.FACTURA_N_TYPE.equals(facturaType)){
			queryS += "OR facturaType.facturaTypeId = ?) ";
			params = new Object[]{clientCode, facturaType, FacturaType.FACTURA_TYPE_ELECTRONIC, dateFilter.getFilterFrom(), dateFilter.getFilterTo()};
		}else{
			queryS += ")";
		}
		queryS += "AND fecha >= ? AND fecha <= ?";
		
		return getHibernateTemplate().find(queryS, params);
	}
	
	@SuppressWarnings(value="unchecked")
	public BigDecimal getTotalSubtotalesClienteByDateAndType(Integer clientCode, DateFilter dateFilter, Integer facturaType){
		Object params [] = new Object[]{clientCode, facturaType, dateFilter.getFilterFrom(), dateFilter.getFilterTo()};
		String queryS = "SELECT sum(subTotal) FROM Factura f WHERE cliente.id = ? AND (facturaType.facturaTypeId = ? ";
		if(!FacturaType.FACTURA_N_TYPE.equals(facturaType)){
			queryS += "OR facturaType.facturaTypeId = ?) ";
			params = new Object[]{clientCode, facturaType, FacturaType.FACTURA_TYPE_ELECTRONIC, dateFilter.getFilterFrom(), dateFilter.getFilterTo()};
		}else{
			queryS += ")";
		}
		queryS += "AND fecha >= ? AND fecha <= ?";
		
		List<BigDecimal> list = getHibernateTemplate().find(queryS, params);
		if(list != null && !list.isEmpty())
			return list.get(0);
		return new BigDecimal(0);
	}
}