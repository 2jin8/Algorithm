# 총주문량 기준 내림차순 정렬, 총주문량이 같다면 출하 번호 기준 오름차순 정렬
SELECT FLAVOR FROM FIRST_HALF
ORDER BY TOTAL_ORDER DESC, SHIPMENT_ID;