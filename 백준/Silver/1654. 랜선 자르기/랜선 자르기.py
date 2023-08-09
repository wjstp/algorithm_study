'''
시간제한 : 2초
가장 긴 랜선을 기준으로(엄청 긴 랜선 하나에서 원하는 길이만큼 찾을 수 있으므로) 탐색 시작
이분 탐색. 반으로 나눴을 때의 길이로 k만큼의 개수를 얻을 수 있다면
그것보다 더 큰 수로 탐색 
k개를 만들 수 없다면 더 작은 수로 탐색
'''
import sys 
input = sys.stdin.readline

def binarySearch(arr, l_arr, arr_len, key) :
    start = 0
    end = arr_len # 배열로 접근하는게 아니므로 -1을 하면 안됨 (1~ arr_len)
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
    return 1 # mid로 했을 떄는 틀렸습니다가 뜨는데 1로 하면 맞았다고 뜸;;

K, N = map(int, input().split())
l_arr = []
for _ in range(K) :
    l_arr.append(int(input()))

# 1~제일 큰수, 랜선길이 배열(몇개로 나뉘는지 확인 목적), 그 길이, 원하는 랜선의 개수
print(binarySearch(range(1, max(l_arr)+1), l_arr, max(l_arr), N))
