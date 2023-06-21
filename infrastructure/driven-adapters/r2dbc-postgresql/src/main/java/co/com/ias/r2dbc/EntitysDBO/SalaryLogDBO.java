package co.com.ias.r2dbc.EntitysDBO;

import co.com.ias.model.employee.Employee;
import co.com.ias.model.salarylog.SalaryLog;
import co.com.ias.model.salarylog.values.*;
import lombok.*;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;

@Table(name = "tblsalarylogs")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class SalaryLogDBO {
    @Id
    private Long id;
    private String employeeid;
    private Float previus;
    private Float change;
    private LocalDate date;

    public static SalaryLog toDomain(SalaryLogDBO salaryLogDBO){
        return new SalaryLog(
           new co.com.ias.model.salarylog.values.Id(salaryLogDBO.id),
           new Employeeid(salaryLogDBO.employeeid),
           new Previus(salaryLogDBO.previus),
           new Change(salaryLogDBO.change),
           new Date(salaryLogDBO.date));
    }
    public static SalaryLogDBO fromDomain(SalaryLog salaryLog){
        return SalaryLogDBO.builder()
                .id(salaryLog.getId().getValue())
                .employeeid(salaryLog.getEmployeeid().getValue())
                .previus(salaryLog.getPrevius().getValue())
                .change(salaryLog.getChange().getValue())
                .date(salaryLog.getDate().getValue())
                .build();
    }

    @Override
    public String toString() {
        return "SalaryLogDTO{" +
                "id=" + id +
                ", employeeid='" + employeeid + '\'' +
                ", previus=" + previus +
                ", change=" + change +
                ", date=" + date +
                '}';
    }
}
