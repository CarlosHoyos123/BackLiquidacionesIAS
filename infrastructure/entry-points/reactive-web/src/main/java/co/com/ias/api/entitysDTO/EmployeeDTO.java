package co.com.ias.api.entitysDTO;

import co.com.ias.model.employee.Employee;

public class EmployeeDTO {

    private long id ;
    private String name;
    private String lastname;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLastname() {
        return lastname;
    }

    public EmployeeDTO() {
    }

    public EmployeeDTO(long id, String name, String lastname) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
    }

    public Employee toDomain(){
        return new Employee(id, name, lastname);
    }

    public static EmployeeDTO fromDomain(Employee employee){
        return new EmployeeDTO(employee.getId(), employee.getName(), employee.getLastname());
    }
}
