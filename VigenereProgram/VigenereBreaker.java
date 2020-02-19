import java.util.*;
import java.io.*;
import edu.duke.*;

public class VigenereBreaker {
    public void breakForAllLangs(String encrypted
    , HashMap<String,HashSet<String>> languages){
        int maxCt = 0;
        String res = "";
        String lang = "";
        for(String langName: languages.keySet()){
            HashSet<String> dictForLang = languages.get(langName);
            System.out.println("Current Lang: "+langName);
            String decrypted = breakForLanguage(encrypted,dictForLang);
            int ct = countWords(decrypted,dictForLang);
            if(ct>maxCt){
                res = decrypted;
                maxCt = ct;
                lang = langName;
            }
        }
        
        System.out.println("Decryted String: \n"
        +res+" Language : "+lang);
    }
    
    public char mostCommonCharIn(HashSet<String> dictionary){
        /**
         * Find which char of the alphabet occurs
         * most and return this char
         */
        int maxCt = 0;
        char mostOccur = 'e';
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        int[] alpArr = new int[alphabet.length()];
        
        for(String word : dictionary){
            char[] splt = word.toCharArray();
            for(int i=0;i<splt.length;i++){
                char letter = splt[i];
                for(int a=0;a<alphabet.length();a++){
                    if(letter==alphabet.charAt(a)){
                        alpArr[a] +=1;
                        break;
                    }
                }
            }
        }
        
        for(int k=0;k<alpArr.length;k++){
            if(alpArr[k]>maxCt){
                maxCt = alpArr[k];
                mostOccur = alphabet.charAt(k);
            }
        }
        
        return mostOccur;
    }
    
    public String breakForLanguage(String encrypted
    , HashSet<String> dictionary){
        /**
         * Try Key Lengths btw 1-100
         * to get keyLength that gives most counts
         */
        int[] key = new int[1];
        int maxCount = 0;
        int length = 100;
        char mostCommon = mostCommonCharIn(dictionary);
        String result = "";
        for(int i=1;i<=length;i++){
            int[] keyGuess = tryKeyLength(encrypted,i,mostCommon);
            VigenereCipher vc = new VigenereCipher(keyGuess);
            String decrpt = vc.decrypt(encrypted);
            int ctWords = countWords(decrpt, dictionary);
            if(ctWords>maxCount){
                maxCount = ctWords;
                key = keyGuess;
                result = decrpt;
            }
        }
        System.out.println("To print Key");
        for(int i=0;i<key.length;i++){
            System.out.print(","+key[i]);
        }
        System.out.println("");
        System.out.println("key in string is "
        +cnvrtIntArrToStr(key));
        System.out.println("key length is "+key.length
        +"valid words "+maxCount);
        return result;
    }
    
    public String cnvrtIntArrToStr(int[] arr){
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        char[] chAlphabet = alphabet.toCharArray();
        StringBuilder sb = new StringBuilder();
        //looping through int array
        for(int i=0;i<arr.length;i++){
            //looping through ch array of alphabets
            for(int k=0;k<chAlphabet.length;k++){
                if(arr[i] == k){
                    sb.append(Character.toString(chAlphabet[k]));
                }
            }
        }
        return sb.toString();
    }
    
    public int countWords(String message
    , HashSet<String> dictionary){
        int count = 0;
        HashSet<String> hs = new HashSet<String>();
        String[] stArr = message.split("\\W+");
        for(int a=0;a<stArr.length;a++){
            if(dictionary.contains(stArr[a].toLowerCase())){
                count++;
            }
            
        }
        return count;
    }
    
    public HashSet<String> readDictionary(FileResource fr){
        HashSet<String> dictionary = new HashSet<String>();
        for(String word : fr.lines()){
            dictionary.add(word.toLowerCase());
        }
        return dictionary;
    }
    
    public String sliceString(String message, int whichSlice, int totalSlices) {
        StringBuilder sb = new StringBuilder();
        for(int k=whichSlice;k<message.length();k+=totalSlices){
            if(k>message.length()){
               break;
            }
            sb.append(message.charAt(k));
        }
        return sb.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        for(int i=0;i<klength;i++){
            String slic = sliceString(encrypted,i,klength);
            CaesarCracker cc = new CaesarCracker('e');
            int k = cc.getKey(slic);
            key[i] = k;
        }
        return key;
    }

    public void breakVigenere () {
        HashMap<String,HashSet<String>> map
        = new HashMap<String,HashSet<String>>();
        FileResource fr = new FileResource();
        String encrypted = fr.asString();
        DirectoryResource drDict = new DirectoryResource();
        for(File f : drDict.selectedFiles()){
            FileResource frDict = new FileResource(f);
            HashSet<String> dict = readDictionary(frDict);
            System.out.println("Putting "
            +f.getName()+" dictionary!!");
            map.put(f.getName(),dict);
        }
        breakForAllLangs(encrypted,map);
    }
    
}
