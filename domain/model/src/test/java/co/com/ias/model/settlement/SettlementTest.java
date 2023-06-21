package co.com.ias.model.settlement;

import co.com.ias.model.employee.Employee;
import co.com.ias.model.employee.constants.Constants;
import co.com.ias.model.employee.values.*;
import co.com.ias.model.settlement.exampleData.ExampleData;
import co.com.ias.model.settlement.values.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class SettlementTest {

    @Test
    void testToString() {
        //GIVEN
        Settlement settlement = ExampleData.crearSettlement();
        //WHEN
        settlement.setTransportApply(new TransportApply(false));
        settlement.toString();
        //THEN
        assertFalse(settlement.getTransportApply().getValue());
    }

    @Test
    void builder() {
        //given
        Settlement settlementBuilder = ExampleData.crearSettlementUsingBuilder();
        //when

        //then
        assertTrue(settlementBuilder.getTransportApply().getValue());
    }

    @Test
    void toBuilder() {
    }
}