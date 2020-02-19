public interface IMarkovModel {
    public void setTraining(String text);
    
    public String getRandomText(int numChars);
    
    public String toString();
    
    public void setRandom(int seed);
}
