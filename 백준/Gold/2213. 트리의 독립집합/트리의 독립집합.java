import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[] weight;
    static int[][] memo; // memo[i][0] : i 번 선택 x, i번 선택 ㅇ
    static List<Integer> independentSet;
    static List<Integer>[] adjList;

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz;

        n = Integer.parseInt(br.readLine());
        stz = new StringTokenizer(br.readLine());

        weight = new int[n + 1]; // 1 ~ n
        for (int i = 1; i < n + 1; i ++)
            weight[i] = Integer.parseInt(stz.nextToken());

        adjList = new List[n + 1]; // 1 ~ n
        for (int i = 1; i < n + 1; i ++)
            adjList[i] = new ArrayList<>();
        for (int i = 0; i < n - 1; i ++) {
            stz = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(stz.nextToken());
            int v = Integer.parseInt(stz.nextToken());
            adjList[u].add(v);
            adjList[v].add(u);
        }
        memo = new int[n + 1][2];
        dpByDFS(1, new boolean[n + 1]);

        independentSet = new ArrayList<>();
        if (memo[1][0] < memo[1][1]) {
            System.out.println(memo[1][1]);
            trace(1, 1, new boolean[n + 1]);
        } else {
            System.out.println(memo[1][0]);
            trace(1, 0, new boolean[n + 1]);
        }
        Collections.sort(independentSet);
        for (int i = 0; i < independentSet.size(); i ++) {
            System.out.print(independentSet.get(i) + " ");
        }

    }

    public static void dpByDFS(int cur, boolean[] visit) {
        visit[cur] = true;
        memo[cur][0] = 0;
        memo[cur][1] = weight[cur];

        for (var next : adjList[cur]) {
            if (! visit[next]) {
                dpByDFS(next, visit);
                memo[cur][0] += Math.max(memo[next][1], memo[next][0]); // 선택안한 노드일 떄는, 그 다음을 선택하거나 안할 수 있다.
                memo[cur][1] += memo[next][0];
            }
        }
    }

    public static void trace(int cur, int select, boolean[] visit) {
        visit[cur] = true;

        if (select == 1) {
            independentSet.add(cur);
            for (var next : adjList[cur]) {
                if (visit[next]) continue;
                trace(next, 0, visit); // 무조건 다음 것은 선택 안함
            }
        } else {
            for (var next : adjList[cur]) {
                if (visit[next]) continue;
                if (memo[next][0] < memo[next][1]) {
                    trace(next, 1, visit);
                } else {
                    trace(next, 0, visit);
                }
            }
        }
    }
}
