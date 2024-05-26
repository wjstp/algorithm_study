-- 스킬코드가 일치하는 개발자의 id, 이메일, 이름, 성 조회, id기준 오름차순
-- 비트연산 활용 100100(a)에 100(b)이 있는지 확인? a & b = b
SELECT ID, EMAIL, FIRST_NAME, LAST_NAME
FROM DEVELOPERS 
WHERE SKILL_CODE & (SELECT sum(CODE) 
                    FROM SKILLCODES 
                    WHERE CATEGORY='Front End') != 0

ORDER BY ID