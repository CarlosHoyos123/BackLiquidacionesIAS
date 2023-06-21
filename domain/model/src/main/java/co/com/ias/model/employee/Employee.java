package co.com.ias.model.employee;
import co.com.ias.model.employee.values.*;
import lombok.*;
//import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class Employee {

    private IdEmployee id ;
    private Idnumber idnumber ;
    private Name name;
    private Indate indate;
    private Cargo cargo;
    private Salary salary;


    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", idnumber='" + idnumber + '\'' +
                ", name='" + name + '\'' +
                ", indate=" + indate +
                ", cargo='" + cargo + '\'' +
                ", salary=" + salary +
                '}';
    }
}
