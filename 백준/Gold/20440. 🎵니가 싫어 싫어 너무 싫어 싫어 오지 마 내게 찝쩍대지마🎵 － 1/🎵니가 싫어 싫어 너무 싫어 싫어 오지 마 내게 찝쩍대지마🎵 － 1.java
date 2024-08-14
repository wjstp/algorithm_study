import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static List<int[]> infos;
    static HashMap<Integer, Integer> mosquito;
    static HashSet<Integer> times;

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        StringTokenizer stz;
        infos = new ArrayList<>();
        mosquito = new HashMap<>();
        times = new HashSet<>();
        for (int i = 0; i < N; i ++) {
            stz = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(stz.nextToken());
            int y = Integer.parseInt(stz.nextToken());
            infos.add(new int[]{x, y});
            mosquito.put(x, mosquito.getOrDefault(x, 0) + 1);
            mosquito.put(y, mosquito.getOrDefault(y, 0) - 1);
            times.add(x);
            times.add(y);
        }
        List<Integer> timeLine = new ArrayList(times);
        Collections.sort(timeLine);
        int[] tmp = new int[timeLine.size()];
        int max = 0, start = 0, end = 0;
        boolean flag = false;
        for (int i = 0; i < timeLine.size(); i ++) {
            if (i != 0) tmp[i] = tmp[i - 1];
            tmp[i] += mosquito.get(timeLine.get(i));
            if (max < tmp[i]) {
                max = tmp[i];
                start = timeLine.get(i);
                flag = true;
            }
            if (flag && max > tmp[i]) {
                end = timeLine.get(i);
                flag = false;
            }
        }
        System.out.println(max);
        System.out.println(start + " " + end);

    }
}
