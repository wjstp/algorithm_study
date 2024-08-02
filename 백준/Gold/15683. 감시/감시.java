import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N, M, answer;
    static int[][] map;

    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};
    static List<Point> cameras;
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
        StringTokenizer stz;
        stz = new StringTokenizer(br.readLine());
        N = Integer.parseInt(stz.nextToken());
        M = Integer.parseInt(stz.nextToken());
        map = new int[N][M];
        cameras = new ArrayList<>();
        answer = N * M ;
        for (int r = 0; r < N; r ++) {
            stz = new StringTokenizer(br.readLine());
            for (int c = 0; c < M ; c++) {
                map[r][c] = Integer.parseInt(stz.nextToken());
                if (map[r][c] != 0 && map[r][c] != 6) {
                    cameras.add(new Point(r, c));
                }
            }
        }
        dfs(0);
        System.out.println(answer);
    }
    
    public static void dfs(int depth) {
        if (depth == cameras.size()) {
            answer = Math.min(answer, getBlindSpot());
            return;
        }

        Point current = cameras.get(depth);
        int camera = map[current.r][current.c];

        switch (camera) {
            case 1 :
                for(int j = 0; j < 4; j ++) {
                    // 복사
                    int[][] copy = copy();
                    List<Integer> direction = new ArrayList<>();
                    direction.add(j);
                    checkVisible(current.r, current.c, direction);
                    dfs(depth + 1);
                    // 원상복구
                    restore(copy);
                }
                break;
            case 2 :
                for (int j = 0; j < 2; j ++) {
                    // 복사
                    int[][] copy = copy();
                    List<Integer> direction = new ArrayList<>();

                    direction.add(j);
                    direction.add(2 + j);
                    checkVisible(current.r, current.c, direction);
                    dfs(depth + 1);
                    // 원상복구
                    restore(copy);
                }
                break;
            case 3 :
                for (int j = 0; j < 4; j ++) {
                    // 복사
                    int[][] copy = copy();
                    List<Integer> direction = new ArrayList<>();

                    direction.add(j);
                    direction.add((j + 1) % 4);
                    checkVisible(current.r, current.c, direction);
                    dfs(depth + 1);
                    // 원상복ㄱ구
                    restore(copy);
                }
                break;
            case 4 :
                for (int j = 0; j < 4; j ++) {
                    // 복사
                    int[][] copy = copy();
                    List<Integer> direction = new ArrayList<>();

                    direction.add(j);
                    direction.add((j + 1) % 4);
                    direction.add((j + 2) % 4);
                    checkVisible(current.r, current.c, direction);
                    dfs(depth + 1);
                    // 원상복ㄱ구
                    restore(copy);
                }
                break;
            case 5 :
                // 복사
                int[][] copy = copy();
                List<Integer> direction = new ArrayList<>();

                for (int k = 0; k < 4; k ++) {
                    direction.add(k);
                }
                checkVisible(current.r, current.c, direction);
                dfs(depth + 1);
                //원상복구
                restore(copy);
                break;
        }

    }

    public static void checkVisible(int cr, int cc, List<Integer> direction) {
        for (int i = 0 ; i < direction.size() ; i ++) {
            int nr = cr + dr[direction.get(i)];
            int nc = cc + dc[direction.get(i)];
            while(0 <= nr && nr < N && 0 <= nc && nc < M && map[nr][nc] != 6) {
                if (map[nr][nc] == 0) {
                    map[nr][nc] = -1;
                }
                nr += dr[direction.get(i)];
                nc += dc[direction.get(i)];
            }
        }
    }

    public static int getBlindSpot() {
        int blind = 0;
        for (int r = 0 ; r < N; r ++) {
            for (int c = 0; c < M ; c++) {
                if (map[r][c] == 0) {
                    blind ++ ;
                }
            }
        }
        return blind;
    }
    
    public static int[][] copy() {
        int[][] copy = new int[N][M];
        for (int r = 0; r < N; r ++) {
            copy[r] = map[r].clone();
        }
        return copy;
    }
    
    public static void restore(int[][] copy) {
        for (int r = 0; r < N; r ++) {
            map[r] = copy[r].clone();
        }

    }
}
