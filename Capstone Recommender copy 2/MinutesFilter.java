public class MinutesFilter implements Filter{
    private int minMinutes;
    private int maxMinutes;
    
    public MinutesFilter(int min, int max){
        minMinutes = min;
        maxMinutes = max;
    }
    
    public boolean satisfies(String movieID){
        int movieDuration = MovieDatabase.getMinutes(movieID);
        if(movieDuration >=minMinutes && movieDuration <= maxMinutes){
            return true;
        }
        return false;
    }
}
