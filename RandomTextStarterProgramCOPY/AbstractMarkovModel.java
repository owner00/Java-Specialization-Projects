import java.util.*;

public abstract class AbstractMarkovModel implements IMarkovModel {
    protected String myText;
    protected Random myRandom;
    
    public AbstractMarkovModel() {
        myRandom = new Random();
    }
    
    public void setTraining(String s) {
        myText = s.trim();
    }
 
    public void setRandom(int seed){
        myRandom = new Random(seed);
    }
    
    abstract public String getRandomText(int numChars);

    protected ArrayList<String> getFollows(String key){
        ArrayList<String> res = new ArrayList<String>();
        int pos = myText.indexOf(key);
        while(pos!=-1 && pos+key.length()<myText.length()){
            res.add(Character.toString(myText.charAt(pos+key.length())));
            pos = myText.indexOf(key,pos+1);
        }
        return res;
    }
}
