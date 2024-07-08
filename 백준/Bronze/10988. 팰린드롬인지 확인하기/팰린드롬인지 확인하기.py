import sys
input = sys.stdin.readline

def check(word) :
    i = 0
    j = len(word) - 1
    while word[i] == word[j] :
        i += 1
        j -= 1
        if i == j or i > j:
            return True
    return False

word = input().rstrip()
if check(word) :
	print(1)
else :
	print(0)
 
 
