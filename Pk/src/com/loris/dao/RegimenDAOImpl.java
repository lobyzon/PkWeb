package com.loris.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.loris.domain.Regimen;

public class RegimenDAOImpl extends HibernateDaoSupport implements RegimenDAO {

	public Regimen getRegimen(Integer id) {
		return getHibernateTemplate().get(Regimen.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<Regimen> getRegimenes() {
		return getHibernateTemplate().find("FROM Regimen r ORDER BY r.id");
	}
}
