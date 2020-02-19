import edu.duke.*;

public class CaesarCipher {
    public String encryptTwoKeys(String input, int key1, int key2){
        //create stringbuilder
        StringBuilder sb = new StringBuilder(input);
        double ct = 0;
        for(int i = 0;i < sb.length();i++){
            if(ct%2==0.0){
                String k = encrypt(Character.toString(sb.charAt(i)),key1);
                sb.setCharAt(i,k.charAt(0));
                ct++;
            }
            else {
                String k = encrypt(Character.toString(sb.charAt(i)),key2);
                sb.setCharAt(i,k.charAt(0));
                ct++;
            }
        }
        return sb.toString();
    }
    
        public String uncrypt(String input){
        StringBuilder sb = new StringBuilder(input);
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int key1 = 23;
        int key2 = 17;
        String shiftedAlphabet1 = alphabet.substring(key1)+alphabet.substring(0,key1);
        String shiftedAlphabet2 = alphabet.substring(key2)+alphabet.substring(0,key2);
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

    
    void testEncryptTwoKeys(){
        String input = "First Legion";
        int key1 = 23;
        int key2 = 17;
        System.out.println(encryptTwoKeys(input,key1,key2));
    }
    
    void testOtherEncrypt(){
        String input = "First Legion";
        System.out.println(uncrypt(input));
    }
    
    public String encrypt(String input, int key) {
        //Make a StringBuilder with message (encrypted)
        StringBuilder encrypted = new StringBuilder(input);
        //Write down the alphabet
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        //Compute the shifted alphabet
        String shiftedAlphabet = alphabet.substring(key)+
        alphabet.substring(0,key);
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
                idx = alphabet.indexOf(Character.toUpperCase(currChar));
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
    public void testCaesar() {
        int key = 15;
        //FileResource fr = new FileResource();
        //String message = fr.asString();
        String message = "At noon be in the conference room with your hat on for a surprise party. YELL LOUD!";
        String encrypted = encrypt(message, key);
        System.out.println(encrypted);
        String decrypted = encrypt(encrypted, 26-key);
        System.out.println(decrypted);
    }
}

