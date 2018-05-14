package credits;

import java.util.List;

public class CollateralCredit extends ConsumerCredit {
    private boolean mandatory_insurance;
    private int down_payment;

    public boolean isMandatory_insurance() {
        return mandatory_insurance;
    }

    public void setMandatory_insurance(boolean mandatory_insurance) {
        this.mandatory_insurance = mandatory_insurance;
    }

    public int getDown_payment() {
        return down_payment;
    }

    public void setDown_payment(int down_payment) {
        this.down_payment = down_payment;
    }

    public CollateralCredit(int rate, int loan_commission, int monthly_fee, boolean early_repayment_possibility, int max_amount, int min_amount, int selected_term, int max_term, int min_term, String bank, String type, boolean mandatory_insurance, int down_payment) {
        super(rate, loan_commission, monthly_fee, early_repayment_possibility, max_amount, min_amount, selected_term, max_term, min_term, bank, type);
        this.mandatory_insurance = mandatory_insurance;
        this.down_payment = down_payment;
    }

    public CollateralCredit() {
    }

    //monthly_fee doesn't use for calculate overpayment
    @Override
    public int calculateCreditOverpayment(){
        return super.getSelected_amount() * super.getRate() / 12 * super.getSelected_term() + super.getMonthly_fee() + super.getSelected_amount() * super.getLoan_commission();
    }

    public void print() {
        final Object[][] table = new Object[2][];
        table[0] = new String[] {"LoanType", "Bank", "Rate", "LoanCommission", "MonthlyFee",
                "earlyRepaymentPossibility", "MaxAmount", "MinAmount", "MaxTerm", "MinTerm",
                "DownPayment", "MandatoryInsurance"};

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
                    String.valueOf(getMin_term()),
                    String.valueOf(getDown_payment()),
                    String.valueOf(isMandatory_insurance())
            };

        for (final Object[] row: table){
            System.out.format("%15s%15s%12s%20s%15s%30s%12s%12s%12s%12s%12s%12s\n", row);
        }
    }
}
