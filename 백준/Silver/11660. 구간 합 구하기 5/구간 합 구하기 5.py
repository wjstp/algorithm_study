import sys
input = sys.stdin.readline

N, M = map(int, input().split())
arr = [list(map(int, input().split())) for _ in range(N)]


for r in range(N):
    for c in range(N):
        if c == 0 :
            continue
        else :
            arr[r][c] = arr[r][c]+arr[r][c-1]

for c in range(N):
    for r in range(N):         
        if r == 0 :
            continue
        else :
            arr[r][c] = arr[r][c] + arr[r-1][c]


ans_arr = []
for _ in range(M):
    x1, y1, x2, y2 = map(int, input().split())
    if x1 == 1 and y1 == 1:
        ans = arr[x2-1][y2-1] 
    elif x1 == 1 :
        ans = arr[x2-1][y2-1] - arr[x2-1][y1-2]
    elif y1 == 1 :
        ans = arr[x2-1][y2-1] - arr[x1-2][y2-1]
    else :
        ans = arr[x2-1][y2-1] - arr[x1-2][y2-1] - arr[x2-1][y1-2] + arr[x1-2][y1-2]
    ans_arr.append(ans)


print('\n'.join(map(str, ans_arr)))

