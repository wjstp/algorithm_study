import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;


// while 문에서 값을 확인하면서 값을 삭제할 때 인덱스 종ㄹ료 조건을 잘못 설정. 비어있을 때와 n-1 보다 같거나 클때가 아니라 클때 탈출해야 함..
// 빈공만 남게 됐을 떄 명령하는 경우 고려 x
public class Main {
    static int N, M, K, answer;
    static int[][] map; // 각 칸에 있는 파이어볼의 개수 카운팅
    static int[] dr ;
    static int[] dc ;
    static List<Point> info;

    static class Point{
        int r;  // 위치
        int c;
        int m;  // 질량
        int s;  // 속력
        int d;  // 이동 거리
        Point(int r, int c, int m, int s, int d){
            this.r = r;
            this.c = c;
            this.m = m;
            this.s = s;
            this.d = d;
        }
    }

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz;
        stz = new StringTokenizer(br.readLine());
        N = Integer.parseInt(stz.nextToken());
        M = Integer.parseInt(stz.nextToken());
        K = Integer.parseInt(stz.nextToken());

        dr = new int[]{N-1, N-1, 0, 1, 1, 1, 0, N-1, N-1};  // 벽에 막히는 경우가 없으므로 N-1로..
        dc = new int[]{0, 1, 1, 1, 0, N-1, N-1, N-1, 0};
        map = new int[N][N];
        info = new ArrayList<>();
        for (int i = 0; i < M ; i ++) {
            stz = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(stz.nextToken());
            int c = Integer.parseInt(stz.nextToken());
            int m = Integer.parseInt(stz.nextToken());
            int s = Integer.parseInt(stz.nextToken());
            int d = Integer.parseInt(stz.nextToken());
            map[r - 1][c - 1] += 1;
            info.add(new Point(r - 1, c - 1, m, s, d));
        }
        for (int i = 0; i < K; i ++) {
            if (! info.isEmpty()) { // 빈공만 있는 경우에는 명령하지 않는다.
                orderFireBall();
            }
        }

        getAnswer();
        System.out.println(answer);
    }

    public static void orderFireBall() {
        // 자신의 방향과 속력에 맞게 이동
        for (int i = 0; i < info.size(); i ++) {
            int nr = (info.get(i).r + dr[info.get(i).d] * info.get(i).s) % N;
            int nc = (info.get(i).c + dc[info.get(i).d] * info.get(i).s) % N;
            map[info.get(i).r][info.get(i).c] -= 1;
            map[nr][nc] += 1;
            info.get(i).r = nr;
            info.get(i).c = nc;
        }
        // 이동이 끝난 뒤 - 두 개 이상의 파이어볼이 있는 칸 확인
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (map[r][c] >= 2) {
                    // 합친 후 각각의 질량(질량이 0이면 사라짐), 속력, 방향 설정
                    divideFireBall(r, c);
                }
            }
        }

    }
    public static void divideFireBall(int r, int c) {
        int measure = 0, speed = 0, checkDir = 0;
        boolean even = true, flag = true, stopCheck = false;
        for (int idx = info.size() - 1 ; idx >= 0 ; idx --) {
            if (info.get(idx).r == r && info.get(idx).c == c) {
                if (!stopCheck) {
                    if (flag) {
                        checkDir = info.get(idx).d;
                        flag = false;
                    } else {
                        if ((checkDir & 1) != (info.get(idx).d & 1)) {
                            stopCheck = true;
                            even = false;
                        }
                    }
                }
                measure += info.get(idx).m;
                speed += info.get(idx).s;
                info.remove(idx);
            }
        }
        // 질량이 0이 되면 사라짐
        if ((measure / 5) != 0) {
            if (even) {// 방향 모두 홀수 or 모두 짝수
                for (int i = 0; i < 4; i ++) {
                    info.add(new Point(r, c, measure / 5, speed / map[r][c], i * 2));
                }
            } else {
                for (int i = 0 ; i < 4; i ++) {
                    info.add(new Point(r, c, measure / 5, speed / map[r][c], i * 2 + 1));
                }
            }
            // 새로운 파이어볼 넣어주기
            map[r][c] = 4;
            return;
        }
        map[r][c] = 0;
    }

    public static void getAnswer () {
        for (int i = 0; i < info.size(); i ++) {
            answer += info.get(i).m;
        }
    }
}
