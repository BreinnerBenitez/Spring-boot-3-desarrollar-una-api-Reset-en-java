alter table medicos add activo tinyint not null;
update medicos set activo = 1