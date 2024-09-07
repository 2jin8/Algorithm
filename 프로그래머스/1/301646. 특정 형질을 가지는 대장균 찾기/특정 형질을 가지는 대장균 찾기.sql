# 2번 형질 보유 X & 1번이나 3번 ㅎㅇ질 보유하고 있는 개체의 수 출력
SELECT COUNT(*) AS COUNT FROM ECOLI_DATA 
    WHERE GENOTYPE & 2 = 0 # 2번 형질 보유 X
    AND (GENOTYPE & 1 = 1 OR GENOTYPE & 4 = 4); # 1번이나 3번 형질 보유 O