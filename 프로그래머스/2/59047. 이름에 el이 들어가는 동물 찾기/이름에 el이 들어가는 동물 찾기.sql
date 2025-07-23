# 동물 이름 중, 이름에 "EL"이 들어가는 개의 아이디와 이름을 조회
# 이름 순으로 조회
SELECT ANIMAL_ID, NAME
FROM ANIMAL_INS
WHERE NAME LIKE '%EL%' # 이름에 "EL"이 들어가는 개
AND ANIMAL_TYPE = 'Dog'
ORDER BY NAME;