package co.com.ias.usecase.settlement;

import co.com.ias.model.employee.Employee;
import co.com.ias.model.employee.constants.Constants;
import co.com.ias.model.employee.gateways.EmployeeRepository;
import co.com.ias.model.employee.values.*;
import co.com.ias.model.settlement.Settlement;
import co.com.ias.model.settlement.values.*;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;

@RequiredArgsConstructor
public class SettlementUseCase {

    private final EmployeeRepository employeeRepository;

    public Mono<Settlement> MadeSettlement (Settlement settlement) {
        Mono<Employee> monoEmployee = employeeConfirm(settlement.getEmployee().getIdnumber().getValue());

        return monoEmployee.flatMap(employee -> {
                Settlement.builder()
                    .employee(new Employee(
                            new IdEmployee(employee.getId().getValue()),
                            new Idnumber(employee.getIdnumber().getValue()),
                            new Name(employee.getName().getValue()),
                            new Indate(employee.getIndate().getValue()),
                            new Cargo(employee.getCargo().getValue()),
                            new Salary(employee.getSalary().getValue())))
                    .transportApply(transportSupportApply(settlement))
                    .transportSupport(TransportSupportValue(settlement))
                    .withdrawalDate(new WithdrawalDate(settlement.getWithdrawalDate().getValue()))
                    .withdrawalReason(new WithdrawalReason(settlement.getWithdrawalReason().getValue()))
                    .workedTotalDays(workedDays(settlement))
                    .workedDaysLastYear(workedDaysInLastYear(settlement))
                    .workedDaysLastHalfYear(workedDaysInLastHalfYear(settlement))
                    .vacationDays(vacationsAmount(settlement))
                    .baseSalary(baseSalarymount(settlement))
                    .cesatias(cesatiasAmount(settlement))
                    .interesesCesantias(interesesCesantiasAmount(settlement))
                    .primaServicio(primaServicioAmount(settlement))
                    .nomina(nominaAmount(settlement))
                    .liquidacion(liquidacionAmount(settlement))
                    .bono(bonoAmount(settlement))
                .build();
                return Mono.just(settlement);
        });
    }

    private Bono bonoAmount(Settlement settlement) {
        if (settlement.getWithdrawalReason().getValue().toLowerCase().equals("injustificado")){

        }
        return null;
    }

    public  VacationDays vacationsAmount(Settlement settlement){
        Float salary = settlement.getBaseSalary().getValue();
        int days = settlement.getWorkedTotalDays().getValue();
        return new VacationDays(salary*(days/720));
    }

    public Mono<Employee> employeeConfirm(String id) throws NullPointerException{
        Mono<Employee> employee =  employeeRepository.findByIdnumber(id);
        if (employee == null) {
            throw new NullPointerException("El empleado no existe");
        }
        return employee;
    }
    public TransportApply transportSupportApply(Settlement settlement){
        if (settlement.getEmployee().getSalary().getValue() > Constants.getDeadLineForTransSupport())
            return new TransportApply(true);
        return new TransportApply(false);
    }
    public TransportSupport TransportSupportValue(Settlement settlement){
        return (settlement.getTransportApply().getValue() == true)? new TransportSupport(Constants.getTransSupportValue()):new TransportSupport(0);
    }

    public WorkedTotalDays workedDays(Settlement settlement){
        return new WorkedTotalDays((int) ChronoUnit.DAYS.between(settlement.getEmployee().getIndate().getValue(),
                                                                 settlement.getWithdrawalDate().getValue()));
    }

    public WorkedDaysLastYear workedDaysInLastYear(Settlement settlement){
        return new WorkedDaysLastYear((int) ChronoUnit.DAYS.between(LocalDate.now().with(TemporalAdjusters.firstDayOfYear()),
                                                                    settlement.getWithdrawalDate().getValue()));
    }

    public WorkedDaysLastHalfYear workedDaysInLastHalfYear(Settlement settlement){

        if (LocalDate.now().isBefore(LocalDate.of(2023,07,01))) {
            return new WorkedDaysLastHalfYear((int) ChronoUnit.DAYS.between(
                                                    LocalDate.of(2023,01,01),
                                                    settlement.getWithdrawalDate().getValue()));
        } else {
            return new WorkedDaysLastHalfYear((int) ChronoUnit.DAYS.between(
                                                    LocalDate.of(2023,07,01),
                                                    settlement.getWithdrawalDate().getValue()));
        }
    }

    public BaseSalary baseSalarymount(Settlement settlement){
        Float salary = settlement.getEmployee().getSalary().getValue()+settlement.getTransportSupport().getValue();
        return new BaseSalary(salary);
    }

    public Cesantias cesatiasAmount(Settlement settlement){
        Float salary = settlement.getBaseSalary().getValue();
        int dias = settlement.getWorkedTotalDays().getValue();
        return new Cesantias(salary*(dias/360));
    }

    public InteresesCesantias interesesCesantiasAmount(Settlement settlement){
        Float cesantias = settlement.getCesatias().getValue();
        int dias = settlement.getWorkedTotalDays().getValue();
        return new InteresesCesantias((float) (cesantias*(dias*(0.12/360))));
    }

    public PrimaServicio primaServicioAmount(Settlement settlement){
        float salary = settlement.getBaseSalary().getValue();
        int dias = settlement.getWorkedDaysLastHalfYear().getValue();
        return  new PrimaServicio(salary*(dias/360));
    }

    public Nomina nominaAmount(Settlement settlement){
        return null;
    }

    public Liquidacion liquidacionAmount(Settlement settlement){
        return  null;
    }





}
