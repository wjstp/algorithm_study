import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, answer;
    static int[] arr;
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        arr = new int[N];
        StringTokenizer stz = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i ++) {
            arr[i] = Integer.parseInt(stz.nextToken());
        }
        Arrays.sort(arr);

        for (int i  = N - 1; i >= 0; i --) {
            // 이분탐색 - 그냥 처음부터 양쪽끝에서..
            checkGood(0, N - 1, i);
        }
        System.out.println(answer);
    }
    public static void checkGood(int start, int end, int val) {
        while (start < end) {
            if (start == val) start ++;
            if (end == val) end -- ;
            if (arr[start] + arr[end] > arr[val]) {
                end -- ;
                if (end == val) end --;
            } else if (arr[start] + arr[end] < arr[val]) {
                start ++ ;
                if (start == val) start ++ ;
            } else {    // 좋은 수
                answer ++;
                return;
            }
        }
    }
}

