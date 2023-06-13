CREATE TABLE IF NOT EXISTS tblemployee
(
        id serial,
        idnumber varchar,
        name varchar,
        indate DATE,
        cargo varchar,
        salary float,
        PRIMARY KEY (idNumber)
);
COMMENT ON TABLE tblemployee IS 'Contiene los empleados a los que se les puede aplicar loquidacion';

--  ******************** fin DDL **************

insert into tblemployee(idNumber,name,inDate,cargo,salary) values('1128399593','carlos','2023-01-01','ingeniero',50000);