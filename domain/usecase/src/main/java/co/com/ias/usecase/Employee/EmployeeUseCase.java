package co.com.ias.usecase.Employee;

import co.com.ias.model.employee.Employee;
import co.com.ias.model.employee.gateways.EmployeeRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RequiredArgsConstructor
public class EmployeeUseCase {

    private final EmployeeRepository employeeRepository;

    public Flux<Employee> findAllEmployees(){
        return employeeRepository.findAll();
    };

}
