'''
N : 연구소의 크기 5이상 50이하
M : 놓을 수 있는 바이러스의 수. 1이상 10이하
0 : 빈칸, 1 : 벽, 2: 바이러스
모든 칸에 바이러스가 퍼질수 있는 최소 시간 출력
바이러스 퍼뜨릴 수 없으면 -1 출력
'''

import sys
from collections import deque
input = sys.stdin.readline
dr = [0, 1, 0, -1]
dc = [1, 0, -1, 0]

# 바이러스 퍼뜨려보기 
def spread():
    global choiced
    q = deque()
    visited = [[-1]*N for _ in range(N)]
    # 큐에 바이러스 위치인 곳 담기 
    for i in range(len(choiced)):
        if choiced[i] :
            q.append((vir[i][0], vir[i][1]))
            visited[vir[i][0]][vir[i][1]] = 0
    while q :
        cr, cc = q.popleft()
        ctime = visited[cr][cc]
        for i in range(4):
            nr, nc = cr + dr[i], cc + dc[i]
            if 0<= nr < N and 0<=nc < N and arr[nr][nc] != 1 and visited[nr][nc] == -1 :
                q.append((nr, nc))
                visited[nr][nc] = ctime + 1
    
    max_time = 0            
    for r in range(N):
        for c in range(N):
            if arr[r][c] != 1 and visited[r][c] == -1 :
                return 'False'
            max_time = max(max_time, visited[r][c])
    return max_time

# 바이러스 위치 조합
def select(cnt, start):
    global choiced, vir, min_time
    if cnt == M :
        # 바이러스 퍼뜨려봐서 다 퍼질때 시간 최소시간인지 확인하기
        time = spread()
        if time != 'False':
            min_time = min(time, min_time)
        return
    for i in range(start, len(vir)):
        if not choiced[i] :
            choiced[i] = 1
            select(cnt+1, i+1)
            choiced[i] = 0

    


N, M = map(int, input().split())
arr = [list(map(int, input().split())) for _ in range(N)]
# 2 위치 다 고르기
vir = []
for r in range(N):
    for c in range(N):
        if arr[r][c] == 2 :
            vir.append([r, c])
choiced = [0]*len(vir)
min_time = 10e9
select(0, 0)
if min_time == 10e9 :
    min_time = -1
print(min_time)
