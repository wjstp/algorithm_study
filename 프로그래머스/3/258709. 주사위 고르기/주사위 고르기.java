import java.util.*;
// 들어오는 조합에 따라 따로 저장해도 될듯

class Solution {
    static int n, maxWin;
    static int[] check, answer;
    static List<Integer> aCom, bCom;
    
    public int[] solution(int[][] dice) {
        n = dice.length;
        answer = new int[n / 2];
        check = new int[n];
        getProduct(-1, 0, dice);
        return answer;
    }
    
    public static void getProduct(int cur, int cnt, int[][] dice) {
        if (cnt == n / 2) {
            // 승률 계산
            getWinnableProduct(dice);
            return;
        }
        for (int i = cur + 1; i < n; i ++) {
            if (check[i] == 1) continue;
            check[i] = 1;
            getProduct(i, cnt + 1, dice);
            check[i] = 0;
        }
    }
    
    public static void getWinnableProduct(int[][] dice) {
        List<Integer> aArr = new ArrayList<>();
        List<Integer> bArr = new ArrayList<>();
        for (int i = 0; i < n; i ++) {
            if (check[i] == 1) aArr.add(i);
            else bArr.add(i);
        }
        aCom = new ArrayList<>();
        bCom = new ArrayList<>();
        
        makeArr(aArr, 0, 0, "A", dice);
        makeArr(bArr, 0, 0, "B", dice);
        int num = compareProbabilities();
        
        if (num > maxWin) {
            maxWin = num;
            for (int i = 0; i < n / 2; i ++) {
                answer[i] = aArr.get(i) + 1;
            }
        }
        return;
    }
    
    public static void makeArr(List<Integer> arr, int depth, int value, String category, int[][] dice) {
        if (depth == n / 2) {
            if (category.equals("A")) aCom.add(value);
            else bCom.add(value);
            return;
        }
        for (int i = 0; i < 6; i ++) {
            makeArr(arr, depth + 1, value + dice[arr.get(depth)][i], category, dice);
        }
    }
    
    
    public static int compareProbabilities() {
        Collections.sort(aCom, (o1, o2) -> o2 - o1);
        Collections.sort(bCom);
        int cnt = 0;
        int right = bCom.size() - 1;
        for (int left = 0; left < aCom.size(); left ++) {
            if (right == 0 && aCom.get(left) <= bCom.get(right)) break;
            while (right >= 0) {
                if (aCom.get(left) > bCom.get(right)) {
                    cnt += right + 1;
                    break;
                }
                else right --;
            }
        }
        return cnt;
        
    }
}