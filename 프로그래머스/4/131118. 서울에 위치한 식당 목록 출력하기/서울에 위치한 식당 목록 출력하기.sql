# 서울에 위치한 식당들의 식당 ID, 식당 이름, 음식 종류, 즐겨찾기수, 주소, 리뷰 평균 점수를 조회
# 리뷰 평균점수는 소수점 세 번째 자리에서 반올림 
# 평균점수를 기준으로 내림차순 정렬, 평균점수가 같다면 즐겨찾기수를 기준으로 내림차순 정렬
SELECT I.REST_ID, I.REST_NAME, I.FOOD_TYPE, I.FAVORITES, I.ADDRESS,
ROUND(AVG(R.REVIEW_SCORE), 2) SCORE
FROM REST_INFO I
JOIN REST_REVIEW R
ON I.REST_ID = R.REST_ID
GROUP BY REST_ID
HAVING I.ADDRESS LIKE '서울%'
ORDER BY SCORE DESC, FAVORITES DESC;