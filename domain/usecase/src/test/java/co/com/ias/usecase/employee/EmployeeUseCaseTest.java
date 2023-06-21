package co.com.ias.usecase.employee;

import co.com.ias.model.employee.Employee;
import co.com.ias.model.employee.exampleData.ExampleData;
import co.com.ias.model.employee.gateways.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeUseCaseTest {

    @MockBean
    EmployeeRepository employeeRepository;

    @Autowired
    EmployeeUseCase employeeUseCase;

    @Test
    void findAll() {
        //GIVEN
        Mockito.when(employeeRepository.findAll()).thenReturn((Flux<Employee>) ExampleData.employeesList());
        //WHEN
        Flux<Employee> response = employeeUseCase.findAll();
        //THEN

    }

    @Test
    void findEmployee() {
        //GIVEN
        Mockito.when(employeeRepository.findByIdnumber("12345678")).thenReturn(Mono.just(ExampleData.crearEmployee()));
        //WHEN
        Mono<Employee> response = employeeUseCase.findEmployee("12345678");
        //THEN

    }

    @Test
    void findEmployeesByPage() {
        //GIVEN
        Mockito.when(employeeRepository.findAllByPage(5,5)).thenReturn((Flux<Employee>) ExampleData.employeesList());
        //WHEN
        Flux<Employee> response = employeeUseCase.findEmployeesByPage(2,5);
        //THEN

    }

    @Test
    void saveEmployee() {
        //GIVEN
        Mockito.when(employeeRepository.saveEmployee(ExampleData.crearEmployee())).thenReturn(Mono.just(ExampleData.crearEmployee()));
        //WHEN
        Mono<Employee> response = employeeUseCase.saveEmployee(ExampleData.crearEmployee());
        //THEN

    }

    @Test
    void updateSalary() {
        //GIVEN
        Mockito.when(employeeRepository.updateSalary(ExampleData.crearEmployee())).thenReturn(Mono.just(ExampleData.crearEmployee()));
        //WHEN
        Mono<Employee> response = employeeUseCase.updateSalary(ExampleData.crearEmployee());
        //THEN

    }
}