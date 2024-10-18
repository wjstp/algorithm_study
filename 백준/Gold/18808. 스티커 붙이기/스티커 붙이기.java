import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


// 제일 위. 안되면 이동. 아예 안되면 회전
public class Main {
    static int N, M, K, answer; // 세로, 가로,(1~40) 스티커 개수 (1~100)
    static int[][] map;
    static List<Sticker> stickers;
    static class Sticker {
        int r;
        int c;
        List<int[]> locs = new ArrayList<>();

        Sticker(int r, int c, List<int[]> locs) {
            this.r = r;
            this.c = c;
            this.locs = locs;
        }
    }

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(br.readLine());
        N = Integer.parseInt(stz.nextToken());
        M = Integer.parseInt(stz.nextToken());
        K = Integer.parseInt(stz.nextToken());

        stickers = new ArrayList<>();
        for (int i = 0; i < K; i ++) {
            stz = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(stz.nextToken());
            int c = Integer.parseInt(stz.nextToken());
            List<int[]> locs = new ArrayList<>();
            for (int cr = 0; cr < r; cr ++) {
                stz = new StringTokenizer(br.readLine());
                for (int cc = 0; cc < c; cc ++) {
                    if (stz.nextToken().equals("1")) locs.add(new int[]{cr, cc});
                }
            }
            stickers.add(new Sticker(r, c, locs));
        }

        map = new int[N][M];
        for (int i = 0; i < K ; i ++) {
            for (int j = 0; j < 4; j ++) {
                if (fitOrMove(stickers.get(i))) break; // 자리에 맞는게 있다면 break
                rotate(i);
            }
        }
        System.out.println(answer);
    }

    public static boolean fitOrMove(Sticker sticker) {
        int cr = 0; int cc = 0; // 기준 점 - 왼쪽 위
        while (cr + sticker.r <= N && cc + sticker.c <= M) { // r, c 범위가 벗어나면 탈출
            if (fit(cr, cc, sticker)) return true;

            if (cc + 1 + sticker.c > M) {
                cr++;
                cc = 0;
            }
            else cc ++;

        }
        return false;
    }
    public static void rotate(int num) {
        // 회전해서 stickers 배열 값 변경
        Sticker sticker = stickers.get(num);
        List<int[]> tmp = new ArrayList<>();
        for (int i = 0; i < sticker.locs.size(); i ++) {
            tmp.add(new int[]{sticker.locs.get(i)[1], sticker.r - 1 - sticker.locs.get(i)[0]})
;        }

        stickers.set(num, new Sticker(sticker.c, sticker.r, tmp));
    }
    public static boolean fit(int r, int c, Sticker sticker) {
        for (int i = 0; i < sticker.locs.size(); i++) {
            int tr = sticker.locs.get(i)[0] + r;
            int tc = sticker.locs.get(i)[1] + c;
            if (map[tr][tc] == 1) return false;
        }
        // 겹치는게 없다면
        for (int i = 0; i < sticker.locs.size(); i++) {
            int tr = sticker.locs.get(i)[0] + r;
            int tc = sticker.locs.get(i)[1] + c;
            map[tr][tc] = 1;
            answer++;
        }
        return true;
    }
}
