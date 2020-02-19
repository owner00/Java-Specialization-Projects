
/**
 * Write a description of Part4 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class Part4 {
        public void readContent(){
            URLResource url = new URLResource("https://www.dukelearntoprogram.com/course2/data/manylinks.html");
            int ytbe = 0;
            StorageResource sr = new StorageResource();
            String s = url.asString();
            int position = 0;
            while(true){
                s = s.toLowerCase();
                ytbe = s.indexOf("youtube.com", position);
                if(ytbe != -1){
                    int start = s.indexOf(">",ytbe);
                    int end = s.indexOf("<",start);
                    sr.add(s.substring(start+1,end));
                    position = ytbe+1;
                }
                else{
                    break;
                }
            }
            for(String ab : sr.data()){
                System.out.println(ab);
            }
}
}
