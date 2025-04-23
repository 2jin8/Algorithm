class Solution {
    static int count = 0;
    
    public int solution(int[] numbers, int target) {
        dfs(-1, 0, numbers, target);
        return count;
    }
    
    static void dfs(int depth, int sum, int[] numbers, int target) {
        if (depth + 1 == numbers.length) {
            if (sum == target) count++;
            return;
        }
        
        dfs(depth + 1, sum + numbers[depth + 1], numbers, target);
        dfs(depth + 1, sum - numbers[depth + 1], numbers, target);
    }
}