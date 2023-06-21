package co.com.ias.r2dbc;

import co.com.ias.model.employee.Employee;
import co.com.ias.r2dbc.EntitysDBO.EmployeeDBO;
import co.com.ias.r2dbc.exampleData.ExampleData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static co.com.ias.r2dbc.exampleData.ExampleData.crearEmployee;
import static co.com.ias.r2dbc.exampleData.ExampleData.crearEmployeeDBO;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class MyReactiveRepositoryAdapterTest {

    @InjectMocks
    MyReactiveRepositoryAdapter adapter;

    @Mock
    MyReactiveRepository repository;

    @Test
    void findAll() {
        //given
        Mockito.when(repository.findAll()).thenReturn(ExampleData.crearFluxEmployeeDBO());
        //when
        Flux<Employee> resp = adapter.findAll();
        //then
        assertNotNull(resp);
    }
    @Test
    void findByIdnumber(){
        //given
        Mockito.when(repository.findByIdnumber("12345678")).thenReturn(ExampleData.crearMonoEmployeeDBO());
        //when
        Mono<Employee> response = adapter.findByIdnumber("12345678");
        //then
        assertNotNull(response);
    }

    @Test
    void findAllByPage(){
        //given
        Mockito.when(repository.findAll(10,5)).thenReturn(ExampleData.crearFluxEmployeeDBO());
        //when
        Flux<Employee> response = adapter.findAllByPage(10,5);
        //then
        assertNotNull(response);
    }
    @Test
    void updateSalary(){
        //given
        Mockito.when(repository.save(ExampleData.crearEmployeeDBO())).thenReturn(ExampleData.crearMonoEmployeeDBO());
        //when
        Mono<Employee> response = adapter.saveEmployee(ExampleData.crearEmployee());
        //then
    }

    @Test
    void saveEmployee() {
        //given
        BDDMockito.given(repository.save(ExampleData.crearEmployeeDBO())).willReturn(ExampleData.crearMonoEmployeeDBO());
        //when
        Mono<Employee> resp = adapter.saveEmployee(ExampleData.crearEmployee());

    }
}