/*
N을 계속 곱하면서 char 한자리씩 확인한다
visited(0)와 check((1<<10)-1이) 비교
0~9까지의 숫자가 다 나왔는지는 비트마스킹을 활용한다 (visited = visited | (1<<n)-1)
 * */
import java.io.BufferedReader;
import java.io.InputStreamReader;


class Solution
{
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T;
        T = Integer.parseInt(br.readLine());
        for(int test_case = 1; test_case <= T ; test_case++) {
            int N = Integer.parseInt(br.readLine());
            int check = (1<<10)-1;  // 모든 숫자가 등장했을 때의 값
            int visited = 0;
            int cnt = 0;
            while (visited != check) {  // 모든 숫자가 등장했다면 while문 탈출
                cnt ++ ;
                char[] nByArr  = String.valueOf(cnt * N).toCharArray();    // N*count 값을 String로 변환후 char배열로 표현한 것  (예: 5 * 13 = 65 -> "65")
                for(char n : nByArr) {
                    int num = n - '0';
                    visited = visited | (1<<num) ;  // 각 숫자에 대해 등장했다는 의미로 bit 를 1로 변경
                }
            }
            System.out.println("#"+test_case+" "+ cnt * N);
        }
	}
}