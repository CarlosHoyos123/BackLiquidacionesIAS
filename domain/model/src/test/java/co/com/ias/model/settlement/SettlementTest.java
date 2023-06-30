package co.com.ias.model.settlement;

import co.com.ias.model.settlement.exampleData.ExampleData;
import co.com.ias.model.settlement.values.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SettlementTest {

    @Test
    void testToString() {
        //GIVEN
        Settlement settlement = ExampleData.crearSettlementUsingBuilder();
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