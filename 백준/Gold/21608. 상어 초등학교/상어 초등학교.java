import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.StreamSupport;

// linkedhashmap
// 좋아하는 학생이 많은 수가 같다면 비어있는 수가 큰 수 순으로
// 우선순위 높은 것 기준으로 maxEmpty도 갱신
public class Main {

    static int N, answer;
    static int[][] map;
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};
    static LinkedHashMap<Integer, HashSet<Integer>> favoriteFriends;
    static class Point{
        int r ;
        int c ;
        Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz;

        N = Integer.parseInt(br.readLine());
        favoriteFriends = new LinkedHashMap<>();
        map = new int[N][N];
        for (int i = 0; i < N*N ; i ++) {
            stz = new StringTokenizer(br.readLine());
            int key = Integer.parseInt(stz.nextToken());
            HashSet<Integer> tmp = new HashSet();
            for (int j = 0 ; j < 4 ;j ++) {
                tmp.add(Integer.parseInt(stz.nextToken()));
            }
            favoriteFriends.put(key, tmp);
        }
        takeSeat();
        getSatisfaction();
        System.out.println(answer);
    }
    public static void takeSeat() {
        for (Map.Entry<Integer, HashSet<Integer>> entry : favoriteFriends.entrySet()) {
            int nom = entry.getKey();
            HashSet<Integer> favorite = entry.getValue();
            // N*N 순회하며 좋아하는 학생수가 가장 많은 곳, 빈칸이 가장 많은 곳을 체크
            boolean flag = true;
            int maxFriend = -1, maxEmpty = -1;
            Point seatPoint = new Point(-1, -1); // 변수명 좀.. 바꾸자

            for (int r = 0; r < N ; r ++) {
                for (int c = 0; c < N ; c++) {
                    // 이미 학생이 앉아있는 자리는 pass
                    if (map[r][c] != 0) {
                        continue;
                    }
                    int empty = 0, friend = 0;
                    for (int i = 0; i < 4; i ++) {
                        int nr = r + dr[i], nc = c + dc[i];
                        if ( 0 <= nr && nr < N && 0 <= nc && nc < N) {
                            if (favorite.contains(map[nr][nc])) {
                                flag = false;
                                friend ++ ;
                            }
                            if (map[nr][nc] == 0) {
                                empty ++ ;
                            }
                        }
                    }
                    // 자리 갱신
                    if (maxFriend < friend) {
                        // 좋아하는 친구 많은 곳
                        maxFriend = friend;
                        seatPoint = new Point(r, c);
                        maxEmpty = empty;   // 친구가 가장 많은 곳 기준으로 maxEmpty 재정의
                    } else if (maxFriend == friend && empty > maxEmpty) {
                        seatPoint = new Point(r, c);
                        maxEmpty = empty;
                    }
                    if (flag && empty > maxEmpty) {
                        seatPoint = new Point(r, c);
                        maxEmpty = empty;
                    }
                }
            }
            map[seatPoint.r][seatPoint.c] = nom;
        }
    }

    public static void getSatisfaction() {
        for (int r = 0 ; r < N ; r ++) {
            for (int c = 0 ; c < N; c ++) {
                HashSet<Integer> favoriteStudents = favoriteFriends.get(map[r][c]);
                int cnt = 0;
                for (int i = 0 ; i < 4 ; i ++) {
                    int nr = r + dr[i], nc = c + dc[i];
                    if (0 > nr || N <= nr || 0 > nc || N <= nc) {
                        continue;
                    }
                    if (favoriteStudents.contains(map[nr][nc])) {
                        cnt ++ ;
                    }
                }
                answer += Math.pow(10, cnt -1);
            }
        }
    }
}
