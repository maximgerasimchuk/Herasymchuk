package services;

import credits.CollateralCredit;
import credits.ConsumerCredit;
import credits.CreditData;
import credits.Loan;

import java.util.List;
import java.util.Scanner;

public class SelectCreditService {
    private PrintService printService;
    private List<CollateralCredit> collateralCredit;
    private List<ConsumerCredit> consumerCredit;

    private Loan selectedCredit;

    public SelectCreditService(List<CollateralCredit> collateralCredit, List<ConsumerCredit> consumerCredit) {
        this.collateralCredit = collateralCredit;
        this.consumerCredit = consumerCredit;
        printService = new PrintService();
    }

    public void showAllOffers(List<Loan> loans) {
        printService.printLoans(loans);
    }

    public void runService() {
        System.out.println("Your can select any credit offer from list above:");
        showAllOffers(consumerCredit);
        showAllOffers(collateralCredit);
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

        boolean isPossibleCredit = isPosibleCredit(selectedCredit);
        if (isPossibleCredit){
            System.out.println("For choice your can select one of next credit:");
            showAllOffers(consumerCredit);
        } else {
            System.out.println("No offers for selected parameters. Please try again");
            runService();
        }
    }

    private boolean isPosibleCredit(Loan selectedCredit) {
        if (selectedCredit.getType().equals("cash")){
            collateralCredit.clear();
        } else {
            consumerCredit.clear();
        }

        for (int i = 0; i < consumerCredit.size(); i++){
            if (consumerCredit.get(i).getMin_amount() > selectedCredit.getSelected_amount() || consumerCredit.get(i).getMax_amount() < selectedCredit.getSelected_amount()){
                consumerCredit.remove(i);
            }
        }

        for (int i = 0; i < collateralCredit.size(); i++){
            if (collateralCredit.get(i).getMin_amount() > selectedCredit.getSelected_amount() || collateralCredit.get(i).getMax_amount() < selectedCredit.getSelected_amount()){
                collateralCredit.remove(i);
            }
        }

        for (int i = 0; i < consumerCredit.size(); i++){
            if (consumerCredit.get(i).getMin_term() > selectedCredit.getSelected_term() || consumerCredit.get(i).getMax_term() < selectedCredit.getSelected_term()){
                consumerCredit.remove(i);
                System.out.println("Your have selected unsupported credit amount");
            }
        }

        for (int i = 0; i < collateralCredit.size(); i++){
            if (collateralCredit.get(i).getMin_term() > selectedCredit.getSelected_term() || collateralCredit.get(i).getMax_term() < selectedCredit.getSelected_term()){
                collateralCredit.remove(i);
                System.out.println("Your have selected unsupported credit amount");
            }
        }

        if (consumerCredit.isEmpty() && collateralCredit.isEmpty()){
            return false;
        }
        return false;
    }
}
