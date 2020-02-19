import edu.duke.*;
import java.util.*;
import java.io.*;

public class WordsInFiles {
    private HashMap<String,ArrayList<String>> map;
    
    public WordsInFiles(){
        map = new HashMap<String,ArrayList<String>>();
    }
    void test(){
        String word = "tree";
        boolean found = false;
        
            FileResource fr = new FileResource();
            for(String s: fr.words()){
                if(s.equals(word)){
                    found=true;
                }
            }
        System.out.println(found);
    }
    void tester(){
        buildWordFileMap();
        int maxNo = maxNumber();
        ArrayList<String> a = wordsInNumFiles(7);
        for(int i=0;i<a.size();i++){
            String wrd = a.get(i);
            printFilesIn(wrd);
            
        }
        
    }
    
    void printFilesIn(String word){
        for(String key: map.keySet()){
            if(key.equals(word)){
                ArrayList<String> a = map.get(key);
                System.out.println(word+"appears in the following files!!\n");
                for(int i=0;i<a.size();i++){
                    System.out.println("File: "+a.get(i));
                }
            }
        }
    }
    
    ArrayList<String> wordsInNumFiles(int number){
        ArrayList<String> res = new ArrayList<String>();
        for(String key: map.keySet()){
            ArrayList<String> a = map.get(key);
            int num = a.size();
            if(num>=number){
                res.add(key);
            }
        }
        return res;
    }
    
    int maxNumber(){
        int maxNumber = 0;
        for(String key: map.keySet()){
            ArrayList<String> a = map.get(key);
            int num = a.size();
            if(num>maxNumber){
                maxNumber = num;
            }
        }
        return maxNumber;
    }
    
    void buildWordFileMap(){
        map.clear();
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()){
            addWordsFromFile(f);
        }
    }
    
    public void addWordsFromFile(File f){
        FileResource fr = new FileResource(f);
        for(String word: fr.words()){
            boolean wordInList = false;
            if(map.containsKey(word)){
                    wordInList = true;
            }
            if(!wordInList){
                //add word to hashmap alongside fileName()
                ArrayList<String> arrList=new ArrayList<String>();
                arrList.add(f.getName());
                map.put(word,arrList);
            }
            else{
                //update existing map of words 
                //adding file.getName() to map arraylist
                ArrayList<String> arrList = map.get(word);
                arrList.add(f.getName());
                map.put(word,arrList);
            }
        }
    }
}
