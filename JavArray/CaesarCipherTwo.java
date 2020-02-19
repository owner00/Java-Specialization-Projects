
/**
 * Write a description of CaesarCipherTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CaesarCipherTwo {
    private String alphabet;
    private String shiftedAlphabet1;
    private String shiftedAlphabet2;
    private int key1;
    private int key2;
    
    public CaesarCipherTwo(int koy1,int koy2){
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        key1 = koy1;
        key2 = koy2;
        shiftedAlphabet1 = alphabet.substring(key1)+
        alphabet.substring(0,key1);
        shiftedAlphabet2 = alphabet.substring(key2)+
        alphabet.substring(0,key2);
    }
    
    public String decrypt(String input){
        int keone = 26-key1;
        int ketwo = 26-key2;
        CaesarCipherTwo cc3 = new CaesarCipherTwo(keone,ketwo);
        String decry = cc3.encrypt(input);
        return decry;
    }
    
    public String encrypt(String input){
        StringBuilder sb = new StringBuilder(input);
        double ct = 0;
        for(int i = 0;i < sb.length();i++){
            //shifted alphabet 1
            if(ct%2==0){
                if(Character.isLetter(sb.charAt(i))){
                    int index = alphabet.indexOf(Character.toUpperCase(sb.charAt(i)));
                    char ch = shiftedAlphabet1.charAt(index);
                    if(Character.isLowerCase(sb.charAt(i))){
                        sb.setCharAt(i,Character.toLowerCase(ch));
                    }
                    else{
                        sb.setCharAt(i,ch);
                    }
                }
                ct++;
            }
            //shifted alphabet 2
            else{
                if(Character.isLetter(sb.charAt(i))){
                    int index = alphabet.indexOf(Character.toUpperCase(sb.charAt(i)));
                    char ch = shiftedAlphabet2.charAt(index);
                    if(Character.isLowerCase(sb.charAt(i))){
                        sb.setCharAt(i,Character.toLowerCase(ch));
                    }
                    else{
                        sb.setCharAt(i,ch);
                    }
                }
                ct++;
            }
        }
    return sb.toString();
  } 
}