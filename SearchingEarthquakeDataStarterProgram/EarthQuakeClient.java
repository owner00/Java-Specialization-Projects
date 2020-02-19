import java.util.*;
import edu.duke.*;

public class EarthQuakeClient {
  public EarthQuakeClient() {
      // TODO Auto-generated constructor stub
  }
  
  public void quakesByPhrase(){
      EarthQuakeParser parser = new EarthQuakeParser();
      //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
      String source = "data/nov20quakedata.atom";
      ArrayList<QuakeEntry> quakeList = parser.read(source);
      
      String where = "any";
      String phrase = "Can";
      
      System.out.println("Find Quakes whose title contains "+phrase
            +" at "+where);
      ArrayList<QuakeEntry> listQuakesByPhrase 
        = filterByPhrase(quakeList,where,phrase);
      printQuakes(quakeList,listQuakesByPhrase);
    }
    
    public ArrayList<QuakeEntry> filterByPhrase
        (ArrayList<QuakeEntry> quakeData,String where
         ,String phrase){
         ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
         for(QuakeEntry qe : quakeData){
            if(where.equals("start")&&qe.getInfo().startsWith(phrase)){
                answer.add(qe);
            }
            else if(where.equals("end")&&qe.getInfo().endsWith(phrase)){
                answer.add(qe);
            }
            else if(where.equals("any")&&(qe.getInfo().indexOf(phrase)!= -1)){
                answer.add(qe);
            }
         }
         return answer;   
    }
    
    public void quakesOfDepth(){
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> quakeList = parser.read(source);
        
        double minDepth = -4000.0;
        double maxDepth = -2000.0;
        
        System.out.println("Find Quakes btw "+minDepth
            +" & "+maxDepth);
        ArrayList<QuakeEntry> listQuakesBtw =
            filterByDepth(quakeList,minDepth,maxDepth);
        printQuakes(quakeList,listQuakesBtw);
    }
    
    public ArrayList<QuakeEntry> filterByDepth
        (ArrayList<QuakeEntry> quakeData,double minDepth
        ,double maxDepth){
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData){
            if(qe.getDepth() > minDepth 
                && qe.getDepth() < maxDepth){
                answer.add(qe);
            }
        }
        return answer;  
    }
    
    public void bigQuakes(){
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> quakeList = parser.read(source);
        double magMin = 5.0;
        
        ArrayList<QuakeEntry> listByMagnitude = 
            filterByMagnitude(quakeList, magMin);
        System.out.println("Quakes with Magnitude > "
            + magMin);
        printQuakes(quakeList, listByMagnitude);
    }
    
    public ArrayList<QuakeEntry> filterByMagnitude(ArrayList<QuakeEntry> quakeData,
    double magMin) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData){
            if(qe.getMagnitude() > magMin){
                answer.add(qe);
            }
        }
        return answer;
    }

    public void closeToMe(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");

        // This location is Durham, NC
        //Location city = new Location(35.988, -78.907);

        // This location is Bridgeport, CA
        Location city =  new Location(38.17, -118.82);

        double distMax = 1000000;
        ArrayList<QuakeEntry> listClose = 
            filterByDistanceFrom(list,distMax,city);
        printQuakes(list,listClose);
    }
    
    public ArrayList<QuakeEntry> filterByDistanceFrom
        (ArrayList<QuakeEntry> quakeData,double distMax,Location from) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData){
            if(from.distanceTo(qe.getLocation())<distMax){
                answer.add(qe);
            }
        }
        return answer;
    }

    public void dumpCSV(ArrayList<QuakeEntry> list){
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }

    }

    public void createCSV(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        //dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
        for (QuakeEntry qe : list) {
            System.out.println(qe);
        }
    }
    
    public void printQuakes(ArrayList<QuakeEntry> quakeData
        ,ArrayList<QuakeEntry> quakeAnswer){
        System.out.println("# quakes read: " 
            + quakeData.size());
        /*for (QuakeEntry qe : quakeAnswer) {
            System.out.println(qe);
        }*/
        System.out.println("Found "+quakeAnswer.size()
            +" quakes that match that criteria");
    }
}
