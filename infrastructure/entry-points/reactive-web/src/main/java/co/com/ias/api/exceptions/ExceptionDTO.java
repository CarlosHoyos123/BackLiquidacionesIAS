package co.com.ias.api.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExceptionDTO {

    private Integer statusNunber;
    private String msg;

    public ExceptionDTO(Integer statusNunber, String msg) {
        this.statusNunber = statusNunber;
        this.msg = msg;
    }
}
