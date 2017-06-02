public class Solution {
    private final int[][] dirs = {{-1, 0}, {1, 0}, {0,-1}, {0, 1}};
    public void solve(char[][] board) {
        if(board == null || board.length == 0 || board[0].length == 0) return;
        int m = board.length, n = board[0].length;
        boolean[][] visited = new boolean[m][n];
        Queue<int[]> queue = new LinkedList<>();
        // add O on border into queue
        for(int i = 0; i < m-1; i++) {
            if(board[i][0] == 'O') {  // left border
                queue.add(new int[]{i, 0});
                visited[i][0] = true;
            }
            if(board[i][n-1] == 'O') { // right border
                queue.add(new int[]{i, n-1});
                visited[i][n-1] = true;
            }
        }
        for(int j = 0; j < n; j++) {
            if(board[0][j] == 'O') { // up border
                queue.add(new int[]{0, j});
                visited[0][j] = true;
            }
            if(board[m-1][j] == 'O') { // down border
                queue.add(new int[]{m-1, j});
                visited[m-1][j] = true;
            }
        }
        // visit cells that are connected to border 'O'.
        bfs(board, queue, visited, m, n, 'O');
        // search surrounded region, and set 'O' to 'X'
        for(int i = 1; i < m-1; i++) {
            for(int j = 1; j < n-1; j++) {
                // find an entrance of the region
                if(board[i][j] == 'O' && !visited[i][j]) {
                    visited[i][j] = true;
                    board[i][j] = 'X';
                    queue.add(new int[]{i, j});
                    bfs(board, queue, visited, m, n, 'X'); // set surrounded 'O' to 'X'
                }
            }
        }
    }
    private void bfs(char[][] board, Queue<int[]> queue, boolean[][] visited, int m, int n, char r) {
        while(!queue.isEmpty()) {
            int[] cur = queue.poll();
            int x = cur[0];
            int y = cur[1];
            for(int[] dir : dirs) {
                int nx = x + dir[0];
                int ny = y + dir[1];
                if(nx >= 0 && nx < m && ny >= 0 && ny < n && !visited[nx][ny] && board[nx][ny] == 'O') {
                    visited[nx][ny] = true;
                    board[nx][ny] = r;
                    queue.add(new int[]{nx, ny});
                }
            }
        }
    }
}