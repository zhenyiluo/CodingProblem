public class Solution{
    public int longestConsecutive(int[] nums){
        if(nums == null || nums.length == 0){
            return 0;
        }
        HashSet<Integer> hs = new HashSet<>();
        for(int num : nums){
            hs.add(num);
        }

        int ret = 0;
        for(int num : nums){
            if(hs.contains(num)){
                int count = 1;
                int left = num-1;
                int right = num+1;
                hs.remove(num);
                while(hs.contains(left)){
                    hs.remove(left);
                    count++;
                    left --;
                }
                while(hs.contains(right)){
                    hs.remove(right);
                    count++;
                    right++;
                }
                ret = Math.max(ret, count);
            }
        }
        return ret;
    }
}
