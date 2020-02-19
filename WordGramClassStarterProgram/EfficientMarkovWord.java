import java.util.*;

public class EfficientMarkovWord implements IMarkovModel {
    private String[] myText;
    private Random myRandom;
    private HashMap<WordGram,ArrayList<String>> map;
    private int myOrder;
    
    public EfficientMarkovWord(int order) {
        myRandom = new Random();
        myOrder = order;
        map = new HashMap<WordGram,ArrayList<String>>();
    }

    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }

    public void setTraining(String text){
        myText = text.split("\\s+");
    }

    public void buildMap(){
        for(int i=0;i<myText.length-myOrder;i++){
            WordGram key = new WordGram(myText,i,myOrder);
            String keyFollow = myText[i+myOrder];
            if(!map.containsKey(key)){
                map.put(key,getFollows(key));
            }
        }
        map.put(new WordGram(myText,myText.length-myOrder,myOrder), new ArrayList<String>());
    }
    
    public String getRandomText(int numWords){
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length-myOrder); 
        WordGram key = new WordGram(myText,index,myOrder);
        sb.append(key);
        sb.append(" ");
        buildMap();
        for(int k=0; k < numWords-myOrder; k++){
            ArrayList<String> follows = getFollowsOther(key);
            if (follows.size() == 0 ) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");
            key = key.shiftAdd(next);
        }

        return sb.toString().trim();
    }

    private ArrayList<String> getFollowsOther(WordGram kGram){
        ArrayList<String> follows = new ArrayList<String>();
        for(WordGram gram : map.keySet()){
            if(gram.equals(kGram)){
                return map.get(gram);
            }
        }
        return follows;
    }
    
    private ArrayList<String> getFollows(WordGram kGram) {
        ArrayList<String> follows = new ArrayList<String>();
        //return arraylist of words that follow kGram
        int pos = 0;
        while(indexOf(myText,kGram,pos)!=-1&&pos<myText.length-myOrder){
            int index = indexOf(myText,kGram,pos);
            if(index>=myText.length-myOrder){
                break;
            }
            follows.add(myText[index+myOrder]);
            pos = index+1;
        }
        return follows;
    }

    public int indexOf(String[] text, WordGram target, int position){
        for(int i=position;i<text.length-myOrder;i++){
            for(int k=0;k<myOrder;k++){
                if(!text[i+k].equals(target.wordAt(k))){
                    break;
                }
                if(k==myOrder-1){
                    return i;
                }
            }
        }
        return -1;
    }
    
    public void printHashMapInfo(){
        //Print all hashmaps only if small
        // System.out.println("Printing HashMaps");
        // for(WordGram gram : map.keySet()){
            // System.out.println("The key => "+gram);
            // System.out.println("Follows are :");
            // for(String elements : map.get(gram)){
                // System.out.println(elements);
            // }
        // }
        // System.out.println("------ ------- ------- ------- ------");
        // System.out.println("------ ------- ------- ------- ------");
        //Print the number of keys in hashmap
        int no = 0;
        for(WordGram gram : map.keySet()){
            no+=1;
        }
        System.out.println("Number of keys in hashmap: "+no);
        System.out.println("------ ------- ------- ------- ------");
        System.out.println("------ ------- ------- ------- ------");
        //Print the size of the largest value in the HashMap
        int largest = 0;
        for(WordGram gram : map.keySet()){
            int val = map.get(gram).size();
            if(val>largest){
                largest = val;
            }
        }
        System.out.println("The largest size is "+largest);
        System.out.println("------ ------- ------- ------- ------");
        System.out.println("------ ------- ------- ------- ------");
        //Print the keys that have the maximum size value
        for(WordGram gram : map.keySet()){
            if(map.get(gram).size()==largest){
                System.out.println("key "+gram+" has the largest arraylist");
            }
        }
        
    }
}
