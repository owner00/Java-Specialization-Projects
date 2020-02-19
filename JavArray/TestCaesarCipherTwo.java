
/**
 * Write a description of TestCaesarCipherTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class TestCaesarCipherTwo {
    void tesEnc(){
        CaesarCipherTwo cc2 = new CaesarCipherTwo(23,17);
        String test = "First Legion";
        String encrypt = cc2.encrypt(test);
        System.out.println(encrypt);
    }
    
    public void simpleTests(){
        FileResource fr = new FileResource();
        String input = fr.asString();
        //String input = "Aal uttx hm aal Qtct Fhljha pl Wbdl. Pvxvxlx!";
        //CaesarCipherTwo cc2 = new CaesarCipherTwo(14,24);
        //String encrypt = cc2.encrypt(input);
        //System.out.println("Encrypted: "+encrypt);
        //String decrypt = cc2.decrypt(input);
        //System.out.println("Decrypted: "+decrypt);
        String brk = breakCaesarCipherTwo(input);
        System.out.println("Decrypt via BreakCaeserCip: "+ brk);
        
    }
    
    public String breakCaesarCipherTwo(String input){
        String firstStr = halfOfString(input,0);
        String secStr = halfOfString(input,1);
        int dex1 = getKey(firstStr);
        int dkey1 = dex1-4;
        if(dex1 < 4){
            dkey1 = 26 -(4-dex1);
        }
        int dex2 = getKey(secStr);
        int dkey2 = dex2-4;
        if(dex2 < 4){
            dkey2 = 26 -(4-dex2);
        }
        System.out.println("key1: "+dkey1+"\nkey2:"+dkey2);
        CaesarCipherTwo cc2 = new CaesarCipherTwo(dkey1,dkey2);
        String res = cc2.decrypt(input);
        return res;
    }
    
    public String halfOfString(String message, int start){
        String sb = "";
        int a = 0;
        for(int i=start;i<message.length();i+=2){
            sb = sb + message.charAt(i);
            a++;
        }
        return sb;
    }
    
    public int getKey(String s){
        int[] arr = countLetters(s);
        int key = maxIndex(arr);
        return key;
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

}
