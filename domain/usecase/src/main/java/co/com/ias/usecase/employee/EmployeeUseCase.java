package co.com.ias.usecase.employee;

import co.com.ias.model.employee.Employee;
import co.com.ias.model.employee.gateways.EmployeeRepository;
import co.com.ias.model.salarylog.SalaryLog;
import co.com.ias.model.salarylog.gateways.SalaryLogRepository;
import co.com.ias.usecase.Exceptions.NotFoundEmployee;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class EmployeeUseCase {

    private final EmployeeRepository employeeRepository;

    public Flux<Employee> findAll(){  /*Searchs with NO pagination*/
        return employeeRepository.findAll();
    };

    public Mono<Employee> findEmployee(String id){
        return  employeeRepository.findByIdnumber(id)
                .switchIfEmpty(Mono.error(new NotFoundEmployee("Id no valido")));
    }

    public Flux<Employee> findEmployeesByPage(int page, int size) throws IllegalArgumentException{
        int DBoffset = (size * (page-1));
        return employeeRepository.findAllByPage(DBoffset, size).
                switchIfEmpty(Mono.error(new NotFoundEmployee("cero resultados")));
    }

    public Flux<Employee> findEmployeeByName(String name){
        return employeeRepository.findByName(name).
                switchIfEmpty(Mono.error(new NotFoundEmployee("No existen empleados con ese nombre")));
    }

    public Mono<Employee> findEmployeeByDocument(String document){
        return  employeeRepository.findByDocument(document).
                switchIfEmpty(Mono.error(new NotFoundEmployee("En documento no existe")));
    }

    public Mono<Employee> saveEmployee(Employee employee){
        return employeeRepository.saveEmployee(employee);
    };

    public Mono<Employee> updateSalary(Employee employee){
        return employeeRepository.updateSalary(employee);
    }
}
