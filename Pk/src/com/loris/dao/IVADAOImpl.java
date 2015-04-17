package com.loris.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.loris.domain.IVA;

public class IVADAOImpl extends HibernateDaoSupport implements IVADAO{	

	public void saveUpdateIVA(IVA iva) {
		getHibernateTemplate().saveOrUpdate(iva);
	}

	@SuppressWarnings(value="unchecked")
	public IVA getIVAInscripto() {
		List<IVA> iva = getHibernateTemplate().find("FROM IVA where inscripto = ?", Boolean.TRUE);
		
		if(iva != null && !iva.isEmpty()){
			return iva.get(0);
		}
		return null;
	}
	
	@SuppressWarnings(value="unchecked")
	public IVA getIVANoInscripto() {
		List<IVA> iva = getHibernateTemplate().find("FROM IVA where inscripto = ?", Boolean.FALSE);
		
		if(iva != null && !iva.isEmpty()){
			return iva.get(0);
		}
		return null;
	}
}