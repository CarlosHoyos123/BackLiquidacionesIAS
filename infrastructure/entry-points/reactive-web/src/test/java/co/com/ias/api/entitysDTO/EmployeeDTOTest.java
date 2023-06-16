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
        EmployeeDTO employeeDTO = new EmployeeDTO(1L,"28435789","Carlos", LocalDate.of(2023,01,01),"ingemiero",20000F);
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
        employeeDTO.toDomain();
    }

    @Test
    void fromDomain() {
        Employee employee = ExampleData.crearEmployee();
        EmployeeDTO.fromDomain(employee);
    }
}