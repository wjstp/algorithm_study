import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M, answer;
    static int[] parents;
    static HashSet<Integer> truth;
    static List<Integer>[] parties;
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(br.readLine());
        N = Integer.parseInt(stz.nextToken());
        M = Integer.parseInt(stz.nextToken());
        parents = new int[N + 1];   // 1번부터..
        for (int i = 1; i < N + 1; i ++) {
            parents[i] = i;
        }
        // 진실을 아는 사람의 수와 사람들 - 그룹으로 만들기
        stz = new StringTokenizer(br.readLine());
        int first = Integer.parseInt(stz.nextToken());
        truth = new HashSet<>();
        while(stz.hasMoreTokens()) {
            truth.add(Integer.parseInt(stz.nextToken()));
        }
        parties = new List[M];
        for (int i = 0; i < M; i ++) {
            stz = new StringTokenizer(br.readLine());
            int tmp = Integer.parseInt(stz.nextToken());
            parties[i] = new ArrayList<>();
            for (int j = 0; j < tmp; j ++) {
                parties[i].add(Integer.parseInt(stz.nextToken()));
            }
            // 같이 그룹으로 묶어줌
            for (int j = 1; j < tmp; j ++) {
                union(parties[i].get(j), parties[i].get(j - 1));
            }
        }
        for (int i = 0; i < M; i ++) {
            boolean talk = true;
            for (int j = 0; j < parties[i].size(); j ++) {
                if (truth.contains(findSet(parties[i].get(j)))) {
                    talk = false;
                    break;
                }
            }
            if (talk) answer ++;
        }
        System.out.println(answer);
    }
    // 합치기
    public static void union(int a, int b) {
        int aRoot = findSet(a);
        int bRoot = findSet(b);
        if (aRoot == bRoot) return;
        if (aRoot < bRoot) {
            parents[bRoot] = aRoot;
            if (truth.contains(bRoot)) truth.add(aRoot);
        }else {
            parents[aRoot] = bRoot;
            if (truth.contains(aRoot)) truth.add(bRoot);
        }
    }
    // 집합 찾기
    public static int findSet(int node) {
        // 자기 자신이라면..
        if(parents[node] == node) {
            return node;
        }
        parents[node] = findSet(parents[node]);
        return parents[node];
    }

}
