import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

// 시간 복잡도 나올 수 있는 알파벳 수 최대 10.. 거기에 들어갈 수 있는 숫자 조합 10!..대충 300만
// 여기에 숫자들 합 구하면서 10 * 10 -> 3억
// 시간복잡도 확인해볼 것
// 이거 Map으로 풀어도 시간초과남. 다음 번엔 그리디로 최적화할 것
public class Main {
    static int N, letter, answer;
    static String[] words;
    static int[] letterMap; // 단어에서 글자가 어떤 수를 가지는지 바로 접근할 용도
    static List<Integer> letters;  // 어떤 키들이 있는지 기록하는 용도. dfs에서 키를 순서대롲 점근 어떤 글자들이 있는지 인덱스에서 맵으로 접근할 용도
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        words = new String[N];
        letterMap = new int[26];
        letters = new ArrayList<>();
        for (int i = 0; i < N; i ++) {
            words[i] = br.readLine();
            for (int j = 0; j < words[i].length(); j ++) {
                if (letterMap[words[i].charAt(j) - 'A'] == 0) {
                    letterMap[words[i].charAt(j) - 'A'] = 1;
                    letter ++ ;
                    letters.add(words[i].charAt(j) - 'A');
                }
            }
        }
        dfs(0, 0);
        System.out.println(answer);
    }

    public static void dfs(int letterIdx, int check) {
        if (letterIdx == letter) {
            answer = Math.max(answer, getSum());
        }
        for (int i = 9; i > 9 - letter; i --) {
            if ((check & 1 << i) == 1 << i) continue;
            check |= 1 << i;
            letterMap[letters.get(letterIdx)] = i;
            dfs(letterIdx + 1, check);
            check &= ~(1 << i);
        }
    }
    public static int getSum() {
        int sum = 0;
        for (int i = 0; i < N; i ++) {
            int ten = 1;
            for (int j = words[i].length() - 1; j >= 0 ; j --) {
                sum += letterMap[words[i].charAt(j) - 'A'] * ten;
                ten *= 10;
            }
        }
        return sum;
    }
}
