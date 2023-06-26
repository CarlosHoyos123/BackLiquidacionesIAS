package co.com.ias.api.entitysDTO;

import co.com.ias.api.exampleData.ExampleData;
import co.com.ias.model.employee.Employee;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeDTOTest {

    @Test
    public void crearEmpleadoDBO() {
        //given
        EmployeeDTO employeeDTO = ExampleData.crearEmployeeDTO();
        //when
        employeeDTO.getId();
        employeeDTO.getName();
        employeeDTO.getIdnumber();
        employeeDTO.getIndate();
        employeeDTO.getCargo();
        employeeDTO.getSalary();
        //then
        employeeDTO.toString();
    }

    @Test
    void toDomain() {
        EmployeeDTO employeeDTO = ExampleData.crearEmployeeDTO();
        Employee employee = employeeDTO.toDomain();
    }

    @Test
    void fromDomain() {
        Employee employee = ExampleData.crearEmployee();
        EmployeeDTO employeeDTO = EmployeeDTO.fromDomain(employee);
    }
}