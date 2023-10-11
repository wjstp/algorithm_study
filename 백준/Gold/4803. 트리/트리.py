import sys
from collections import deque
input = sys.stdin.readline

def isTreeByBFS(start, parent):
    q = deque()
    q.append(start)
    parent[start] = 0
    while q :
        cnode = q.popleft()
        for a in adj_list[cnode]:
            if a == parent[cnode] :
                continue
            if parent[a] != -1:
                return False
            else:
                q.append(a)
                # 방문 처리시 부모노드를 저장한다
                parent[a] = cnode
    return True



tc = 0
while True :
    tc += 1
    n, m = map(int, input().split())
    if n == 0 and m == 0 :
        break
    adj_list = [[] * (n+1) for _ in range(n+1)] # 0번지 안씀
    for _ in range(m):
        node1, node2 = map(int, input().split())
        adj_list[node1].append(node2)
        adj_list[node2].append(node1)
            
            
    parent= [-1]*(n+1)
    cnt = 0
    for i in range(1, n+1):
        if parent[i] == -1: 
            if isTreeByBFS(i, parent): 
                cnt += 1
                    
    if cnt == 0 :
        print(f'Case {tc}: No trees.')
    elif cnt == 1 :
        print(f'Case {tc}: There is one tree.')
    else :
        print(f'Case {tc}: A forest of {cnt} trees.')