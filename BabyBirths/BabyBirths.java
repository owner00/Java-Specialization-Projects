
/**
 * Write a description of BabyBirths here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class BabyBirths {
    int getTotalBirthsRankedHigher(int year, String name, String gender){
        int rank = getRank(year, name, gender);
        int sumBirths = 0;
        String filePath = "us_babynames_by_year/yob"+year+".csv";
        FileResource fr = new FileResource(filePath);
        int rankMale = 0;
        int rankFemale = 0;
        for(CSVRecord rec : fr.getCSVParser(false)){
            if(rec.get(1).equals("M")){
                rankMale++;
                if(gender.equals("M")&&rankMale<rank){
                    sumBirths += Integer.parseInt(rec.get(2));
                }
            }
            else{
                rankFemale++;
                if(gender.equals("F")&&rankFemale<rank){
                    sumBirths += Integer.parseInt(rec.get(2));
                }
        }
        }
    return sumBirths;
    }
    
    void testGetTotalBirthsRankedHigher(){
        int year = 1990;
        String name = "Drew";
        String gender = "M";
        int totalBirthsRankedHigher = 
        getTotalBirthsRankedHigher(year,name,gender);
        System.out.println("Total Births Ranked Higher than "
        +name+" in "+year+" is "+totalBirthsRankedHigher
        );
    }
    
    double getAverageRank(String name, String gender){
        DirectoryResource dr = new DirectoryResource();
        double sumRank = 0.0;
        double count = 0.0;
        for(File f : dr.selectedFiles()){
            double rank = getRankFromFile(f, name, gender);
            if(rank != -1){
                sumRank += rank;
                count++;
            }
        }
        if(count == 0.0){
            return -1;
        }
        return sumRank/count;
    }
    
    void testGetAverageRank(){
        String name = "Robert";
        String gender = "M";
        double avgRank = getAverageRank(name,gender);
        if(avgRank != -1){
        System.out.println("Average Rank : "
        +avgRank);
        }
        else{
            System.out.println("NO AVERAGE RANK!");
        }
    
    }
    
    String yearOfHighestRank(String name, String gender){
        DirectoryResource dr = new DirectoryResource();
        double greatestRank = 100000000;
        String year = "";
        for(File f : dr.selectedFiles()){
            double rank = getRankFromFile(f, name, gender);
            if(rank != -1 && (rank < greatestRank)){
                greatestRank = rank;
                year = getYear(f);
            }
        }
        return year;
    }
    
    void testYearOfHighestRank(){
        String name = "Mich";
        String gender = "M";
        String res = yearOfHighestRank(name, gender);
        System.out.println("Year of highest rank is "
        +res);
    }
    
    String getYear(File f){
        String fileName = f.getName();
        int index = fileName.indexOf("yob");
        String res = fileName.substring(index+3,fileName.lastIndexOf(".csv"));
        return res;
    }
    
    void whatIsNameInYear(String name, int year, int newYear, String gender){
        int rank = getRank(year, name, gender);
        if(rank != -1){
            String newName = getName(newYear, rank, gender);
            if(newName != ""){
            System.out.println(name+" born in "+year+" would be "+newName
            +" if born in "+newYear);
            }
            else{
            System.out.println("NO NAME!");
            }
        }
        else{
            System.out.println("NO RANK!");
        }
    }
    
    void testWhatIsNameInYear(){
        String name = "Owen";
        String gender = "M";
        int year = 1974;
        int newYear = 2014;
        whatIsNameInYear(name,year,newYear, gender);
        
    }
    
    String getName(int year, int rank, String gender){
        String filePath = "us_babynames_by_year/yob"+year+".csv";
        FileResource fr = new FileResource(filePath);
        int rankMale = 0;
        int rankFemale = 0;
        for(CSVRecord rec : fr.getCSVParser(false)){
            String sex = rec.get(1);
            if(sex.equals("M")){
                rankMale++;
                if(gender.equals(sex)&&(rankMale == rank)){
                return rec.get(0);
                }
            }
            else if(sex.equals("F")){
                rankFemale++;
                if(gender.equals(sex)&&(rankFemale==rank)){
                return rec.get(0);
                }
        }
        }
    return "";
    }
    
    void testGetName(){
        int year = 1982;
        int rank = 450;
        String gender = "M";
        String name = getName(year, rank, gender);
        if(name != ""){
        System.out.println("The name at rank "+rank
        +" in the year "+year+" is "+name);
        }
        else{
            System.out.println("NO NAME!");
        }
    }
    
    double getRankFromFile(File f, String name, String gender){
        FileResource fr = new FileResource(f);
        double rankMale = 0;
        double rankFemale = 0;
        for(CSVRecord rec : fr.getCSVParser(false)){
            String sex = rec.get(1);
            if(sex.equals("M")){
                rankMale++;
                if(gender.equals("M")&&name.equals(rec.get(0))){
                return rankMale;
                }
            }
            else{
                rankFemale++;
                if(gender.equals("F")&&name.equals(rec.get(0))){
                return rankFemale;
                }
        }
        }
    return -1;
    }
    
    int getRank(int year, String name, String gender){
        String filePath = "us_babynames_by_year/yob"+year+".csv";
        FileResource fr = new FileResource(filePath);
        int rankMale = 0;
        int rankFemale = 0;
        for(CSVRecord rec : fr.getCSVParser(false)){
            String sex = rec.get(1);
            if(sex.equals("M")){
                rankMale++;
                if(gender.equals("M")&&name.equals(rec.get(0))){
                return rankMale;
                }
            }
            else{
                rankFemale++;
                if(gender.equals("F")&&name.equals(rec.get(0))){
                return rankFemale;
                }
        }
        }
    return -1;
    }
    
    void testGetRank(){
        String name = "Frank";
        int year = 1971;
        String gender = "M";
        int rank = getRank(year, name, gender);
        if(rank != -1){
        System.out.println("The rank of "+name
        +" in the year "+year+" is "+rank);
        }
        else{
            System.out.println("The name does not exist!");
        }
    }
    
    void totalBirths(FileResource fr){
        int totalBirth = 0;
        int countBoys = 0;
        int countGirls = 0;
        int totalNames = 0;
        for(CSVRecord rec : fr.getCSVParser(false)){
            int numBirth = Integer.parseInt(rec.get(2));
            String name = rec.get(0);
            String gender = rec.get(1);
            totalBirth += numBirth;
            if(gender.equals("F")){
                countGirls++;
            }
            else{
                countBoys++;
            }
        }
        totalNames = countBoys + countGirls;
        System.out.println("No of Births : "+totalBirth);
        System.out.println("No of Boy Names : "+countBoys);
        System.out.println("No of Girl Names : "+countGirls);
        System.out.println("Total No of Names : "+totalNames);
    }

    void testTotalBirths(){
        FileResource fr = new FileResource();
        totalBirths(fr);
    }
}
