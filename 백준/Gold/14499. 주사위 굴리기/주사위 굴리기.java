import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static int N, M, x, y, K;
    static int[][] map;
    static int[] commands;
    public static void main (String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(br.readLine());
        N = Integer.parseInt(stz.nextToken()); // 세로
        M = Integer.parseInt(stz.nextToken()); // 가로
        x = Integer.parseInt(stz.nextToken()); // 주사위 좌표 0 ~ N - 1
        y = Integer.parseInt(stz.nextToken()); // 주사위 자효 0 ~ M - 1
        K = Integer.parseInt(stz.nextToken()); // 명령의 개수

        map = new int[N][M];
        for (int r = 0; r < N; r ++) {
            stz = new StringTokenizer(br.readLine());
            for (int c = 0; c < M; c ++) {
                map[r][c] = Integer.parseInt(stz.nextToken());
            }
        }

        stz = new StringTokenizer(br.readLine());
        commands = new int[K];
        for (int i = 0; i < K; i ++) {
            commands[i] = Integer.parseInt(stz.nextToken());
        }

        int[] dice = new int[6];

        for (int i = 0; i < K; i ++) {
            int command = commands[i];

            boolean flag = false;
            if (command == 1 && y + 1 < M) {
                y ++;
                flag = true;
            } else if (command == 2 && y - 1 >= 0) {
                y--;
                flag = true;
            } else if (command == 3 && x - 1 >= 0) {
                x --;
                flag = true;
            } else if (command == 4 && x + 1 < N) {
                x ++;
                flag = true;
            }
            if (!flag) continue;
            dice = moveDice(command, dice);
            if (map[x][y] == 0) {
                map[x][y] = dice[5];
            } else if (map[x][y] > 0) {
                dice[5] = map[x][y];
                map[x][y] = 0;
            }
            System.out.println(dice[0]);
        }
    }
    public static int[] moveDice (int direction, int[] cur) {
        // 이전 배열 복사
        int[] nextDice = new int[6];
        switch (direction) {
            case 1 :
                nextDice[0] = cur[3];
                nextDice[1] = cur[1];
                nextDice[2] = cur[0];
                nextDice[3] = cur[5];
                nextDice[4] = cur[4];
                nextDice[5] = cur[2];
                break;
            case 2 :
                nextDice[0] = cur[2];
                nextDice[1] = cur[1];
                nextDice[2] = cur[5];
                nextDice[3] = cur[0];
                nextDice[4] = cur[4];
                nextDice[5] = cur[3];
                break;
            case 3 :
                nextDice[0] = cur[4];
                nextDice[1] = cur[0];
                nextDice[2] = cur[2];
                nextDice[3] = cur[3];
                nextDice[4] = cur[5];
                nextDice[5] = cur[1];
                break;
            case 4 :
                nextDice[0] = cur[1];
                nextDice[1] = cur[5];
                nextDice[2] = cur[2];
                nextDice[3] = cur[3];
                nextDice[4] = cur[0];
                nextDice[5] = cur[4];
                break;
        }

        return nextDice;
    }
}
