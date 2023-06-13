package co.com.ias.api.entitysDTO;

import co.com.ias.model.employee.Employee;

import java.time.LocalDate;

public class EmployeeDTO {

    private Long id;
    private String idnumber ;
    private String name;
    private LocalDate indate;
    private String cargo;
    private Float salary;

    public Long getId() {
        return id;
    }

    public String getIdnumber() {
        return idnumber;
    }

    public String getName() {
        return name;
    }

    public LocalDate getIndate() {
        return indate;
    }

    public String getCargo() {
        return cargo;
    }

    public Float getSalary() {
        return salary;
    }

    public EmployeeDTO() {
    }

    public EmployeeDTO(Long id, String idnumber, String name, LocalDate indate, String cargo, Float salary) {
        this.id = id;
        this.idnumber = idnumber;
        this.name = name;
        this.indate = indate;
        this.cargo = cargo;
        this.salary = salary;
    }

    public Employee toDomain(){
        return new Employee( id, idnumber, name, indate, cargo, salary);
    }

    public static EmployeeDTO fromDomain(Employee employee){
        return new EmployeeDTO( employee.getId(),
                                employee.getIdnumber(),
                                employee.getName(),
                                employee.getIndate(),
                                employee.getCargo(),
                                employee.getSalary());
    }
}
