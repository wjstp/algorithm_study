import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

// 입력받을 떄 집과 사무실의 위치가 뒤집혀서 마이너스가 되는 경우는 고려안함
// peek으로만 접근해서 ㅃ바져야 할 값이 빠지지 않아 그 다음 값으로 넘어가지 못하고 계쏙 조건이 충족이 안돼 poll이 안됐었음
// 정렬한 것을 뛰어넘어서 범위에 포함되는 경우 처리를 하지 않았음
// 답봤다.. 끝지점을 기준으로 오름차순, 끝지점이 같다면 시작지점이 앞에 있는 것 (길이가 긴 순으로)
// 가장 앞에 있는 것이 그 다음것보다 무조건 범위에서 먼저 빠져나가는 것일 수 있도록
public class Main {
    static int N, d, answer;
    static PriorityQueue<Point> people;
    static PriorityQueue<Point> currentRailWay;

    static class Point implements Comparable<Point> {
        int start;
        int end;

        Point(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Point point) {
            if (this.end == point.end) {
                return this.start - point.start;
            } else {
                return this.end - point.end;
            }
        }
    }

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        people = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            StringTokenizer stz = new StringTokenizer(br.readLine());
            int h = Integer.parseInt(stz.nextToken());
            int o = Integer.parseInt(stz.nextToken());
            people.add(new Point(Math.min(h, o), Math.max(h, o)));
        }
        d = Integer.parseInt(br.readLine());
        currentRailWay = new PriorityQueue<>(Comparator.comparingInt(p -> p.start));
        answer = 0;
        int cnt = 0;

        while (!people.isEmpty()) {
            Point current = people.poll();
            currentRailWay.add(current);
            cnt++;

            while (!currentRailWay.isEmpty() && currentRailWay.peek().start < current.end - d) {
                currentRailWay.poll();
                cnt--;
            }

            answer = Math.max(answer, cnt);
        }

        System.out.println(answer);
    }
}