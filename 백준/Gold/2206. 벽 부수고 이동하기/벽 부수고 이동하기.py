import sys
from collections import deque

input = sys.stdin.readline

dr = [0, 1, 0, -1]
dc = [1, 0, -1, 0]
N, M = map(int, input().split())
arr = [list(map(int, input().rstrip())) for _ in range(N)] # 0, 0~ N-1, M-1

bfsq = deque()
visited1 = [[0]*M for _ in range(N)] # 벽을 뚫지 않았을 떄
visited2 = [[0]*M for _ in range(N)] # 벽을 뚫고난 이후

bfsq.append((0, 0, 1, 0)) # r, c, 이동 횟수, 벽을 뚫었다면 1 아니라면 0
visited1[0][0] = 1

ans = -1
while bfsq :
    x = bfsq.popleft()
    cr, cc, ccnt, barrier = x[0], x[1], x[2], x[3]
    if cr == N-1 and cc == M-1 :
        ans = ccnt
        break
    for i in range(4):
        nr, nc = cr + dr[i], cc + dc[i]
        if 0<=nr<N and 0<=nc<M :
            # 1을 만났을 때
            if arr[nr][nc] == 1 and barrier != 1 and visited1[nr][nc] != 1 :
                bfsq.append((nr, nc, ccnt+1, 1))
                visited1[nr][nc] = 1
            # 0을 만났을 떄
            elif arr[nr][nc] == 0 and visited1[nr][nc] == 0 : # 벽을 안뚫은게 먼저 왔다면 벽을 뚫은 경우를 카운트해줄 필요가 없다. (visited1이 1일 떄 벽을 뚫고온 경우를 체크해줄 필요가 없음)
                if barrier and visited2[nr][nc] == 0 : # 벽을 뚫은게 먼저 도착한 경우
                    bfsq.append((nr, nc, ccnt+1, 1))
                    visited2[nr][nc] = 1
                elif barrier == 0 : # 벽을 뚫지 않은게 먼저 온 경우
                    bfsq.append((nr, nc, ccnt+1, 0))
                    visited1[nr][nc] = 1
print(ans)
                    