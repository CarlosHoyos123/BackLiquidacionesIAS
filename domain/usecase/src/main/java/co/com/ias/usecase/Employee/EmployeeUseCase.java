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
        int DBoffset = (size * (page-1));
        Flux<Employee> list = employeeRepository.findAllByPage(DBoffset, size);
        return list;
    }

    public Mono<Employee> saveEmployee(Employee employee){
        return employeeRepository.saveEmployee(employee);
    };

    public Mono<Employee> updateSalary(Employee employee){
        return employeeRepository.updateSalary(employee);
    }

}
