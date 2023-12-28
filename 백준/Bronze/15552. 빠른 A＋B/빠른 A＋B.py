import sys
input = sys.stdin.readline
T = int(input().rstrip())
for _ in range(T):
    A, B = map(int, input().split())
    print(A + B)