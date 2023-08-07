N, M = map(int, input().split())
s_word_arr = []
for _ in range(N) :
    word = input()
    s_word_arr.append(word)
cnt = 0
for _ in range(M) :
    tmp = input()
    if tmp in s_word_arr :
        cnt += 1
print(cnt)