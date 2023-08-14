n=int(input())
for i in range(0, n) :
    stack=[]
    khList=list(input())
    
    for a in khList:
        stack.append(a)
        if len(stack)>=2 and stack[-1]==')' and stack[-2]=='(':
            stack.pop()
            stack.pop()
    
    if len(stack)==0 :
        print('YES')
    else : 
        print('NO')