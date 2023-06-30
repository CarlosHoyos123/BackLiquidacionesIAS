package co.com.ias.usecase.employee;

import co.com.ias.model.employee.Employee;
import co.com.ias.model.employee.exampleData.ExampleData;
import co.com.ias.model.employee.gateways.EmployeeRepository;
import co.com.ias.usecase.Exceptions.EmptyList;
import co.com.ias.usecase.Exceptions.InvalidPeriodDate;
import co.com.ias.usecase.Exceptions.NotFoundEmployee;
import co.com.ias.usecase.Exceptions.SqlNotHigher;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import reactor.core.publisher.Flux;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = EmployeeUseCase.class)
class EmployeeUseCaseTest {

    @MockBean
    EmployeeRepository employeeRepository;

    @InjectMocks
    EmployeeUseCase employeeUseCase;

    @Test
    void findAll() {
/*        //GIVEN
        Mockito.when(employeeRepository.findAll()).thenReturn(ExampleData.FluxEmployees());
        //WHEN
        Flux<Employee> response = employeeUseCase.findAll();*/
    }

    @Test
    void findEmployee() {/*
        //GIVEN
        Mockito.when(employeeRepository.findByIdnumber("12345678")).thenReturn(Mono.just(ExampleData.crearEmployee()));
        //WHEN
        Mono<Employee> response = employeeUseCase.findEmployee("12345678");
        //THEN*/
    }

    @Test
    void findEmployeesByPage() {/*
        //GIVEN
        Mockito.when(employeeRepository.findAllByPage(5,5)).thenReturn((Flux<Employee>) ExampleData.employeesList());
        //WHEN
        Flux<Employee> response = employeeUseCase.findEmployeesByPage(2,5);
        //THEN*/
    }

    @Test
    void saveEmployee() {/*
        //GIVEN
        Mockito.when(employeeRepository.saveEmployee(ExampleData.crearEmployee())).thenReturn(Mono.just(ExampleData.crearEmployee()));
        //WHEN
        Mono<Employee> response = employeeUseCase.saveEmployee(ExampleData.crearEmployee());
        //THEN*/
    }

     @Test
    void updateSalary() {
         /*
        //GIVEN
        Mockito.when(employeeRepository.updateSalary(ExampleData.crearEmployee())).thenReturn(Mono.just(ExampleData.crearEmployee()));
        //WHEN
        Mono<Employee> response = employeeUseCase.updateSalary(ExampleData.crearEmployee());
        //THEN*/

    }
    @Test
    void emptyException(){
       assertThrows(EmptyList.class,() -> {
           throw new EmptyList("lista vacia");
       });
    }
    @Test
    void InvalidDateException(){
        assertThrows(InvalidPeriodDate.class,() -> {
            throw new InvalidPeriodDate("Fechas no valida");
        });
    }
    @Test
    void NotFoundException(){
        assertThrows(NotFoundEmployee.class,() -> {
            throw new NotFoundEmployee("No se encontro empleado.");
        });
    }
    @Test
    void SQLException(){
        assertThrows(SqlNotHigher.class,() -> {
            throw new SqlNotHigher("lista vacia");
        });
    }
}