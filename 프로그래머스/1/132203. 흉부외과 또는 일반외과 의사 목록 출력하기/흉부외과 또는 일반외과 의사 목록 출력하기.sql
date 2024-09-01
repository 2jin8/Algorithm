# 이름, 의사ID, 진료과, 고용 일자
SELECT DR_NAME, DR_ID, MCDP_CD, DATE_FORMAT(HIRE_YMD, '%Y-%m-%d') AS HIRE_YMD
    FROM DOCTOR
    WHERE MCDP_CD = 'CS' OR MCDP_CD = 'GS' # 진료과가 CS 또는 GS
    ORDER BY HIRE_YMD DESC, DR_NAME; # 고용일자 기준 내림차순 정렬, 같다면 이름 기준 오름차순 정렬