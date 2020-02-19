import edu.duke.*;

public class CaesarCipher {
    public String encryptTwoKeys(String input, int key1, int key2){
        //create stringbuilder
        StringBuilder sb = new StringBuilder(input);
        double ct = 0;
        for(int i = 0;i < sb.length();i++){
            if(ct == 0.0||(ct%2)==0.0){
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
    
    void testEncryptTwoKeys(){
        String input = "Can you imagine life WITHOUT the internet AND computers in your pocket?";
        int key1 = 21;
        int key2 = 8;
        System.out.println(encryptTwoKeys(input,key1,key2));
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
        String message = "Can you imagine life WITHOUT the internet AND computers in your pocket?";
        String encrypted = encrypt(message, key);
        System.out.println(encrypted);
    }
}

