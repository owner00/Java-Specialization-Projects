
/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part3 {
    int findStopCodon(String dna, int startIndex, String stopCodon){
        int result = -1;
        int temp = 0;
        //String announcer = "no StopCodon Index! ehya!";
        if(startIndex != -1){
            temp = dna.indexOf(stopCodon, startIndex+3);
            if(temp != -1 && (temp-startIndex) % 3 == 0){
                result = temp;
                //announcer = "Well, Hooray! There's a StopCodon index!";
                //System.out.println(announcer);
                return result;
            }
        }
        //System.out.println(announcer);
        return result;
    }
    
    String findGene (String dna){
        String result = "";
        int startIndex = dna.indexOf("ATG");
        int stopIndex = 0;
        int smallestIndex = 0;
        if(startIndex != -1){
            int taaIndex = findStopCodon(dna,startIndex,"TAA");
            int tagIndex = findStopCodon(dna,startIndex,"TAG");
            int tgaIndex = findStopCodon(dna,startIndex,"TGA");
            if(taaIndex == -1 || (tagIndex != -1 && (tagIndex < taaIndex))){
                smallestIndex = tagIndex;
            }
            else{
                smallestIndex = taaIndex;
            }
            if(smallestIndex == -1 || (tgaIndex != -1 
            && (tgaIndex < smallestIndex))){
                smallestIndex = tgaIndex;
            }
            if(smallestIndex != -1){
                stopIndex = smallestIndex;
                result = retrieveGene(dna,startIndex,stopIndex);
                return result;
            }
        }
        return result;
    }
    
    void PrintAllGenes (String dnaStr){
        String dna = dnaStr;
        String gene = "";
        //Stop loop if next gene does not exist
        while(dna != ""){
            gene = findGene(dna);
            if(!gene.isEmpty()){
                
            }
            System.out.println(gene);
            dna = nextDna(dna);
        }
    }
    
    int countGenes(String dnaStr) {
        int result = 0;
        String dna = dnaStr;
        String gene = "";
        //Stop loop if next gene does not exist
        while(dna != ""){
            gene = findGene(dna);
            if(!gene.isEmpty()){
                result++;
            }
            System.out.println(gene);
            dna = nextDna(dna);
        }
        return result;
    }
    
    void testCountGenes(){
        String dna = "";
        int gene = 0;
        //DNA without ATG
             //012345678901234567
        dna = "ATDTAADGDTGATAGADTATDTAADGDTGATAGADTATDTAADGDTGATAGADTATDTAADGDTGATAGADTATDTAADGDTGATAGADT";
        gene = countGenes(dna);
        System.out.println("dna = " + dna + " and gene = " +gene);
        //DNA without TAG
             //01234567890123
        dna = "TDATGTDGTADTGATDATGTDGTADTGATDATGTDGTADTGATDATGTDGTADTGATDATGTDGTADTGA";
        gene = countGenes(dna);
        System.out.println("dna = " + dna + " and gene = " +gene);
        //DNA without TGA
             //012345678901234
        dna = "TDATGATTDATATDGTDATGATTDATATDGTDATGATTDATATDGTDATGATTDATATDGTDATGATTDATATDG";
        gene = countGenes(dna);
        System.out.println("dna = " + dna + " and gene = " +gene);
        //DNA without TAA
             //0123ÍÍ4567890123
        dna = "GDATGGDATADTATGDATGGDATADTATGDATGGDATADTATGDATGGDATADTATGDATGGDATADTAT";
        gene = countGenes(dna);
        System.out.println("dna = " + dna + " and gene = " +gene);
        //DNA complete but not complete 3 codons
             //0123456789012
        dna = "DGTATGAATGTAAGDATGGDATADTATGDATGGDATADTATGDATGGDATADTAT";
        gene = countGenes(dna);
        System.out.println("dna = " + dna + " and gene = " +gene);
        //DNA complete with complete 3 codons
             //01234567890123456
        dna = "ADTATGATDTGDTGATAADTATGATDTGDTGATAADTATGATDTGDTGATA";
        gene = countGenes(dna);
        System.out.println("dna = " + dna + " and gene = " +gene);
    }
    
    String nextDna (String previousDna){
        String nextDna = "";
        int startIndex = previousDna.indexOf("ATG");
        int stopIndex = 0;
        int smallestIndex = 0;
        if(startIndex != -1){
            int taaIndex = findStopCodon(previousDna,startIndex,"TAA");
            int tagIndex = findStopCodon(previousDna,startIndex,"TAG");
            int tgaIndex = findStopCodon(previousDna,startIndex,"TGA");
            if(taaIndex == -1 || (tagIndex != -1 && (tagIndex < taaIndex))){
                smallestIndex = tagIndex;
            }
            else{
                smallestIndex = taaIndex;
            }
            if(smallestIndex == -1 || (tgaIndex != -1 
            && (tgaIndex < smallestIndex))){
                smallestIndex = tgaIndex;
            }
            if(smallestIndex != -1){
                stopIndex = smallestIndex;
                nextDna = previousDna.substring(stopIndex+3);
                return nextDna;
            }
        }
        return nextDna;
    }
    
    String retrieveGene (String dna, int startIndex, int stopIndex){
        String result = dna.substring(startIndex, stopIndex+3);
        return result;
    }
    
    void testPrintAllGenes(){
        String dna = "";
        PrintAllGenes(dna);
    }
    
    void testFindGene (){
        String dna = "";
        String gene = "";
        //DNA without ATG
             //012345678901234567
        dna = "ATDTAADGDTGATAGADT";
        gene = findGene(dna);
        System.out.println("dna = " + dna + " and gene = " +gene);
        //DNA without TAG
             //01234567890123
        dna = "TDATGTDGTADTGA";
        gene = findGene(dna);
        System.out.println("dna = " + dna + " and gene = " +gene);
        //DNA without TGA
             //012345678901234
        dna = "TDATGATTDATATDG";
        gene = findGene(dna);
        System.out.println("dna = " + dna + " and gene = " +gene);
        //DNA without TAA
             //01234567890123
        dna = "GDATGGDATADTAT";
        gene = findGene(dna);
        System.out.println("dna = " + dna + " and gene = " +gene);
        //DNA complete but not complete 3 codons
             //0123456789012
        dna = "DGTATGAATGTAA";
        gene = findGene(dna);
        System.out.println("dna = " + dna + " and gene = " +gene);
        //DNA complete with complete 3 codons
             //01234567890123456
        dna = "ADTATGATDTGDTGATA";
        gene = findGene(dna);
        System.out.println("dna = " + dna + " and gene = " +gene);
    }
}
