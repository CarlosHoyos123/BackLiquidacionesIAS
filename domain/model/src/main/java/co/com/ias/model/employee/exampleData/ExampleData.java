package co.com.ias.model.employee.exampleData;

import co.com.ias.model.employee.Employee;
import co.com.ias.model.employee.values.*;
import co.com.ias.model.settlement.Settlement;
import reactor.core.publisher.Flux;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class ExampleData {

    public static Employee crearEmployee(){
        return  new Employee(
                    new IdEmployee(3L),
                    new Idnumber("28435789"),
                    new Name("Carlos"),
                    new Indate(LocalDate.of(2015,01,05)),
                    new Cargo("ingeniero de software"),
                    new Salary(2000000F));
    }

    public static Flux<Employee> FluxEmployees(){
        return Flux.just(crearEmployee());
    }
}
