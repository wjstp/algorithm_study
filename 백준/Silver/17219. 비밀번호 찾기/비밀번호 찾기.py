import sys
input = sys.stdin.readline

N, M = map(int, input().split())
dict = {}
for _ in range(N):
    site, passwd = input().split()
    dict[site] = passwd

for _ in range(M):
    site_name = input().rstrip()
    print(dict[site_name])
    
    