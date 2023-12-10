from sys import stdin
import math

def solution(N, array):
    answer = math.inf
    total = 0
    for i in range(N-1):
        x1, y1 = array[i]
        x2, y2 = array[i+1]
        total += abs(x1-x2)+abs(y1-y2)
    for i in range(N-2):
        x1, y1 = array[i]
        x2, y2 = array[i+1]
        x3, y3 = array[i+2]
        answer = min(answer, total - (abs(x1-x2)+abs(y1-y2)) - (abs(x2-x3)+abs(y2-y3)) + (abs(x1-x3)+abs(y1-y3)))
    # print answer
    print(answer)

# input
N = int(stdin.readline().rstrip())
array = [tuple(map(int, stdin.readline().rstrip().split())) for _ in range(N)]

solution(N, array)