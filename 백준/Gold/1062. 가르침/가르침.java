import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, K, answer;
    static String[] words;

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(br.readLine());
        N = Integer.parseInt(stz.nextToken()); // 주어진 단어 수
        K = Integer.parseInt(stz.nextToken()); // 배울 수 있는 글자 수

        if(K < 5 || K == 26) {
            answer = K < 5? 0 : N;  // 배울 수 있는 숫자가 크면 굳이 필요 없다.
        } else {
            words = new String[N];
            for (int i = 0; i < N; i ++)
                words[i] = br.readLine().replaceAll("anta|tica", "");
            int visit = 0;
            visit += 1 << ('a' - 'a');
            visit += 1 << ('n' - 'a');
            visit += 1 << ('t' - 'a');
            visit += 1 << ('c' - 'a');
            visit += 1 << ('i' - 'a');
            dfs(0, 0, visit);
        }

        System.out.println(answer);
    }

    public static void dfs(int idx, int cnt, int visit) {// idx, 읽을 줄 아는 글자수
        if (cnt == K - 5) {
            int tmp = 0;
            for (int i = 0; i < N; i ++) {
                boolean readable = true;
                for (int j = 0; j < words[i].length() ; j ++) {
                    if ((visit & (1 << (words[i].charAt(j) - 'a'))) == 0) {
                        readable = false;
                        break;
                    }
                }
                if (readable) tmp ++ ;

            }
            answer = Math.max(tmp, answer);
            return;
        }
        for (int i = idx; i < 26; i ++) {
            if ((visit & (1 << i)) == 0)
                dfs(i + 1, cnt + 1, visit | 1 << i);
        }
    }



}
