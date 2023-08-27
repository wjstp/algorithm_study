'''
위에 한줄 이상 하얀색 W
다음 몇줄(한줄 이상) 파란색 B
나머지 줄(한줄 이상) 빨간색 R

가운데서 모두 B인 경우는 있어도 되지만, B가 한번도 나오지 않으면 안되고
순서도 WBR 순이어야함
이전 글자를 보고 다음 글자 고르기
'''
nom = [['W', 'B'], ['B', 'R'], ['R']]
def dfs(x, level, arr, num): # 글자수, 몇글자까지 썼는지
    global ans, flag
    if x == 'B': # B가 한번이라도 나오는지 확인용
        flag = 1
    if level == N-1 :
        if flag :
            ans = min(num, ans)
        return
    
    if x == 'W': nomCheck = nom[0]  # w나 b  
    elif x == 'B': 
        nomCheck = nom[1] # b나 r
    else: nomCheck = nom[2] # r

    for clr in nomCheck:
        # 글자 바꾸는 수 계산
        cnt = 0
        for j in range(M):
            if arr[level][j] != clr:
                cnt += 1
        # 현재 글자랑 레벨 넣기
        num += cnt
        dfs(clr, level+1, arr, num)
        num -= cnt

T = int(input())
for tc in range(1, T+1):
    N, M = map(int, input().split()) #가로, 세로3~50 
    arr = []
    for _ in range(N):
        row = list(input())
        arr.append(row)
    
    ans = 2600
    num = 0
    flag = 0
    # 제일 첫줄과 제일 마지막 줄은 색깔 고정
    for i in range(M):
        if arr[0][i] !='W':
            num += 1
    for i in range(M):
        if arr[N-1][i] != 'R':
            num += 1
    dfs('W', 1, arr, num)
    
    print(f'#{tc} {ans}')