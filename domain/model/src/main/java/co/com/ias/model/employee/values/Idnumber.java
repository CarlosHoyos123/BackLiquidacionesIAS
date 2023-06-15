package co.com.ias.model.employee.values;

import lombok.Builder;

public class Idnumber{

    private String value;

    public Idnumber(String value) {
        validation(value);
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    private void validation(String value) throws IllegalArgumentException{
        if(value.isEmpty()){
            throw new IllegalArgumentException("Id number can't be empty");
        }else if(value.length() > 15 || value.length() < 7){
            throw new IllegalArgumentException("lenght must be between 7 and 15");
        }
    }
}
