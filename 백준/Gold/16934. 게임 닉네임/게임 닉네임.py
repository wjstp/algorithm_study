import sys

class Trie :
    dict = {}
    def add_and_print_prefix(self, word):
        flag = True
        prefix = ''
        cur = self.dict
        for w in word :
            if flag :
                prefix += w
            if w not in cur :
                cur[w] = {}
                flag = False
            cur = cur[w]
        if not cur.get('*'):        
            cur['*'] = 1
        else :
            cur['*'] += 1
            
        if prefix == word and cur['*'] != 1:
            prefix = word + str(cur['*'])
        print(prefix)
        

input = sys.stdin.readline

N = int(input().rstrip())
trie = Trie()
for _ in range(N):
    word = input().rstrip()
    trie.add_and_print_prefix(word)