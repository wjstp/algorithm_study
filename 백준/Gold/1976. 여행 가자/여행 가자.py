import sys
sys.setrecursionlimit(10**9)
input = sys.stdin.readline

def root(node):
    if parent[node] == node :
        return node
    else :
        parent[node] = root(parent[node])
        return parent[node]

def union(x, y):
    parent_x = root(x)
    parent_y = root(y)
    if parent_x == parent_y:
        return
    else :
        if parent_x > parent_y :
            parent[parent_x] = parent_y
        else :
            parent[parent_y] = parent_x
        return



N = int(input().rstrip()) # 도시 수
M = int(input().rstrip()) # 여행 계획한 도시 수
parent = [i for i in range(N)] # 0번지부터 쓴다
# N번 연결정보 받기, 좌우 대각선 방향 삼각형 정보만 확인해도 된다..
for num in range(N):
    linked = list(map(int, input().split()))
    for i in range(N):
        if not linked[i]:
            continue
        else :
            union(num, i)

# 여행갈 도시 정보 받기
flag = True
destination = list(map(int, input().split()))
for i in range(len(destination)-1):
    if root(destination[i]-1) == root(destination[i+1]-1):
        continue
    else :
        flag = False
        break

if not flag :
    print('NO')
else :
    print('YES')
            


