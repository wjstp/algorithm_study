import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

// 헷갈렸던 점 : 칸의 개수는 8개여도 다 이동하는 데 이동하는 거리는 7임
public class Main {
    static int R, C, M, answer;
    static int NO_FISH = Integer.MAX_VALUE;
    static int[][] map;
    static int[] nearest;
    static HashMap<Integer, int[]> fish;
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(br.readLine());
        R = Integer.parseInt(stz.nextToken());
        C = Integer.parseInt(stz.nextToken());
        M = Integer.parseInt(stz.nextToken());
        fish = new HashMap<>();
        map = new int[R][C];
        nearest = new int[C];
        Arrays.fill(nearest, NO_FISH);
        for (int i = 1; i <= M; i ++) {
            stz = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(stz.nextToken()) - 1;
            int c = Integer.parseInt(stz.nextToken()) - 1;
            int s = Integer.parseInt(stz.nextToken());
            int d = Integer.parseInt(stz.nextToken());
            int z = Integer.parseInt(stz.nextToken());
            map[r][c] = i;
            fish.put(i, new int[]{r, c, s, d, z});
            getNearest(c, r);
        }
        for (int cur = 0; cur < C; cur ++) {
            if (nearest[cur] != NO_FISH) {
                int r = nearest[cur];
                answer += fish.get(map[r][cur])[4];
                fish.remove(map[r][cur]);
                map[r][cur] = 0;
                nearest[cur] = NO_FISH;
            }
            moveFish();
        }
        System.out.println(answer);
    }
    public static void moveFish() {
        int[][] tmp = new int[R][C];
        int[] tmpNearest = new int[C];
        Arrays.fill(tmpNearest, NO_FISH);
        for (int i = 1 ; i <= M; i ++) {
            if (fish.get(i) == null) continue; // 이미 먹혔다면 패스
            int r = fish.get(i)[0]; int c = fish.get(i)[1];
            int s = fish.get(i)[2]; int d = fish.get(i)[3]; int z = fish.get(i)[4];

            // 이동전 nearest가 자신이었으면 max값으로 변경
            if (nearest[c] == r) nearest[c] = NO_FISH;
            // 이동 거리 계산
            switch (d) {
                case 1 : // 위
                    if (s < r) {
                        r -= s;
                    } else {
                        if ((((s - r) / (R - 1)) & 1) == 1) {
                            r = R - (s - r) % (R - 1) - 1;
                        } else {
                            r = (s - r) % (R - 1);
                            d = 2;
                        }
                    }
                    break;
                case 2 : // 아래
                    if (s + r < R) {
                        r += s;
                    } else {
                        if ((((s + r) / (R - 1)) & 1) == 1) {
                            r = R - (s + r) % (R - 1) - 1;
                            d = 1;
                        } else {
                            r = (s + r) % (R - 1);
                        }
                    }
                    break;
                case 3 : // 오른쪽
                    if (s + c < C) {
                        c += s;
                    } else {
                        if (((c + s) / (C - 1) & 1) == 1) {
                            c = C - (s + c) % (C - 1) - 1;
                            d = 4;
                        } else {
                            c = (s + c) % (C - 1);
                        }
                    }
                    break;
                case 4 :
                    if (s < c) {
                        c -= s;
                    } else {
                        if ((((s - c) / (C - 1)) & 1) == 1) {
                            c = C - (s - c) % (C - 1) - 1;
                        } else {
                            c = (s - c) % (C - 1);
                            d = 3;
                        }
                    }
                    break;
            }
            // 이동 시 그 자리에 물고기 있으면 크기 비교 후 작은 놈은 hashmap에서 remove
            if (tmp[r][c] == 0 || fish.get(tmp[r][c])[4] < z) {
                if (tmp[r][c] != 0) fish.remove(tmp[r][c]);
                tmp[r][c] = i;
                getNearest(c, r);
                fish.replace(i, new int[]{r, c, s, d, z});
                if (tmpNearest[c] >= r) tmpNearest[c] = r;
            } else {
                fish.remove(i);
            }
        }
        for (int i = 0; i < R; i ++) {
            map[i] = tmp[i].clone();
        }
        nearest = tmpNearest.clone();
    }

    public static void getNearest(int column, int cur) {
        if (nearest[column] >= cur) nearest[column] = cur;
    }
}
