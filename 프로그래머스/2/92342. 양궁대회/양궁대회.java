class Solution {
    
    static int maxDiff = Integer.MIN_VALUE;
    static int[] ans = new int[11];
    static int[] peach = new int[11];
    static int[] lion = new int[11];
    public int[] solution(int n, int[] info) {
        peach = info.clone();
        
        makeScore(0, n);
        if (maxDiff == -1)
            return new int[]{-1};
        return ans;
    }
    
    static void makeScore(int depth, int n) {
        // 화살 n발 쐈을 때
        if (depth == n) {
            // 점수 차이 구하기
            int diff = getDiffScore();
            if (maxDiff <= diff) {
                maxDiff = diff;
                ans = lion.clone();
            }
            return;
        }
        
        // 중복 조합
        for (int i = 0; i < peach.length && lion[i] <= peach[i]; i++) {
            lion[i]++;
            makeScore(depth + 1, n);
            lion[i]--;
        }
    }
    
    static int getDiffScore() {
        int pScore = 0, lScore = 0;
        for (int i = 0; i < peach.length; i++) {
            // 둘 다 못 맞혔으면 넘어가기
            if (peach[i] == 0 && lion[i] == 0)
                continue;
            
            // 라이언이 더 많이 맞혔으면 라이언 득점
            if (peach[i] < lion[i]) {
                lScore += (10 - i);
            } else { // 점수가 같거나 어피치가 더 많이 맞혔으면 어피치 득점
                pScore += (10 - i);
            }
        }
        int diff = lScore - pScore;
        return (diff <= 0? -1 : diff);
    }
}