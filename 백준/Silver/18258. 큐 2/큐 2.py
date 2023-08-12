import sys
from collections import deque
input = sys.stdin.readline
N = int(input())
q = deque()
for _ in range(N) :
    cmd= list(input().split())
    if cmd[0] == 'push':
        q.append(cmd[1])
    elif cmd[0] == 'front':
        print(-1) if len(q)==0 else print(q[0])
    elif cmd[0] == 'back':
        print(-1) if len(q)== 0 else print(q[-1])
    elif cmd[0] == 'size':
        print(len(q))
    elif cmd[0] == 'empty':
        print(1) if len(q) == 0 else print(0)
    elif cmd[0] == 'pop' :
        if len(q) == 0 :
            print(-1)
        else :
            x = q.popleft()
            print(x)
