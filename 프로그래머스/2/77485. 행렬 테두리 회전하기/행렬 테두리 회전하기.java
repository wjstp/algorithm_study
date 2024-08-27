import java.util.*;


class Solution {
    static int[][] map;
    public List<Integer> solution(int rows, int columns, int[][] queries) {
        List<Integer> answer = new ArrayList<>();
        // 처음 맵 초기화
        map = new int[rows][columns];
        for (int r = 0; r < rows; r ++) {
            for (int c = 0; c < columns ; c ++) {
                map[r][c] = r * columns + (c + 1);
            }
        }
        for (var query : queries) {
            int min = rotate(query[0] - 1, query[1] - 1, query[2] - 1, query[3] - 1);
            answer.add(min);
        }
        return answer;
    }
    
    
    
    public static int rotate(int r1, int c1, int r2, int c2) {
        int cr = r1;
        int cc = c2;
        int tmp = map[cr][cc];
        int minValue = tmp;
        
        // 오른쪽으로 이동 (거꾸로 기록)
        while (cc > c1) {
            cc -- ;
            map[cr][cc + 1] = map[r1][cc];
            minValue = Math.min(minValue, map[cr][cc]);
        }
        // 위로 이동 (거꾸로 기록)
        while (cr < r2) {
            cr ++ ;
            map[cr - 1][cc] = map[cr][c1];
            minValue = Math.min(minValue, map[cr][cc]);
        }
        // 왼쪽 이동 (거꾸로 기록)
        while (cc < c2) {
            cc ++;
            map[cr][cc - 1] = map[r2][cc];
            minValue = Math.min(minValue, map[cr][cc]);
        }
        // 아래로 이동 (거꾸로 기록)
        while (cr > r1 + 1) {
            cr --;
            map[cr + 1][cc] = map[cr][c2];
            minValue = Math.min(minValue, map[cr][cc]);
        }
        map[cr][cc] = tmp;
        return minValue;
    }
}