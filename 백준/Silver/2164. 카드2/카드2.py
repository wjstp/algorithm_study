import sys
from collections import deque
input = sys.stdin.readline
N = int(input())
q = deque()
for i in range(1, N+1):
    q.append(i)
while len(q) > 1 :
    # 제일 앞에 수 빼기
    q.popleft()
    x = q.popleft()
    q.append(x)  

print(q[0])