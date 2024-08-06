from copy import deepcopy

def solution(maze):
    global R, C, dr, dc, visited, answer
    answer = 10e9
    R = len(maze)
    C = len(maze[0])
    dr = [0, 1, 0, -1]
    dc = [1, 0, -1, 0]
    start_red, start_blue = [0, 0, False], [0, 0, False]    # r, c, stop 여부
    visited = [[0 for _ in range(C)] for _ in range(R)] # red : 1, blue : 2
    for r in range(R) :
        for c in range(C) :
            if maze[r][c] == 1 :
                start_red[0] = r
                start_red[1] = c
                start_red[2] = is_stop('RED', start_red, maze)
            elif maze[r][c] == 2 :
                start_blue[0] = r
                start_blue[1] = c
                start_blue[2] = is_stop('BLUE', start_blue, maze)
    visited[start_red[0]][start_red[1]] += 1
    visited[start_blue[0]][start_blue[1]] += 2
    dfs(0, start_red, start_blue, maze)
    if answer == 10e9 : answer = 0
    return answer

def dfs(cnt, red, blue, maze) :
    global R, C, dr, dc, visited, answer
    
    if cnt > answer :   # 시간초과 방지
        return
    
    if red[2] and blue[2] : # 모두 도착했다면
        answer = min(answer, cnt)
        return
    
    for i in range(4) :
        for j in range(4) :
            next_red = deepcopy(red)
            next_blue = deepcopy(blue)
            if not red[2] : # 빨강 도착 x - 빨강 이동
                next_red[0] = red[0] + dr[i]
                next_red[1] = red[1] + dc[i]
            if not blue[2] : # 파랑 도착 x - 파랑 이동
                next_blue[0] = blue[0] + dr[j]
                next_blue[1] = blue[1] + dc[j]
            if is_valid('RED', next_red, maze) and is_valid('BLUE', next_blue, maze) and not is_switch(next_red, next_blue, red, blue):
                next_red[2] = is_stop('RED', next_red, maze)
                next_blue[2] = is_stop('BLUE', next_blue, maze)
                if not next_red[2] : 
                    visited[next_red[0]][next_red[1]] += 1
                if not next_blue[2] :
                    visited[next_blue[0]][next_blue[1]] += 2
                dfs(cnt + 1, next_red, next_blue, maze)
                if not next_red[2] :
                    visited[next_red[0]][next_red[1]] -= 1
                if not next_blue[2] :
                    visited[next_blue[0]][next_blue[1]] -= 2
                

def is_stop(color, cart, maze) :
    r = cart[0]
    c = cart[1]
    if color == 'RED' and maze[r][c] == 3 :
        return True
    if color == 'BLUE' and maze[r][c] == 4 :
        return True
    return False


def is_valid(color, cart, maze) :
    global R, C
    r = cart[0]
    c = cart[1]
    stop = cart[2]
    # 도착한 카트라면 바로 리턴
    if stop :
        return True
    # 이동중이라면 유효성 체크 (범위, 벽 여부)
    if 0 <= r < R and 0 <= c < C and maze[r][c] != 5 :
        if color == 'RED' and visited[r][c] % 2 == 0 : # 0, 2
            return True
        if color == 'BLUE' and visited[r][c] < 2 : # 0, 1
            return True
    return False


def is_switch(next1, next2, cur1, cur2,) :
    # 상대방과 같은 곳 가려는지
    if next1[0] == next2[0] and next1[1] == next2[1] :
        return True
    # 교차 - 이전에 상대방이 있던 곳을 감 && 상대방도 내가 있던 곳으로 옴
    if cur1[0] == next2[0] and cur1[1] == next2[1] :
        if next1[0] == cur2[0] and next1[1] == cur2[1] :
            return True
    return False
    
def print_map() :
    global R, visited
    print("#####")
    for r in range(R) :
        print(visited[r])