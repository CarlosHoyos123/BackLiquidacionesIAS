package co.com.ias.usecase.settlement;

import co.com.ias.model.employee.constants.Constants;
import co.com.ias.model.employee.values.Salary;
import co.com.ias.model.settlement.Settlement;
import co.com.ias.model.settlement.exampleData.ExampleData;
import co.com.ias.model.settlement.values.TransportApply;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.mockito.ArgumentMatchers.any;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyFloat;

@SpringBootTest(classes = SettlementUseCase.class)
class SettlementUseCaseTest {
    @MockBean
    SettlementUseCase settlementUseCase;

    @Test
    void madeSettlement() {

    }
    @Test
    void vacationsAmount() {
    }

    @Test
    void employeeConfirm() {
    }

    @Test
    void transportSupportApply() {
        //given
        Settlement settlement = ExampleData.crearSettlementUsingBuilder();
        settlement.setEmployee(co.com.ias.model.employee.exampleData.ExampleData.crearEmployee());
        //when
        TransportApply response = settlementUseCase.transportSupportApply(2000000f);
        //then
        assertTrue(response.getValue());
    }

    @Test
    void transportSupportNotApply() {
        /*//given
        Settlement settlement = ExampleData.crearSettlementUsingBuilder();
        settlement.setEmployee(co.com.ias.model.employee.exampleData.ExampleData.crearEmployee());
        //when
        TransportApply response = settlementUseCase.transportSupportApply(settlement);
        //then
        assertFalse(response.getValue());*/
    }

    @Test
    void transportSupportValue() {
    }

    @Test
    void workedDays() {
    }

    @Test
    void workedDaysInLastYear() {
    }

    @Test
    void workedDaysInLastHalfYear() {
    }

    @Test
    void baseSalarymount() {
    }

    @Test
    void cesatiasAmount() {
    }

    @Test
    void interesesCesantiasAmount() {
    }

    @Test
    void primaServicioAmount() {
    }

    @Test
    void nominaAmount() {
    }

    @Test
    void liquidacionAmount() {
    }
}