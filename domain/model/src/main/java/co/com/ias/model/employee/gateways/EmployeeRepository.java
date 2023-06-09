package co.com.ias.model.employee.gateways;

import co.com.ias.model.employee.Employee;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface EmployeeRepository {

    Flux findAll();

    Mono<Employee> saveEmployee(Employee employee);
}
