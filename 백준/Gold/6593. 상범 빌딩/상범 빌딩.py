import sys
from collections import deque
input = sys.stdin.readline

dl = [0, 0, 0, 0, 1, -1]
dr = [-1, 1, 0, 0, 0, 0]
dc = [0, 0, 1, -1, 0, 0]

while True :
    L, R, C = map(int, input().split())
    if L == 0 and R == 0 and C == 0 :
        break
    building_arr = [[[0]*C for _ in range(R)] for _ in range(L)]

    bfsq = deque()
    visited = [[[0]*C for _ in range(R)] for _ in range(L)]            
    for l in range(L):
        for r in range(R):
            tmp = input()
            for c in range(C):
                building_arr[l][r][c] = tmp[c]
                if building_arr[l][r][c] == 'S':
                    bfsq.append((l, r, c, 0)) # 마지막은 시간
                    visited[l][r][c] = 1
        input()        

    flag = True
    while bfsq :
        cl, cr, cc, ch = bfsq.popleft()
        if building_arr[cl][cr][cc] == 'E':
            print(f'Escaped in {ch} minute(s).')
            flag = False
            break
        for i in range(6):
            nl = cl + dl[i]
            nr = cr + dr[i]
            nc = cc + dc[i]
            if 0 <= nl < L and 0 <= nr < R and 0 <= nc < C :        
                if building_arr[nl][nr][nc] != '#' and not visited[nl][nr][nc]:
                    bfsq.append((nl, nr, nc, ch+1))
                    visited[nl][nr][nc] = 1 

    if flag :
        print('Trapped!')
               
    
                       
