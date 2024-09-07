# 가장 큰 물고기 10마리의 ID와 길이 출력
SELECT ID, LENGTH FROM FISH_INFO
    WHERE LENGTH IS NOT NULL # 가장 큰 물고기 중 10cm 이하인 경우 없음 = LEGNTH가 NULL인 것이 없음
    ORDER BY LENGTH DESC, ID # 길이 기준 내림차순, 같다면 물고기 ID 기준 오름차순 정렬
    LIMIT 10; # 10마리 