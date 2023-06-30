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
import static org.mockito.ArgumentMatchers.*;

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
    void findByEmployeeidTest (){
/*        //GIVEN
            Mockito.when(repository.findAll()).thenReturn(ExampleData.crearFluxEmployeeDBO());
        //WHEN
            Flux<EmployeeDBO> response = adapter.findAll();
        //THEN
            response.subscribe(emp -> {
                assertEquals(emp.getCargo(),"ingeniero de soft");
            });*/
    }

    @Test
    void findByNameTest (){

    }

    @Test
    void findByDocumentTest (){

    }
    @Test
    void findByIdnumber(){
        //given
        Mockito.when(repository.findByIdnumber(anyString())).thenReturn(ExampleData.crearMonoEmployeeDBO());
        //when
        Mono<Employee> response = adapter.findByIdnumber("12345678");
        //then
        assertNotNull(response);
    }

    @Test
    void findAllByPage(){
        //given
        Mockito.when(repository.findAll(anyInt(),anyInt())).thenReturn(ExampleData.crearFluxEmployeeDBO());
        //when
        Flux<Employee> response = adapter.findAllByPage(10,5);
        //then
        assertNotNull(response);
    }
    @Test
    void updateSalary(){
        //given
        Mockito.when(repository.save(any(EmployeeDBO.class))).thenReturn(ExampleData.crearMonoEmployeeDBO());
        //when
        Mono<Employee> response = adapter.saveEmployee(ExampleData.crearEmployee());
        //then
        response.subscribe(info -> System.out.println(info.toString()));
    }

    @Test
    void saveEmployee() {
        //given
        BDDMockito.given(repository.save(any(EmployeeDBO.class))).willReturn(ExampleData.crearMonoEmployeeDBO());
        //when
        Mono<Employee> resp = adapter.saveEmployee(ExampleData.crearEmployee());

    }
}