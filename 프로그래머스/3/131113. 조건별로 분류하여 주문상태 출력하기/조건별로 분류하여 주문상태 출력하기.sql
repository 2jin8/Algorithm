# 2022년 5월 1일을 기준으로 주문 ID, 제품 ID, 출고일자, 출고여부를 조회하는 SQL문을 작성
# 출고여부: 2022년 5월 1일까지 출고완료, 이 후 날짜는 출고 대기, 미정이면 출고미정
# 주문 ID를 기준으로 오름차순 정렬
# OUT_DATE가 NULL: 미정
# 5월 1일보다 빠르면 출고완료

SELECT ORDER_ID, PRODUCT_ID, DATE_FORMAT(OUT_DATE, '%Y-%m-%d') OUT_DATE, (
    CASE 
        WHEN OUT_DATE IS NULL THEN '출고미정'
        WHEN DATEDIFF('2022-05-01', OUT_DATE) >= 0 THEN '출고완료'
        ELSE '출고대기'
    END
) AS '출고여부'
FROM FOOD_ORDER
ORDER BY ORDER_ID;