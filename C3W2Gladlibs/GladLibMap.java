import edu.duke.*;
import java.util.*;

public class GladLibMap {
    private HashMap<String,ArrayList<String>> myMap;
    
    private ArrayList<String> usedWordsList;
    private int counter = 0;
    
    private Random myRandom;
    
    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "data/GladLib/data";
    
    public GladLibMap(){
        myMap = new HashMap<String,ArrayList<String>>();
        initializeFromSource(dataSourceDirectory);
        myRandom = new Random();
    }
    
    public GladLibMap(String source){
        myMap = new HashMap<String,ArrayList<String>>();
        initializeFromSource(source);
        myRandom = new Random();
    }
    
    private void initializeFromSource(String source) {
        String[] category = {"adjective","noun","color"
            ,"country","name","animal","timeframe","verb"
            ,"fruit" };
        for(int k=0;k<category.length;k++){
            ArrayList<String> IndividualList = readIt(source+"/"+category[k]+".txt");
            myMap.put(category[k],IndividualList);
        }
        
        usedWordsList = new ArrayList<String>();
    }
    
    private String randomFrom(ArrayList<String> source){
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }
    
    public void totalWordsInMap(){
        int words = 0;
        for(String label: myMap.keySet()){
            ArrayList<String> aList = myMap.get(label);
            words += aList.size();
        }
        System.out.println("Total Words in Map is "+words);
    }
    
    public void totalWordsConsidered(){
        ArrayList<String> usedLabels = new ArrayList<String>();
        int totalWords = 0;
        for(int i=0;i<usedWordsList.size();i++){
            for(String al : myMap.keySet()){
                if((myMap.get(al)).contains(usedWordsList.get(i))){
                    if(!usedLabels.contains(al)){
                        usedLabels.add(al);
                    }
                }
            }
        }
        
        for(int i=0;i<usedLabels.size();i++){
            for(String key: myMap.keySet()){
                if(key.equals(usedLabels.get(i))){
                    totalWords+=(myMap.get(key)).size();
                }
            }
        }
        
        System.out.println("Labels that occur are : ");
        for(int i=0;i<usedLabels.size();i++){
            System.out.println("\t"+usedLabels.get(i));
        }
        
        System.out.println("Total words Considered: "+totalWords);
    }
    
    private String getSubstitute(String label) {
        for(String key: myMap.keySet()){
            if(key.equals(label)){
                return randomFrom(myMap.get(key));
            }
        }
        
        if (label.equals("number")){
            return ""+myRandom.nextInt(50)+5;
        }
        return "**UNKNOWN**";
    }
    
    private String processWord(String w){
        int first = w.indexOf("<");
        int last = w.indexOf(">",first);
        if (first == -1 || last == -1){
            return w;
        }
        String prefix = w.substring(0,first);
        String suffix = w.substring(last+1);
        String label = w.substring(first+1,last);
        String sub = "";
        if(label.equals("number")){
            return getSubstitute(label);
        }
        
        ArrayList<String> n = readIt(dataSourceDirectory+"/"+label+".txt");
        String s= getSubstitute(label);
        boolean sIsInArrList = usedWordsList.contains(s);
        if(!sIsInArrList){
            sub=s;
            usedWordsList.add(s);
        }
        else{
            boolean allWordInList = false;
            while(!allWordInList){
                s=getSubstitute(label);
                if(!usedWordsList.contains(s)){
                    counter++;
                    sub = s;
                    usedWordsList.add(s);
                    break;
                }
                
                for(int i=0;i<n.size();i++){
                    if(usedWordsList.contains(n.get(i))){
                        allWordInList=true;
                    }
                }
            }
        }
        return prefix+sub+suffix;
    }
    
    private void printOut(String s, int lineWidth){
        int charsWritten = 0;
        for(String w : s.split("\\s+")){
            if (charsWritten + w.length() > lineWidth){
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w+" ");
            charsWritten += w.length() + 1;
        }
    }
    
    private String fromTemplate(String source){
        String story = "";
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        return story;
    }
    
    private ArrayList<String> readIt(String source){
        ArrayList<String> list = new ArrayList<String>();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        return list;
    }
    
    public void makeStory(){
        //usedWordsList.clear();
        System.out.println("\n");
        System.out.println("Words replaced= "+counter+" times");
        String story = fromTemplate("data/GladLib/data/madtemplate2.txt");
        printOut(story, 60);
        for(int i=0;i<usedWordsList.size();i++){
            System.out.println("\n"+usedWordsList.get(i));
        }
        
        totalWordsConsidered();
    }
    


}
