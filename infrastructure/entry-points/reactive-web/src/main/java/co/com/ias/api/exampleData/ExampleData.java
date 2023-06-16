package co.com.ias.api.exampleData;

import co.com.ias.api.entitysDTO.EmployeeDTO;
import co.com.ias.model.employee.Employee;
import co.com.ias.model.employee.values.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class ExampleData {

    public static EmployeeDTO crearEmployeeDTO(){
        return new EmployeeDTO(1L,"28435789","Carlos", LocalDate.of(2015,01,10),"ingeniero de spoftware",2000000F);
    }
    public static EmployeeDTO invalidEmployeeDTO(){
        return new EmployeeDTO(1L,"28435789","Carlos", LocalDate.of(2016,01,10),"ingeniero de spoftware",2000000F);
    }
    public static Employee crearEmployee(){
        return  new Employee(IdEmployee.builder().value(3L).build(),
                new Idnumber("28435789"),
                new Name("Carlos"),
                new Indate(LocalDate.of(2015,01,02)),
                new Cargo("ingeniero de software"),
                new Salary(2000000F));
    }
    public static List<EmployeeDTO> employeeList(){
        return Arrays.asList(crearEmployeeDTO(),crearEmployeeDTO());
    }
    public static Flux<EmployeeDTO> crearFluxEmployee(){
        return Flux.just(crearEmployeeDTO());
    }
    public static Mono<EmployeeDTO> crearMonoEmployeeDTO(){
        return Mono.just(crearEmployeeDTO());
    }
}
