import sys
input = sys.stdin.readline

N, S = map(int, input().split())
arr = list(map(int, input().split()))

end = 0
a_sum = 0
answer = 10000000000000000
min_length = N
for start in range(N):
    while a_sum < S and end < N:
        a_sum += arr[end]
        end += 1 
    if start == end-1 and a_sum >= S:
        answer = 1
        break
    if start == 0 and end == N and a_sum < S :
        answer = 0
        break
    if a_sum >= S :
        min_length = min(min_length, end - start)  
    a_sum -= arr[start]

answer = min(answer, min_length)
print(answer)