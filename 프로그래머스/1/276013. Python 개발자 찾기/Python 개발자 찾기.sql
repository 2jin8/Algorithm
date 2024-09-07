# 개발자의 ID, 이메일, 이름, 성 조회
SELECT ID, EMAIL, FIRST_NAME, LAST_NAME FROM DEVELOPER_INFOS 
    WHERE SKILL_1 = 'Python' OR SKILL_2 = 'Python' OR SKILL_3 = 'Python' # Python 스킬
    ORDER BY ID; # ID 기준 오름차순 정렬