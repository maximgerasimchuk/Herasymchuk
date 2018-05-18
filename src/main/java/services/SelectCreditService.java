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
    private List<ConsumerCredit> consumerCreditList;
    private List<CollateralCredit> collateralCreditList;

    private ConsumerCredit selectedConsumerCredit;
    private CollateralCredit selectedCollateralCredit;

    public SelectCreditService(List<CollateralCredit> collateralCredit, List<ConsumerCredit> consumerCredit) {
        this.consumerCreditList = consumerCredit;
        this.collateralCreditList = collateralCredit;
        printService = new PrintService();
    }

    public void showAllOffers() {
        printService.printHeader();
        printService.printLoans(consumerCreditList);
        printService.printLoans(collateralCreditList);
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
        System.out.println("What type of credit you need? (1 - cash; 2 - carLoan; 3 - mortgage)");
        int creditType = Integer.valueOf(scanner.nextLine());

        System.out.println("What credit amount you need ?");
        int amount = Integer.valueOf(scanner.nextLine());

        System.out.println("What term of credit you want ?");
        int term = Integer.valueOf(scanner.nextLine());

        switch (creditType) {
            case 1:
                selectedConsumerCredit = new ConsumerCredit();
                selectedConsumerCredit.setType("cash");
                selectedConsumerCredit.setSelected_term(term);
                selectedConsumerCredit.setSelected_amount(amount);
                if (!isPossibleCredit(selectedConsumerCredit)) {
                    System.out.println("No offers for selected parameters. Please try again");
                    runService();
                } else if (consumerCreditList.size() > 1) {
                    finalChoice(consumerCreditList, scanner);
                } else {
                    selectedConsumerCredit.setId(consumerCreditList.get(0).getId());
                    selectedConsumerCredit.setBank(consumerCreditList.get(0).getBank());
                    selectedConsumerCredit.setType(consumerCreditList.get(0).getType());
                    selectedConsumerCredit.setRate(consumerCreditList.get(0).getRate());
                    selectedConsumerCredit.setLoan_commission(consumerCreditList.get(0).getLoan_commission());
                    selectedConsumerCredit.setMonthly_fee(consumerCreditList.get(0).getMonthly_fee());
                    selectedConsumerCredit.setEarly_repayment_possibility(consumerCreditList.get(0).isEarly_repayment_possibility());
                    selectedConsumerCredit.setMax_amount(consumerCreditList.get(0).getMax_amount());
                    selectedConsumerCredit.setMin_amount(consumerCreditList.get(0).getMin_amount());
                    selectedConsumerCredit.setMax_term(consumerCreditList.get(0).getMax_term());
                    selectedConsumerCredit.setMin_term(consumerCreditList.get(0).getMin_term());

                    System.out.println("You have selected next offer:");
                    showAllOffers();
                    printService.printSelectedLoan(selectedConsumerCredit);
                }
                break;
            case 2:
                selectedCollateralCredit = new CollateralCredit();
                selectedCollateralCredit.setType("carLoan");
                selectedCollateralCredit.setSelected_term(term);
                selectedCollateralCredit.setSelected_amount(amount);
                if (!isPossibleCredit(selectedCollateralCredit)) {
                    System.out.println("No offers for selected parameters. Please try again");
                    runService();
                } else if (collateralCreditList.size() > 1) {
                    finalChoice(collateralCreditList, scanner);
                } else {
                    selectedCollateralCredit.setId(collateralCreditList.get(0).getId());
                    selectedCollateralCredit.setType(collateralCreditList.get(0).getType());
                    selectedCollateralCredit.setBank(collateralCreditList.get(0).getBank());
                    selectedCollateralCredit.setRate(collateralCreditList.get(0).getRate());
                    selectedCollateralCredit.setLoan_commission(collateralCreditList.get(0).getLoan_commission());
                    selectedCollateralCredit.setMonthly_fee(collateralCreditList.get(0).getMonthly_fee());
                    selectedCollateralCredit.setEarly_repayment_possibility(collateralCreditList.get(0).isEarly_repayment_possibility());
                    selectedCollateralCredit.setMax_amount(collateralCreditList.get(0).getMax_amount());
                    selectedCollateralCredit.setMin_amount(collateralCreditList.get(0).getMin_amount());
                    selectedCollateralCredit.setMax_term(collateralCreditList.get(0).getMax_term());
                    selectedCollateralCredit.setMin_term(collateralCreditList.get(0).getMin_term());
                    selectedCollateralCredit.setDown_payment(collateralCreditList.get(0).getDown_payment());
                    selectedCollateralCredit.setMandatory_insurance(collateralCreditList.get(0).isMandatory_insurance());

                    System.out.println("You have selected next offer:");
                    showAllOffers();
                    selectedCollateralCredit = enterDownPayment(scanner, selectedCollateralCredit);
                    printService.printSelectedLoan(selectedCollateralCredit);
                }
                break;
            case 3:
                selectedCollateralCredit = new CollateralCredit();
                selectedCollateralCredit.setType("mortgage");
                selectedCollateralCredit.setSelected_term(term);
                selectedCollateralCredit.setSelected_amount(amount);
                if (!isPossibleCredit(selectedCollateralCredit)) {
                    System.out.println("No offers for selected parameters. Please try again");
                    runService();
                } else if (collateralCreditList.size() > 1) {
                    finalChoice(collateralCreditList, scanner);
                } else {
                    selectedCollateralCredit.setId(collateralCreditList.get(0).getId());
                    selectedCollateralCredit.setType(collateralCreditList.get(0).getType());
                    selectedCollateralCredit.setBank(collateralCreditList.get(0).getBank());
                    selectedCollateralCredit.setRate(collateralCreditList.get(0).getRate());
                    selectedCollateralCredit.setLoan_commission(collateralCreditList.get(0).getLoan_commission());
                    selectedCollateralCredit.setMonthly_fee(collateralCreditList.get(0).getMonthly_fee());
                    selectedCollateralCredit.setEarly_repayment_possibility(collateralCreditList.get(0).isEarly_repayment_possibility());
                    selectedCollateralCredit.setMax_amount(collateralCreditList.get(0).getMax_amount());
                    selectedCollateralCredit.setMin_amount(collateralCreditList.get(0).getMin_amount());
                    selectedCollateralCredit.setMax_term(collateralCreditList.get(0).getMax_term());
                    selectedCollateralCredit.setMin_term(collateralCreditList.get(0).getMin_term());
                    selectedCollateralCredit.setDown_payment(collateralCreditList.get(0).getDown_payment());
                    selectedCollateralCredit.setMandatory_insurance(collateralCreditList.get(0).isMandatory_insurance());

                    System.out.println("You have selected next offer:");
                    showAllOffers();
                    selectedCollateralCredit = enterDownPayment(scanner, selectedCollateralCredit);
                    printService.printSelectedLoan(selectedCollateralCredit);
                }
                break;
        }
    }

    private CollateralCredit enterDownPayment(Scanner scanner, CollateralCredit selectedCollateralCredit) {
        System.out.println("Enter down payment. Minimum value is '" + selectedCollateralCredit.getDown_payment() + "'");
        int downPayment = Integer.valueOf(scanner.nextLine());
        if (downPayment < selectedCollateralCredit.getDown_payment()) {
            System.out.println("You have entered value that less than '" + selectedCollateralCredit.getDown_payment() + "'. Please re-enter greater value.");
            enterDownPayment(scanner, selectedCollateralCredit);
        } else {
            selectedCollateralCredit.setSelectedDownPayment(downPayment);
        }
        return selectedCollateralCredit;
    }

    private boolean isPossibleCredit(ConsumerCredit selectedConsumerCredit) {
        collateralCreditList.clear();

        if (!consumerCreditList.isEmpty()) {
            for (int i = consumerCreditList.size() - 1; i >= 0; i--) {
                if (consumerCreditList.get(i).getMin_amount() > selectedConsumerCredit.getSelected_amount() || consumerCreditList.get(i).getMax_amount() < selectedConsumerCredit.getSelected_amount()) {
                    consumerCreditList.remove(i);
                }
            }
        }

        if (!consumerCreditList.isEmpty()) {
            for (int i = consumerCreditList.size() - 1; i >= 0; i--) {
                if (consumerCreditList.get(i).getMin_term() > selectedConsumerCredit.getSelected_term() || consumerCreditList.get(i).getMax_term() < selectedConsumerCredit.getSelected_term()) {
                    consumerCreditList.remove(i);
                }
            }
        }

        if (consumerCreditList.isEmpty()) {
            return false;
        }
        return true;
    }

    private boolean isPossibleCredit(CollateralCredit selectedCollateralCredit) {
        consumerCreditList.clear();

        if (selectedCollateralCredit.getType().equals("carLoan")) {
            for (int i = collateralCreditList.size() - 1; i >= 0; i--) {
                if (collateralCreditList.get(i).getType().equals("mortgage")) {
                    collateralCreditList.remove(i);
                }
            }
        } else if (selectedCollateralCredit.getType().equals("mortgage")) {
            for (int i = collateralCreditList.size() - 1; i >= 0; i--) {
                if (collateralCreditList.get(i).getType().equals("carLoan")) {
                    collateralCreditList.remove(i);
                }
            }
        }

        if (!collateralCreditList.isEmpty()) {
            for (int i = collateralCreditList.size() - 1; i >= 0; i--) {
                if (collateralCreditList.get(i).getMin_amount() > selectedCollateralCredit.getSelected_amount() || collateralCreditList.get(i).getMax_amount() < selectedCollateralCredit.getSelected_amount()) {
                    collateralCreditList.remove(i);
                }
            }
        }

        if (!collateralCreditList.isEmpty()) {
            for (int i = collateralCreditList.size() - 1; i >= 0; i--) {
                if (collateralCreditList.get(i).getMin_term() > selectedCollateralCredit.getSelected_term() || collateralCreditList.get(i).getMax_term() < selectedCollateralCredit.getSelected_term()) {
                    collateralCreditList.remove(i);
                }
            }
        }

        if (collateralCreditList.isEmpty()) {
            return false;
        }
        return true;
    }

    private <T extends Loan> void finalChoice(List<T> loans, Scanner scanner) {
        System.out.println("Make your final choice. Your can select one of next credit. Type offer ID. If you want to exit - type '0'.");
        showAllOffers();
        int id = Integer.valueOf(scanner.nextLine());
        if (id == 0) {
            System.exit(0);
        } else {
            if (loans.get(0).getClass().equals(ConsumerCredit.class)) {
                for (int i = 0; i < loans.size(); i++) {
                    if (id == loans.get(i).getId()) {
                        selectedConsumerCredit.setId(consumerCreditList.get(i).getId());
                        selectedConsumerCredit.setBank(consumerCreditList.get(i).getBank());
                        selectedConsumerCredit.setType(consumerCreditList.get(i).getType());
                        selectedConsumerCredit.setRate(consumerCreditList.get(i).getRate());
                        selectedConsumerCredit.setLoan_commission(consumerCreditList.get(i).getLoan_commission());
                        selectedConsumerCredit.setMonthly_fee(consumerCreditList.get(i).getMonthly_fee());
                        selectedConsumerCredit.setEarly_repayment_possibility(consumerCreditList.get(i).isEarly_repayment_possibility());
                        selectedConsumerCredit.setMax_amount(consumerCreditList.get(i).getMax_amount());
                        selectedConsumerCredit.setMin_amount(consumerCreditList.get(i).getMin_amount());
                        selectedConsumerCredit.setMax_term(consumerCreditList.get(i).getMax_term());
                        selectedConsumerCredit.setMin_term(consumerCreditList.get(i).getMin_term());
                    }
                }
                if (selectedConsumerCredit.getId() != id) {
                    System.out.println("You entered incorrect offer ID. Please try again");
                    finalChoice(loans, scanner);
                }
                printService.printSelectedLoan(selectedConsumerCredit);
            } else {
                for (int i = 0; i < loans.size(); i++) {
                    if (id == loans.get(i).getId()) {
                        selectedCollateralCredit = setSelectedCreditParameters(selectedCollateralCredit, ((CollateralCredit) loans.get(i)));
                    }
                }
                if (selectedCollateralCredit.getId() != id) {
                    System.out.println("You entered incorrect offer ID");
                    finalChoice(loans, scanner);
                }
                selectedCollateralCredit = enterDownPayment(scanner, selectedCollateralCredit);
                printService.printSelectedLoan(selectedCollateralCredit);
            }
        }
    }

    private <T extends CollateralCredit> T setSelectedCreditParameters(T selectedCredit, T availableCredit) {
        selectedCredit.setId(availableCredit.getId());
        selectedCredit.setBank(availableCredit.getBank());
        selectedCredit.setType(availableCredit.getType());
        selectedCredit.setRate(availableCredit.getRate());
        selectedCredit.setLoan_commission(availableCredit.getLoan_commission());
        selectedCredit.setMonthly_fee(availableCredit.getMonthly_fee());
        selectedCredit.setEarly_repayment_possibility(availableCredit.isEarly_repayment_possibility());
        selectedCredit.setMax_amount(availableCredit.getMax_amount());
        selectedCredit.setMin_amount(availableCredit.getMin_amount());
        selectedCredit.setMax_term(availableCredit.getMax_term());
        selectedCredit.setMin_term(availableCredit.getMin_term());

        if (selectedCredit.getClass().equals(CollateralCredit.class)) {
            ((CollateralCredit) selectedCredit).setDown_payment(((CollateralCredit) availableCredit).getDown_payment());
            ((CollateralCredit) selectedCredit).setMandatory_insurance(((CollateralCredit) availableCredit).isMandatory_insurance());
        }
        return selectedCredit;
    }
}
