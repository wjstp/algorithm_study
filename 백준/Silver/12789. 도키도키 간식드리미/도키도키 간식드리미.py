from collections import deque

N = int(input())
arr = list(map(int, input().split())) # 대기열

deq = deque() # 대기열
n_stack = [] # 간식줄
delay = [] # 임시줄
# 대기열 큐로받기
for a in arr:
    deq.append(a)

# n_stack에 더 이상 추가할게 없는 경우까지 반복
while True :
    num = 0 if len(n_stack)==0 else n_stack[-1]
   
    if len(deq) != 0 and deq[0] == num+1:
        x = deq.popleft()
        n_stack.append(x)
    elif len(delay) != 0 and delay[-1] == num+1 :
        y = delay.pop()
        n_stack.append(y)
    elif len(deq) != 0:
        x = deq.popleft()
        delay.append(x)
    else :
        break        

if len(n_stack)== N :
    print('Nice')
else :
    print('Sad')
    

