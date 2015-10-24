import java.util.ArrayList;


public class Solution1 {
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
            sb.append(s.length() + ":" + s);
        }
        return sb.toString();
    }
    
    public static String[] decode(String s){
        StringBuilder sb = new StringBuilder(s);
        ArrayList<String> list = new ArrayList<String>();
        while(sb.length() > 0){
            int index = sb.indexOf(":");
            int len = Integer.valueOf(sb.substring(0, index));
            list.add(sb.substring(index+1, index+1+len));
            sb.delete(0, index+1+len);
        }
        String[] ret = new String[list.size()];
        for(int i = 0; i < ret.length; i++){
            ret[i] = list.get(i);
        }
        return ret;
    }
}
