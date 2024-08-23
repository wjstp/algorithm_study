import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, answer1, answer2, answer3;
    static int[] arr;
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer stz = new StringTokenizer(br.readLine());
        arr = new int[N];
        for (int i = 0; i < N; i ++) {
            arr[i] = Integer.parseInt(stz.nextToken());
        }
        Arrays.sort(arr);   // 정렬 후
        long minValue = Long.MAX_VALUE;
        // for문으로 돌면서 하나 찾기
        for (int i = 0; i < N; i ++) {
            int cur = arr[i];
            int start = 0;  // 자기 다음부터 보면 됨
            int end = N - 1;
            if (minValue == 0) break;
            while(start < end) {
                if (start == i) start ++;
                if (end == i) end --;
                long tmp = (long)cur + (long)arr[start] + (long)arr[end];
                if (Math.abs(tmp) <= minValue) {
                    minValue = Math.abs(tmp);   // 최소값 항상 절대값으로 관리
                    answer1 = cur;
                    answer2 = arr[start];
                    answer3 = arr[end];
                    if (tmp == 0) break;
                }
                if (tmp > 0) {
                    end -- ;
                    if (i == end) end --;
                } else if (tmp < 0) {
                    start ++;
                    if (i == start) start ++;
                }
            }
        }
        int[] answers = new int[] {answer1, answer2, answer3};
        Arrays.sort(answers);
        for (int i = 0; i< 3; i ++) {
            System.out.print(answers[i] + " ");
        }
    }
}
