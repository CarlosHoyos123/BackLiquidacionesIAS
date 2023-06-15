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

    public Flux<Employee> findAll(){  /*Searchs with NO pagination*/
        return employeeRepository.findAll();
    };

    public Flux<Employee> findEmployeesByPage(int page, int size) throws IllegalArgumentException{
        Flux<Employee> list = employeeRepository.findAllByPage(page, size);
        return list;
    }

    public Mono<Employee> saveEmployee(Employee employee){
        return employeeRepository.saveEmployee(employee);
    };

}
