package co.com.ias.model.employee.values;

import lombok.Builder;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Indate {

    private LocalDate value;

    public Indate(LocalDate value) {
        validation(value);
        this.value = value;
    }

    private void validation(LocalDate value) throws IllegalArgumentException{
        //notNull(value, "the date can't be empty");
        try {value.format(DateTimeFormatter.ofPattern("YYYY/MM/dd"));}
        catch (Exception e) {
            throw new IllegalArgumentException("Formato incorrecto de fecha: YYYY/MM/DD");
        }
        if (value.isAfter(LocalDate.of(2015,12,31)))
            throw new IllegalArgumentException("Date above of range");
        else if (value.isBefore(LocalDate.of(2015,1,1)))
            throw new IllegalArgumentException("Date below of range");
        else if (value.equals(""))
            throw new IllegalArgumentException("Date can't be empty");
    }

    public LocalDate getValue() {
        return value;
    }
}
