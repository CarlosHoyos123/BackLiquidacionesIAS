CREATE TABLE IF NOT EXISTS tblemployees
(
        id Serial,
        name varchar,
        lastname varchar,
        PRIMARY KEY (id)
);
COMMENT ON TABLE tblemployees IS 'Contiene los empleados a los que se les puede aplicar loquidacion';

--  ******************** fin DDL **************

insert into tblemployees(name, lastname) values('carlos','hoyos');