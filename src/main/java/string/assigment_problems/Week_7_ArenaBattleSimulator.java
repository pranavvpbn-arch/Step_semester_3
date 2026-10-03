import java.util.Scanner;
interface Attackable{String attack();String attack(String weaponName);} interface Defendable{String defend();}
abstract class GameCharacter{
 private static int counter; private final String characterId; protected GameCharacter(){characterId="GC-"+(++counter);} public abstract String getSpecialMove(); public String getCharacterId(){return characterId;}
}
class Warrior extends GameCharacter implements Attackable,Defendable{
 private final String name; public Warrior(String n){name=n;} public String attack(){return name+" strikes with a blade";}
 public String attack(String w){return name+" strikes with an "+w;} public String defend(){return name+" raises a shield";} public String getSpecialMove(){return name+" unleashes Whirlwind Slash";}
}
class Trap implements Defendable{
 private final String type; public Trap(String t){type=t;} public String defend(){return type+" triggers automatically";}
}
public class Week_7_ArenaBattleSimulator{
 static void resolveDefense(Defendable[] c){for(Defendable x:c)System.out.println(x.defend());}
 public static void main(String[] args){
  Scanner sc=new Scanner(System.in); System.out.println("Warrior name:"); Warrior w=new Warrior(sc.nextLine()); System.out.println("Weapon:"); String weapon=sc.nextLine(); System.out.println("Trap type:"); Trap t=new Trap(sc.nextLine());
  System.out.println(w.attack());System.out.println(w.attack(weapon));System.out.println(w.defend());System.out.println(w.getSpecialMove());System.out.println(w.getCharacterId());resolveDefense(new Defendable[]{w,t});sc.close();
 }
}
