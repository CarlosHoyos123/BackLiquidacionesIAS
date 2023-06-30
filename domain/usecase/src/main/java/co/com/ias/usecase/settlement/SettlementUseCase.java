package co.com.ias.usecase.settlement;

import co.com.ias.model.employee.Employee;
import co.com.ias.model.employee.constants.Constants;
import co.com.ias.model.employee.gateways.EmployeeRepository;
import co.com.ias.model.employee.values.*;
import co.com.ias.model.settlement.Settlement;
import co.com.ias.model.settlement.values.*;
import co.com.ias.usecase.Exceptions.InvalidPeriodDate;
import co.com.ias.usecase.Exceptions.NotFoundEmployee;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.beans.JavaBean;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;

@RequiredArgsConstructor
public class SettlementUseCase {

    private final EmployeeRepository employeeRepository;

    public Mono<Settlement> MadeSettlement (Settlement settlement) throws InvalidPeriodDate {
        //Mono<Employee> val = employeeConfirm(settlement).switchIfEmpty(Mono.error(new NotFoundEmployee("sfdgkjsdflñkgjñalsdfj")));
        Employee employeeInDB = new Employee(
                new IdEmployee(settlement.getEmployee().getId().getValue()),
                new Idnumber(settlement.getEmployee().getIdnumber().getValue()),
                new Name(settlement.getEmployee().getName().getValue()),
                new Indate(settlement.getEmployee().getIndate().getValue()),
                new Cargo(settlement.getEmployee().getCargo().getValue()),
                new Salary(settlement.getEmployee().getSalary().getValue()));

        return Mono.just(
                Settlement.builder()
                    .employee(new Employee(
                            new IdEmployee(settlement.getEmployee().getId().getValue()),
                            new Idnumber(settlement.getEmployee().getIdnumber().getValue()),
                            new Name(settlement.getEmployee().getName().getValue()),
                            new Indate(settlement.getEmployee().getIndate().getValue()),
                            new Cargo(settlement.getEmployee().getCargo().getValue()),
                            new Salary(settlement.getEmployee().getSalary().getValue())))
                    .transportApply(transportSupportApply(employeeInDB.getSalary().getValue()))
                    .transportSupport(TransportSupportValue(employeeInDB.getSalary().getValue()))
                    .baseSalary(baseSalarymount(employeeInDB.getSalary().getValue(),TransportSupportValue(employeeInDB.getSalary().getValue()).getValue()))
                    .withdrawalDate(new WithdrawalDate(settlement.getWithdrawalDate().getValue()))
                    .withdrawalReason(new WithdrawalReason(settlement.getWithdrawalReason().getValue()))
                    .workedTotalDays(workedDays(settlement))
                    .workedDaysLastYear(workedDaysInLastYear(settlement))
                    .workedDaysLastHalfYear(workedDaysInLastHalfYear(settlement))
                    .vacationDays(vacationsAmount(
                            baseSalarymount(employeeInDB.getSalary().getValue(),TransportSupportValue(employeeInDB.getSalary().getValue()).getValue()).getValue(),
                            workedDays(settlement).getValue()))
                    .cesatias(cesatiasAmount(
                            baseSalarymount(employeeInDB.getSalary().getValue(),TransportSupportValue(employeeInDB.getSalary().getValue()).getValue()).getValue(),
                            workedDays(settlement).getValue()
                    ))
                    .interesesCesantias(interesesCesantiasAmount(
                            cesatiasAmount(
                                    baseSalarymount(employeeInDB.getSalary().getValue(),TransportSupportValue(employeeInDB.getSalary().getValue()).getValue()).getValue(),
                                    workedDays(settlement).getValue()).getValue(),
                            workedDaysInLastYear(settlement).getValue()
                    ))
                    .primaServicio(primaServicioAmount(baseSalarymount(employeeInDB.getSalary().getValue(),TransportSupportValue(employeeInDB.getSalary().getValue()).getValue()).getValue(),
                            workedDaysInLastHalfYear(settlement).getValue()))
                    .nomina(nominaAmount(settlement))
                    .liquidacion(liquidacionAmount(settlement))
                    .bono(bonoAmount(settlement,
                            baseSalarymount(employeeInDB.getSalary().getValue(),TransportSupportValue(employeeInDB.getSalary().getValue()).getValue()).getValue()))
                .build());
    }

    public InteresesCesantias interesesCesantiasAmount(Float cesantias, int days){
        return new InteresesCesantias((float) ((cesantias*days*0.12)/360));
    }

    public Cesantias cesatiasAmount(Float baseSalary,int days){
        return new Cesantias(baseSalary*days/360);
    }

    public  VacationDays vacationsAmount(Float baseSalary,int days){;
        return new VacationDays(baseSalary*days/720);
    }

    public BaseSalary baseSalarymount(Float salary, Float transportValue){
        return new BaseSalary(salary+transportValue);
    }


    private Bono bonoAmount(Settlement settlement, Float baseSalary) {
        if (settlement.getWithdrawalReason().getValue().toLowerCase().equals("injustificado")){
            return new Bono(baseSalary);
        }
        else {
            return  new Bono(0F);
        }
    }

    public TransportApply transportSupportApply(Float salary){
        if (salary < Constants.getDeadLineForTransSupport()){
            return new TransportApply(true);
        }
        return new TransportApply(false);
    }
    public TransportSupport TransportSupportValue(Float salary){
        if (salary < Constants.getDeadLineForTransSupport()) {
            return new TransportSupport(Constants.getTransSupportValue());
        }
        return new TransportSupport(0);
    }

    public WorkedTotalDays workedDays(Settlement settlement) throws InvalidPeriodDate {
        if(settlement.getEmployee().getIndate().getValue().isAfter(settlement.getWithdrawalDate().getValue())){
            throw new InvalidPeriodDate("La fecha de retiro es anterior a la de inicio del contrato.");
        }
        return new WorkedTotalDays((int) ChronoUnit.DAYS.between(settlement.getEmployee().getIndate().getValue(),
                                                                 settlement.getWithdrawalDate().getValue()));
    }

    public WorkedDaysLastYear workedDaysInLastYear(Settlement settlement){
        LocalDate initialDate;
        if (settlement.getEmployee().getIndate().getValue().
                isAfter(settlement.getWithdrawalDate().getValue().with(TemporalAdjusters.firstDayOfYear()))) {
                    initialDate = settlement.getEmployee().getIndate().getValue();
                    return new WorkedDaysLastYear((int) ChronoUnit.DAYS.between(initialDate,
                                                                        settlement.getWithdrawalDate().getValue()));
                } else {
                    initialDate = settlement.getWithdrawalDate().getValue().with(TemporalAdjusters.firstDayOfYear());
                    return new WorkedDaysLastYear((int) ChronoUnit.DAYS.between(initialDate,
                            settlement.getWithdrawalDate().getValue()));
                }
    }

    public WorkedDaysLastHalfYear workedDaysInLastHalfYear(Settlement settlement){
        LocalDate firstDayYear = settlement.getWithdrawalDate().getValue().with(TemporalAdjusters.firstDayOfYear());
        LocalDate midYear = LocalDate.of(2015,07,01);
        LocalDate contractDate = settlement.getEmployee().getIndate().getValue();
        LocalDate initialDate;

        if (contractDate.isAfter(firstDayYear)) {
            initialDate =contractDate;
        } else {
            initialDate = firstDayYear;
        };

        if (settlement.getWithdrawalDate().getValue().isBefore(midYear)) {
            return new WorkedDaysLastHalfYear((int) ChronoUnit.DAYS.between(
                                                    initialDate,
                                                    settlement.getWithdrawalDate().getValue()));
        } else {
            return new WorkedDaysLastHalfYear((int) ChronoUnit.DAYS.between(
                                                    midYear,
                                                    settlement.getWithdrawalDate().getValue()));
        }
    }

    public PrimaServicio primaServicioAmount(Float basesalary, int days){
        return  new PrimaServicio(basesalary*days/360);
    }

    public Nomina nominaAmount(Settlement settlement){
        return new Nomina(0F);
    }

    public Liquidacion liquidacionAmount(Settlement settlement){
        return  new Liquidacion(0F);
    }
}
