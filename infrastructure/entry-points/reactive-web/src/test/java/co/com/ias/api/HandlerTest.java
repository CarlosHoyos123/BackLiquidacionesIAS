package co.com.ias.api;

import co.com.ias.api.entitysDTO.EmployeeDTO;
import co.com.ias.api.exampleData.ExampleData;
import co.com.ias.model.employee.Employee;
import co.com.ias.usecase.employee.EmployeeUseCase;
import co.com.ias.usecase.salarylog.SalaryLogUseCase;
import co.com.ias.usecase.settlement.SettlementUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.matchers.Any;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@WebFluxTest({RouterRest.class, Handler.class})
class HandlerTest {

    @Mock
    private EmployeeUseCase employeeUseCase;

    @Mock
    private SettlementUseCase settlementUseCase;

    @Mock
    private SalaryLogUseCase salaryLogUseCase;

    @InjectMocks
    private WebTestClient client;

    @Test
    void salarysUpdates() {
        //GIVEN
        EmployeeDTO employeeDTO = ExampleData.crearEmployeeDTO();
        when(employeeUseCase.updateSalary(employeeDTO.toDomain())).thenReturn(Mono.just(employeeDTO.toDomain()));
        //WHEN
        client.put().uri("/api/user/salaryUpdate").bodyValue(employeeDTO)
        //THEN
        .exchange()
        .expectStatus().isOk()
        .expectBody(EmployeeDTO.class);
    }

    @Test
    void getEmployeesPage() {
        //GIVEN

        //WHEN

        //THEN
    }

    @Test
    void getEmployeeByName() {
        //GIVEN

        //WHEN

        //THEN
    }

    @Test
    void getEmployeesByDoc() {
        //GIVEN

        //WHEN

        //THEN
    }

    @Test
    void saveUser() {
        //GIVEN

        //WHEN

        //THEN
    }

    @Test
    void updateSalary() {
        //GIVEN

        //WHEN

        //THEN
    }

    @Test
    void getSettlement() {
        //GIVEN

        //WHEN

        //THEN
    }

    @Test
    void getError() {
        //GIVEN

        //WHEN

        //THEN
    }
}