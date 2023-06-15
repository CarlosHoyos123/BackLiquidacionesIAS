package co.com.ias.model.employee.values;

import lombok.Builder;

@Builder
public class IdEmployee {

    private Long value;

    public Long getValue() {
        return value;
    }
}
