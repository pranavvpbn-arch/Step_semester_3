import java.util.Scanner;
abstract class HomeDevice{
 private static int counter=1000; private final String serialNumber; protected HomeDevice(){serialNumber="HD-"+(++counter);} public abstract String activate(); public String getSerialNumber(){return serialNumber;}
}
interface RemoteControllable{String connect(String appId);} interface EnergyTrackable{double getConsumptionWatts();}
class WashingMachine extends HomeDevice implements RemoteControllable,EnergyTrackable{
 private final double watts; public WashingMachine(double w){if(w<=0)throw new IllegalArgumentException();watts=w;}
 public String activate(){return "Washing machine "+getSerialNumber()+" started a cycle";} public String connect(String id){return getSerialNumber()+" connected to "+id;} public double getConsumptionWatts(){return watts;}
}
class Refrigerator extends HomeDevice implements EnergyTrackable{
 private final double watts; public Refrigerator(double w){if(w<=0)throw new IllegalArgumentException();watts=w;}
 public String activate(){return "Refrigerator "+getSerialNumber()+" activated";} public double getConsumptionWatts(){return watts;}
}
class MobileApp implements RemoteControllable{
 private final String name; public MobileApp(String n){name=n;} public String connect(String id){return name+" connected to "+id;}
}
public class Week_7_ConnectedHomeControlPanel{
 static void connectAll(RemoteControllable[] items,String id){for(RemoteControllable x:items)System.out.println(x.connect(id));}
 static double getConsumptionIfTrackable(HomeDevice d){if(d instanceof EnergyTrackable)return ((EnergyTrackable)d).getConsumptionWatts();return -1;}
 public static void main(String[] args){
  Scanner sc=new Scanner(System.in);System.out.println("Washing machine watts:");WashingMachine w=new WashingMachine(sc.nextDouble());System.out.println("Refrigerator watts:");Refrigerator r=new Refrigerator(sc.nextDouble());sc.nextLine();
  System.out.println("App name:");MobileApp app=new MobileApp(sc.nextLine());System.out.println("App ID:");String id=sc.nextLine();
  System.out.println(w.activate());System.out.println(r.activate());connectAll(new RemoteControllable[]{w,app},id);System.out.println(getConsumptionIfTrackable(r));HomeDevice ref=w;System.out.println(getConsumptionIfTrackable(ref));sc.close();
 }
}
