import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int R, C, answer;
    static char[][] map;
    static int[][] visit;
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(br.readLine());
        R = Integer.parseInt(stz.nextToken());
        C = Integer.parseInt(stz.nextToken());
        map = new char[R][C];
        for (int r = 0; r < R; r ++) {
            String tmp = br.readLine();
            for (int c = 0; c < C; c++) {
                map[r][c] = tmp.charAt(c);
            }
        }
        dfs(0, 0, 1, 1 << map[0][0] - 'A');
        System.out.println(answer);
    }

    public static void dfs(int cr, int cc, int cnt, int check) {
        answer = Math.max(cnt, answer);
        if (cr == R - 1 && cc == C - 1) return;

        // 가장 도착지점에서 먼 곳부터 순회할 수 있도록 함
        for(var dir : List.of(new int[]{-1, 0}, new int[]{0, -1}, new int[]{1, 0}, new int[]{0, 1})) {
            int nr = cr + dir[0];
            int nc = cc + dir[1];
            if (0 > nr || nr >= R || 0 > nc || nc >= C) continue;   // 범위 체크
            if ((check & (1 << map[nr][nc] - 'A')) != 0) continue;  // 알파벳 체크
            check |= (1 << map[nr][nc] - 'A');
            dfs(nr, nc, cnt + 1, check);
            check &= ~(1 << map[nr][nc] - 'A');

        }
    }
}
