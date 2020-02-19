
/**
 * Write a description of WordLengths here.
 * 
 * @author (Aja Nnaemeka) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class WordLengths {
    public int indexOfMax(int[] values){
        int maxIndex = 0;
        int largest = 0;
        for(int a=0;a<values.length;a++){
            if(values[a]>largest){
                maxIndex = a;
                largest = values[a];
            }
        }
        return maxIndex;
    }
    
    public void countWordLengths(FileResource fr, int[] counts){
        for(String word : fr.words()){
            int temp = word.length();
            int length = correctedLength(word, temp);
            counts[length] += 1;
        }
    }
    
    public int correctedLength(String word, int temp){
        int length = temp;
        if(!Character.isLetter(word.charAt(0))
             ||!Character.isLetter(word.charAt(word.length()-1))){
                 if(!Character.isLetter(word.charAt(0))){
                        length -= 1;
                  }
                 else if(!Character.isLetter(word.charAt(word.length()-1))){
                        length -= 1;
                  }
        }
        return length;
    }
    
    public void testCountWordLengths(){
        FileResource fr = new FileResource();
        int[] arr = new int[31];
        countWordLengths(fr,arr);
        //for(int a = 1;a<arr.length;a++){
        //    System.out.println(a+"'s = "+arr[a]);
        //}
        System.out.println("Most common word length: "+indexOfMax(arr));
    }
}
