import sys
input = sys.stdin.readline

def compress(length, cr, cc):
    if length == 1 :
        return f'{arr[cr][cc]}'
        
    a1 = compress(length//2, cr, cc)
    a2 = compress(length//2, cr, cc+length//2)
    a3 = compress(length//2, cr+length//2, cc)
    a4 = compress(length//2, cr+length//2, cc+length//2)

    if a1 == a2 == a3 == a4 and len(a1)== 1:
        return a1
    else :
        return f'({a1}{a2}{a3}{a4})'

N = int(input().rstrip())
arr = [list(input().rstrip()) for _ in range(N)]

print(compress(N, 0, 0))
