import sys
from collections import deque
input = sys.stdin.readline

N, M, R = map(int, input().split())
adj_list = [[] for _ in range(N+1)]
for _ in range(M):
    u, v = map(int, input().split())
    adj_list[u].append(v)
    adj_list[v].append(u)

for i in range(N+1) :
    adj_list[i].sort(reverse=True)

num = 1
num_arr = ['0']*(N+1)
bfsq = deque()
visited = [0]*(N+1)
bfsq.append(R)
visited[R] = 1
while bfsq :
    cnode = bfsq.popleft()
    num_arr[cnode] = str(num)
    num += 1
    for a in adj_list[cnode] :
        if visited[a] != 1:
            bfsq.append(a)
            visited[a] = 1
print('\n'.join(num_arr[1:]))