import sys
input = sys.stdin.readline

N = int(input().rstrip())
p_arr = list(map(int, input().split())) # p_arr[i] i+1개의 카드가 들어있는 카드팩의 금액
dp = [0 for _ in range(N+1)] # dp[i] 카드 i장을 만드는 가장 작은 금액, 0~N
for i in range(1, N+1):
    flag = True
    for j in range(len(p_arr)):
        if j+1 <= i :
            if flag :
                flag = False
                dp[i] = dp[i-(j+1)] + p_arr[j]
            else :
                dp[i] = min(dp[i-(j+1)]+p_arr[j], dp[i])
      
    
print(dp[N])                

