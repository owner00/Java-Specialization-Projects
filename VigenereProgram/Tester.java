import java.util.*;
import edu.duke.*;

public class Tester {
    public void testVigenere(){
        int[] intAr = {17,14,12,4};
        VigenereCipher vc = new VigenereCipher(intAr);
        FileResource fr = new FileResource();
        String textt = fr.asString();
        String enc = vc.encrypt(textt);
        System.out.println(vc.decrypt(enc));
    }
    
    public void testVigenereBreakerTryKeyLength(){
        VigenereBreaker vb = new VigenereBreaker();
        int kLength = 38;
        FileResource fr = new FileResource();
        String enc = fr.asString();
        int[] ar = vb.tryKeyLength(enc,kLength,'e');
        //for(int i=0;i<ar.length;i++){
        //    System.out.println(ar[i]);
        //}
        VigenereCipher vc = new VigenereCipher(ar);
        String dec = vc.decrypt(enc);
        FileResource fr2 = new FileResource();
        HashSet<String> dictionary = vb.readDictionary(fr2);
        int ctWrds = vb.countWords(dec,dictionary);
        System.out.println("Valid words: "+ctWrds);
    }
    
    public void testVigenereBreakerSliceString(){
        VigenereBreaker cc = new VigenereBreaker();
        int a = 0;
        int b = 3;
        for(int i=0;i<12;i++){
            if(a==(b-1)){
                String res = cc.sliceString("abcdefghijklm",a,b);
                System.out.println("@"+a+" "+b+" "+ res);
                b++;
                a=0;
                continue;
            }
            String res = cc.sliceString("abcdefghijklm",a,b);
            System.out.println("@"+a+" "+b+" "+ res);
            a++;
        }
    }
}
