import java.util.*;

public class MarkovModel {
    private String myText;
    private Random myRandom;
    private int order;

    public MarkovModel(int num) {
        myRandom = new Random();
        order = num;
    }

    public void setRandom(int seed){
        myRandom = new Random(seed);
    }

    public void setTraining(String s){
        myText = s.trim();
    }

    //return ArrayList<String> of 1 char strings that follow 
    //key in myText
    public ArrayList<String> getFollows(String key){
        ArrayList<String> res = new ArrayList<String>();
        int pos = myText.indexOf(key);
        while(pos!=-1 && pos+key.length()<myText.length()){
            res.add(Character.toString(myText.charAt(pos+key.length())));
            pos = myText.indexOf(key,pos+1);
        }
        return res;
    }
        
    public String getRandomText(int numChars){
        if (myText == null){
            return "";
        }
        StringBuffer sb = new StringBuffer();
        int index = myRandom.nextInt(myText.length()-order);
        String key = myText.substring(index,index+order);
        sb.append(key);
        for(int k=0; k < numChars-order; k++){
            ArrayList<String> follows = getFollows(key);
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            key = key.substring(1)+next;
        }

        return sb.toString();
    }
}

