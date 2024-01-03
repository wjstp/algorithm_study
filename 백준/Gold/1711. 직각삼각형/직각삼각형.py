'''
a**2 + b**2 = c**2 
2 * c**2 = a**2 + b**2 + c**2
'''
import sys
input = sys.stdin.readline

N = int(input().rstrip())
arr = []
for _ in range(N):
    a, b = map(int, input().split())
    arr.append((a, b))
cnt = 0
for i in range(N-2):
    for j in range(i+1, N-1):
        for k in range(j+1, N):
            dot1, dot2, dot3 = arr[i], arr[j], arr[k]
            line1 = (dot1[0]-dot2[0])**2 + (dot1[1]-dot2[1])**2
            line2 = (dot2[0]-dot3[0])**2 + (dot2[1]-dot3[1])**2
            line3 = (dot3[0]-dot1[0])**2 + (dot3[1]-dot1[1])**2
            if max(line1, line2, line3) * 2 == (line1 + line2 + line3) :
                cnt += 1
                       
print(cnt)