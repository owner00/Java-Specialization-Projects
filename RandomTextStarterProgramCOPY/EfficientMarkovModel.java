import java.util.*;

public class EfficientMarkovModel extends AbstractMarkovModel{
    private int order;
    private HashMap<String,ArrayList<String>> map;

    public EfficientMarkovModel(int num) {
        myRandom = new Random();
        order = num;
        map = new HashMap<String,ArrayList<String>>();
    }

    public String getRandomText(int numChars){
        if (myText == null){
            return "";
        }
        StringBuffer sb = new StringBuffer();
        int index = myRandom.nextInt(myText.length()-order);
        String key = myText.substring(index,index+order);
        sb.append(key);
        buildMap();
        for(int k=0; k < numChars-order; k++){
            if(!map.containsKey(key) || map.get(key).size()==0){
                break;
            }
            else{
                ArrayList<String> follows = map.get(key);
                index = myRandom.nextInt(follows.size());
                String next = follows.get(index);
                sb.append(next);
                key = key.substring(1)+next;
            }
        }

        return sb.toString();
    }
    //order 2, size 8, 8-2 == 6. 
    //          01234567
    public void buildMap(){
        for(int i=0;i<myText.length()-order;i++){
            String keyText = myText.substring(i,i+order);
            String keyFollow = myText.substring(i+order,i+order+1);
            if(!map.containsKey(keyText)){
                map.put(keyText,getFollows(keyText));
            }
            map.put(myText.substring(myText.length()-order),new ArrayList<String>());
        }
    }

    protected ArrayList<String> getFollows(String key){
        ArrayList<String> res = new ArrayList<String>();
        int pos = myText.indexOf(key);
        while(pos!=-1 && pos+key.length()<myText.length()){
            res.add(Character.toString(myText.charAt(pos+key.length())));
            pos = myText.indexOf(key,pos+1);
        }
        return res;
    }

    public String toString(){
        return "MarkovModel of order "+order;
    }

    public void printHashMapInfo(){
        //Print all hashmaps only if small
        // System.out.println("Printing HashMaps");
        // for(String key : map.keySet()){
            // System.out.println("The key is "+key);
            // System.out.println("Follows are :");
            // for(String elements : map.get(key)){
                // System.out.println(elements);
            // }
        // }
        // System.out.println("------ ------- ------- ------- ------");
        // System.out.println("------ ------- ------- ------- ------");
        //Print the number of keys in hashmap
        int no = 0;
        for(String key : map.keySet()){
            no+=1;
        }
        System.out.println("Number of keys in hashmap: "+no);
        System.out.println("------ ------- ------- ------- ------");
        System.out.println("------ ------- ------- ------- ------");
        //Print the size of the largest value in the HashMap
        int largest = 0;
        for(String key : map.keySet()){
            int val = map.get(key).size();
            if(val>largest){
                largest = val;
            }
        }
        System.out.println("The largest size is "+largest);
        System.out.println("------ ------- ------- ------- ------");
        System.out.println("------ ------- ------- ------- ------");
        //Print the keys that have the maximum size value
        for(String key : map.keySet()){
            if(map.get(key).size()==largest){
                System.out.println("key "+key+" has the largest arraylist");
            }
        }
    }
}

