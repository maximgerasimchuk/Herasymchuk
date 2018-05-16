package credits;

public class Loan {
    private int id;
    private float rate;
    private float loan_commission;
    private float monthly_fee;
    private boolean early_repayment_possibility;
    private int max_amount;
    private int min_amount;
    private int selected_amount;
    private int max_term;
    private int min_term;
    private int selected_term;
    private String bank;
    private String type;

    public int getId() {
        return id;
    }

    public Loan setId(int id) {
        this.id = id;
        return this;
    }

    public float getRate() {
        return rate;
    }

    public Loan setRate(float rate) {
        this.rate = rate;
        return this;
    }

    public float getLoan_commission() {
        return loan_commission;
    }

    public Loan setLoan_commission(float loan_commission) {
        this.loan_commission = loan_commission;
        return this;
    }

    public float getMonthly_fee() {
        return monthly_fee;
    }

    public Loan setMonthly_fee(float monthly_fee) {
        this.monthly_fee = monthly_fee;
        return this;
    }

    public boolean isEarly_repayment_possibility() {
        return early_repayment_possibility;
    }

    public Loan setEarly_repayment_possibility(boolean early_repayment_possibility) {
        this.early_repayment_possibility = early_repayment_possibility;
        return this;
    }

    public int getMax_amount() {
        return max_amount;
    }

    public Loan setMax_amount(int max_amount) {
        this.max_amount = max_amount;
        return this;
    }

    public int getMin_amount() {
        return min_amount;
    }

    public Loan setMin_amount(int min_amount) {
        this.min_amount = min_amount;
        return this;
    }

    public int getSelected_amount() {
        return selected_amount;
    }

    public Loan setSelected_amount(int selected_amount) {
        this.selected_amount = selected_amount;
        return this;
    }

    public int getMax_term() {
        return max_term;
    }

    public Loan setMax_term(int max_term) {
        this.max_term = max_term;
        return this;
    }

    public int getMin_term() {
        return min_term;
    }

    public Loan setMin_term(int min_term) {
        this.min_term = min_term;
        return this;
    }

    public int getSelected_term() {
        return selected_term;
    }

    public Loan setSelected_term(int selected_term) {
        this.selected_term = selected_term;
        return this;
    }

    public String getBank() {
        return bank;
    }

    public Loan setBank(String bank) {
        this.bank = bank;
        return this;
    }

    public String getType() {
        return type;
    }

    public Loan setType(String type) {
        this.type = type;
        return this;
    }

    public Loan() {
    }

    public Loan(int id, float rate, float loan_commission, float monthly_fee, boolean early_repayment_possibility, int max_amount, int min_amount, int max_term, int min_term, int selected_term, String bank, String type) {
        this.id = id;
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

    public float calculateCreditOverpayment() {
        return selected_amount * rate / 12 * selected_term + monthly_fee + selected_amount * monthly_fee * selected_term + selected_amount * loan_commission;
    }

    public void print() {
        final Object[][] table = new Object[1][];
        table[0] = new String[]{
                String.valueOf(id),
                type,
                bank,
                String.valueOf(rate),
                String.valueOf(loan_commission),
                String.valueOf(monthly_fee),
                String.valueOf(early_repayment_possibility),
                String.valueOf(max_amount),
                String.valueOf(min_amount),
                String.valueOf(max_term),
                String.valueOf(min_term)
        };

        for (final Object[] row : table) {
            System.out.format("%10s%15s%15s%12s%20s%15s%30s%12s%12s%12s%12s\n", row);
        }
    }

    public void printSelectedCredit(){
        final Object[][] table = new Object[1][];
        table[0] = new String[]{
                String.valueOf(id),
                type,
                bank,
                String.valueOf(rate),
                String.valueOf(loan_commission),
                String.valueOf(monthly_fee),
                String.valueOf(early_repayment_possibility),
                String.valueOf(selected_amount),
                String.valueOf(selected_term),
        };
        for (final Object[] row : table) {
            System.out.format("%10s%15s%15s%12s%20s%15s%30s%18s%18s\n", row);
        }
    }
}
