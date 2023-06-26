package co.com.ias.model.settlement.values;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
        try {value.format(DateTimeFormatter.ofPattern("YYYY/MM/dd"));}
        catch (Exception e) {
            throw new IllegalArgumentException("Formato incorrecto de fecha: YYYY/MM/DD");
        }
        if (value.isBefore(LocalDate.of(2015,1,1)))
            throw new IllegalArgumentException("Date can't be prior than contact date");
        else if (value.equals(""))
            throw new IllegalArgumentException("Date can't be empty");
    };
}
