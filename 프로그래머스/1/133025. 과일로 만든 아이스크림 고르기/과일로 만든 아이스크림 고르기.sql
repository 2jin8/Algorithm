# 상반기 아이스크림 총주문량이 3,000보다 높으면서 아이스크림의 주 성분이 과일
# 아이스크림의 맛 조회
# 총주문량이 큰 순서대로

SELECT F.FLAVOR FROM FIRST_HALF F
JOIN ICECREAM_INFO I
ON F.FLAVOR = I.FLAVOR
WHERE I.INGREDIENT_TYPE = 'fruit_based'
AND F.TOTAL_ORDER >= 3000
ORDER BY F.TOTAL_ORDER DESC;
