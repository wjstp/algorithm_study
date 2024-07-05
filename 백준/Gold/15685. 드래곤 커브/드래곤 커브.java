import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N, x, y, d, g;
    static int[] dr = {0, -1, 0, 1};
    static int[] dc = {1, 0, -1, 0};
    static int[][] map;

    static class Point{
        int x;
        int y;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[101][101]; // 0 ~ 100
        for (int i = 0; i < N; i ++) {
            StringTokenizer stz = new StringTokenizer(br.readLine());
            x = Integer.parseInt(stz.nextToken());
            y = Integer.parseInt(stz.nextToken());
            d = Integer.parseInt(stz.nextToken());
            g = Integer.parseInt(stz.nextToken());
            rotateAndCheck(x, y, d, g);
        }
        System.out.println(getAnswer());
    }
    public static void rotateAndCheck(int x, int y, int d, int g) {
        // 첫번째 획 그리기, 첫 번쨰 획에서 범위 벗어나지 않음
        List<Point> previousPoints = new ArrayList<>();
        map[y][x] = 1;
        map[y + dr[d]][x + dc[d]] = 1;
        previousPoints.add(new Point(x, y));
        previousPoints.add(new Point(x + dc[d],y + dr[d]));

        for (int i = 0; i < g; i ++) {
            // 가장 마지막 점이 중심점
            Point center = previousPoints.get(previousPoints.size() - 1);
            List<Point> tmp = copy(previousPoints);
            for (int j = previousPoints.size() - 2; j >= 0; j --) {
                int nx = (previousPoints.get(j).y - center.y) * -1 + center.x;
                int ny = (previousPoints.get(j).x - center.x) + center.y;
                if (0 <= nx && nx < 101 && 0 <= nx && nx < 101) {
                    map[ny][nx] = 1;
                    tmp.add(new Point(nx, ny));
                }
            }
            previousPoints.clear();
            previousPoints = copy(tmp);
        }
    }

    public static int getAnswer() {
        int answer = 0;
        for (int r = 1; r < 101; r ++) {
            for (int c = 0; c < 100; c ++) {
                boolean flag = true;
                int cr = r, cc = c;
                for(int i = 0; i < 4; i ++) {
                    cr = cr + dr[i];
                    cc = cc + dc[i];
                    if (map[cr][cc] != 1) {
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    answer ++ ;
                }
            }
        }
        return answer;
    }

    public static List<Point> copy(List<Point> origin) {
        List<Point> copyList = new ArrayList<>();
        for(int i = 0; i < origin.size(); i ++) {
            copyList.add(new Point(origin.get(i).x, origin.get(i).y));
        }
        return copyList;
    }
}
