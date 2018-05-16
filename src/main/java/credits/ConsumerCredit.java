package credits;

import java.util.List;

public class ConsumerCredit extends Loan {

    public ConsumerCredit() {
        super();
    }

    public ConsumerCredit(int id, float rate, float loan_commission, float monthly_fee, boolean early_repayment_possibility, int max_amount, int min_amount, int max_term, int min_term, int selected_term, String bank, String type) {
        super(id, rate, loan_commission, monthly_fee, early_repayment_possibility, max_amount, min_amount, max_term, min_term, selected_term, bank, type);
    }
}
