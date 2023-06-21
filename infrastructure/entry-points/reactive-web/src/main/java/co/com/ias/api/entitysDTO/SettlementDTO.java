package co.com.ias.api.entitysDTO;

import co.com.ias.model.employee.Employee;
import co.com.ias.model.employee.values.*;
import co.com.ias.model.settlement.Settlement;
import co.com.ias.model.settlement.values.*;
import java.time.LocalDate;

public class SettlementDTO {

    private EmployeeDTO employee;
    private Boolean transportApply;
    private Float transportSupport;
    private LocalDate withdrawalDate;
    private String withdrawalReason;
    private int workedTotalDays;
    private int workedDaysLastYear;
    private int workedDaysLastHalfYear;
    private Float vacationDays;
    private Float baseSalary;
    private Float cesatias;
    private Float interesesCesantias;
    private Float primaServicio;
    private Float nomina;
    private Float liquidacion;
    public  Float bono;

    public Float getBono() {
        return bono;
    }
    public Float getBaseSalary() {
        return baseSalary;
    }
    public EmployeeDTO getEmployee() {
        return employee;
    }
    public Boolean getTransportApply() {
        return transportApply;
    }
    public Float getTransportSupport() {
        return transportSupport;
    }
    public LocalDate getWithdrawalDate() {
        return withdrawalDate;
    }
    public String getWithdrawalReason() {
        return withdrawalReason;
    }
    public int getWorkedTotalDays() {
        return workedTotalDays;
    }
    public int getWorkedDaysLastYear() {
        return workedDaysLastYear;
    }
    public int getWorkedDaysLastHalfYear() {
        return workedDaysLastHalfYear;
    }
    public Float getVacationDays() {
        return vacationDays;
    }
    public Float getCesatias() {
        return cesatias;
    }
    public Float getInteresesCesantias() {
        return interesesCesantias;
    }
    public Float getPrimaServicio() {
        return primaServicio;
    }
    public Float getNomina() {
        return nomina;
    }
    public Float getLiquidacion() {
        return liquidacion;
    }

    public SettlementDTO() {
    }


    public SettlementDTO(EmployeeDTO employee, Boolean transportApply, Float transportSupport, LocalDate withdrawalDate, String withdrawalReason, int workedTotalDays, int workedDaysLastYear, int workedDaysLastHalfYear, Float vacationDays, Float baseSalary, Float cesatias, Float interesesCesantias, Float primaServicio, Float nomina, Float liquidacion, Float bono) {
        this.employee = employee;
        this.transportApply = transportApply;
        this.transportSupport = transportSupport;
        this.withdrawalDate = withdrawalDate;
        this.withdrawalReason = withdrawalReason;
        this.workedTotalDays = workedTotalDays;
        this.workedDaysLastYear = workedDaysLastYear;
        this.workedDaysLastHalfYear = workedDaysLastHalfYear;
        this.vacationDays = vacationDays;
        this.baseSalary = baseSalary;
        this.cesatias = cesatias;
        this.interesesCesantias = interesesCesantias;
        this.primaServicio = primaServicio;
        this.nomina = nomina;
        this.liquidacion = liquidacion;
        this.bono = bono;
    }

    public Settlement toDomain(){
        return new Settlement(
                new Employee(
                        new IdEmployee(employee.getId()),
                        new Idnumber(employee.getIdnumber()),
                        new Name(employee.getName()),
                        new Indate(employee.getIndate()),
                        new Cargo(employee.getCargo()),
                        new Salary(employee.getSalary())),
                new TransportApply(transportApply),
                new TransportSupport(transportSupport),
                new WithdrawalDate(withdrawalDate),
                new WithdrawalReason(withdrawalReason),
                new WorkedTotalDays(workedTotalDays),
                new WorkedDaysLastYear(workedDaysLastYear),
                new WorkedDaysLastHalfYear(workedDaysLastHalfYear),
                new VacationDays(vacationDays),
                new BaseSalary(baseSalary),
                new Cesantias(cesatias),
                new InteresesCesantias(interesesCesantias),
                new PrimaServicio(primaServicio),
                new Nomina(nomina),
                new Liquidacion(liquidacion),
                new Bono(bono)
        );
    }
    public static SettlementDTO fromDomain(Settlement settlement){
        return new SettlementDTO(
                EmployeeDTO.fromDomain(settlement.getEmployee()),
                settlement.getTransportApply().getValue(),
                settlement.getTransportSupport().getValue(),
                settlement.getWithdrawalDate().getValue(),
                settlement.getWithdrawalReason().getValue(),
                settlement.getWorkedTotalDays().getValue(),
                settlement.getWorkedDaysLastYear().getValue(),
                settlement.getWorkedDaysLastHalfYear().getValue(),
                settlement.getVacationDays().getValue(),
                settlement.getBaseSalary().getValue(),
                settlement.getCesatias().getValue(),
                settlement.getInteresesCesantias().getValue(),
                settlement.getPrimaServicio().getValue(),
                settlement.getNomina().getValue(),
                settlement.getLiquidacion().getValue(),
                settlement.getBono().getValue()
        );


    }
}
