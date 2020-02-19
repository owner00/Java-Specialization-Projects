
/**
 * Write a description of ooTestCaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class ooTestCaesarCipher {
        void simpleTests(){
        FileResource fr = new FileResource();
        String input = fr.asString();
        ooCaesarCipher cc = new ooCaesarCipher(18);
        String encrypt = cc.encrypt(input);
        System.out.println("Encrypted: "+encrypt);
        String decrypt = cc.decrypt(encrypt);
        System.out.println("Decrypted: "+decrypt);
        String decryptBreak = breakCaesarCipher(input);
        System.out.println("Encrypted: "+input);
        System.out.println("Using BreakCipher: "+decryptBreak);
    }
    
        public int maxIndex(int[] frequency){
        int max = 0;
        int k=0;
        for(int i = 0;i < frequency.length;i++){
            if(frequency[i]>max){
                max = frequency[i];
                k=i;
            }
        }
        return k;
    }
    
    public int[] countLetters(String input){
        String abc = "abcdefghijklmnopqrstuvwxyz";
        int[] arr = new int[26];
        for(int i=0;i<input.length();i++){
            char ch = Character.toLowerCase(input.charAt(i));
            int indx = abc.indexOf(ch);
            if(indx != -1){
                arr[indx] += 1;
            }
        }
        return arr;
    }
    
    public String breakCaesarCipher(String encrypted){
        int[] freqs = countLetters(encrypted);
        int maxDex = maxIndex(freqs);
        int dkey = maxDex-4;
        if(maxDex < 4){
            dkey = 26 -(4-maxDex);
        }
        ooCaesarCipher cc = new ooCaesarCipher(dkey);
        return cc.encrypt(encrypted);
    }
}
