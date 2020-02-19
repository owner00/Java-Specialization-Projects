import java.util.*;
public class TitleLastAndMagnitudeComparator implements Comparator<QuakeEntry>{
    public int compare(QuakeEntry qe1, QuakeEntry qe2){
        String lWordQE1 = lastWord(qe1.getInfo());
        String lWordQE2 = lastWord(qe2.getInfo());
        if(lWordQE1.equals(lWordQE2)){
            if(qe1.getMagnitude()<qe2.getMagnitude()){
                return -1;
            }
            if(qe1.getMagnitude()>qe2.getMagnitude()){
                return 1;
            }
            return 0;
        }
        return lWordQE1.compareTo(lWordQE2);
    }
    
    public String lastWord(String str){
        String word = str.substring(str.lastIndexOf(" ")+1);
        return word;
    }
}
