public class Solution {
    private final int[][] dirs = {{-1, 0}, {1, 0}, {0,-1}, {0, 1}};
    public int longestIncreasingPath(int[][] matrix) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;
        
        int m = matrix.length, n = matrix[0].length;
        int maxLen = 0;
        // note: we need to use a cache to store the maxLen starting at (i,j) to avoid re-compute.
        // no need to use boolean[][] to mark visited cell since we can know the infomation from cache.
        // if cache[i][j] > 0, then cell (i,j) has been visited before
        int[][] cache = new int[m][n];
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                maxLen = Math.max(maxLen, dfs(matrix, cache, i, j));
            }
        }
        return maxLen;
    }
    public int dfs(int[][] matrix, int[][] cache, int x, int y) {
        // if the longest increasing path starting from (x,y) has been calculated already, directly return.
        if(cache[x][y] > 0) return cache[x][y];
        int max = 1;
        for(int[] dir : dirs) {
            int nx = x + dir[0];
            int ny = y + dir[1];
            if(nx >= 0 && nx < matrix.length && ny >= 0 && ny < matrix[0].length && matrix[nx][ny] > matrix[x][y]) {
                max = Math.max(max, dfs(matrix, cache, nx, ny)+1);
            }
        }
        // put the max to cache.
        cache[x][y] = max;
        return max;
    }
}