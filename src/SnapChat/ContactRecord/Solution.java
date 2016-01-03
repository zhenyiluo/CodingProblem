import java.util.*;
public class Solution {
    static class Record {
        String name;
        String number;
        public Record (String name, String number) {
            this.name = name;
            this.number = number;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        List<Record> list = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            String name = sc.next();
            String number = sc.next();
            list.add(new Record(name, number));
        }
        solve(n, list);
    }

    private static void solve(int n, List<Record> list) {
        HashMap<String, List<String>> hmName = new HashMap<>();
        HashMap<String, List<String>> hmNumber = new HashMap<>();
        for(int i = 0; i < n; i++) {
            Record record = list.get(i);
            String name = record.name;
            String number = record.number;
            if(!hmName.containsKey(name)) {
                hmName.put(name, new ArrayList<>());
            }
            if(!hmNumber.containsKey(number)){
                hmNumber.put(number, new ArrayList<>());
            }
            hmName.get(name).add(number);
            hmNumber.get(number).add(name);
        }

        List<List<Record>> ret = new ArrayList<>();
        HashSet<String> visited = new HashSet<>();
        HashMap<String, Integer> nameColor = new HashMap<>();
        int color = 0;
        for(String name: hmName.keySet()) {
            if(visited.contains(name)) {
                continue;
            }
            visited.add(name);
            HashSet<String> nameSet = new HashSet<>();
            nameSet.add(name);
            Queue<String> q = new LinkedList<>();
            q.add(name);
            while(!q.isEmpty()) {
                String tmpName = q.poll();
                List<String> numbers = hmName.get(tmpName);
                for(String number : numbers) {
                    List<String> names = hmNumber.get(number);
                    for(String newName: names) {
                        if(!nameSet.contains(newName)) {
                            nameSet.add(newName);
                            q.add(newName);
                        }
                    }
                }
            }
            for(String tmpName: nameSet) {
                visited.add(tmpName);
                nameColor.put(tmpName, color);
            }
            color ++;
        }
        for(int i = 0; i < color; i ++) {
            ret.add(new ArrayList<>());
        }

        for(Record record: list) {
            int tmpColor = nameColor.get(record.name);
            ret.get(tmpColor).add(record);
        }
        int ans = 0;
        for(List<Record> records: ret) {
            System.out.println(ans);
            for(Record record: records) {
                System.out.println(record.name + " " + record.number);
            }
            ans++;
        }
    }
}