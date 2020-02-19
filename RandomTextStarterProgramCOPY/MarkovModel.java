import java.util.*;

public class MarkovModel extends AbstractMarkovModel{
    private int order;

    public MarkovModel(int num) {
        myRandom = new Random();
        order = num;
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
    
    public String toString(){
        return "MarkovModel of order "+order;
    }
}

