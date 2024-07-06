import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// ㄷㅐ충 for문으로 다 돌아도 8만 정도..
public class Main {

    static int N, answer;
    static int[][] map;

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N + 1][N + 1];

        for (int r = 1; r < N + 1; r++) {
            StringTokenizer stz = new StringTokenizer(br.readLine());
            for (int c = 1; c < N + 1; c++) {
                map[r][c] = Integer.parseInt(stz.nextToken());
            }
        }
        answer = Integer.MAX_VALUE;
        solution();
        System.out.println(answer);
    }

    public static void solution() {
        for (int r = 1; r < N + 1; r ++) {
            for (int c = 1; c < N + 1; c++) {
                for(int d1 = 1 ; d1 < 2 * N; d1 ++) {
                    for (int d2 = 1; d2 < 2 * N; d2 ++) {
                        if (1 <= c - d1 &&  c + d2 <= N && r < r + d1 + d2 && r + d1 + d2 <= N) {
                            countPeople(r, c, d1, d2);
                        }
                    }
                }
            }
        }
    }

    public static void countPeople(int cr, int cc, int d1, int d2) {
        int[] wards = new int[6];
        for (int r = 1; r < N + 1; r++) {
            for (int c = 1; c < N + 1; c++) {
                if (r < cr + d1 && c <= cc - Math.max(r - cr + 1, 0)) {    // 위에 행이두개이상일때 체크
                    wards[1] += map[r][c];
                } else if (r <= cr + d2 && cc + Math.max((r - cr), 0) < c && c <= N) {
                    wards[2] += map[r][c];
                } else if (cr + d1 <= r && r <= N && c < cc - d1 + d2 - Math.max(cr + d1 + d2 - r, 0)) {// 가장 아래 꼭지점 - 현재 위치 꼭지점 - 1
                    wards[3] += map[r][c];
                } else if (cr + d2 < r && r <= N && cc - d1 + d2 + Math.max(cr + d1 + d2 - r + 1, 0)<= c && c <= N) {
                    wards[4] += map[r][c];
                } else {
                    wards[5] += map[r][c];
                }
            }
        }
        int minPeople = Integer.MAX_VALUE;
        int maxPeople = 0;
        for (int i = 1; i < 6; i ++) {
            minPeople = Math.min(minPeople, wards[i]);
            maxPeople = Math.max(maxPeople, wards[i]);
        }

        answer = Math.min(answer, maxPeople - minPeople);
    }
}
