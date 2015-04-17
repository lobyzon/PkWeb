package com.loris.dao;

import java.util.List;

import com.loris.bean.DateFilter;
import com.loris.domain.ItemFactura;

public interface EstadisticasDAO {
	public List<ItemFactura> getArticulosMasVendidos(DateFilter dateFilter);
}
