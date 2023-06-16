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
        return new EmployeeDTO(1L,"28435789","Carlos", LocalDate.of(2023,01,01),"ingeniero",20000F);
    }
    public static Employee crearEmployee(){
        return  new Employee(IdEmployee.builder().value(3L).build(),
                new Idnumber("28435789"),
                new Name("Carlos"),
                new Indate(LocalDate.of(2023,01,01)),
                new Cargo("ingeniero"),
                new Salary(20000F));
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
