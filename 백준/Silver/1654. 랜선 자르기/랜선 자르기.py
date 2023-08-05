import sys 
input = sys.stdin.readline

def binarySearch(arr, l_arr, arr_len, key) :
    start = 0
    end = arr_len # 
    flag = 0
    while start <= end:
        mid = (start+end) //2
        if mid == 0 :
            break
        line_num = 0
        for l in l_arr :
            line_num += l // mid
        if line_num >= key :
            flag = 1
            start = mid + 1
            ans_mid = mid # 마지막에 key보다 작은 경우까지 탐색할 경우를 대비
        else :
            end = mid - 1
    if flag == 1:
        return ans_mid
    return 1

K, N = map(int, input().split())
l_arr = []
for _ in range(K) :
    l_arr.append(int(input()))

# 1~제일 큰수, 랜선길이 배열(몇개로 나뉘는지 확인 목적), 그 길이, 원하는 랜선의 개수
print(binarySearch(range(1, max(l_arr)+1), l_arr, max(l_arr), N))


                 