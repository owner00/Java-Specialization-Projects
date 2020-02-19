import java.util.*;
/**
 * Write a description of MovieRunnerWithFilters here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MovieRunnerWithFilters {
    public void printAverageRatingsByDirectorsAndMinutes(){
        ThirdRatings thirdRat = new ThirdRatings("data/ratings_short.csv");
        System.out.println("Num of movies => "+MovieDatabase.size()
            +"\nNum of raters => "+thirdRat.getRaterSize());
        int minimalRaters = 1;
        int min = 110;
        int max = 170;
        String directors = "Charles Chaplin,Michael Mann,Spike Jonze";
        DirectorFilter df = new DirectorFilter(directors);
        MinutesFilter mf = new MinutesFilter(min,max);
        AllFilters af = new AllFilters();
        
        af.addFilter(df);
        af.addFilter(mf);
        
        ArrayList<Rating> res = thirdRat.getAverageRatingsByFilter(minimalRaters,af);
        Collections.sort(res);
        System.out.println("Found "+res.size()+" movies");
        System.out.println("The average movie ratings with at least "
            +minimalRaters+" Raters");
        for(Rating rating : res){
            System.out.println(rating.getValue()+" Time: "
                +MovieDatabase.getMinutes(rating.getItem())+" "
                +MovieDatabase.getTitle(rating.getItem())
                +"\n     "+MovieDatabase.getDirector(rating.getItem()));
        }
    }
    
    public void printAverageRatingsByYearAfterAndGenre(){
        ThirdRatings thirdRat = new ThirdRatings("data/ratings_short.csv");
        System.out.println("Num of movies => "+MovieDatabase.size()
            +"\nNum of raters => "+thirdRat.getRaterSize());
        int minimalRaters = 1;
        int year = 1980;
        String genre = "Romance";
        GenreFilter gf = new GenreFilter(genre);
        YearAfterFilter yaf = new YearAfterFilter(year);
        AllFilters af = new AllFilters();
        
        af.addFilter(gf);
        af.addFilter(yaf);
        ArrayList<Rating> res = thirdRat.getAverageRatingsByFilter(minimalRaters,af);
        Collections.sort(res);
        System.out.println("Ratings returned are "+res.size());
        System.out.println("The average movie ratings with at least "
            +minimalRaters+" Raters");
        for(Rating rating : res){
            System.out.println(rating.getValue()+" "
                +MovieDatabase.getYear(rating.getItem())+" "
                +MovieDatabase.getTitle(rating.getItem())
                +"\n     "+MovieDatabase.getGenres(rating.getItem()));
        }
    }
    
    public void printAverageRatingsByDirectors(){
        ThirdRatings thirdRat = new ThirdRatings("data/ratings_short.csv");
        System.out.println("Num of movies => "+MovieDatabase.size()
            +"\nNum of raters => "+thirdRat.getRaterSize());
        int minimalRaters = 1;
        String directors = "Charles Chaplin,Michael Mann,Spike Jonze";
        DirectorFilter df = new DirectorFilter(directors); 
        ArrayList<Rating> res = thirdRat.getAverageRatingsByFilter(minimalRaters,df);
        Collections.sort(res);
        System.out.println("Found "+res.size()+" movies");
        System.out.println("The average movie ratings with at least "
            +minimalRaters+" Raters");
        for(Rating rating : res){
            System.out.println(rating.getValue()+" Time: "
                +MovieDatabase.getTitle(rating.getItem())
                +"\n     "+MovieDatabase.getDirector(rating.getItem()));
            
        }
    }
    
    public void printAverageRatingsByMinutes(){
        ThirdRatings thirdRat = new ThirdRatings("data/ratings_short.csv");
        System.out.println("Num of movies => "+MovieDatabase.size()
            +"\nNum of raters => "+thirdRat.getRaterSize());
        int minimalRaters = 1;
        int min = 110;
        int max = 170;
        MinutesFilter mf = new MinutesFilter(min,max);
        ArrayList<Rating> res = thirdRat.getAverageRatingsByFilter(minimalRaters,mf);
        Collections.sort(res);
        System.out.println("Found "+res.size()+" movies");
        System.out.println("The average movie ratings with at least "
            +minimalRaters+" Raters");
        for(Rating rating : res){
            System.out.println(rating.getValue()+" Time: "
                +MovieDatabase.getMinutes(rating.getItem())+" "
                +MovieDatabase.getTitle(rating.getItem()));
        }
    }
    
    public void printAverageRatingsByGenre(){
        ThirdRatings thirdRat = new ThirdRatings("data/ratings_short.csv");
        System.out.println("Num of movies => "+MovieDatabase.size()
            +"\nNum of raters => "+thirdRat.getRaterSize());
        int minimalRaters = 1;
        String genre = "Crime";
        GenreFilter gf = new GenreFilter(genre);
        ArrayList<Rating> res = thirdRat.getAverageRatingsByFilter(minimalRaters,gf);
        Collections.sort(res);
        System.out.println("Found "+res.size()+" movies");
        System.out.println("The average movie ratings with at least "
            +minimalRaters+" Raters");
        for(Rating rating : res){
            System.out.println(rating.getValue()+" "
                +MovieDatabase.getTitle(rating.getItem()));
            System.out.println("    "+MovieDatabase.getGenres(rating.getItem()));
        }
    }
    
    public void printAverageRatingsByYear(){
        ThirdRatings thirdRat = new ThirdRatings("data/ratings_short.csv");
        System.out.println("Num of movies => "+MovieDatabase.size()
            +"\nNum of raters => "+thirdRat.getRaterSize());
        int minimalRaters = 1;
        int year = 2000;
        YearAfterFilter yaf = new YearAfterFilter(year);
        ArrayList<Rating> res = thirdRat.getAverageRatingsByFilter(minimalRaters,yaf);
        Collections.sort(res);
        System.out.println("Ratings returned are "+res.size());
        System.out.println("The average movie ratings with at least "
            +minimalRaters+" Raters");
        for(Rating rating : res){
            System.out.println(rating.getValue()+" "
                +MovieDatabase.getYear(rating.getItem())+" "
                +MovieDatabase.getTitle(rating.getItem()));
        }
    }
    
    public void printAverageRatings(){
        ThirdRatings thirdRat = new ThirdRatings("data/ratings_short.csv");
        System.out.println("Num of movies => "+MovieDatabase.size()
            +"\nNum of raters => "+thirdRat.getRaterSize());
        int minimalRaters = 1;
        ArrayList<Rating> res = thirdRat.getAverageRatings(minimalRaters);
        Collections.sort(res);
        System.out.println("Ratings returned are "+res.size());
        System.out.println("The average movie ratings with at least are "
            +minimalRaters+" Raters");
        for(Rating rating : res){
            System.out.println(rating.getValue()+" "+MovieDatabase.getTitle(rating.getItem()));
        }
    }
}
