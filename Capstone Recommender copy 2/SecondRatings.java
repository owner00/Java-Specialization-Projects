
/**
 * Write a description of SecondRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<EfficientRater> myRaters;
    
    public SecondRatings() {
        // default constructor
        this("ratedmoviesfull.csv", "ratings.csv");
    }
    
    public SecondRatings(String movieFile, String ratingsFile) {
        FirstRatings loader = new FirstRatings();
        myMovies = loader.loadMovies(movieFile);
        myRaters = loader.loadRaters(ratingsFile);
    }
    
    public int getMovieSize(){
        return myMovies.size();
    }
    
    public int getRaterSize(){
        return myRaters.size();
    }
    
    public String getID(String title){
        for(Movie movie : myMovies){
            if(movie.getTitle().equals(title)){
                return movie.getID();
            }
        }
        return "NO SUCH TITLE";
    }
    
    public String getTitle(String movieID){
        for(Movie movie : myMovies){
            if(movie.getID().equals(movieID)){
                return movie.getTitle();
            }
        }
        return "Title not found";
    }
    
    public double getAverageByID(String movieID, int minimalRaters){
        if(hasMinimalRaters(movieID,minimalRaters)){
            return averageMovRatingByID(movieID);
        }
        return 0.0;
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
        for(Movie movie : myMovies){
            String movieID = movie.getID();
            if(hasMinimalRaters(movieID,minimalRaters)){
                avgRatingsWithMinimalRaters.add(new Rating(movieID
                    ,averageMovRatingByID(movieID)));
            }
        }
        return avgRatingsWithMinimalRaters;
    }
    
    
}
