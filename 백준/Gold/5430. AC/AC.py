import sys
from collections import deque
input = sys.stdin.readline

T = int(input().rstrip())
for _ in range(T) :
    cmd = input().rstrip()
    n = int(input().rstrip())
    arr = deque(input().rstrip()[1:-1].split(','))
    if n == 0 :
        arr = deque()
    is_reversed = False
    not_error = True
    cnt = 0
    for c in cmd :
        if c == 'D' :
            if not len(arr) :
                print('error')
                not_error = False
                break
            if is_reversed :
                arr.pop()      
            else :
                arr.popleft()
        elif c == 'R':
            is_reversed = not is_reversed
            cnt += 1
        
    if not_error :
        if is_reversed : # 뒤집히는 경우
            print('[' + ','.join(reversed(arr)) +']')
        else :
            print('[' + ','.join(arr) +']')
        

