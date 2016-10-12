insert into pk.factura_type (factura_type_id, description) values (5, 'Factura Electr�nica');
insert into pk.factura_type (factura_type_id, description) values (6, 'Nota de Cr�dito Electr�nica');

alter table pk.factura ADD COLUMN CAE varchar(14);
alter table pk.factura ADD COLUMN FECHA_VTO_CAE datetime;

alter table pk.params ADD COLUMN PROX_NUM_FACTURA_ELECTRONICA int(11) NOT NULL;
alter table pk.params ADD COLUMN PROX_NUM_NC_D_ELECTRONICA int(11) NOT NULL;


insert into pk.factura_type (factura_type_id, description) values (6,'Nota Cr�d Elec');

alter table pk.params ADD COLUMN SIGN varchar(255);
alter table pk.params ADD COLUMN TOKEN varchar(1000);
alter table pk.params ADD COLUMN GENERATION_TIME_TA varchar(255);
alter table pk.params ADD COLUMN EXPIRATION_TIME_TA varchar(255);
