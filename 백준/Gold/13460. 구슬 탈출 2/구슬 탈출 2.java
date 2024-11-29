import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 풀이.. 이동은 벽 만날때까지, 대신 bfs로 최단거리
// 자잘한 실수.. 조건 앞뒤로 바뀐 것, map[c][c]로 하는 등...
public class Main {
    static int N, M;
    static int[] dr = {0, 0, -1, 1};
    static int[] dc = {1, -1, 0, 0};
    static int[][][][] visit;
    static char[][] map;
    static class Status {
        Point redPoint;
        Point bluePoint;
        int level;
        Status (Point redPoint, Point bluePoint, int level) {
            this.redPoint = redPoint;
            this.bluePoint = bluePoint;
            this.level = level;
        }
    }
    static class Point {
        int r;
        int c;
        Point (int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(br.readLine());
        N = Integer.parseInt(stz.nextToken());
        M = Integer.parseInt(stz.nextToken());
        map = new char[N][M];
        Point red = null, blue = null;
        for (int r = 0; r < N; r ++) {
            String line = br.readLine();
            for (int c = 0; c < M; c ++) {
                map[r][c] = line.charAt(c);
                if (map[r][c] == 'R') {
                    red = new Point(r, c);
                } else if (map[r][c] == 'B') {
                    blue = new Point (r, c);
                }
            }
        }
        Queue<Status> q = new LinkedList<>();
        q.add(new Status(red, blue,0));
        visit = new int[N][M][N][M];
        visit[red.r][red.c][blue.r][blue.c] = 1;
        int answer = -1;
        while (!q.isEmpty()) {
            Status cur = q.poll();
            Point cRed = cur.redPoint;
            Point cBlue = cur.bluePoint;
            if (cur.level > 10) break;
            if (map[cRed.r][cRed.c] == 'O') {
                answer = cur.level;
                break;
            }
            for (int i = 0; i < 4; i ++) {
                Point[] next = moveBeads(i, cRed, cBlue);
                Point nRed = next[0];
                Point nBlue = next[1];
                if (nBlue == null || nRed == null) continue;
                if (nBlue.r == -1 && nBlue.c == -1) continue;
                if (visit[nRed.r][nRed.c][nBlue.r][nBlue.c] == 1) continue;
                q.add(new Status(nRed, nBlue, cur.level + 1));
                visit[nRed.r][nRed.c][nBlue.r][nBlue.c] = 1;
            }
        }
        System.out.println(answer);
    }


    public static Point[] moveBeads(int dir, Point red, Point blue) {
        // return (-1, -1) 파란색 구슬이 먼저 탈출 or 동시에 탈출
        Point newRed = null;
        Point newBlue = null;
        // 겹칠 일 없는 경우
        if ((red.r == blue.r && dir < 2) || (red.c == blue.c && dir >= 2)) {
            Point[] tmp = new Point[2];
            switch (dir) {
                case 0: // 오른쪽
                    if (red.c > blue.c || red.r > blue.r) {
                        tmp[0] = new Point(red.r, red.c);
                        tmp[1] = new Point(blue.r, blue.c);
                        Point[] res = move(tmp[0], tmp[1], dir);

                        newRed = res[0];
                        newBlue = res[1];
                    } else {
                        tmp[0] = new Point(blue.r, blue.c);
                        tmp[1] = new Point(red.r, red.c);
                        Point[] res = move(tmp[0], tmp[1], dir);
                        newRed = res[1];
                        newBlue = res[0];
                    }
                    break;
                case 3 :
                    if (red.r > blue.r) {
                        tmp[0] = new Point(red.r, red.c);
                        tmp[1] = new Point(blue.r, blue.c);
                        Point[] res = move(tmp[0], tmp[1], dir);

                        newRed = res[0];
                        newBlue = res[1];
                    } else {
                        tmp[0] = new Point(blue.r, blue.c);
                        tmp[1] = new Point(red.r, red.c);
                        Point[] res = move(tmp[0], tmp[1], dir);
                        newRed = res[1];
                        newBlue = res[0];
                    }
                    break;
                case 1:
                    if (red.c < blue.c || red.r < blue.r) {
                        tmp[0] = new Point(red.r, red.c);
                        tmp[1] = new Point(blue.r, blue.c);
                        Point[] res = move(tmp[0], tmp[1], dir);
                        newRed = res[0];
                        newBlue = res[1];
                    } else {
                        tmp[0] = new Point(blue.r, blue.c);
                        tmp[1] = new Point(red.r, red.c);
                        Point[] res = move(tmp[0], tmp[1], dir);
                        newRed = res[1];
                        newBlue = res[0];
                    }
                    break;
                case 2 :
                    if (red.r < blue.r) {
                        tmp[0] = new Point(red.r, red.c);
                        tmp[1] = new Point(blue.r, blue.c);
                        Point[] res = move(tmp[0], tmp[1], dir);
                        newRed = res[0];
                        newBlue = res[1];
                    } else {
                        tmp[0] = new Point(blue.r, blue.c);
                        tmp[1] = new Point(red.r, red.c);
                        Point[] res = move(tmp[0], tmp[1], dir);
                        newRed = res[1];
                        newBlue = res[0];
                    }
                    break;
            }
            if (map[newBlue.r][newBlue.c] == 'O') {
                newRed = new Point(-1, -1);
                newBlue = new Point(-1, -1);
            }
        } else {
            int redR = red.r;
            int redC = red.c;
            int blueR = blue.r;
            int blueC = blue.c;
            // red 이동
            while (redR + dr[dir] >= 0 && redR + dr[dir] < N && redC + dc[dir] >= 0 && redC + dc[dir] < M) {
                if ((map[redR + dr[dir]][redC + dc[dir]] == '#' || map[redR][redC] == 'O')) {
                    break;
                }
                redR += dr[dir];
                redC += dc[dir];
            }
            while (blueR + dr[dir] >= 0 && blueR + dr[dir] < N && blueC + dc[dir] >= 0 && blueC + dc[dir] < M) {
                if (map[blueR + dr[dir]][blueC + dc[dir]] == '#' ||  map[blueR][blueC] == 'O') break;
                blueR += dr[dir];
                blueC += dc[dir];
            }
            if (map[blueR][blueC] == 'O') {
                newRed = new Point(-1, -1);
                newBlue = new Point(-1, -1);
            } else {
                newRed = new Point(redR, redC);
                newBlue = new Point(blueR, blueC);
            }
        }
        return new Point[]{newRed, newBlue};
    }

    public static Point[] move(Point first, Point second, int dir) {
        while (first.c + dc[dir] < M && first.c + dc[dir] >= 0 && first.r + dr[dir] < N && first.r + dr[dir] >= 0) {
            if (map[first.r + dr[dir]][first.c + dc[dir]] == '#' || map[first.r][first.c] == 'O') break;
            first.r += dr[dir];
            first.c += dc[dir];
        }
        while (second.c + dc[dir] < M && second.c + dc[dir] >= 0 && second.r + dr[dir] < N && second.r + dr[dir] >= 0) {
            if (map[second.r + dr[dir]][second.c + dc[dir]] == '#' || map[second.r][second.c] == 'O' || (map[first.r][first.c] != 'O' && second.c + dc[dir] == first.c && second.r + dr[dir] == first.r)) break;
            second.r += dr[dir];
            second.c += dc[dir];
        }
        return new Point[]{first, second};
    }
}
