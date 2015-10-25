/**
 * Definition for a point.
 * class Point {
 *     int x;
 *     int y;
 *     Point() { x = 0; y = 0; }
 *     Point(int a, int b) { x = a; y = b; }
 * }
 */
import  java.util.*;
public class Solution {
    static final int[] dx = {-1, 1, 0, 0};
    static final int[] dy = {0, 0, -1, 1};
    static int[] id;
    static int[] sz;
    static boolean[][] visited;

    public static int find(int x){
        while(x != id[x]){
            id[x] = id[id[x]];
            x = id[x];
        }
        return x;
    }

    public static void union(int x, int y){
        int i = find(x);
        int j = find(y);
        if( i == j){
            return ;
        }

        if(sz[i] < sz[j]){
            id[i] = j;
            sz[j] += sz[i];
        }else{
            id[j] = i;
            sz[i] += sz[j];
        }
    }


    /**
     * @param n an integer
     * @param m an integer
     * @param operators an array of point
     * @return an integer array
     */
    public List<Integer> numIslands2(int n, int m, Point[] operators) {
        // Write your code here
        List<Integer> ret = new LinkedList<Integer>();
        if(operators == null || operators.length == 0){
            return ret;
        }
        int cnt = 0;
        visited = new boolean[n][m];
        id = new int[n * m];
        sz = new int[n * m];
        for(int i = 0; i < n * m; i++){
            id[i] = i;
            sz[i] = 1;
        }
        for(Point operator : operators){
            int x = operator.x;
            int y = operator.y;
            if(visited[x][y]){
                ret.add(cnt);
                continue;
            }

            cnt ++;
            visited[x][y] = true;
            for(int i = 0; i < 4; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];
                int p = find(m * x + y);
                if(nx >= 0 && ny >= 0 && nx < n && ny < m && visited[nx][ny]){
                    int q = find(nx * m + ny);
                    if(p != q){
                        cnt --;
                        union(p, q);
                    }
                }
            }
            ret.add(cnt);
        }
        return ret;
    }
}




