package co.com.ias.model.salarylog;
import co.com.ias.model.salarylog.values.*;
import lombok.*;
//import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class SalaryLog {
    private Id id;
    private Employeeid employeeid;
    private Previus previus;
    private Change change;
    private Date date;

    @Override
    public String toString() {
        return "SalaryLog{" +
                "id=" + id +
                ", employeeid=" + employeeid +
                ", previus=" + previus +
                ", change=" + change +
                ", date=" + date +
                '}';
    }
}
