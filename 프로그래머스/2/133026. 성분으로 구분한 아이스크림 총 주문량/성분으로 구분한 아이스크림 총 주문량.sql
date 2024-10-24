# 아이스크림의 주 성분이 설탕인 아이스크림들에 대한 총주문량
# 아이스크림의 주 성분이 과일인 아이스크름들에 대한 총주문량
SELECT B.INGREDIENT_TYPE, SUM(A.TOTAL_ORDER) TOTAL_ORDER
FROM FIRST_HALF A JOIN ICECREAM_INFO B
ON A.FLAVOR = B.FLAVOR
GROUP BY B.INGREDIENT_TYPE
HAVING B.INGREDIENT_TYPE IN ('sugar_based', 'fruit_based');