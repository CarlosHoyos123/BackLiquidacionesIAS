CREATE TABLE IF NOT EXISTS tblemployee
(
        id          serial,
        idnumber    varchar,
        name        varchar,
        indate      DATE,
        cargo varchar,
        salary float,
        PRIMARY KEY (idNumber)
);
COMMENT ON TABLE tblemployee IS 'Contiene los empleados a los que se les puede aplicar loquidacion';

CREATE TABLE IF NOT EXISTS tblsalaryLogs
(
    id      serial,
    employeeid varchar;
    previus float,
    change  float,
    date        DATE,
    PRIMARY KEY (id)
);
COMMENT ON TABLE tblemployee IS 'Contiene los cambios realizados a los salarios de los empleados';

CREATE OR REPLACE FUNCTION public.salarytrigger()
 RETURNS trigger
 LANGUAGE plpgsql
AS $function$
DECLARE
previusSalary 	FLOAT   := OLD.salary;
newerSalary   	FLOAT   := NEW.salary;
updateDate 		Date    := now();
employee        varchar := OLD.idnumber;
begin
    IF (newerSalary > previusSalary) then
        INSERT INTO tblsalaryLogs(previus,change, date, employeeid) VALUES (previusSalary, newerSalary, updateDate, employee);
    else
    	raise exception 'Salary can not be lower. %', newerSalary;
    END IF;
   return NEW;
END;
$function$;

create or REPLACE TRIGGER trgsalary BEFORE UPDATE
   ON tblemployee
   FOR EACH row
EXECUTE FUNCTION salarytrigger();

--  ******************** fin DDL **************

insert into tblemployee(idNumber,name,inDate,cargo,salary) VALUES
     ('112399593','carlos Andres Hoyos' ,'2023-01-01'   ,'ingeniero de software',50000);
     ('153627894','maira lopez garrido' ,'2015-05-08'   ,'abogada en aduanas',4300000.0),
     ('612095551','MARTHA garrido ortega','2015-03-15'  ,'SERVICIOS GENERALES',3200000.0),
     ('11112598963','miguel del prado'  ,'2015-09-09'   ,'tecnico de soporte de tecnologia',3600000.0),
     ('152784163','ana maria de alvarez','2015-09-09'   ,'abogada en contratacion estatal',3100000.0),
     ('456789542123','adriana atehortua','2015-05-15'   ,'secretariado con enfasis en mercado',3300000.0),
     ('45678954123','carlos alberto'    ,'2015-05-15'   ,'secretariado con efasis en pacientes',3500000.0),
     ('456789523','gabriel angel lopez' ,'2015-05-15'   ,'gerente de area de soporte ',4000000.0),
     ('15347284','angel maria usuga'    ,'2015-05-15'   ,'gerente de compras',4200000.0),
     ('84731023551','MARTHA rodriguez'  ,'2015-03-15'   ,'ingeniero de  mantenimiento',4500000.0),
     ('10399593','Luis amaranto perea'  ,'2015-01-02'   ,'soporte de ventas',2610000.0);