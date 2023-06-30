package co.com.ias.api;

import co.com.ias.api.entitysDTO.EmployeeDTO;
import co.com.ias.api.entitysDTO.SettlementDTO;
import co.com.ias.api.exampleData.ExampleData;
import co.com.ias.model.employee.Employee;
import co.com.ias.model.settlement.Settlement;
import co.com.ias.usecase.Exceptions.EmptyList;
import co.com.ias.usecase.Exceptions.InvalidPeriodDate;
import co.com.ias.usecase.Exceptions.NotFoundEmployee;
import co.com.ias.usecase.employee.EmployeeUseCase;
import co.com.ias.usecase.salarylog.SalaryLogUseCase;
import co.com.ias.usecase.settlement.SettlementUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.exceptions.base.MockitoException;
import org.mockito.internal.matchers.Any;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.sql.SQLNonTransientException;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willThrow;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {RouterRest.class, Handler.class})
@WebFluxTest
class HandlerTest {

    @MockBean
    private EmployeeUseCase employeeUseCase;

    @MockBean
    private SettlementUseCase settlementUseCase;

    @MockBean
    private SalaryLogUseCase salaryLogUseCase;

    @Autowired
    private WebTestClient client;

    /*@Test
    void salarysUpdates() {
        //GIVEN
        EmployeeDTO employeeDTO = ExampleData.crearEmployeeDTO();
        given(employeeUseCase.updateSalary(any(Employee.class))).willReturn(Mono.just(employeeDTO.toDomain()));
        //WHEN
        client.put().uri("/api/user/salaryUpdate").contentType(MediaType.APPLICATION_JSON).bodyValue(employeeDTO)
        //THEN
        .exchange()
        .expectStatus().isAccepted()
        .expectBody(EmployeeDTO.class);
    }*/

  /*  @Test
    void salarysUpdatesFailure() {
     //GIVEN
     EmployeeDTO employeeDTO = ExampleData.crearEmployeeDTO();
     given(employeeUseCase.updateSalary(any(Employee.class))).willThrow(MockitoException.class);
     //WHEN
     client.put().uri("/api/user/salaryUpdate").contentType(MediaType.APPLICATION_JSON).bodyValue(employeeDTO)
             //THEN
             .exchange()
             .expectStatus().is5xxServerError()
             .expectBody(EmployeeDTO.class);
    }*/

    @Test
    void saveUser() {
    //GIVEN
    EmployeeDTO employeeDTO = ExampleData.crearEmployeeDTO();
    given(employeeUseCase.saveEmployee(any(Employee.class))).willReturn(Mono.just(employeeDTO.toDomain()));
    //WHEN
    client.post().uri("/api/user/create").contentType(MediaType.APPLICATION_JSON).bodyValue(employeeDTO)
    //THEN
            .exchange()
            .expectStatus()
            .isCreated()
            .expectBody(EmployeeDTO.class)
            .equals(employeeDTO.toDomain());
    }

    @Test
    void saveUserFailure() {
     //GIVEN
     EmployeeDTO employeeDTO = ExampleData.crearEmployeeDTO();
     given(employeeUseCase.saveEmployee(any(Employee.class))).willThrow(new IllegalArgumentException());
     //WHEN
     client.post().uri("/api/user/create").contentType(MediaType.APPLICATION_JSON).bodyValue(employeeDTO)
     //THEN
     .exchange()
     .expectStatus()
     .is5xxServerError();
    }

    @Test
    void getEmployeesPage() {
        //GIVEN
        Flux<Employee> lista = ExampleData.crearFluxEmployee();
        when(employeeUseCase.findEmployeesByPage(anyInt(),anyInt())).thenReturn(lista);
        //WHEN
        client.get().uri("/api/users/1-5")
        .exchange()
                .expectStatus().isOk();
        //THEN
    }

    @Test
    void getEmployeesPageNoResults() {
/*     //GIVEN
     Flux<Employee> lista = ExampleData.crearFluxEmployee();
     when(employeeUseCase.findEmployeesByPage(anyInt(),anyInt())).thenReturn(Mono.)
     //WHEN
     client.get().uri("/api/users/6-5")
             .exchange()
             .expectStatus().isNotFound();
     //THEN*/
    }
    @Test
    void getEmployeeByName() {
     //GIVEN
     Flux<Employee> lista = ExampleData.crearFluxEmployee();
     when(employeeUseCase.findEmployeeByName(anyString())).thenReturn(lista);
     //WHEN
     client.get().uri("/api/users/noName")
     //THEN
     .exchange()
     .expectStatus().isOk();
    }

    @Test
    void getEmployeesByDoc() {
     //GIVEN
     Employee employee = ExampleData.crearEmployee();
     when(employeeUseCase.findEmployeeByDocument(anyString())).thenReturn(Mono.just(employee));
     //WHEN
     client.get().uri("/api/user/anyNumber")
             //THEN
             .exchange()
             .expectStatus().isOk();
    }

    @Test
    void updateSalary() throws SQLNonTransientException {
     //GIVEN
     Employee employee = ExampleData.crearEmployee();
     when(employeeUseCase.updateSalary(any(Employee.class))).thenReturn(Mono.just(employee));
     //WHEN
     client.put().uri("/api/user/salaryUpdate")
             //THEN
             .exchange()
             .expectStatus().isOk();
    }

    @Test
    void updateSalaryException() throws SQLNonTransientException {
/*     //GIVEN
     Employee employee = ExampleData.crearEmployee();
     when(employeeUseCase.updateSalary(any(Employee.class))).thenThrow(RuntimeException.class);
     //WHEN
     client.put().uri("/api/user/salaryUpdate")
             //THEN
             .exchange()
             .expectStatus().is5xxServerError();*/
    }

    @Test
    void getSettlement() throws InvalidPeriodDate {
     //GIVEN
     Settlement settlement = co.com.ias.model.settlement.exampleData.ExampleData.crearSettlementUsingBuilder();
     SettlementDTO settlementDTO = SettlementDTO.fromDomain(settlement);
     when(settlementUseCase.MadeSettlement(any(Settlement.class))).thenReturn(Mono.just(settlement));
     //WHEN
     client.post().uri("/api/user/settlement").contentType(MediaType.APPLICATION_JSON).bodyValue(settlementDTO)
             //THEN
             .exchange()
             .expectStatus().isOk();
    }

    @Test
    void getError() {
        //GIVEN

        //WHEN

        //THEN
    }
}