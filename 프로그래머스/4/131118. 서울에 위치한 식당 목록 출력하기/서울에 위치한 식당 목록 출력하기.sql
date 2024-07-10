-- 코드를 입력하세요
-- 서울에 위치한 식당들의 식당 id, 식당 이름, 음식 종류, 즐겨찾기 수, 주소, 리뷰 평균 점수(소숮점 세번쨰 자리에서 반올림) 평균점수 기준 내림차순, 같다면 즐겨찾기수 기준 내림차순
-- 서울에 위치한걸 왜 빼먹어!!
SELECT I.REST_ID, I.REST_NAME, I.FOOD_TYPE, 
        I.FAVORITES, I.ADDRESS, ROUND(AVG(R.REVIEW_SCORE), 2) AS SCORE
FROM REST_INFO I
JOIN REST_REVIEW R
ON I.REST_ID = R.REST_ID
WHERE I.ADDRESS LIKE '서울%'
GROUP BY R.REST_ID
ORDER BY 6 DESC, 4 DESC