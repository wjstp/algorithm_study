-- 코드를 입력하세요
-- 조회수가 가장 높은 첨부파일 경로 조회. file id 를 기준으로 첨부파일 경로 내림차순 정렬
-- 기본 파일 경로는 /home/grep/src/ 게시글 Id로 디렉토리 구분, 파일 이름은 파일 Id, 파일 이름, 파일 확장자로 구성 
SELECT CONCAT('/home/grep/src/', F.BOARD_ID, '/', F.FILE_ID, F.FILE_NAME, F.FILE_EXT) AS FILE_PATH
FROM USED_GOODS_BOARD B
JOIN USED_GOODS_FILE F
ON B.BOARD_ID = F.BOARD_ID
WHERE B.VIEWS = (SELECT 
                 VIEWS 
                 FROM USED_GOODS_BOARD 
                 ORDER BY VIEWS DESC 
                 LIMIT 1)
 ORDER BY F.FILE_ID DESC