/**
 * Time: O(m*n*log(m+n))
 * Space: O(m*n) 
*/
public class Solution {
    private final int[][] dirs = {{-1, 0}, {1, 0}, {0,-1}, {0, 1}};
    public int trapRainWater(int[][] heightMap) {
        if(heightMap == null || heightMap.length <= 2 || heightMap[0].length <= 2) return 0;
        int m = heightMap.length, n = heightMap[0].length;
        boolean[][] visited = new boolean[m][n];
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>(){
            public int compare(int[] a, int[] b) {
                return a[2]-b[2];
            }
        });
        // add all border cell into pq
        for(int i = 0; i < m; i++) { // left and right border
            visited[i][0] = true;
            visited[i][n-1] = true;
            pq.add(new int[]{i, 0, heightMap[i][0]});
            pq.add(new int[]{i, n-1, heightMap[i][n-1]});
        }
        for(int j = 0; j < n; j++) { // up and down border 
            visited[0][j] = true;
            visited[m-1][j] = true;
            pq.add(new int[]{0, j, heightMap[0][j]});
            pq.add(new int[]{m-1, j, heightMap[m-1][j]});
        }
        int water = 0;
        // poll the cell with lowest height, and visit its unvisited neighbors 
        // if the neighbor is lower, collect the water it can trap and update its height to the height after trapping water
        // add the neighbors to the queue.
        while(!pq.isEmpty()) {
            int[] cur = pq.poll();
            for(int[] dir : dirs) {
                int nx = cur[0] + dir[0];
                int ny = cur[1] + dir[1];
                if(nx >= 0 && nx < m && ny >= 0 && ny < n && visited[nx][ny] == false) {
                    visited[nx][ny] = true;
                    water += Math.max(0, cur[2] - heightMap[nx][ny]);
                    pq.add(new int[]{nx, ny, Math.max(cur[2], heightMap[nx][ny])});
                }
            }
        }
        return water;
    }
}