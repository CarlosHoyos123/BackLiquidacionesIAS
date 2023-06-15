package co.com.ias.model.employee.values;

import lombok.Builder;

import java.util.regex.Pattern;

public class Name {

    private String value;

    public Name(String value) {
        validation(value);
        this.value = value;
    }

    private void validation(String value) throws IllegalArgumentException{
        if (Pattern.matches(".*[^a-zA-Z0-9 ].*", value)){
            throw new IllegalArgumentException("Special characters not allowed.");
        }else if (value.length() >= 50){
            throw new IllegalArgumentException("Name size  is 50 characters max");
        } else if (value.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
    }

    public String getValue() {
        return value;
    }
}
