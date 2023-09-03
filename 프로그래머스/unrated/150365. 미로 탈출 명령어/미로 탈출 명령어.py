import sys
sys.setrecursionlimit(10**5)
dr = [1, 0, 0, -1]
dc = [0, -1, 1, 0]
dl = ['d', 'l', 'r', 'u']    

def findRoute(n, m, cr, cc, end_r, end_c, k, cnt, route):
    global ans, flag
    if cnt == 0 :
        if (not(abs(cr-end_r)+abs(cc-end_c)) & 1 and k &1 ) or (abs(cr-end_r)+abs(cc-end_c) & 1 and not (k &1) ):
            return
    if cr == end_r and cc == end_c and cnt == k :
        ans = route
        flag = 1
        return    
    for i in range(4):
        nr, nc = cr + dr[i], cc + dc[i]
        if 0<=nr<n and 0<=nc<m :
            if abs(end_r - nr) + abs(end_c - nc) < k-cnt and not flag:
                findRoute(n, m, nr, nc, end_r, end_c, k, cnt+1, route+dl[i])


def solution(n, m, x, y, r, c, k):
    global flag, ans
    answer = ''
    flag = 0
    ans = ''
    distance = abs(x-r)+abs(y-c)
    findRoute(n, m, x-1, y-1, r-1, c-1, k, 0, '')    
    if flag :
        answer = ans
    else :
        answer = 'impossible'
    
    return answer