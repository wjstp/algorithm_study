import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
    static int N, B, C; // 시험장 개수, 총감독, 부감독
    static long answer; 
    static int[] Aarr;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz;
        N = Integer.parseInt(br.readLine());
        Aarr = new int[N];
        stz = new StringTokenizer(br.readLine());
        for (int i = 0; i < N ; i ++) {
            Aarr[i] = Integer.parseInt(stz.nextToken());
        }
        stz = new StringTokenizer(br.readLine());
        B = Integer.parseInt(stz.nextToken());
        C = Integer.parseInt(stz.nextToken());
        countWatcher();
        System.out.println(answer);
    }
    public static void countWatcher() {
        answer += N ; // 총 감독관 수
        for (int i = 0; i < N ; i ++) {
            if (Aarr[i] - B > 0) {
                answer += Math.ceil((Aarr[i] - B) / (double)C);
            }
        }
    }
}