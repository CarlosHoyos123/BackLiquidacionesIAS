package co.com.ias.r2dbc;

import co.com.ias.r2dbc.EntitysDBO.EmployeeDBO;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyReactiveRepository extends ReactiveCrudRepository<EmployeeDBO, Long>{

}
