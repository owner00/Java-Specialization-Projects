
/**
 * Write a description of DnaThreeCodon here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;

public class DnaThreeCodon {
    private HashMap<String,Integer> map;
    private HashMap<Integer,HashMap<String,Integer>> readingFrame;
    private int count =0;
    public DnaThreeCodon(){
        map = new HashMap<String,Integer>();
        readingFrame = new HashMap<Integer,HashMap<String,Integer>>();
    }
    
    public void tester(){
        FileResource fr = new FileResource();
        String dna = fr.asString();
        //String dna = "CGTTCAAGTTCAA";
        int start = 0;
        int end = 10;
        for(int i=0;i<3;i++){
            System.out.println("Reading Frame : "+(i));
            map = buildCodonMap(i,dna);
            for(Integer k: map.values()){
                count+=i;
            }
            System.out.println("Unique codons: "+count);
            count=0;
            printCodonCounts(map,start,end);
            getMostCommonCodon(map);
            readingFrame.put(i,map);
        }
    }
    
    void printCodonCounts(HashMap<String,Integer> map, int start, int end){
        for(String codon: map.keySet()){
            if((map.get(codon)>=start)&&(map.get(codon)<=end)){
                System.out.println("codon:"+codon+"\t"+"count:"+map.get(codon));
            }
        }
    }
    
    HashMap<String,Integer> buildCodonMap(int start, String dna){
        map.clear();
        //               v  v  v  v  v
        //               0123456789012
        //example string CGTTCAAGTTCAA
        for(int i=start;i<dna.length();i+=3){
            if(i+3>dna.length()){
                break;
            }
            String input = dna.substring(i,i+3);
            if(!map.keySet().contains(input.toUpperCase())){
                map.put(input.toUpperCase(),1);
            }   
            else{
                map.put(input.toUpperCase(),map.get(input.toUpperCase())+1);
            }
        }
        
        
        return map;
    }
    
    void getMostCommonCodon(HashMap<String,Integer> map){
        int greater = 0;
        String result = "";
        for(String s: map.keySet()){
            if(map.get(s)>greater){
                greater = map.get(s);
                result = s;
            }
        }
        System.out.println("Most common codon: "+result
        +" occurs: "+greater+"\n");
    }
}
