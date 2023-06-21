package co.com.ias.model.settlement.exampleData;

import co.com.ias.model.employee.Employee;
import co.com.ias.model.employee.constants.Constants;
import co.com.ias.model.employee.values.*;
import co.com.ias.model.settlement.Settlement;
import co.com.ias.model.settlement.values.*;

import java.time.LocalDate;

public class ExampleData {

    public static Settlement crearSettlement(){
        return new Settlement(
                new Employee(new IdEmployee(1l),new Idnumber("12345678"),new Name("Carlos"), new Indate(LocalDate.of(2015,03,05)),new Cargo("Ingeniero biomedico"),new Salary(3000000F)),
                new TransportApply(true),
                new TransportSupport(Constants.getTransSupportValue()),
                new WithdrawalDate(LocalDate.of(2015,01,01)),
                new WithdrawalReason("voluntario"),
                new WorkedTotalDays(100),
                new WorkedDaysLastYear(200),
                new WorkedDaysLastHalfYear(40),
                new VacationDays(1000000F),
                new BaseSalary(Constants.getSMMLV()),
                new Cesantias(0F),
                new InteresesCesantias(0F),
                new PrimaServicio(0F),
                new Nomina(0F),
                new Liquidacion(0F),
                new Bono(0F)
        );
    }
    public static Settlement crearSettlementUsingBuilder(){
        return Settlement.builder()
                .employee(new Employee(
                        new IdEmployee(3L),
                        new Idnumber("28435789"),
                        new Name("Carlos"),
                        new Indate(LocalDate.of(2015,01,05)),
                        new Cargo("ingeniero de software"),
                        new Salary(2000000F)))
                .transportApply(new TransportApply(true))
                .transportSupport(new TransportSupport(Constants.getTransSupportValue()))
                .withdrawalDate(new WithdrawalDate(LocalDate.of(2015,01,01)))
                .withdrawalReason(new WithdrawalReason("voluntario"))
                .workedTotalDays(new WorkedTotalDays(100))
                .workedDaysLastYear(new WorkedDaysLastYear(200))
                .workedDaysLastHalfYear(new WorkedDaysLastHalfYear(40))
                .vacationDays(new VacationDays(1000000F))
                .baseSalary(new BaseSalary(Constants.getSMMLV()))
                .cesatias(new Cesantias(0F))
                .interesesCesantias(new InteresesCesantias(0F))
                .primaServicio(new PrimaServicio(0F))
                .nomina(new Nomina(0F))
                .liquidacion(new Liquidacion(0F))
                .bono(new Bono(0F))
                .build();
    }
}
