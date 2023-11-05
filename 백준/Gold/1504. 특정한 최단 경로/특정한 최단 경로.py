import sys
import heapq

input = sys.stdin.readline
INF = 10e9
def dijkstra(start, end):
    global adj_list, INF
    pq = []
    
    distance = [INF] * (N + 1) # 0번지는 안쓴다    
    heapq.heappush(pq, [0, start])
    distance[start] = 0
    
    while pq :
        cweight, cnode = heapq.heappop(pq)
        if distance[cnode] < cweight :
            continue
        for nweight, nnode in adj_list[cnode] :
            next = nweight + distance[cnode]
            if next < distance[nnode]:
                distance[nnode] = next
                heapq.heappush(pq, [next, nnode])
                
    return distance[end]
    

N, E = map(int, input().split())
adj_list = [[] for _ in range(N+1)] # 0번지는 쓰지 않는다

for _ in range(E):
    a, b, c = map(int, input().split())
    adj_list[b].append([c, a])
    adj_list[a].append([c, b])


u, v = map(int, input().split())

'''
1 -> u // v -> N
1 -> v // u -> N
'''
cost = dijkstra(u, v)
start_to_u = dijkstra(1, u)
start_to_v = dijkstra(1, v)
v_to_end = dijkstra(v, N)
u_to_end = dijkstra(u, N)

route1 = start_to_u + v_to_end + cost
route2 = start_to_v + u_to_end + cost
# route3 = start_to_u + u_to_end + cost*2
# route4 = start_to_v + v_to_end + cost*2


# if route1 > INF and route2 > INF and route3 > INF and route4 > INF:
if route1 >=INF and route2 >= INF :
    print(-1)
else :
    # print(min(route1, route2, route3, route4))
    print(min(route1, route2))

    