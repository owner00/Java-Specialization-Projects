
/**
 * Write a description of class QuakeSortInPlace here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class QuakeSortInPlace {
    public QuakeSortInPlace() {
        // TODO Auto-generated constructor stub
    }

    public void sortByMagnitudeWithCheck(ArrayList<QuakeEntry> in) {
        int passes = 0;
        for (int i=0; i< in.size(); i++) {
            if(checkInSortedOrder(in)){
                break;
            }
            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx,qi);
            passes += 1;
        }
        System.out.println("Passes used is "+passes);
    }
    
    public void sortByMagnitudeWithBubbleSortWithCheck(ArrayList<QuakeEntry> in){
        System.out.println("Quakes before Bubble Pass ");
        for (QuakeEntry qe: in) { 
            System.out.println(qe.getMagnitude());
        } 
        int passes = 0;
        for(int i=0;i<in.size()-1;i++){
            if(checkInSortedOrder(in)){
                break;
            }
            onePassBubbleSort(in,i);
            passes += 1;
            // System.out.println("Quakes after Bubble Pass "+i);
            // for (QuakeEntry qe: in) { 
                // System.out.println(qe.getMagnitude());
            // } 
        }
        System.out.println("Passes used is "+passes);
    }
    
    public boolean checkInSortedOrder(ArrayList<QuakeEntry> quakes){
        for(int i=0;i<quakes.size()-1;i++){
            if(quakes.get(i+1).getMagnitude()<quakes.get(i).getMagnitude()){
                return false;
            }
        }
        return true;
    }
    
    public void sortByMagnitudeWithBubbleSort(ArrayList<QuakeEntry> in){
        System.out.println("Quakes before Bubble Pass ");
        for (QuakeEntry qe: in) { 
            System.out.println(qe.getMagnitude());
        } 
        for(int i=0;i<in.size()-1;i++){
            onePassBubbleSort(in,i);
            System.out.println("Quakes after Bubble Pass "+i);
            for (QuakeEntry qe: in) { 
                System.out.println(qe.getMagnitude());
            } 
        }
    }

    public void onePassBubbleSort(ArrayList<QuakeEntry> quakeData, int numSorted){
        for(int i=0;i<quakeData.size()-(numSorted+1);i++){
            if(quakeData.get(i+1).getMagnitude()<quakeData.get(i).getMagnitude()){
                QuakeEntry qMagGreater = quakeData.get(i);
                QuakeEntry qMagLesser = quakeData.get(i+1);
                quakeData.set(i,qMagLesser);
                quakeData.set(i+1,qMagGreater);
            }
        }
    }

    public void sortByLargestDepth(ArrayList<QuakeEntry> in) {
        int passes = 50;
        for (int i=0; i< in.size(); i++) {
            if(i==passes){
                break;
            }
            int maxIdx = getLargestDepth(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmax = in.get(maxIdx);
            in.set(i,qmax);
            in.set(maxIdx,qi);
        }

    }

    public int getLargestDepth(ArrayList<QuakeEntry> quakeData, int from){
        int maxIdx = from;
        for (int i=from+1; i< quakeData.size(); i++) {
            if (quakeData.get(i).getDepth() > quakeData.get(maxIdx).getDepth()) {
                maxIdx = i;
            }
        }
        return maxIdx;
    }

    public int getSmallestMagnitude(ArrayList<QuakeEntry> quakes, int from) {
        int minIdx = from;
        for (int i=from+1; i< quakes.size(); i++) {
            if (quakes.get(i).getMagnitude() < quakes.get(minIdx).getMagnitude()) {
                minIdx = i;
            }
        }
        return minIdx;
    }

    public void sortByMagnitude(ArrayList<QuakeEntry> in) {

        for (int i=0; i< in.size(); i++) {
            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx,qi);
        }

    }

    public void testSort() {
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/earthQuakeDataWeekDec6sample1.atom";
        //String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);  

        System.out.println("read data for "+list.size()+" quakes");    
        //sortByMagnitude(list);
        //sortByLargestDepth(list);
        sortByMagnitudeWithBubbleSortWithCheck(list);
        //sortByMagnitudeWithCheck(list);
        // for (QuakeEntry qe: list) { 
            // System.out.println(qe);
        // } 
    }

    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
    }

    public void dumpCSV(ArrayList<QuakeEntry> list) {
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }

    }
}
