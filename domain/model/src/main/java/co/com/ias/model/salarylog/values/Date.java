package co.com.ias.model.salarylog.values;

import java.time.LocalDate;

public class Date {
    private LocalDate value;

    public Date(LocalDate value) {
        this.value = value;
    }

    public LocalDate getValue() {
        return value;
    }
}
