import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
    static int N, S;
    static long answer;
    static int[] numbers;
    static HashMap<Integer, Integer> total;

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(br.readLine());
        N = Integer.parseInt(stz.nextToken());  // 정수의 개수
        S = Integer.parseInt(stz.nextToken());  // 만들려는 합

        numbers = new int[N];
        stz = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i ++) {
            numbers[i] = Integer.parseInt(stz.nextToken());
        }
        Arrays.sort(numbers);   // 작은 값부터 조회해서 sum보다 커지면 그 다음을 볼 필요가 없도록
        total = new HashMap<>();
        left(0, 0);
        right(N / 2, 0);
        System.out.println(S == 0? answer - 1 : answer);
    }

    public static void left(int cur, int sum) {
        if (cur == N / 2) {
            total.put(sum, total.getOrDefault(sum, 0) + 1);
            return;
        }
        // 고르거나 안고르거나
        left(cur + 1, sum + numbers[cur]);
        left(cur + 1, sum);
    }

    public static void right(int cur, int sum) {
        if (cur == N) {
            if (total.getOrDefault(S - sum, 0) != 0) {
                answer += total.get(S - sum);
            }
            return;
        }
        right(cur + 1, sum + numbers[cur]);
        right(cur + 1, sum);
    }
}
