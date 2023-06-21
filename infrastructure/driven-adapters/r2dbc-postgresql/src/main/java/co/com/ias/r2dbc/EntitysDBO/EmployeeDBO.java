package co.com.ias.r2dbc.EntitysDBO;

import co.com.ias.model.employee.Employee;
import co.com.ias.model.employee.values.*;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import java.time.LocalDate;

@Table(name = "tblemployee")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDBO {

    @Id
    private Long id;
    private String idnumber ;
    private String name;
    private LocalDate indate;
    private String cargo;
    private Float salary;


    public static Employee toDomain(EmployeeDBO employeeDBO){
        return new Employee(
                new IdEmployee(employeeDBO.id),
                new Idnumber(employeeDBO.idnumber),
                new Name(employeeDBO.name),
                new Indate(employeeDBO.indate),
                new Cargo(employeeDBO.getCargo()),
                new Salary(employeeDBO.getSalary()));
    }

    public static EmployeeDBO fromDomain(Employee employee){
        return EmployeeDBO.builder()
                .id(employee.getId().getValue())
                .idnumber(employee.getIdnumber().getValue())
                .name(employee.getName().getValue())
                .indate(employee.getIndate().getValue())
                .cargo(employee.getCargo().getValue())
                .salary(employee.getSalary().getValue())
                .build();
    }
    @Override
    public String toString() {
        return "EmployeeDBO{" +
                "idNumber='" + idnumber + '\'' +
                ", name='" + name + '\'' +
                ", inDate=" + indate +
                ", cargo='" + cargo + '\'' +
                ", salary=" + salary +
                '}';
    }
}
