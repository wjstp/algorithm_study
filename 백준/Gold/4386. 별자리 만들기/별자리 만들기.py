import sys

input = sys.stdin.readline


def find_set(x):
    if parent[x] == x :
        return x
    else :
        parent[x] = find_set(parent[x])
        return parent[x]


def union(a, b):
    p_a = find_set(a)
    p_b = find_set(b)

    if p_a < p_b:
        parent[p_b] = a
    else:
        parent[p_a] = b


n = int(input())    
stars = [tuple(map(float, input().split())) for _ in range(n)]  # 별의 좌표 정보
dist = []   # 두개의 별 사이의 거리를 저장할 리스트


# 별 사이의 거리를 계산하여 dist에 저장
for i in range(n - 1):
    for j in range(i + 1, n):
        dist.append(
            (((stars[i][0]-stars[j][0])**2+(stars[i][1]-stars[j][1])**2)**0.5, i, j))

dist.sort()

answer, parent = 0, [i for i in range(n)]

for d, a, b in dist:
    if find_set(a) != find_set(b):
        union(a, b)
        answer += d

print("{:.2f}".format(answer))