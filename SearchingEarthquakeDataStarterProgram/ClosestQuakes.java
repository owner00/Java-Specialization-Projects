
/**
 * Find N-closest quakes
 * 
 * @author Duke Software/Learn to Program
 * @version 1.0, November 2015
 */

import java.util.*;

public class ClosestQuakes {
    public ArrayList<QuakeEntry> getClosest(ArrayList<QuakeEntry> quakeData, Location current, int howMany) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();;
        ArrayList<QuakeEntry> quakeDataCopy = quakeData;
        double arraySize = howMany;
        
        if(quakeData.size()<howMany){
            arraySize = quakeData.size();
        }
        
        for(int i=0;i<arraySize;i++){
            double smallest = 10000000000.0;
            QuakeEntry closestQuake = null;
            for(QuakeEntry qe : quakeDataCopy){
                double dist = current.distanceTo(qe.getLocation());
                if(dist<smallest){
                    closestQuake = qe;
                    smallest = dist;
                }
            }
            answer.add(closestQuake);
            quakeDataCopy.remove(closestQuake);
        }
        return answer;
    }

    public void findClosestQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size());

        Location jakarta  = new Location(-6.211,106.845);
        int howMany = 3;
        
        ArrayList<QuakeEntry> close = getClosest(list,jakarta,howMany);
        for(int k=0; k < close.size(); k++){
            QuakeEntry entry = close.get(k);
            double distanceInMeters = jakarta.distanceTo(entry.getLocation());
            System.out.printf("%4.2f\t %s\n", distanceInMeters/1000,entry);
        }
        System.out.println("number found: "+close.size());
    }
    
}
