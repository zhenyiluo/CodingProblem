import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(System.out);
        while(sc.hasNext()){
            solve(sc, pw);
        }
        sc.close();
        pw.flush();
        pw.close();

    }

    private static void solve(Scanner sc, PrintWriter pw){
        int n = sc.nextInt();
        int w = sc.nextInt();
        int[][] dp = new int[2][w+1];
        for(int i = 0; i <= 1; i++){
            for(int j = 0; j <= w; j++){
                dp[i][j] = -1;
            }
        }

        Arrays.fill(dp[0], 0);
        for(int i = 1; i <= n; i++){
            int box = sc.nextInt();
            int num = sc.nextInt();
            Arrays.fill(dp[1], -1);
            for(int k = box; k <= w; k++){
                dp[1][k] = dp[0][k-box];
            }


            for(int j = 0; j < num; j++){
                int c = sc.nextInt();
                int v = sc.nextInt();
                for(int k = w; k >= c; k--){
                    if(dp[1][k-c] != -1){
                        dp[1][k] = Math.max(dp[1][k], dp[1][k-c] + v);
                    }
                }
            }

            for(int j = 0; j <= w; j++){
                dp[1][j] = Math.max(dp[0][j], dp[1][j]);
            }
            for(int j = 0; j <= w; j++){
                dp[0][j] = dp[1][j];
            }

        }

        pw.println(dp[1][w]);
    }

}
