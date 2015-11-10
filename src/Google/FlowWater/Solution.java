import java.util.*;
public class Solution {

    public static void main(String[] args){
        char[][] map = {{'~','~','~','~','~','~','~'},
                        {'~','1','2','2','3','5','*'},
                        {'~','3','2','3','4','4','*'},
                        {'~','2','4','5','3','1','*'},
                        {'~','6','7','1','4','5','*'},
                        {'~','5','1','1','2','4','*'},
                        {'*','*','*','*','*','*','*'}};
        for(Point point : new Solution().getFlowPoints(map)){
            System.out.println("(" + point.x + "," + point.y + ") : " + map[point.x][point.y]);
        }
    }

    public static final int[] dx = {-1, 1, 0, 0};
    public static final int[] dy = {0, 0, -1, 1};
    public List<Point> getFlowPoints(char[][] map){
        List<Point> ret = new LinkedList<>();
        if(map == null || map.length == 0 || map[0].length == 0){
            return ret;
        }

        int m = map.length;
        int n = map.length;
        boolean[][] marks1 = new boolean[m][n];
        boolean[][] marks2 = new boolean[m][n];
        Queue<Integer> q1 = new LinkedList<>();
        Queue<Integer> q2 = new LinkedList<>();
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(map[i][j] == '~'){
                    q1.add(i * n + j);
                }else if(map[i][j] == '*'){
                    q2.add(i * n + j);
                }
            }
        }
        while(!q1.isEmpty()){
            int val = q1.poll();
            int x = val / n;
            int y = val % n;

            for(int k = 0; k < 4; k++){
                int nx = x + dx[k];
                int ny = y + dy[k];
                if(nx >= 0 && ny >= 0 && nx < m && ny < n && map[nx][ny] != '*' && !marks1[nx][ny]){
                    if(map[x][y] == '~' || map[x][y] <= map[nx][ny]){
                        marks1[nx][ny] = true;
                        q1.add(nx * n + ny);
                    }
                }
            }
        }

        while(!q2.isEmpty()){
            int val = q2.poll();
            int x = val / n;
            int y = val % n;

            for(int k = 0; k < 4; k++){
                int nx = x + dx[k];
                int ny = y + dy[k];
                if(nx >= 0 && ny >= 0 && nx < m && ny < n && map[nx][ny] != '~' && !marks2[nx][ny]){
                    if(map[x][y] == '*' || map[x][y] <= map[nx][ny]){
                        marks2[nx][ny] = true;
                        q2.add(nx * n + ny);
                    }
                }
            }
        }


        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(marks1[i][j] && marks2[i][j]){
                    ret.add(new Point(i, j));
                }
            }
        }

        return ret;
    }

    static class Point{
        int x;
        int y;
        public Point(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
}