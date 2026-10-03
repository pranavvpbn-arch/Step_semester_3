import java.util.Scanner;
abstract class LibraryItem{
 private static int counter=1000; private final String itemId;
 public LibraryItem(){itemId="LIB-"+(++counter);} public abstract int getLoanPeriodDays(); public String getItemId(){return itemId;}
}
interface Renewable{String renew();} interface Reservable{String reserve();}
class Textbook extends LibraryItem implements Renewable,Reservable{
 private final String title; public Textbook(String t){title=t;} public int getLoanPeriodDays(){return 14;}
 public String renew(){return title+" renewed";} public String reserve(){return title+" reserved";}
}
class Magazine extends LibraryItem implements Renewable{
 private final String title; public Magazine(String t){title=t;} public int getLoanPeriodDays(){return 7;} public String renew(){return title+" renewed";}
}
class DigitalPass implements Renewable{
 private final String name; public DigitalPass(String n){name=n;} public String renew(){return name+" renewed";}
}
public class Week_7_CommunityLibraryCheckoutSystem{
 static void processCheckouts(LibraryItem[] items){for(LibraryItem x:items)System.out.println(x.getItemId()+" | Loan period: "+x.getLoanPeriodDays()+" days");}
 static String reserveIfSupported(Object o){if(o instanceof Reservable)return ((Reservable)o).reserve();return "Reservation not supported";}
 public static void main(String[] args){
  Scanner sc=new Scanner(System.in); System.out.println("Textbook title:"); Textbook t=new Textbook(sc.nextLine());
  System.out.println("Magazine title:"); Magazine m=new Magazine(sc.nextLine()); System.out.println("Digital resource:"); DigitalPass d=new DigitalPass(sc.nextLine());
  processCheckouts(new LibraryItem[]{t,m}); System.out.println(t.renew()); System.out.println(t.reserve()); System.out.println(m.renew()); System.out.println(d.renew());
  System.out.println(reserveIfSupported(m)); System.out.println(reserveIfSupported(d)); LibraryItem ref=t; System.out.println(reserveIfSupported(ref)); sc.close();
 }
}
