
/**
 * Write a description of CaesarBreaker here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class CaesarBreaker {
    public String decryptTwoKeys(String encrypted){
        String firstStr = halfOfString(encrypted,0);
        String secStr = halfOfString(encrypted,1);
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
        System.out.println("Key1:"+dkey1+"\nKey2:"+dkey2);
        CaesarCipher cc = new CaesarCipher();
        //String decrypt = cc.encryptTwoKeys(encrypted,26-dkey1,26-dkey2);
        String decrypt = cc.encryptTwoKeys(encrypted,26-14,26-24);
        return decrypt;
    }
    
    public void testDecryptTwoKeys(){
        //FileResource fr = new FileResource();
        //String dec = fr.asString();
        String dec = "Hfs cpwewloj loks cd Hoto kyg Cyy.";
        String res = decryptTwoKeys(dec);
        System.out.println(res);
    }
    
    public int getKey(String s){
        int[] arr = countLetters(s);
        int key = maxIndex(arr);
        return key;
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
    
    void testHalfOfString(){
        String res = halfOfString("Qbkm Zgis",0);
        System.out.println("from 0:"+res);
        res = halfOfString("Qbkm Zgis",1);
        System.out.println("from 1:"+res);
    }
    
    public String decrypt(String encrypted){
        CaesarCipher cc = new CaesarCipher();
        int[] freqs = countLetters(encrypted);
        int maxDex = maxIndex(freqs);
        int dkey = maxDex-4;
        if(maxDex < 4){
            dkey = 26 -(4-maxDex);
        }
        return cc.encrypt(encrypted,26-dkey);
    }
    
    void testDecrypt(){
        String msg = "Laer. My necessaries are embark'd. Farewell."
        +"And, sister, as the winds give benefit";
        CaesarCipher cd = new CaesarCipher();
        String encrypt = cd.encrypt(msg,22);
        System.out.println("Encrypted:" +encrypt);
        String decrypt = decrypt(encrypt);
        System.out.println("Decrypted:" +decrypt);
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
