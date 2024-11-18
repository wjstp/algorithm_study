import java.util.*;

class Solution {
    public String[] solution(String[] record) {
        Map<String, String> idAndName = new HashMap<>();
        List<String> tmp = new ArrayList<>();
        for (var r : record) {
            String cmd = r.split(" ")[0];
            String id = r.split(" ")[1];

            if (cmd.equals("Change")) {
                // 닉네임 변경
                idAndName.put(id, r.split(" ")[2]);
            } else if (cmd.equals("Enter")) {
                // 닉네임 추가 or 변경
                idAndName.put(id, r.split(" ")[2]);
                tmp.add(id + " " + "님이 들어왔습니다.");
            } else {
                tmp.add(id + " " + "님이 나갔습니다.");
            }
        }
        String[] answer = new String[tmp.size()];
        for (int i = 0; i < tmp.size(); i ++) {
            String nickname = idAndName.get(tmp.get(i).split(" ")[0]);
            answer[i] = nickname + tmp.get(i).split(" ")[1] + " " + tmp.get(i).split(" ")[2];
        }
        return answer;
    }
}