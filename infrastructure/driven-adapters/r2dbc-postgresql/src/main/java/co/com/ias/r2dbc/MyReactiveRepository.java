package co.com.ias.r2dbc;

import co.com.ias.r2dbc.EntitysDBO.EmployeeDBO;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.awt.print.Pageable;

@Repository
public interface MyReactiveRepository extends ReactiveCrudRepository<EmployeeDBO, Long>, ReactiveSortingRepository<EmployeeDBO, Long> {

    @Query("select * from tblemployee  limit :size offset :DBoffset")
    Flux<EmployeeDBO> findAll(int DBoffset, int size);


    @Query("select * from tblemployee  where idnumber = :id")
    Mono<EmployeeDBO> findByIdnumber(String id);

}
