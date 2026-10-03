import java.util.Scanner;
interface Playable{String play();String play(int fromSecond);String pause();}
abstract class MediaFile{
 private static int counter=1000; private final String fileId;
 public MediaFile(){fileId="MF-"+(++counter);}
 public abstract String getFormatInfo(); public String getFileId(){return fileId;}
}
class AudioFile extends MediaFile implements Playable{
 private final String title; public AudioFile(String t){title=t;}
 public String play(){return "Playing audio: "+title;}
 public String play(int s){return String.format("Playing audio: %s from %d:%02d",title,s/60,s%60);}
 public String pause(){return "Paused audio: "+title;}
 public String getFormatInfo(){return "Audio file, ID: "+getFileId();}
}
class Podcast implements Playable{
 private final String show; private final int episode;
 public Podcast(String s,int e){show=s;episode=e;}
 public String play(){return "Streaming episode "+episode+" of "+show;}
 public String play(int s){return play()+" from "+s+" seconds";}
 public String pause(){return "Paused podcast: "+show;}
}
public class Week_7_UniversalMediaLauncher{
 static void launchAll(Playable[] items){for(Playable x:items)System.out.println(x.play());}
 public static void main(String[] args){
  Scanner sc=new Scanner(System.in); System.out.println("Audio title:"); String t=sc.nextLine();
  System.out.println("Podcast name:"); String p=sc.nextLine(); System.out.println("Episode:"); int e=sc.nextInt();
  AudioFile a=new AudioFile(t); Podcast pod=new Podcast(p,e); System.out.println(a.play());
  System.out.println("Start second:"); System.out.println(a.play(sc.nextInt())); System.out.println(a.getFormatInfo()); System.out.println(pod.play());
  Playable ref=a; launchAll(new Playable[]{ref,pod}); sc.close();
 }
}
