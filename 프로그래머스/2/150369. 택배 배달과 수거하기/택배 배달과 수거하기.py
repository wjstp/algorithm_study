def solution(cap, n, deliveries, pickups):
    answer = 0
    deliver = 0
    pickup = 0
    for i in range(n-1, -1, -1):
        cnt = 0
        # 배달이나 수거중 가장 멀리 있는 것을 기준으로 그걸 다 가져오기 위해 몇번 이동해야하는지, 그 때 얼마나 배달, 수거할 수 있는지 지정
        while deliveries[i] > deliver or pickups[i] > pickup :
            cnt += 1 # 배달, 수거할 cap이 부족한 경우에만 이 거리로 이동
            deliver += cap 
            pickup += cap 
        # 배달, 수거 가능한 개수에서 가장 뒷부분부터 빼기
        deliver -= deliveries[i]
        pickup -= pickups[i]
        answer += cnt * (i+1)
    return answer * 2

