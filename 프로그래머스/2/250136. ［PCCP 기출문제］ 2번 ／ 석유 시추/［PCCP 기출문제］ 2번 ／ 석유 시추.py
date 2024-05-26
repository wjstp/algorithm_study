from collections import deque
dr = [0, 1, 0, -1]
dc = [-1, 0, 1, 0]

def bfs(r, c, land) :
    global visited, n, m
    area = 0
    q = deque()
    q.append((r, c))
    visited[r][c] = 1
    check = set()
    while q :
        cr, cc = q.popleft()
        area += 1
        check.add(cc)
        for i in range(4) :
            nr, nc = cr + dr[i], cc + dc[i]
            if 0<= nr < n and 0<= nc < m and not visited[nr][nc] and land[nr][nc] == 1:
                q.append((nr, nc))
                visited[nr][nc] = 1

    return area, check

def solution(land):
    global visited, n, m
    answer = 0
    n = len(land) # 세로
    m = len(land[0]) # 가로
    visited = [[0 for _ in range(m)] for _ in range(n)]
    # 구역별로 표시하고 해당 구역의 면적 기록
    # 면적을 기록하면서 그 면적이 어디 컬럼들에 포함되는지 기록
    memo = {i :0for i in range(m)}
    for r in range(len(land)) : 
        for c in range(len(land[r])) :
            if not visited[r][c] and land[r][c] == 1:
                area, check = bfs(r, c, land)
                for e in list(check) :
                    memo[e] += area
    return max(memo.values())