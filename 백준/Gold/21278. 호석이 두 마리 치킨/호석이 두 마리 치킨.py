'''
조합으로 두개씩 골라서 bfs
'''
import sys
from itertools import combinations
from collections import deque
input = sys.stdin.readline

N, M = map(int, input().split())
adj_list = [[] for _ in range(N+1)] # 0번지 사용 x
for _ in range(M):
    A, B = map(int, input().split())
    adj_list[A].append(B)
    adj_list[B].append(A)

place1 = place2 = 0    
combi = list(combinations(range(1, N+1), 2))
# print(combi)
min_move = 10e9
for t1, t2 in combi :
    a_sum = 0
    for i in range(1, N+1):
        if i != t1 and i != t2 :
             q = deque()
             visited = [0 for _ in range(N+1)]
             q.append((i, 0))
             visited[i] = 1
             while q :
                cnode, cmove = q.popleft()
                if cnode == t1 or cnode == 2 :
                    # 거리 계산
                    a_sum += cmove
                    break
                for a in adj_list[cnode] : 
                    if not visited[a] :
                        visited[a] = 1
                        q.append((a, cmove + 1))
    if a_sum < min_move :
        place1, place2 = t1, t2
        min_move = a_sum
        break
    elif a_sum == min_move :
        if place1 > t1:
            place1, place2 = t1, t2
        elif place1 == t1 and place2 > t2 :
            place1, place2 = t1, t2
        break
    

print(place1, place2, min_move*2)
                        