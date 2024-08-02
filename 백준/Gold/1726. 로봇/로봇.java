import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

// bfs든, dfs든 방문표시 복잡.. visited를 3차원으로 관리
// 문제 똑바로 읽을 것.. 왼쪽 또는 오른쪽 & 1은 갈 수 없음
// 범위 지정안한게 계속 괴롭힌.. n + 1로 할거면 처음부터 제대로 해라..
// 메모리 초과.. 재귀 호출이 너무 많이 되므로 bfs가 더 적합.. 100 * 100 * 5.. 5만 제곱
// 애초에 모든 경우를 다 볼 필요없이.. 같은 레벨의 최단 경로를 보는거엔 bfs가 적합하다.. 이걸 까먹어?
// 결국 우선순위큐인듯이 아니라 안써도될거같은데 이미 visited를 3차원으로 구분해서..
public class Main {
    static int M, N, answer;
    static Status start, end;
    static int[][] map;
    static int[][][] visited;
    static int[] dr = {0, 0, 1, -1}; // 0, 2, 1, 3, 0
    static int[] dc = {1, -1, 0, 0};

    public static class Status implements Comparable<Status>{
        int r;
        int c;
        int dir;
        int cost;
        Status(int r, int c, int dir, int cost) {
            this.r = r;
            this.c = c;
            this.dir = dir;
            this.cost = cost;
        }

        @Override
        public int compareTo(Status o) {
            return this.cost - o.cost;
        }
    }

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(br.readLine());
        M = Integer.parseInt(stz.nextToken());  // 세로
        N = Integer.parseInt(stz.nextToken());

        map = new int[M + 1][N + 1];
        for (int r = 1; r < M + 1; r ++) {
            stz = new StringTokenizer(br.readLine());
            for (int c = 1; c < N + 1; c++) {
                map[r][c] = Integer.parseInt(stz.nextToken());
            }
        }
        stz = new StringTokenizer(br.readLine());
        start = new Status(Integer.parseInt(stz.nextToken()), Integer.parseInt(stz.nextToken()), Integer.parseInt(stz.nextToken()), 0);

        stz = new StringTokenizer(br.readLine());
        end = new Status(Integer.parseInt(stz.nextToken()), Integer.parseInt(stz.nextToken()), Integer.parseInt(stz.nextToken()), 0);


        breadthFirstSearch();
        System.out.println(answer);
    }

    public static void breadthFirstSearch() {
        Queue<Status> q = new PriorityQueue<>();
        visited = new int[M + 1][N + 1][5];

        q.add(new Status(start.r, start.c, start.dir, 0));
        visited[start.r][start.c][start.dir] = 1;

        while (!q.isEmpty()) {
            Status cur = q.poll();
            if (cur.r == end.r && cur.c == end.c && cur.dir == end.dir) {
                answer = cur.cost;
                return;
            }

            // 방문을 했더라도 코스트가 더 작은 경우라면 다시 방문
            // 방향 전환
            for (int i = 0; i < 2; i ++) {
                int newDir = rotate(cur.dir, i);
                if (visited[cur.r][cur.c][newDir] == 0) {
                    visited[cur.r][cur.c][newDir] = 1;
                    q.add(new Status(cur.r, cur.c, newDir, cur.cost + 1));
                }
            }
            // go 1, 2, 3
            for (int i = 1; i <= 3; i ++) {
                int nr = cur.r + dr[cur.dir - 1] * i;
                int nc = cur.c + dc[cur.dir - 1] * i;
                if (1 <= nr && nr <= M && 1<= nc && nc <= N && visited[nr][nc][cur.dir] == 0) {
                    if (checkRoute(cur, i)) {
                        visited[nr][nc][cur.dir] = cur.cost + 1;
                        q.add(new Status(nr, nc, cur.dir, cur.cost + 1));
                    }
                }
            }
        }
    }

    public static boolean checkRoute(Status cur, int go) {
        for (int i = 1 ; i <= go ; i ++) {
            int nr = cur.r + dr[cur.dir - 1] * i;
            int nc = cur.c + dc[cur.dir - 1] * i;
            if (map[nr][nc] == 1) return false;
        }
        return true;
    }
    public static int rotate(int dir, int right) {
        switch (dir) {
            case 1 :
                if (right == 0) return 4;
                else return 3;
            case 2 :
                if (right == 0) return 3;
                else return 4;
            case 3 :
                if (right == 0) return 1;
                else return 2;
            default:
                if (right == 0) return 2;
                else return 1;
        }
    }
}
