package com.loris.dao;

import java.util.List;

import com.loris.domain.Regimen;

public interface RegimenDAO {
	public Regimen getRegimen(Integer id);
	public List<Regimen> getRegimenes();
}
