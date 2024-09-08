import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, H;
    static int[] up, down;
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(br.readLine());
        N = Integer.parseInt(stz.nextToken());
        H = Integer.parseInt(stz.nextToken());

        up = new int[H + 1]; // up[i] : i = H - l + 1 . i로 날때 부딪히는 장애물의 수. l보다 큰 장애물의 개수까지 누적합.
        down = new int[H + 1]; // down[i] : i로 날떄 부딪히는 장애물의 수. i보다 큰 장애물의 개수까지 누적합

        for (int i = 0; i < N; i ++) {
            int height = Integer.parseInt(br.readLine());
            if ((i & 1) == 1) {
                up[height] += 1;
            } else {
                down[height] += 1;
            }
        }

        for (int i = H - 1 ; i >= 0 ; i --) {
            up[i] += up[i + 1];
            down[i] += down[i + 1];
        }

        int min = Integer.MAX_VALUE;
        int cnt = 0;
        for (int i = 1; i < H + 1; i ++) {
            int tmp = up[H - i + 1] + down[i];
            if (tmp < min) {
                min = tmp;
                cnt = 1;
            } else if (tmp == min) {
                cnt ++;
            }
        }
        System.out.println(min + " " + cnt);
    }

}
