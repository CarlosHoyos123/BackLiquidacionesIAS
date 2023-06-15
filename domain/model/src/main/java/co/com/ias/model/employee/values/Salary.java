package co.com.ias.model.employee.values;

import co.com.ias.model.employee.constants.Constants;
import lombok.Builder;


public class Salary {

    private Float value;

    public Salary(Float value) {
        validation(value);
        this.value = value;
    }

    private void validation(Float value) throws IllegalArgumentException{
        Float SMMLV = Constants.getSMMLV();
        if (value <= SMMLV || value > 7000000){
            throw new IllegalArgumentException("Salary must be between 1 SVML and 7 millions");
        }
    }

    public Float getValue() {
        return value;
    }
}
