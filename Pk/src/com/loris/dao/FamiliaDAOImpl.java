package com.loris.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.loris.domain.Familia;

public class FamiliaDAOImpl extends HibernateDaoSupport implements FamiliaDAO {
	
	Transaction transaction = null;
	
	public Familia getFamilia(Integer id) {		
		return getHibernateTemplate().get(Familia.class, id);
	}
	
	@SuppressWarnings(value="unchecked")
	public List<Familia> getFamilias() {		
		return getHibernateTemplate().find("FROM Familia WHERE activo = 'S' ORDER BY id ");
	}

	public void saveFamilia(Familia familia) {		
		getHibernateTemplate().saveOrUpdate(familia);
	}

	public void deleteFamilia(Familia familia) {
		getHibernateTemplate().delete(familia);
	}
	
	@SuppressWarnings(value="unchecked")
	public List<Familia> getFamiliaRecordsPage(Integer pageNumber, Integer recordSize){		
		Query query = this.getFamiliaQuery();
		query.setFirstResult((pageNumber - 1) * recordSize);
		query.setMaxResults(recordSize);		
		
		List<Familia> familias = query.list();
		transaction.commit();
		
		return familias;
	}
	
	private Query getFamiliaQuery(){
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		transaction = session.beginTransaction();
		return session.createQuery("FROM Familia WHERE activo = 'S' ORDER BY id");
	}		
}