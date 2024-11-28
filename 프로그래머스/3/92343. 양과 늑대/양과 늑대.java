import java.util.*;

class Solution {
    
    static int maxSheep;
    static boolean[][][] check = new boolean[17][18][18]; // 현재 노드, 양 수, 늑대 수
    static Map<Integer, List<Integer>> adjList = new HashMap<>();
    
    public int solution(int[] info, int[][] edges) {
        int answer = 0;
        for (var edge : edges) {
            int parent = edge[0];
            int child = edge[1];
            
            adjList.putIfAbsent(parent, new ArrayList<>());
            adjList.get(parent).add(child);
            
            adjList.putIfAbsent(child, new ArrayList<>());
            adjList.get(child).add(parent);
        }
        backtracking(0, 0, 0, info);
        answer = maxSheep;
        return answer;
    }
    public static void backtracking(int curNode, int sheep, int wolves, int[] info) {
        if (info[curNode] == 0) sheep ++;
        else if (info[curNode] == 1) wolves ++;
        
        if (sheep <= wolves) return;
        maxSheep = Math.max(sheep, maxSheep);
        
        for (var adj : adjList.getOrDefault(curNode, new ArrayList<>())) {
            if (check[curNode][sheep][wolves] == false) {
                int tmp = info[curNode];
                info[curNode] = -1;
                check[curNode][sheep][wolves] = true;
                backtracking(adj, sheep, wolves, info);
                check[curNode][sheep][wolves] = false;
                info[curNode] = tmp;
            }
        }
    }
}