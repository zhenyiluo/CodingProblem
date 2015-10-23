import java.util.ArrayList;


public class Solution {
    public static void main(String[] args){
        String[] arr = {"abc%cde\\", "a#aa,", "ha\\,ha"};
        for(String s: arr){
            System.out.println(s);
        }
        System.out.println("###########");
        String encoded = encode(arr);
        System.out.println(encoded);
        
        String[] decoded = decode(encoded);
        
        for(String s: decoded){
            System.out.println(s);
        }
        
    }
    
    public static String encode(String[] arr){
        StringBuilder sb = new StringBuilder();
        for(String s : arr){
            for(int i = 0; i < s.length(); i++){
                char c = s.charAt(i);
                if(c == '\\'){
                    sb.append("\\\\");
                }else if(c == ','){
                    sb.append("\\,");
                }else{
                    sb.append(c);
                }
            }
            sb.append(",");
        }
        return sb.toString();
    }
    
    public static String[] decode(String s){
        ArrayList<String> ret = new ArrayList<String>();
        boolean escape = false;
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if(c == '\\' && !escape){
                escape = true;
            }else if(c == ',' && !escape){
                ret.add(sb.toString());
                sb = new StringBuilder();
            }else{
                escape = false;
                sb.append(c);
            }
        }
        String[] arr = new String[ret.size()];
        for(int i = 0; i < ret.size(); i++){
            arr[i] = ret.get(i);
        }
        return arr;
    }
}
