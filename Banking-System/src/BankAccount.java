import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;


public class BankAccount   {
//creating hashmap for storing the user information
    HashMap<String,User> userDetail=new HashMap<>();


    //creating method for storing the detail of the user .
    //thest detail fre from the new user class
    public void userInitialDetail(long accountNumber, String phno, String userName, String userId,String pass,String dob,String accountType,double initialAmount,double annual_income){

        //creating constructoe for the User class to set the initial details and pass the values.
        User use=new User(accountNumber,phno, userName, userId,pass,dob,accountType,initialAmount,annual_income);

        //put the detail into hashmap
        //use is the object of the User class that store the user information.
        userDetail.put(userId,use);
    }



 public void file(ObjectOutputStream obs,BankAccount bankAccount){
     try {
         obs.writeObject(bankAccount.userDetail);
     } catch (IOException e) {
         throw new RuntimeException(e);
     }
 }
}