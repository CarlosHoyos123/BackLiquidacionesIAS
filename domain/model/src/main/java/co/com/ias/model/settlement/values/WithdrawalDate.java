package co.com.ias.model.settlement.values;

import java.time.LocalDate;

public class WithdrawalDate {

    private LocalDate value;

    public WithdrawalDate(LocalDate value) {
        this.value = value;
    }

    public LocalDate getValue() {
        return value;
    }
}
