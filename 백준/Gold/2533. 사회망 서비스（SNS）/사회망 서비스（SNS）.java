import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] memo;
    static List<Integer>[] adjList;


    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz;

        N = Integer.parseInt(br.readLine());
        adjList = new List[N + 1];
        for (int i = 1; i < N + 1; i ++)
            adjList[i] = new ArrayList<>();
        for (int i = 0; i < N - 1; i ++) {
            stz = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(stz.nextToken());
            int v = Integer.parseInt(stz.nextToken());
            adjList[u].add(v);
            adjList[v].add(u);
        }
        memo = new int[N + 1][2];
        dpByDfs(1, new boolean[N + 1]);
        System.out.println(Math.min(memo[1][0], memo[1][1]));
    }

    public static void dpByDfs(int cur, boolean[] visit) {
        visit[cur] = true;
        memo[cur][0] = 0;
        memo[cur][1] = 1;

        for (var next : adjList[cur]) {
            if (visit[next]) continue;

            dpByDfs(next, visit);
            memo[cur][0] += memo[next][1];
            memo[cur][1] += Math.min(memo[next][0], memo[next][1]); // 얼리어답터면 친구가 얼리어답터든 아니든 친구가 적은 버전으로 합한다.
        }
    }
}
