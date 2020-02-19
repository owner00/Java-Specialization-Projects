import edu.duke.*;
import java.util.*;

public class Tester {
    public void testGetFollowsWithFile(){
        MarkovOne markov = new MarkovOne();
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        markov.setTraining(st);
        ArrayList<String> follows = markov.getFollows("he");
        // for(String str : follows){
            // System.out.println(str);
        // }
        System.out.println("Array size is "+follows.size());
    }
    
    public void testGetFollows(){
        MarkovOne markov = new MarkovOne();
        //FileResource fr = new FileResource();
        //String st = fr.asString();
        String st = "this is a test yes this is a test.";
        st = st.replace('\n', ' ');
        markov.setTraining(st);
        ArrayList<String> follows = markov.getFollows("t.");
        for(String str : follows){
            System.out.println(str);
        }
        System.out.println("Array size is "+follows.size());
    }
}
