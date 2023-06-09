package co.com.ias.api;

import co.com.ias.model.employee.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import co.com.ias.usecase.Employee.EmployeeUseCase;

@Component
@RequiredArgsConstructor
public class Handler {

    private  final EmployeeUseCase employeeUseCase;

    public Mono<ServerResponse> getAllEmployees(ServerRequest serverRequest) {
        Flux<Employee> res = employeeUseCase.findAllEmployees();
        return res
                .collectList()
                .flatMap(employeesList -> ServerResponse
                        .status(HttpStatus.OK)
                        .bodyValue(employeesList))
                .onErrorResume(exception -> ServerResponse
                        .unprocessableEntity()
                        .bodyValue(exception.getMessage()));
        //return ServerResponse.ok().bodyValue("");
    }

    public Mono<ServerResponse> listenGETOtherUseCase(ServerRequest serverRequest) {
        // useCase2.logic();
        return ServerResponse.ok().bodyValue("");
    }

    public Mono<ServerResponse> listenPOSTUseCase(ServerRequest serverRequest) {
        // usecase.logic();
        return ServerResponse.ok().bodyValue("");
    }
}
