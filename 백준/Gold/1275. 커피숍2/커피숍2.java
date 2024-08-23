import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, Q, x, y, a, b, startIdx;
    static int[] arr;
    static long[] tree; // 합이 int 범위 넘어간다.
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(br.readLine());
        N = Integer.parseInt(stz.nextToken());
        Q = Integer.parseInt(stz.nextToken());
        arr = new int[N];
        stz = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i ++) {
            arr[i] = Integer.parseInt(stz.nextToken());
        }

        makeTree();
        for (int tc = 0; tc < Q; tc ++) {
            stz = new StringTokenizer(br.readLine());
            x = Integer.parseInt(stz.nextToken()) - 1;
            y = Integer.parseInt(stz.nextToken()) - 1;
            a = Integer.parseInt(stz.nextToken()) - 1;
            b = Integer.parseInt(stz.nextToken());
            // x ~ y 값 구하기
            System.out.println(getSum(Math.min(x, y), Math.max(x, y)));
            // a번쨰 수를 b로 바꾸어라
            changeNum(a, b);
        }
    }
    public static void makeTree() {
        // 트리 배열의 수 : 리프 노드의 수 2^3 -> 전체 노드의 수 2^4, k는 리프 노드의 승수
        int tmp = 1, k = 0;
        while (tmp < N) {
            tmp *= 2;
            k ++;
        }
        tree = new long[(int) Math.pow(2, k + 1)];

        // 리프 노드 채우기
        startIdx = (int) Math.pow(2, k);
        for (int i = 0; i < arr.length; i ++) {
            tree[startIdx + i] = arr[i];
        }
        // 브랜치, 루트 노드 채우기
        int idx = tree.length - 1;
        while (idx > 1) {
            tree[idx / 2] = tree[idx] + tree[idx - 1];
            idx -= 2;
        }
    }

    public static long getSum(int x, int y) {
        int start = startIdx + x;
        int end = startIdx + y;
        long sum = 0;
        while (start <= end) {
            if (start % 2 == 1) {
                sum += tree[start];
                start ++;
            }
            if (end % 2 == 0) {
                sum += tree[end];
                end --;
            }
            start = start / 2;
            end = end / 2;
        }
        return sum;
    }

    public static void changeNum(int a, int b) {
        int idx = a + startIdx;
        long add = b - tree[idx];   // 2^31 - (- 2 ^31)
        while (idx > 0) {
            tree[idx] += add;
            idx /= 2;
        }
    }

}
