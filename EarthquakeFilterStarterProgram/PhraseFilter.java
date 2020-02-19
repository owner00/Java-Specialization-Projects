public class PhraseFilter implements Filter{
    private String where = "";
    private String phrase = "";

    public PhraseFilter(String whe, String phr){
        where = whe;
        phrase = phr;
    }

    public boolean satisfies(QuakeEntry qe){
        boolean check = false;
        if(where.equals("start")&&qe.getInfo().startsWith(phrase)){
            check = true;
        }
        else if(where.equals("end")&&qe.getInfo().endsWith(phrase)){
            check = true;
        }
        else if(where.equals("any")&&(qe.getInfo().indexOf(phrase)!= -1)){
            check = true;
        }
        return check;
    }
    
    public String getName(){
        return "PhraseFilter";
    }
}
