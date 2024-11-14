import java.util.*;

class Solution {
    public int[] solution(int[][] edges) {
        int[] answer = new int[4];
        Map<Integer, Integer> out = new HashMap<>();
        Map<Integer, Integer> in = new HashMap<>();
        
        for (int[] edge : edges) {
            out.put(edge[0], out.getOrDefault(edge[0], 0) + 1);
            in.put(edge[1], in.getOrDefault(edge[1], 0) + 1);
        }
        
        for (var node : out.keySet()) {
            if (out.get(node) >= 2) {
                if (!in.containsKey(node)) {
                    answer[0] = node;
                } else {
                    answer[3] ++; 
                }
            }
        }
        
        for (var node : in.keySet()) {
            if (! out.containsKey(node)) {
                answer[2] ++;
            }
        }
        answer[1] = out.get(answer[0]) - answer[3] - answer[2];
        
        return answer;
    }
}