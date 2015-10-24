import java.util.*;
public class Solution {
    public static void main(String[] args){
        List<LogEntry> logs = new ArrayList<>();
        logs.add(new LogEntry("c1", 1));
        logs.add(new LogEntry("c2", 2));
        logs.add(new LogEntry("c1", 2));
        logs.add(new LogEntry("c2", 3));
        logs.add(new LogEntry("c2", 4));
        logs.add(new LogEntry("c4", 4));
        System.out.println(new Solution().findWinner(4, logs));
        for(String s : new Solution().findFirstKWinner(2, 4, logs)){
            System.out.println(s);
        }
    }
    public ArrayList<String> findFirstKWinner(int k, int time, List<LogEntry> logs){
        ArrayList<String> ret = new ArrayList<>();
        if(logs == null || logs.size() == 0){
            return ret;
        }
        HashMap<String, Integer> hm = new HashMap<>();
        for(LogEntry log : logs){
            if(log.time <= time){
                if(hm.containsKey(log.candidate)){
                    hm.put(log.candidate, hm.get(log.candidate) + 1);
                }else{
                    hm.put(log.candidate, 1);
                }
            }
        }
        
        ArrayList<Person> list = new ArrayList<>();
        for(String candidate : hm.keySet()){
            list.add(new Person(candidate, hm.get(candidate)));
        }
        
        PriorityQueue<Person> minHeap = new PriorityQueue<Person>(k+1, new Comparator<Person>(){
            @Override
            public int compare(Person p1, Person p2){
                return p1.votes - p2.votes;
            }
        });
        
        for(int i = 0; i < list.size(); i++){
            minHeap.add(list.get(i));
            if(minHeap.size() > k){
                minHeap.poll();
            }
        }
        
        ArrayList<String> strList = new ArrayList<String>();
        for(Person person: minHeap){
            strList.add(person.candidate);
        }
        return strList;
    }
    
   public String findWinner(int time, List<LogEntry> logs){
       if(logs == null || logs.size() == 0){
           return "No input";
       }
       
       HashMap<String, Integer> hm = new HashMap<>();
       for(LogEntry log : logs){
           if(log.time <= time){
               if(hm.containsKey(log.candidate)){
                   hm.put(log.candidate, hm.get(log.candidate) + 1);
               }else{
                   hm.put(log.candidate, 1);
               }
           }
       }
       String ret = "";
       int max = 0;
       for(String candidate : hm.keySet()){
           if(hm.get(candidate) > max){
               max = hm.get(candidate);
               ret = candidate;
           }
       }
       return ret;
   }
}
class Person{
    String candidate;
    int votes;
    public Person(String candidate, int votes){
        this.candidate = candidate;
        this.votes = votes;
    }
}

class LogEntry{
    String candidate;
    int time;
    public LogEntry(String candidate, int time){
        this.candidate = candidate;
        this.time = time;
    }
}