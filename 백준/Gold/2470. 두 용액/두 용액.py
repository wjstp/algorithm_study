import sys
input = sys.stdin.readline
N = int(input())
arr = list(map(int, input().split()))
arr.sort()
start = 0
end = N-1
ans = []
min_sub = 2000000000
while start<end :    
    if arr[start]+ arr[end] == 0 :
        ans = [arr[start], arr[end]]
        break
    while arr[start] + arr[end] >0 and end != start:
        if min_sub > abs(arr[start]+arr[end]):
            ans = [arr[start], arr[end]]
            min_sub = abs(arr[start]+arr[end])
        end -= 1
    while arr[start] + arr[end] <0 and end != start:
        if min_sub > abs(arr[start]+arr[end]):
            ans = [arr[start], arr[end]]
            min_sub = abs(arr[start]+arr[end])
        start += 1
print(*ans)