import sys
input = sys.stdin.readline

sentence = input().rstrip()
q = int(input().rstrip())

dict = {}
for i in range(len(sentence)):
    if dict.get(sentence[i]) == None :
        dict.setdefault(sentence[i], [i])
    else :
        dict[sentence[i]].append(i)
        

# print(dict)


answer = []
for i in range(q):
    ans = 0
    word, l, r = input().split()
    l, r = int(l), int(r)
    if dict.get(word) == None :
        answer.append(0)
        continue
    for a in dict[word]:
        if a >= l and a<= r :
            ans += 1
    answer.append(ans)

for i in range(q):
    print(answer[i])