# bfsq에서 intensity 갱신, visited에 나보다 긴 intensity가 기록되어 있다면 q에 삽입
from collections import deque
def solution(n, paths, gates, summits):
    answer = []
    
    visited = [10e9] * (n + 1) # 1부터 기록
    adjList = {}
    for path in paths :
        i, j, w = path[0], path[1], path[2]
        adjList.setdefault(i, []).append((j, w))
        adjList.setdefault(j, []).append((i, w))
    
    q = deque()
    for gate in gates :
        q.append((0, gate)) # intensity, 현재 노드
        visited[gate] = 0
    
    min_summit = 10e9
    min_intensity = 10e9
    summit_set = set(summits)
    while q :
        intensity, node = q.pop()
        
        # 정상에 도착했을 경우
        if node in summit_set :
            if min_intensity > intensity : 
                min_intensity = intensity
                min_summit = node
            elif min_intensity == intensity and min_summit > node:
                min_summit = node
            continue
    
        if visited[node] < intensity :
            continue
        if min_intensity < intensity :
            continue
            
        for adj in adjList.get(node) :
            next = adj[0]
            cost = adj[1]
            new_intensity = intensity
            if cost > intensity :
                new_intensity = cost
            if visited[next] > new_intensity :
                visited[next] = new_intensity
                q.append((new_intensity, next))
    
    return [min_summit, min_intensity]
     


