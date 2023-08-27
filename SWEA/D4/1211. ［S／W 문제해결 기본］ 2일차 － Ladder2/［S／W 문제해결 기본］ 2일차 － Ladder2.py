dc = [-1, 1]
for tc in range(1, 11):
    t = input()
    arr =[]
    for _ in range(100):
        row = list(map(int, input().split())) 
        arr.append(row)
    min_length = 10000
    ans = -1
    # 첫 줄에서 1인 것들로 시작
    for i in range(100):
        if arr[0][i] == 1 :
            length = 0
            r = 0
            c = nom = i
            while r != 99 :
                flag = 0
                # 좌우에 길이 있는지 계속 체크하면서 내려갈 것
                for j in range(2):
                    while 0 <= c+dc[j] < 100 and arr[r][c+dc[j]] == 1:
                        c = c+dc[j]
                        length += 1
                        flag =1
                    if flag == 1 : break
                r += 1
                length += 1
        if min_length > length :
            min_length = length
            ans = nom
                     
    print(f'#{tc} {ans}')