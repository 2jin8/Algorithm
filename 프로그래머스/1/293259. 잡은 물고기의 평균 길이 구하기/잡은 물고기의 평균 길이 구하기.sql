# 잡은 물고기의 평균 길이 출력
# 소수점 3째자리에서 반올림
# 10CM 이하(=NULL)는 10CM로 취급
SELECT ROUND(AVG(IFNULL(LENGTH, 10)), 2) AVERAGE_LENGTH
FROM FISH_INFO;