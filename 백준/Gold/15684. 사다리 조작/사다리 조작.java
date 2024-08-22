import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N, M, H;
    static int[][] map;
    static boolean flag;
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(br.readLine());
        N = Integer.parseInt(stz.nextToken());  // 세로선 수
        M = Integer.parseInt(stz.nextToken());  // 현재 있는 가로 선 수
        H = Integer.parseInt(stz.nextToken());  // 가로 점선 수
        map = new int[H][N - 1];    //가로선 기록
        for (int i = 0; i < M; i ++) {
            stz = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(stz.nextToken());
            int b = Integer.parseInt(stz.nextToken());
            map[a - 1][b - 1] = 1;
        }
        flag = check();
        if (flag) System.out.println(0);
        // 하나만 선택 - 최대 270 *
        if (!flag) {
            for (int i = 0; i < (N - 1) * H; i ++) {
                int r = i / (N - 1);
                int c = i - r * (N - 1);
                if (! isValid(r, c)) continue;
                map[r][c] = 1;
                flag = check();
                if (flag) {
                    System.out.println(1);
                    break;
                }
                map[r][c] = 0;
            }
        }
        if (! flag) {
            // 두개 선택 - 270C2
            for (int i = 0; i < (N - 1) * H; i ++) {
                for (int j = i + 1; j < (N - 1) * H; j ++) {
                    int r1 = i / (N - 1);
                    int c1 = i - r1 * (N - 1);
                    int r2 = j / (N - 1);
                    int c2 = j - r2 * (N - 1);
                    if (!isValid(r1, c1) || !isValid(r2, c2)) continue;
                    map[r1][c1] = 1;
                    map[r2][c2] = 1;
                    flag = check();
                    if (flag) {
                        System.out.println(2);
                        break;
                    }
                    map[r1][c1] = 0;
                    map[r2][c2] = 0;
                }
                if (flag) break;
            }
        }
        if (! flag) {
            for (int i = 0; i < (N - 1) * H; i ++) {
                for (int j = i + 1; j < (N - 1) * H; j ++) {
                    for (int k = j + 1; k < (N - 1) * H ; k ++) {
                        int r1 = i / (N - 1);
                        int c1 = i - r1 * (N - 1);
                        int r2 = j / (N - 1);
                        int c2 = j - r2 * (N - 1);
                        int r3 = k / (N - 1);
                        int c3 = k - r3 * (N - 1);
                        if (!isValid(r1, c1) || !isValid(r2, c2) || !isValid(r3, c3)) continue;
                        map[r1][c1] = 1;
                        map[r2][c2] = 1;
                        map[r3][c3] = 1;
                        flag = check();
                        if (flag) {
                            System.out.println(3);
                            break;
                        }
                        map[r1][c1] = 0;
                        map[r2][c2] = 0;
                        map[r3][c3] = 0;
                    }
                    if (flag) break;
                }
                if (flag) break;
            }
        }
        if (!flag) System.out.println(-1);
    }
    public static boolean isValid(int r, int c) {
        if (map[r][c] == 1 || (c > 0 && map[r][c - 1] == 1) || (c < N - 1 && map[r][c] == 1)) {
            return false;
        }
        return true;
    }

    // 찾고 나서 이동 후 도착하는 지 확인
    public static boolean check() {
        for (int person = 0; person < N; person ++) {
            int cur = person;
            for (int r = 0; r < H; r ++) {
                if (cur < N - 1 && map[r][cur] == 1) {
                    cur ++ ;
                } else if (cur > 0 && map[r][cur - 1] == 1) {
                    cur -- ;
                }
            }
            if (cur != person) return false;
        }
        return true;
    }
}
