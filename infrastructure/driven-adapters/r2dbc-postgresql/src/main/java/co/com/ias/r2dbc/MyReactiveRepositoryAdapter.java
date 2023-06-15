package co.com.ias.r2dbc;

import co.com.ias.model.employee.Employee;
import co.com.ias.model.employee.gateways.EmployeeRepository;
import co.com.ias.r2dbc.EntitysDBO.EmployeeDBO;
import co.com.ias.r2dbc.helper.ReactiveAdapterOperations;
import lombok.AllArgsConstructor;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.awt.print.Pageable;
import java.util.stream.Collectors;

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
    public Flux findAllByPage(int page, int size) {
        return myReactiveRepository.findAll(page, size);
    }

/*    @Override
    public Flux<Employee> findAllByPage(int page, int size) {
        System.out.println("Page: "+page+"size: "+size);
        Flux resp = myReactiveRepository.findAll();
        return resp;
    }*/

    @Override
    public Mono<Employee> saveEmployee(Employee employee) {
        Mono<EmployeeDBO> res = myReactiveRepository.save(EmployeeDBO.fromDomain(employee) );
        return res.map(EmployeeDBO::toDomain);
    }

}
