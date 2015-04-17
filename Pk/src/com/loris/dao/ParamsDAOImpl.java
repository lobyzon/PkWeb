package com.loris.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.loris.domain.Params;

public class ParamsDAOImpl extends HibernateDaoSupport implements ParamsDAO {
	
	@SuppressWarnings(value="unchecked")
	public Params getParams(){
		List<Params> params = getHibernateTemplate().find("FROM Params");
		if(params != null && !params.isEmpty()){
			return params.get(0);
		}
		return null;
	}
	
	public void saveOrUpdateParams(Params params){
		getHibernateTemplate().saveOrUpdate(params);
	}
}