package com.loris.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.loris.bean.DateFilter;
import com.loris.domain.Articulo;
import com.loris.domain.Familia;
import com.loris.domain.ItemFactura;
import com.loris.domain.Marca;

public class EstadisticasDAOImpl extends HibernateDaoSupport implements EstadisticasDAO{
	
	@SuppressWarnings("unchecked")
	public List<ItemFactura> getArticulosMasVendidos(DateFilter dateFilter){
		List<Date> fechas = new ArrayList<Date>();
		String query = 	"SELECT sum(item.cantidad), item.articulo.familia.descripcion, " +
						" item.articulo.descripcion, item.articulo.marca.descripcion " +						
						"FROM Factura f " +
						"JOIN f.items item " +
						"WHERE ";
										
		query += "f.fecha >= ? ";
		fechas.add(dateFilter.getFilterFrom());

		query += "AND f.fecha <= ? ";
		fechas.add(dateFilter.getFilterTo());		
		
		query += 	"GROUP BY item.articulo.id " +
					"ORDER BY sum(item.cantidad) DESC ";
		
		List<Object[]> objects = getHibernateTemplate().find(query, fechas.toArray());
		
		List<ItemFactura> items = new ArrayList<ItemFactura>();
		for(int i = 0; i < objects.size() && i < 10; i++){
			Articulo articulo = new Articulo();
			ItemFactura item = new ItemFactura();
			Familia familia = new Familia();
			Marca marca = new Marca();
			Object [] array = objects.get(i);
			
			item.setCantidad(((BigDecimal)array[0]));
			familia.setDescripcion((String)array[1]);
			articulo.setDescripcion((String)array[2]);			
			marca.setDescripcion((String)array[3]);
			articulo.setMarca(marca);
			articulo.setFamilia(familia);
			
			item.setArticulo(articulo);
			items.add(item);
		}
		
		return items;
	}
}
