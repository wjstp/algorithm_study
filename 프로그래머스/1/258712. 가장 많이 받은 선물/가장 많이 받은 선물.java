import java.util.*;

class Solution {
    public int solution(String[] friends, String[] gifts) {
        int answer = 0;
        Map<String, Integer> friendIdx = new HashMap<>();
        int[] giftScore = new int[friends.length];
        int[][] giftRelationship = new int[friends.length][friends.length];
        
        for (int i = 0; i < friends.length; i ++) {
            friendIdx.put(friends[i], i);
        }
        
        for (var gift : gifts) {
            int from = friendIdx.get(gift.split(" ")[0]);
            int to = friendIdx.get(gift.split(" ")[1]);
            // 선물 지수 조정
            giftScore[from] ++;
            giftScore[to] --;
            // 선물 주고받은 기록 저장
            giftRelationship[from][to] ++;
        }
        
        // 다음 달에 가장 많은 선물을 받을 사람 구하기
        for (int from = 0; from < friends.length; from ++) {
            int tmp = 0;
            for (int to = 0; to < friends.length; to ++) {
                // 준 선물이 더 많은 경우
                if (giftRelationship[from][to] > giftRelationship[to][from]) tmp ++;
                // 선물 지수가 더 많은 경우
                else if (giftRelationship[from][to] == giftRelationship[to][from]) {
                    if (giftScore[from] > giftScore[to])  tmp ++;
                    
                }
            }
            System.out.println(tmp);
            answer = Math.max(answer, tmp);
        }
        return answer;
    }
}