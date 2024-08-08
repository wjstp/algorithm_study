import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.BitSet;
import java.util.StringTokenizer;

public class Main {
   static BitSet check;
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(br.readLine());
        StringBuffer sb = new StringBuffer();
        check = new BitSet(1<<25);
        while (stz.hasMoreTokens()) {
            int tmp = Integer.parseInt(stz.nextToken());
            if (! check.get(tmp)) {
                sb.append(tmp).append(' ');
                check.set(tmp);
            }
        }
        System.out.println(sb);
    }
}
