import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int M;
    static PriorityQueue<Line> pq;
    static class Line implements Comparable<Line>{
        int start;
        int end;
        Line(int start, int end) {
            this.start = start;
            this.end = end;
        }
        public int compareTo(Line o) {
            return this.start - o.start;
        }
    }
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        M = Integer.parseInt(br.readLine());

        StringTokenizer stz;
        pq = new PriorityQueue<>();
        String line;
        while ((line = br.readLine()) != null) {
            stz = new StringTokenizer(line);
            int start = Integer.parseInt(stz.nextToken());
            int end = Integer.parseInt(stz.nextToken());
            if ((start <= 0 && end <= 0) || (start >= M && end >= M)) continue;
            pq.add(new Line(start, end));
        }
        int end = 0, number = 0;
        while (!pq.isEmpty()) {
            // 더 이상 이어나갈 수 없거나 목표 길이에 도달한 경우
            if (end < pq.peek().start || end >= M) {
                break;
            }
            int max = 0;
            boolean flag = true;
            while (! pq.isEmpty() && end >= pq.peek().start) {
                Line next = pq.poll();
                if (next.end > end) {
                    max = Math.max(max, next.end);
                    flag = false;
                }
            }
            if (flag) break;
            end = max;
            number ++ ;
        }

        System.out.println(end < M ? 0 : number);
    }
}
