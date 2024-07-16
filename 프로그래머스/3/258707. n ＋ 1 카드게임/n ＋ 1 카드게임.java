import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


class Solution {
    
    static int answer, N, remainCoin;
    static List<Integer> own;
    static List<Integer> candidate;
    
    public static int solution(int coin, int[] cards) {
        own = new ArrayList<>();
        N = cards.length;
        remainCoin = coin;


        for (int i = 0; i < N / 3; i ++) {
            own.add(cards[i]);
        }
        int idx = N / 3 ;
        candidate = new ArrayList<>();

        while (true) {
            answer ++ ;
            // 카드가 더 이상 남아있지 않다면 stop
            if (idx >= N) {
                break;
            }
            // 카드 두 장 후보군에 넣기
            candidate.add(cards[idx ++]);
            candidate.add(cards[idx ++]);  // idx += 2 도 같이

            // 지금 소유하고 있는 것들에서 뽑을 수 있다면 넘어감
            if (getFromOwn()) {
                continue;
            }
            // 후보군에서조차 합해서 뽑을 수 없다면 break;
            if (! getFromCandidate()) {
                break;
            }

        }
        return answer;
    }
    // 왜 한개로 해결할 수 있는 경우가 더 좋나..일단 한개 뽑고 나중에 필요하다고 판단될 떄 나머지 하나 더 뽑으면 되는 것
    public static boolean getFromCandidate() {
        if (remainCoin == 0) {
            return false;
        }
        Collections.sort(candidate);
        // 코인 한개로 해결할 수 있는 경우 - own + candidate 조합
        if (remainCoin >= 1) {
            for (int i = 0; i < own.size(); i++) {
                for (int j = 0; j < candidate.size() ; j ++) {
                    if (own.get(i) + candidate.get(j) == N + 1) {
                        candidate.remove(j);
                        own.remove(i);
                        remainCoin -- ;
                        return true;
                    }
                }
            }
        }
        // 한 개로는 안되지만, 코인을 두개 써야 하는 경우 - 나중에는 여기서만 뽑게 된다. (candidate 안에서만 2개 뽑기)
        if (remainCoin >= 2) {
            int left = 0;
            int right = candidate.size() - 1;
            while (left < right) {
                int tmp = candidate.get(left) + candidate.get(right);
                if (tmp == N + 1) {
                    candidate.remove(right);
                    candidate.remove(left);
                    remainCoin -= 2;
                    return true;
                } else if (tmp < N + 1) {
                    left ++ ;
                } else {
                    right -- ;
                }
            }
        }
        return false;
    }
    public static boolean getFromOwn() {
        Collections.sort(own);
        int left = 0;
        int right = own.size() - 1;
        while (left < right) {
            int tmp = own.get(left) + own.get(right);
            if (tmp > N + 1) {
                right -- ;
            } else if (tmp < N + 1) {
                left ++ ;
            } else {    // tmp == N + 1
                own.remove(right);  // 오른쪽부터 뺴줘야 왼쪽 인덱스가 안바뀜
                own.remove(left);
                return true;
            }
        }
        return false;
    }
}