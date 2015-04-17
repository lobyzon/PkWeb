package com.loris.dao;

import java.util.List;

import com.loris.bean.CheckBoxes;
import com.loris.bean.Paginator;
import com.loris.domain.Articulo;
import com.loris.domain.Familia;

public interface ArticuloDAO {	
	public Articulo getArticulo(Articulo articulo, String activo);
	public Articulo getArticulo(Articulo articulo);
	public List<Familia> getFamiliasByMarca(Integer marcaId);
	public List<Articulo> getCodigosByMarcaAndFamilia(Integer marcaId, Integer familiaId);
	public List<Articulo> getArticulosByPage(Paginator paginator);
	public List<Articulo> getFilteredArticulos(Integer pageNumber, Integer recordSize, Integer marcaId, Integer familiaId, String articuloId);
	public List<Articulo> getFilteredArticulos(Integer marcaId, Integer familiaId, String articuloId);
	public List<Articulo> getArticulosByMarca();
	public List<Articulo> getArticulosByMarca(Integer marcaCode);
	public List<Articulo> getArticulosByFamilia();
	public List<Articulo> getArticulosByFamilia(Integer familiaCode);
	public List<Articulo> getArticulosByFamiliaParcial(CheckBoxes familiaChecks);
	public List<Articulo> getArticulosByMarcaParcial(CheckBoxes marcaChecks);
	public int getTotalItemsSearch(Integer marcaId, Integer familiaId, String articuloId);
	public void saveUpdateArticulo(Articulo articulo);
	public void deleteArticulo(Articulo articulo);
}
