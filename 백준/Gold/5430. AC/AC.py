import sys
from collections import deque
input = sys.stdin.readline

T = int(input().rstrip())
for _ in range(T) :
    cmd = input().rstrip()
    n = int(input().rstrip())
    arr_str = input().rstrip()
    # 배열로 변환
    arr = deque()
    tmp = ''
    flag = False
    for ele in arr_str:
        if ele.isdigit() :
            tmp += ele
            flag = True
        else :
            if flag :
                arr.append(tmp)
            tmp = ''
    flag = True
    exitFlag = False
    cnt = 0
    for c in cmd :
        # print(arr)
        if c == 'D' :
            if len(arr) :
                if flag :
                    arr.popleft()
                else :
                    arr.pop()      
            else :
                arr = 'error'
                exitFlag = True
                break
        elif c == 'R':
            flag = not flag
            cnt += 1
        
    if exitFlag :
        print(arr)
    else :
        if cnt %2 : # 뒤집히는 경우
            ans = '[' + ','.join(reversed(arr)) +']'
        else :
            ans = '[' + ','.join(arr) +']'
        print(ans)