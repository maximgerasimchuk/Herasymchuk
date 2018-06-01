package creditsConsoleApp.services;

import creditsConsoleApp.credits.Loan;

import java.util.List;

public class PrintService {
    public PrintService() {
    }

    public void printHeader() {
        final Object[][] tableHeader = new Object[1][];
        tableHeader[0] = new String[]{"ID", "LoanType", "Bank", "Rate", "LoanCommission", "MonthlyFee",
                "earlyRepaymentPossibility", "MaxAmount", "MinAmount", "MaxTerm", "MinTerm",
                "DownPayment", "MandatoryInsurance"};
        for (final Object[] row : tableHeader) {
            System.out.format("%10s%15s%15s%12s%20s%15s%30s%12s%12s%12s%12s%15s%20s\n", row);
        }
    }

    public <T extends Loan> void printLoans(List<T> loans) {
        for (int i = 0; i < loans.size(); i++) {
            loans.get(i).print();
        }
    }

    public <T extends Loan> void printSelectedLoan(T selectedCredit) {
        System.out.println("Your final choice with next parameters:");
        final Object[][] tableHeader = new Object[1][];
        tableHeader[0] = new String[]{
                "ID",
                "LoanType",
                "Bank",
                "Rate",
                "LoanCommission",
                "MonthlyFee",
                "earlyRepaymentPossibility",
                "SelectedAmount",
                "SelectedTerm",
                "RepaymentAmount",
                "Overpayment",
                "SelectedDownPayment",
                "MandatoryInsurance"};
        for (final Object[] row : tableHeader) {
            System.out.format("%10s%15s%15s%12s%20s%15s%30s%18s%18s%20s%18s%24s%22s\n", row);
        }
        selectedCredit.printSelectedCredit();
    }
}
