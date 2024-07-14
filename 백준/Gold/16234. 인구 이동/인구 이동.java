import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, L, R;
    static int[][] map;
    static boolean continueChecking = true;
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};
    static class Point{
        int r;
        int c;
        Point(int r , int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static void main (String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(br.readLine());
        N = Integer.parseInt(stz.nextToken());
        L = Integer.parseInt(stz.nextToken());
        R = Integer.parseInt(stz.nextToken());

        map = new int[N][N];
        for (int r = 0; r < N; r ++) {
            stz = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; c++) {
                map[r][c] = Integer.parseInt(stz.nextToken());
            }
        }
        int day = 0;
        while (continueChecking) {
            checkPopulation();
            day ++ ;
        }
        System.out.println(day - 1);
    }
    public static void checkPopulation() {
        boolean flag = true;
        boolean flag2 = true;
        int num = 1;
        int[][] visited = new int[N][N];
        int[][] tmpMap = new int[N][N];
        for (int r = 0; r < N; r++) {
            tmpMap[r] = map[r].clone();
        }
        for (int r = 0; r < N; r ++) {
            for (int c = 0; c < N; c ++) {
                if (visited[r][c] == 0) {
                    int total = 0, cnt = 0;
                    Queue<Point> q = new LinkedList<>();
                    visited[r][c] = num;
                    q.add(new Point(r, c));
                    while (!q.isEmpty()) {
                        Point current = q.poll();
                        total += tmpMap[current.r][current.c];
                        cnt ++ ;
                        for (int i = 0; i < 4; i ++) {
                            int nr = current.r + dr[i];
                            int nc = current.c + dc[i];
                            if (0 <= nr && nr < N && 0 <= nc && nc < N && visited[nr][nc] == 0) {
                                int tmp = Math.abs(tmpMap[nr][nc] - tmpMap[current.r][current.c]);
                                if (tmp >= L && tmp <= R) {
                                    flag = false;
                                    q.add(new Point(nr, nc));
                                    visited[nr][nc] = num;
                                }
                            }
                        }
                    }
                    if (!flag) {
                        movePopulation(num, total, cnt, visited);
                        flag = true;
                        flag2 = false;
                    }
                }
                num ++ ;
            }
        }
        if (flag2) {
            // 연합이 더 이상 없는 경우
            continueChecking = false;
        }
    }
    public static void movePopulation(int num, int totalPopulation, int cnt, int[][] visited) {
        // visited가 num으로 칠해진 부분 인구수 바꾸기
        int newPopulation = totalPopulation / cnt;
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (visited[r][c] == num) {
                    map[r][c] = newPopulation;
                }
            }
        }
    }
    
}
