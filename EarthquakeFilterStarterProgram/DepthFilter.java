public class DepthFilter implements Filter{
    private double minDep;
    private double maxDep;
    
    public DepthFilter(double minDepth,double maxDepth){
        minDep = minDepth;
        maxDep = maxDepth;
    }
    
    public boolean satisfies(QuakeEntry qe){
        return qe.getDepth()>=minDep && qe.getDepth()<=maxDep;
    }
    
    public String getName(){
        return "DepthFilter";
    }
}
