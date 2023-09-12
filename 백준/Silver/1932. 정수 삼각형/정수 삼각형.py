import sys
input = sys.stdin.readline
N = int(input().rstrip())
arr = []
for _ in range(N):
    row = list(map(int, input().split()))
    arr.append(row)

for i in range(1, N):
    arr[i][0] = arr[i][0] + arr[i-1][0]
    arr[i][i] = arr[i][i] + arr[i-1][i-1] 
    for j in range(1, i):
        arr[i][j] = arr[i][j]+ max(arr[i-1][j-1], arr[i-1][j])
    
print(max(arr[N-1]))
    
