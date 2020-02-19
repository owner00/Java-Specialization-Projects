import edu.duke.*; 

public class MarkovRunnerWithInterface {
    public void compareMethods(){
        System.out.println("Select file for Efficient Markov Model ==>");
        FileResource fr = new FileResource();
        String test = fr.asString();
        test.trim();
        EfficientMarkovModel markovEff = new EfficientMarkovModel(2);
        System.out.println("Program starts "+System.nanoTime());
        runModel(markovEff,test,1000,42);
        System.out.println("Program ends "+System.nanoTime());
        
        System.out.println("Select file for Normal Markov Model ==>");
        fr = new FileResource();
        test = fr.asString();
        test.trim();
        MarkovModel markovMod = new MarkovModel(2);
        System.out.println("Program starts "+System.nanoTime());
        runModel(markovMod,test,1000,42);
        System.out.println("Program ends "+System.nanoTime());
    }
    
    public void testHashMap(){
        EfficientMarkovModel markov = new EfficientMarkovModel(5);
        markov.setRandom(531);
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        markov.setTraining(st);
        String randomText = markov.getRandomText(50);
        markov.printHashMapInfo();
    }
    
    public void runModel(IMarkovModel markov, String text, int size, int seed) {
        markov.setTraining(text);
        markov.setRandom(seed);
        System.out.println("running with " + markov);
        for(int k=0; k < 1; k++){
            String st= markov.getRandomText(size);
            printOut(st);
        }
    }

    public void runMarkov() {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        int size = 200;
        int seed = 615;
        // MarkovZero mz = new MarkovZero();
        // runModel(mz, st, size, seed);

        // MarkovOne mOne = new MarkovOne();
        // runModel(mOne, st, size, seed);

        MarkovModel mThree = new MarkovModel(5);
        runModel(mThree, st, size, seed);

        // MarkovFour mFour = new MarkovFour();
        // runModel(mFour, st, size, seed);

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
