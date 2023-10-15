import sys
input = sys.stdin.readline
sys.setrecursionlimit(10**9)

def root(node): # 부모 찾기
    global parent
    # 자기 자신이 부모인 경우 - 가장 상단의 뿌리
    if parent[node] == node :
        return node
    parent[node] = root(parent[node])
    return parent[node]
def union(x, y): # 합집합으로 만들기
    global parent
    x = root(x)
    y = root(y)
    if x == y : # 싸이클
        return 
    else :
        if x > y :
            parent[x] = y                     
        else :
            parent[y] = x   
        return


n, m = map(int, input().split())
parent = [i for i in range(n+1)]
answers = []
for i in range(m):
    calculate, a, b = map(int, input().split())
    # 합집합
    if not calculate :
        union(a, b)
    else :
        if root(a) == root(b) :
            answers.append('YES')
        else :
            answers.append('NO')

for answer in answers :
    print(answer)