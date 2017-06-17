public class Solution {
    public List<String> findItinerary(String[][] tickets) {
        LinkedList<String> res = new LinkedList<>();
        if(tickets == null || tickets.length == 0 || tickets[0].length == 0) return res;
        // map one from airport to all related destination airports
        Map<String, PriorityQueue<String>> map = new HashMap<>();
        map.put("JFK", new PriorityQueue<String>());
        for(int i = 0; i < tickets.length; i++) {
            String from = tickets[i][0];
            String to = tickets[i][1];
            if(!map.containsKey(from)) map.put(from, new PriorityQueue<String>());
            map.get(from).add(to);
        }
        dfs("JFK", map, res);
        return res;
    }
    
    public void dfs(String start, Map<String, PriorityQueue<String>> map, LinkedList<String> res) {
        PriorityQueue<String> neighbors = map.get(start);
        while(neighbors != null && !neighbors.isEmpty()) {
            dfs(neighbors.poll(), map, res);
        }
        res.addFirst(start);
    }
}