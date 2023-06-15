package co.com.ias.r2dbc.EntitysDBO;

import co.com.ias.model.employee.Employee;
import co.com.ias.r2dbc.exampleData.ExampleData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeDBOTest{

    @Test
    public void crearEmpleadoDBO() {
            //given
            EmployeeDBO employeeDBO = new EmployeeDBO(1L,"28435789","Carlos", LocalDate.of(2023,01,01),"ingemiero",20000F);
            //when
            employeeDBO.setId(2L);
            employeeDBO.setName("nombre");
            employeeDBO.setIdnumber("xxxx");
            employeeDBO.setIndate(LocalDate.of(2023,02,02));
            employeeDBO.setCargo("cargo");
            employeeDBO.setSalary(10000F);
            employeeDBO.getId();
            employeeDBO.getName();
            employeeDBO.getIdnumber();
            employeeDBO.getIndate();
            employeeDBO.getCargo();
            employeeDBO.getSalary();
            //then
            employeeDBO.toString();
    }
        @Test
        void toDomain() {
                Employee employee = EmployeeDBO.toDomain(ExampleData.crearEmployeeDBO());
        }
        @Test
        void fromDomain() {
                Employee employee = ExampleData.crearEmployee();
                EmployeeDBO employeeDBO = EmployeeDBO.fromDomain(employee);
        }
}