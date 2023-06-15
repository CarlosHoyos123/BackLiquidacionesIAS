package co.com.ias.model.employee.gateways;

import co.com.ias.model.employee.Employee;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface EmployeeRepository {

    Flux<Employee> findAll();

    Flux<Employee> findAllByPage(int page, int size);

    Mono<Employee> saveEmployee(Employee employee);

}
