import sys
from collections import deque
input = sys.stdin.readline
q = deque()
N = int(input())
for _ in range(N) :
    cmd= list(input().split())
    if cmd[0] == '1':
        q.appendleft(cmd[1])
    elif cmd[0] == '2':
        q.append(cmd[1])
    elif cmd[0] == '3' :
        print(-1) if len(q)== 0 else print(q.popleft()); 
    elif cmd[0] == '4' :
        print(-1) if len(q) == 0 else print(q.pop()); 
    elif cmd[0] == '5' :
        print(len(q))
    elif cmd[0] == '6' :
        print(0) if len(q) else print(1)
    elif cmd[0] == '7' :
        print(-1) if len(q) == 0 else print(q[0])
    elif cmd[0] == '8' :
        print(-1) if len(q) == 0 else print(q[-1]) 