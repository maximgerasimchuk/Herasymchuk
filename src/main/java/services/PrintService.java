package services;

import credits.CollateralCredit;
import credits.ConsumerCredit;
import credits.Loan;

import java.util.List;

public class PrintService {
    public PrintService() {
    }

    public void printHeader(){
        final Object[][] tableHeader = new Object[1][];
        tableHeader[0] = new String[]{"LoanType", "Bank", "Rate", "LoanCommission", "MonthlyFee",
                "earlyRepaymentPossibility", "MaxAmount", "MinAmount", "MaxTerm", "MinTerm",
                "DownPayment", "MandatoryInsurance"};
        for (final Object[] row : tableHeader) {
            System.out.format("%15s%15s%12s%20s%15s%30s%12s%12s%12s%12s%15s%20s\n", row);
        }
    }

    public <T extends Loan> void printLoans(List<T> loans) {
        for (int i = 0; i < loans.size(); i++) {
            loans.get(i).print();
        }
    }
}
