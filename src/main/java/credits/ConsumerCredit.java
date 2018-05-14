package credits;

import java.util.List;

public class ConsumerCredit {
    private int rate;
    private int loan_commission;
    private int monthly_fee;
    private boolean early_repayment_possibility;
    private int max_amount;
    private int min_amount;
    private int selected_amount;
    private int max_term;
    private int min_term;
    private int selected_term;
    private String bank;
    private String type;

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public int getLoan_commission() {
        return loan_commission;
    }

    public void setLoan_commission(int loan_commission) {
        this.loan_commission = loan_commission;
    }

    public int getMonthly_fee() {
        return monthly_fee;
    }

    public void setMonthly_fee(int monthly_fee) {
        this.monthly_fee = monthly_fee;
    }

    public boolean isEarly_repayment_possibility() {
        return early_repayment_possibility;
    }

    public void setEarly_repayment_possibility(boolean early_repayment_possibility) {
        this.early_repayment_possibility = early_repayment_possibility;
    }

    public int getMax_amount() {
        return max_amount;
    }

    public void setMax_amount(int max_amount) {
        this.max_amount = max_amount;
    }

    public int getMin_amount() {
        return min_amount;
    }

    public void setMin_amount(int min_amount) {
        this.min_amount = min_amount;
    }

    public int getMax_term() {
        return max_term;
    }

    public void setMax_term(int max_term) {
        this.max_term = max_term;
    }

    public int getMin_term() {
        return min_term;
    }

    public void setMin_term(int min_term) {
        this.min_term = min_term;
    }

    public int getSelected_term() {
        return selected_term;
    }

    public void setSelected_term(int selected_term) {
        this.selected_term = selected_term;
    }

    public int getSelected_amount() {
        return selected_amount;
    }

    public void setSelected_amount(int selected_amount) {
        this.selected_amount = selected_amount;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ConsumerCredit(int rate, int loan_commission, int monthly_fee, boolean early_repayment_possibility, int max_amount, int min_amount, int selected_term, int max_term, int min_term, String bank, String type) {
        this.rate = rate;
        this.loan_commission = loan_commission;
        this.monthly_fee = monthly_fee;
        this.early_repayment_possibility = early_repayment_possibility;
        this.max_amount = max_amount;
        this.min_amount = min_amount;
        this.max_term = max_term;
        this.min_term = min_term;
        this.selected_term = selected_term;
        this.bank = bank;
        this.type = type;
    }

    public ConsumerCredit() {
    }

    public int calculateCreditOverpayment(){
        return selected_amount * rate / 12 * selected_term + monthly_fee + selected_amount * monthly_fee * selected_term + selected_amount * loan_commission;
    }

    public void print() {
        final Object[][] table = new Object[2][];
        table[0] = new String[] {"LoanType", "Bank", "Rate", "LoanCommission", "MonthlyFee",
                "earlyRepaymentPossibility", "MaxAmount", "MinAmount", "MaxTerm", "MinTerm"};

        table[1] = new String[]{
                getType(),
                getBank(),
                String.valueOf(getRate()),
                String.valueOf(getLoan_commission()),
                String.valueOf(getMonthly_fee()),
                String.valueOf(isEarly_repayment_possibility()),
                String.valueOf(getMax_amount()),
                String.valueOf(getMin_amount()),
                String.valueOf(getMax_term()),
                String.valueOf(getMin_term())
        };

        for (final Object[] row: table){
            System.out.format("%15s%15s%12s%20s%15s%30s%12s%12s%12s%12s\n", row);
        }
    }
}
