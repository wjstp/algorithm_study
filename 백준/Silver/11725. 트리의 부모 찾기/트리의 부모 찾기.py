import sys
from collections import deque

input = sys.stdin.readline

N = int(input().rstrip())
adj_list = [[]*(N+1) for _ in range(N+1)] # 0번지 안씀
for i in range(N-1):
    node1, node2 = map(int, input().split())
    adj_list[node1].append(node2)
    adj_list[node2].append(node1)
# 루트 노드는 1번
q = deque()
visited = [0]*(N+1)

parent = [0]*(N+1)
q.append(1)
visited[1] = 1
while q :
    cnode = q.popleft()
    for a in adj_list[cnode]:
        if visited[a]!= 1 :
            q.append(a)
            parent[a] = cnode
            visited[a] = 1
            
for i in range(2, N+1):
    print(parent[i])
    