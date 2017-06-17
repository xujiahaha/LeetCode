/**
 * Time complexity: O(m*n) 
 * Space complexity: O(m*n) 
*/
// BFS solution. 26ms
public class Solution {
    private final int[][] dirs = {{-1, 0}, {1, 0}, {0,-1}, {0, 1}};
    public List<int[]> pacificAtlantic(int[][] matrix) {
        List<int[]> res = new ArrayList<>();
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) return res;
        int m = matrix.length, n = matrix[0].length;
        boolean[][] pacific = new boolean[m][n];
        boolean[][] atlantic = new boolean[m][n];
        Queue<int[]> pacificQ = new LinkedList<>();
        Queue<int[]> atlanticQ = new LinkedList<>();
        // add left border to pacificQ, right border to atlanticQ
        for(int i = 0; i < m; i++) {
            pacificQ.add(new int[]{i, 0});
            atlanticQ.add(new int[]{i, n-1});
            pacific[i][0] = true;
            atlantic[i][n-1] = true;
        }
        // add up border to pacificQ, down border to atlanticQ
        for(int j = 0; j < n; j++) {
            pacificQ.add(new int[]{0, j});
            atlanticQ.add(new int[]{m-1, j});
            pacific[0][j] = true;
            atlantic[m-1][j] = true;
        }
        bfs(matrix, pacific, pacificQ, m, n);
        bfs(matrix, atlantic, atlanticQ, m, n);
        
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(pacific[i][j] && atlantic[i][j]) res.add(new int[]{i, j});
            }
        }
        return res;
    }
    private void bfs(int[][] matrix, boolean[][] visited, Queue<int[]> queue, int m, int n) {
        while(!queue.isEmpty()) {
            int[] cur = queue.poll();
            int x = cur[0];
            int y = cur[1];
            for(int[] dir : dirs) {
                int nx = x + dir[0];
                int ny = y + dir[1];
                if(nx >= 0 && nx < m && ny >= 0 && ny < n && !visited[nx][ny] && matrix[nx][ny] >= matrix[x][y]) {
                    visited[nx][ny] = true;
                    queue.add(new int[]{nx, ny});
                }
            }
        }
    }
}
// DFS solution. 21ms
public class Solution {
    private final int[][] dirs = {{-1, 0}, {1, 0}, {0,-1}, {0, 1}};
    public List<int[]> pacificAtlantic(int[][] matrix) {
        List<int[]> res = new ArrayList<>();
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) return res;
        int m = matrix.length, n = matrix[0].length;
        boolean[][] pacific = new boolean[m][n];
        boolean[][] atlantic = new boolean[m][n];
        // left and right border
        for(int i = 0; i < m; i++) {
            if(!pacific[i][0]) {
                dfs(matrix, pacific, i, 0, m, n);
            }
            if(!atlantic[i][n-1]) {
                dfs(matrix, atlantic, i, n-1, m, n);
            }
        }
        // up and bottom border
        for(int j = 0; j < n; j++) {
            if(!pacific[0][j]) {
                dfs(matrix, pacific, 0, j, m, n);
            }
            if(!atlantic[m-1][j]) {
                dfs(matrix, atlantic, m-1, j, m, n);
            }
        }
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(pacific[i][j] && atlantic[i][j]) res.add(new int[]{i, j});
            }
        }
        return res;
    }
    private void dfs(int[][] matrix, boolean[][] visited, int x, int y, int m, int n) {
        visited[x][y] = true;
        for(int[] dir : dirs) {
            int nx = x + dir[0];
            int ny = y + dir[1];
            if(nx >= 0 && nx < m && ny >= 0 && ny < n && !visited[nx][ny] && matrix[nx][ny] >= matrix[x][y]) {
                dfs(matrix, visited, nx, ny, m ,n);
            }
        }
    }
}