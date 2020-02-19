
/**
 * Write a description of Part1 here.
 * 
 * @author (Aja Ukpa Nnaemeka) 
 * @version (a version number or a date)
 */
public class Part1 {
    String findSimpleGene (String dna){
        String result = "";
        int startIndex = dna.indexOf("ATG");
        if(startIndex == -1){
            return "";
        }
        int endIndex = dna.indexOf("TAA", startIndex+3);
        if(endIndex == -1){
            return "";
        }
        String temp = dna.substring(startIndex, endIndex+3);
        if(temp.length() % 3 == 0){
            result = temp;
        }
        return result;
    }
    
    void findSimpleGene (){
        //DNA with no “ATG”
        String gene = "AAATGCCCTAACTAGATTAAGAAACC";
        String dna = findSimpleGene(gene);
        System.out.println("The gene is " + gene);
        System.out.println("The dna is " + dna);
        //DNA with no “TAA”
        //gene = "ADTATGTADGATATGD";
        //dna = findSimpleGene(gene);
        //System.out.println("The gene is " + gene);
        //System.out.println("The dna is " + dna);
        //DNA with no “ATG” or “TAA”
        //gene = "ATDTGDDATDTAGDTADDA";
        //dna = findSimpleGene(gene);
        //System.out.println("The gene is " + gene);
        //System.out.println("The dna is " + dna);
        //DNA with ATG, TAA and the substring between
        //them is a multiple of 3 (a gene),
        //gene = "ATATDGATGDGAATATGDTAADTG";
        //dna = findSimpleGene(gene);
        //System.out.println("The gene is " + gene);
        //System.out.println("The dna is " + dna);
        //DNA with ATG, TAA and the substring between
        //them is not a multiple of 3
        //gene = "ATGTGADGTAA";
        //dna = findSimpleGene(gene);
        //System.out.println("The gene is " + gene);
        //System.out.println("The dna is " + dna);
    }
}
