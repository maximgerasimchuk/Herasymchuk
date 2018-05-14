package entity;

public class СollateralLoan extends Loan {
    private boolean mandatory_insurance;
    private boolean down_payment;

    public boolean isMandatory_insurance() {
        return mandatory_insurance;
    }

    public void setMandatory_insurance(boolean mandatory_insurance) {
        this.mandatory_insurance = mandatory_insurance;
    }

    public boolean isDown_payment() {
        return down_payment;
    }

    public void setDown_payment(boolean down_payment) {
        this.down_payment = down_payment;
    }

    public СollateralLoan(int rate, int loan_commission, int monthly_fee, boolean early_repayment_possibility, int max_amount, int min_amount, int max_term, int min_term, String bank, boolean mandatory_insurance, boolean down_payment) {
        super(rate, loan_commission, monthly_fee, early_repayment_possibility, max_amount, min_amount, max_term, min_term, bank);
        this.mandatory_insurance = mandatory_insurance;
        this.down_payment = down_payment;
    }

    public СollateralLoan() {
    }
}
