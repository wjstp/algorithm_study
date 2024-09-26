import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;

public class Main {
    static int[][] map, answer;
    static boolean flag;
    static HashSet<Integer>[] colCheck;
    static HashSet<Integer>[] rowCheck;
    static HashSet<Integer>[] byCheck;
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        map = new int[9][9];
        byCheck = new HashSet[9];
        colCheck = new HashSet[9];
        rowCheck = new HashSet[9];
        for (int i = 0; i < 9; i ++) {
            byCheck[i] = new HashSet<>();
            colCheck[i] = new HashSet<>();
            rowCheck[i] = new HashSet<>();
        }
        for (int r = 0; r < 9; r ++) {
            String line = br.readLine();
            for (int c = 0; c < 9; c ++) {
                int x = Integer.parseInt(String.valueOf(line.charAt(c)));
                map[r][c] = x;
                if (x != 0) saveCheck(r, c, x);
            }
        }
        answer = new int[9][9];
        dfs(0, 0);
        for (int r = 0; r < 9; r ++) {
            for (int c = 0; c < 9; c ++) {
                System.out.print(answer[r][c]);
            }
            System.out.println();
        }
    }
    public static void dfs(int r, int c) {
        if (flag) return;
        if (r > 8) {
            flag = true;
            for (int i = 0; i < 9; i ++) answer[i] = map[i].clone();
            return;
        }
        if (map[r][c] != 0) {
            dfs(c + 1 == 9? r + 1 : r, c + 1 == 9 ? 0 : c + 1);
            return;
        }
        // 0인 경우만 체크
        for (int i = 1; i <= 9; i ++) {
            if (colCheck[c].contains(i) || rowCheck[r].contains(i) || byCheck[getBy(r, c)].contains(i)) continue;
            saveCheck(r, c, i);
            map[r][c] = i;
            dfs(c + 1 == 9? r + 1 : r, c + 1 == 9 ? 0 : c + 1);
            colCheck[c].remove(i);
            rowCheck[r].remove(i);
            byCheck[getBy(r, c)].remove(i);
            map[r][c] = 0;
        }
    }

    public static void saveCheck(int r, int c, int x) {
        colCheck[c].add(x);
        rowCheck[r].add(x);
        // 뭉탱이 체크.. 이거 나중에 코드 좀 바꿀 것
        byCheck[getBy(r, c)].add(x);
    }
    public static int getBy(int r, int c) {
        if (r < 3) {
            if (c < 3) {
                return 0;
            } else if (c < 6) {
                return 1;
            } else {
                return 2;
            }
        } else if (r < 6) {
            if (c < 3) {
                return 3;
            } else if (c < 6) {
                return 4;
            } else {
                return 5;
            }
        } else {
            if (c < 3) {
                return 6;
            } else if (c < 6) {
                return 7;
            } else {
                return 8;
            }
        }
    }
}
