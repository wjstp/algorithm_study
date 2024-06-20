import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int N, M, answer;
    static int[][] map;
    static int[][] cloudMap;    // 이전 구름 위치
    static int[] dr = {0, -1, -1, -1, 0, 1, 1, 1};
    static int[] dc = {-1, -1, 0, 1, 1, 1, 0, -1};
    static List<Point> clouds;  // 현재 구름의 위치

    static class Point{
        int r;
        int c;
        Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String argsp[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz ;
        stz = new StringTokenizer(br.readLine());
        N = Integer.parseInt(stz.nextToken());
        M = Integer.parseInt(stz.nextToken());

        map = new int[N][N];
        for (int r = 0; r < N ; r++) {
            stz = new StringTokenizer(br.readLine());
            for (int c= 0; c < N; c++) {
                map[r][c] = Integer.parseInt(stz.nextToken());
            }
        }
        // 가장 처음 구름의 위치
        cloudMap = new int[N][N];

        clouds = new ArrayList<>();
        for (int i = 1; i <= 2; i++) {
            clouds.add(new Point(N-i, 0));
            clouds.add(new Point(N-i, 1));
        }


        for (int i = 0; i < M ; i++) {
            stz = new StringTokenizer(br.readLine());
            solution(Integer.parseInt(stz.nextToken()), Integer.parseInt(stz.nextToken()) );
        }
        getTotalWater();
        System.out.println(answer);

    }

    public static void solution(int d, int s) {
        // 구름 이동 및 비내림
        for (int i = 0; i < clouds.size(); i ++) {
            int nr = (clouds.get(i).r + ((dr[d-1] + N) * s) % N) % N;
            int nc = (clouds.get(i).c + ((dc[d-1] + N) * s) % N) % N;
            clouds.get(i).r = nr;
            clouds.get(i).c = nc;
            map[nr][nc] += 1;
        }
        // 물복사버그 마법
        for (int i = 0 ; i < clouds.size() ; i ++) {
            cloudMap[clouds.get(i).r][clouds.get(i).c] = 1; // 이전 구름 위치 기록
            // 물복사 마법 - 대각선에 물이 있다면 물이 더 증가
            for (int j = 0; j < 4; j ++) {
                int nr = (clouds.get(i).r + dr[j * 2 + 1]) ;
                int nc = (clouds.get(i).c + dc[j * 2 + 1]);
                if (0 <= nr && 0 <= nc && nr < N && nc < N) {
                    if (map[nr][nc] > 0) {
                        map[clouds.get(i).r][clouds.get(i).c] ++;
                    }
                }
            }
        }
        clouds.clear();
        // 구름 생성 - 이때는 물이 안 생김
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (map[r][c] >= 2) {
                    if (cloudMap[r][c] == 1) {
                        cloudMap[r][c] = 0;
                    }else {
                        map[r][c] -= 2;
                        clouds.add(new Point(r, c));
                    }
                }
            }
        }
    }
    public static void getTotalWater() {
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                answer += map[r][c];
            }
        }
    }
}
