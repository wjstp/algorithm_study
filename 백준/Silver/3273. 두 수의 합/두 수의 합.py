import sys
input = sys.stdin.readline
n = int(input().rstrip())
arr = list(map(int, input().split()))
x = int(input().rstrip())

arr.sort()
# [1, 2, 3, 5, 7, 9, 10, 11, 12]
end = n-1
cnt = 0
for start in range(n):
    while arr[start]+arr[end] > x and end >start :
        end -= 1
    if start == end :
        break
    if arr[start] + arr[end] == x :
       cnt += 1

print(cnt)