import java.util.LinkedList;
import java.util.Scanner;

public class Loan {
    private static BankAccount bankAccount;
    private static String userId;
    private static double mi;
    private static double annual_I;
    private static double check;
    private static double checkper;
    private static double totalDepts;
    private static LinkedList<Double> depts;
    private static double lamount;


    public Loan(BankAccount bankAccount, String userId) {
        this.bankAccount = bankAccount;
        this.userId = userId;
    }

    public static double loanamount() {
        annual_I = bankAccount.userDetail.get(userId).getAnnual_income();
        depts = bankAccount.userDetail.get(userId).getdeposite();
        totalDepts = depts.stream().mapToDouble(Double::doubleValue).sum();
        mi = annual_I / 12;
        check = (totalDepts / mi) * 100;
        checkper = check / 100;
        lamount = Math.round(annual_I*0.5+(annual_I * checkper));
//        System.out.println(annual_I); debugging
//        System.out.println(depts);
//        System.out.println(totalDepts);
//        System.out.println(mi);
//        System.out.println(check);
//        System.out.println(lamount);
        return lamount;

    }


    static Scanner sc = new Scanner(System.in);

    public static void loan(BankAccount bankAccount, String userId) {
        System.out.println("====================================================================================================================================");
        System.out.println("\t\t\t\t\t\t\t AccountNumber: " + Loan.bankAccount.userDetail.get(Loan.userId).getaccountNumber() + "\t\t\t\t\t\t\t AccountType: " + Loan.bankAccount.userDetail.get(Loan.userId).getaccountType());
        System.out.println("====================================================================================================================================");
        double lamount = loanamount();

        System.out.println("your loan eligible  amount    " + lamount);
        System.out.println("your INTEREST 8%\n\n");
        System.out.println("would   you like apply  for loan?\n");
        System.out.println("1) apply loan \t2) dashboard");

        int check  = sc.nextInt();

        while (true){
            switch (check){
                case 1:
                    passcheck();
                    break;
                case 2:
                    Transaction  trans =  new Transaction(bankAccount);
                    trans.main(userId);
                    break;
                default:
                    loan(bankAccount,userId);

            }
        }

//        String accType=bankAccount.userDetail.get(userId).getaccountType();
//        double balance=bankAccount.userDetail.get(userId).getbalance();
//        LinkedList<Double> dep=bankAccount.userDetail.get(userId).getdeposite();
//        LinkedList<Double> wit=bankAccount.userDetail.get(userId).getWithdraw();
//        System.out.println(dep.stream().mapToDouble(Double::doubleValue).sum());
}


//password checking method for applying loan
    private static void passcheck() {
        sc.nextLine();
        System.out.println("Enter your password:");
        String pass = sc.nextLine().trim();
        System.out.println("Input Password: " + pass); // Debugging

        String storedPass = bankAccount.userDetail.get(userId).getPass();
       // System.out.println("Stored Password: " + storedPass);  //Debugging

        if (storedPass.equals(pass)) {
            System.out.println("Password match, proceeding to loan");
            loan_amount_add();
        } else {
            System.out.println("Invalid password, please try again.");
            loan(bankAccount, userId);
        }

    }
// set loan amount by getting amount from user method
    private static void loan_amount_add() {
        System.out.println("enter an  amount");

        double l_amount_add = sc.nextDouble();
        double temp_a= loanamount();
        if (l_amount_add>=0 && l_amount_add<=temp_a ){
            double interest=l_amount_add*0.08;


            System.out.println("your interest amount :" + interest );
            bankAccount.userDetail.get(userId).setInterestAmount(interest);

            bankAccount.userDetail.get(userId).setLoanamount(l_amount_add+interest);
            System.out.println("Loan credited successfully");
            Transaction  trans =  new Transaction(bankAccount);
            trans.main(userId);


        }
        else {
            System.out.println("Invalid  amount");
            loan(bankAccount,userId);
        }


    }
    }

