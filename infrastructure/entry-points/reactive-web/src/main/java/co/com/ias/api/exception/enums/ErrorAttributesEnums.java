package co.com.ias.api.exception.enums;

import lombok.Getter;

@Getter
public enum ErrorAttributesEnums {

    CODE("code"),
    MESSAGE("message"),
    TIME("timestamp");

    private final String key;
    ErrorAttributesEnums(String key) {
        this.key = key;
    }

}
