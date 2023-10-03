'''
앞의 꺼랑 같은데 세등분 한다는 차이점 
'''
import sys
input = sys.stdin.readline

def checkPaper(length, cr, cc):
    global m_one, zero, one
    if length == 1 :
        return arr[cr][cc]
    
    papers = []
    for i in range(3):
        for j in range(3):
            x = checkPaper(length//3, cr+(length//3)*i, cc+(length//3)*j)
            papers.append(x)
    
    # 다 같은건지 확인
    flag = 1
    for paper in papers :
        if paper != papers[0]:
            flag = 0
            break
        
    # 다 같은 경우
    if flag and papers[0] != 'Mix':
        if length == N :
            if papers[0] == 0 : zero += 1
            elif papers[0] == 1 : one += 1
            elif papers[0] == -1 : m_one += 1
        return papers[0]
    else :
        for paper in papers:
            if paper == 0 :
                zero += 1
            elif paper == 1 :
                one += 1
            elif paper == -1 :
                m_one += 1
        return 'Mix'


N = int(input().rstrip())
arr = [list(map(int, input().split())) for _ in range(N)]
m_one = zero = one = 0
checkPaper(N, 0, 0)
print(m_one)
print(zero)
print(one)