import java.util.*;

public class MarkovFour {
    private String myText;
    private Random myRandom;

    public MarkovFour() {
        myRandom = new Random();
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
        int index = myRandom.nextInt(myText.length()-4);
        String key = myText.substring(index,index+4);
        sb.append(key);
        for(int k=0; k < numChars-4; k++){
            ArrayList<String> follows = getFollows(key);
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            key = key.substring(1)+next;
        }

        return sb.toString();
    }
}

