money = [50000, 10000, 5000, 1000, 500, 100, 50, 10]
T = int(input())
for tc in range(1, T+1):
    N = int(input())
    ans = []
    for i in range(8):
        cnt = 0
        while N >= money[i]:
            cnt += N//money[i]
            N = N % money[i]
        ans.append(cnt)
    print(f'#{tc}')
    print(*ans)
