package co.com.ias.r2dbc.EntitysDBO;

import co.com.ias.model.employee.Employee;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serializable;
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
        return new Employee(employeeDBO.id, employeeDBO.idnumber, employeeDBO.name, employeeDBO.indate, employeeDBO.getCargo(),employeeDBO.getSalary());
    }

    public static EmployeeDBO fromDomain(Employee employee){
        return EmployeeDBO.builder()
                .id(employee.getId())
                .idnumber(employee.getIdnumber())
                .name(employee.getName())
                .indate(employee.getIndate())
                .cargo(employee.getCargo())
                .salary(employee.getSalary())
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
