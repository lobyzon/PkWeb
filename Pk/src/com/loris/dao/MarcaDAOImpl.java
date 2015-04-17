package com.loris.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.loris.domain.Marca;

public class MarcaDAOImpl extends HibernateDaoSupport implements MarcaDAO{

	public Marca getMarca(Integer id) {		
		return getHibernateTemplate().get(Marca.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<Marca> getMarcas() {
		return getHibernateTemplate().find("FROM Marca WHERE activo = 'S' ORDER BY id");
	}
	
	public void saveMarca(Marca marca){
		getHibernateTemplate().saveOrUpdate(marca);
	}
	
	public void deleteMarca(Marca marca){
		getHibernateTemplate().delete(marca);
	}
}