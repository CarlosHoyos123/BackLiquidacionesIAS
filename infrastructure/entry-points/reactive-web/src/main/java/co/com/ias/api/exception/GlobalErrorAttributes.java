package co.com.ias.api.exception;

import co.com.ias.api.exception.Personalized.SettlementDateInvalid;
import co.com.ias.api.exception.enums.ErrorAttributesEnums;
import co.com.ias.api.exception.Personalized.PeriodDateNoValid;
import co.com.ias.usecase.Exceptions.InvalidPeriodDate;
import co.com.ias.usecase.Exceptions.NotFoundEmployee;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
import org.springframework.core.annotation.MergedAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.server.*;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

record ExceptionRule(Class<?> exceptionClass, HttpStatus status){}

@Component
public class GlobalErrorAttributes extends DefaultErrorAttributes {

    private final List<ExceptionRule> exceptionsRules = List.of
            (
                new ExceptionRule(PeriodDateNoValid.class, HttpStatus.UNAUTHORIZED),
                new ExceptionRule(InvalidPeriodDate.class, HttpStatus.NOT_ACCEPTABLE),
                new ExceptionRule(SettlementDateInvalid.class, HttpStatus.NOT_ACCEPTABLE),
                new ExceptionRule(IllegalArgumentException.class, HttpStatus.NOT_ACCEPTABLE),
                new ExceptionRule(NotFoundEmployee.class, HttpStatus.NOT_FOUND)
    );

    @Override
    public Map<String, Object> getErrorAttributes(ServerRequest request, ErrorAttributeOptions options) {
        Throwable error = getError(request);

        final String timestamp = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
                Optional<ExceptionRule> exceptionRuleOptional = exceptionsRules.stream()
                .map(exceptionRule -> exceptionRule.exceptionClass().isInstance(error) ?
                        exceptionRule :
                        null)
                .filter(Objects::nonNull)
                .findFirst();

        return exceptionRuleOptional.<Map<String, Object>>map(
                exceptionRule -> Map.of
                        (ErrorAttributesEnums.CODE.getKey(),
                        exceptionRule.status().value(),
                        ErrorAttributesEnums.MESSAGE.getKey(),
                        error.getMessage(),
                        ErrorAttributesEnums.TIME.getKey(),
                        timestamp)
                )
                .orElseGet(
                        () -> Map.of(
                                ErrorAttributesEnums.CODE.getKey(),
                                determineHttpStatus(error).value(),
                                ErrorAttributesEnums.MESSAGE.getKey(),
                                error.getMessage(),
                                ErrorAttributesEnums.TIME.getKey(),
                                timestamp)
                );
    }

    private HttpStatus determineHttpStatus(Throwable error) {
        return error instanceof
                ResponseStatusException err ?
                (HttpStatus) err.getStatusCode() :
                    MergedAnnotations.from( error.getClass(),
                                            MergedAnnotations.SearchStrategy.TYPE_HIERARCHY).
                                            get(ResponseStatus.class).
                                            getValue(ErrorAttributesEnums.CODE.getKey(), HttpStatus.class).
                                            orElse(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
