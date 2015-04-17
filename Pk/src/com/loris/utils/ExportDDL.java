package com.loris.utils;

import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

import com.loris.domain.Articulo;
import com.loris.domain.Cliente;
import com.loris.domain.Direccion;
import com.loris.domain.Factura;
import com.loris.domain.FacturaType;
import com.loris.domain.Familia;
import com.loris.domain.IVA;
import com.loris.domain.ItemFactura;
import com.loris.domain.Marca;
import com.loris.domain.Params;
import com.loris.domain.Provincia;

public class ExportDDL {
	
	public static void main(String[] args) {
        final AnnotationConfiguration configuration = new AnnotationConfiguration();
        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        configuration.addAnnotatedClass(Marca.class);
        configuration.addAnnotatedClass(Familia.class);
        configuration.addAnnotatedClass(Articulo.class);
        configuration.addAnnotatedClass(Cliente.class);
        configuration.addAnnotatedClass(Direccion.class);
        configuration.addAnnotatedClass(Provincia.class);
        configuration.addAnnotatedClass(ItemFactura.class);
        configuration.addAnnotatedClass(Factura.class);
        configuration.addAnnotatedClass(FacturaType.class);
        configuration.addAnnotatedClass(IVA.class);
        configuration.addAnnotatedClass(Params.class);
       
        SchemaExport export = new SchemaExport(configuration);
        export.setOutputFile("D:\\Desarrollo\\MySql\\Scripts\\HibernateSchemaExport.sql");
        export.setDelimiter(";\r\n");
        export.create(true, false);
	} 
}
