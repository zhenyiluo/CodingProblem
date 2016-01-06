public class Solution {
    public int majorityElement(int[] nums) {
        int major = -1;
        int cnt = 0;
        for (int i = 0; i < nums.length; i++) {
            if(cnt == 0) {
                cnt = 1;
                major = nums[i];
                continue;
            }
            
            if (major == nums[i]) {
                cnt ++;
            } else {
                cnt --;
            }
        }
        return major;
    }
}