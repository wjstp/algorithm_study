import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M, answer ;
    static int[][] matrix;
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(br.readLine());
        N = Integer.parseInt(stz.nextToken());
        M = Integer.parseInt(stz.nextToken());

        matrix = new int[N][M];
        for (int r = 0; r < N; r ++) {
            stz = new StringTokenizer(br.readLine());
            for (int c = 0; c < M; c ++) {
                matrix[r][c] = Integer.parseInt(stz.nextToken());
            }
        }

        // 0, 0 ~ r, c까지 누적합 구하기
        for (int r = 0; r < N; r ++) {
            for (int c = 0; c < M; c ++) {
                if (r == 0 && c == 0) continue;

                if (r == 0) {
                    matrix[r][c] += matrix[r][c - 1];
                } else if (c == 0) {
                    matrix[r][c] += matrix[r - 1][c];
                }else{
                    matrix[r][c] += matrix[r - 1][c] + matrix[r][c - 1] - matrix[r - 1][c - 1];
                }
            }
        }
        answer = -400 * 10000;
        // 부분행렬의 합 구하기 - 400칸 중에 두 칸 고르기 - 최적화?
        for (int r1 = 0; r1 < N; r1 ++) {
            for (int c1 = 0; c1 < M; c1 ++) {
                for (int r2 = r1; r2 < N; r2 ++) {
                    for (int c2 = c1; c2 < M; c2 ++) {
                        int width = r1 > 0 ? matrix[r1 - 1][c2] : 0;
                        int depth = c1 > 0 ? matrix[r2][c1 - 1] : 0;
                        int duplicated = r1 > 0 && c1 > 0 ? matrix[r1 - 1][c1 - 1] : 0;
                        answer = Math.max(answer, matrix[r2][c2] - width - depth + duplicated);
                    }
                }
            }
        }
        System.out.println(answer);
    }
}
