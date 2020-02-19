/**
 * Class to process movies and rating data
 * and answer questions about the movies
 * @author (Aja Ukpa Nnaemeka) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;

public class FirstRatings {
    public ArrayList<EfficientRater> loadRaters(String fileName){
        FileResource fr = new FileResource(fileName);
        CSVParser parser = fr.getCSVParser();
        ArrayList<EfficientRater> raters = new ArrayList<EfficientRater>();
        for(CSVRecord row : parser){
            String raterId = row.get("rater_id");
            EfficientRater rater = new EfficientRater(raterId);
            int index = raters.indexOf(rater);
            if(index!=-1){
                rater = raters.get(index);
                String movieId = row.get("movie_id");
                if(!rater.hasRating(movieId)){
                    double rating = Double.parseDouble(row.get("rating"));
                    rater.addRating(movieId,rating);
                    raters.set(index,rater);
                }
            }
            else{
                double rating = Double.parseDouble(row.get("rating"));
                String movieId = row.get("movie_id");
                rater.addRating(movieId,rating);
                raters.add(rater);
            }
        }
        return raters;
    }
    
    public void testLoadRaters(){
        ArrayList<EfficientRater> raters = loadRaters("data/ratings_short.csv");
        //Print total no of raters 
        //System.out.println(raters.size());
        
        //Print rater id, num of ratings by rater, and individual ratings 
        //by rater
        //allRaterDetails(raters);
        
        //Print rater by given id
        String raterId = "2";
        int num = ratingsByRater(raters,raterId);
        System.out.println("The number of ratings by rater with id "+raterId
        +" ==> "+num);
        
        //The largest ratings
        maxRatings(raters);
        
        //..
        String movId = "1798709";
        int noOfRatings = movieRatings(raters,movId);
        System.out.println("The number of ratings movie with id "+movId
        +" have = "+noOfRatings);
        
        int noOfDifferentMovies = diffMovies(raters);
        System.out.println("There are "+noOfDifferentMovies+" different movies rated by the raters");
        
    }
    
    public void maxRatings(ArrayList<EfficientRater> raters){
        int large = 0;
        for(EfficientRater rater : raters){
            if(rater.numRatings()>large){
                large = rater.numRatings();
            }
        }
        System.out.println("max no of ratings is "+large);
        int count = 0;
        for(EfficientRater rater : raters){
            if(rater.numRatings()==large){
                count++;
            }
        }
        System.out.println("Raters that have max rating are "+count
        +"\nThe Raters are ==>");
        
        for(EfficientRater rater : raters){
            if(rater.numRatings()==large){
                System.out.println(rater);
            }
        }
    }
    
    public int ratingsByRater(ArrayList<EfficientRater> raters, String raterId){
        EfficientRater newRater = new EfficientRater(raterId);
        for(EfficientRater rater : raters){
            if(rater.equals(newRater)){
                newRater = rater;
            }
        }
        return newRater.numRatings();
    }
    
    public void allRaterDetails(ArrayList<EfficientRater> raters){
        for(EfficientRater rater : raters){
            System.out.println("Rater id "+rater.getID());
            System.out.println("Number of ratings "+rater.numRatings());
            System.out.println("Ratings are ==>");
            for(Rating rating : rater.getRatings().values()){
                System.out.println(rating);
            }
            System.out.println("------ ------- ------- -------- -------");
        }
    }
    
    public int movieRatings(ArrayList<EfficientRater> raters, String movieId){
        int count = 0;
        for(EfficientRater rater : raters){
            if(rater.hasRating(movieId)){
                count++;
            }
        }
        return count;
    }
    
    public int diffMovies(ArrayList<EfficientRater> raters){
        ArrayList<String> holder = new ArrayList<String>();
        for(EfficientRater rater : raters){
            HashMap<String,Rating> ratings = rater.getRatings();
            for(Rating rat : ratings.values()){
                if(!holder.contains(rat.getItem())){
                    holder.add(rat.getItem());
                }
            }
        }
        return holder.size();
    }
    
    /**
     * Method loadMovies
     * @param fileName contains movie csv
     * @return arraylist of movies contained in fileName
     */
    public ArrayList<Movie> loadMovies(String fileName){
        FileResource fr = new FileResource(fileName);
        CSVParser parser = fr.getCSVParser();
        ArrayList<Movie> movies = new ArrayList<Movie>();
        for(CSVRecord row : parser){
            Movie mov = new Movie(row.get("id"),row.get("title"),row.get("year")
            ,row.get("genre"),row.get("director"),row.get("country")
            ,row.get("poster"),Integer.parseInt(row.get("minutes")));
            movies.add(mov);
        }
        return movies;
    }
    
    public void testLoadMovies(){
        ArrayList<Movie> movies = loadMovies("data/ratedmovies_short.csv");
        int noOfMovies = movies.size();
        //System.out.println("Number of movies are "+noOfMovies);
        // String genre = "Romance";
        // noOfMovies = withGenre(movies,genre);
        //System.out.println("Movies with genre "+genre+" are = "+noOfMovies);
        // int mins = 150;
        // noOfMovies = withTimeGreaterThan(movies,mins);
        // System.out.println("Movies with length greater than "+mins+" are = "+noOfMovies);
        noOfMovies = maxNumberOfMoviesByDirector(movies);
        System.out.println("Maximum number of movies by any director is = "+noOfMovies);
    }
    
    public int withGenre(ArrayList<Movie> movies, String genre){
        int num = 0;
        for(Movie movie : movies){
            String movieGenres = movie.getGenres();
            if(movieGenres.contains(genre)){
                num++;
            }
        }
        return num;
    }
    
    public int withTimeGreaterThan(ArrayList<Movie> movies, int minutes){
        int num = 0;
        for(Movie movie : movies){
            int movieMinutes = movie.getMinutes();
            if(movieMinutes>minutes){
                num++;
            }
        }
        return num;
    }
    
    public int maxNumberOfMoviesByDirector(ArrayList<Movie> movies){
        HashMap<String,Integer> directorsMovies = new HashMap<String,Integer>();
        int num;
        for(Movie movie : movies){
            // Adding all directors to HashMap
            String director = movie.getDirector();
            if(!directorsMovies.containsKey(director)){
                num = 0;
                // Adding all Directors movies to corresponding Director
                for(Movie mov : movies){
                    if(mov.getDirector().contains(director)){
                        num++;
                    }
                }
                directorsMovies.put(director,num);
            }
        }
        int largest = 0;
        for(int large : directorsMovies.values()){
            if(large>largest){
                largest = large;
            }
        }
        System.out.println("The following directors made the largest number of movies");
        ArrayList<String> directors = new ArrayList<String>();
        for(String director : directorsMovies.keySet()){
            if(directorsMovies.get(director) == largest){
                System.out.println(director);
                directors.add(director);
            }
        }
        System.out.println("----- ------ ------ ------ ------- -------- -------");
        System.out.println("The number of directors with the largest number of films are "+directors.size());
        return largest;
    }
}
