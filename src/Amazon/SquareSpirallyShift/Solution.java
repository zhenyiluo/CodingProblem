public class Solution {

    public static int[][] shift(int[][] matrix) {
        int tmp = matrix[1][0];
        int x = 0;
        int y = 0;
        int m = matrix.length;
        int n = matrix[0].length;

        while(m > 0 && n > 0) {
            for(int i = 0 ; i < n - 1; i++) {
                int t = tmp;
                tmp = matrix[x][y];
                matrix[x][y] = t;
                y++;
            }
            for(int i = 0 ; i < m - 1; i++) {
                int t = tmp;
                tmp = matrix[x][y];
                matrix[x][y] = t;
                x++;
            }
            for(int i = 0 ; i < n - 1; i++) {
                int t = tmp;
                tmp = matrix[x][y];
                matrix[x][y] = t;
                y--;
            }
            for(int i = 0 ; i < m - 1; i++) {
                int t = tmp;
                tmp = matrix[x][y];
                matrix[x][y] = t;
                x--;
            }
            x++;
            y++;
            m-=2;
            n-=2;
        }
        return matrix;
    }
}