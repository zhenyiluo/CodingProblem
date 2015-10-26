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
        int s = sc.nextInt();
        int n = sc.nextInt();
        double[] dp = new double[s+1];
        for(int i = 0; i < n; i++){
            int c = sc.nextInt();
            int v = sc.nextInt();
            double unitV = (double) v / c;
            int unitC = c/2;
            for(int cost = unitC; cost <= c; cost++){
                for(int j = s; j >= cost; j--){
                    dp[j] = Math.max(dp[j], dp[j-cost] + cost * unitV);
                }
            }
        }
        pw.println(dp[s]);
    }

}

