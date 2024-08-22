import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    static int k, n, question;
    static int[] result, middle, first;
    static int[][] map;
    static boolean flag;
    static String answer;
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        k = Integer.parseInt(br.readLine());    // 사람 수
        n = Integer.parseInt(br.readLine());    // 가로줄 수
        String res = br.readLine();
        result = new int[k];
        for (int i = 0; i < k; i ++) {
            result[i] = res.charAt(i) - 'A' + 1;    // A가 1
        }
        map = new int[n][k - 1];

        for (int i = 0; i < n; i ++) {
            String line = br.readLine();
            for (int j = 0; j < k - 1; j ++) {
                if (line.charAt(j) == '*') {
                    map[i][j] = 0;
                }else if (line.charAt(j) == '-') {
                    map[i][j] = 1;
                } else if (line.charAt(j) == '?') {
                    question = i;
                    break;  // ?인 곳은 0으로 표시.. 어차피 다 채울 거이므로
                }
            }
        }
        answer = "";
        for (int i = 0; i < k - 1; i ++) {
            answer += "x";
        }
        middle = new int[k];
        // 마지막 줄부터 question 라인 전까지 이동
        boolean valid = goLadder(result,n - 1, question + 1); // ?나오기 직전까지 읻동
        if (valid) {
            // 첫 줄부터 question 라인 전까지 이동
            for (int i = 0; i < k ; i ++) {
                int person = i + 1; // A부터
                int cur = i;
                for (int r = 0; r < question ; r ++) {
                    if (cur - 1>= 0 && map[r][cur - 1] == 1) {
                        cur --;
                        continue;
                    }
                    if (cur < k - 1 && map[r][cur] == 1) {
                        cur ++;
                    }
                }
                first[cur] = person;
            }
            dfs(0);
        }
        System.out.println(answer);

    }
    public static boolean goLadder(int[] arr, int start, int end) {
        first = new int[k];
        // start부터 end까지 거꾸로 이동
        for (int i = 0; i < k ; i ++) {
            int person = arr[i];
            int cur = i;
            for (int r = start; r >= end; r --) {
                if (cur - 1>= 0 && map[r][cur - 1] == 1) {
                    cur --;
                    continue;
                }
                if (cur < k - 1 && map[r][cur] == 1) {
                    cur ++;
                }

            }
            if (start == n - 1) {
                if (middle[cur] == 0) {
                    middle[cur] = person;
                } else {
                    return false;   // 겹치는게 있다면 false;
                }
            }else {
                if (first[cur] == 0) {
                    first[cur] = person;
                } else {
                    return false;   // 겹치는게 있다면 false;
                }
            }
        }
        return true;
    }

    public static void dfs(int x) {
        if (flag) return;
        if (x == k - 1) {
            for (int i = 0; i < k ; i ++) {
                int person = middle[i];
                int cur = i;
                if (cur - 1>= 0 && map[question][cur - 1] == 1) {
                    cur --;
                }
                else if (cur < k - 1 && map[question][cur] == 1) {
                    cur ++;
                }
                if (first[cur] != person) {
                    return;
                }
            }
            String tmp = "";
            for (int i = 0; i < k - 1; i ++) {
                if (map[question][i] == 0) {
                    tmp += "*";
                } else {
                    tmp += "-";
                }
            }
            answer = tmp;
            flag = true;
            return;
        }

        map[question][x] = 1;
        dfs(x + 1);
        map[question][x] = 0;
        dfs(x + 1);
    }
}
