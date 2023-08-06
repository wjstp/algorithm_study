def countingSort(input_arr):
    # 1. count_arr input_arr의 가장 큰 숫자+1 크기로 만들기
    count_arr = [0]* (max(input_arr)+1)
    # 2. count_arr input_arr 요소 개수만큼 값 채우기
    for ele in input_arr:
        count_arr[ele] += 1
    # 3. 위치 파악 위해 count_arr 값 변경
    for idx in range(1, len(count_arr)) :
        count_arr[idx]+= count_arr[idx-1]
    # 4. out_put배열 0으로 input_arr 크기만큼 값 채우기
    output_arr = [0]*len(input_arr)
    # 5. input_arr의 가장 뒤에 값부터 위치 찾아주기. count_Arr는 개수를 담고 있으므로 위치를 넣어줄 때는 -1을 해주어야 한다. 
    for idx in range(len(output_arr)-1, -1, -1):
        count_arr[input_arr[idx]] -= 1
        output_arr[count_arr[input_arr[idx]]] = input_arr[idx]

    return output_arr

T = int(input())
for tc in range(1, T+1) :
    N = int(input())
    a_list = list(map(int, input().split()))
    print(f'#{tc}', end =' ')
    print(*countingSort(a_list))