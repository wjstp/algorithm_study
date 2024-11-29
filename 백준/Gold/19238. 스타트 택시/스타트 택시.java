import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

// 도착지가 겹칠 수 있음
public class Main {
    static int N, M, fuel, taxiR, taxiC;
    static int[][] map, startInfo, endInfo; // 벽 정보, 승객하차 정보
    static Set[][] endList;
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};
    static class Point implements Comparable<Point>{
        int r;
        int c;
        int level;

        Point(int r, int c, int level) {
            this.r = r;
            this.c = c;
            this.level = level;
        }
        @Override
        public int compareTo(Point o) {
            if (o.level != this.level) return this.level - o.level;
            if (o.r != this.r) return this.r - o.r;
            return this.c - o.c;
        }
    }
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(br.readLine());
        N = Integer.parseInt(stz.nextToken());
        M = Integer.parseInt(stz.nextToken());
        fuel = Integer.parseInt(stz.nextToken());
        map = new int[N][N];
        for (int r = 0; r < N; r ++) {
            stz = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; c ++) {
                map[r][c] = Integer.parseInt(stz.nextToken());
            }
        }
        stz = new StringTokenizer(br.readLine());
        taxiR = Integer.parseInt(stz.nextToken()) - 1;
        taxiC = Integer.parseInt(stz.nextToken()) - 1;
        startInfo = new int[N][N];
        endInfo = new int[N][N];
        endList = new HashSet[N][N];
        for (int i = 0; i < N; i ++) {
            for (int j = 0; j < N; j ++) {
                endList[i][j] = new HashSet<>();
            }
        }
        for (int i = 1 ; i <= M; i ++) {
            stz = new StringTokenizer(br.readLine());
            int sr = Integer.parseInt(stz.nextToken()) - 1;
            int sc = Integer.parseInt(stz.nextToken()) - 1;
            int er = Integer.parseInt(stz.nextToken()) - 1;
            int ec = Integer.parseInt(stz.nextToken()) - 1;
            startInfo[sr][sc] = i;
            endInfo[er][ec] += -1;
            endList[er][ec].add(i);

        }
        for (int i = 0; i < M; i ++){
            // 가장 가까운 승객으로 이동 - 연료 소모되면 stop
            int customerNumber = moveToCustomer(taxiR, taxiC);
            if (fuel <= 0) break;
            // 목적지까지 이동 - 연료 소모되면 stop
            goToDestination(customerNumber);
            if (fuel <= 0) break;
        }
        if (fuel >= 0) System.out.println(fuel);
        else System.out.println(-1);
    }

    public static int moveToCustomer(int r, int c) {
        PriorityQueue<Point> pq = new PriorityQueue<>();
        pq.add(new Point(r, c, 0));
        int[][] visited = new int[N][N];
        visited[r][c] = 1;
        while (!pq.isEmpty()) {
            Point cur = pq.poll();
            if (startInfo[cur.r][cur.c] > 0) {
                int customerNumber = startInfo[cur.r][cur.c];
                fuel -= cur.level;
                taxiR = cur.r;
                taxiC = cur.c;
                startInfo[cur.r][cur.c] = 0;
                return customerNumber;
            }
            for (int i = 0; i < 4; i ++) {
                int nr = cur.r + dr[i];
                int nc = cur.c + dc[i];
                if (0 > nr || nr >= N || 0 > nc || nc >= N) continue;
                if (visited[nr][nc] == 1 || map[nr][nc] == 1) continue;
                pq.add(new Point(nr, nc, cur.level + 1));
                visited[nr][nc] = 1;
            }
        }
        fuel = -1; // 다음 승객을 찾을 수 없음
        return -1;
    }
    public static int goToDestination(int customerNumber) {
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(taxiR, taxiC, 0));
        int[][] visited = new int[N][N];
        visited[taxiR][taxiC] = 1;
        while (!q.isEmpty()) {
            Point cur = q.poll();
            if (endInfo[cur.r][cur.c] < 0 && endList[cur.r][cur.c].contains(customerNumber)) {
                fuel -= cur.level;
                if (fuel >= 0) fuel += cur.level * 2;
                taxiR = cur.r;
                taxiC = cur.c;
                endInfo[cur.r][cur.c] += 1;
                return 1;
            }
            for (int i = 0; i < 4; i ++) {
                int nr = cur.r + dr[i];
                int nc = cur.c + dc[i];
                if (0 > nr || nr >= N || 0 > nc || nc >= N) continue;
                if (visited[nr][nc] == 1 || map[nr][nc] == 1) continue;
                q.add(new Point(nr, nc, cur.level + 1));
                visited[nr][nc] = 1;
            }
        }
        fuel = -1;
        return -1;
    }
}
