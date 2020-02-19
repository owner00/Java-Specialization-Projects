import java.util.*;

public class MovieRunnerSimilarRatings {
    public void printSimilarRatingsByYearAfterAndMinutes(){
        FourthRatings fortRat = new FourthRatings("data/ratings.csv");
        System.out.println("Num of movies => "+MovieDatabase.size()
            +"\nNum of raters => "+fortRat.getRaterSize());
        String raterID = "65";
        int topSimilarRaters = 10;
        int minimalRaters = 5;
        
        int year = 2000;
        int min = 80;
        int max = 100;
        
        YearAfterFilter yaf = new YearAfterFilter(year);
        MinutesFilter mf = new MinutesFilter(min,max);
        AllFilters af = new AllFilters();
        
        af.addFilter(yaf);
        af.addFilter(mf);
        
        ArrayList<Rating> res = fortRat.getSimilarRatingsWFilter(raterID
            ,topSimilarRaters,minimalRaters,yaf);
            
        System.out.println("Ratings returned are "+res.size());
        System.out.println("The similar averaged movie ratings with at least "
            +minimalRaters+" Raters");
        for(Rating rating : res){
            System.out.println(rating.getValue()+" "
                +MovieDatabase.getTitle(rating.getItem())+"\n   "
                +MovieDatabase.getDirector(rating.getItem()));
        }
    }
    
    public void printSimilarRatingsByDirector(){
        FourthRatings fortRat = new FourthRatings("data/ratings.csv");
        System.out.println("Num of movies => "+MovieDatabase.size()
            +"\nNum of raters => "+fortRat.getRaterSize());
        String directors = "Clint Eastwood,Sydney Pollack,David Croneberg,Oliver Stone";
        String raterID = "1034";
        int topSimilarRaters = 10;
        int minimalRaters = 3;
        DirectorFilter df = new DirectorFilter(directors);
        
        ArrayList<Rating> res = fortRat.getSimilarRatingsWFilter(raterID
            ,topSimilarRaters,minimalRaters,df);
            
        System.out.println("Ratings returned are "+res.size());
        System.out.println("The similar averaged movie ratings with at least "
            +minimalRaters+" Raters");
        for(Rating rating : res){
            System.out.println(rating.getValue()+" "
                +MovieDatabase.getTitle(rating.getItem())+"\n   "
                +MovieDatabase.getDirector(rating.getItem()));
        }
    }
   
    public void printSimilarRatingsByGenre(){
        FourthRatings fortRat = new FourthRatings("data/ratings.csv");
        System.out.println("Num of movies => "+MovieDatabase.size()
            +"\nNum of raters => "+fortRat.getRaterSize());
        String raterID = "65";
        int topSimilarRaters = 20;
        int minimalRaters = 5;
        String genre = "Action";
        GenreFilter gf = new GenreFilter(genre);
        
        ArrayList<Rating> res = fortRat.getSimilarRatingsWFilter(raterID
            ,topSimilarRaters,minimalRaters,gf);
            
        System.out.println("Ratings returned are "+res.size());
        System.out.println("The similar averaged movie ratings with at least "
            +minimalRaters+" Raters");
        for(Rating rating : res){
            System.out.println(rating.getValue()+" "
                +MovieDatabase.getTitle(rating.getItem())+"\n   "
                +MovieDatabase.getGenres(rating.getItem()));
        }
    }
    
    public void printSimilarRatings(){
        FourthRatings fortRat = new FourthRatings("data/ratings.csv");
        String raterID = "65";
        int topSimilarRaters = 20;
        int minimalRaters = 5;
        ArrayList<Rating> res = fortRat.getSimilarRatings(raterID,topSimilarRaters
            ,minimalRaters);
            
        for(Rating rat : res){
            System.out.println(MovieDatabase.getTitle(rat.getItem())
                +" "+rat);
        }
    }
    
    public void printAverageRatingsByYearAfterAndGenre(){
        FourthRatings fortRat = new FourthRatings("data/ratings_short.csv");
        System.out.println("Num of movies => "+MovieDatabase.size()
            +"\nNum of raters => "+fortRat.getRaterSize());
        int minimalRaters = 1;
        int year = 1980;
        String genre = "Romance";
        GenreFilter gf = new GenreFilter(genre);
        YearAfterFilter yaf = new YearAfterFilter(year);
        AllFilters af = new AllFilters();
        
        af.addFilter(gf);
        af.addFilter(yaf);
        ArrayList<Rating> res = fortRat.getAverageRatingsByFilter(minimalRaters,af);
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
    
    public void printAverageRatings(){
        FourthRatings fortRat = new FourthRatings("data/ratings_short.csv");
        System.out.println("Num of movies => "+MovieDatabase.size()
            +"\nNum of raters => "+fortRat.getRaterSize());
        int minimalRaters = 1;
        ArrayList<Rating> res = fortRat.getAverageRatings(minimalRaters);
        Collections.sort(res);
        System.out.println("Ratings returned are "+res.size());
        System.out.println("The average movie ratings with at least are "
            +minimalRaters+" Raters");
        for(Rating rating : res){
            System.out.println(rating.getValue()+" "+MovieDatabase.getTitle(rating.getItem()));
        }
    }
}
