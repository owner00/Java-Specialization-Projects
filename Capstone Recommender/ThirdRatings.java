import java.util.*;

public class ThirdRatings {
    private ArrayList<EfficientRater> myRaters;

    public ThirdRatings() {
        // default constructor
        this("ratings.csv");
    }

    public ThirdRatings(String ratingsFile) {
        FirstRatings loader = new FirstRatings();
        myRaters = loader.loadRaters(ratingsFile);
    }

    public int getRaterSize(){
        return myRaters.size();
    }

    public double getAverageByID(String movieID, int minimalRaters){
        if(hasMinimalRaters(movieID,minimalRaters)){
            return averageMovRatingByID(movieID);
        }
        return 0.0;
    }

    public ArrayList<Rating> getAverageRatingsByFilter (int minimalRaters,Filter filterCriteria){
        ArrayList<Rating> avgRtgFilter = new ArrayList<Rating>();
        ArrayList<String> passedCriterias = new ArrayList<String>();
        for(EfficientRater efRater : myRaters){
            HashMap<String,Rating> ratersRatings = efRater.getRatings();
            for(String movieID : ratersRatings.keySet()){
                if(hasMinimalRaters(movieID,minimalRaters)
                    && filterCriteria.satisfies(movieID)){
                    if(!passedCriterias.contains(movieID)){
                        avgRtgFilter.add(new Rating(movieID
                            ,averageMovRatingByID(movieID)));
                        passedCriterias.add(movieID);
                    }
                }
            }
        }
        return avgRtgFilter;
    }
    
    //Helper method to check if movieId has a minimal number of raters 
    public boolean hasMinimalRaters(String movieID,int minimalRaters){
        int sumRaters = 0;
        for(EfficientRater rater : myRaters){
            HashMap<String,Rating> allMoviesRated = rater.getRatings();
            for(Rating rating : allMoviesRated.values()){
                if(rating.getItem().equals(movieID)){
                    sumRaters++;
                    break;
                }
            }
        }
        if(sumRaters>=minimalRaters){
            return true;
        }
        return false;
    }

    public double averageMovRatingByID(String movieID){
        double sumRatings = 0;
        double sumRaters = 0;
        for(EfficientRater rater : myRaters){
            HashMap<String,Rating> allMoviesRated = rater.getRatings();
            for(Rating rating : allMoviesRated.values()){
                if(rating.getItem().equals(movieID)){
                    sumRatings += rating.getValue();
                    sumRaters++;
                    break;
                }
            }
        }
        double result = sumRatings/sumRaters;
        return result;
    }

    public ArrayList<Rating> getAverageRatings(int minimalRaters){
        ArrayList<Rating> avgRatingsWithMinimalRaters 
            = new ArrayList<Rating>();
        ArrayList<String> myMovies = MovieDatabase.filterBy(new TrueFilter());
        for(String movieID : myMovies){
            if(hasMinimalRaters(movieID,minimalRaters)){
                avgRatingsWithMinimalRaters.add(new Rating(movieID
                    ,averageMovRatingByID(movieID)));
            }
        }
        return avgRatingsWithMinimalRaters;
    }

}
