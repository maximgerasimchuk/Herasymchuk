package services;

import credits.CollateralCredit;
import credits.ConsumerCredit;
import credits.CreditData;
import credits.Loan;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SelectCreditService {
    private PrintService printService;
    private List<? extends Loan> consumerCredit;
    private List<? extends Loan> collateralCredit;

    private Loan selectedCredit;

    public SelectCreditService(List<CollateralCredit> collateralCredit, List<ConsumerCredit> consumerCredit) {
        this.consumerCredit = consumerCredit;
        this.collateralCredit = collateralCredit;
        printService = new PrintService();
    }

    public void showAllOffers() {
        printService.printHeader();
        printService.printLoans(consumerCredit);
        printService.printLoans(collateralCredit);
    }

    public void runService() {
        System.out.println("Your can select any credit offer from list above:");
        showAllOffers();
        Scanner scanner = new Scanner(System.in);

        System.out.println("How do you want to select credit? " + '\n' + "1 - by parameters; \n2 - by monthly repayment amount;\n0 - exit");

        switch (scanner.nextLine()) {
            case "0":
                System.exit(0);
            case "1":
                selectCreditByParameters(scanner);
                break;
            case "2":
                selectCreditByRepaymentAmount(scanner);
                break;
            default:
                System.out.println("Please make your choice");
                runService();
        }

    }

    private void selectCreditByRepaymentAmount(Scanner scanner) {

    }

    private void selectCreditByParameters(Scanner scanner) {
        System.out.println("What type of credit you need? (1 - cash; 2 - carLoan; 3 - mortgage");
        int creditType = Integer.valueOf(scanner.nextLine());

        System.out.println("What credit amount you need ?");
        int amount = Integer.valueOf(scanner.nextLine());

        System.out.println("What term of credit you want ?");
        int term = Integer.valueOf(scanner.nextLine());

        switch (creditType) {
            case 1:
                selectedCredit = new ConsumerCredit();
                selectedCredit.setType("cash");
                break;
            case 2:
                selectedCredit = new CollateralCredit();
                selectedCredit.setType("carLoan");
                break;
            case 3:
                selectedCredit = new CollateralCredit();
                selectedCredit.setType("mortgage");
                break;
        }
        selectedCredit.setSelected_term(term);
        selectedCredit.setSelected_amount(amount);

        boolean isPossibleCredit = isPossibleCredit();
        if (!isPossibleCredit) {
            System.out.println("No offers for selected parameters. Please try again");
            runService();
        } else if (consumerCredit.size() > 1) {
            finalChoice(consumerCredit);
        } else if (collateralCredit.size() > 1){
            finalChoice(collateralCredit);
        }
    }

    private boolean isPossibleCredit() {
        if (selectedCredit.getType().equals("cash")) {
            collateralCredit.clear();
        } else if (selectedCredit.getType().equals("carLoan")) {
            consumerCredit.clear();
            for (int i = collateralCredit.size() - 1; i >= 0; i--) {
                if (collateralCredit.get(i).getType().equals("mortgage")) {
                    collateralCredit.remove(i);
                }
            }
        } else if (selectedCredit.getType().equals("mortgage")) {
            consumerCredit.clear();
            for (int i = collateralCredit.size() - 1; i >= 0; i--) {
                if (collateralCredit.get(i).getType().equals("carLoan")) {
                    collateralCredit.remove(i);
                }
            }
        }

        if (!consumerCredit.isEmpty()) {
            for (int i = consumerCredit.size() - 1; i >= 0; i--) {
                if (consumerCredit.get(i).getMin_amount() > selectedCredit.getSelected_amount() || consumerCredit.get(i).getMax_amount() < selectedCredit.getSelected_amount()) {
                    consumerCredit.remove(i);
                }
            }
        }
        if (!collateralCredit.isEmpty()) {
            for (int i = collateralCredit.size() - 1; i >= 0; i--) {
                if (collateralCredit.get(i).getMin_amount() > selectedCredit.getSelected_amount() || collateralCredit.get(i).getMax_amount() < selectedCredit.getSelected_amount()) {
                    collateralCredit.remove(i);
                }
            }
        }

        if (!consumerCredit.isEmpty()) {
            for (int i = consumerCredit.size() - 1; i >= 0; i--) {
                if (consumerCredit.get(i).getMin_term() > selectedCredit.getSelected_term() || consumerCredit.get(i).getMax_term() < selectedCredit.getSelected_term()) {
                    consumerCredit.remove(i);
                    System.out.println("Your have selected unsupported credit amount");
                }
            }
        }

        if (!collateralCredit.isEmpty()) {
            for (int i = collateralCredit.size() - 1; i >= 0; i--) {
                if (collateralCredit.get(i).getMin_term() > selectedCredit.getSelected_term() || collateralCredit.get(i).getMax_term() < selectedCredit.getSelected_term()) {
                    collateralCredit.remove(i);
                    System.out.println("Your have selected unsupported credit amount");
                }
            }
        }

        if (consumerCredit.isEmpty() && collateralCredit.isEmpty()) {
            return false;
        }
        return false;
    }

    private <T extends Loan> void finalChoice(List<T> loans) {
        System.out.println("Make your final choice. Your can select one of next credit:");
        showAllOffers();

    }
}
