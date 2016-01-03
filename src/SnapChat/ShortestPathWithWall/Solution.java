import java.util.*;
public class Solution {
    static final int[] dx = {-1, 1, 0, 0};
    static final int[] dy = {0, 0, -1, 1};
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        int n = sc.nextInt();
        char[][] board = new char[m][n];
        for(int i = 0; i < m; i++) {
            board[i] = sc.next().toCharArray();
        }
        solve(m, n, board);
        sc.close();
    }

    private static void solve(int m, int n, char[][] board) {
        int startX = -1;
        int startY = -1;
        int endX = -1;
        int endY = -1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(board[i][j] == 'S') {
                    startX = i;
                    startY = j;
                }

                if (board[i][j] == 'E') {
                    endX = i;
                    endY = j;
                }
            }
        }
        Queue<Element> q = new LinkedList<>();
        q.add(new Element(startX, startY, 0));
        int[][] visited = new int[m][n];
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                visited[i][j] = Integer.MAX_VALUE;
            }
        }
        visited[startX][startY] = 0;

        while(!q.isEmpty()) {
            Element ele = q.poll();
            int x = ele.x;
            int y = ele.y;
            int level = ele.level;

            for(int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if(nx >= 0 && ny >= 0 && nx < m && ny < n) {
                    int tmpLevel = level + (board[nx][ny] != 'W' ? 1 : 2) ;
                    if( tmpLevel < visited[nx][ny] && tmpLevel < visited[endX][endY]) {
                        visited[nx][ny] = tmpLevel;
                        q.add(new Element(nx, ny, tmpLevel));
                    }
                }
            }
        }
        System.out.println(visited[endX][endY]);
    }

    static class Element {
        int x;
        int y;
        int level;
        public Element(int x, int y, int level) {
            this.x = x;
            this.y = y;
            this.level = level;
        }
    }
}