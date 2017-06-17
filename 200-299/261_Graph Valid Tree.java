/**
 * Time: O(V+2E)
 * Space: O(V+2E)
*/
// BFS solution
public class Solution {
    // note: no cycle, one connected component
    public boolean validTree(int n, int[][] edges) {
        // 提交好几次错误，没考虑清楚corner case。
        // n == 1 && edges.length == 0, true
        if(edges.length == 0 && n == 1) return true;
        // 第一次提交错误，没考虑有两个component。
        // eg. n = 4, [[0,1],[2,3]], 应return false
        // n个node，至少n-1个edge才能保证所有node都相连
        if(edges.length < n-1) return false; 
        List[] adj = new ArrayList[n];
        int[] numOfNeighbor = new int[n];
        // build adjacency list and count number of neighbors for each node
        for(int i = 0; i < n; i++) {
            adj[i] = new ArrayList<Integer>();
        }
        for(int i = 0; i < edges.length; i++) {
            adj[edges[i][0]].add(edges[i][1]);
            adj[edges[i][1]].add(edges[i][0]);
            numOfNeighbor[edges[i][0]]++;
            numOfNeighbor[edges[i][1]]++;
        }
        // count the number of nodes have been visited.
        // return count == n;
        // add all nodes with only one neighbor into queue
        int count = 0;
        Queue<Integer> queue = new LinkedList<>();
        for(int i = 0; i < n; i++) {
            if(numOfNeighbor[i] == 1) {
                queue.add(i);
                count++;
            }
        }
        // traverse and update numOfNeighbor
        while(!queue.isEmpty()) {
            int cur = queue.poll();
            for(int i = 0; i < adj[cur].size(); i++) {
                int neighbor = (int) adj[cur].get(i);
                numOfNeighbor[neighbor]--;
                if(numOfNeighbor[neighbor] == 1) {
                    queue.add(neighbor);
                    count++;
                }
            }
        }
        return count == n;
    }
}
// DFS solution -> check if there is a cycle in undirected graph
public class Solution {
    // note: no cycle, one connected component
    public boolean validTree(int n, int[][] edges) {
        if(edges.length == 0 && n == 1) return true;
        if(edges.length < n-1) return false; 
        List[] adj = new ArrayList[n];
        // build adjacency list and count number of neighbors for each node
        for(int i = 0; i < n; i++) {
            adj[i] = new ArrayList<Integer>();
        }
        for(int i = 0; i < edges.length; i++) {
            adj[edges[i][0]].add(edges[i][1]);
            adj[edges[i][1]].add(edges[i][0]);
        }
        boolean[] visited = new boolean[n];
        // check if there is a cycle and if all nodes are connected.
        int count = 0;
        for(int i = 0; i < n; i++) {
            if(!visited[i]) {
                if(hasCycle(adj, visited, i, -1)) return false;
                count++;
            }
        }
        return count == 1;
    }
    
    public boolean hasCycle(List[] adj, boolean[] visited, int cur, int parent) {
        visited[cur] = true;
        for(int i = 0; i < adj[cur].size(); i++) {
            int neighbor = (int) adj[cur].get(i);
            if(!visited[neighbor]) {
                if(hasCycle(adj, visited, neighbor, cur)) return true;
            } else {
                if(neighbor != parent) return true;
            }
        }
        return false;
    }
}