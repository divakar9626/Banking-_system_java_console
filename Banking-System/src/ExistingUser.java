import java.util.Scanner;

public class ExistingUser {
    Scanner sc=new Scanner(System.in);
    //getting the bankAccount object from main
    public void existinguser(BankAccount bankAccount){
        String userid;
        while(true){
            System.out.println("Enter your user id");
            userid = sc.nextLine();
            if (bankAccount.userDetail.containsKey(userid)){
                break;
            }else {
                System.out.println("invalid UserId ReEnter your userid");
                userid="";
            }
        }
        while(true){
            System.out.println("Enter your password");
            String pass2 = sc.nextLine();
            if (bankAccount.userDetail.get(userid).getPass() .equals( pass2)){
                //creating object for Transaction
                //pass the bankAccount object through the construntor
                Transaction trans = new Transaction(bankAccount);
                trans.main(userid);



                break;
            }else{
                System.out.println("Invalid password");
                pass2="";
            }
        }
    }
}
