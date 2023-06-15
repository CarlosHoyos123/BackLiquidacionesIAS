package co.com.ias.model.employee.values;

import lombok.Builder;

import java.time.LocalDate;

public class Indate {

    private LocalDate value;

    public Indate(LocalDate value) {
        validation(value);
        this.value = value;
    }

    private void validation(LocalDate value) throws IllegalArgumentException{
        //notNull(value, "the date can't be empty");
        if (value.isAfter(LocalDate.of(2015,6,6)))
            throw new IllegalArgumentException("Date above of range");
        else if (value.isBefore(LocalDate.of(2015,1,1)))
            throw new IllegalArgumentException("Date below of range");
    }

    public LocalDate getValue() {
        return value;
    }
}
