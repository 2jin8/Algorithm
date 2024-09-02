# 공장 ID, 공장 이름, 주소 조회
# 공장 ID 기준 오름차순 정렬
# 강원도에 위치한 식품 공장
SELECT FACTORY_ID, FACTORY_NAME, ADDRESS
    FROM FOOD_FACTORY
    WHERE ADDRESS LIKE '강원도%'
    ORDER BY FACTORY_ID;