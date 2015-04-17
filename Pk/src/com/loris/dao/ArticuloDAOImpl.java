package com.loris.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.loris.bean.CheckBoxes;
import com.loris.bean.Paginator;
import com.loris.domain.Articulo;
import com.loris.domain.Familia;

public class ArticuloDAOImpl extends HibernateDaoSupport implements ArticuloDAO{	
	Transaction transaction = null;
	
	private Query generateQuery(Integer marcaId, Integer familiaId, String articuloId){
		StringBuffer queryS = new StringBuffer("FROM Articulo a where a.activo = 'S' AND ");
		
		Map<String,Object> paramsClear = clearBlanks(marcaId, familiaId, articuloId);		
		
		int i = 1;
		for (Iterator<String> it = paramsClear.keySet().iterator(); it.hasNext(); ++i) {
			String key = it.next();
			if(!key.contains("'"))
				queryS.append(key + paramsClear.get(key));
			else
				queryS.append(key + paramsClear.get(key) + "'");
			
			if(i != paramsClear.size())
				queryS.append(" AND ");
		}
		
		queryS.append(" ORDER BY a.familia.codigo, a.marca.id, a.codigo");
		
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		transaction = session.beginTransaction();
		Query query = session.createQuery(queryS.toString());
		
		return query;
	}
		
	public int getTotalItemsSearch(Integer marcaId, Integer familiaId, String articuloId){
		
		Query query = generateQuery(marcaId, familiaId, articuloId);
		
		int size = query.list().size();
		transaction.commit();
		
		return size;
	}

	
	@SuppressWarnings(value="unchecked")
	public List<Articulo> getFilteredArticulos(Integer pageNumber, Integer recordSize, 
			Integer marcaId, Integer familiaId, String articuloId){
		
		Query query = generateQuery(marcaId, familiaId, articuloId);
		query.setFirstResult((pageNumber - 1) * recordSize);
		query.setMaxResults(recordSize);
				
		List<Articulo> articulos = query.list();
		
		transaction.commit();
		
		return articulos;
	}
	
	@SuppressWarnings(value="unchecked")
	public List<Articulo> getFilteredArticulos(Integer marcaId, Integer familiaId, String articuloId){		
		Query query = generateQuery(marcaId, familiaId, articuloId);		
		
		List<Articulo> articulos = query.list();
		transaction.commit();
		
		return articulos;
	}
	
	@SuppressWarnings(value="unchecked")
	public List<Articulo> getCodigosByMarcaAndFamilia(Integer marcaId, Integer familiaId){
		return getHibernateTemplate().find("SELECT a FROM Articulo a WHERE a.marca.id = ? AND a.familia.codigo = ? AND a.activo = ? " +
			"ORDER BY a.familia.codigo, a.marca.id, a.codigo", new Object[]{marcaId, familiaId, Articulo.ACTIVE_MARK_YES});		
	}
	
	@SuppressWarnings(value="unchecked")
	public List<Familia> getFamiliasByMarca(Integer marcaId){
		return getHibernateTemplate().find("SELECT DISTINCT a.familia FROM Articulo a where a.marca.id = ? AND a.activo = ? " +
				" ORDER BY a.familia.codigo, a.marca.id, a.codigo", new Object[]{marcaId, Articulo.ACTIVE_MARK_YES});		
	}
	
	@SuppressWarnings(value="unchecked")
	public Articulo getArticulo(Articulo articulo, String active) {		
		List<Object> params = new ArrayList<Object>();
		params.add(articulo.getCodigo());
		
		if(articulo.getMarca() == null)
			params.add(articulo.getMarcaId());
		else
			params.add(articulo.getMarca().getId());
		
		if(articulo.getFamilia() == null)
			params.add(articulo.getFamiliaId());
		else			
			params.add(articulo.getFamilia().getCodigo());
		
		params.add(active);
		
		List<Articulo> articulos = getHibernateTemplate().find("FROM Articulo a where a.codigo = ? AND a.marca.id = ? AND a.familia.codigo = ? AND a.activo = ?", params.toArray());
		
		if(articulos != null && !articulos.isEmpty()){
			return articulos.get(0);
		}
		
		return null;
	}
	
	@SuppressWarnings(value="unchecked")
	public Articulo getArticulo(Articulo articulo) {		
		List<Object> params = new ArrayList<Object>();
		params.add(articulo.getCodigo());
		
		if(articulo.getMarca() == null)
			params.add(articulo.getMarcaId());
		else
			params.add(articulo.getMarca().getId());
		
		if(articulo.getFamilia() == null)
			params.add(articulo.getFamiliaId());
		else			
			params.add(articulo.getFamilia().getCodigo());		
		
		List<Articulo> articulos = getHibernateTemplate().find("FROM Articulo a where a.codigo = ? AND a.marca.id = ? AND a.familia.codigo = ? AND a.activo = 'S'", params.toArray());
		
		if(articulos != null && !articulos.isEmpty()){
			return articulos.get(0);
		}
		
		return null;
	}
	
	@SuppressWarnings(value="unchecked")
	public List<Articulo> getArticulosByPage(Paginator paginator) {
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		Query query = session.createQuery("FROM Articulo a WHERE a.activo = 'S' ORDER BY a.codigo");
		List<Articulo> articulos = query.list(); 
		transaction.commit();
		
		return articulos;
	}

	public void saveUpdateArticulo(Articulo articulo) {
		getHibernateTemplate().saveOrUpdate(articulo);		
	}

	public void deleteArticulo(Articulo articulo) {
		getHibernateTemplate().delete(articulo);
	}
	
	private Map<String, Object> clearBlanks(Integer marcaId, Integer familiaId, String articuloId) {
		
		Map<String, Object> paramsClear = new HashMap<String, Object>();
			
		if(marcaId != null)
			paramsClear.put("a.marca.id =  ", marcaId);
		if(familiaId != null)
			paramsClear.put("a.familia.codigo =  ", familiaId);
		if(StringUtils.isNotBlank(articuloId))
			paramsClear.put("a.codigo = '", articuloId);		

		return paramsClear;
	}
	
	@SuppressWarnings("unchecked")
	public List<Articulo> getArticulosByMarca(){
		return getHibernateTemplate().find("FROM Articulo a WHERE a.activo = 'S' ORDER BY a.marca.id, a.familia.codigo, a.codigo");
	}
	
	@SuppressWarnings("unchecked")
	public List<Articulo> getArticulosByFamilia(){
		return getHibernateTemplate().find("FROM Articulo a WHERE a.activo = 'S' ORDER BY a.familia.codigo, a.marca.id, a.codigo");
	}

	@SuppressWarnings("unchecked")
	public List<Articulo> getArticulosByFamiliaParcial(CheckBoxes familiaChecks) {
		List<Object> params = new ArrayList<Object>();
		int [] checks = familiaChecks.getCheckBoxes();
		
		String query = "FROM Articulo a WHERE a.activo = ? AND (a.familia.codigo = ?";
		params.add(Articulo.ACTIVE_MARK_YES);
		params.add(checks[0]);
		
		for(int i = 1; i < checks.length; i++){
			query += " OR a.familia.codigo = ?";
			params.add(checks[i]);
		}
		query += ") ORDER BY a.familia.codigo, a.marca.id, a.codigo";		
		
		return getHibernateTemplate().find(query, params.toArray());
	}

	@SuppressWarnings("unchecked")
	public List<Articulo> getArticulosByMarcaParcial(CheckBoxes marcaChecks) {
		List<Object> params = new ArrayList<Object>();
		int [] checks = marcaChecks.getCheckBoxes();
		
		String query = "FROM Articulo a WHERE  a.activo = ? AND (a.marca.id = ?";
		params.add(Articulo.ACTIVE_MARK_YES);
		params.add(checks[0]);
		
		for(int i = 1; i < checks.length; i++){
			query += " OR a.marca.id = ?";
			params.add(checks[i]);
		}
		query += ") ORDER BY a.marca.id, a.familia.codigo, a.codigo";
		
		return getHibernateTemplate().find(query, params.toArray());
	}
	
	@SuppressWarnings("unchecked")
	public List<Articulo> getArticulosByFamilia(Integer familiaCode) {
		List<Object> params = new ArrayList<Object>();		
		
		String query = "FROM Articulo a WHERE a.activo = ? AND a.familia.codigo = ?";
		params.add(Articulo.ACTIVE_MARK_YES);
		params.add(familiaCode);								
		
		return getHibernateTemplate().find(query, params.toArray());
	}

	@SuppressWarnings("unchecked")
	public List<Articulo> getArticulosByMarca(Integer marcaCode) {
		List<Object> params = new ArrayList<Object>();		
		
		String query = "FROM Articulo a WHERE  a.activo = ? AND a.marca.id = ?";
		params.add(Articulo.ACTIVE_MARK_YES);
		params.add(marcaCode);						
		
		return getHibernateTemplate().find(query, params.toArray());
	}
}