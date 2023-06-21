package co.com.ias.api;

import co.com.ias.api.entitysDTO.EmployeeDTO;
import co.com.ias.api.entitysDTO.SettlementDTO;
import co.com.ias.api.exceptions.ExceptionDTO;
import co.com.ias.model.employee.Employee;
import co.com.ias.model.salarylog.SalaryLog;
import co.com.ias.usecase.salarylog.SalaryLogUseCase;
import co.com.ias.usecase.settlement.SettlementUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import co.com.ias.usecase.employee.EmployeeUseCase;

@Component
@RequiredArgsConstructor
public class Handler {

    private final EmployeeUseCase employeeUseCase;
    private final SettlementUseCase settlementUseCase;
    private final SalaryLogUseCase salaryLogUseCase;

    public Mono<ServerResponse> salarysUpdates(ServerRequest serverRequest) {
        Flux<SalaryLog> res = salaryLogUseCase.findSalarysUpdates(
                String.valueOf(serverRequest.pathVariable("id")));
        return res.collectList().flatMap(salarysList -> ServerResponse
                                            .status(HttpStatus.OK)
                                            .bodyValue(salarysList))
                .onErrorResume(exception -> ServerResponse
                        .unprocessableEntity()
                        .bodyValue(
                                new ExceptionDTO(HttpStatus.NO_CONTENT.value(), exception.getMessage())));
    }

    public Mono<ServerResponse> getAllEmployees(ServerRequest serverRequest) {
        //Flux<Employee> res = employeeUseCase.findAll(); /*Searchs with NO pagination*/
        Flux<Employee> res = employeeUseCase.findEmployeesByPage(/*Search with pagination*/
                Integer.valueOf(serverRequest.pathVariable("page")),
                Integer.valueOf(serverRequest.pathVariable("size")));
        return res
                .collectList()
                .flatMap(employeesList -> ServerResponse
                        .status(HttpStatus.OK)
                        .bodyValue(employeesList))
                .onErrorResume(exception -> ServerResponse
                        .unprocessableEntity()
                        .bodyValue(
                                new ExceptionDTO(HttpStatus.NO_CONTENT.value(), exception.getMessage())));
    }

    public Mono<ServerResponse> saveUser(ServerRequest serverRequest){
        return serverRequest.bodyToMono(EmployeeDTO.class)
                .flatMap(EmployeeDTO -> employeeUseCase.saveEmployee(EmployeeDTO.toDomain()))
                .flatMap(userSaved -> ServerResponse
                        .status(HttpStatus.OK)
                        .bodyValue(EmployeeDTO.fromDomain(userSaved)))
                .onErrorResume(exception -> ServerResponse
                        .unprocessableEntity()
                        .bodyValue(
                            new ExceptionDTO(HttpStatus.BAD_REQUEST.value(), exception.getMessage())));
    }

    public Mono<ServerResponse> updateSalary(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(EmployeeDTO.class)
                .flatMap(employeeDTO -> employeeUseCase.updateSalary(employeeDTO.toDomain()))
                .flatMap(employeeSalaryUpdated -> ServerResponse
                        .status(HttpStatus.ACCEPTED)
                        .bodyValue(EmployeeDTO.fromDomain(employeeSalaryUpdated)))
                .onErrorResume(exception -> ServerResponse
                        .unprocessableEntity()
                        .bodyValue(
                                new ExceptionDTO(HttpStatus.NOT_MODIFIED.value(), exception.getMessage())));
    }

    public Mono<ServerResponse> getSettlement(ServerRequest serverRequest){
        return serverRequest.bodyToMono(SettlementDTO.class)
                .flatMap(settlement -> settlementUseCase.MadeSettlement(settlement.toDomain()))
                .flatMap(settlementMade -> ServerResponse
                        .status(HttpStatus.OK)
                        .bodyValue(SettlementDTO.fromDomain(settlementMade)))
                .onErrorResume(exception -> ServerResponse
                        .unprocessableEntity()
                        .bodyValue(
                                new ExceptionDTO(HttpStatus.NOT_MODIFIED.value(), exception.getMessage())));
    }

}
