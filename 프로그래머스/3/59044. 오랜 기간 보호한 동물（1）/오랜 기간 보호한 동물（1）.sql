# 입양을 못 간 동물 중, 가장 오래 보호소에 있었던 동물 3마리의 이름과 보호 시작일을 조회
# 보호 시작일 순으로 조회
SELECT NAME, DATETIME FROM ANIMAL_INS
WHERE ANIMAL_ID NOT IN (
    SELECT ANIMAL_ID FROM ANIMAL_OUTS
)
ORDER BY DATETIME
LIMIT 3;