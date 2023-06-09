package co.com.ias.r2dbc.EntitysDBO;

import co.com.ias.model.employee.Employee;
import lombok.*;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "tblemployees")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDBO {

    private long id ;
    private String name;
    private String lastname;

    public static Employee toDomain(EmployeeDBO employeeDBO){
        return new Employee(employeeDBO.id, employeeDBO.name, employeeDBO.lastname);
    }

    public static EmployeeDBO fromDomain(Employee employee){
        return EmployeeDBO.builder()
                .id(employee.getId())
                .name(employee.getName())
                .lastname(employee.getLastname())
                .build();
    }
}
