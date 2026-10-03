import java.util.Scanner;
interface Alertable{String sendAlert(String message);}
class SecuritySensor{protected String zoneName; public SecuritySensor(String z){zoneName=z;} public String getZoneName(){return zoneName;}}
class MotionSensor extends SecuritySensor implements Alertable{
 public MotionSensor(String z){super(z);}
 public String sendAlert(String m){return "["+zoneName+"] "+m;}
}
class DualZoneMotionSensor extends MotionSensor{
 private final String secondZoneName;
 public DualZoneMotionSensor(String z,String s){super(z);secondZoneName=s;}
 public String sendAlert(String m){return super.sendAlert(m)+" [also covering "+secondZoneName+"]";}
}
class SmokeDetector implements Alertable{
 private final String deviceId; public SmokeDetector(String id){deviceId=id;}
 public String sendAlert(String m){return "["+deviceId+"] "+m;}
}
public class Week_7_HomeSafetyAlertNetwork{
 static void broadcastAll(Alertable[] d,String m){for(Alertable x:d)System.out.println(x.sendAlert(m));}
 static String getZoneIfMotionSensor(Alertable a){
  if(a instanceof MotionSensor)return ((MotionSensor)a).getZoneName();
  return "Not a motion sensor";
 }
 public static void main(String[] args){
  Scanner sc=new Scanner(System.in);
  System.out.println("Motion zone:"); String z=sc.nextLine();
  System.out.println("Second zone:"); String s=sc.nextLine();
  System.out.println("Smoke detector ID:"); String id=sc.nextLine();
  System.out.println("Alert message:"); String m=sc.nextLine();
  MotionSensor ms=new MotionSensor(z); DualZoneMotionSensor ds=new DualZoneMotionSensor(z,s); SmokeDetector sd=new SmokeDetector(id);
  broadcastAll(new Alertable[]{ms,ds,sd},m);
  System.out.println(getZoneIfMotionSensor(ms)); System.out.println(getZoneIfMotionSensor(sd));
  sc.close();
 }
}
