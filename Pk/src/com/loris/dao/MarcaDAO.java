package com.loris.dao;

import java.util.List;

import com.loris.domain.Marca;

public interface MarcaDAO {
	public Marca getMarca(Integer id);
	public List<Marca> getMarcas();
	public void saveMarca(Marca marca);
	public void deleteMarca(Marca id);
}
