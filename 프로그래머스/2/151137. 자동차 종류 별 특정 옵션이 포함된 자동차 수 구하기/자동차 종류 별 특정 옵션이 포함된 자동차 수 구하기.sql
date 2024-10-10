# 자동차 종류별로 몇 대인지 출력
SELECT CAR_TYPE, COUNT(CAR_TYPE) AS CARS FROM CAR_RENTAL_COMPANY_CAR # 컬럼명은 CARS
# '통풍시트', '열선시트', '가죽시트' 중 하나 이상의 옵션이 포함된 자동차
WHERE OPTIONS LIKE '%통풍시트%' 
OR OPTIONS LIKE '%열선시트%'
OR OPTIONS LIKE '%가죽시트%'
GROUP BY CAR_TYPE
ORDER BY CAR_TYPE; # 자동차 종류를 기준으로 오름차순 정렬
