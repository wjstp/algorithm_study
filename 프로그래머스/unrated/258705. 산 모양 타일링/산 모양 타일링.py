def solution(n, tops):
    answer = 0
    dp = [0 for _ in range(2*n+2)] # dp[i] i번째까지 모양을 만들 수 있는 경우의 수
    dp[0] = 1 # 아무것도 없을 떄
    dp[1] = 1
    for i in range(2, 2*n+2):
        if i % 2 == 0 and tops[i//2-1] : # 짝수 & 뿔이 있는 경우
            dp[i] = (dp[i-1] * 2 + dp[i-2]) % 10007
        else : 
            dp[i] = (dp[i-1] + dp[i-2]) % 10007
            
    answer = dp[2*n+1]
    return answer