public class Solution {
    private final int[][] dirs = {{-1, 0}, {1, 0}, {0,-1}, {0, 1}};
    public boolean exist(char[][] board, String word) {
        // corner case
        if(word == null || word.length() == 0) return true;
        if(board == null || board.length == 0 || board[0].length == 0) return false;
        
        // general case: scan the whole board, once we find one entrance, do dfs. 
        int m = board.length, n = board[0].length;
        char[] words = word.toCharArray();
        char start = words[0];
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                // if we find an entrance
                if(board[i][j] == start && dfs(board, words, new boolean[m][n], 1, i, j)) return true; 
            }
        }
        return false;
    }
    
    public boolean dfs(char[][] board, char[] words, boolean[][] visited, int idx, int x, int y) {
        // already found the whole word, return true
        if(idx == words.length) return true;
        int m = board.length, n = board[0].length;
        visited[x][y] = true;
        for(int[] dir : dirs) {
            int nx = x + dir[0];
            int ny = y + dir[1];
            if(nx >= 0 && nx < m && ny >= 0 && ny < n && !visited[nx][ny] && board[nx][ny] == words[idx]) {
                if(dfs(board, words, visited, idx+1, nx, ny)) return true;
            }
        }
        // (x, y) could be visited agian through another path
        visited[x][y] = false;
        return false;
    }
}
// another implementation, much faster than the above one
public class Solution {
    public boolean exist(char[][] board, String word) {
        if (word == null || word.length() == 0) {
            return true;
        }
        char[] chs = word.toCharArray();
        int n = board.length, m = board[0].length;
        boolean[][] visited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(dfs(board, visited, chs, 0, i, j)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    private boolean dfs(char[][] board, boolean[][] visited, char[] words, int idx, int x, int y) {
        if (idx == words.length) {
            return true;
        } 
        if (x < 0 || x == board.length || y < 0 || y == board[0].length || visited[x][y] || board[x][y] != words[idx]) {
            return false;
        }
        visited[x][y] = true;
        boolean exist = dfs(board, visited, words, idx + 1, x, y + 1) ||
        dfs(board, visited, words, idx + 1, x, y - 1) || dfs(board, visited, words, idx + 1, x + 1, y) ||
        dfs(board, visited, words, idx + 1, x - 1, y) ;
        visited[x][y] = false;
        return exist;
    }
}
