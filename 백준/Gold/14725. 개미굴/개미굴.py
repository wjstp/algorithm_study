import sys

class Trie:
    dict = {}
    def add(self, arr):
        cur = self.dict
        for ele in arr :
            if ele not in cur :
                cur[ele] = {}
            cur = cur[ele]
        cur['*'] = True
    
    def printCurve(self, a_dict, level):
        cur_dict = a_dict
        cur_arr = sorted(list(cur_dict.keys()))
        for ele in cur_arr :
            if ele != '*':
                print('--'*level, end='')
                print(ele)
                self.printCurve(cur_dict[ele], level+1)
        
input = sys.stdin.readline
N = int(input().rstrip())
trie = Trie()
for _ in range(N):
    lst = list(input().split())
    trie.add(lst[1:])

# 개미굴 출력
trie.printCurve(trie.dict, 0)
    
