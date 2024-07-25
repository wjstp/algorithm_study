class Solution {
    public String solution(String play_time, String adv_time, String[] logs) {
        // 보기 시작한 시점에 +1, 빠져나간 시점에 -1을 표시
        long playTime = getSeconds(play_time);
        long advTime = getSeconds(adv_time);
        long[] views = new long[(int) (playTime + 1)];
        for (var log : logs) {
            long start = getSeconds(log.split("-")[0]);
            long end = getSeconds(log.split("-")[1]); 
            views[(int)start] += 1;
            views[(int)end] -= 1;
        }

        // 0부터 종료 시점까지 시청 인원 체크
        for (int i = 1; i < playTime + 1; i ++) {
            views[i] += views[i - 1];
        }

        // 0부터 광고 시간까지의 시청 인원 수 체크 후, prefix_sum 방식으로 체크
        // 전체 시간을 벗어난다면, 0으로 바로 끝
        long answer = 0;
        if (advTime < playTime) {
            long tmp = 0;
            for (int i = 0; i < advTime ; i ++) {
                tmp += views[i];
            }
            long maxTime = tmp;
            for (int i = 0; i < playTime - advTime; i ++ ){
                tmp = tmp - views[i] + views[(int)(i + advTime)];
                if (maxTime < tmp) {
                    maxTime = tmp;
                    answer = i + 1;
                }
            }
        }
        return converToString(answer);
    }

    public static long getSeconds(String time) {
        long hour = Integer.parseInt(time.split(":")[0]);
        long minute = Integer.parseInt(time.split(":")[1]);
        long second = Integer.parseInt(time.split(":")[2]);
        return hour * 60 * 60 + minute * 60 + second;
    }

    public static String converToString(long time) {
        long hour = time / 3600;
        long minute = (time - hour * 3600) / 60;
        long second = time - hour * 3600 - minute * 60;
        return (String.valueOf(hour).length()== 2? hour : "0" + hour) + ":"
                + (String.valueOf(minute).length() == 2? minute : "0" + minute) + ":"
                + (String.valueOf(second).length() == 2? second : "0" + second);
    }
}