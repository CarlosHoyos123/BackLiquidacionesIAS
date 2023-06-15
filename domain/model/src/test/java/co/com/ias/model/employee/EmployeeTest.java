package co.com.ias.model.employee;

import co.com.ias.model.employee.values.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import java.time.LocalDate;

class EmployeeTest {

        @Test
    public void crearEmpleado(){
        //given
        Employee employee = new Employee(IdEmployee.builder().value(1L).build(),
                new Idnumber("28435789"),
                new Name("Carlos"),
                new Indate(LocalDate.of(2023,01,01)),
                new Cargo("ingemiero"),
                new Salary(20000F));
        employee.setId(IdEmployee.builder().value(2L).build());
        employee.setName(new Name("nombre"));
        employee.setIdnumber(new Idnumber("xxxx"));
        employee.setIndate(new Indate(LocalDate.of(2023,02,02)));
        employee.setCargo(new Cargo("cargo"));
        employee.setSalary(new Salary(10000F));
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