import java.util.*;

public class Solution {
    
    public int[] solution(int []arr) {
        Stack<Integer> stack = new Stack<>();
        for (int num : arr) {
            // 스택이 비어있거나 제일 위의 값과 현재 원소가 같지 않다면 큐에 넣기
            if (stack.isEmpty() || (!stack.isEmpty() && stack.peek() != num)) {
                stack.push(num);
            }
        }
        
        int[] answer = new int[stack.size()];
        for (int i = answer.length - 1; i >= 0; i--) {
            answer[i] = stack.pop();
        }
        return answer;
    }
}