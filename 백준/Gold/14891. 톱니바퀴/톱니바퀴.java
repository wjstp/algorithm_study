import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static String[] wheels;
    static int[] center;
    static boolean[] difference;
    static int K;

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        wheels = new String[4];
        for (int i = 0; i < 4; i ++)
            wheels[i] = br.readLine();
        center = new int[4];
        difference = new boolean[3];

        K = Integer.parseInt(br.readLine());
        StringTokenizer stz;
        for(int i = 0; i < K; i ++) {
            stz = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(stz.nextToken()) - 1;
            int dir = Integer.parseInt(stz.nextToken());
            // 연결된 부분 다른 거 체크
            checkDifference();
            // 움직일거 먼저 이동
            center[num] = getCenter(center[num], dir);
            Queue<int[]> q = new LinkedList<>();
            int[] check = new int[4];
            q.add(new int[]{num, dir});
            check[num]  =1;
            // bfs로 다른거 체크했던거에 속하는 것들만 회전 - bfs에는 회전한 것들만 담는다.
            while (!q.isEmpty()) {
                int[] current = q.poll();
                int cur = current[0], cdir = current[1];
                for (int t : new int[]{-1, 1}) {
                    int next = cur + t;
                    if (next < 0 || next >= 4 || check[next] == 1) continue;
                    if (difference[t == -1? cur - 1 : cur]) {
                        center[next] = getCenter(center[next], cdir * -1);
                        q.add(new int[]{next, cdir * -1});
                        check[next] = 1;
                    }
                }
            }
        }
        int answer = 0;
        for (int i = 0; i < 4; i ++) {
            answer += (wheels[i].charAt(center[i]) - '0') * Math.pow(2, i);
        }
        System.out.println(answer);
    }
    public static void checkDifference() {
        for(int i = 0; i < 3; i ++) {
            char left = wheels[i].charAt((center[i] + 2) % 8);
            char right = wheels[i + 1].charAt((center[i + 1] + 6) % 8);
            difference[i] = right != left; // 다르다면 true
        }
    }
    public static int getCenter(int center, int dir) {
        return dir == 1 ? (center + 7) % 8 : (center + 1) % 8;
    }
}
