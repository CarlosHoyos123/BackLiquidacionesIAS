package co.com.ias.model.employee.gateways;

import reactor.core.publisher.Flux;

public interface EmployeeRepository {

    Flux findAll();
}
