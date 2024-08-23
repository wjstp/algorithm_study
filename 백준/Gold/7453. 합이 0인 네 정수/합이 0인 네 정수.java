import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static long answer;
    static int[][] arrs;
    static long[] comb1;
    static long[] comb2;

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arrs = new int[4][N];
        StringTokenizer stz;
        for (int i = 0; i < N; i ++) {
            stz = new StringTokenizer(br.readLine());
            arrs[0][i] = Integer.parseInt(stz.nextToken());
            arrs[1][i] = Integer.parseInt(stz.nextToken());
            arrs[2][i] = Integer.parseInt(stz.nextToken());
            arrs[3][i] = Integer.parseInt(stz.nextToken());
        }
        // 1, 2번째 배열과 3, 4번쨰 배열끼리 조합을 만듦 - n ^2
        comb1 = new long[N * N];
        comb2 = new long[N * N];
        for (int i = 0; i < N; i ++) {
            for (int j = 0; j < N; j ++) {
                comb1[i * N + j] = arrs[0][i] + arrs[1][j];
                comb2[i * N + j] = arrs[2][i] + arrs[3][j];
            }
        }
        // 각각 정렬 후 0이 되는 조합 찾기
        Arrays.sort(comb1);
        Arrays.sort(comb2);

        int p2 = comb2.length - 1;
        int p1 = 0;
        while (p1 < comb1.length){
            int cnt = 0;    // 이번 턴에서 0이 되는 수
            while (p2 >= 0) {
                long cur = comb1[p1] + comb2[p2];
                if (cur < 0) break;
                if (cur == 0) {
                    while (cur == 0) {
                        cnt ++;
                        if (p2 == 0) break;
                        p2 --;
                        cur = comb1[p1] + comb2[p2];
                    }
                    break;
                }
                p2 --;
            }
            answer += cnt;
            while (p1 < comb1.length - 1 && comb1[p1] == comb1[p1 + 1]) {
                p1 ++;
                answer += cnt;
            }
            p1 ++;
        }

        System.out.println(answer);
    }
}
