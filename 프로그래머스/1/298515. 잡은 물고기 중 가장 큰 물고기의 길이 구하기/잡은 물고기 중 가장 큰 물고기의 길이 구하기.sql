# 가장 큰 물고기의 길이 출력(컬럼명: 은 'MAX_LENGTH)
SELECT CONCAT(MAX(LENGTH), 'cm') MAX_LENGTH # 'cm' 붙여서 출력하기
FROM FISH_INFO