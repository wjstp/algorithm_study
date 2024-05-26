-- 리뷰를 가장 많이 작성한 회원의 이름, 리뷰 텍스트, 리뷰 작성일 조회. 리뷰 작성일 (오름차순), 리뷰텍스트(오름차순)
SELECT M.MEMBER_NAME, R.REVIEW_TEXT, DATE_FORMAT(R.REVIEW_DATE, '%Y-%m-%d') AS REVIEW_DATE
FROM MEMBER_PROFILE M
JOIN REST_REVIEW R
ON M.MEMBER_ID = R.MEMBER_ID
WHERE R.MEMBER_ID = (SELECT MEMBER_ID 
                     FROM REST_REVIEW 
                     GROUP BY MEMBER_ID 
                     ORDER BY COUNT(*) 
                     DESC LIMIT 1)
ORDER BY R.REVIEW_DATE, R.REVIEW_TEXT

