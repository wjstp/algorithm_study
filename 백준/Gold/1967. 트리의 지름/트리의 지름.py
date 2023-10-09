import sys
input = sys.stdin.readline
from collections import deque


def findDiameter(start):
    q = deque()
    INF = 10e9
    distance = [INF]*(n+1) # 0번지는 안씀
    q.append(start) # 노드 위치
    distance[start] = 0
    max_distance = 0
    fnode = -1
    while q:
        cnode = q.popleft()
        ccost = distance[cnode]
        if ccost > max_distance :
            fnode = cnode
            max_distance = ccost
        for node, cost in adj_list[cnode]:
            if distance[node] > ccost + cost:
                distance[node] = ccost + cost
                q.append(node)

    return fnode, max_distance
n = int(input().rstrip())
adj_list = [[]*(n+1) for _ in range(n+1)]
for _ in range(n-1):
    pnode, chnode, cost = map(int, input().split())
    adj_list[pnode].append((chnode, cost))
    adj_list[chnode].append((pnode, cost))
# 1에서 가장 먼 노드 찾기
rnode = findDiameter(1)[0]
diameter = findDiameter(rnode)[1]
print(diameter)