import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M, K, startIdx, treeSize;
    static int MOD = 1000000007;
    static int[] arr;
    static long[] tree;
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(br.readLine());
        N = Integer.parseInt(stz.nextToken());
        M = Integer.parseInt(stz.nextToken());
        K = Integer.parseInt(stz.nextToken());
        arr = new int[N];
        for (int i = 0; i < N; i ++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        getTree();
        for (int i = 0; i < M + K; i ++) {
            stz = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(stz.nextToken());
            int b = Integer.parseInt(stz.nextToken());
            int c = Integer.parseInt(stz.nextToken());
            if (a == 1) {
                changeNum(b - 1, c);    // b 번쨰 수를 c로 바꿈
            } else {
                System.out.println(getProduct(Math.min(b - 1, c - 1), Math.max(b - 1, c - 1)));   // b부터 c까지의 곱을 구함.
            }
        }
    }

    public static void getTree() {
        // tree 전체 사이즈
        treeSize = 1;
        while (true) {
            treeSize *= 2;
            if (treeSize >= N) {
                startIdx = treeSize;
                treeSize *= 2;
                break;
            }
        }
        tree = new long[treeSize];
        Arrays.fill(tree, 1);   // 입력으로 주어지는 수가 0보다 크므로 일단 1로 다 채워둠
        for (int i = 0; i < N; i ++) {
            tree[i + startIdx] = arr[i];
        }
        // 브랜치, 루트 노드 채우기
        for (int i = treeSize - 1; i > 1 ; i --) {
            tree[i / 2] = (tree[i / 2] * tree[i]) % MOD;
        }
    }

    public static void changeNum(int b, int c) {
        int idx = startIdx + b;
        tree[idx] = c;
        while (idx > 1) {
            idx /= 2;
            // 부모노드가 계속 바뀔지 체크
            if (tree[idx] == 0 && (tree[idx * 2] == 0 || tree[idx * 2 + 1] == 0)) {
                break;  // 여전히 0
            }
            tree[idx] = (tree[idx * 2] * tree[idx * 2 + 1]) % MOD;
        }
    }

    public static long getProduct(int min, int max) {
        int start = startIdx + min;
        int end = startIdx + max;
        long result = 1;
        while (start <= end) {
            if (start % 2 == 1) {
                result = (result * tree[start]) % MOD;
                start ++;
            }
            if (end % 2 == 0) {
                result = (result * tree[end]) % MOD;
                end --;
            }
            start /= 2;
            end /= 2;
        }
        return result;
    }
}
