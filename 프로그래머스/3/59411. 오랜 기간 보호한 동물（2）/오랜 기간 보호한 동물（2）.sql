-- 코드를 입력하세요
-- 입양 간 동물 중, 보호 기간이 가장 길었던 동물 두 마리의 아이디와 이름 보호 기간이 긴 순으로 조회
SELECT I.ANIMAL_ID, I.NAME
FROM ANIMAL_OUTS O
JOIN ANIMAL_INS I
ON I.ANIMAL_ID = O.ANIMAL_ID
ORDER BY datediff(O.DATETIME, I.DATETIME) DESC  
LIMIT 2