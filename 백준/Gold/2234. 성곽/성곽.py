'''
비트 마스킹 활용해서 시간 줄일 수 있는 것이 있는지 확인
벽 여부를 확인할 떄 비트마스킹 활용
서, 북, 동, 남
0, 1, 2, 3
ex) 동쪽에 벽이 있는지  : 해당 칸의 숫자 & 1<<2 == 1<<2 ? 있다 : 없다
'''

import sys
from collections import deque
input = sys.stdin.readline
dr = [0, -1, 0, 1]
dc = [-1, 0, 1, 0]

N, M = map(int, input().split())
arr = [list(map(int, input().split())) for _ in range(M)]

room_arr = [[0]*N for _ in range(M)]
# 각 공간이 몇번방인지 체크, 각 방에서 처음 발견하는 노드들은 다음 연산을 위해 배열에 저장한다   
room_number = 0
room_space = []
room_node = []
for r in range(M) : 
    for c in range(N)  :
        if not room_arr[r][c] : 
            room_number += 1
            q = deque()
            room_arr[r][c] = room_number
            q.append((r, c))
            room_node.append((r, c))
            area = 1
            while q : 
                cr, cc = q.popleft()
                for i in range(4) :
                    if (arr[cr][cc] & 1<<i) != 1<<i : 
                        nr, nc = cr + dr[i], cc + dc[i]
                        if 0<= nr < M and 0<= nc < N and not room_arr[nr][nc] : 
                            q.append((nr, nc))
                            room_arr[nr][nc] = room_number
                            area += 1
            room_space.append(area)

print(room_number)  
print(max(room_space))

# 1번방부터 인접한 방 탐색.
max_space = 0
for i in range(room_number):
    q = deque()
    visited = [[0] * N for _ in range(M)]
    q.append((room_node[i][0], room_node[i][1]))
    visited[room_node[i][0]][room_node[i][1]] = 1
    check_room = [0 for _ in range(room_number)]
    while q : 
        cr, cc = q.popleft()
        for j in range(4) :
            nr, nc = cr + dr[j], cc + dc[j]
            if 0<= nr < M and 0<= nc < N and not visited[nr][nc] : 
                if i + 1 == room_arr[nr][nc] : 
                    q.append((nr, nc))
                    visited[nr][nc] = 1
                else :
                    if room_arr[nr][nc] > i + 1 and not check_room[room_arr[nr][nc]-1] :  
                        # 다른방일 때 계산
                        max_space = max(max_space, room_space[i] + room_space[room_arr[nr][nc]-1])
                        check_room[room_arr[nr][nc]-1] = 1
                        visited[nr][nc] = 1
print(max_space)             
              
    
