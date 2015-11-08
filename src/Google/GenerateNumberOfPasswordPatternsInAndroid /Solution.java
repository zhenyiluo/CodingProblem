import java.util.LinkedList;
import java.util.List;

/**
 * Created by eric on 2015-11-07.
 */
public class Solution {

    public static void main(String[] args){
        System.out.println(new Solution().getNumberOfPatterns(1, 10));
    }
    public static final int ROW = 4;
    public static final int COL = 7;
    public long getNumberOfPatterns(int minLen, int maxLen){
        long[][][] dp = new long[2][ROW][COL];

        // lists[i][j] contains the points that cannot connect to points[i][j] without passing through other points
        List<Integer>[][] lists = new List[ROW][COL];
        for(int i = 0; i < ROW; i++){
            for(int j = 0; j < COL; j++){
                dp[1][i][j] = 1;
                lists[i][j] = new LinkedList<>();
            }
        }
        initLists(lists);

        for(int k = 1; k<= maxLen; k++){
            long sum = 0;
            dp[0] = dp[1];
            dp[1] = new long[ROW][COL];
            for(int i = 0; i < ROW; i++){
                for(int j = 0; j < COL; j++){
                    sum += dp[0][i][j];
                }
            }

            for(int i = 0; i < ROW; i++){
                for(int j = 0; j < COL; j++){
                    dp[1][i][j] = sum;
                    if(k <= minLen){
                        dp[1][i][j] -= dp[0][i][j];
                    }

                    for(int num : lists[i][j]){
                        int x = num / COL;
                        int y = num % COL;
                        dp[1][i][j] -= dp[0][x][y];
                    }
                }
            }
        }

        long ret = 0;

        for(int i = 0; i < ROW; i++){
            for(int j = 0; j < COL; j++){
                ret += dp[1][i][j];
            }
        }
        return ret;
    }

    private void initLists(List<Integer>[][] lists) {
        for(int i = 0; i < ROW; i++){
            for(int j = 0; j < COL; j++){

                for(int x = 0; x < ROW; x++){
                    for(int y = 0; y < COL; y++){
                        int difX = Math.abs(x - i);
                        int difY = Math.abs(y - j);
                        if(difX == 0 && difY == 0){
                            continue;
                        }else if(gcd(difX, difY) > 1){
                              lists[i][j].add(x * COL + y);
                        }
                    }
                }
            }
        }
    }

    private int gcd(int a, int b){
        if(a == 0){
            return b;
        }
        return gcd(b % a, a);
    }
}

