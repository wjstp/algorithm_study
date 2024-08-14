import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
    static int N, K;
    static long answer;
    static int[] nums;
    static HashMap<Integer, Integer> prefixCount;   // {2:2} 누적합 2인게 2개
    public static void main(String args[]) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(br.readLine());
        N = Integer.parseInt(stz.nextToken());
        K = Integer.parseInt(stz.nextToken());
        nums = new int[N];
        stz = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i ++) {
            nums[i] = Integer.parseInt(stz.nextToken());
            if (i > 0) nums[i] += nums[i - 1];
        }
        prefixCount = new HashMap<>();
        for (int x = 0; x < N; x ++) {
            // 0부터 누적합으로 K가 되는 경우
            if (nums[x] == K)  answer ++;
            // 1부터 누적합으로 K가 되는 경우
            if (x > 0) {
                answer += prefixCount.getOrDefault(nums[x] - K, 0);
            }
            prefixCount.put(nums[x], prefixCount.getOrDefault(nums[x], 0) + 1);
        }
        System.out.println(answer);
    }
}
