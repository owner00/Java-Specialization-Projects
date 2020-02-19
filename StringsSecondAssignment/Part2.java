
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
    int howMany(String stringa, String stringb){
        int count = 0;
        int tempPosition = 0;
        int currPosition = stringb.indexOf(stringa);
        if(currPosition != -1){
            count++;
        }
        while(currPosition != -1){
            tempPosition = stringb.indexOf(stringa
            , currPosition+stringa.length());
            if(tempPosition != -1) {
                currPosition = tempPosition;
                count++;
            }
            else{
                break;
            }
        }
        return count;
    }
    
    void testHowMany() {
        String strA = "";
        String strB = "";
        int numOccurence = 0;
        //No strA in strB
        strA = "AGC";
        strB = "ATGAACGAATTGAATC";
        numOccurence = howMany(strA,strB);
        System.out.println("Ex 1. string "+strA
        + " occurs "+numOccurence+" times in string "+strB);
        //continuos strA in strB
        strA = "good";
        strB = "goodgoodgoodgoodgood";
        numOccurence = howMany(strA,strB);
        System.out.println("Ex 2. string "+strA
        + " occurs "+numOccurence+" times in string "+strB);
        //strA in end and in beginning
        strA = "ba";
        strB = "bachuckwdba";
        numOccurence = howMany(strA,strB);
        System.out.println("Ex 3. string "+strA
        + " occurs "+numOccurence+" times in string "+strB);
        //strA seperated
        strA = "cok";
        strB = "antcokbutcokseen";
        numOccurence = howMany(strA,strB);
        System.out.println("Ex 4. string "+strA
        + " occurs "+numOccurence+" times in string "+strB);
    }
    
    int nextIndex(String stringA, String stringB) {
        int previousIndex = stringB.indexOf(stringA);
        int nextIndex = stringB.indexOf(stringA
        , previousIndex+stringA.length());
        return nextIndex;
    }
}
