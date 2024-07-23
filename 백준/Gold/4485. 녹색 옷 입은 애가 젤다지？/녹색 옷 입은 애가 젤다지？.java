import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int N, answer, problemNumber;
    static int[][] map; // 도둑 루피의 양이 들어있다.
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};
    static class Point implements Comparable<Point>{
        int r;
        int c;
        int cost;
        Point(int r, int c, int cost) {
            this.r = r;
            this.c = c;
            this.cost = cost;
        }

        @Override
        public int compareTo(Point point) {
            return this.cost - point.cost;
        }
    }

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            N = Integer.parseInt(br.readLine());
            if (N == 0) {
                break;
            }

            StringTokenizer stz ;
            map = new int[N][N];
            for (int r = 0; r < N; r ++) {
                stz = new StringTokenizer(br.readLine());
                for (int c = 0; c < N; c ++) {
                    map[r][c] = Integer.parseInt(stz.nextToken());
                }
            }
            dijkstra();
            System.out.println("Problem " + ++ problemNumber + ": " + answer);
        }
    }

    public static void dijkstra() {
        // 0, 0에서 n -1, n-1 로 이동
        PriorityQueue<Point> pq = new PriorityQueue<>();
        int[][] visited = new int[N][N];    // 누적 코스트 기록
        for (int r = 0; r < N; r ++) {
            for (int c = 0; c < N; c ++) {
                visited[r][c] = 50000;
            }
        }

        pq.add(new Point(0, 0, map[0][0]));
        visited[0][0] = map[0][0];

        while (!pq.isEmpty()) {
            Point cur = pq.poll();
            //  n - 1 , n - 1 종료 조건 x - 이후에 더 작은 값이 나올 수 있으므로

            // 이미 기록된 최소 비용보다 값이 더 크다면 갱신되지않도록 continue
            if (visited[cur.r][cur.c] < cur.cost) {
                continue;
            }

            for (int i = 0; i < 4; i ++) {
                int nr = cur.r + dr[i];
                int nc = cur.c + dc[i];
                if (0 <= nr && nr < N && 0 <= nc && nc < N) {
                    // 들어갈 값이 원래 있던 값보다 작다면 pq 와 visited 갱신
                    if(visited[nr][nc] > cur.cost + map[nr][nc]) {
                        pq.add(new Point(nr, nc, cur.cost + map[nr][nc]));
                        visited[nr][nc] = cur.cost + map[nr][nc];
                    }
                }
            }
        }

        answer = visited[N-1][N-1];

    }
}
