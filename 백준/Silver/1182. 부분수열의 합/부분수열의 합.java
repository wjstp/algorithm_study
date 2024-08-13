import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, S, answer;
    static int[] sequence;
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(br.readLine());
        N = Integer.parseInt(stz.nextToken());
        S = Integer.parseInt(stz.nextToken());

        sequence = new int[N];
        stz = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i ++) {
            sequence[i] = Integer.parseInt(stz.nextToken());
        }
        dfs(0, 0);
        System.out.println(answer + (S == 0 ? -1 : 0));
    }

    public static void dfs(int idx, int sum) {
        if (idx == N) {
            if (sum == S) {
                answer ++ ;
            }
            return;
        }
        // 선택을 하거나 안하거나
        dfs(idx + 1, sum);
        dfs(idx + 1, sum + sequence[idx]);
    }
}
