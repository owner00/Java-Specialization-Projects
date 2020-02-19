
/**
 * Write a description of ooCaesarCipher here.
 * 
 * @author (Aja Ukpa Nnaemeka) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class ooCaesarCipher {
    private String alphabet;
    private String shiftedAlphabet;
    private int mainkey;
    
    public ooCaesarCipher(int key){
        mainkey = key;
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet = alphabet.substring(key)+
        alphabet.substring(0,key);
    }
    
    public String decrypt(String encrypted){
        ooCaesarCipher cc = new ooCaesarCipher(26-mainkey);
        return cc.encrypt(encrypted);
    }
    
    public String encrypt(String input) {
        //Make a StringBuilder with message (encrypted)
        StringBuilder encrypted = new StringBuilder(input);
        //Count from 0 to < length of encrypted, (call it i)
        for(int i = 0; i < encrypted.length(); i++) {
            //Look at the ith character of encrypted (call it currChar)
            char currChar = encrypted.charAt(i);
            int idx = -1;
            //for lowercase ch
            if(Character.isLowerCase(currChar)){
                idx = alphabet.indexOf(Character.toUpperCase(currChar));
            }
            //Find the index of currChar in the alphabet (call it idx)
            //for uppercase chars
            else {
                idx = alphabet.indexOf(currChar);
            }
            //If currChar is in the alphabet
            if(idx != -1){
                //Get the idxth character of shiftedAlphabet (newChar)
                char newChar = shiftedAlphabet.charAt(idx);
                //Replace the ith character of encrypted with newChar
                if(Character.isLowerCase(currChar)){
                    encrypted.setCharAt(i, Character.toLowerCase(newChar));
                 }
                 else {
                     encrypted.setCharAt(i, newChar);
                    }
                
            }
            //Otherwise: do nothing
        }
        //Your answer is the String inside of encrypted
        return encrypted.toString();
    }
    
    
}
