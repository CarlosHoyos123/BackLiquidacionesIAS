package co.com.ias.model.employee;

import co.com.ias.model.employee.exampleData.ExampleData;
import co.com.ias.model.employee.values.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

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
}