import sys
input = sys.stdin.readline

N = int(input().rstrip())
coins = [1, 2, 5, 7]
dp = [100000 for _ in range(N+1)]
dp[0] = 0
for i in range(1, N+1):
    for coin in coins :
        if coin <= i : 
            dp[i] = min(dp[i-coin]+1, dp[i]) 

print(dp[N])