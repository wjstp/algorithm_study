import java.util.*;

class Solution {
    public static class StageAndFailure implements Comparable<StageAndFailure>{
        int stage;
        double failure;
        
        StageAndFailure(int stage, double failure) {
            this.stage = stage;
            this.failure = failure;
        }
        
        @Override
        public int compareTo(StageAndFailure o) {
            if (Double.compare(o.failure, this.failure) == 0) {
                return this.stage - o.stage;
            }
            return Double.compare(o.failure, this.failure);
        }
        
    }
    public int[] solution(int N, int[] stages) {
        int[] answer = new int[N]; // 0 ~ N - 1, stage는 N개 있음
        int[] current = new int[N + 2]; // 1 ~ N + 1, N + 1은 N번 스테이지를 깬 사람
        int[] pass = new int[N + 2]; 
        PriorityQueue<StageAndFailure> percentages = new PriorityQueue<>();
        for (var stage : stages) current[stage] ++;
        
        for (int i = N + 1; i > 0; i --) {
            if (i == N + 1) {
                pass[i] = current[i];
                continue;
            }
            pass[i] = current[i] + pass[i + 1]; 
        }
        // for (int i = 1; i < N + 2; i ++) {
        //     System.out.println(pass[i]);
        // }
        for (int i = 1; i < N + 1; i ++) {
            double percentage = pass[i] != 0 ? (current[i]/ (double)pass[i]) : 0.0;
            percentages.add(new StageAndFailure(i, percentage));
        }
        
        
        for (int i = 0; i < N; i ++) {
            answer[i] = percentages.poll().stage;
        }
        
        return answer;
    }
}