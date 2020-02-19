import java.util.*;

public class MarkovWordOne implements IMarkovModel {
    private String[] myText;
    private Random myRandom;

    public MarkovWordOne() {
        myRandom = new Random();
    }

    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }

    public void setTraining(String text){
        myText = text.split("\\s+");
    }

    public void testIndexOf(){
        String str = "this is just a test yes this is a simple test";
        String[] strArr = str.split("\\s+");
        String key = "frog";
        int start = 5;

        while(indexOf(strArr,key,start)!=-1){
            int index = indexOf(strArr,key,start);
            System.out.println(key+" occurs at "+index);
            start = index+1;
        }
    }

    public String getRandomText(int numWords){
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length-1);  // random word to start with
        String key = myText[index];
        sb.append(key);
        sb.append(" ");
        for(int k=0; k < numWords-1; k++){
            ArrayList<String> follows = getFollows(key);
            // System.out.println("Key is "+key);
            // System.out.println("Elements of Arraylist are ");
            // for(String element : follows){
                // System.out.println(element);
            // }

            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");
            key = next;
        }

        return sb.toString().trim();
    }

    public void testGetFollows(){
        String myTex = "this is just a test yes this is a simple test";
        myText = myTex.split("\\s+");
        String key = "is";
        ArrayList<String> follows = getFollows(key);
        System.out.println("Key is "+key);
        System.out.println("Elements of Arraylist are ");
        for(String element : follows){
            System.out.println(element);
        }
    }

    private ArrayList<String> getFollows(String key) {
        ArrayList<String> follows = new ArrayList<String>();
        //loop through to get follows
        int pos = 0;
        while(indexOf(myText,key,pos)!=-1){
            int index = indexOf(myText,key,pos);
            if(index==-1||index>=myText.length-1){
                break;
            }
            follows.add(myText[index+1]);
            pos = index+1;
        }
        return follows;
    }

    public int indexOf(String[] arr, String key, int position){
        for(int i=position;i<arr.length;i++){
            if(arr[i].equals(key)){
                return i;
            }
        }
        return -1;
    }
}
