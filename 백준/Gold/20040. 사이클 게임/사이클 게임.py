'''
0~ n-1 까지 번호
선 플레이어가 홀수, 후 플레이어가 짝수
사이클을 완성하면 게임 종료
몇번쨰 차례에서 사이클이 완성되는지, 게임이 진행중인지 판단
완성되었다면 몇 번쨰 차례에서 처음으로 사이클이 완성되었는지 출력

'''
import sys
input = sys.stdin.readline

def find_set(node):
    if parent[node] == node :
        return node
    else :
        parent[node] = find_set(parent[node])
        return parent[node]

def union(x, y):
    parent_x = find_set(x)
    parent_y = find_set(y)
    if parent_x == parent_y :
        return True
    else :
        if parent_x > parent_y :
            parent[parent_x] = parent_y
        else :
            parent[parent_y] = parent_x
        return False
n, m = map(int, input().split())
parent = [ i for i in range(n) ]
flag = True
for i in range(m):
    num1, num2 = map(int, input().split())
    isCycle = union(num1, num2)
    if isCycle :
        print(i+1)
        flag = False
        break
if flag :
    print(0)