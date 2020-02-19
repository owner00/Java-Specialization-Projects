
public class GenreFilter implements Filter{
    private String genre;
    
    public GenreFilter(String genree){
        genre = genree;
    }
    
    public boolean satisfies(String movieID){
        if(MovieDatabase.getGenres(movieID).indexOf(genre)!=-1){
            return true;
        }
        return false;
    }
}
