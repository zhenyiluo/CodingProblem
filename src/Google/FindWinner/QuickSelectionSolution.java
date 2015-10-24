import java.util.*;

public class QuickSelectionSolution {
    public static void main(String[] args) {
        List<LogEntry> logs = new ArrayList<>();
        logs.add(new LogEntry("c1", 1));
        logs.add(new LogEntry("c2", 2));
        logs.add(new LogEntry("c1", 2));
        logs.add(new LogEntry("c2", 3));
        logs.add(new LogEntry("c2", 4));
        logs.add(new LogEntry("c4", 4));
        for (String s : new QuickSelectionSolution().findFirstKWinner(2, 4, logs)) {
            System.out.println(s);
        }
    }

    public ArrayList<String> findFirstKWinner(int k, int time,
            List<LogEntry> logs) {
        ArrayList<String> ret = new ArrayList<>();
        if (logs == null || logs.size() == 0) {
            return ret;
        }
        HashMap<String, Integer> hm = new HashMap<>();
        for (LogEntry log : logs) {
            if (log.time <= time) {
                if (hm.containsKey(log.candidate)) {
                    hm.put(log.candidate, hm.get(log.candidate) + 1);
                } else {
                    hm.put(log.candidate, 1);
                }
            }
        }

        Person[] arr = new Person[hm.size()];
        int index = 0;
        for (String candidate : hm.keySet()) {
            arr[index++] = new Person(candidate, hm.get(candidate));
        }

        Person[] arr1 = getFirstKCandidates(arr, 0, arr.length-1, k);
        ArrayList<String> strList = new ArrayList<String>();
        for (Person person : arr1) {
            strList.add(person.candidate);
        }

        return strList;
    }

    public Person[] getFirstKCandidates(Person[] arr, int start, int end, int k) {
        if (start > end) {
            return new Person[0];
        }
        int r = (int) (Math.random() * (end - start + 1)) + start;
        swap(arr, r, start);
        int pivot = partition(arr, start, end);
        int len = pivot - start + 1;
        Person[] ret = new Person[k];
        if (len == k) {
            for (int i = 0; i < k; i++) {
                ret[i] = arr[i];
            }
            return ret;
        } else if (len < k) {
            return getFirstKCandidates(arr, pivot + 1, end, k);
        } else {
            return getFirstKCandidates(arr, start, pivot - 1, k);
        }
    }

    public int partition(Person[] arr, int start, int end) {
        int pivot = arr[start].votes;
        int left = start;
        int right = end;
        while (left < right) {
            while (arr[left].votes >= pivot && left < right) {
                left++;
            }
            while (arr[right].votes < pivot) {
                right--;
            }
            if (left < right) {
                swap(arr, left, right);
            }
        }
        swap(arr, start, right);
        return right;
    }

    public void swap(Person[] arr, int i, int j) {
        Person tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

}

class Person {
    String candidate;
    int votes;

    public Person(String candidate, int votes) {
        this.candidate = candidate;
        this.votes = votes;
    }
}

class LogEntry {
    String candidate;
    int time;

    public LogEntry(String candidate, int time) {
        this.candidate = candidate;
        this.time = time;
    }
}
