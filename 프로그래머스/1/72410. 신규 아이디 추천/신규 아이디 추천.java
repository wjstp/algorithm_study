import java.util.*;

class Solution {
    public static String solution(String new_id) {
        String answer = "";
        
        // 1. 대문자 -> 소문자
        new_id = new_id.toLowerCase();

        // 2. 소문자, 숫자, -, _, . 빼고 전부 제거
        String second = "";
        for (int i = 0; i < new_id.length(); i ++) {
            char tmp = new_id.charAt(i);
            if (tmp == '-' || tmp == '_' || tmp == '.') {
                second += tmp;
                continue;
            }
            if (((int)tmp <= 122 && (int)tmp >= 97) || ((int)tmp <= 57 && (int)tmp >= 48)) {
                second += tmp;
            }

        }
        System.out.println("조건 2 후 결과 : "+ second);

        // 3. .이 연속되면 하나로 줄인다.
        char previous = second.charAt(0);
        String third = String.valueOf(second.charAt(0));
        for (int i = 1; i < second.length() ; i ++) {
            char tmp = second.charAt(i);
            if (previous == '.' && tmp == previous) {
                continue;
            }
            third += tmp;
            previous = tmp;
        }
        System.out.println("조건 3 후 결과 " + third);
        // 4. 처음이나 끝에 .이 있으면 제거
        while (third.length() > 0 && third.charAt(0) == '.') {
            third = third.length() == 1 ? "" : third.substring(1);
        }
        while (third.length() > 0 && third.charAt(third.length() - 1) == '.') {
            third = third.length() == 1 ? "" : third.substring(0, third.length() - 1);
        }

        System.out.println("조건 4 후 결과 : " + third);
        // 5. 빈 문자면 a 추가
        // 6.16자 이상이면 15까지 자르기
        if (third.length() == 0) {
            third += 'a';
        } else if (third.length() >= 16) {
           third = third.substring(0, 15);
            while (third.length() > 0 && third.charAt(third.length() - 1) == '.') {
                third = third.length() == 1 ? "" : third.substring(0, third.length() - 1);
            }
        }

        // 7. 길이 2자 이하면 마지막 문자가 길이 3이 될때까지 반복
        while (third.length() <= 2) {
            third += third.charAt(third.length() - 1);
        }
        answer = third;
        return answer;
    }
}