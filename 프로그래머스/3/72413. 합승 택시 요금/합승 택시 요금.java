import java.util.*;

class Solution {
    static Map<Integer, List<Pair>> adjList;   // 1 : [(2, 4), (3, 2)]
    static Map<Integer, Info> distance;
    static class Info{
        int a;
        int b;
        int s;
        Info(int a, int b, int s) {
            this.a = a;
            this.b = b;
            this.s = s;
        }
        int sum() {
            return this.a + this.b + this.s;
        }
    }
    static class Pair implements Comparable<Pair> {
        int point;
        int cost;
        Pair(int point, int cost) {
            this.point = point;
            this.cost = cost;
        }

        @Override
        public int compareTo(Pair pair) {   // 음수이면 자리 교체
            return this.cost  - pair.cost;
        }
    }
    public static int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = Integer.MAX_VALUE;
        adjList = new HashMap<>();
        for (int i = 0; i < fares.length; i ++) {
            List<Pair> tmp1 = adjList.getOrDefault(fares[i][1], new ArrayList<>());
            tmp1.add(new Pair(fares[i][0],  fares[i][2]));
            adjList.put(fares[i][1], tmp1);

            List<Pair> tmp2 = adjList.getOrDefault(fares[i][0], new ArrayList<>());
            tmp2.add(new Pair(fares[i][1],  fares[i][2]));
            adjList.put(fares[i][0], tmp2);
        }


        distance = new HashMap<>();
        getDistanceFromEachPoint(s, 's', n);
        getDistanceFromEachPoint(a, 'a', n);
        getDistanceFromEachPoint(b, 'b', n);
        

        for (int i = 0; i < n + 1; i ++) {
            if (distance.containsKey(i)) {
                answer = Math.min(answer, distance.get(i).sum());
            }
        }

        return answer;
    }
    public static void getDistanceFromEachPoint(int start, char point, int n) {
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        int[] visited = new int[n + 1];
        for (int i = 0; i < n + 1; i ++) {
            visited[i] = Integer.MAX_VALUE;
        }
        pq.add(new Pair(start, 0));
        visited[start] = 0;

        while (! pq.isEmpty()) {
            Pair cur = pq.poll();
            Info info = distance.getOrDefault(cur.point, new Info(0, 0, 0));
            if (cur.cost > visited[cur.point]) {
                continue;
            }
            switch (point) {
                case 's' : info.s = cur.cost;
                    break;
                case 'a' : info.a = cur.cost;
                    break;
                case 'b' : info.b = cur.cost;
                    break;
            }
            distance.put(cur.point, info);

            if (adjList.containsKey(cur.point)) {
                for (var adj : adjList.get(cur.point)) {
                    int next = adj.point;
                    int cost = adj.cost;


                    if (visited[next] > visited[cur.point] + cost) {
                        pq.add(new Pair(next, cost + cur.cost));
                        visited[next] = visited[cur.point] + cost;
                    }
                }
            }
        }
    }
}