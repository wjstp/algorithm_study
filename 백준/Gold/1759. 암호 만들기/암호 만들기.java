import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.StringTokenizer;


public class Main {
    static int L, C;
    static String[] letters;

    static HashSet<String> vowels = new HashSet<>(List.of("a", "e", "i", "o", "u"));
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(br.readLine());
        L = Integer.parseInt(stz.nextToken()); // 만들려는 문자열의 길이
        C = Integer.parseInt(stz.nextToken()); // 주어진 문자의 개수

        letters = new String[C];
        stz = new StringTokenizer(br.readLine());
        for (int i = 0; i < C ; i ++) {
            letters[i] = stz.nextToken();
        }
        Arrays.sort(letters);
        dfs(0, "", 0, 0);
    }
    public static void dfs(int start, String word, int vowel, int consonant) {
        if (word.length() == L) {
            if (vowel >= 1 && consonant >= 2) {
                System.out.println(word);
            }
            return;
        }

        for (int i = start; i < C; i ++) {
            dfs(i + 1, word + letters[i],
                    vowel + (vowels.contains(letters[i])? 1 : 0),
                    consonant + (vowels.contains(letters[i])? 0 : 1));

        }
    }
}
