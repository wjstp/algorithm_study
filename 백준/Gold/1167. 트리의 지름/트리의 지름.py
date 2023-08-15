'''
트리: 사이클이 없이 모든 정점이 연결되어 있는 그래프
어디를 들어올리든 루트, 트리
임의의 두 노드 간의 경로는 하나밖에 없다. (트리는 싸이클이 없기 떄문)

'''



from collections import deque
V = int(input()) # 노드의 개수 1~10000
adj_list = [[] for _ in range(V+1)]
bfsq = deque()
visited = [0]*(V+1)
flag = True
for _ in range(V) :
    info = list(map(int, input().split()))
    for i in range(1, len(info)-1, 2):
        if i & 1 :
            adj_list[info[0]].append([info[i], info[i+1]])
            if flag :
                flag = False
                bfsq.append((info[0], 0)) 
                visited[info[0]] = 1 
                max_len = [info[0],0] # 길이, 노드

# 임의의 한 노드에서 가장 먼 노드 찾기
while len(bfsq) :
    cnode = bfsq[0][0]
    clen = bfsq[0][1]
    bfsq.popleft()
    if clen > max_len[0]:
        max_len = [clen, cnode]
    for arr in adj_list[cnode] :
        if visited[arr[0]] != 1 :
            visited[arr[0]] = 1
            bfsq.append((arr[0], clen+arr[1]))


visited = [0]*(V+1)
# 그 노드에서 가장 먼 노드 찾기 
bfsq.append((max_len[1], 0))
visited[max_len[1]] = 1
max_length = 0
while len(bfsq) :
    cnode = bfsq[0][0]
    clen = bfsq[0][1]
    bfsq.popleft()
    max_length = max(clen, max_length)
    for arr in adj_list[cnode] :
        if visited[arr[0]] != 1 :
            visited[arr[0]] = 1
            bfsq.append((arr[0], clen+arr[1]))

print(max_length)
    


