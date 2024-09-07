# 동물의 아이디와 이름, 보호 시작일 조회
SELECT ANIMAL_ID, NAME, DATETIME FROM ANIMAL_INS
    ORDER BY NAME, DATETIME DESC; 
# 이름 순으로 조회, 이름이 같다면 보호를 나중에 시작한 동물 순으로