import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static int N, M, answer;
    public static int[][] map;
    public static Point[][] directions = {
            // ver1
            {new Point(0, 1), new Point(0, 2), new Point(0, 3)},
            {new Point(1, 0), new Point(2, 0), new Point(3, 0)},
            // ver2
            {new Point(0, 1), new Point(1, 0), new Point(1, 1)},
            // ver3
            {new Point(1, 0), new Point(2, 0), new Point(2, 1)},
            {new Point(0, 1), new Point(0, 2), new Point(1, 0)},
            {new Point(0, 1), new Point(1, 1), new Point(2, 1)},
            {new Point(0, 1), new Point(0, 2), new Point(-1, 2)},
            {new Point(1, 0), new Point(2, 0), new Point(2, -1)},
            {new Point(0, 1), new Point(0, 2), new Point(1, 2)},
            {new Point(0, 1), new Point(1, 0), new Point(2, 0)},
            {new Point(1, 0), new Point(1, 1), new Point(1, 2)},
            // ver4
            {new Point(1, 0), new Point(1, 1), new Point(2, 1)},
            {new Point(0, 1), new Point(-1, 1), new Point(-1, 2)},
            {new Point(0, 1), new Point(1, 1), new Point(1, 2)},
            {new Point(0, 1), new Point(-1, 1), new Point(1, 0)},
            // ver5
            {new Point(0, 1), new Point(0, 2), new Point(1, 1)},
            {new Point(1, 0), new Point(2, 0), new Point(1, 1)},
            {new Point(0, 1), new Point(0, 2), new Point(-1, 1)},
            {new Point(0, 1), new Point(1, 1), new Point(-1, 1)}
    } ;

    static class Point{
        int r;
        int c;
        Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(br.readLine());
        N = Integer.parseInt(stz.nextToken());
        M = Integer.parseInt(stz.nextToken());
        map = new int[N][M];
        for (int r = 0; r < N; r ++) {
            stz = new StringTokenizer(br.readLine());
            for (int c = 0; c < M; c ++) {
                map[r][c] = Integer.parseInt(stz.nextToken());
            }
        }
        rotateAndCheckMax();
        System.out.println(answer);
    }
    public static void rotateAndCheckMax() {
        for (int r = 0; r < N; r ++) {
            for (int c = 0; c < M; c ++) {
                for (int i = 0; i < 19; i ++) {
                    int sum = map[r][c];
                    boolean flag = true;
                    for (int j = 0; j < 3; j ++) {
                        int nr = r + directions[i][j].r;
                        int nc = c + directions[i][j].c;
                        if (0 > nr || nr >= N || 0 > nc || nc >= M) {
                            flag = false;
                            break;
                        }
                        sum += map[nr][nc];
                    }
                    if (flag) {
                        answer = Math.max(answer, sum);
                    }
                }
            }
        }
    }

}
