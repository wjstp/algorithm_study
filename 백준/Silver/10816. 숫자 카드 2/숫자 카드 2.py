N = int(input())
s_arr = list(map(int,input().split()))
M = int(input())
c_arr = list(map(int, input().split())  )

n_plus_arr = [0]*10000001 # 0~10000000
n_minus_arr = [0]*10000001 # -1 ~-10000000
for s in s_arr :
    if s >= 0 :
        n_plus_arr[s] += 1
    else :
        n_minus_arr[s] += 1
ans_arr = []
for c in c_arr :
    if c >= 0 :
        ans_arr.append(n_plus_arr[c])
    else :
        ans_arr.append(n_minus_arr[c])

print(*ans_arr)