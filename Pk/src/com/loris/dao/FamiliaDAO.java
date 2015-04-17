package com.loris.dao;

import java.util.List;

import com.loris.domain.Familia;

public interface FamiliaDAO {
	public Familia getFamilia(Integer id);
	public List<Familia> getFamilias();
	public List<Familia> getFamiliaRecordsPage(Integer pageNumber, Integer recordSize);	
	public void saveFamilia(Familia familia);
	public void deleteFamilia(Familia familia);	
}
