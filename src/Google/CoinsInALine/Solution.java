import com.sun.javafx.binding.SelectBinding;

public class Solution {

    public static void main(String[] args){
        int[] values = {1, 20, 4};
        System.out.println(new Solution().firstWillWin(values));
    }
    /**
     * @param values: an array of integers
     * @return: a boolean which equals to true if the first player will win
     */
    public boolean firstWillWin(int[] values) {
        // write your code here
        if(values == null || values.length == 0){
            return false;
        }

        int len = values.length;
        if(len <= 2){
            return true;
        }

        int sum = 0;
        for(int i =0; i < len ;i++){
            sum += values[i];
        }

        int maxMoney = getMaxMoney(values);

        if(maxMoney * 2 > sum){
            return true;
        }else{
            return false;
        }
    }

    private int getMaxMoney(int[] values){
        int len = values.length;
        int[][] dp = new int[len][len];

        for(int i = len-1; i >= 0; i--){
            for(int j = i; j < len; j++){
                if(i == j){
                    dp[i][j] = values[i];
                }else if(i == j-1){
                    dp[i][j] = Math.max(values[i], values[j]);
                }else{
                    int first = values[i] + Math.min(dp[i+2][j], dp[i+1][j-1]);
                    int second = values[j] + Math.min(dp[i+1][j-1], dp[i][j-2]);
                    dp[i][j] = Math.max(first, second);
                }
            }
        }
        return dp[0][len-1];
    }
}
