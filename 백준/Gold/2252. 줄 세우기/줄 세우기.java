import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M;
    static List<Integer>[] adjList;
    static int[] inDegree;

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(br.readLine());
        N = Integer.parseInt(stz.nextToken());
        M = Integer.parseInt(stz.nextToken());

        // 인접 리스트, 진입차수
        adjList = new List[1 + N]; // 1번 노드부터
        for (int i = 0; i < 1 + N; i ++) {
            List<Integer> tmp = new ArrayList<>();
            adjList[i] = tmp;
        }
        inDegree = new int[N + 1];
        for (int i = 0 ; i < M; i ++) {
            stz = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(stz.nextToken());
            int B = Integer.parseInt(stz.nextToken());
            adjList[A].add(B);
            inDegree[B] += 1;
        }

        topologicalSorting();
    }
    public static void topologicalSorting() {
        Queue<Integer> q = new LinkedList<>();
        // 진입차수가 0인 것부터 큐에 삽입
        for(int i = 1; i < N + 1; i ++)
            if (inDegree[i] == 0) q.add(i);

        // bfsq로 순회하면서 진입차수가 0인 것만 큐에 삽입한다.
        while(!q.isEmpty()) {
            int cur = q.poll();
            System.out.print(cur + " ");
            for (var adj : adjList[cur]) {
                // 진입할 때 진입차수를 하나 뺀다.
                inDegree[adj] -- ;
                if (inDegree[adj] == 0) {
                    q.add(adj);

                }
            }
        }
    }
}
