
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import java.io.*;
import edu.duke.*;


public class Tester
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzerPrintAll() {
        LogAnalyzer la = new LogAnalyzer();
        String fileName = "weblog3-short_log";
        la.readFile(fileName);
        
        la.printAll();
    }
    
    public void testIPsWithHighestVisitsOnDay(){
        LogAnalyzer la = new LogAnalyzer();
        String fileName = "weblog2_log";
        la.readFile(fileName);
        HashMap<String,ArrayList<String>> ipsFoDays = la.iPsForDays();
        String date = "Sep 29";
        ArrayList<String> ipsHighestVisits = 
        la.iPsWithHighestVisitsOnDay(ipsFoDays,date);
        for(String l: ipsHighestVisits){
            System.out.println(l);
        }
    }
    
    public void testDayWithMostIPVisits(){
        LogAnalyzer la = new LogAnalyzer();
        String fileName = "weblog2_log";
        la.readFile(fileName);
        HashMap<String,ArrayList<String>> ha = la.iPsForDays();
        String dayWithMost = la.dayWithMostIPVisits(ha);
        System.out.println(dayWithMost);
    }
    
    public void testCountUniqueIPs(){
        LogAnalyzer la = new LogAnalyzer();
        String fileName = "weblog2_log";
        la.readFile(fileName);
        System.out.println("\nUnique ips "+la.countUniqueIPs());
    }
    
    public void testPrintAllHigherThanStat(){
        LogAnalyzer la = new LogAnalyzer();
        String fileName = "weblog3-short_log";
        la.readFile(fileName);
        int statusCode = 400;
        la.printAllHigherThanStat(statusCode);
    }
    
    public void testUniqueIPVisitsOnDay(){
        LogAnalyzer la = new LogAnalyzer();
        String fileName = "weblog2_log";
        la.readFile(fileName);
        String date = "Sep 27";
        System.out.println("\nUnique ips on day "
        +la.uniqueIPVisitsOnDay(date).size());
    }
    
    public void testCountUniqueIPsInRange(){
        LogAnalyzer la = new LogAnalyzer();
        String fileName = "weblog2_log";
        la.readFile(fileName);
        int low = 400;
        int high = 499;
        System.out.println("count unique in range: "
        +la.countUniqueIPsInRange(low,high));
    }
    
    public void testMostNumberVisitsPerIP(){
        LogAnalyzer la = new LogAnalyzer();
        String fileName = "weblog2_log";
        la.readFile(fileName);
        HashMap<String,Integer> map = la.countVisitsPerIp();
        //for(String key: map.keySet()){
        //    System.out.println("IP "+key+" freqs "+map.get(key));
        //}
        int mostPerIp = la.mostNumberVisitsPerIp(map);
        System.out.println(mostPerIp);
    }
    
    public void testiPsMostVisits(){
        LogAnalyzer la = new LogAnalyzer();
        String fileName = "weblog2_log";
        la.readFile(fileName);
        HashMap<String,Integer> map = la.countVisitsPerIp();
        ArrayList<String> lst = la.iPsMostVisits(map);
        for(String a: lst){
            System.out.println(a);
        }
    }
    
    public void testIPForDays(){
        LogAnalyzer la = new LogAnalyzer();
        String fileName = "weblog3-short_log";
        la.readFile(fileName);
        HashMap<String,ArrayList<String>> ha = la.iPsForDays();
        for(String date: ha.keySet()){
            System.out.println(date);
            ArrayList<String> aL = ha.get(date);
            for(String ip: aL){
                System.out.println(ip);
            }
        }
    }
}
