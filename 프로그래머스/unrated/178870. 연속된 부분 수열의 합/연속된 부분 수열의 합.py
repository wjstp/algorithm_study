from collections import deque
def solution(sequence, k):
    answer = []
    q = deque()
    a_sum = 0
    end = 0
    length = len(sequence)
    for start in range(len(sequence)):
        while a_sum < k and end < len(sequence):
            a_sum += sequence[end]
            end += 1
        if a_sum == k and end-1-start <length :
            answer = [start, end-1]
            length = end - 1 - start
        a_sum -= sequence[start]
    return answer 