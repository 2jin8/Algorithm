# 이름이 NULL인 경우 집계 X, 중복된 이름은 하나로
# 동물의 이름 조회 (중복된 이름은 하나로 치기 & NULL이면 집계 X)
SELECT COUNT(DISTINCT NAME) FROM ANIMAL_INS;