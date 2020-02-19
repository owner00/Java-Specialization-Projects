
/**
 * Write a description of WordPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;

public class WordPlay {
    public String emphasize (String phrase, char ch){
        //create stringbuilder to hold mutable input
        StringBuilder sb = new StringBuilder(phrase.toLowerCase());
        //loop through input
        for(int k=0;k<sb.length();k++){
        //check if input @ index equals ch
            if(sb.charAt(k)==ch){
                //0123
                //1234
                //if odd
                if(k==0||(k%2.0)==0){
                    sb.setCharAt(k,'*');
                }
                //if even
                else{
                    sb.setCharAt(k,'+');
                }
                //using two ifs replace the StringBuilder at that pos
            }
        }
        return sb.toString();
    }
    
    public void testEmphasize(){
        String phrase = "Mary Bella Abracadabra";
        char ch = 'a';
        String out = emphasize(phrase,ch);
        System.out.println(out);
    }
    
    public String replaceWords (String phrase, char ch){
        //Call StringBuilder method to hold input
        StringBuilder sb = new StringBuilder(phrase);
        boolean checkVowel = false;
        //loop through stringbuilder length
        for(int k=0;k<sb.length();k++){
        //check if each string builder char isVowel
        checkVowel = isVowel(sb.charAt(k));
        //if it is replace with ch
        if(checkVowel){
        //update stringbuilder string
            sb.setCharAt(k,ch);
         }
        }
        return sb.toString();
    }
    
    public void testReplaceWords(){
        String phrase = "I like good things";
        char ch = '*';
        String replace = replaceWords(phrase,ch);
        System.out.println("Original string : \n"+phrase
        +"\nReplaced string : \n"+replace);
    }
    
    public boolean isVowel (char ch){
        //return true if letter == vowel or uppercase version
        //char array to hold vowels a-e
        char[] chArr = {'a', 'e', 'i', 'o', 'u'};
        //for loop to loop through array
        for(int k = 0; k < chArr.length;k++){
        //if arr[i] || arr[i].toUpperCase() == letter
            char val = Character.toUpperCase(chArr[k]);
            if((chArr[k]==ch)||(val==ch)){
                return true;
            }
        }
        return false;
    }
}
