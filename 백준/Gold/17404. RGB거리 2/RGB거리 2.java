import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, answer;
    static int[][] cost;
    static int[][][] dp; // dp[i][j][k] : 첫번째에 i색 골랐고, j번쨰에 k색을 고른 비용중 최소값

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer stz;
        cost = new int[N][3];
        for (int i = 0; i < N; i++) {
            stz = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                cost[i][j] = Integer.parseInt(stz.nextToken());
            }
        }
        dp = new int[3][N][3];
        answer = Integer.MAX_VALUE;
        for (int i = 0; i < 3; i++) {
            dp[i][0][i] = cost[0][i];
            // 2행부터
            for (int j = 1; j < N; j++) {
                for (int k = 0; k < 3; k++) {
                    int tmp = 0;
                    if (j == 1) {
                        if (k == i) {
                            dp[i][j][k] = Integer.MAX_VALUE;
                            continue;
                        } else {
                            tmp = dp[i][j - 1][i];
                        }
                    } else if (j == N - 1 && k == i) {
                            dp[i][j][k] = Integer.MAX_VALUE;
                            continue;
                    } else {
                        switch (k) {
                            case 0:
                                tmp = Math.min(dp[i][j - 1][k + 1], dp[i][j - 1][k + 2]);
                                break;
                            case 1:
                                tmp = Math.min(dp[i][j - 1][k - 1], dp[i][j - 1][k + 1]);
                                break;
                            case 2:
                                tmp = Math.min(dp[i][j - 1][k - 2], dp[i][j - 1][k - 1]);
                        }
                    }
                    dp[i][j][k] = cost[j][k] + tmp;
                    if (j == N - 1) answer = Math.min(answer, dp[i][j][k]);

                }
            }
        }

        System.out.println(answer);
    }
}
