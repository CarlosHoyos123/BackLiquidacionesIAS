package co.com.ias.model.salarylog.gateways;

import co.com.ias.model.salarylog.SalaryLog;
import reactor.core.publisher.Flux;

public interface SalaryLogRepository {
    Flux<SalaryLog> findByEmployeeid(String id);
}
