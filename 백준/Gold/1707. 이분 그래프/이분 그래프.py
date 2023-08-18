import sys
from collections import deque
input = sys.stdin.readline
K = int(input().rstrip())
for _ in range(K) :
    V, E = map(int, input().split())
    adj_list = [[] for _ in range(V+1)]
    for _ in range(E) :
        x, y = map(int, input().split())
        adj_list[x].append(y)
        adj_list[y].append(x)
    color_arr = [0]*(V+1)
    visited = [0]*(V+1)
    bfsq = deque()
    # bfs큐로 돌면서 색을 칠해준다.
    # visited로 걸러주기 전에 나와야될 색이 안나오면 fail
    answer = 'YES'
    for i in range(1, V+1) :
        
        if visited[i] != 1 :
            bfsq.append(i) # 정점 번호
            visited[i] = 1
            color_arr[i] = 1
            while bfsq :
                cnode = bfsq.popleft()
                ccolor = color_arr[cnode]
                for a in adj_list[cnode]:
                    if visited[a] == 0 :
                        if ccolor == 1 :
                            color_arr[a] = 0
                        else :
                            color_arr[a] = 1
                        bfsq.append(a)
                        visited[a] = 1
    # 색 확인
    for i in range(len(adj_list)):
        for a in adj_list[i]:
            if color_arr[i] == color_arr[a] :
                answer = 'NO'

    print(answer)