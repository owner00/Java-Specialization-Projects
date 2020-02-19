import java.util.*;

public class MarkovWord implements IMarkovModel {
    private String[] myText;
    private Random myRandom;
    private int myOrder;
    
    public MarkovWord(int order) {
        myRandom = new Random();
        myOrder = order;
    }

    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }

    public void setTraining(String text){
        myText = text.split("\\s+");
    }

    public String getRandomText(int numWords){
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length-myOrder); 
        WordGram key = new WordGram(myText,index,myOrder);
        sb.append(key);
        sb.append(" ");
        for(int k=0; k < numWords-myOrder; k++){
            ArrayList<String> follows = getFollows(key);
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

    private ArrayList<String> getFollows(WordGram kGram) {
        ArrayList<String> follows = new ArrayList<String>();
        int pos = 0;
        while(indexOf(myText,kGram,pos)!=-1&&pos<myText.length-myOrder){
            int index = indexOf(myText,kGram,pos);
            if(index==-1||index>=myText.length-myOrder){
                break;
            }
            follows.add(myText[index+myOrder]);
            pos = index+1;
        }
        return follows;
    }

    public int indexOf(String[] text, WordGram target, int position){
        //go through all elements of text array bounded by wordgram size
        for(int i=position;i<text.length-myOrder;i++){
            //compare first 4 or N words with 4 or N words in wordgram
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
    
    
}
