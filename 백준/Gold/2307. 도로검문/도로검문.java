import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

//  메모리 초과 - max value 값,, 원인은 최단 거리 상의 거리만 막으면 되기 때문 그 이상으로 하면 pq가 매우 커짐
// 블로그 아이디어 - path[next] = 이전에 왔던 길 기록 이후 N 부터 순회, 우선순위큐에 제일 처음으로 들어오는 경로들이 최단 경로이다.
public class Main {
    static int N, M, minDistance, tmpDistance, answer;
    static int MAX_VALUE = 50000000;

    static int[] paths;
    static Map<Integer, List<Pair>> adjList;    // 1 : {(1, 2), (3, 4)}, ...
    static class Pair implements Comparable<Pair>{
        int node;
        int cost;
        Pair(int node, int cost) {
            this.node = node;
            this.cost = cost;
        }

        @Override
        public int compareTo(Pair o) {
            return this.cost - o.cost;
        }
    }

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(br.readLine());
        N = Integer.parseInt(stz.nextToken());
        M = Integer.parseInt(stz.nextToken());

        adjList = new HashMap<>();
        for (int i = 0; i < M ; i ++) {
            stz = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(stz.nextToken());
            int b = Integer.parseInt(stz.nextToken());
            int cost = Integer.parseInt(stz.nextToken());

            List<Pair> tmp1 = adjList.getOrDefault(a, new ArrayList<>());
            tmp1.add(new Pair(b, cost));
            adjList.put(a, tmp1);

            List<Pair> tmp2 = adjList.getOrDefault(b, new ArrayList<>());
            tmp2.add(new Pair(a, cost));
            adjList.put(b, tmp2);
        }
        // 최단 거리 구한 후, 각 도로를 제외한 값을 구한다.
        paths = new int[N + 1];
        getMinDistance();
        for (int i = N ; i > 0 ; i --) {
            getOtherRoute(paths[i], i);
            if (tmpDistance == MAX_VALUE) {
                answer = -1;
                break;
            }
            answer = Math.max(tmpDistance - minDistance, answer);
        }
        System.out.println(answer);

    }

    static void getMinDistance() {
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        int[] visited = new int[N + 1]; // 1 ~ N + 1
        Arrays.fill(visited, MAX_VALUE);

        pq.add(new Pair(1, 0));
        visited[1] = 0;

        while(! pq.isEmpty()) {
            Pair cur = pq.poll();
            if (cur.node == N) {
                minDistance = visited[N];
                return;
            }

            // 이미 방문해서 지금 값보다 작은 값을 가지고 있는 경우 방문 x - 방문 처리 역할
            if (visited[cur.node] < cur.cost) continue;

            for (var adj : adjList.getOrDefault(cur.node, new ArrayList<>())) {
                if (visited[adj.node] > cur.cost + adj.cost) {
                    visited[adj.node] = cur.cost + adj.cost;
                    pq.add(new Pair(adj.node, cur.cost + adj.cost));
                    paths[adj.node] = cur.node;
                }
            }
        }
    }

    static void getOtherRoute(int from, int to) {
        tmpDistance = MAX_VALUE;
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        int[] visited = new int[N + 1]; // 1 ~ N + 1
        Arrays.fill(visited, MAX_VALUE);

        pq.add(new Pair(1, 0));
        visited[1] = 0;

        while(! pq.isEmpty()) {
            Pair cur = pq.poll();
            if (cur.node == N) {
                tmpDistance = visited[N];
                return;
            }
            if (visited[cur.node] < cur.cost) continue;

            for (var adj : adjList.getOrDefault(cur.node, new ArrayList<>())) {
                if (cur.node == from && adj.node == to) {
                    continue;
                }
                if (visited[adj.node] > cur.cost + adj.cost) {
                    visited[adj.node] = cur.cost + adj.cost;
                    pq.add(new Pair(adj.node, cur.cost + adj.cost));
                    paths[adj.node] = cur.node;
                }
            }
        }
    }
}
