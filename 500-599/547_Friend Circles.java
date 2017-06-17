// O(n^2) time, O(1) space
public class Solution {
    public int findCircleNum(int[][] M) {
        // find connected components problem
        // corner case: if M is null or empty, return 0
        // firstly, at most N friend circles when N students are only friend of theirselves
        // mark [i][j] and [j][i] as visited at the same time
        // if allow to modify input matrix, set -1 as visited.
        // if not, use boolean[N]. (1-d array)
        if(M == null || M.length == 0 || M[0].length == 0) return 0;
        int n = M.length;
        int count = 0;
        for(int i = 0; i < n; i++) {
            // visited cell has been marked as -1
            if(M[i][i] == 1) {
                dfs(M, i, n);
                count++;
            }
        }
        return count;
    }
    
    public void dfs(int[][] M, int i, int n) {
        // mark source point as visited
        M[i][i] = -1;
        for(int j = 0; j < n; j++) {
            if(M[i][j] == 1) {
                M[i][j] = -1;
                M[j][i] = -1;
                dfs(M, j, n);
            }
        }
    }
}