
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class Part1 {
    void testHottestHourInMultipleFile(){
        DirectoryResource dr = new DirectoryResource();
        CSVRecord largestSoFar = null;
        for(File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVRecord currentRow = hottestHourInFile(fr.getCSVParser());
            largestSoFar = largestBetween(largestSoFar, currentRow);
        }
        System.out.println("hottest temp is "
        +largestSoFar.get("TemperatureF")+" at " +largestSoFar.get("DateUTC"));
    }
    
    public CSVRecord hottestHourInFile(CSVParser parser){
        //Find hottestHour in a parser
        CSVRecord largestSoFar = null;
        for(CSVRecord currentRow : parser){
            largestSoFar = largestBetween(largestSoFar, currentRow);
        }
        return largestSoFar;
    }
    
    void testHottestHourInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord result = hottestHourInFile(parser);
        System.out.println("hottest temp is "
        +result.get("TemperatureF")+" at " +result.get("TimeEST"));
    }
    
    CSVRecord largestBetween(CSVRecord largestSoFar, CSVRecord currentRow){
        if(largestSoFar == null){
            largestSoFar = currentRow;
        }
        else{
            double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
            double largestTemp = Double.parseDouble(largestSoFar.get("TemperatureF"));
            if(currentTemp>largestTemp){
                largestSoFar = currentRow;
            }
        }
        return largestSoFar;
    }
}
