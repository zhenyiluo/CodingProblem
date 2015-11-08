import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by eric on 2015-11-07.
 */
public class Solution {
    public static void main(String[] args){
        String[] s = {"00000",
                      "0s000",
                      "0#e00",
                      "0#000",
                      "00000"};
        System.out.println(new Solution().getCount(s, 4));
    }

    static final int[] dx = {-1, 1, 0, 0};
    static final int[] dy = {0, 0, -1, 1};
    public int getCount(String[] s, int k){
        int m = s.length;
        int n = s[0].length();
        int startX = -1;
        int startY = -1;
        int endX = -1;
        int endY = -1;
        int[][][] dp = new int[2][m][n];
        for(int i = 0; i < m; i++){
            for(int j = 0; j< n; j++){
                if(s[i].charAt(j) == 's'){
                    startX = i;
                    startY = j;
                }else if(s[i].charAt(j) == 'e'){
                    endX = i;
                    endY = j;
                }
            }
        }

        Queue<Vertex> q = new LinkedList<>();
        q.add(new Vertex(startX, startY, 0));
        dp[startX][startY][1] = 1;
        int prev = -1;
        boolean[][] visited = null;
        while(!q.isEmpty()){
            Vertex vertex = q.poll();
            int step = vertex.step;
            if(step == k){
                break;
            }
            if(step > prev){
                visited = new boolean[m][n];
                dp[0] = dp[1];
                dp[1] = new int[m][n];
                prev = step;
            }
            int x = vertex.x;
            int y = vertex.y;
            for(int i = 0; i < 4; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];
                if(nx >= 0 && ny >= 0 && nx < m && ny < n && s[nx].charAt(ny)!= '#'){
                    if(!visited[nx][ny]){
                        q.add(new Vertex(nx, ny, step + 1));
                    }
                    visited[nx][ny] = true;
                    dp[1][nx][ny] += dp[0][x][y];
                }
            }
        }

        return dp[1][endX][endY];
    }

    static class Vertex{
        int x;
        int y;
        int step;
        public Vertex(int x, int y, int step){
            this.x = x;
            this.y = y;
            this.step = step;
        }
    }
}

