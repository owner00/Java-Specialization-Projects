import edu.duke.*;

public class MarkovRunner {
    public void runModel(IMarkovModel markov, String text, int size){ 
        markov.setTraining(text); 
        System.out.println("running with " + markov); 
        for(int k=0; k < 3; k++){ 
            String st = markov.getRandomText(size); 
            printOut(st); 
        } 
    } 

    public void compareMethods(){
        int order = 2;
        int seed = 42;
        int size = 100;
        long start = 0;
        long end = 0;
        long diff = 0;
        FileResource fr = new FileResource();
        String str = fr.asString();
        str.trim();
        MarkovWord markov = new MarkovWord(order);
        EfficientMarkovWord effMarkov = new EfficientMarkovWord(order);
        //markovWord
        end = System.nanoTime();
        runModel(markov,str,size,seed);
        start = System.nanoTime();
        diff = end-start;
        System.out.println("Time Taken for MarkovWord => "+diff);
        //efficientMarkovWord
        end = System.nanoTime();
        runModel(effMarkov,str,size,seed);
        start = System.nanoTime();
        diff = end-start;
        System.out.println("Time Taken for EfficientMarkovWord => "+diff);
    }
    
    public void testHashMap(){
        int order = 2;
        EfficientMarkovWord effMarkov = new EfficientMarkovWord(order);
        int seed = 65;
        FileResource fr = new FileResource(); 
        String text = fr.asString(); 
        text = text.replace('\n', ' ');
        effMarkov.setRandom(seed);
        effMarkov.setTraining(text);
        int size = 50;
        String randText = effMarkov.getRandomText(size);
        effMarkov.printHashMapInfo();
    }
    
    public void runModel(IMarkovModel markov, String text, int size, int seed){ 
        markov.setTraining(text); 
        markov.setRandom(seed);
        System.out.println("running with " + markov); 
        for(int k=0; k < 3; k++){ 
            String st = markov.getRandomText(size); 
            printOut(st); 
        } 
    } 

    public void runMarkov() { 
        FileResource fr = new FileResource(); 
        String st = fr.asString(); 
        st = st.replace('\n', ' '); 
        int order = 5;
        int seed = 844;
        MarkovWord markovWord = new MarkovWord(order); 
        runModel(markovWord, st, 200, seed); 
    } 

    private void printOut(String s){
        String[] words = s.split("\\s+");
        int psize = 0;
        System.out.println("----------------------------------");
        for(int k=0; k < words.length; k++){
            System.out.print(words[k]+ " ");
            psize += words[k].length() + 1;
            if (psize > 60) {
                System.out.println(); 
                psize = 0;
            } 
        } 
        System.out.println("\n----------------------------------");
    } 

}
