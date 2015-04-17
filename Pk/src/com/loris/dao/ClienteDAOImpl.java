package com.loris.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.loris.domain.Cliente;

public class ClienteDAOImpl extends HibernateDaoSupport implements ClienteDAO{
	
	Transaction transaction = null;
	
	public Cliente getCliente(Integer id) {	
		return getHibernateTemplate().get(Cliente.class, id);
	}

	public void saveUpdateCliente(Cliente cliente) { 
		getHibernateTemplate().saveOrUpdate(cliente);
	}

	public void deleteCliente(Cliente cliente) {
		getHibernateTemplate().delete(cliente);
	}

	@SuppressWarnings(value="unchecked")
	public List<Cliente> getClientes() {		
		return getHibernateTemplate().find("FROM Cliente ORDER BY id");
	}	
	
	@SuppressWarnings(value="unchecked")
	public List<Cliente> getClienteRecordsPage(Integer pageNumber, Integer recordSize){		
		Query query = this.getClienteQuery();
		query.setFirstResult((pageNumber - 1) * recordSize);
		query.setMaxResults(recordSize);		
		
		List<Cliente> clientes = query.list();
		
		transaction.commit();
		
		return clientes;
	}
	
	private Query getClienteQuery(){
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		transaction = session.beginTransaction();
		return session.createQuery("FROM Cliente order by id");
	}
}