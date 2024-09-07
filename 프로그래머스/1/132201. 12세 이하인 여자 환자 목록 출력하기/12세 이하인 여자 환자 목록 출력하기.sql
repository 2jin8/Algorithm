# CASE 사용
# 환자이름, 환자번호, 성별코드, 나이, 전화번호
SELECT PT_NAME, PT_NO, GEND_CD, AGE, 
    CASE 
        WHEN TLNO IS NULL THEN 'NONE' # 전화번호가 없는 경우 'NONE'으로 출력
        ELSE TLNO
    END AS TLNO
    FROM PATIENT 
    WHERE AGE <= 12 # 12세 이하
    AND GEND_CD = 'W' # 여자 환자
    ORDER BY AGE DESC, PT_NAME; # 나이 기준 내림차순, 같다면 이름 기준으로 오름차순 정렬