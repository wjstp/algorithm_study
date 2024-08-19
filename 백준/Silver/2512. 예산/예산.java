import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M, answer;
    static int[] arr;
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        StringTokenizer stz = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i ++) {
            arr[i] = Integer.parseInt(stz.nextToken());
        }
        M = Integer.parseInt(br.readLine());
        binarySearch();
        System.out.println(answer);
    }

    public static void binarySearch() {
        int start = 0;
        int end = M;
        int mid;
        while (start < end) {
            mid = (start + end) / 2;
            int result = check(mid);
            if (result > 0) {
                start = mid + 1;
                answer = result;
            } else {
                end = mid;
            }
        }
    }

    public static int check(int limit) {
        int total = 0;
        int max = 0;
        for (int i = 0; i < N; i ++) {
            max = Math.max(max, Math.min(limit, arr[i]));
            total += Math.min(limit, arr[i]);
        }
        if (total <= M) {
            return max;
        }
        return -1;
    }
}
