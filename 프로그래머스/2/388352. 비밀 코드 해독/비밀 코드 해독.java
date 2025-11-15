class Solution {
    static int N = 5, M, answer = 0;
    static int[] code;
    static boolean[] used; 
    
    // q: 입력한 정수, ans: 시스템 응답 (일치하는 개수)
    public int solution(int n, int[][] q, int[] ans) {
        code = new int[N]; // 생성된 조합 결과(비밀코드) 기록
        M = n + 1;
        used = new boolean[M]; // 비밀 코드에 포함된 숫자 사용 처리
        
        makeSecretCode(0, 1, q, ans);
        return answer;
    }
    
    // 비밀코드 생성 (조합)
    static void makeSecretCode(int depth, int start, int[][] q, int[] ans) {
        if (depth == N) {
            // 조건 확인하고 전부 조건에 들어맞으면 "비밀코드로 가능한 정수 조합의 개수" 증가
            if (isSecretCode(q, ans)) 
                answer++;
            return;
        }
        
        for (int i=start; i<M; i++) {
            used[i] = true;
            code[depth] = i;
            makeSecretCode(depth + 1, i + 1, q, ans);
            used[i] = false;            
        }
    }
    
    static boolean isSecretCode(int[][] q, int[] ans) {
        for (int i = 0; i < q.length; i++) {
            int count = 0;
            for (int j = 0; j < N; j++) {
                // 생성한 비밀코드에 "입력한 정수"가 포함되는 경우, 응답 + 1
                if (used[q[i][j]])
                    count++;
            }
            // 생성한 비밀코드와 입력한 정수를 비교했을 때
            // 시스템 응답과 다르다면 가능한 비밀코드가 아님
            if (count != ans[i])
                return false;
        }
        return true;
    }
}