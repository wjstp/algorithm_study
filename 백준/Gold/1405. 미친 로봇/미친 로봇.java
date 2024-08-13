import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static double answer;
    static int[] possibilities;
    static int[][] visit;
    static int[] dr = {0, 0, 1, -1};
    static int[] dc = {1, -1, 0, 0};

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(br.readLine());
        N = Integer.parseInt(stz.nextToken());
        possibilities = new int[4];
        for (int i = 0; i < 4; i ++) {
            possibilities[i] = Integer.parseInt(stz.nextToken());
        }
        visit = new int[29][29]; // 14,14에서 출발
        visit[14][14] = 1;
        dfs(14, 14, 1, 0);
        System.out.println(answer);
    }
    public static void dfs(int cr, int cc, double possibility, int depth) {
        if (depth == N) {
            answer += possibility;
            return;
        }
        for (int i = 0; i < 4; i ++) {
            if (possibilities[i] == 0) continue;    // 길 수 없음
            int nr = cr + dr[i];
            int nc = cc + dc[i];
            if (0<= nr && nr < 29 && 0 <= nc && nc < 29 && visit[nr][nc] == 0) {
                visit[nr][nc] = 1;
                dfs(nr, nc, possibility * (possibilities[i]/(double)100), depth + 1);
                visit[nr][nc] = 0;
            }
        }
    }

}
