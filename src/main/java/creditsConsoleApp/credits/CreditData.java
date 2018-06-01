package creditsConsoleApp.credits;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;

public class CreditData {
    @JsonProperty()
    private HashMap<String, ConsumerCredit> cash_loan;
    @JsonProperty()
    private HashMap<String, CollateralCredit> car_loan;
    @JsonProperty()
    private HashMap<String, CollateralCredit> mortgage;

    public HashMap<String, ConsumerCredit> getCash_loan() {
        return cash_loan;
    }

    public HashMap<String, CollateralCredit> getCar_loan() {
        return car_loan;
    }

    public HashMap<String, CollateralCredit> getMortgage() {
        return mortgage;
    }
}
