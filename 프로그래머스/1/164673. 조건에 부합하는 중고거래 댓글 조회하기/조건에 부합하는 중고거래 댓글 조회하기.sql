# 게시글 제목, 게시글 ID, 댓글 ID, 댓글 작성자 ID, 댓글 내용, 댓글 작성일
SELECT A.TITLE, A.BOARD_ID, B.REPLY_ID, B.WRITER_ID, B.CONTENTS,
    DATE_FORMAT(B.CREATED_DATE, '%Y-%m-%d') AS CREATED_DATE
    FROM USED_GOODS_BOARD AS A 
    INNER JOIN USED_GOODS_REPLY AS B ON A.BOARD_ID = B.BOARD_ID
        WHERE YEAR(A.CREATED_DATE) = 2022 AND MONTH(A.CREATED_DATE) = 10 # 2022년 10월에 작성
        ORDER BY CREATED_DATE, TITLE; # 댓글 작성일 기준으로 오름차순, 같다면 게시물 제목 기준으로 오름차순 정렬