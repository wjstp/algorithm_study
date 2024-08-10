import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static boolean flag;
    static int[] win;
    static int[] draw;
    static int[] lose;
    static List<int[]> pairs;

    public static void main(String args[]) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        getCombination();
        for (int t = 0; t < 4; t ++) {
            win = new int[6]; draw = new int[6]; lose = new int[6];
            StringTokenizer stz = new StringTokenizer(br.readLine());
            boolean valid = true;
            for (int i = 0; i < 6; i ++) {
                win[i] = Integer.parseInt(stz.nextToken());
                draw[i] = Integer.parseInt(stz.nextToken());
                lose[i] = Integer.parseInt(stz.nextToken());
                // 너무 여러개면 15개 넘어서까지 경기할 수 있음.. 총 승패표의 총점을 체크해야함
                if (win[i] + draw[i] + lose[i] != 5) valid = false;
            }
            if (valid) dfs(0);
            if (!flag) System.out.print(0 + " ");
            flag = false;
        }
    }
    public static void getCombination() {
        pairs = new ArrayList<>(); // {{1, 2}, {1, 3}, ...} 6C2 = 15
        for (int i = 0; i < 6; i ++) {
            for (int j = i + 1; j < 6; j ++) {
                pairs.add(new int[]{i, j});
            }
        }
    }

    public static void dfs(int cnt) {
        if (flag) return;
        if (cnt == 15) {
            // 모든 경기를 다 돌았을 때
            flag = true;
            System.out.print(1 + " ");
            return;
        }
        int home = pairs.get(cnt)[0];
        int away = pairs.get(cnt)[1];
        // 가능한 조합을 다 체크한다.
        if (win[home] > 0 && lose[away] > 0) {
            win[home] --;
            lose[away] --;
            dfs(cnt + 1);
            win[home] ++;
            lose[away] ++;
        }
        if (lose[home] > 0 && win[away] > 0) {
            lose[home] --;
            win[away] --;
            dfs(cnt + 1);
            lose[home] ++;
            win[away] ++;
        }
        if (draw[home] > 0 && draw[away] > 0) {
            draw[home] --;
            draw[away] --;
            dfs(cnt + 1);
            draw[home] ++;
            draw[away] ++;
        }
        // 이 세 조건 모두에 부합하지 않으면 둘이 경기를 할 수 없다는 뜻 -> cnt가 15가 되기 전에 return됨
    }
}
