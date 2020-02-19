import java.util.*;
import java.io.*;
import edu.duke.*;

public class LogAnalyzer
{
     private ArrayList<LogEntry> records;
     
     public LogAnalyzer() {
         records = new ArrayList<LogEntry>();
     }
       
     public ArrayList<String> iPsWithHighestVisitsOnDay
     (HashMap<String,ArrayList<String>> map,String dat){
         //create arraylist for ipAddress
         ArrayList<String> ipsWithHighestVisits = new ArrayList<String>();
         //collect ips that occur on given day 
         ArrayList<String> ipsOnDay = new ArrayList<String>();
         for(String ipOnDate: map.keySet()){
             if(ipOnDate.equals(dat)){
                 ipsOnDay = map.get(ipOnDate);
                }
            }
         //seperate ips into map of ip & freqs
         HashMap<String,Integer> ct = new HashMap<String,Integer>();
         for(String ip: ipsOnDay){
             if(!ct.containsKey(ip)){
              ct.put(ip,1);
           }
           else{
                ct.put(ip,ct.get(ip)+1); 
           }
         }
         //retrieve value of most occuring ip
         int highestVisits = mostNumberVisitsPerIp(ct);
         //loop through map of ip & count
         //if count == the max Value
         for(String ip: ct.keySet()){
             if(ct.get(ip) == highestVisits){
                 ipsWithHighestVisits.add(ip);
             }
         }
         //add to arraylist
         //return arraylist
         return ipsWithHighestVisits;
     }
     
     public String dayWithMostIPVisits 
     (HashMap<String,ArrayList<String>> map){
         int greater = 0;
         String result = "";
         for(String loDate: map.keySet()){
             if(map.get(loDate).size()>greater){
                 greater = map.get(loDate).size();
                 result = loDate;
            }
        }
         return result;
     }
     
     public HashMap<String,ArrayList<String>> iPsForDays(){
         HashMap<String,ArrayList<String>> map = new HashMap<String,ArrayList<String>>();
         for(LogEntry log : records){
             String logDat = log.getAccessTime().toString().substring(4,10);
             ArrayList<String> aL = new ArrayList<String>();
             if(!map.containsKey(logDat)){
                 aL.add(log.getIpAddress());
                 map.put(logDat,aL);
            }
             else{
                 aL = map.get(logDat);
                 aL.add(log.getIpAddress());
                 map.put(logDat,aL);
                }
            }
         return map;
        }
        
     public ArrayList<String> iPsMostVisits(HashMap<String,Integer> map){
         ArrayList<String> mostVstList = new ArrayList<String>();
         int maxNumber = mostNumberVisitsPerIp(map);
         for(String i: map.keySet()){
             if((map.get(i)).equals(maxNumber)){
                 mostVstList.add(i);
                }
            }
         return mostVstList;
        }
     
     public int mostNumberVisitsPerIp(HashMap<String,Integer> map){
         int maxNumber = 0;
         for(Integer i : map.values()){
             if(i>maxNumber){
                 maxNumber = i;
                }
            }
         return maxNumber;
        }
     
     public HashMap<String,Integer> countVisitsPerIp(){
         HashMap<String,Integer> map = new HashMap<String,Integer>();
         for(LogEntry log: records){
             if(!map.containsKey(log.getIpAddress())){
                 map.put(log.getIpAddress(),1);
                }
                else{
                   map.put(log.getIpAddress()
                   ,map.get(log.getIpAddress())+1); 
                }
            }
         return map;
        }
     
     public int countUniqueIPsInRange(int low, int high){
         int count = 0;
         ArrayList<String> uniqueIpOnDay = new ArrayList<String>();
         for(LogEntry log: records){
             int statusCode = log.getStatusCode();
             if(!uniqueIpOnDay.contains(log.getIpAddress())
             &&(statusCode>=low&&statusCode<=high)){
                 count+=1;
                 uniqueIpOnDay.add(log.getIpAddress());
                }
            }
         return count;
        }
        
     public ArrayList<String> uniqueIPVisitsOnDay(String givenDate){
         ArrayList<String> uniqueIpOnDay = new ArrayList<String>();
         for(LogEntry log: records){
             String logDat = log.getAccessTime().toString();
             String logDate = logDat.substring(4,10);
             String logIp = log.getIpAddress();
             if(logDate.equals(givenDate)
             &&!uniqueIpOnDay.contains(logIp)){
                 uniqueIpOnDay.add(logIp);
                }
        }
        return uniqueIpOnDay;
     }
        
     public void printAllHigherThanStat(int status){
         ArrayList<String> ip = new ArrayList<String>();
         for(LogEntry log: records){
             int statusCode = log.getStatusCode();
             if(statusCode>status&&!ip.contains(log.getIpAddress())){
                 ip.add(log.getIpAddress());
                 System.out.println(log);
                }
            }
        }
        
     public int countUniqueIPs(){
         HashMap<String,Integer> uniqueIp = new HashMap<String,Integer>();
         for(LogEntry log: records){
         String ip = log.getIpAddress();
         if(!uniqueIp.containsKey(ip)){
             uniqueIp.put(ip,1);
            }
            else{
                uniqueIp.put(ip,uniqueIp.get(ip)+1);
            }
         }
         return uniqueIp.size();
        }
     
     public void readFile(String filename) {
         FileResource fr = new FileResource(filename);
         for(String line: fr.lines()){
             LogEntry le = WebLogParser.parseEntry(line);
             records.add(le);
         }
     }
        
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }
     
     
}
