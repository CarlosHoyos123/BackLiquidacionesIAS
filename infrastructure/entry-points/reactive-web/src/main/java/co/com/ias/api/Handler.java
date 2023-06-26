package co.com.ias.api;

import co.com.ias.api.entitysDTO.EmployeeDTO;
import co.com.ias.api.entitysDTO.SettlementDTO;
import co.com.ias.api.exception.Personalized.EmployeeUnvalid;
import co.com.ias.api.exception.Personalized.SettlementDateInvalid;
import co.com.ias.api.exception.Personalized.SettlementUnvalid;
import co.com.ias.api.exceptions.ExceptionDTO;
import co.com.ias.model.employee.Employee;
import co.com.ias.model.salarylog.SalaryLog;
import co.com.ias.usecase.Exceptions.InvalidPeriodDate;
import co.com.ias.usecase.Exceptions.NotFoundEmployee;
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

import java.util.function.Predicate;

@Component
@RequiredArgsConstructor
public class Handler {

    private final EmployeeUseCase employeeUseCase;
    private final SettlementUseCase settlementUseCase;
    private final SalaryLogUseCase salaryLogUseCase;

    public Mono<ServerResponse> salarysUpdates(ServerRequest serverRequest) {
        return salaryLogUseCase.findSalarysUpdates(
                String.valueOf(serverRequest.pathVariable("id")))
                    .collectList()
                    .flatMap(salarysList -> ServerResponse
                        .status(HttpStatus.OK)
                        .bodyValue(salarysList));
    }

    public Mono<ServerResponse> getEmployeesPage(ServerRequest serverRequest) {
        //Flux<Employee> res = employeeUseCase.findAll(); /*Searchs with NO pagination*/
        return employeeUseCase.findEmployeesByPage(/*Search with pagination*/
                        Integer.valueOf(serverRequest.pathVariable("page")),
                        Integer.valueOf(serverRequest.pathVariable("size")))
                .collectList()
                .flatMap(employeesList -> ServerResponse
                        .status(HttpStatus.OK)
                        .bodyValue(employeesList))
                .onErrorResume(exception -> ServerResponse
                        .unprocessableEntity()
                        .bodyValue(
                                new ExceptionDTO(HttpStatus.NO_CONTENT.value(), exception.getMessage())));
    }

    public Mono<ServerResponse> getEmployeeByName(ServerRequest serverRequest) {
        return employeeUseCase.findEmployeeByName(
                serverRequest.pathVariable("name"))
                    .collectList().flatMap(employeeList -> ServerResponse
                        .status(HttpStatus.OK)
                        .bodyValue(employeeList));
    }

    public Mono<ServerResponse> getEmployeesByDoc(ServerRequest serverRequest) {

        return employeeUseCase.findEmployeeByDocument(
                serverRequest.pathVariable("document"))
                    .flatMap(employee -> ServerResponse
                            .status(HttpStatus.OK)
                            .bodyValue(EmployeeDTO.fromDomain(employee)));
    }


    public Mono<ServerResponse> saveUser(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(EmployeeDTO.class)
                .flatMap(EmployeeDTO -> {
                    try{return employeeUseCase.saveEmployee(EmployeeDTO.toDomain());}
                    catch (Exception exception)
                    {return Mono.error(exception);}
                })
                .flatMap(userSaved -> ServerResponse
                        .status(HttpStatus.OK)
                        .bodyValue(EmployeeDTO.fromDomain(userSaved)));
    }

    public Mono<ServerResponse> updateSalary(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(EmployeeDTO.class)
                .flatMap(employeeDTO -> {
                        try{return employeeUseCase.updateSalary(employeeDTO.toDomain());}
                        catch (Exception exception)
                        {return Mono.error(exception);}
                })
                .flatMap(employeeSalaryUpdated -> ServerResponse
                        .status(HttpStatus.ACCEPTED)
                        .bodyValue(EmployeeDTO.fromDomain(employeeSalaryUpdated)));
    }

    public Mono<ServerResponse> getSettlement(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(SettlementDTO.class)
                    .flatMap(settlement -> {
                        try {return settlementUseCase.MadeSettlement(settlement.toDomain());}
                        catch (InvalidPeriodDate  exception)
                            {return Mono.error(exception);}
                    })
                    .flatMap(settlementMade -> ServerResponse
                                    .status(HttpStatus.OK)
                                    .bodyValue(SettlementDTO.fromDomain(settlementMade)));
    }

    public Mono<ServerResponse> getError(ServerRequest serverRequest) {
        return Mono.error(new InvalidPeriodDate("Access denied"));
    }
}
