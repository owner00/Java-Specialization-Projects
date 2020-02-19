
/**
 * Write a description of Part2 here.
 * 
 * @author (Aja Ukpa Nnaemeka) 
 * @version (a version number or a date)
 */
public class Part2 {
    String findSimpleGene (String gene, String startCodon, String endCodon){
        String result = "";
        int startIndex = gene.indexOf(startCodon);
        if(startIndex == -1){
            return "";
        }
        int endIndex = gene.indexOf(endCodon, startIndex+3);
        if(endIndex == -1){
            return "";
        }
        String dna = gene.substring(startIndex, endIndex+3);
        if(dna.length() % 3 == 0){
            result = dna;
            return result;
        }
        return result;
    }
    
    void findSimpleGene (){
        String startCodon = "";
        String endCodon = "";
        String gene = "";
        String dna = "";
        //DNA with no “ATG”
        //gene = "ATAGTAATAD";
        //dna = findSimpleGene(gene, startCodon, endCodon);
        //System.out.println("The gene is " + gene);
        //System.out.println("The dna is " + dna);
        //DNA with no “TAA”
        //gene = "ADTATGTADGATATGD";
        //dna = findSimpleGene(gene, startCdn, endCdn);
        //System.out.println("The gene is " + gene);
        //System.out.println("The dna is " + dna);
        //DNA with no “ATG” or “TAA”
        //gene = "ATDTGDDATDTAGDTADDA";
        //dna = findSimpleGene(gene, startCdn, endCdn);
        //System.out.println("The gene is " + gene);
        //System.out.println("The dna is " + dna);
        //DNA with ATG, TAA and the substring between
        //them is a multiple of 3 (a gene),
        gene = "AAATGCCCTAACTAGATTAAGAAACC";
        startCodon = getStartCodon(gene);
        endCodon = getEndCodon(gene);
        System.out.println(startCodon + "" + endCodon);
        dna = findSimpleGene(gene, startCodon, endCodon);
        System.out.println("The gene is " + gene);
        System.out.println("The dna is " + dna);
        //DNA with ATG, TAA and the substring between
        //them is not a multiple of 3
        //gene = "ATGTGADGTAA";
        //dna = findSimpleGene(gene, startCdn, endCdn);
        //System.out.println("The gene is " + gene);
        //System.out.println("The dna is " + dna);
    }
    
    String getStartCodon(String gene){
        String result = "";
        boolean lowerCase = gene.equals(gene.toLowerCase());
        boolean upperCase = gene.equals(gene.toUpperCase());
        if(lowerCase){
            result = "atg";
        }
        else if(upperCase){
            result = "ATG";
        }
        return result;
    }
    
    String getEndCodon(String gene){
        String result = "";
        boolean lowerCase = gene.equals(gene.toLowerCase());
        boolean upperCase = gene.equals(gene.toUpperCase());
        if(lowerCase){
            result = "taa";
        }
        else if(upperCase){
            result = "TAA";
        }
        return result;
    }
}
