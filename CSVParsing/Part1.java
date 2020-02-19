
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;

public class Part1 {
    void tester(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        //String country = "Macedonia";
        //String info = countryInfo(parser,country);
        //System.out.println(info);
        //parser = fr.getCSVParser();
        //String exportItem1 = "cotton";
        //String exportItem2 = "flowers";
        //listExportersTwoProducts(parser, exportItem1, exportItem2);
        //parser = fr.getCSVParser();
        //String exportItem = "cocoa";
        //int noOfCtries = numberOfExporters(parser,exportItem);
        //System.out.println(noOfCtries);
        //parser = fr.getCSVParser();
        String amount = "$999,999,999";
        bigExporters(parser,amount);
    }
    
    void bigExporters(CSVParser parser, String amount){
        for(CSVRecord record : parser){
            if(record.get("Value (dollars)").length()
            >amount.length()){
                System.out.println(record.get("Country") 
                + " " + record.get("Value (dollars)"));
            }
        }
    }
    
    int numberOfExporters(CSVParser parser, String exportItem){
        int count = 0;
        for(CSVRecord record : parser){
            if(record.get("Exports").contains(exportItem)){
                count++;
            }
        }
        return count;
    }
    
    void listExportersTwoProducts(CSVParser parser,String exportItem1,String exportItem2){
        for(CSVRecord record : parser){
            if(record.get("Exports").contains(exportItem1)
            && record.get("Exports").contains(exportItem2)){
                System.out.println(record.get("Country"));
            }
        }
    }
    
    String countryInfo(CSVParser parser, String country){
        String result = "";
        for(CSVRecord record : parser){
            if(record.get("Country").contains(country)){
                result = country +" : " 
                + record.get("Exports") + ": " + record.get("Value (dollars)");
                return result;
            }
        }
        return "NOT FOUND!";
    }
    
    String countryIn(CSVParser parser, String country){
        String result = "";
        for(CSVRecord record : parser){
            if(record.get("Country") == country){
                result = country +" : " 
                + record.get("Exports") + ": " + record.get("Value (dollars)");
                return result;
            }
        }
        return "NOT FOUND!";
    }
}
