
/**
 * Write a description of CharactersInPlay here.
 * 
 * @author (Aja Ukpa Nnaemeka) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;

public class CharactersInPlay {
    private ArrayList<String> words;
    private ArrayList<Integer> freqs;
    
    public CharactersInPlay(){
        words = new ArrayList<String>();
        freqs = new ArrayList<Integer>();
    }
    
    public void findAllCharacters(){
        FileResource fr = new FileResource();
        for(String line : fr.lines()){
            int period = line.indexOf(".");
            if(period!=-1){
                String character = line.substring(0,period);
                update(character);
            }
        }
    }
    
    public void tester(){
        findAllCharacters();
        //for(int i=0;i<words.size();i++){
        //    if(freqs.get(i)>2){
        //        System.out.println(words.get(i)+"\t"+freqs.get(i));
        //    }
        //}
        int num1 = 70;
        int num2 = 500;
        CharacterWithNumParts(num1,num2);
    }
    
    public void CharacterWithNumParts(int num1,int num2){
        System.out.println("Characters between "+num1
                +" and "+num2);
        for(int i = 0;i<words.size();i++){
            if((freqs.get(i)>=num1)&&(freqs.get(i)<num2)){
                System.out.println(words.get(i)+"occurs"+freqs.get(i));
            }
        }
    }
    
    public void update(String person){
        int index = words.indexOf(person);
        if(index==-1){
            words.add(person);
            freqs.add(1);
        }
        else{
            freqs.set(index,freqs.get(index)+1);
        }
    }
}
