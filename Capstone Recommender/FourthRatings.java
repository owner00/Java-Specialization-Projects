import java.util.*;

public class FourthRatings {
    private ArrayList<Rater> myRaters;

    public FourthRatings() {
        // default constructor
        this("data/ratings.csv");
    }

    public FourthRatings(String ratingsFile) {
        RaterDatabase.addRatings(ratingsFile);
        myRaters = RaterDatabase.getRaters();
    }

    public int getRaterSize(){
        return myRaters.size();
    }

    public ArrayList<Rater> getRaterObjects(ArrayList<Rating> similarRaters){
        ArrayList<Rater> allRaters = new ArrayList<Rater>();
        for(Rating raterID : similarRaters){
            allRaters.add(RaterDatabase.getRater(raterID.getItem()));
        }
        return allRaters;
    }
    
    public ArrayList<Rater> getTopRaters(ArrayList<Rater> allSimRaters
        ,int numSimilarRaters){
        int size = 0;
        if(allSimRaters.size()>=numSimilarRaters){
            size = numSimilarRaters;
        }
        else{
            size = allSimRaters.size();
        }
        ArrayList<Rater> allTopRaters = new ArrayList<Rater>();
        for(int i=0;i<size;i++){
            allTopRaters.add(allSimRaters.get(i));
        } 
        return allTopRaters;
    }
    
    public ArrayList<String> getSimAndTopMovies(ArrayList<Rater> allTopRaters){
        ArrayList<String> movies = new ArrayList<String>();
        for(Rater rater : allTopRaters){
            HashMap<String,Rating> map = rater.getRatings();
            for(Rating rating : map.values()){
                if(!movies.contains(rating.getItem())){
                    movies.add(rating.getItem());
                }
            }
        }
        return movies;
    }
  
    public ArrayList<String> getSimAndTopMoviesWFilter(ArrayList<Rater> allTopRaters
        ,Filter f){
        ArrayList<String> movies = new ArrayList<String>();
        
        for(Rater rater : allTopRaters){
            HashMap<String,Rating> map = rater.getRatings();
            for(Rating rating : map.values()){
                String movieID = rating.getItem();
                if(!movies.contains(movieID) 
                    && f.satisfies(movieID) 
                    ){
                        movies.add(rating.getItem());
                }
            }
        }
        return movies;
    }
    
    public ArrayList<Rating> getSimilarRatings(String raterID
        ,int numSimilarRaters,int minimalRaters){
        ArrayList<Rating> res = new ArrayList<Rating>();
        //similarRaters is an array of +VE rater id to their closeness value
        ArrayList<Rating> similarityRating = getSimilarities(raterID);
        System.out.println("similarityRating returned are "+similarityRating.size());
        //to retrieve all similar rater objects
        ArrayList<Rater> allSimRaters = getRaterObjects(similarityRating);
        System.out.println("allSimRaters returned are "+allSimRaters.size());
        //to retrieve top of similarRaters by numSimilarRaters
        ArrayList<Rater> allTopRaters = getTopRaters(allSimRaters,numSimilarRaters);
        System.out.println("allTopRaters returned are "+allTopRaters.size());
        //to get all movies found in allSimRaters
        ArrayList<String> movies = getSimAndTopMovies(allTopRaters);
        System.out.println("movies returned are "+movies.size());
        
        //all weighted average ratings of all movies 
        //in all checked raters
        ArrayList<Rating> temp = cgetAverageRatings(minimalRaters
            ,movies,allTopRaters,similarityRating);
        Collections.sort(temp, Collections.reverseOrder());
        return temp;
    }
    
    public ArrayList<Rating> getSimilarRatingsWFilter(String raterID
        ,int numSimilarRaters,int minimalRaters,Filter f){
        ArrayList<Rating> res = new ArrayList<Rating>();
        ArrayList<Rating> similarityRating = getSimilarities(raterID);
        ArrayList<Rater> allSimRaters = getRaterObjects(similarityRating);
        ArrayList<Rater> allTopRaters = getTopRaters(allSimRaters,numSimilarRaters);
        //Stopped here last...checked others mentally
        ArrayList<String> movies = getSimAndTopMoviesWFilter(allTopRaters,f);
        
        //all weighted average ratings of all movies 
        //in all checked raters
        ArrayList<Rating> temp = cgetAverageRatings(minimalRaters
            ,movies,allTopRaters,similarityRating);
        Collections.sort(temp, Collections.reverseOrder());
        return temp;
    }
    
    public ArrayList<Rating> getSimilarities(String raterID){
        ArrayList<Rating> res = new ArrayList<Rating>();
        Rater rat = RaterDatabase.getRater(raterID);
        for(Rater rater : myRaters){
            if(rater.getID().equals(raterID)){
                continue;
            }
            double vl = dotProduct(rater,rat);
            if(vl>0.0){
                res.add(new Rating(rater.getID(),vl));
            }
        }
        Collections.sort(res, Collections.reverseOrder());
        return res;
    }
    
    public double cgetAverageByID(String movieID, int minimalRaters
        ,ArrayList<Rater> checkedRaters
        ,ArrayList<Rating> ratersSimilarityValue){
        if(chasMinimalRaters(movieID,minimalRaters,checkedRaters)){
            return caverageMovRatingByID(movieID,checkedRaters,ratersSimilarityValue);
        }
        return 0.0;
    }

    public ArrayList<Rating> cgetSimilarRatingsByFilter (String raterID,
        int numSimilarRaters,int minimalRaters
        ,Filter filterCriteria,ArrayList<Rater> topRaters
        ,ArrayList<Rating> ratersSimilarityValue){
        ArrayList<Rating> simRtgFilter = new ArrayList<Rating>();
        ArrayList<String> passedCriterias = new ArrayList<String>();
        for(Rater efRater : topRaters){ 
            HashMap<String,Rating> ratersRatings = efRater.getRatings();
            for(String movieID : ratersRatings.keySet()){
                if(chasMinimalRaters(movieID,minimalRaters,topRaters)
                    && filterCriteria.satisfies(movieID)){
                    if(!passedCriterias.contains(movieID)){
                        simRtgFilter.add(new Rating(movieID
                            ,caverageMovRatingByID(movieID
                            ,topRaters,ratersSimilarityValue)));
                        passedCriterias.add(movieID);
                    }
                }
            }
        }
        Collections.sort(simRtgFilter, Collections.reverseOrder());
        return simRtgFilter;
    }
    
    //Helper method to check if movieId has a minimal number of raters 
    public boolean chasMinimalRaters(String movieID,int minimalRaters
        ,ArrayList<Rater> topRaters){
        int sumRaters = 0;
        for(Rater rater : topRaters){
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

    public double caverageMovRatingByID(String movieID
        ,ArrayList<Rater> checkedRaters
        ,ArrayList<Rating> ratersSimilarityValue){
        double sumRatings = 0.0;
        double sumRaters = 0.0;
        double similarityValue = 0.0;
        for(Rater rater : checkedRaters){
            String raterID = rater.getID();
            similarityValue = getSimilarityValue(raterID,ratersSimilarityValue);
            HashMap<String,Rating> allMoviesRated = rater.getRatings();
            for(Rating rating : allMoviesRated.values()){
                if(rating.getItem().equals(movieID)){
                    sumRatings += (rating.getValue()*similarityValue);
                    sumRaters++;
                    break;
                }
            }
        }
        double result = sumRatings/sumRaters;
        return result;
    }
    
    private double getSimilarityValue(String raterId
        ,ArrayList<Rating> similarityValue){
        for(Rating ratersID : similarityValue){
            if(ratersID.getItem().equals(raterId)){
                return ratersID.getValue();
            }
        }
        return 0.0;
    }

    public ArrayList<Rating> cgetAverageRatings(int minimalRaters
        ,ArrayList<String> checkedMovies,ArrayList<Rater> checkedRaters
        ,ArrayList<Rating> ratersSimilarityValue){
        ArrayList<Rating> avgRatingsWithMinimalRaters 
            = new ArrayList<Rating>();
        ArrayList<String> myMovies = checkedMovies;
        for(String movieID : myMovies){
            if(chasMinimalRaters(movieID,minimalRaters,checkedRaters)){
                avgRatingsWithMinimalRaters.add(new Rating(movieID
                    ,caverageMovRatingByID(movieID,checkedRaters,ratersSimilarityValue)));
            }
        }
        return avgRatingsWithMinimalRaters;
    }
    
    private double dotProduct(Rater me,Rater r){
        double result = 0.0;
        //to add all dot products of movies both rated by me and r
        HashMap<String,Rating> meRatings = me.getRatings();
        HashMap<String,Rating> rRatings = r.getRatings();
        HashMap<String,Rating> larger = null;
        HashMap<String,Rating> smaller = null;
        if(meRatings.size()>=rRatings.size()){
            larger = meRatings;
            smaller = rRatings;
        }
        else{
            larger = rRatings;
            smaller = meRatings;
        }
        for(int i=0;i<larger.size();i++){
            for(Rating ratig : larger.values()){
                String movieID = ratig.getItem();
                if(smaller.containsKey(movieID)){
                    double largVal = ratig.getValue()-5;
                    double smalVal = smaller.get(movieID).getValue()-5;
                    result += (largVal)*(smalVal);
                }
            }
        }
        
        return result;
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
        for(Rater efRater : myRaters){ 
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
        for(Rater rater : myRaters){
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
        for(Rater rater : myRaters){
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
