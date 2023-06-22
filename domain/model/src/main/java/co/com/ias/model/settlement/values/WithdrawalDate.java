package co.com.ias.model.settlement.values;

import java.time.LocalDate;

public class WithdrawalDate {

    private LocalDate value;

    public WithdrawalDate(LocalDate value) {
        validation(value);
        this.value = value;
    }

    public LocalDate getValue() {
        return value;
    }

    public void validation(LocalDate value){

    };
}
