import java.util.*;

public class MatchAllFilter implements Filter{
    private ArrayList<Filter> filter;
    
    public MatchAllFilter(){
        filter = new ArrayList<Filter>();
    }
    
    public void addFilter(Filter flter){
        filter.add(flter);
    }
    
    public boolean satisfies(QuakeEntry qe){
        for(Filter ft : filter){
            if(!ft.satisfies(qe)){
                return false;
            }
        }
        return true;
    }
    
    public String getName(){
        StringBuilder sb = new StringBuilder();
        for(Filter f : filter){
            sb.append(f.getName()+" ");
        }
        return sb.toString();
    }
}
