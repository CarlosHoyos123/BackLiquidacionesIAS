package co.com.ias.model.employee;

import co.com.ias.model.employee.exampleData.ExampleData;
import co.com.ias.model.employee.values.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

class EmployeeTest {

    @Test
    public void crearEmpleado(){
        //given
        Employee employee = ExampleData.crearEmployee();
        employee.setId(new IdEmployee(2L));
        employee.setName(new Name("nombre"));
        employee.setIdnumber(new Idnumber("xxxxxxxx"));
        employee.setIndate(new Indate(LocalDate.of(2015,01,02)));
        employee.setCargo(new Cargo("cargo de mantenimiento"));
        employee.setSalary(new Salary(3000000F));
        employee.getId();
        employee.getName();
        employee.getIdnumber();
        employee.getIndate();
        employee.getCargo();
        employee.getSalary();
        //then
        employee.toString();
        }

    @Test
    public void IllegalExceptionsbyShortId(){
        //THEN
        assertThrows(IllegalArgumentException.class, () ->{
            Employee respone = new Employee(
                    new IdEmployee(3L),
                    new Idnumber("284389"),
                    new Name("Carlos"),
                    new Indate(LocalDate.of(2015,01,05)),
                    new Cargo("ingeniero de software"),
                    new Salary(2000000F));
        });
    }

    @Test
    public void IllegalExceptionsByPriorDate(){
        //THEN
        assertThrows(IllegalArgumentException.class, () ->{
            Employee respone = new Employee(
                    new IdEmployee(3L),
                    new Idnumber("28434589"),
                    new Name("Carlos"),
                    new Indate(LocalDate.of(2014,01,05)),
                    new Cargo("ingeniero de software"),
                    new Salary(2000000F));
        });
    }
    @Test
    public void IllegalExceptionsByLowSalary(){
        //THEN
        assertThrows(IllegalArgumentException.class, () ->{
            Employee respone = new Employee(
                    new IdEmployee(3L),
                    new Idnumber("28434589"),
                    new Name("Carlos"),
                    new Indate(LocalDate.of(2014,01,05)),
                    new Cargo("ingeniero de software"),
                    new Salary(2000000F));
        });
    }
}