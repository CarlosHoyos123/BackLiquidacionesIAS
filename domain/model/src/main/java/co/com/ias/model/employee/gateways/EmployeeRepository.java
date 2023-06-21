package co.com.ias.model.employee.gateways;

import co.com.ias.model.employee.Employee;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface EmployeeRepository {

    Flux<Employee> findAll();

    Mono<Employee> findByIdnumber(String id);

    Flux<Employee> findAllByPage(int DBoffset, int size);

    Mono<Employee> saveEmployee(Employee employee);

    Mono<Employee> updateSalary(Employee employee);

}
