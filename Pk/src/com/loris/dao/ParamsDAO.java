package com.loris.dao;

import com.loris.domain.Params;

public interface ParamsDAO {
	public Params getParams();
	public void saveOrUpdateParams(Params params);
}
