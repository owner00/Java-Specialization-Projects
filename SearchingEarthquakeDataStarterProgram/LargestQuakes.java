import java.util.*;

public class LargestQuakes {
    public ArrayList<QuakeEntry> getLargest(ArrayList<QuakeEntry> quakeData
    ,int howMany){
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        ArrayList<QuakeEntry> quakeDataCopy = quakeData;
        double arraySize = howMany;

        if(quakeData.size()<howMany){
            arraySize = quakeData.size();
        }

        for(int i=0;i<arraySize;i++){
            int tempIndex = indexOfLargest(quakeDataCopy);
            QuakeEntry qe = quakeDataCopy.get(tempIndex);
            int trueIndex = quakeData.indexOf(qe);
            answer.add(qe);
            quakeDataCopy.remove(qe);
        }
        return answer;
    }

    public int indexOfLargest(ArrayList<QuakeEntry> data){
        int index = 0;
        double largestMag = 0.0;
        QuakeEntry largestQuakeMag = null;

        for(QuakeEntry qe : data){
            double mag = qe.getMagnitude();
            if(mag>largestMag){
                largestQuakeMag = qe;
                largestMag = mag;
            }
        }
        index = data.indexOf(largestQuakeMag);
        return index;
    }

    public void findLargestQuakes(){
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> quakeList = parser.read(source);
        /* for(QuakeEntry qe : quakeList){
        System.out.println(qe);
        }*/
        System.out.println("# quakes read = "+quakeList.size());
        int howMany = 50;
        ArrayList<QuakeEntry> lrg = getLargest(quakeList,howMany);
        /*System.out.println("Index of quake with largest mag is "+index
            +" & magnitude is "+quakeList.get(index).getMagnitude());*/
        for(QuakeEntry qe : lrg){
            System.out.println(qe);
        }
    }
}
