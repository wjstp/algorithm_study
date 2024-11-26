class Solution {
    public static boolean solution(int[][] key, int[][] lock) {
        boolean answer = false;
        // 방향 4번 회전
        for (int i = 0; i < 4; i ++) {
            // key 이동
            for (int r = 0; r < lock.length + key.length- 1; r ++) {
                for (int c = 0; c < lock.length + key.length - 1; c ++) {
                    if (checkValidationOfKey(r, c, lock, key)) {
                        answer = true;
                        return answer;
                    }
                }
            }
            if (i < 3) {
                key = rotateKey(key);
            }
        }
        return answer;
    }
    public static int[][] rotateKey(int[][] key) {
        int[][] newKey = new int[key.length][key.length];
        for (int c = 0; c < key.length ; c ++) {
            for (int r = key.length - 1; r >= 0; r --) {
                newKey[c][key.length - r - 1] = key[r][c];
            }
        }
        return newKey;
    }
    public static boolean checkValidationOfKey(int keyR, int keyC, int[][] lock, int[][] key) { // key 가장 오른쪽 아래의 위치
        for (int r = 0; r < lock.length; r ++) {
            for (int c = 0 ; c < lock.length ; c ++) {
                int kr = key.length + r - keyR - 1;
                int kc = key.length + c - keyC - 1;
                if (lock[r][c] == 0) {
                    if (0 <= kr && kr < key.length && 0 <= kc && kc < key.length && key[kr][kc] == 1) {
                        continue;
                    } else {
                        return false;
                    }
                }
                else if (lock[r][c] == 1) {
                    if (0 <= kr && kr < key.length && 0 <= kc && kc < key.length && key[kr][kc] == 1) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}