-- 코드를 입력하세요
-- 생일이 3월인 여성회원의 id, 이름, 성별, 생년월일, 전화번호가 null이면 제외, id기준 오름차순 정렬
SELECT MEMBER_ID, MEMBER_NAME, GENDER, DATE_FORMAT(DATE_OF_BIRTH, '%Y-%m-%d')
FROM MEMBER_PROFILE
WHERE DATE_FORMAT(DATE_OF_BIRTH, '%m') = 3 
AND GENDER = 'W'
AND TLNO != 'NULL'
ORDER BY MEMBER_ID ASC;








# SELECT MEMBER_ID, MEMBER_NAME, GENDER, DATE_FORMAT(DATE_OF_BIRTH, '%Y-%m-%d') DATE_OF_BIRTH from MEMBER_PROFILE where TLNO !='null' and GENDER='W' and DATE_FORMAT(DATE_OF_BIRTH, '%m')='03' order by MEMBER_ID asc;