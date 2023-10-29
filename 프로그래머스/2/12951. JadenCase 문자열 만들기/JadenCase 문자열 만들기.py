def solution(s):
    answer = ''
    flag = True
    for w in s : 
        if w == ' ':
            flag = True
            answer += w
        elif w.isdigit() : 
            answer += w
            flag = False
        else : 
            if flag :
                answer += w.upper()
            else : 
                answer += w.lower()
            flag = False
            

    return answer