import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.Comparator;

public class Main {
    static int n, d, cnt, answer;
    static Info[] infos;
    static PriorityQueue<Info> pq;
    static class Info implements Comparable<Info>{
        int s;
        int e;
        Info(int s, int e) {
            this.s = s;
            this.e = e;
        }

        @Override
        public int compareTo(Info o) {
            if (this.e == o.e) return this.s - o.s;
            return this.e - o.e; // 시작점 기준 오름차순
        }
    }

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        StringTokenizer stz;
        infos = new Info[n];
        for (int i = 0; i < n; i++) {
            stz = new StringTokenizer(br.readLine());
            int h = Integer.parseInt(stz.nextToken());
            int o = Integer.parseInt(stz.nextToken());
            infos[i] = new Info(Math.min(h, o), Math.max(h, o));
        }
        Arrays.sort(infos);
        d = Integer.parseInt(br.readLine());

        pq = new PriorityQueue<>(Comparator.comparingInt(p->p.s));

        for (int i = 0; i < n; i ++) {
            pq.add(infos[i]);
            cnt ++;
            while (! pq.isEmpty() && pq.peek().s + d < infos[i].e) {
                pq.poll();
                cnt --;
            }
            answer = Math.max(answer, cnt);
        }
        System.out.println(answer);
    }

}
