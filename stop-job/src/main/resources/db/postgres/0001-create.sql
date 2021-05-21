CREATE TABLE interop.batch_parameters(id serial primary key, name varchar(80) unique not null, property_value varchar(200) not null);
INSERT INTO interop.batch_parameters (id, name, property_value) values (1, 'enabled','false');

CREATE TABLE interop.user (id serial primary key, name varchar(80) unique not null default '');
INSERT INTO interop.user (id, name) select generate_series(1, 20000), 'teste' || generate_series(1, 20000);
