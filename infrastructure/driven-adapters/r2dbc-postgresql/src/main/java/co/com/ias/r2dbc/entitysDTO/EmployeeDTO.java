package co.com.ias.r2dbc.entitysDTO;

import co.com.ias.model.employee.Employee;

public class EmployeeDTO {

    private long id ;
    private String name;
    private String lastname;

    public EmployeeDTO(long id, String name, String lastname) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
    }

    public Employee toDomain(){
        return new Employee(id, name, lastname);
    }

    public EmployeeDTO fromDomain(Employee employee){
        return new EmployeeDTO(employee.getId(), employee.getName(), employee.getLastname());
    }
}
