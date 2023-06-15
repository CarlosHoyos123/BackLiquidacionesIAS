package co.com.ias.r2dbc.exampleData;

import co.com.ias.model.employee.Employee;
import co.com.ias.model.employee.values.*;
import co.com.ias.r2dbc.EntitysDBO.EmployeeDBO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class ExampleData {
    public static EmployeeDBO crearEmployeeDBO(){
        return new EmployeeDBO(1L,"28435789","Carlos", LocalDate.of(2023,01,01),"ingeniero",20000F);
    }
    /*public static IdEmployee crearValueIdEmploye(){
        return new IdEmployee();
    }*/
    public static Employee crearEmployee(){
        return  new Employee(IdEmployee.builder().value(3L).build(),
                new Idnumber("28435789"),
                new Name("Carlos"),
                new Indate(LocalDate.of(2023,01,01)),
                new Cargo("ingeniero"),
                new Salary(20000F));
    }
    public static List<EmployeeDBO> employeeList(){
        return Arrays.asList(crearEmployeeDBO(),crearEmployeeDBO());
    }
    public static Flux<EmployeeDBO> crearFluxEmployee(){
        return Flux.just(crearEmployeeDBO());
    }
    public static Mono<EmployeeDBO> crearMonoEmployeeDBO(){
        return Mono.just(crearEmployeeDBO());
    }
}
