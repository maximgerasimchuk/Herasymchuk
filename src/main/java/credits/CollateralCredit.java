package credits;

public class CollateralCredit extends Loan {
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

    public CollateralCredit(int rate, int loan_commission, int monthly_fee, boolean early_repayment_possibility, int max_amount, int min_amount, int selected_term, int max_term, int min_term, String bank, boolean mandatory_insurance, boolean down_payment) {
        super(rate, loan_commission, monthly_fee, early_repayment_possibility, max_amount, min_amount, selected_term, max_term, min_term, bank);
        this.mandatory_insurance = mandatory_insurance;
        this.down_payment = down_payment;
    }

    public CollateralCredit () {
    }

    //monthly_fee doesn't use for calculate overpayment
    @Override
    public int calculateCreditOverpayment(){
        return super.getSelected_amount() * super.getRate() / 12 * super.getSelected_term() + super.getMonthly_fee() + super.getSelected_amount() * super.getLoan_commission();
    }
}
