import java.util.*;

public  class Main{
    public static void main(String args[]){
        Scanner sc=new Scanner(System.in);
        //creating object for BankAccount
        BankAccount bankAccount=new BankAccount();
        boolean t = true;
        while (t) {
            System.out.println("============================================");
            System.out.println("====== Console Based - Banking System ======");
            System.out.println("============================================");
            System.out.println("\nWelcome to Banking");
            System.out.println("1. New User Registration");
            System.out.println("2. Login with User ID");
            System.out.println("3. Exit");
            System.out.println("Enter your option: ");
            int opt=sc.nextInt();
            switch (opt) {
                case 1:
                    //cretung object for New user and pass the bankAccount object to the generate new user method
                    NewUser newuser=new NewUser();
                    newuser.generateNewUser(bankAccount);
                    break;
                case 2:
                    //cretung object for Existinguser and pass the bankAccount object to the existinguser method
                    ExistingUser existing=new ExistingUser();
                    existing.existinguser(bankAccount);
                    break;
                case 3:
                    System.out.println("Thank you for accessing our BANK service");
                    t= false;
            }
        }
    }
}