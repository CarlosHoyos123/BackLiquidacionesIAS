package co.com.ias.api.exampleData;

import co.com.ias.api.entitysDTO.EmployeeDTO;
import co.com.ias.api.entitysDTO.SettlementDTO;
import co.com.ias.api.exception.Personalized.EmployeeUnvalid;
import co.com.ias.model.employee.Employee;
import co.com.ias.model.employee.values.*;
import co.com.ias.model.settlement.Settlement;
import co.com.ias.model.settlement.values.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.security.PublicKey;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class ExampleData {

    public static EmployeeDTO crearEmployeeDTO() {
        return new EmployeeDTO(1L,
                "28435789",
                "Carlos",
                LocalDate.of(2015,01,10),
                "ingeniero de spoftware",
                2000000F);
    }
    public static SettlementDTO crearSettlementDTO(){
        return new SettlementDTO(
            crearEmployeeDTO(),
        false,
        0F,
        LocalDate.of(2015,01,01),
        "injustificado",
        1,
        1,
        1,
        0F,
        0F,
        0F,
        0F,
        0F,
        0F,
        0F,
        0F);
    }
    public static EmployeeDTO invalidEmployeeDTO(){
        return new EmployeeDTO(1L,"28435789","Carlos", LocalDate.of(2016,01,10),"ingeniero de spoftware",2000000F);
    }
    public static Employee crearEmployee(){
        return  new Employee(
            new IdEmployee(3L),
            new Idnumber("28435789"),
            new Name("Carlos"),
            new Indate(LocalDate.of(2015,01,02)),
            new Cargo("ingeniero de software"),
            new Salary(2000000F));
    }
    public static Settlement crearSettlement(){
        return new Settlement(
            crearEmployee(),
            new TransportApply (false),
            new TransportSupport (0f),
            new WithdrawalDate (LocalDate.of(2015,02,02)),
            new WithdrawalReason ("injustificado"),
            new WorkedTotalDays (1),
            new WorkedDaysLastYear (1),
            new WorkedDaysLastHalfYear (1),
            new VacationDays (1F),
            new BaseSalary (0f),
            new Cesantias (0F),
            new InteresesCesantias (0F),
            new PrimaServicio (0F),
            new Nomina (0F),
            new Liquidacion (0F),
            new Bono (0F));
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
