# 1000이라서 1000*999/2 최대  1초 ㄴㄴ
sentence = input()
a_set = set()
n = 1
while n <= len(sentence) :
    for i in range(0, len(sentence)-n+1):
        a_set.add(sentence[i:i+n])
    n += 1
print(len(a_set))
