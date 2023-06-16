package co.com.ias.model.employee.exampleData;

import co.com.ias.model.employee.Employee;
import co.com.ias.model.employee.values.*;

import java.time.LocalDate;

public class ExampleData {

    public static Employee crearEmployee(){
        return  new Employee(IdEmployee.builder().value(3L).build(),
                new Idnumber("28435789"),
                new Name("Carlos"),
                new Indate(LocalDate.of(2015,01,05)),
                new Cargo("ingeniero de software"),
                new Salary(2000000F));
    }
}
