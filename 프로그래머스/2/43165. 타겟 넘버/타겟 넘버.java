import java.util.*;

// 타겟 넘버 만드는 방법의 수 return

class Solution {
    static int answer = 0;
    public int solution(int[] numbers, int target) {
        dfs(0, 0, numbers, target);
        return answer;
    }
    
    static void dfs(int depth, int total, int[] numbers, int target) {
        if (depth == numbers.length) {
            if (total == target) answer++;
            return;
        }
        
        // +
        dfs(depth + 1, total + numbers[depth], numbers, target);
        
        // -
        dfs(depth + 1, total - numbers[depth], numbers, target);
    }
}