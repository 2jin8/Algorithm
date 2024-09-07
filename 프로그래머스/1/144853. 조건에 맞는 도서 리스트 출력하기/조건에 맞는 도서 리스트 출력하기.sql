# 도서ID, 출판일 출력하기
SELECT BOOK_ID, DATE_FORMAT(PUBLISHED_DATE, '%Y-%m-%d') AS PUBLISHED_DATE FROM BOOK
    WHERE YEAR(PUBLISHED_DATE) = 2021 # 2021년에 출판
    AND CATEGORY = '인문' # '인문' 카테고리에 속하는 도서 리스트
    ORDER BY PUBLISHED_DATE; # 출판일 기준 오름차순 정렬
    