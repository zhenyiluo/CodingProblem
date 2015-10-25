import java.util.HashMap;
import java.util.HashSet;

public class Solution{

    public boolean checkDuplicated(HashSet<String> hs, String input){
        HashMap<String, Integer> keyMap = new HashMap<>();
        for(String s: hs){
            String key = getKey(s);
            if(keyMap.containsKey(key)){
                keyMap.put(key, keyMap.get(key) + 1);
            }else{
                keyMap.put(key, 1);
            }
        }

        if(hs.contains(input)){
            String key = getKey(input);
            if(keyMap.get(key) == 1){
                return true;
            }else{
                return false;
            }
        }
        return true;
    }

    public String getKey(String s){
        if(s == null || s.length() < 2){
            return "";
        }
        return "" + s.charAt(0) + (s.length() -2) + s.charAt(s.length() -1);
    }
}

