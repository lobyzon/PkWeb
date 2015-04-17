package com.loris.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.loris.domain.Provincia;

public class ProvinciaDAOImpl extends HibernateDaoSupport implements ProvinciaDAO{

	public Provincia getProvincia(Integer id) {
		return getHibernateTemplate().get(Provincia.class, id);
	}
	
	 @SuppressWarnings(value="unchecked")
	public List<Provincia> getProvincias() {		
		return getHibernateTemplate().find("FROM Provincia p ORDER BY p.descripcion");
	}
}