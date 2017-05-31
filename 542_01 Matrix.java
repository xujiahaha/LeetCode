/**
 * Time: O(m*n)
 * Space: O(m*n)
*/
public class Solution {

    private final int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public int[][] updateMatrix(int[][] matrix) {
        // check corner case
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) return matrix;

        int m = matrix.length, n = matrix[0].length;
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[m][n]; // avoid visited 1 being overwrote again
        // add all position filled with 0 into queue
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(matrix[i][j] == 0) {
                    queue.add(new int[]{i,j});
                    visited[i][j] = true;
                }
            }
        }
        // bfs
        while(!queue.isEmpty()) {
            int[] cur = queue.poll();
            int x = cur[0];
            int y = cur[1];
            // visit cur's four neighbors
            for(int[] dir : dirs) {
                int nx = x+dir[0];
                int ny = y+dir[1];
                if(nx >= 0 && nx < m && ny >= 0 && ny < n && matrix[nx][ny] == 1 && !visited[nx][ny]) {
                    matrix[nx][ny] = matrix[x][y] + 1;
                    visited[nx][ny] = true;
                    queue.add(new int[]{nx, ny});
                }
            }
        }
        return matrix;
    }
}