-- 식품 분류가 과자, 국, 김치, 식용유인 경우만 출력, 식품 가격 기준 내림차순
-- sfwgho
SELECT A.CATEGORY, B.MAX_PRICE, A.PRODUCT_NAME
FROM FOOD_PRODUCT A
JOIN (SELECT CATEGORY, MAX(PRICE) AS MAX_PRICE
    FROM FOOD_PRODUCT
    WHERE CATEGORY = '과자' OR CATEGORY = '국' OR CATEGORY = '김치' OR CATEGORY = '식용유'
    GROUP BY CATEGORY) B
ON A.CATEGORY = B.CATEGORY AND A.PRICE = B.MAX_PRICE
ORDER BY MAX_PRICE DESC

    



