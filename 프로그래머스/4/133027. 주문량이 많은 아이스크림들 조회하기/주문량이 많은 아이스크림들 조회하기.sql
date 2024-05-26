-- 7월 아이스크림 총 주문량과 상반기 아이스크림 총 주문량을 더한 값이 큰 순서대로 상위 3개의 맛(FLAVOR) 조회
SELECT H.FLAVOR
FROM FIRST_HALF H
JOIN JULY J
ON H.flavor = j.flavor
GROUP BY H.FLAVOR
ORDER BY SUM(H.TOTAL_ORDER) + SUM(J.TOTAL_ORDER) DESC
LIMIT 3