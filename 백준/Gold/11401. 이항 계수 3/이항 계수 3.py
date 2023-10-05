'''
1/a의 나머지는 a^(p-2)의 나머지와 같다 - 페르마 소정리
분할 정복은 제곱수를 구할 떄 사용
'''
import sys
input = sys.stdin.readline
p = 1000000007
def fac(num):
    res = 1
    for i in range(2, num+1):
        res = (res*i)%p
    return res

def power(bm, depth):
    if depth ==1 :
        return bm
    if depth == 0 :
        return 1
    
    else :
        tmp = power(bm, depth//2)
        if depth % 2 :
            return (tmp * tmp * bm)% p
        else :
            return (tmp * tmp) %p
N, K = map(int, input().split())
bj = fac(N)
bm = fac(K)*fac(N-K)%p

print((bj*power(bm, p-2)%p))
