K = int(input())
stack = []
sum = 0
for i in range(0, K) :
    num =int(input())
    if num != 0:
        stack.append(num)
    else :
        stack.pop()
for i in range (0, len(stack)):
    sum += stack[i]

print(sum)