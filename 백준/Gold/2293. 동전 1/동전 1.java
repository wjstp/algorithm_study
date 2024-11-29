import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n, k;
    static int[] dp;
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(br.readLine());
        n = Integer.parseInt(stz.nextToken());
        k = Integer.parseInt(stz.nextToken());
        dp = new int[k + 1];
        dp[0] = 1;
        for (int i = 0; i < n; i ++ ) {
            int n = Integer.parseInt(br.readLine());
            for (int j = n; j < k + 1; j ++) {
                dp[j] += dp[j - n];
            }
        }
        System.out.println(dp[k]);
    }
}
