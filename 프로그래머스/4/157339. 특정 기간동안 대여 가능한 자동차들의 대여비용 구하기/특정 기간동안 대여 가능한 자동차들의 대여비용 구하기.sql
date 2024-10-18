-- 세단 or suv 인 자동차 중 2022.11.1부터 2022.11.30까지 대여 가능하고 30일간의 대여 금엑이 50만원 이상 200만원 미만인 자동차에 대해 자동차id, 자동차 종류, 대여금액 출력
-- 대여금액 기준 내림차순, 자동차 종류 기준 오름차순, 자동차 id기준 내림차순
-- history에서 가장 최근에 빌린 리스트들 중에서 거기에 없거나 대여 기간이 위 기간과 겹치지 않는 자동차 리스트
-- 30일간 대여한다고 했을 떄 50~200 사이인 것

WITH RECENTLY_USED AS (
    SELECT C.CAR_ID, MAX(H.END_DATE) AS END_DATE
    FROM CAR_RENTAL_COMPANY_CAR C
    JOIN CAR_RENTAL_COMPANY_RENTAL_HISTORY H
    ON C.CAR_ID = H.CAR_ID
    GROUP BY H.CAR_ID
), AVAILABLE AS (
    SELECT CAR_ID, CAR_TYPE, DAILY_FEE
    FROM CAR_RENTAL_COMPANY_CAR 
    WHERE CAR_ID NOT IN (SELECT CAR_ID 
                         FROM RECENTLY_USED 
                         WHERE END_DATE > DATE_FORMAT('2022-11-01', '%Y-%m-%d')) 
        AND CAR_TYPE IN ('세단', 'SUV')
), DISCOUNTED AS (
    SELECT CAR_ID, CAR_TYPE, DAILY_FEE * 30 * (100 - (SELECT REPLACE(DISCOUNT_RATE, '%', '') FROM CAR_RENTAL_COMPANY_DISCOUNT_PLAN P WHERE A.CAR_TYPE = P.CAR_TYPE AND REPLACE(DURATION_TYPE, '일 이상', '') = '30')) / 100 AS FEE
FROM AVAILABLE A

)
SELECT CAR_ID, CAR_TYPE, FEE
FROM DISCOUNTED
WHERE FEE < 2000000 AND FEE > 500000
ORDER BY FEE DESC, CAR_TYPE, CAR_ID DESC











