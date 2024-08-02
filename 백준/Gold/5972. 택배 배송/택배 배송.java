import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;


// 양방향, 하나 이상의 길 존재 - 여물 작은쪽부터 순회할 수 있게 정렬..
// 여물이 0인 경우도 존재..!
public class Main {
    static int N, M, answer;
    static HashMap<Integer, List<NodeInfo>> paths;
    static int[] visited;

    public static class NodeInfo implements Comparable<NodeInfo>{
        int node;
        int cost;

        NodeInfo(int node, int cost) {
            this.node = node;
            this.cost = cost;
        }
        @Override
        public int compareTo(NodeInfo o) {
            return this.cost - o.cost;  // 오름차순
        }
    }

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(br.readLine());
        N = Integer.parseInt(stz.nextToken());
        M = Integer.parseInt(stz.nextToken());

        paths = new HashMap<>();

        for (int i = 0; i < M ; i ++) {
            stz = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(stz.nextToken());
            int b = Integer.parseInt(stz.nextToken());
            int c = Integer.parseInt(stz.nextToken());
            List<NodeInfo> values = paths.getOrDefault(a, new ArrayList<>());
            values.add(new NodeInfo(b, c));
            Collections.sort(values);   // 같은 지점으로 가는 다른 길이 있어도 여물이 적게 드는 곳부터 순회할 수 있도록
            paths.put(a, values);

            values = paths.getOrDefault(b, new ArrayList<>());
            values.add(new NodeInfo(a, c));
            Collections.sort(values);
            paths.put(b, values);


        }

        PriorityQueue<NodeInfo> pq = new PriorityQueue<>();
        visited = new int[N + 1];
        Arrays.fill(visited, Integer.MAX_VALUE);

        pq.add(new NodeInfo(1, 0));
        visited[1] = 0;

        while(!pq.isEmpty()) {
            NodeInfo cur = pq.poll();
            if (cur.node == N) {
                answer = cur.cost;
                break;
            }
            if (cur.cost > visited[cur.node]) continue;
            for (var adj : paths.getOrDefault(cur.node, new ArrayList<>())) {
                if (visited[adj.node] > adj.cost + cur.cost) {
                    visited[adj.node] = adj.cost + cur.cost;
                    pq.add(new NodeInfo(adj.node, visited[adj.node]));
                }
            }
        }
        System.out.println(answer);
    }
}
