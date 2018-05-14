package entity;

public class Loan {
    private int rate;
    private int loan_commission;
    private int monthly_fee;
    private boolean early_repayment_possibility;
    private int max_amount;
    private int min_amount;
    private int max_term;
    private int min_term;
    private String bank;

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

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public Loan(int rate, int loan_commission, int monthly_fee, boolean early_repayment_possibility, int max_amount, int min_amount, int max_term, int min_term, String bank) {
        this.rate = rate;
        this.loan_commission = loan_commission;
        this.monthly_fee = monthly_fee;
        this.early_repayment_possibility = early_repayment_possibility;
        this.max_amount = max_amount;
        this.min_amount = min_amount;
        this.max_term = max_term;
        this.min_term = min_term;
        this.bank = bank;
    }

    public Loan() {
    }
}
