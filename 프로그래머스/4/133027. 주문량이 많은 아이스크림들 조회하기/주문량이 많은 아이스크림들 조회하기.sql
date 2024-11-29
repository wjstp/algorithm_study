-- 7월 아이스크림 총 주문량과 상반기 아이스크림 총 주문량을 더한 값이 큰 순서대로 상위 3개의 맛(FLAVOR) 조회
-- 맛을 기준으로 조인해서 맛을 기준으로 합을 구한다




# SELECT F.FLAVOR
# FROM FIRST_HALF F
# JOIN JULY J
# ON F.FLAVOR = J.FLAVOR
# GROUP BY J.FLAVOR
# ORDER BY SUM(J.TOTAL_ORDER) + F.TOTAL_ORDER DESC 
# LIMIT 3







SELECT H.FLAVOR
FROM FIRST_HALF H
JOIN JULY J
ON H.flavor = j.flavor
GROUP BY H.FLAVOR
ORDER BY SUM(H.TOTAL_ORDER) + SUM(J.TOTAL_ORDER) DESC
LIMIT 3


-- 그냥 id를 기준으로 조인하면 교집합이므로 누락되는 맛이 존재한다. 
-- full outer join으로 합집합을 조회. mysql은 left join과 right join을 union하는 식으로 쿼리 작성