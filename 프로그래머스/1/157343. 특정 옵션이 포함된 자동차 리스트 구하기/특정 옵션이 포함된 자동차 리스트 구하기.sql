# '네비게이션' 옵션이 포함된 자동차 리스트
# 자동차ID 기준 내림차순 정렬
SELECT * FROM CAR_RENTAL_COMPANY_CAR
WHERE OPTIONS LIKE '%네비게이션%'
ORDER BY CAR_ID DESC;