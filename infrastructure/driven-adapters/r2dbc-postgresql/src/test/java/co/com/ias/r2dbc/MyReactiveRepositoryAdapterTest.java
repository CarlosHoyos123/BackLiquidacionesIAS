package co.com.ias.r2dbc;

import co.com.ias.model.employee.Employee;
import co.com.ias.r2dbc.EntitysDBO.EmployeeDBO;
import co.com.ias.r2dbc.exampleData.ExampleData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import org.mockito.ArgumentMatcher;
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
        Mockito.when(repository.findAll()).thenReturn(ExampleData.crearFluxEmployee());
        //when
        Flux<Employee> resp = adapter.findAll();
        //then
        assertNotNull(resp);
    }

 /*   @Test
    void saveEmployee() {
        //given
        Mockito.lenient().when(repository.save(crearEmployeeDBO().fromDomain(crearEmployee()))).thenReturn(ExampleData.crearMonoEmployeeDBO());
        //when
        Mono<Employee> resp = adapter.saveEmployee(crearEmployee());

    }*/
}