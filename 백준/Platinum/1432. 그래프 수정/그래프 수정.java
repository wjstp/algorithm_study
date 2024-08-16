import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static HashMap<Integer, List<Integer>> adjList;
    static int[] outDegree;

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        adjList = new HashMap<>();
        outDegree = new int[N];
        for (int from = 0 ; from < N; from ++) {
            String adj = br.readLine();
            for (int to = 0; to < N; to ++) {
                if (adj.charAt(to) == '1') {
                    List<Integer> value = adjList.getOrDefault(to, new ArrayList<>());
                    value.add(from);
                    adjList.put(to, value);
                    outDegree[from] ++ ;
                }
            }
        }
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < N; i ++) {
            if (outDegree[i] == 0) {
                pq.add(-i);
            }
        }
        int[] visitOrder = new int[N]; // 방문순서가 새로운 번호임
        int cnt = N;
        while (!pq.isEmpty()) {
            int cur = -pq.poll();
            visitOrder[cur] = cnt --;
            for (var adj : adjList.getOrDefault(cur, new ArrayList<>())) {
                outDegree[adj] -- ;
                if (outDegree[adj] == 0) {
                    pq.add(-adj);
                }
            }
        }
        if (cnt != 0) { // 다 돌지 않은 경우
            System.out.println(-1);
        } else {
            for (int i = 0; i < N; i ++) {
                System.out.print(visitOrder[i] + " ");
            }
        }
    }

}
