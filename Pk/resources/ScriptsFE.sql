insert into pk.factura_type (factura_type_id, description) values (5, 'Factura Electr�nica');
insert into pk.factura_type (factura_type_id, description) values (6, 'Nota de Cr�dito Electr�nica');

alter table pk.factura ADD COLUMN CAE varchar(14);
alter table pk.factura ADD COLUMN FECHA_VTO_CAE datetime;