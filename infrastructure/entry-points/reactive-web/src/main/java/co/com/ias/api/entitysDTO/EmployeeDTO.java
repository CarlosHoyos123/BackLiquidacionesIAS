package co.com.ias.api.entitysDTO;

import co.com.ias.model.employee.Employee;
import co.com.ias.model.employee.values.*;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

//validaciones con @Valid
    /*atributo name :     maximo 50 caracteres      *
                        No caracteres especiales
                        no vacio                    *
    atributo documento: maximo 15 digitos           *
                        minimo 7 digitos            *
                        no vacio                    *
    atributo fecha:     YYYY/DD/MM
                        no superior a 6/jun/2015
                        no inferior a 1/ene/2015
                        no vacio                    *
    atributo cargo:     maximo 30 caracteres        *
                        minimo 10 caracteres        *
                        no caracteres especiales
                        no vacio                    *
    atributo salario:   no inferior a 1SMMLV        *
                        no superior a 7 millones    *
                        no vacio                    * */

public class EmployeeDTO {
    private Long id;
    @Size(min = 7, max = 15, message = "Documento debe contener entre 7 y 15 digitos")
    @NotEmpty
    private String idnumber ;
    @Max(value = 50)
    @NotEmpty
    private String name;
    @NotEmpty
    private LocalDate indate;
    @Size(min = 10, max = 30, message = "Cargo debe contener entre 10 y 30 caracteres")
    @NotEmpty
    private String cargo;
    @Max(value = 7000000)
    @Min(value = 1300606)
    @NotEmpty
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

    public EmployeeDTO(Long id, String idnumber, String name, LocalDate indate, String cargo, Float salary) throws IllegalArgumentException{
        this.id = id;
        this.idnumber = idnumber;
        this.name = name;
        this.indate = indate;
        this.cargo = cargo;
        this.salary = salary;
    }

    public Employee toDomain(){
        return new Employee(
                new IdEmployee(id),
                new Idnumber(idnumber),
                new Name(name),
                new Indate(indate),
                new Cargo(cargo),
                new Salary(salary));
    }

    public static EmployeeDTO fromDomain(Employee employee){
        return new EmployeeDTO( employee.getId().getValue(),
                                employee.getIdnumber().getValue(),
                                employee.getName().getValue(),
                                employee.getIndate().getValue(),
                                employee.getCargo().getValue(),
                                employee.getSalary().getValue());
    }
}
