import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;


public class Main {
    static int T, n;
    static int[] original;
    static int[] inDegree;

    static List<Integer>[] adjList;

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(br.readLine());
        T = Integer.parseInt(stz.nextToken());
        for (int t = 0; t < T; t ++) {
            n = Integer.parseInt(br.readLine());

            original = new int[n + 1];
            stz = new StringTokenizer(br.readLine());
            for(int i = 0; i < n ; i ++)
                original[i] = Integer.parseInt(stz.nextToken()); // 0 ~ n-1

            inDegree = new int[n + 1];
            adjList = new List[n + 1];
            for(int i = 0; i < n ; i ++){
                int team = original[i];
                inDegree[team] = i; // 처음에 진입차수
                adjList[team] = new ArrayList<>();
                for (int j = i + 1; j < n ; j ++) {
                    adjList[team].add(original[j]);
                }
            }

            int m = Integer.parseInt(br.readLine());
            int[] tmp = new int[n + 1];
            // 진입차수 변화 & 인접리스트 기록
            for(int i = 0; i < m ; i ++) {
                stz = new StringTokenizer(br.readLine());
                int A = Integer.parseInt(stz.nextToken());
                int B = Integer.parseInt(stz.nextToken());
                // B의 진입차수가 더 크다면 B의 순위가 A보다 낮은 순위
                if (inDegree[A] < inDegree[B] ) {
                    tmp[B] -- ;
                    tmp[A] ++ ;
                    adjList[B].add(A);
                    for(int j = 0; j < adjList[A].size() ; j ++) {
                        if (adjList[A].get(j) == B) {
                            adjList[A].remove(j);
                        }
                    }
                } else {
                    tmp[B] ++ ;
                    tmp[A] -- ;
                    adjList[A].add(B);
                    for(int j = 0; j < adjList[B].size() ; j ++) {
                        if (adjList[B].get(j) == A) {
                            adjList[B].remove(j);
                        }
                    }
                }
            }
            // 진입차수 갱신
            for (int i = 1 ; i < n + 1; i ++) {
                inDegree[i] += tmp[i];
            }
            topologicalSorting();
        }
    }

    public static void topologicalSorting() {
        Queue<Integer> q = new LinkedList<>();
        // 진입차수가 0인것부터 큐에 넣는다.
        for (int i = 1; i < n + 1; i ++) {
            if (inDegree[i] == 0) {
                q.add(i);
            }
        }
        List<Integer> newRanking = new ArrayList<>();
        while (! q.isEmpty()) {
            int cur = q.poll();
            newRanking.add(cur);
            for(var adj : adjList[cur]) {
                inDegree[adj] -- ;
                if (inDegree[adj] == 0) {
                    q.add(adj);
                }
            }
        }

        if (newRanking.size() == n) {
            for (int i = 0; i < n ; i ++) {
                System.out.print(newRanking.get(i) + " ");
            }
            System.out.println();
        }else {
            System.out.println("IMPOSSIBLE");
        }

    }
}
