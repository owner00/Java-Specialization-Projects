
/**
 * Write a description of ColdCSV here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class ColdCSV {
    void testColdestHourInMultipleFile(){
        DirectoryResource dr = new DirectoryResource();
        CSVRecord smallestSoFar = null;
        for(File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVRecord currentRow = coldestHourInFile(fr.getCSVParser());
            smallestSoFar = smallestBetween(smallestSoFar, currentRow);
        }
        System.out.println("coldest temp is "
        +smallestSoFar.get("TemperatureF")+" at " +smallestSoFar.get("DateUTC"));
    }
    
    public CSVRecord coldestHourInFile(CSVParser parser){
        //Find coldestHour in a parser
        CSVRecord smallestSoFar = null;
        for(CSVRecord currentRow : parser){
            double check = Double.parseDouble(currentRow.get("TemperatureF"));
            if(check != -9999){
            smallestSoFar = smallestBetween(smallestSoFar, currentRow);
            }
        }
        return smallestSoFar;
    }
    
    void testColdestHourInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord result = coldestHourInFile(parser);
        System.out.println("coldest temp is "
        +result.get("TemperatureF")+" at " +result.get("DateUTC"));
    }
    
    CSVRecord smallestBetween(CSVRecord smallestSoFar, CSVRecord currentRow){
        if(smallestSoFar == null){
            smallestSoFar = currentRow;
        }
        else{
            double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
            double smallestTemp = Double.parseDouble(smallestSoFar.get("TemperatureF"));
            if(currentTemp<smallestTemp){
                smallestSoFar = currentRow;
            }
        }
        return smallestSoFar;
    }
    
    CSVRecord smallestHumid(CSVRecord smallestSoFar, CSVRecord currentRow){
        if(smallestSoFar == null){
            smallestSoFar = currentRow;
        }
        else{
            double currentTemp = Double.parseDouble(currentRow.get("Humidity"));
            double smallestTemp = Double.parseDouble(smallestSoFar.get("Humidity"));
            if(currentTemp<smallestTemp){
                smallestSoFar = currentRow;
            }
        }
        return smallestSoFar;
    }
    
    void fileWithColdestTemperature(){
        DirectoryResource dr = new DirectoryResource();
        CSVRecord smallestSoFar = null;
        File fle = null;
        CSVParser parser = null;
        FileResource fr = null;
        double smllstTemp = 1000.0;
        FileResource ft = null;
        for(File f : dr.selectedFiles()){
            fr = new FileResource(f);
            CSVRecord currentRow = coldestHourInFile(fr.getCSVParser());
            if(Double.parseDouble(currentRow.get("TemperatureF"))<smllstTemp){
                smllstTemp = Double.parseDouble(currentRow.get("TemperatureF"));
                smallestSoFar = currentRow;
                fle = f;
                ft = fr;
            }
        }
        System.out.println("Coldest day was in the file "
        +fle.getName());
        System.out.println("Coldest temp was "
        +smallestSoFar.get("TemperatureF"));
        parser = fr.getCSVParser();
        for(CSVRecord rd : parser){
            System.out.println(rd.get("DateUTC")
            +": "+rd.get("TemperatureF"));
        }
    }
    
    void lowestHumidityInManyFile(){
        DirectoryResource dr = new DirectoryResource();
        CSVRecord smallestSoFar = null;
        double checker = 0.0;
        for(File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVRecord currentRow = lowestHumidityInFile(fr.getCSVParser());
            smallestSoFar = smallestHumid(smallestSoFar, currentRow);
        }
        System.out.println("lowest humidity is "
        +smallestSoFar.get("Humidity")+" at "+smallestSoFar.get("DateUTC"));
    }
    
    CSVRecord lowestHumidityInFile(CSVParser parser){
        CSVRecord smallestHumidity = null;
        for(CSVRecord record : parser){
            if(!record.get("Humidity").contains("N/A")){
                if(smallestHumidity == null){
                    smallestHumidity = record;
                }
                else{
                    double smallestHumid = Double.parseDouble(smallestHumidity.get("Humidity"));
                    double recordHumid = Double.parseDouble(record.get("Humidity"));
                    if(recordHumid<smallestHumid){
                        smallestHumidity = record;
                    }
                }
            }
        }
        return smallestHumidity;
    }
    
    void testLowestHumidityInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord csv = lowestHumidityInFile(parser);
        System.out.println("Lowest Humidity was "
        +csv.get("Humidity")+" at "+csv.get("DateUTC"));
    }
    
    Double averageTemperatureInFile(CSVParser parser){
        int count = 0;
        double sum = 0.0;
        for(CSVRecord record : parser){
            sum += Double.parseDouble(record.get("TemperatureF"));
            count++;
        }
        return sum/count;
    }
    
    void testAverageTemperatureInFile(){
        FileResource fr = new FileResource();
        double avgTemp = averageTemperatureInFile(fr.getCSVParser());
        System.out.println("Average Temp is "
        +avgTemp);
    }
    
    Double averageTemperatureWithHighHumidityFile
    (CSVParser parser, int value){
        int count = 0;
        double sum = 0.0;
        for(CSVRecord record : parser){
            if(Double.parseDouble(record.get("Humidity"))>=value){
            sum += Double.parseDouble(record.get("TemperatureF"));
            count++;
            }
        }
        if(sum == 0.0 && count == 0){
            return 0.0;
        }
        return sum/count;
    }
    
    void testAverageTemperatureHighHumidityFile(){
        FileResource fr = new FileResource();
        int value = 80;
        double avgTemp = averageTemperatureWithHighHumidityFile
    (fr.getCSVParser(), value);
        if(avgTemp == 0.0){
            System.out.println("No temperatures with that humidity");
        }
        else{
            System.out.println("Average Temperature when humidity > "
            +value+" is "+avgTemp);
        }
    }
}
