import java.util.Scanner;
abstract class PaymentMethod {
 private static int counter=1000; private final String transactionId;
 public PaymentMethod(){transactionId="TXN-"+(++counter);}
 public abstract String processPayment(double amount);
 public String processPayment(double amount,String note){return processPayment(amount)+" ("+note+")";}
 public String getTransactionId(){return transactionId;}
}
class CreditCardPayment extends PaymentMethod {
 private final String lastFour;
 public CreditCardPayment(String lastFour){this.lastFour=lastFour;}
 public String processPayment(double amount){return "Charged $"+amount+" to card ending "+lastFour+" - Txn "+getTransactionId();}
}
class CashPayment extends PaymentMethod {
 public String processPayment(double amount){return "Received $"+amount+" in cash - Txn "+getTransactionId();}
}
public class Week_7_CheckoutPaymentHandler {
 static void printConfirmation(PaymentMethod p,double amount){System.out.println(p.processPayment(amount));}
 public static void main(String[] args){
  Scanner sc=new Scanner(System.in);
  System.out.println("Payment type (1-Credit Card, 2-Cash):"); int type=sc.nextInt(); sc.nextLine();
  System.out.println("Amount:"); double amount=sc.nextDouble(); sc.nextLine();
  PaymentMethod payment;
  if(type==1){System.out.println("Last four digits:"); payment=new CreditCardPayment(sc.nextLine());}
  else payment=new CashPayment();
  System.out.println("Add note? (yes/no):"); String choice=sc.nextLine();
  if(choice.equalsIgnoreCase("yes")){System.out.println("Note:"); System.out.println(payment.processPayment(amount,sc.nextLine()));}
  else printConfirmation(payment,amount);
  sc.close();
 }
}
