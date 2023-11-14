def solution(n):
    answer = 0
    # 1부터 n까지 
    limit = 100000
    for i in range(1, n+1):
        a_sum = 0
        cnt = 0
        for j in range(i, n+1):
            if cnt > limit :
                break
            cnt += 1
            a_sum += j
            if a_sum == n :
                answer += 1
            if a_sum > n :
                limit = cnt
                break
    return answer