insert into pk.factura_type (factura_type_id, description) values (5, 'Factura Electrónica');
insert into pk.factura_type (factura_type_id, description) values (6, 'Nota de Crédito Electrónica');

alter table pk.factura ADD COLUMN CAE varchar(14);
alter table pk.factura ADD COLUMN FECHA_VTO_CAE datetime;

alter table pk.params ADD COLUMN PROX_NUM_FACTURA_ELECTRONICA int(11) NOT NULL;
alter table pk.params ADD COLUMN PROX_NUM_NC_D_ELECTRONICA int(11) NOT NULL;
