package creditsConsoleApp.services;

import creditsConsoleApp.credits.CollateralCredit;
import creditsConsoleApp.credits.ConsumerCredit;
import creditsConsoleApp.credits.Loan;

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

        System.out.println("How do you want to select credit? " + '\n' + "1 - by parameters; \n2 - by first repayment amount;\n0 - exit");

        switch (scanner.nextLine()) {
            case "0":
                System.exit(0);
            case "1":
                selectCreditByParameters(scanner);
                break;
            case "2":
                selectCreditByFirstRepaymentAmount(scanner);
                break;
            default:
                System.out.println("Please make your choice");
                runService();
        }

    }

    private void selectCreditByFirstRepaymentAmount(Scanner scanner) {
        System.out.println("What type of credit do you need? (1 - cash; 2 - carLoan; 3 - mortgage)");
        int creditType = Integer.valueOf(scanner.nextLine());

        System.out.println("How much many do you need?");
        int enteredCreditAmount = Integer.valueOf(scanner.nextLine());

        System.out.println("What month repayment amount you can pay every month?");
        int enteredRepaymentAmount = Integer.valueOf(scanner.nextLine());

        int enteredDownPayment = 0;
        if (creditType != 1) {
            System.out.println("What down payment you can pay? (UAH)");
            enteredDownPayment = Integer.valueOf(scanner.nextLine());
        }

        switch (creditType) {
            case 1:
                selectedConsumerCredit = new ConsumerCredit();
                selectedConsumerCredit.setType("cash");
                selectedConsumerCredit.setSelected_amount(enteredCreditAmount);
                if (!isPossibleCreditByRepaymentAmount(selectedConsumerCredit, enteredRepaymentAmount, enteredDownPayment)) {
                    System.out.println("No offers for selected parameters. Please try again");
                    runService();
                } else if (consumerCreditList.size() > 1) {
                    finalChoice(consumerCreditList, scanner);
                } else {
                    setSelectedCreditParameters(scanner, selectedConsumerCredit, consumerCreditList.get(0));
                    System.out.println("You have selected next offer:");
                    showAllOffers();
                    printService.printSelectedLoan(selectedConsumerCredit);
                }
                break;
            case 2:
                selectedCollateralCredit = new CollateralCredit();
                selectedCollateralCredit.setType("carLoan");
                selectedCollateralCredit.setSelected_amount(enteredCreditAmount);
                if (!isPossibleCreditByRepaymentAmount(selectedCollateralCredit, enteredRepaymentAmount, enteredDownPayment)) {
                    System.out.println("No offers for selected parameters. Please try again");
                    runService();
                } else if (collateralCreditList.size() > 1) {
                    finalChoice(collateralCreditList, scanner);
                } else {
                    setSelectedCreditParameters(scanner, selectedCollateralCredit, collateralCreditList.get(0));
                    System.out.println("You have selected next offer:");
                    showAllOffers();
                    selectedCollateralCredit = enterDownPayment(scanner, selectedCollateralCredit);
                    printService.printSelectedLoan(selectedCollateralCredit);
                }
                break;
            case 3:
                selectedCollateralCredit = new CollateralCredit();
                selectedCollateralCredit.setType("mortgage");
                selectedCollateralCredit.setSelected_amount(enteredCreditAmount);
                if (!isPossibleCreditByRepaymentAmount(selectedCollateralCredit, enteredRepaymentAmount, enteredDownPayment)) {
                    System.out.println("No offers for selected parameters. Please try again");
                    runService();
                } else if (collateralCreditList.size() > 1) {
                    finalChoice(collateralCreditList, scanner);
                } else {
                    setSelectedCreditParameters(scanner, selectedCollateralCredit, collateralCreditList.get(0));
                    System.out.println("You have selected next offer:");
                    showAllOffers();
                    selectedCollateralCredit = enterDownPayment(scanner, selectedCollateralCredit);
                    printService.printSelectedLoan(selectedCollateralCredit);
                }
                break;
        }


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
                    selectedConsumerCredit = setSelectedCreditParameters(scanner, selectedConsumerCredit, consumerCreditList.get(0));
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
                    selectedCollateralCredit = setSelectedCreditParameters(scanner, selectedCollateralCredit, collateralCreditList.get(0));
                    System.out.println("You have selected next offer:");
                    showAllOffers();
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
                    selectedCollateralCredit = setSelectedCreditParameters(scanner, selectedCollateralCredit, collateralCreditList.get(0));
                    System.out.println("You have selected next offer:");
                    showAllOffers();
                    printService.printSelectedLoan(selectedCollateralCredit);
                }
                break;
        }
    }

    private CollateralCredit enterDownPayment(Scanner scanner, CollateralCredit selectedCollateralCredit) {
        System.out.println("Enter down payment in %. Minimum value is '" + selectedCollateralCredit.getDown_payment() + "%'");
        int downPayment = Integer.valueOf(scanner.nextLine());
        if (downPayment < selectedCollateralCredit.getDown_payment()) {
            System.out.println("You have entered value that less than '" + selectedCollateralCredit.getDown_payment() + "'. Please re-enter greater value.");
            enterDownPayment(scanner, selectedCollateralCredit);
        } else {
            selectedCollateralCredit.setSelectedDownPayment(selectedCollateralCredit.getSelected_amount() * downPayment / 100);
        }
        return selectedCollateralCredit;
    }

    private <T extends Loan> boolean isPossibleCreditByRepaymentAmount(T selectedLoan, int repaymentAmount, int enteredDownPayment) {
        if (selectedLoan.getClass().equals(ConsumerCredit.class)) {
            //remove other type of credit:
            collateralCreditList.clear();
//            consumerCreditList.stream().filter(consumerCredit -> consumerCredit.calculateFirstMonthlyRepayment(selectedLoan.getSelected_amount()) > repaymentAmount);
            for (int i = consumerCreditList.size() - 1; i >= 0; i--) {
                if (consumerCreditList.get(i).calculateFirstMonthlyRepayment(selectedLoan.getSelected_amount()) > repaymentAmount) {
                    consumerCreditList.remove(i);
                } else {
                    selectedLoan.setSelected_term(consumerCreditList.get(i).getMax_term());
                }
            }
        } else {
            //remove other type of credit:
            consumerCreditList.clear();
            if (!collateralCreditList.isEmpty()) {
                for (int i = collateralCreditList.size() - 1; i >= 0; i--) {
                    if (!collateralCreditList.get(i).getType().equals(selectedLoan.getType())) {
                        collateralCreditList.remove(i);
                    } else {
                        selectedLoan.setSelected_term(collateralCreditList.get(i).getMax_term());
                    }
                }
            }

            //remove offer if entered downPayment less than in offer
            if (!collateralCreditList.isEmpty()) {
                for (int i = collateralCreditList.size() - 1; i >= 0; i--) {
                    if (collateralCreditList.get(i).getDown_payment() * selectedLoan.getSelected_amount() / 100 > enteredDownPayment) {
                        collateralCreditList.remove(i);
                    } else {
                        collateralCreditList.get(i).setSelectedDownPayment(enteredDownPayment);
                    }
                }
            }
            if (!collateralCreditList.isEmpty()) {
                for (int i = collateralCreditList.size() - 1; i >= 0; i--) {
                    float offeredAmount = collateralCreditList.get(i).calculateFirstMonthlyRepayment(collateralCreditList.get(i).getSelected_amount(), enteredDownPayment);
                    if (offeredAmount > repaymentAmount) {
                        collateralCreditList.remove(i);
                    }
                }
            }

        }

        if (selectedLoan.getClass().equals(ConsumerCredit.class) && consumerCreditList.isEmpty()) {
            return false;
        } else if (selectedLoan.getClass().equals(CollateralCredit.class) && collateralCreditList.isEmpty()) {
            return false;
        } else {
            return true;
        }
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
                        selectedConsumerCredit = setSelectedCreditParameters(scanner, selectedConsumerCredit, ((ConsumerCredit) loans.get(i)));
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
                        selectedCollateralCredit = setSelectedCreditParameters(scanner, selectedCollateralCredit, ((CollateralCredit) loans.get(i)));
                    }
                }
                if (selectedCollateralCredit.getId() != id) {
                    System.out.println("You entered incorrect offer ID");
                    finalChoice(loans, scanner);
                }
                printService.printSelectedLoan(selectedCollateralCredit);
            }
        }
    }

    private <T extends Loan> T setSelectedCreditParameters(Scanner scanner, T selectedCredit, T availableCredit) {
        if (selectedCredit.getClass().equals(CollateralCredit.class)) {
            selectedCredit = (T)((CollateralCredit) selectedCredit).setSelectedDownPayment(enterDownPayment(scanner, (CollateralCredit) availableCredit).getSelectedDownPayment());
            selectedCredit = (T)((CollateralCredit) selectedCredit).setMandatory_insurance(((CollateralCredit) availableCredit).isMandatory_insurance());
        }
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
        selectedCredit.setRepaymentAmount((int) selectedCredit.calculateFirstMonthlyRepayment(selectedCredit.getSelected_amount()));
        selectedCredit.setOverpayment((int) selectedCredit.calculateCreditOverpayment());
        return selectedCredit;
    }
}
