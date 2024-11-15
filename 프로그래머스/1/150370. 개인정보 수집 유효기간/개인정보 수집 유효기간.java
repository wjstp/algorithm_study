import java.util.*;

class Solution {
    public List<Integer> solution(String today, String[] terms, String[] privacies) {
        List<Integer> answer = new ArrayList<>();
        Map<String, Integer> conditions = new HashMap<>();
        for (var term : terms) {
            String condition = term.split(" ")[0];
            int period = Integer.parseInt(term.split(" ")[1]);
            conditions.put(condition, period);
        }
            
        int todayYear = Integer.parseInt(today.split("\\.")[0]);
        int todayMonth = Integer.parseInt(today.split("\\.")[1]);
        int todayDate = Integer.parseInt(today.split("\\.")[2]);
        
        int num = 0;
        for (var privacy : privacies) {
            num ++;
            String condition = privacy.split(" ")[1];
            int year = Integer.parseInt(privacy.split("\\.")[0]);
            int month = Integer.parseInt(privacy.split("\\.")[1]);
            int date = Integer.parseInt(privacy.split("\\.")[2].split(" ")[0]);
            int term = conditions.get(condition);
            // 약정 끝나는 시점 계산
            month += term % 12;
            if (month > 12) {
                year += 1;
                month -= 12;
            }
            year += term / 12;
            if (todayYear < year) {
                continue;
            } else if (todayYear > year) {
                answer.add(num);
            } else {
                if (todayMonth < month) {
                    continue;
                } else if (todayMonth > month) {
                    answer.add(num);
                } else {
                    if (todayDate < date) {
                        continue;
                    } else {
                        answer.add(num);
                    }
                }
            }
        }
        Collections.sort(answer);
        return answer;
    }
}