package creditsConsoleApp.credits;

public class CollateralCredit extends Loan {
    private boolean mandatory_insurance;
    private int down_payment;
    private int selectedDownPayment;

    public boolean isMandatory_insurance() {
        return mandatory_insurance;
    }

    public CollateralCredit setMandatory_insurance(boolean mandatory_insurance) {
        this.mandatory_insurance = mandatory_insurance;
        return this;
    }

    public int getDown_payment() {
        return down_payment;
    }

    public CollateralCredit setDown_payment(int down_payment) {
        this.down_payment = down_payment;
        return this;
    }

    public int getSelectedDownPayment() {
        return selectedDownPayment;
    }

    public CollateralCredit setSelectedDownPayment(int selectedDownPayment) {
        this.selectedDownPayment = selectedDownPayment;
        return this;
    }

    public CollateralCredit(int id, float rate, float loan_commission, float monthly_fee, boolean early_repayment_possibility, int max_amount, int min_amount, int max_term, int min_term, int selected_term, String bank, String type) {
        super(id, rate, loan_commission, monthly_fee, early_repayment_possibility, max_amount, min_amount, max_term, min_term, selected_term, bank, type);
        this.mandatory_insurance = mandatory_insurance;
        this.down_payment = down_payment;
    }

    public CollateralCredit() {
    }

    @Override
    public float calculateCreditOverpayment() {
        return calculateFirstMonthlyRepayment(getSelected_amount(), selectedDownPayment) * super.getSelected_term() - super.getSelected_amount();
    }

    public float calculateFirstMonthlyRepayment(int amount, int downPayment) {
        amount = amount - downPayment;
        return amount / super.getMax_term() + amount * super.getMonthly_fee() + amount * super.getRate() / 12 / 100;
    }

    @Override
    public void print() {
        final Object[][] table = new Object[1][];
        table[0] = new String[]{
                String.valueOf(getId()),
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
                String.valueOf(down_payment),
                String.valueOf(mandatory_insurance)
        };

        for (final Object[] row : table) {
            System.out.format("%10s%15s%15s%12s%20s%15s%30s%12s%12s%12s%12s%15s%20s\n", row);
        }
    }

    @Override
    public void printSelectedCredit() {
        final Object[][] table = new Object[1][];
        table[0] = new String[]{
                String.valueOf(getId()),
                getType(),
                getBank(),
                String.valueOf(getRate()),
                String.valueOf(getLoan_commission()),
                String.valueOf(getMonthly_fee()),
                String.valueOf(isEarly_repayment_possibility()),
                String.valueOf(getSelected_amount()),
                String.valueOf(getSelected_term()),
                String.valueOf(getRepaymentAmount()),
                String.valueOf(getOverpayment()),
                String.valueOf(selectedDownPayment),
                String.valueOf(mandatory_insurance)
        };
        for (final Object[] row : table) {
            System.out.format("%10s%15s%15s%12s%20s%15s%30s%18s%18s%20s%18s%24s%22s\n", row);
        }
    }
}
