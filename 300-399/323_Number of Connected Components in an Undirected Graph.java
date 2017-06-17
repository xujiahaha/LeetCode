/**
 * Time: O(V+E)
 * Space: O(V+2E)
 * Very similar to "Number of Islands"
*/
public class Solution {
    public int countComponents(int n, int[][] edges) {
        if(edges == null || edges.length ==0 || edges[0].length == 0) return n;
        // use adjacency list to represent this graph
        List[] adj = new List[n];
        boolean[] visited = new boolean[n];
        // initailize adj
        for(int i = 0; i < n; i++) {
            adj[i] = new ArrayList<Integer>();
        }
        // filled out the adj
        for(int i = 0; i < edges.length; i++) {
            int u = edges[i][0];
            int v = edges[i][1];
            adj[u].add(v);
            adj[v].add(u);
        }
        // visit all nodes and count connected components
        int count = 0;
        for(int i = 0; i < n; i++) {
            // find an entrance and mark all connected nodes as visited
            if(!visited[i]) { 
                // bfs(i, adj, visited);
                dfs(i, adj, visited);
                count++;
            }
        }
        return count;
    }
    
    // dfs is a little bit faster
    private void dfs(int s, List[] adj, boolean[] visited) {
        visited[s] = true;
        for(int i = 0; i < adj[s].size(); i++) {
            int neighbor = (int) adj[s].get(i);
            if(!visited[neighbor]) dfs(neighbor, adj, visited);
        }
    }
    
    private void bfs(int s, List[] adj, boolean[] visited) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        visited[s] = true;
        while(!queue.isEmpty()){
            int cur = queue.poll();
            for(int i = 0; i < adj[cur].size(); i++) {
                int neighbor = (int) adj[cur].get(i);
                if(!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.add(neighbor);
                }
            }
        }
    }
}