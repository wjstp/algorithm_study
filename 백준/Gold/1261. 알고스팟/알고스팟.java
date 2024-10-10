import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int M, N; // ㄱㅏ로, 세로
    static int[][] map, visited;
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};
    static PriorityQueue<Point> pq;

    static class Point implements Comparable<Point>{
        int r;
        int c;
        int cnt;
        Point(int r, int c, int cnt) {
            this.r = r;
            this.c = c;
            this.cnt = cnt;
        }
        @Override
        public int compareTo(Point o) {
            return this.cnt - o.cnt; // cnt 오름차순
        }

    }
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(br.readLine());
        M = Integer.parseInt(stz.nextToken());
        N = Integer.parseInt(stz.nextToken());

        map = new int[N][M];
        for (int r = 0; r < N; r ++) {
            String line = br.readLine();
            for (int c = 0; c < M; c ++) {
                map[r][c] = Integer.parseInt(String.valueOf(line.charAt(c)));
            }
        }
        visited = new int[N][M];
        for (int r = 0; r < N; r++) {
            Arrays.fill(visited[r], Integer.MAX_VALUE); // 체크
        }

        pq = new PriorityQueue<>();
        pq.add(new Point(0, 0, 0));
        visited[0][0] = 0;
        while (!pq.isEmpty()) {
            Point cur = pq.poll();
            if (cur.cnt > visited[cur.r][cur.c]) continue;
            
            for (int i = 0; i < 4; i ++) {
                int nr = cur.r + dr[i];
                int nc = cur.c + dc[i];
                if (0 > nr || nr >= N || 0 > nc || nc >= M) continue;
                if (visited[nr][nc] <= cur.cnt + map[nr][nc]) continue;
                visited[nr][nc] = cur.cnt + map[nr][nc];
                pq.add(new Point(nr, nc, visited[nr][nc]));
            }
        }
        System.out.println(visited[N - 1][M - 1]);
    }
}
