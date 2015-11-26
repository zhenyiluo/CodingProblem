public class Solution {
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        return lengthOfLongestSubstringDistinct(s, 2);
    }
    
    private int lengthOfLongestSubstringDistinct(String s, int k){
        if(s == null || s.length() == 0 || k <= 0){
            return 0;
        }
        int ret = 0;
        HashMap<Character, Integer> hm = new HashMap<>();
        int low = -1;
        int high = 0;
        while(high < s.length()){
            char c = s.charAt(high);
            hm.put(c, high);
            if(hm.size() > k){
                int min = Integer.MAX_VALUE;
                for(int num : hm.values()){
                    min = Math.min(min, num);
                }
                char tmp = s.charAt(min);
                hm.remove(tmp);
                low = min;
            }
            ret = Math.max(ret, high - low);
            high++;
        }
        return ret;
    }
}