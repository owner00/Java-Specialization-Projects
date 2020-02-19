public class DistanceFilter implements Filter{
    private Location loc;
    private double maxDist;
    
    public DistanceFilter(Location location,double maxDistance){
        loc = location;
        maxDist = maxDistance;
    }
    
    public boolean satisfies(QuakeEntry qe){
        return qe.getLocation().distanceTo(loc) < maxDist;
    }
    
    public String getName(){
        return "DistanceFilter";
    }
}
