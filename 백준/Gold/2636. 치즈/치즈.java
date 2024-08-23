import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int R, C, answer;
    static int[][] map;
    static int[][] visited;
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};
    static class Point{
        int r;
        int c;

        Point(int r, int c){
            this.r = r;
            this.c = c;
        }
    }
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(br.readLine());
        R = Integer.parseInt(stz.nextToken());
        C = Integer.parseInt(stz.nextToken());
        map = new int[R][C];
        for (int r = 0; r < R; r ++) {
            stz = new StringTokenizer(br.readLine());
            for (int c = 0; c < C; c++) {
                map[r][c] = Integer.parseInt(stz.nextToken());
            }
        }

        // 가장 자리에 맞닿아있는 것들은 전부 넣는다.
        Queue<Point> q = new LinkedList<>();
        visited = new int[R][C];
        for (int c = 0; c < C; c ++) {
            q.add(new Point(0, c));
            q.add(new Point(R - 1, c));
            visited[0][c] = 1;
            visited[R- 1][c] = 1;
        }
        for (int r = 0; r < R; r++) {
            q.add(new Point(r, 0));
            q.add(new Point(r, C - 1));
            visited[r][0] = 1;
            visited[r][C - 1] = 1;
        }
        while (!q.isEmpty()) {
            Point cur = q.poll();
            int curLevel = visited[cur.r][cur.c];
            for (int i = 0; i < 4; i ++) {
                int nr = cur.r + dr[i];
                int nc = cur.c + dc[i];
                if (0 > nr || R <= nr || 0 > nc || C <= nc ) continue;
                if (map[nr][nc] == 0) {
                    if (visited[nr][nc] == 0 || (visited[nr][nc] != 0 && visited[nr][nc] > curLevel)) {
                        visited[nr][nc] = curLevel;
                        q.add(new Point(nr, nc));
                    }
                } else {
                    if (visited[nr][nc] == 0 || (visited[nr][nc] != 0 && visited[nr][nc] > (curLevel + 1))) {
                        visited[nr][nc] = curLevel + 1;
                        q.add(new Point(nr, nc));
                    }
                }
            }
        }
        int maxLevel = 0;
        for (int r = 0; r < R; r ++) {
            for (int c = 0; c < C; c++) {
                if (map[r][c] == 0) continue;
                if (maxLevel == visited[r][c] - 1) answer ++;
                if (maxLevel < visited[r][c] - 1) {
                    maxLevel = visited[r][c] - 1;
                    answer = 1;
                }
            }
        }

        System.out.println(maxLevel);
        System.out.println(answer);
    }
}
