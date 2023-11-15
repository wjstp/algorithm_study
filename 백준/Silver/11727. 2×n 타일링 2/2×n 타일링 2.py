import sys
import math
input = sys.stdin.readline

'''
i-1 칸에서 +1
i-2 칸에서 + 2
'''
n = int(input().rstrip())
dp = [0]*(n+1)
dp[0] = dp[1] = 1
for i in range(2, n+1):
    dp[i] = (dp[i-1] + dp[i-2]*2)%10007
    
print(dp[n])