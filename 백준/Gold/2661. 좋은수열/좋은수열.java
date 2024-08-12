import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

public class Main {
    static int N;
    static boolean flag;
    public static void main(String args[]) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        dfs("", 0, "");
    }

    public static void dfs(String before, int depth, String sequence) {
        if (flag) return;
        if (depth == N) {
            flag = true;
            System.out.println(sequence);
            return;
        }
        for (var num : List.of(new String[]{"1", "2", "3"})) {
            if (before.equals(num)) continue;
            if (isBad(sequence + num)) continue;
            dfs(num, depth + 1, sequence + num);
        }
    }

    public static boolean isBad(String sequence) {
        if (N < 3) return false; // 한자리수끼리 겹치는게 업기만 하면됨.
        // 새로 들어올 값만 체크하면 된다.
        int len = sequence.length();
        for (int i = 1; i < len / 2; i ++) {
            String tmp1 = sequence.substring(len - 1 - i, len); // 멘끝 - i ~ 맨끝
            String tmp2 = sequence.substring(len - 2 - i * 2, len - 1 - i ); // 맨끝앞 - i ~ 맨끝앞
            if (tmp1.equals(tmp2)) return true;
        }
        return false;
    }
}
