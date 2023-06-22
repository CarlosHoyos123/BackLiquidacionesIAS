package co.com.ias.model.employee.constants;

public class Constants {
    private static Float SMMLV = 1300606F;
    private static Float TransSupportValue = 102854F;
    private static Float deadLineForTransSupport = 2*SMMLV;
    public static Float getSMMLV() {
        return SMMLV;
    }
    public static Float getTransSupportValue() {
        return TransSupportValue;
    }
    public static Float getDeadLineForTransSupport() {
        return deadLineForTransSupport;
    }
}
