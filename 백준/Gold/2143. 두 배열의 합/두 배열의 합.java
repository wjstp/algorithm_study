import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int T, n, m;
    static long cnt;
    static int[] aArr, bArr;
    static Map<Integer, Integer> prefixMap;

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        n = Integer.parseInt(br.readLine());
        StringTokenizer stz = new StringTokenizer(br.readLine());
        aArr = new int[n];
        for (int i = 0; i < n; i ++) {
            aArr[i] = Integer.parseInt(stz.nextToken());
        }
        m = Integer.parseInt(br.readLine());
        stz = new StringTokenizer(br.readLine());
        bArr = new int[m];
        for (int i = 0; i < m; i ++) {
            bArr[i] = Integer.parseInt(stz.nextToken());
        }

        // 나올 수 있는 모든 누적합 구하기
        prefixMap = new HashMap<>();
        for (int i = 0; i < n; i ++) {
            int tmp =  0 ;
            for (int j = i; j < n; j ++) {
                tmp += aArr[j] ;
                prefixMap.put(tmp, prefixMap.getOrDefault(tmp, 0) + 1);
            }
        }
        for (int i = 0; i < m; i ++) {
            int tmp = 0;
            for (int j = i; j < m; j ++) {
                tmp += bArr[j];
                cnt += prefixMap.getOrDefault(T - tmp, 0);
            }
        }

        System.out.println(cnt);
    }
}
