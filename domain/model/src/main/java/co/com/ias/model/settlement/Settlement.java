package co.com.ias.model.settlement;
import co.com.ias.model.employee.Employee;
import co.com.ias.model.settlement.values.*;
import lombok.*;
//import lombok.NoArgsConstructor;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class Settlement {

    private Employee employee;
    private TransportApply transportApply;
    private TransportSupport transportSupport;
    private WithdrawalDate  withdrawalDate;
    private WithdrawalReason withdrawalReason;
    private WorkedTotalDays workedTotalDays;
    private WorkedDaysLastYear workedDaysLastYear;
    private WorkedDaysLastHalfYear workedDaysLastHalfYear;
    private VacationDays vacationDays;
    private BaseSalary baseSalary;
    private Cesantias cesatias;
    private InteresesCesantias interesesCesantias;
    private PrimaServicio primaServicio;
    private Nomina nomina;
    private Liquidacion liquidacion;
    private Bono bono;

    @Override
    public String toString() {
        return "Settlement{" +
                "employee=" + employee +
                ", transportApply=" + transportApply +
                ", transportSupport=" + transportSupport +
                ", withdrawalDate=" + withdrawalDate +
                ", withdrawalReason=" + withdrawalReason +
                ", workedTotalDays=" + workedTotalDays +
                ", workedDaysLastYear=" + workedDaysLastYear +
                ", workedDaysLastHalfYear=" + workedDaysLastHalfYear +
                ", vacationDays=" + vacationDays +
                ", baseSalary=" + baseSalary +
                ", cesatias=" + cesatias +
                ", interesesCesantias=" + interesesCesantias +
                ", primaServicio=" + primaServicio +
                ", nomina=" + nomina +
                ", liquidacion=" + liquidacion +
                ", bono=" + bono +
                '}';
    }
}
