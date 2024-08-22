import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, answer1, answer2;
    static int[] arr;
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        StringTokenizer stz = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i ++) {
            arr[i] = Integer.parseInt(stz.nextToken());
        }
        int start = 0, end = N - 1;
        long min = Long.MAX_VALUE;    // 항상 양수로 유지
        while (start < end) {
            long cur = arr[start] + arr[end];
            if (Math.abs(cur) < min) {
                min = Math.abs(cur);
                answer1 = arr[start];
                answer2 = arr[end];
            }
            if (cur > 0) {
                end --;
                continue;
            }
            if (cur < 0) {
                start ++;
                continue;
            }
            if (cur == 0) {
                answer1 = arr[start];
                answer2 = arr[end];
                break;
            }
        }
        System.out.println(answer1 + " "+ answer2);
    }
}
