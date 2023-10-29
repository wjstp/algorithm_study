import sys
import heapq
input = sys.stdin.readline

while True :
    m, n = map(int, input().split())
    if m == 0 and n == 0 :
        break
    adj_list = [[] for _ in range(m)]
    max_money = 0
    for _ in range(n):
        x, y, z = map(int, input().split())
        adj_list[x].append((y, z))
        adj_list[y].append((x, z))
        max_money += z

    MST = [0] * m
    pq = []
    heapq.heappush(pq, (0, 0))
    total_money = 0
    while pq :
        cweight, cnode = heapq.heappop(pq)
        if MST[cnode] == 1 :
            continue
        MST[cnode] = 1
        total_money += cweight
        for nnode, nweight in adj_list[cnode]:
            if not MST[nnode] :
                heapq.heappush(pq, (nweight, nnode))

    print(max_money - total_money)
            
