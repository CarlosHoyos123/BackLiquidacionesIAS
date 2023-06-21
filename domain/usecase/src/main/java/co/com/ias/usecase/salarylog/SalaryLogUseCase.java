package co.com.ias.usecase.salarylog;

import co.com.ias.model.salarylog.SalaryLog;
import co.com.ias.model.salarylog.gateways.SalaryLogRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
public class SalaryLogUseCase {

    private final SalaryLogRepository salaryLogRepository;

    public Flux<SalaryLog> findSalarysUpdates(String id) {
        return salaryLogRepository.findByEmployeeid(id);
    }
}
