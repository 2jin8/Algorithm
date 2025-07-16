class Solution {
    public int solution(int[] numbers) {
        boolean[] used = new boolean[10];
        for (int number : numbers) {
            used[number] = true;
        }
        
        int sum = 0;
        for (int i = 1 ; i < 10; i++) {
            if (!used[i]) sum += i;
        }
        return sum;
    }
}