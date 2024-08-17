import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static PriorityQueue<Info> info;
    static PriorityQueue<Time> pq;
    static class Info implements Comparable<Info> {
        int start;
        int end;
        Info(int start, int end) {
            this.start = start;
            this.end = end;
        }
        @Override
        public int compareTo(Info o) {
            return this.start - o.start;
        }
    }
    static class Time implements Comparable<Time> {
        int start;
        int end;
        int number;     // 앉은 자리
        Time(int start, int end, int number) {
            this.start = start;
            this.end = end;
            this.number = number;
        }
        @Override
        public int compareTo(Time o) {
            return this.end - o.end;    // 오름차순 ..?
        }
    }
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer stz;
        // 시작이 빠른 순대로 정렬
        info = new PriorityQueue<>();
        for (int i = 0; i < N; i ++) {
            stz = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(stz.nextToken());
            int end = Integer.parseInt(stz.nextToken());
            info.add(new Info(start, end));
        }
        pq = new PriorityQueue<>();
        int[] chairs = new int[N];
        PriorityQueue<Integer> empty = new PriorityQueue<>();
        int number = 0;
        while (!info.isEmpty()) {
            Info next = info.poll();
            while (!pq.isEmpty() && pq.peek().end <= next.start) {
                Time tmp = pq.poll();
                empty.add(tmp.number);
            }
            if (! empty.isEmpty()) {
                chairs[empty.peek() - 1] ++ ;
                pq.add(new Time(next.start, next.end, empty.poll()));
            } else {
                chairs[number] ++ ;
                pq.add(new Time(next.start, next.end, ++ number));
            }
        }
        System.out.println(number);
        for (int i = 0; i < number; i ++) {
            System.out.print(chairs[i] + " ");
        }
    }
}
