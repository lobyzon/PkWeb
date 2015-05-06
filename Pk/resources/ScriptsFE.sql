insert into pk.factura_type (factura_type_id, description) values (5, 'Factura Electrónica');

alter table pk.factura ADD COLUMN CAE varchar(14);
alter table pk.factura ADD COLUMN FECHA_VTO_CAE datetime;