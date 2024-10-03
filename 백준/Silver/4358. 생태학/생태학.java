import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int total;
    static class TrieNode {
        Map<Character, TrieNode> map = new HashMap<>();
        int terminal;
        String word;

        TrieNode() {};
        public void add(String word) {
            TrieNode trieNode = this;
            for (int i = 0; i < word.length(); i ++) {
                char c = word.charAt(i);
                if (!trieNode.map.containsKey(c)) trieNode.map.put(c, new TrieNode());
                trieNode = trieNode.map.get(c);
                if (i == word.length() - 1) {
                    trieNode.terminal ++ ; // 마지막 노드 true 처리 대신 개수 카운트
                    trieNode.word = word;
                    return;
                }
            }
        }
        public void print() {
            List<Character> keys = new ArrayList<>(this.map.keySet());
            Collections.sort(keys);
            if (this.terminal != 0) System.out.printf("%s %.4f\n", this.word, (this.terminal / (double) total) * 100); // 소수점 표기
            for (var key : keys) {
                this.map.get(key).print();
            }
        }
    }
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        TrieNode trieNode = new TrieNode();
        while((line = br.readLine()) != null) { // 입력 개수 미정일 때 -> ctrl + d
            trieNode.add(line);
            total ++;
        }
        trieNode.print();
    }
}
