import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    static String nums;
    static boolean stop;

    static int[] visit;
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        nums = br.readLine();
        visit = new int[100];   // 길이가 최대 50이므로 두 자리수 이내
        dfs(0, "", 0);
    }
    public static void dfs(int idx, String permutation, int max) {
        if (stop) return;
        if (nums.length() == idx) {
            if (checkPermutation(max)) {
                System.out.println(permutation);
                stop = true;
            }
            return;
        }
        // 하나만
        if (idx < nums.length() && visit[Integer.parseInt(nums.substring(idx, idx + 1))] == 0) {
            int num = Integer.parseInt(nums.substring(idx, idx + 1));
            visit[num] = 1;
            dfs(idx + 1, permutation + nums.charAt(idx) + " ", Math.max(max, num));
            visit[num] = 0;
        }
        // 두자리
        if (idx + 1 < nums.length() && visit[Integer.parseInt(nums.substring(idx, idx + 2))] == 0) {
            int num = Integer.parseInt(nums.substring(idx, idx + 2));
            visit[num] = 1;
            dfs(idx + 2, permutation + nums.charAt(idx) + nums.charAt(idx + 1) + " ", Math.max(max, num));
            visit[num] = 0;
        }

    }
    public static boolean checkPermutation(int max) {
        // max값부터 visit 체크해봤을 때 1까지 0이 없어야 함
        for (int i = max; i >= 1; i --) {
            if (visit[i] == 0) return false;
        }
        return true;
    }
}
