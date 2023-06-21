package co.com.ias.model.settlement.values;

public class WithdrawalReason {

    private String value;

    public WithdrawalReason(String value) {
        validation(value);
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void validation(String value) throws IllegalArgumentException{
        if(!value.toLowerCase().equals("voluntario") && !value.toLowerCase().equals("injustificado") && !value.toLowerCase().equals("justificado"))
            throw new IllegalArgumentException("WithdrawalReason does not apply.");
    }
}
