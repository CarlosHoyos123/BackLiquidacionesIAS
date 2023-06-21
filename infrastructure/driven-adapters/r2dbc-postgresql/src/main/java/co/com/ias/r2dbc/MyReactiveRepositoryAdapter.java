package co.com.ias.r2dbc;

import co.com.ias.model.employee.Employee;
import co.com.ias.model.employee.gateways.EmployeeRepository;
import co.com.ias.model.salarylog.SalaryLog;
import co.com.ias.model.salarylog.gateways.SalaryLogRepository;
import co.com.ias.r2dbc.EntitysDBO.EmployeeDBO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
@AllArgsConstructor
public class MyReactiveRepositoryAdapter implements EmployeeRepository, SalaryLogRepository
{
    private  MyReactiveRepository myReactiveRepository;
    private SalaryLogsRepository salaryLogsRepository;

    @Override
    public Flux findAll() {
        return myReactiveRepository.findAll();
    }

    @Override
    public Flux findByEmployeeid(String id) {
        return salaryLogsRepository.findByEmployeeid(id);
    }

    @Override
    public Flux findByName(String name) {
        return myReactiveRepository.findByName(name);
    }

    @Override
    public Mono<Employee> findByDocument(String document) {
        return myReactiveRepository.findByDocument(document).map(EmployeeDBO::toDomain);
    }

    @Override
    public Mono<Employee> findByIdnumber(String id) {
        return  myReactiveRepository.findByIdnumber(id).map(EmployeeDBO::toDomain);
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
