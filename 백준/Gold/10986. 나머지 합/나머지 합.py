import sys

input = sys.stdin.readline
# 5로 나눌 때 나머지 1인것과 나머지 4인것을 합치면 나머지가 0이 된다.
N, M = map(int, input().split())
arr = list(map(int, input().split()))
mod_info = [0] * M  # 나머지 별 카운팅
for i in range(N) :
    if i > 0 :
        arr[i] = arr[i - 1] + arr[i]
    mod_info[arr[i] % M] += 1 

ans =  mod_info[0] if mod_info[0] > 1 else 0
for i in range(M):
    ans +=  (mod_info[i] * (mod_info[i]-1)) //2 # 바로 조합 계산

print(ans)