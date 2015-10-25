// Excel Sheet Column Number

public class Solution{
    public int titleToNumber(String s){
        if( s == null || s.length() == 0){
            return 0;
        }
        int ret = 0;
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            int val = c - 'A';
            ret = ret * 26 + val + 1;
        }

        return ret;
    }
}
