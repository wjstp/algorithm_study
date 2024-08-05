import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;



// 회전 - 리스트를 잘라서 붙여줌
// 리스트를 자를 떄는 새로운 객체에 넣어줘야 한다.
// 0으로 바꿨다는것은 계산에서 제외된다는건데 이걸 추가를 안해줘서(평균구할 떄 나누는 수, 평균과 비교해서 값 늘리거나 줄일떄) 잘못된 값이 나옴
public class Main {
    static int N, M, T, total;
    static List<Integer>[] discs;
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(br.readLine());
        N = Integer.parseInt(stz.nextToken());
        M = Integer.parseInt(stz.nextToken());
        T = Integer.parseInt(stz.nextToken());

        discs = new List[N];
        for (int i = 0; i < N; i ++) {
            stz = new StringTokenizer(br.readLine());
            discs[i] = new ArrayList<>();
            for (int j = 0; j < M; j ++) {
                discs[i].add(Integer.parseInt(stz.nextToken()));
            }
        }
        for (int i = 0; i < T ; i ++) {
            stz = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(stz.nextToken());  // 배수
            int d = Integer.parseInt(stz.nextToken());  // 방향 - 0 : 시계, 1 : 반시계
            int k = Integer.parseInt(stz.nextToken());  // 회전칸수
            rotate(x, d, k);
            total = modify();
        }
        System.out.println(total);
    }

    public static void rotate(int x, int direction, int move) {
        for (int i = 0; i < N; i ++) {
            if ((i + 1) % x == 0) { // 배수일떄만 회전
                int subIdx = direction == 1 ? move % M : M - move % M;
                List<Integer> tmp = new ArrayList<>(discs[i].subList(subIdx, M));
                tmp.addAll(discs[i].subList(0, subIdx));
                discs[i].clear();
                discs[i] = tmp;
            }
        }
    }

    public static int modify() {
        // 인접하면서 같은 수 지우기
        // 바로 지우면 갱신이 잘못된다.
        List<Integer>[] copy = new List[N];
        for (int i = 0; i < N; i ++) {
            copy[i] = new ArrayList<>(discs[i]);
        }
        boolean haveSame = false;

        // 인접한 것 중 같은 것이 있다면 0으로 바꿔준다.
        int sum = 0, cnt = 0;
        for (int i = 0; i < N; i ++) {
            for (int j = 0; j < M; j ++) {
                int cur = copy[i].get(j);
                for (int k = 0; k < 4; k ++) {
                    int disc = i + dr[k];
                    int num = (M + j + dc[k]) % M;

                    if (cur != 0 && 0 <= disc && disc < N && 0<= num && num < M) {
                        if (cur == copy[disc].get(num)) {
                            haveSame = true;
                            discs[i].set(j, 0);
                        }
                    }
                }
                if (discs[i].get(j) != 0) cnt ++ ;
                sum += discs[i].get(j);
            }
        }
        // 같은게 없다면 평균 기준으로 다시 계산
        double avg = sum / (double)(cnt);
        if (!haveSame) {
            for (int i = 0; i < N; i ++) {
                for (int j = 0; j < M; j ++) {
                    int cur = discs[i].get(j);
                    if (cur != 0) {
                        if (cur > avg) {
                            discs[i].set(j, cur - 1);
                            sum -- ;
                        };
                        if (cur < avg) {
                            discs[i].set(j, cur + 1);
                            sum ++ ;
                        }
                    }
                }
            }
        }

        return sum;
    }
}
