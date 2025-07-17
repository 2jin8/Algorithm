import java.util.*;

class Solution {
    
    static int N;
    static int[] number, record;
    static boolean[] visited;
    static HashSet<Integer> hashSet = new HashSet<>();
    public int solution(String numbers) {
        N = numbers.length();
        number = new int[N];
        visited = new boolean[N];
        for (int i=0; i<N; i++) {
            number[i] = numbers.charAt(i) - '0';
        }
        
        // i: 선택할 종이 조각의 개수
        for (int i=1; i<=N; i++) {
            record = new int[i];
            dfs(0, i);
        }
        return hashSet.size();
    }
    
    static void dfs(int depth, int maxDepth) {
        // 종이 조각 다 선택한 경우
        if (depth == maxDepth) {
            int total = 0;
            for (int i=0; i<depth; i++) {
                total = total * 10 + record[i];
            }
            // 소수일 경우 저장하기
            if (isPrime(total)) hashSet.add(total);
            return;
        }
        
        for (int i=0;i<N;i++) {
            if (visited[i]) continue;
            
            visited[i] = true;
            record[depth] = number[i];
            dfs(depth + 1, maxDepth);
            visited[i] = false;
        }
    }
    
    static boolean isPrime(int num) {
        if (num <= 1) return false;
        for (int i=2;i<=Math.sqrt(num); i++) {
            if (num % i == 0)
                return false;
        }
        return true;
    }
}