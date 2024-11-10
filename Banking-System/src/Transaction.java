import java.util.*;

public class Transaction {

    public static BankAccount bankAccount;
    private  static double total_loan_amount;

//    creating construstor  getting the bank account object from the existing user
    Transaction(BankAccount bankAccount){
        this.bankAccount=bankAccount;
    }

    Date dt = new Date();
    boolean t=true;
    Scanner sc=new Scanner(System.in);

    public double loancheck(String userId){
        double annual_I = bankAccount.userDetail.get(userId).getAnnual_income();
        LinkedList<Double> depts=bankAccount.userDetail.get(userId).getdeposite();
        double totalDepts = depts.stream().mapToDouble(Double::doubleValue).sum();
        double mi = annual_I/12;

        double check = (totalDepts/mi)*100;


        return check;

    }


    //getting the userId from the existinguser
    public void main(String userId){
        while (t){
            System.out.println("====================================================================================================================================");
            System.out.println("\t\t\t\t\t\t\t AccountNumber: " + bankAccount.userDetail.get(userId).getaccountNumber()+ "\t\t\t\t\t\t\t AccountType: " +bankAccount.userDetail.get(userId).getaccountType());
            System.out.println("\n\n\t\t\t\t\t\t\t Balance: " + bankAccount.userDetail.get(userId).getbalance() + "\t\t\t\t\t\t\t\t Borrowings: " + bankAccount.userDetail.get(userId).getLoanamount());
            System.out.println("====================================================================================================================================");
            System.out.println("\n1)Profile\n2)Deposite\n3)Withdraw\n4)Balance\n5)transaction\n6)Apply for loan\n7)Log out");

            int opt=sc.nextByte();
            switch (opt){
                case 1:
                    profile(userId);
                    break;
                case 2:
                    deposite(userId);
                    break;
                case 3:
                    withdraw(userId);
                    break;
                case 4:
                    balance(userId);
                    break;
                case 5:
                    transaction(userId);
                    break;
                case 6:
                    //passing the bank account object and user id to the loan class
                    double percentage = loancheck(userId);
//                    System.out.println(percentage); used  for debugging
                    if (percentage>35){
                        Loan l = new Loan(bankAccount,userId);
                        l.loan(bankAccount, userId);
                    }
                    else {
                        System.out.println("\t\t\t\t\t\t\t low CREDIT SCORE /  you already avail the LOAN SERVICE");
                    }

                    break;
                case 7:
                   t=false;
                   Main main;


                    break;
                default:
                    main(userId);
            }
        }
    }



    public void deposite(String userId){
        double amt=0;
        System.out.println("Enter amount to deposite");
        while(true){
            try{

                //getting the related userid values from hashmap and store it in the userDetail .
                User userDetail=bankAccount.userDetail.get(userId);
                amt=sc.nextDouble();
                if(amt>0){
                    userDetail.setbalance(userDetail.getbalance()+amt);
                    userDetail.setTransaction(String.valueOf(dt+" "+String.valueOf(amt)+" "+"DR"));
                    userDetail.setdeposite(amt);
                    System.out.println("Balanece : "+userDetail.getbalance());
                    break;
                }else{
                    amt=0;
                    System.out.println("*Enter a valid amount to deposite*");
                }

            }catch (InputMismatchException e){
                System.out.println("Enter a valid Amount");
                sc.next();
            }
        }
    }
    public void withdraw(String userId){


        //getting the related userid values from hashma;p and store it in the userDetail .
        User userDetail=bankAccount.userDetail.get(userId);
        double amt;
        while (true){
            System.out.println("enter amount to withdraw :");
            amt= sc.nextDouble();
                if(amt>0){
                    if (userDetail.getbalance()>amt){
                        userDetail.setbalance(userDetail.getbalance()-amt);
                        userDetail.setTransaction(String.valueOf(dt+" " +String.valueOf(amt)+" "+"CR"));
                        userDetail.setWithdraw(amt);

                    }else{
                        System.out.println("less balance");
                        amt=0;
                    }
                    break;
                }else {
                    System.out.println("Enter a valid amount to withdraw");
                }
        }

        System.out.println("Withdraw anmount"+amt);
        System.out.println("balance : "+userDetail.getbalance());
    }
    public void balance(String userId){
        System.out.println(bankAccount.userDetail.get(userId).getbalance());
    }
    public void transaction(String userId){
        Iterator<String> it= (Iterator<String>) bankAccount.userDetail.get(userId).gettransaction().iterator();
        while (it.hasNext()){
            System.out.println(it.next());
        }
    }

    public void profile(String userId){

        System.out.println("====================================================================================================================================");
        System.out.println("************************************************************* PROFILE **************************************************************");
        System.out.println("\t\t\t\t\t\t\t AccountNumber: " + bankAccount.userDetail.get(userId).getaccountNumber()+ "\t\t\t\t\t\t\t AccountType: " +bankAccount.userDetail.get(userId).getaccountType()+"\n");
        System.out.println("\t\t\t\t\t\t\t User Name:     " + bankAccount.userDetail.get(userId).getuserName()+ "\t\t\t\t\t\t\t  DOB :" + bankAccount.userDetail.get(userId).getdob()+"\n");
        System.out.println("\t\t\t\t\t\t\t User ID:     " + bankAccount.userDetail.get(userId).getuserId()+ "\t\t\t\t\t\t\t  Phone No :" + bankAccount.userDetail.get(userId).getPhno()+"\n");
        System.out.println("\n\n\t\t\t\t\t\t\t Balance: " + bankAccount.userDetail.get(userId).getbalance() + "\t\t\t\t\t\t\t\t Borrowings: " + bankAccount.userDetail.get(userId).getLoanamount() +"\n");
        System.out.println("====================================================================================================================================");
        System.out.println("\n1)Loan EMI\t 2)dashboard");
        int check = sc.nextInt();
        switch (check){
            case 1:
                double borrow =bankAccount.userDetail.get(userId).getLoanamount();
                double monthlyemi = monthlyEMI(userId);
                if(borrow>0){
                    System.out.println("============================your monthly EMI for 12 month============================");
                    System.out.println(monthlyemi);
                    System.out.println("pay your EMI");
                    double amt = sc.nextDouble();
                    if(amt>=monthlyemi){

                        double extraamount = amt - monthlyemi;// if customer has extra amount
                        amt=amt-extraamount;
                        bankAccount.userDetail.get(userId).setLoanamount(bankAccount.userDetail.get(userId).getLoanamount()-amt);// reduce the loan amount
                        System.out.println("your change  :" + extraamount);
                        System.out.println("============================you successfully Paid your EMI ============================\n");
                        System.out.println("============================your loan amount============================\n");
                        System.out.println(bankAccount.userDetail.get(userId).getLoanamount());



                    }

                    else {
                        System.out.println("Insufficient amount");
                        profile(userId);
                    }
                }
                else {
                    System.out.println("NO loan on your account\n\n");
                }
            case 2:
                main(userId);

        }



    }
    static double monthlyEMI(String userId){
        double totalemi = bankAccount.userDetail.get(userId).getLoanamount();
        double monthlyemi = totalemi/12;

        return monthlyemi;
    }


}
