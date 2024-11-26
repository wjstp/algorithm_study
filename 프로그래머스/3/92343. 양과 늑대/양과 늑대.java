import java.util.*;

class Solution {
    
    static Map<Integer, List<Integer>> adjList = new HashMap<>();
    static boolean[][][] check = new boolean[17][18][18]; // 노드, 양 개수, 늑대 개수
    static int maxSheep;
    
    public int solution(int[] info, int[][] edges) {
        int answer = 0;
        for (int i = 0; i < info.length; i ++) 
            adjList.put(i, new ArrayList<>());
        
        for (var edge : edges) {
            int s = edge[0];
            int e = edge[1];
            
            adjList.get(s).add(e);
            adjList.get(e).add(s);
        }
        backtracking(0, 0, 0, info);
        answer = maxSheep;
        
        return answer;
    }
    
    public static void backtracking(int cur, int sheep, int wolves, int[] info) {
        if (info[cur] == 0) sheep ++;
        else if (info[cur] == 1) wolves ++;
        
        if (wolves >= sheep) return;
        maxSheep = Math.max(sheep, maxSheep);
        
        for (var adj : adjList.getOrDefault(cur, new ArrayList<>())) {
            int tmp = info[cur];
            if (check[cur][sheep][wolves] == false) {
                check[cur][sheep][wolves] = true;
                info[cur] = -1;
                backtracking(adj, sheep, wolves, info);
                check[cur][sheep][wolves] = false;
                info[cur] = tmp;
            } 
        }
    }
}