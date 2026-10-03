import java.util.Scanner;
interface Exportable{String exportData();}
class ExportCounter{private static int total;static void inc(){total++;}static int get(){return total;}}
class ReportGenerator implements Exportable{
 private final String name; public ReportGenerator(String n){name=n;} public String exportData(){ExportCounter.inc();return "Exported report: "+name;}
}
class UserProfile implements Exportable{
 private final String username; public UserProfile(String u){username=u;} public String exportData(){ExportCounter.inc();return "Exported profile: "+username;}
}
public class Week_7_OneClickDataExport{
 static void exportAll(Exportable[] items){for(Exportable x:items)System.out.println(x.exportData());}
 static int getTotalExports(){return ExportCounter.get();}
 public static void main(String[] args){
  Scanner sc=new Scanner(System.in); System.out.println("Report name:"); ReportGenerator r=new ReportGenerator(sc.nextLine());
  System.out.println("Username:"); UserProfile u=new UserProfile(sc.nextLine()); Exportable ref=r; exportAll(new Exportable[]{ref,u}); System.out.println("Total exports: "+getTotalExports()); sc.close();
 }
}
