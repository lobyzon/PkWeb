insert into pk.factura_type (factura_type_id, description) values (5, 'Factura Electrónica');
insert into pk.factura_type (factura_type_id, description) values (6, 'Nota de Crédito Electrónica');

alter table pk.factura ADD COLUMN CAE varchar(14);
alter table pk.factura ADD COLUMN FECHA_VTO_CAE datetime;

alter table pk.params ADD COLUMN PROX_NUM_FACTURA_ELECTRONICA int(11) NOT NULL;
alter table pk.params ADD COLUMN PROX_NUM_NC_D_ELECTRONICA int(11) NOT NULL;
alter table pk.params ADD COLUMN SIGN varchar(255);
alter table pk.params ADD COLUMN TOKEN varchar(1000);
alter table pk.params ADD COLUMN GENERATION_TIME_TA varchar(255);
alter table pk.params ADD COLUMN EXPIRATION_TIME_TA varchar(255);

--Sin implementar
create table pk.regimen (regimen_id integer not null, description varchar(128) not null, value varchar (1), primary key (regimen_id)); 
insert into pk.regimen (regimen_id, description, value) values (1, 'Inscripto', 'S');
insert into pk.regimen (regimen_id, description, value) values (2, 'No Inscripto, Monotributista', 'N');

ALTER TABLE pk.cliente CHANGE responsable_insc RESPONSABLE_INSC_ID_FK INT;

--Cambiar el valor de responsable Inscripto S o N al id de Regimen 1 o 2
update pk.cliente set responsable_insc = 1
where id IN (105, 135, 556);

update pk.cliente set responsable_insc = 2
where id IN (105, 135, 556);

alter table Pk.Cliente add index Cliente_Regimen_Index (RESPONSABLE_INSC_ID_FK), add constraint Cliente_Regimen_FK foreign key (RESPONSABLE_INSC_ID_FK) references Pk.Regimen (regimen_id);

update pk.factura_type set description = 'Factura Elect A' where FACTURA_TYPE_ID = 5;
update pk.factura_type set description = 'NotaCred Elect A' where FACTURA_TYPE_ID = 6;

insert into pk.factura_type (FACTURA_TYPE_ID, description) values (7, 'Factura Elect B');
insert into pk.factura_type (FACTURA_TYPE_ID, description) values (8, 'NotaCred Elect B');

alter table Pk.Cliente add constraint Cliente_Regimen_FK foreign key (RESPONSABLE_INSC_ID_FK) references Pk.Regimen (regimen_id);