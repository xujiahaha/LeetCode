/**
 * Definition for undirected graph.
 * class UndirectedGraphNode {
 *     int label;
 *     List<UndirectedGraphNode> neighbors;
 *     UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
 * };
 */
// Time: O(V+2E)
// Space: O(V)
public class Solution {
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if(node == null) return null;
        Map<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<>();
        // make a copy of node first, save the copy to newNode and return it at last.
        UndirectedGraphNode newNode = new UndirectedGraphNode(node.label);
        // map original node to copy node
        map.put(node, newNode);
        Queue<UndirectedGraphNode> queue = new LinkedList<>();
        queue.add(node);
        UndirectedGraphNode cur, neighbor, newNeighbor;
        while(!queue.isEmpty()) {
            cur = queue.poll();
            for(int i = 0; i < cur.neighbors.size(); i++) {
                neighbor = cur.neighbors.get(i);
                newNeighbor = map.get(neighbor);
                // if newNeighbor is null, means this is the first time visiting neighbor. 
                // need to add neighbor to queue, and create a copy of it.
                if(newNeighbor == null) { 
                    queue.add(neighbor);
                    newNeighbor = new UndirectedGraphNode(neighbor.label);
                    map.put(neighbor, newNeighbor);
                }
                map.get(cur).neighbors.add(newNeighbor);
            }
        }
        return newNode;
    }
}

// DFS solution
public class Solution {
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        // dfs, visit node first, then go to its neighbors.
        // if the neighbor is visited first time,1) create a copy of that neighbor, 
        // 2) add old and copy to map, 3) do dfs starting from the neighbor
        // link node with neighbor.
        if(node == null) return node;
        Map<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<UndirectedGraphNode, UndirectedGraphNode>();
        UndirectedGraphNode newNode = new UndirectedGraphNode(node.label);
        map.put(node, newNode);
        dfs(node, map);
        return newNode;
    }
    
    public void dfs(UndirectedGraphNode node, Map<UndirectedGraphNode,UndirectedGraphNode> map) {
        for(int i = 0; i < node.neighbors.size(); i++) {
            UndirectedGraphNode neighbor = node.neighbors.get(i);
            UndirectedGraphNode newNeighbor = map.get(neighbor);
            if(newNeighbor == null) { // first visit
                // 1. create copy
                newNeighbor = new UndirectedGraphNode(neighbor.label);
                // 2. put to map
                map.put(neighbor, newNeighbor);
                // 3. do dfs
                dfs(neighbor, map);
            }
            map.get(node).neighbors.add(newNeighbor);
        }
    }
}