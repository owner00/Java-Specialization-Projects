import java.util.*;

public class MovieRunnerAverage {
    public void getAverageRatingOneMovie(){
        SecondRatings secRat = new SecondRatings("data/ratedmovies_short.csv"
            ,"data/ratings_short.csv");
        String movieTitle = "Her";
        String movieID = secRat.getID(movieTitle);
        double avg = secRat.averageMovRatingByID(movieID);
        System.out.println("The average rating of the movie "
            +movieTitle+" is "+avg);
    }
    
    public void printAverageRatings(){
        SecondRatings secRat = new SecondRatings("data/ratedmovies_short.csv"
            ,"data/ratings_short.csv");
        // System.out.println("Num of movies => "+secRat.getMovieSize()
            // +"\nNum of ratings => "+secRat.getRaterSize());
        int minimalRaters = 3;
        ArrayList<Rating> res = secRat.getAverageRatings(minimalRaters);
        Collections.sort(res);
        System.out.println("The average movie ratings with at least "
            +minimalRaters+" Raters");
        for(Rating rating : res){
            System.out.println(rating.getValue()+" "+secRat.getTitle(rating.getItem()));
        }
    }
}
