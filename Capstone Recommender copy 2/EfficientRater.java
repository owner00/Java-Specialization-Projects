import java.util.*;

public class EfficientRater implements Rater{
    private String myID;
    private HashMap<String,Rating> myRatings;

    public EfficientRater(String id) {
        myID = id;
        myRatings = new HashMap<String,Rating>();
    }

    public void addRating(String item, double rating) {
        myRatings.put(item,new Rating(item,rating));
    }

    public boolean hasRating(String item) {
        if (myRatings.containsKey(item)){
                return true;
        }
        return false;
    }

    public String getID() {
        return myID;
    }
    
    public HashMap<String,Rating> getRatings() {
        return myRatings;
    }

    public double getRating(String item) {
        for(String movieID : myRatings.keySet()){
            if (movieID.equals(item)){
                return myRatings.get(movieID).getValue();
            }
        }
        
        return -1;
    }

    public int numRatings() {
        return myRatings.size();
    }

    public ArrayList<String> getItemsRated() {
        ArrayList<String> list = new ArrayList<String>();
        for(String movieID : myRatings.keySet()){
            list.add(movieID);
        }
        
        return list;
    }
    
    public boolean equals(Object o){
        EfficientRater other = (EfficientRater)o;
        if(myID.equals(other.getID())){
            return true;
        }
        return false;
    }
    
    public String toString(){
        return "Rater's id ==> "+myID;
    }
}
