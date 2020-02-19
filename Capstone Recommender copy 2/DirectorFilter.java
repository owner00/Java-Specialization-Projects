
/**
 * Write a description of DirectorFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DirectorFilter implements Filter{
    private String[] directors;
    
    public DirectorFilter(String parameter){
        directors = parameter.split(",");
    }
    
    public boolean satisfies(String movieID){
        for(int i=0;i<directors.length;i++){
            if(MovieDatabase.getDirector(movieID).indexOf(directors[i])!=-1){
                return true;
            }
        }
        return false;
    }
}
