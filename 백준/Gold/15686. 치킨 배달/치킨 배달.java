import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.*;

// 대채 왜 무조건 bfs로 하려고 했을까..?
// dfs의 시작점을 지정해주지 않았었음 - 가지치기
public class Main {

    static int N, M;
    static int[][] map;
    static int totalChickenDistance = Integer.MAX_VALUE;
    static List<Point> chicken;
    static List<Point> home;
    static List<Point> selected = new ArrayList<>();
    public static class Point{
        int r ;
        int c ;
        Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }


    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz;

        stz = new StringTokenizer(br.readLine());
        N = Integer.parseInt(stz.nextToken());
        M = Integer.parseInt(stz.nextToken());
        map = new int[N][N];
        chicken = new ArrayList<>();
        home = new ArrayList<>();
        for (int r = 0; r < N ; r ++) {
            stz = new StringTokenizer(br.readLine());
            for (int c = 0; c < N ; c ++) {
                map[r][c] = Integer.parseInt(stz.nextToken());
                if (map[r][c] == 2) {
                    chicken.add(new Point(r, c));
                }
                else if (map[r][c] == 1) {
                    home.add(new Point(r, c));
                }
            }
        }
        dfs(0, 0);
        System.out.println(totalChickenDistance);
    }
    public static void dfs(int depth, int start) {
        if (depth == M) {
            // 이때 거리 측정
            int nomDistance = 0;
            for (int i = 0; i < home.size() ; i ++) {
                Point h = home.get(i);
                // 선택된 치킨 집 중 가장 가까운 곳으로 치킨 거리 책정
                int minDistance = 100000;
                for (int j = 0; j < M; j ++) {
                    Point c = selected.get(j);
                    minDistance = Math.min(minDistance, Math.abs(c.c - h.c) + Math.abs(c.r - h.r));
                }
                nomDistance += minDistance;
                if (nomDistance >= totalChickenDistance) {
                    return;
                }
            }
            totalChickenDistance = Math.min(nomDistance, totalChickenDistance);
            return;
        }
        for (int i = start; i < chicken.size() ; i ++) {
            selected.add(chicken.get(i));
            dfs(depth + 1, i + 1);
            selected.remove(chicken.get(i));
        }
    }


}
