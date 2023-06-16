package co.com.ias.api;

import co.com.ias.api.entitysDTO.EmployeeDTO;
import co.com.ias.api.exceptions.ExceptionDTO;
import co.com.ias.model.employee.Employee;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
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
        System.out.println("Entro una peticion getAll");
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
                        .bodyValue(exception.getMessage()));
    }

    public Mono<ServerResponse> saveUser(ServerRequest serverRequest) throws IllegalArgumentException{
        System.out.println("Entro una peticion save");
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
}
