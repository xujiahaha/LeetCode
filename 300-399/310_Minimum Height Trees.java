/**
 * Time: O(V+E)
 * Space: O(V+2E)
 * similar to Course Schedule problem
*/
public class Solution {
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        List<Integer> res = new ArrayList<>();
        if(edges == null || edges.length == 0 || edges[0].length == 0) {
            res.add(0);
            return res;
        }
        List[] adj = new List[n];
        int[] numOfNeighbor = new int[n];
        // initailize adjacency list
        for(int i = 0; i < n; i++) {
            adj[i] = new ArrayList<Integer>();
        }
        // fill out adj and count number of neighbors of each node
        for(int i = 0; i < edges.length; i++) {
            int u = edges[i][0];
            int v = edges[i][1];
            adj[u].add(v);
            adj[v].add(u);
            numOfNeighbor[u]++;
            numOfNeighbor[v]++;
        }
        // add all the nodes with only one neighbor into queue
        // count the number of nodes left need to be visited
        // if count == 0, meaning all nodes have been visited. 
        // Then last level (all nodes left in queue when count == 0) is the result
        int count = n; 
        Queue<Integer> queue = new LinkedList<>();
        for(int i = 0; i < n; i++) {
            if(numOfNeighbor[i] == 1) {
                queue.add(i);
                count--;
            }
        }
        // bfs. Update numOfNeighbor. only add nodes with numOfNeighbor == 1 into queue
        while(!queue.isEmpty()) {
            int size = queue.size();
            if(count == 0) { // means all the nodes left in queue is the result
                break;
            }
            for(int i = 0; i < size; i++) {
                int cur = queue.poll();
                for(int j = 0; j < adj[cur].size(); j++) {
                    int neighbor = (int) adj[cur].get(j);
                    numOfNeighbor[neighbor]--;
                    if(numOfNeighbor[neighbor] == 1) {
                        queue.add(neighbor);
                        count--;
                    }
                }
            }
        }
        while(!queue.isEmpty()) {
            res.add(queue.poll());
        }
        return res;
    }
}