import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main {
    static int N, alphabet, answer;
    static String[] words;

    static int[] wordMath;
    static List<Integer> letters;
    static int visit;
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        words = new String[N];
        wordMath = new int[26];    // a~z
        letters = new ArrayList<>();
        for (int i = 0; i < N; i ++) {
            words[i] = br.readLine();
            for (int j = 0; j < words[i].length() ; j ++) {
                if (letters.contains(words[i].charAt(j) - 'A')) continue;
                letters.add(words[i].charAt(j) - 'A');
                alphabet ++ ;
            }
        }
        dfs(0);
        System.out.println(answer);
    }
    public static void dfs(int cur) {
        if (cur == alphabet) {
            answer = Math.max(answer, getSum());
            return;
        }
        for (int i = 9 ; i >= 10 - alphabet ; i--) {    // 10개 다 돌 필요없음
            if ((visit & (1 << i)) == 0) {
                wordMath[letters.get(cur)] = i;
                visit |= 1 << i;
                dfs(cur + 1);
                visit &= ~(1 << i);
            }
        }
    }

    public static int getSum() {
        int sum = 0;
        for (int i = 0; i < N; i ++) {
            int len = words[i].length();
            int pow = 1;
            for (int j = len - 1; j >= 0; j --) {
                sum += wordMath[words[i].charAt(j) - 'A'] * pow;
                pow *= 10;
            }
        }
        return sum;
    }
}
