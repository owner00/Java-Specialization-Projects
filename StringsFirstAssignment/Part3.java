
/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part3 {
    boolean twoOccurrences(String stringa, String stringb){
        boolean result = false;
        int firstIndex = stringb.indexOf(stringa);
        if(firstIndex != -1){
            int secondIndex = stringb.indexOf(stringa, firstIndex+stringa.length());
            if(secondIndex != -1){
                result = true;
            }
        }
        return result;
    }
    
    void testing(){
        String example1 = "";
        String example2 = "";
        //example1 = "by";
        //example2 = "A story by Abby Long";
        //System.out.println("Are there 2 occurrences? " 
        //+ twoOccurrences(example1, example2));
        
        //example1 = "a";
        //example2 = "banana";
        //System.out.println("Are there 2 occurrences? " 
        //+ twoOccurrences(example1, example2));
        
        //example1 = "atg";
        //example2 = "ctgtatgta";
        //System.out.println("Are there 2 occurrences? " 
        //+ twoOccurrences(example1, example2));
        
        example1 = "an";
        example2 = "banana";
        System.out.println("Whats the last part? " 
        + lastPart(example1, example2));
        
        example1 = "zoo";
        example2 = "forest";
        System.out.println("Whats the last part? " 
        + lastPart(example1, example2));
    }
    
    String lastPart(String stringa, String stringb){
        String result = "";
        int firstIndex = stringb.indexOf(stringa);
        if(firstIndex != -1){
            result = stringb.substring(firstIndex+stringa.length()
            ,stringb.length());
        }
        else if(firstIndex == -1){
            result = stringb;
        }
        return result;
    }
}
