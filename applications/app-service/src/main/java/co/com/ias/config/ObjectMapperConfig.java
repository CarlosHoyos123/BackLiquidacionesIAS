package co.com.ias.config;

import co.com.ias.model.employee.gateways.EmployeeRepository;
import co.com.ias.model.salarylog.gateways.SalaryLogRepository;
import co.com.ias.usecase.employee.EmployeeUseCase;
import co.com.ias.usecase.salarylog.SalaryLogUseCase;
import org.reactivecommons.utils.ObjectMapper;
import org.reactivecommons.utils.ObjectMapperImp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ObjectMapperConfig {

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapperImp();
    }

    @Bean
    public EmployeeUseCase employeeUseCase(EmployeeRepository employeeRepository) {
        return new EmployeeUseCase(employeeRepository);
    }

    @Bean
    public SalaryLogUseCase salaryLogUseCase(SalaryLogRepository salaryLogRepository){
        return  new SalaryLogUseCase(salaryLogRepository);
    }

}
