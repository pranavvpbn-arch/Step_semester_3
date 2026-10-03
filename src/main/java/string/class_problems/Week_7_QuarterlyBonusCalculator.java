import java.util.Scanner;
abstract class StaffMember{
 private double baseSalary; protected double bonusRate;
 public StaffMember(double salary){this(salary,.10);}
 public StaffMember(double salary,double rate){if(salary<0||rate<0)throw new IllegalArgumentException("Invalid values");baseSalary=salary;bonusRate=rate;}
 public abstract double calculateBonus();
 public double getSalary(){return baseSalary;}
 public void setSalary(double salary){if(salary<0){System.out.println("Rejected: salary unchanged");return;}baseSalary=salary;}
}
interface Auditable{String auditRecord();}
class TeamLead extends StaffMember implements Auditable{
 private final int teamSize;
 public TeamLead(double salary,int size){super(salary);teamSize=size;}
 public TeamLead(double salary,double rate,int size){super(salary,rate);teamSize=size;}
 public double calculateBonus(){return getSalary()*bonusRate;}
 public String auditRecord(){return "TeamLead audit: "+teamSize+" team members, salary $"+getSalary();}
}
public class Week_7_QuarterlyBonusCalculator{
 static String getAuditIfApplicable(StaffMember s){if(s instanceof Auditable)return ((Auditable)s).auditRecord();return "No audit required";}
 public static void main(String[] args){
  Scanner sc=new Scanner(System.in); System.out.println("Base salary:"); double sal=sc.nextDouble();
  System.out.println("Custom rate? true/false:"); boolean custom=sc.nextBoolean(); System.out.println("Team size:"); int n=sc.nextInt();
  TeamLead t=custom?new TeamLead(sal,sc.nextDouble(),n):new TeamLead(sal,n);
  System.out.println("Bonus: "+t.calculateBonus()); StaffMember ref=t; System.out.println(getAuditIfApplicable(ref)); sc.close();
 }
}
