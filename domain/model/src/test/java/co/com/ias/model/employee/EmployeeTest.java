package co.com.ias.model.employee;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import java.time.LocalDate;

class EmployeeTest {

        @Test
    public void crearEmpleado(){
        //given
        Employee employee = new Employee(1L,"28435789","Carlos", LocalDate.of(2023,01,01),"ingemiero",20000F);
        employee.setId(2L);
        employee.setName("nombre");
        employee.setIdnumber("xxxx");
        employee.setIndate(LocalDate.of(2023,02,02));
        employee.setCargo("cargo");
        employee.setSalary(10000F);
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