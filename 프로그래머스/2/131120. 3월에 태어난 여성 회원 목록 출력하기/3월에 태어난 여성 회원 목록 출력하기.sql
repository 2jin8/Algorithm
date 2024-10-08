# ID, 이름, 성별, 생년월일 조회
SELECT MEMBER_ID, MEMBER_NAME, GENDER, DATE_FORMAT(DATE_OF_BIRTH, '%Y-%m-%d') DATE_OF_BIRTH
FROM MEMBER_PROFILE
WHERE MONTH(DATE_OF_BIRTH) = 3 # 생일이 3월
AND GENDER = 'W' # 여성 회원
AND TLNO IS NOT NULL # 전화번호가 NULL이면 제외
ORDER BY MEMBER_ID; # 회원 ID 기준 오름차순 정렬