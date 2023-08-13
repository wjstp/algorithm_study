#deque의 rotate라는 함수를 쓰면 매우매우 간단하게 풀린다..
N= int(input())
arr = list(map(int, input().split()))
info_arr = []
for i in range(N) :
    info_arr.append((i+1, arr[i])) # 풍선번호, 안에 들어있는 수

ans_arr = []
idx = 0
while True :
    x = info_arr.pop(idx)
    ans_arr.append(x[0])
    if len(info_arr) == 0 :
        break
    if x[1] > 0 :
        idx = (idx +x[1]-1)%len(info_arr)
    else :
        idx = (idx +x[1])%len(info_arr)
    
    if idx < 0 :
        idx += len(info_arr)
print(*ans_arr)
