import sys
input = sys.stdin.readline

def REV(num):
    new_num = ''
    for i in range(len(str(num))-1, -1, -1):
        new_num += str(num)[i]
    return int(new_num)

A, B = map(int, input().split())
print(REV(REV(A)+REV(B)))