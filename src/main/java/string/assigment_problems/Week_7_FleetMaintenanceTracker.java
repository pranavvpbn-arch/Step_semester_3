import java.util.Scanner;
abstract class ServiceableVehicle{
 private double mileage; public abstract String performMaintenance(); public double getMileage(){return mileage;}
 public void addMileage(double km){if(km<0)throw new IllegalArgumentException("Mileage cannot be negative");mileage+=km;}
}
interface Insurable{String getInsuranceInfo();}
class Forklift extends ServiceableVehicle implements Insurable{
 protected final String tag; public Forklift(String t){tag=t;} public String performMaintenance(){return "Forklift "+tag+": hydraulic and fork inspection complete";}
 public String getInsuranceInfo(){return "Insured under fleet policy - Asset "+tag;}
}
class HeavyDutyForklift extends Forklift{
 public HeavyDutyForklift(String t){super(t);} public String performMaintenance(){return super.performMaintenance()+" | high-pressure hydraulic check complete";}
}
public class Week_7_FleetMaintenanceTracker{
 static String getInsuranceIfApplicable(ServiceableVehicle v){if(v instanceof Insurable)return ((Insurable)v).getInsuranceInfo();return "No insurance record exists";}
 public static void main(String[] args){
  Scanner sc=new Scanner(System.in); System.out.println("Forklift tag:"); Forklift f=new Forklift(sc.nextLine()); System.out.println("Heavy-duty tag:"); HeavyDutyForklift h=new HeavyDutyForklift(sc.nextLine());
  System.out.println("Mileage to add:"); double km=sc.nextDouble(); try{f.addMileage(km);h.addMileage(km);}catch(Exception e){System.out.println(e.getMessage());}
  System.out.println(f.getMileage()); System.out.println(f.performMaintenance()); System.out.println(h.performMaintenance()); System.out.println(getInsuranceIfApplicable(f)); System.out.println(getInsuranceIfApplicable(h)); sc.close();
 }
}
