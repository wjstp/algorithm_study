-- 헤비 유저(공간을 둘 이상 등록한 사람)의 공간 정보를 아이디 순으로 조회
SELECT ID, NAME, HOST_ID
FROM PLACES
WHERE HOST_ID IN (SELECT HOST_ID 
                    FROM PLACES 
                    GROUP BY HOST_ID 
                    HAVING COUNT(*) >= 2)
ORDER BY ID