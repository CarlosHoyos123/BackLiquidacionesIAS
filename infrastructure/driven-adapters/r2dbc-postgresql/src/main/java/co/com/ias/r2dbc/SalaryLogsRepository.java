package co.com.ias.r2dbc;

import co.com.ias.model.salarylog.SalaryLog;
import co.com.ias.r2dbc.EntitysDBO.EmployeeDBO;
import co.com.ias.r2dbc.EntitysDBO.SalaryLogDBO;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface SalaryLogsRepository extends ReactiveCrudRepository<SalaryLogDBO, Long>, ReactiveSortingRepository<SalaryLogDBO, Long> {

    @Query("select * from tblsalarylogs where employeeid = :id")
    Flux<SalaryLogDBO> findByEmployeeid(String id);
}
