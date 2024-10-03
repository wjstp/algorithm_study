import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {

    static class TrieNode{
        Map<Character, TrieNode> childNode = new HashMap<>();
        boolean terminal;
        TrieNode () {}
        public void add(String word) {
            TrieNode trieNode = this;
            for (int i = 0; i < word.length(); i ++) {
                char c = word.charAt(i);
                trieNode.childNode.putIfAbsent(c, new TrieNode());
                trieNode = trieNode.childNode.get(c);
                if (i == word.length() - 1) {
                    trieNode.terminal = true;   // 마지막 문자에 terminal 처리
                    return;
                }
            }
        }
        public boolean contains(String word) {
            TrieNode trieNode = this;
            for (int i = 0; i < word.length(); i ++) {
                char c = word.charAt(i);
                TrieNode node = trieNode.childNode.get(c);
                if (node == null) {
                    return false;
                }
                trieNode = node;
            }
            // 난 끝났는데 거기서 더 가는게 있는 경우
            if (trieNode.childNode.isEmpty()) {
                return false;
            }
            
            return true;
        }
    }
    static int t, n;
    static String[] numbers;
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < t; tc ++) {
            n = Integer.parseInt(br.readLine());
            numbers = new String[n];
            TrieNode trieNode = new TrieNode();
            for (int i = 0; i < n; i ++) {
                numbers[i] = br.readLine();
                trieNode.add(numbers[i]);
            }
            boolean isConsistent = true;
            for (int i = 0; i < n; i ++) {
                if (trieNode.contains(numbers[i])) {
                    isConsistent = false;
                    break;
                }
            }
            System.out.println(isConsistent? "YES" : "NO");
        }
    }
}
