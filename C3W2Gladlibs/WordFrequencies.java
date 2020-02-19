
/**
 * Write a description of WordFrequencies here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;

public class WordFrequencies {
    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;
    
    public WordFrequencies(){
        myWords = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
    }
    
    public void tester(){
        findUnique();
        System.out.println("# of unique words: "+myWords.size());
        int maxIndex = findIndexOfMax();
        //for(int i = 0;i<myWords.size();i++){
        //    System.out.println("Freq: "+myFreqs.get(i)+"\t"
        //    +"Word: "+myWords.get(i));
        //}
        System.out.println("Most occuring word: "+myWords.get(maxIndex) 
        +" "+myFreqs.get(maxIndex));
        
    }
    
    public void findUnique(){
        FileResource fr = new FileResource();
        for(String word : fr.words()){
            word = word.toLowerCase();
            int index = myWords.indexOf(word);
            if(index == -1){
                myWords.add(word);
                myFreqs.add(1);
            }
            else{
                myFreqs.set(index, myFreqs.get(index)+1);
            }
        }
    }
    
    public int findIndexOfMax(){
        int greatest = 0;
        int index = 0;
        for(int i=0;i<myFreqs.size();i++){
            if(myFreqs.get(i)>greatest){
                greatest = myFreqs.get(i);
                index = i;
            }
        }
        return index;
    }
}
