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

insert into tblemployee(idNumber,name,inDate,cargo,salary) values('1128399593','carlos','2023-01-01','ingeniero',50000);