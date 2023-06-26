package co.com.ias.api.entitysDTO;


import co.com.ias.api.exampleData.ExampleData;
import co.com.ias.model.employee.Employee;
import co.com.ias.model.settlement.Settlement;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class SettlementDTOTest {

    @Test
    void toDomain() {
        SettlementDTO settlementDTO = ExampleData.crearSettlementDTO();
        Settlement settlement = settlementDTO.toDomain();
    }

    @Test
    void fromDomain() {
        Settlement settlement = ExampleData.crearSettlement();
        SettlementDTO settlementDTO = SettlementDTO.fromDomain(settlement);
    }
}