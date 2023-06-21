package co.com.ias.r2dbc;

import co.com.ias.model.employee.Employee;
import co.com.ias.model.employee.gateways.EmployeeRepository;
import co.com.ias.r2dbc.EntitysDBO.EmployeeDBO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
@AllArgsConstructor
public class MyReactiveRepositoryAdapter implements EmployeeRepository
{
    private  MyReactiveRepository myReactiveRepository;
    @Override
    public Flux findAll() {
        return myReactiveRepository.findAll();
    }

    @Override
    public Mono<Employee> findByIdnumber(String id) {
        Mono<EmployeeDBO> res = myReactiveRepository.findByIdnumber(id);
        return res.map(EmployeeDBO::toDomain);
    }

    @Override
    public Flux findAllByPage(int DBoffset, int size) {
        return myReactiveRepository.findAll(size, DBoffset);
    }

    @Override
    public Mono<Employee> saveEmployee(Employee employee) {
        Mono<EmployeeDBO> res = myReactiveRepository.save(EmployeeDBO.fromDomain(employee) );
        return res.map(EmployeeDBO::toDomain);
    }

    @Override
    public Mono<Employee> updateSalary(Employee employee) {
        Mono<EmployeeDBO> res = myReactiveRepository.save(EmployeeDBO.fromDomain(employee));
        return res.map(EmployeeDBO::toDomain);
    }

}
