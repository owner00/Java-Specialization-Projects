import java.util.*;
import edu.duke.*;

public class EarthQuakeClient2 {
    public EarthQuakeClient2() {
        // TODO Auto-generated constructor stub
    }

    public void testMatchAllFilter2(){
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");
        //dumpCSV(list);
        
        double minMag = 0.0;
        double maxMag = 5.0;
        double dist = 3000000;
        Location loc = new Location(55.7308,9.1153);
        String where = "any";
        String phrase = "e";
        Filter fMag = new MagnitudeFilter(minMag,maxMag);
        Filter fDist = new DistanceFilter(loc,dist);
        Filter fPhra = new PhraseFilter(where,phrase);
        MatchAllFilter maf = new MatchAllFilter();
        maf.addFilter(fMag);
        maf.addFilter(fDist);
        maf.addFilter(fPhra);
        //MatchAllFilter can be used as a Filter object
        //since it implements Filter
        ArrayList<QuakeEntry> match = filter(list,maf);
        /*for (QuakeEntry qe: match) { 
            System.out.println(qe);
        }*/
        System.out.println("Quakes that match all filters "+match.size());
        System.out.println("Filters used are : "+maf.getName());
    }
    
    public void testMatchAllFilter(){
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");
        //dumpCSV(list);
        
        double minMag = 1.0;
        double maxMag = 4.0;
        double minDep = -180000.0;
        double maxDep = -30000.0;
        String where = "any";
        String phrase = "o";
        Filter fMag = new MagnitudeFilter(minMag,maxMag);
        Filter fDep = new DepthFilter(minDep,maxDep);
        Filter fPhra = new PhraseFilter(where,phrase);
        MatchAllFilter maf = new MatchAllFilter();
        maf.addFilter(fMag);
        maf.addFilter(fDep);
        maf.addFilter(fPhra);
        //MatchAllFilter can be used as a Filter object
        //since it implements Filter
        ArrayList<QuakeEntry> match = filter(list,maf);
        /*for (QuakeEntry qe: match) { 
            System.out.println(qe);
        }*/
        System.out.println("Quakes that match all filters "+match.size());
        System.out.println("Filters used are : "+maf.getName());
    }
    
    public ArrayList<QuakeEntry> filter(ArrayList<QuakeEntry> quakeData, Filter f) { 
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData) { 
            if (f.satisfies(qe)) { 
                answer.add(qe); 
            } 
        } 
        
        return answer;
    } 

    public void quakesWithFilter() { 
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");
        
        //Filter f = new MinMagFilter(4.0); 
        //ArrayList<QuakeEntry> m7  = filter(list, f); 
        /*for (QuakeEntry qe: m7) { 
            System.out.println(qe);
        }*/
        
        double minMag = 3.5;
        double maxMag = 4.5;
        double minDep = -55000.0;
        double maxDep = -20000.0;
        /**/
        Filter fMag = new MagnitudeFilter(minMag,maxMag);
        Filter fDist = new DepthFilter(minDep,maxDep);
        ArrayList<QuakeEntry> listMag = filter(list,fMag);
        ArrayList<QuakeEntry> listAndDist = filter(listMag,fDist);
        /**/
         
        System.out.println("Quakes that match all are "+listAndDist.size());
        
        /*for (QuakeEntry qe: listAndDist) { 
            System.out.println(qe);
        }*/
        
        /*Location loc = new Location(39.7492,-104.9903);
        double dist = 1000000;
        
        Filter fDist = new DistanceFilter(loc,dist);
        String where = "end";
        String phrase = "a";
        Filter fPhra = new PhraseFilter(where,phrase);
        
        ArrayList<QuakeEntry> listDist = filter(list,fDist);
        ArrayList<QuakeEntry> listAndPhrase = filter(listDist,fPhra);
        System.out.println("Quakes that match all are "+listAndPhrase.size());
        for (QuakeEntry qe: listAndPhrase) { 
            System.out.println(qe);
        }*/
    }

    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "../data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: "+list.size());
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
