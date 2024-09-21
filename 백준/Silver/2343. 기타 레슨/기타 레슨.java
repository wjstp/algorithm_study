import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M, answer;
    static int[] playTimes;
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(br.readLine());
        N = Integer.parseInt(stz.nextToken());
        M = Integer.parseInt(stz.nextToken());

        stz = new StringTokenizer(br.readLine());
        playTimes = new int[N];
        for (int i = 0 ; i < N; i ++) {
            playTimes[i] = Integer.parseInt(stz.nextToken());
        }
        binarySearch();
        System.out.println(answer);
    }

    public static void binarySearch() {
        int start = 0;
        int end = 10000 * N; // N이 10만이면 10의 9승 int 범위 이내
        while (start < end) {
            int mid = (start + end) / 2;
            if (isValid(mid)) {
                end = mid;
                answer = mid;
            } else {
                start = mid + 1;
            }
        }
    }

    public static boolean isValid(int x) {
        int tmp = 0;
        int cnt = 1;
        for (int i = 0; i < N; i ++) {
            if (tmp + playTimes[i] <= x) {
                tmp += playTimes[i];
                continue;
            }
            if (cnt + 1 > M || playTimes[i] > x) return false;
            tmp = playTimes[i];
            cnt ++;
        }
        return true;
    }
}
