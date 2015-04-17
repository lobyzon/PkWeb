package com.loris.dao;

import java.util.List;

import com.loris.domain.Provincia;

public interface ProvinciaDAO {
	public Provincia getProvincia(Integer id);
	public List<Provincia> getProvincias();
}
