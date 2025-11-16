import java.util.*;

class Solution {
    static int N;
    static boolean[] isIncentive;
    public int solution(int[][] scores) {
        N = scores.length;
        isIncentive = new boolean[N];
        Arrays.fill(isIncentive, true);
        ArrayList<Score> scoreList = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            scoreList.add(new Score(i, scores[i][0], scores[i][1]));
        }
        
        // A 점수 기준 내림차순 정렬, 같다면 B 기준 오름차순 정렬 
        Collections.sort(scoreList, (s1, s2) -> {
            if (s1.a == s2.a)
                return Integer.compare(s1.b, s2.b);
            return Integer.compare(s2.a, s1.a);
        });
        
        int wonA = scores[0][0], wonB = scores[0][1], beforeMaxB = -1;
        for (Score score : scoreList) {
            // 원호가 둘 다 점수가 낮은 경우가 존재하면 인센티브 X
            if (score.a > wonA && score.b > wonB)
                return -1;
            
            if (score.b < beforeMaxB) { // 두 점수가 모두 낮은 경우가 존재하면 인센티브 X
                isIncentive[score.idx] = false;
            } else {
                beforeMaxB = score.b;
            }
        }
        
//         for (int i = 1; i < N; i++) {
//             // 본인보다 높은 점수가 존재하면 
//             Score s1 = scoreList.get(i);
//             for (int j = 0; j < i; j++) {
//                 Score s2 = scoreList.get(j);
//                 if (s1.a == s2.a) continue;
                
//                 // 두 점수가 모두 낮은 경우가 존재하면 인센티브 X
//                 if (s1.b < s2.b) {
//                     isIncentive[s1.idx] = false;
//                     continue;
//                 }
//             }
//         }
        
        // 총 점수 기준 내림차순 정렬        
        PriorityQueue<Score> pq = new PriorityQueue<>((s1, s2) -> Integer.compare(s2.total, s1.total));
        for (Score score : scoreList) { // 인센티브 받을 수 있는 사원만 PQ에 저장
            if (isIncentive[score.idx]) {
                score.total = score.a + score.b;
                pq.offer(score);
            }
        }
        
        int answer = -1, rank = 0, checkCnt = 0, prev = -1;
        while (!pq.isEmpty()) {
            Score score = pq.poll();
            checkCnt++; // 현재까지 확인한 사람의 수
            
            // 이전 점수와 다르면 순위 checkCnt로 갱신
            if (score.total != prev) {
                rank = checkCnt;
                prev = score.total;
            }
            
            if (score.idx == 0) {
                answer = rank;
                break;
            }
        }
        return answer;
    }
}

class Score {
    int idx, total, a, b;
    
    public Score(int idx, int a, int b) {
        this.idx = idx;
        this.a = a;
        this.b = b;
    }
    
}