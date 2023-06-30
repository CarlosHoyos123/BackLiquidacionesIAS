package co.com.ias.usecase.settlement;

import co.com.ias.model.employee.Employee;
import co.com.ias.model.employee.constants.Constants;
import co.com.ias.model.employee.gateways.EmployeeRepository;
import co.com.ias.model.employee.values.*;
import co.com.ias.model.settlement.Settlement;
import co.com.ias.model.settlement.exampleData.ExampleData;
import co.com.ias.model.settlement.values.*;
import co.com.ias.usecase.Exceptions.InvalidPeriodDate;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyFloat;

@SpringBootTest(classes = SettlementUseCase.class)
class SettlementUseCaseTest {
    @MockBean
    EmployeeRepository employeeRepository;

    @InjectMocks
    SettlementUseCase settlementUseCase;

    @Test
    void madeSettlement() throws InvalidPeriodDate {
        //given
        Settlement settlement = ExampleData.crearSettlementUsingBuilder();
        settlement.setEmployee(new Employee(new IdEmployee(21L),
                                            new Idnumber("98745201"),
                                            new Name("LETICIA GONZALEZ"),
                                            new Indate(LocalDate.of(2015,03,03)),
                                            new Cargo("AUDITOR DE FACTURACIONES"),
                                            new Salary(2400000f)));
        settlement.setWithdrawalDate(new WithdrawalDate(LocalDate.of(2015,04,04)));
        // when
        Mono<Settlement> result = settlementUseCase.MadeSettlement(settlement);
        //then
    }
    @Test
    void vacationsAmount() {
        //given
        Float salary = 200000f;
        int days = 50;
        Float result = (salary*days)/720;
        //when
        VacationDays response = settlementUseCase.vacationsAmount(salary, days);
        //then
        assertEquals(result, response.getValue());
    }

    @Test
    void employeeConfirm() {
        //given

        // when

        //then
    }

    @Test
    void transportSupportApply() {
        //given
        //when
        TransportApply response = settlementUseCase.transportSupportApply(2000000f);
        //then
        assertTrue(response.getValue());
    }

    @Test
    void transportSupportNotApply() {
        //given
        //when
        TransportApply response = settlementUseCase.transportSupportApply(4000000f);
        //then
        assertFalse(response.getValue());
    }

    @Test
    void transportSupportValue() {
        //given
        //when
        TransportSupport response = settlementUseCase.TransportSupportValue(2000000f);
        //then
        assertEquals(Constants.getTransSupportValue(),response.getValue());
    }

    @Test
    void transportSupportValueZero() {
        //given
        //when
        TransportSupport response = settlementUseCase.TransportSupportValue(4000000f);
        //then
        assertEquals(0f,response.getValue());
    }

    @Test
    void workedDays() throws InvalidPeriodDate {
        //given
        Settlement settlement = ExampleData.crearSettlementUsingBuilder();
        settlement.setWithdrawalDate(new WithdrawalDate(LocalDate.of(2016,01,05)));
        //when
        WorkedTotalDays response = settlementUseCase.workedDays(settlement);
        //then
        assertEquals(365,response.getValue());
    }

    @Test
    void workedDaysInLastYearSameYear() {
        //given
        Settlement settlement = ExampleData.crearSettlementUsingBuilder();
        settlement.setWithdrawalDate(new WithdrawalDate(LocalDate.of(2015,01,8)));
        //when
        WorkedDaysLastYear response = settlementUseCase.workedDaysInLastYear(settlement);
        //then
        assertEquals(3,response.getValue());
    }

    @Test
    void workedDaysInLastYearEarlierYears() {
        //given
        Settlement settlement = ExampleData.crearSettlementUsingBuilder();
        settlement.setWithdrawalDate(new WithdrawalDate(LocalDate.of(2016,01,11)));
        //when
        WorkedDaysLastYear response = settlementUseCase.workedDaysInLastYear(settlement);
        //then
        assertEquals(10,response.getValue());
    }

    @Test
    void workedDaysInLastHalfYear() {
        //given
        Settlement settlement = ExampleData.crearSettlementUsingBuilder();
        settlement.setWithdrawalDate(new WithdrawalDate(LocalDate.of(2015,01,20)));
        //when
        WorkedDaysLastHalfYear response = settlementUseCase.workedDaysInLastHalfYear(settlement);
        //then
        assertEquals(15,response.getValue());
    }

    @Test
    void workedDaysInSecondtHalfYear() {
        //given
        Settlement settlement = ExampleData.crearSettlementUsingBuilder();
        settlement.setWithdrawalDate(new WithdrawalDate(LocalDate.of(2015,07,12)));
        //when
        WorkedDaysLastHalfYear response = settlementUseCase.workedDaysInLastHalfYear(settlement);
        //then
        assertEquals(11,response.getValue());
    }

    @Test
    void baseSalarymount() {
        //Given
        Float result = Constants.getSMMLV() + Constants. getTransSupportValue();
        //when
        BaseSalary response = settlementUseCase.baseSalarymount(Constants.getSMMLV(), Constants.getTransSupportValue());
        //then
        assertEquals(result,response.getValue());
    }

    @Test
    void cesatiasAmountTest() {
        int dias = 50;
        Settlement settlement = ExampleData.crearSettlementUsingBuilder();
        settlement.setWorkedTotalDays(new WorkedTotalDays(dias));
        Float result = (Constants.getSMMLV()*dias)/360 ;
        //when
        Cesantias response = settlementUseCase.cesatiasAmount(Constants.getSMMLV(), settlement.getWorkedTotalDays().getValue());
        //then
        assertEquals(result,response.getValue());
    }

    @Test
    void interesesCesantiasAmount() {
        Settlement settlement = ExampleData.crearSettlementUsingBuilder();
        Float valorCesantias = 200000f;
        int dias = 50;
        settlement.setCesatias(new Cesantias(valorCesantias));
        settlement.setWorkedTotalDays(new WorkedTotalDays(dias));
        Float result = (float) ((valorCesantias*dias*0.12)/360);
        //when
        InteresesCesantias response = settlementUseCase.interesesCesantiasAmount(settlement.getCesatias().getValue(),
                                                                        settlement.getWorkedTotalDays().getValue());
        //then
        assertEquals(result,response.getValue());
    }

    @Test
    void primaServicioAmount() {

        //given
        Settlement settlement = ExampleData.crearSettlementUsingBuilder();
        int days = 50;
        Float result = (Constants.getSMMLV()*days)/360;
        //when
        PrimaServicio response = settlementUseCase.primaServicioAmount(Constants.getSMMLV(), days);
        //then
        assertEquals(result,response.getValue());
    }

    @Test
    void nominaAmount() {
    }

    @Test
    void liquidacionAmount() {
    }
}