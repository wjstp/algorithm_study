def solution(n, m, x, y, r, c, k):
    answer = ''
    #x,y -> r,c 
    #좌상단이 1,1/ 우하단이 n,m
    #세로,가로  -> 2.3이 S
    diff = abs(x-r)+abs(y-c)
    if diff%2!=k%2 or diff>k:
        return 'impossible'
    #dlru 순서 

    rest = k-diff
    lcount = 0
    rcount = 0
    dcount = 0
    ucount = 0
    if x<r : #내려가야함
        dcount = r-x
    else:
        ucount = x-r
    if y<c :
        rcount = c-y
    else:
        lcount = y-c

    dplus = min( n-max(x,r), rest//2)
    rest -= dplus*2

    lplus = min( min(y,c)-1, rest//2)
    rest -= lplus*2

    answer = 'd'*(dcount+dplus)+'l'*(lcount+lplus)+'rl'*(rest//2)+'r'*(rcount+lplus)+'u'*(dplus+ucount)
    print(lcount,lplus,rcount,rest)



    return answer