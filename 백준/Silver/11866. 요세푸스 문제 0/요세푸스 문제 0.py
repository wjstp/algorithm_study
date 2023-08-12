import sys
input = sys.stdin.readline

N, K = map(int, input().split())
arr = list(range(1, N+1))

num = K-1
ans_arr = [] 
while True :
    x = arr.pop(num)
    ans_arr.append(x)
    # 종료조건
    if len(arr)== 0 :
        break
    # 인덱스 조정
    num += K-1
    while num > len(arr)-1 :
        num -= len(arr)
        
print('<'+', '.join(map(str, ans_arr))+'>')