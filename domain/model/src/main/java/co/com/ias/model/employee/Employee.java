package co.com.ias.model.employee;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.Getter;
//import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
//@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Employee {

    private Long id ;
    private String idnumber ;
    private String name;
    private LocalDate indate;
    private String cargo;
    private Float salary;

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
