T = int(input())
for tc in range(1, T+1):
    N, K = map(int, input().split()) # 0~100
    arr = list(map(int, input().split()))
    arr.sort()
    score = sum(arr[N-1:N-1-K:-1])
    print(f'#{tc} {score}')