package co.com.ias.model.employee.values;

import lombok.Builder;

import java.util.regex.Pattern;

public class Cargo {

    private String value;

    public Cargo(String value) {
        validation(value);
        this.value = value;
    }

    private void validation(String value) throws IllegalArgumentException{
        if(value.isEmpty()){
            throw new IllegalArgumentException("Cargo can't be empty");
        }else if(value.length() > 30 || value.length() < 10){
            throw new IllegalArgumentException("lenght must be between 10 and 30");
        }else if(Pattern.matches(".*[^a-zA-Z0-9 ].*", value)){
            throw new IllegalArgumentException("Special characters in cargo not allowed.");
        }
    }

    public String getValue() {
        return value;
    }
}
