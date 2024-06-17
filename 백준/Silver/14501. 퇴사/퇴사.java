import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 미친 계속 최대 일수 일때 최대 수익 구하고 ㅇ있었음 아오
public class Main {
    static int N, answer;  // 일수, 최대 이익
    static int[][] arr ;

    public static void main(String args[]) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer stz;
        arr = new int[N][2];
        for (int i = 0; i < N ; i ++) {
            stz = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(stz.nextToken());
            arr[i][1] = Integer.parseInt(stz.nextToken());

        }
        answer = 0;
        dfs(0, 0, 0);
        System.out.println(answer);
    }
    public static void dfs(int start, int profit, int cnt) {
        answer = Math.max(answer, profit);
        for (int i = start ; i < N ; i ++) {
            if (i + arr[i][0] <= N) {
                dfs(i + arr[i][0], profit + arr[i][1], cnt + 1);
            }
        }
    }
}
